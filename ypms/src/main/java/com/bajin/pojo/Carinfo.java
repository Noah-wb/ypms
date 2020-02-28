package com.bajin.pojo;

import java.util.Date;

public class Carinfo {
    private Integer id;

    private Integer dishid;

    private Integer userid;

    private Integer carnum;

    private Date createtime;

    private Date updatetime;

    public Carinfo(Integer id, Integer dishid, Integer userid, Integer carnum, Date createtime, Date updatetime) {
        this.id = id;
        this.dishid = dishid;
        this.userid = userid;
        this.carnum = carnum;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Carinfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDishid() {
        return dishid;
    }

    public void setDishid(Integer dishid) {
        this.dishid = dishid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getCarnum() {
        return carnum;
    }

    public void setCarnum(Integer carnum) {
        this.carnum = carnum;
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