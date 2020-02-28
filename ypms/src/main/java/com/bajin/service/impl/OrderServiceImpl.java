package com.bajin.service.impl;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.utils.ZxingUtils;
import com.bajin.bo.OrderDetailBO;
import com.bajin.dao.CarinfoMapper;
import com.bajin.dao.OrderDetileMapper;
import com.bajin.dao.OrderinfoMapper;
import com.bajin.pojo.Carinfo;
import com.bajin.pojo.OrderDetile;
import com.bajin.pojo.Orderinfo;
import com.bajin.service.ICarService;
import com.bajin.service.IOrderService;
import com.bajin.utils.BigDecimalUtil;
import com.bajin.vo.CarinfoVO;
import com.bajin.vo.OrderVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderinfoMapper orderinfoMapper;
    @Autowired
    private ICarService carService;
    @Autowired
    private CarinfoMapper carinfoMapper;
    @Autowired
    private OrderDetileMapper orderDetileMapper;
    @Override
    public List<OrderVO> queryOrder(Integer id) {
        List<OrderVO> orderVOS = orderinfoMapper.queryOrder(id);
        return orderVOS;
    }

    @Override
    public Long createOrder(Integer id) {
        //总价变量
        Double totalPrice = 0.0;
        //订单编号
        long orderNo = (long) (System.currentTimeMillis()+(int) (Math.random()*100));
        //查询餐车计算订单总价
        List<CarinfoVO> carinfoList = carService.selectCar(id);

        for (int i = 0;i < carinfoList.size();i++){
            double price = BigDecimalUtil.mul(carinfoList.get(i).getPrice(),carinfoList.get(i).getCarnum()).doubleValue();
            totalPrice = BigDecimalUtil.add(totalPrice,price).doubleValue();
        }
        //生成订单信息
        Orderinfo orderinfo = new Orderinfo(null,id,orderNo,totalPrice,"未支付",null,null);
        int insert = orderinfoMapper.insert(orderinfo);
        //生成订单详情信息
        List<Carinfo> carList = carinfoMapper.queryCarByUid(id);
        List<OrderDetile> detileList = new ArrayList<>();
        carList.forEach(s ->{
            OrderDetile orderDetile = new OrderDetile(null,orderNo,s.getDishid(),s.getCarnum(),null,null);
            detileList.add(orderDetile);
        });
        orderDetileMapper.insetOrderDetaile(detileList);
        //清空当前用户的餐车
        carinfoMapper.cancleAll(id);

        return orderNo;
    }


    @Override
    public Map<String, Object> pay(Integer userId, Long orderNo) {
        Map<String, Object> resultMap = new HashMap<>();
        Orderinfo orderinfo = orderinfoMapper.selectByOrderNo(orderNo);
        System.out.println(orderinfo);
        if(orderinfo == null){

            resultMap.put("status",1);
            resultMap.put("msg","用户没有该订单");
            return resultMap;
        }
        resultMap.put("orderNo",String.valueOf(orderinfo.getOrderno()));

        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = orderinfo.getOrderno().toString();

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = new StringBuilder().append("yunmall扫码支付,订单号:").append(outTradeNo).toString();

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = orderinfo.getTotalprice().toString();

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = new StringBuilder().append("订单:").append(outTradeNo).append("购买商品共").append(totalAmount).append("元").toString();

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();

        List<OrderDetailBO> orderItemList = orderDetileMapper.getByOrderNo(orderNo);
        for(OrderDetailBO orderItem : orderItemList){
            //主键  商品名称 单价 数量
            GoodsDetail goods = GoodsDetail.newInstance(orderItem.getId().toString(), orderItem.getDishname(),
                    orderItem.getPrice().longValue(),
                    orderItem.getOrdernum());
            goodsDetailList.add(goods);
        }

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                .setNotifyUrl("http://4qcbx9.natappfree.cc/order/alipay_callback")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);

        Configs.init("zfbinfo.properties");
        AlipayTradeService tradeService = new  AlipayTradeServiceImpl.ClientBuilder().build();
        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                System.out.println("支付宝预下单成功");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                String path = "C:\\Users\\Administrator\\HBuilderProjects\\ypms\\crcode";
                File folder = new File(path);
                if(!folder.exists()){
                    folder.setWritable(true);
                    folder.mkdirs();
                }

                // 需要修改为运行机器上的路径
                String qrPath = String.format(path+"/qr-%s.png",response.getOutTradeNo());
                String qrFileName = String.format("qr-%s.png",response.getOutTradeNo());
                ZxingUtils.getQRCodeImge(response.getQrCode(), 256, qrPath);

                String qrUrl = "http://mall.bajin.com/ypms/crcode/"+qrFileName;
                resultMap.put("qrUrl",qrUrl);

                Map<String, Object> map = new HashMap<>();
                map.put("status",0);
                map.put("data",resultMap);
                return map;

            case FAILED:
                System.out.println("支付宝预下单失败!!!");
                resultMap.put("status",1);
                resultMap.put("msg","支付宝预下单失败!!!");
                return resultMap;

            case UNKNOWN:
                System.out.println("系统异常，预下单状态未知!!!");
                resultMap.put("status",1);
                resultMap.put("msg","系统异常，预下单状态未知!!!");
                return resultMap;

            default:
                System.out.println("不支持的交易状态，交易返回异常!!!");
                resultMap.put("status",1);
                resultMap.put("msg","不支持的交易状态，交易返回异常!!!");
                return resultMap;
        }
        //return null;
    }

    /**
     * 支付宝回调
     * @param params
     * @return
     */
    @Override
    public String aliCallback(Map<String, String> params) {
        Long orderNo = Long.parseLong(params.get("out_trade_no"));
        String tradeNo = params.get("trade_no");
        String tradeStatus = params.get("trade_status");
        Orderinfo orderinfo = orderinfoMapper.selectByOrderNo(orderNo);
        if(orderinfo == null){
            return "failed";
        }

        if("TRADE_SUCCESS".equals(tradeStatus)){

            //order.setPaymentTime(params.get("gmt_payment"));
            orderinfo.setState("已支付");
            orderinfoMapper.updateByPrimaryKeySelective(orderinfo);
        }
        return "success";
    }

    @Override
    public Map<String, Object> queryOrderPayStatus(Long orderNo) {
        Map<String, Object> map = new HashMap<>();

        Orderinfo orderinfo = orderinfoMapper.selectByOrderNo(orderNo);
        if(orderinfo != null){
            if("已支付".equals(orderinfo.getState())){
                map.put("status",0);
                map.put("data",true);
            }else{
                map.put("status",0);
                map.put("data",false);
            }
        }
        return map;
    }


    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            if (StringUtils.isNotEmpty(response.getSubCode())) {
            }
        }
    }



}
