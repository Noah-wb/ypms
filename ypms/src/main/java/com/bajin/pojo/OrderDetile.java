package com.bajin.pojo;

import java.util.Date;

public class OrderDetile {
    private Integer id;

    private Long orderno;

    private Integer dishid;

    private Integer ordernum;

    private Date createtime;

    private Date updatetime;

    public OrderDetile(Integer id, Long orderno, Integer dishid, Integer ordernum, Date createtime, Date updatetime) {
        this.id = id;
        this.orderno = orderno;
        this.dishid = dishid;
        this.ordernum = ordernum;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public OrderDetile() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOrderno() {
        return orderno;
    }

    public void setOrderno(Long orderno) {
        this.orderno = orderno;
    }

    public Integer getDishid() {
        return dishid;
    }

    public void setDishid(Integer dishid) {
        this.dishid = dishid;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "OrderDetile{" +
                "id=" + id +
                ", orderno=" + orderno +
                ", dishid=" + dishid +
                ", ordernum=" + ordernum +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}