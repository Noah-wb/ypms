package com.bajin.vo;

import java.util.Date;

public class OrderVO {
    private Long orderno;
    private Double totalprice;
    private String realname;
    private String tel;
    private String address;
    private String state;
    private String createtime;

    public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public OrderVO() {
    }

    public OrderVO(Long orderno, Double totalprice, String realname, String tel, String address, String state, String createtime) {
        this.orderno = orderno;
        this.totalprice = totalprice;
        this.realname = realname;
        this.tel = tel;
        this.address = address;
        this.state = state;
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderno=" + orderno +
                ", totalprice=" + totalprice +
                ", realname='" + realname + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
