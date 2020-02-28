package com.bajin.pojo;

import java.util.Date;

public class Orderinfo {
    private Integer id;

    private Integer userid;

    private Long orderno;

    private Double totalprice;

    private String state;

    private Date createtime;

    private Date updatetime;

    public Orderinfo(Integer id, Integer userid, Long orderno, Double totalprice, String state, Date createtime, Date updatetime) {
        this.id = id;
        this.userid = userid;
        this.orderno = orderno;
        this.totalprice = totalprice;
        this.state = state;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Orderinfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
}