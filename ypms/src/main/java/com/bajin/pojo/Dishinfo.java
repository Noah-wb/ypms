package com.bajin.pojo;

import java.util.Date;

public class Dishinfo {
    private Integer id;

    private String dishname;

    private Double price;

    private String dishdes;

    private String dishimg;

    private Integer dishnum;

    private Integer dishtype;

    private Date createtime;

    private Date updatetime;

    public Dishinfo(Integer id, String dishname, Double price, String dishdes, String dishimg, Integer dishnum, Integer dishtype, Date createtime, Date updatetime) {
        this.id = id;
        this.dishname = dishname;
        this.price = price;
        this.dishdes = dishdes;
        this.dishimg = dishimg;
        this.dishnum = dishnum;
        this.dishtype = dishtype;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Dishinfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname == null ? null : dishname.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDishdes() {
        return dishdes;
    }

    public void setDishdes(String dishdes) {
        this.dishdes = dishdes == null ? null : dishdes.trim();
    }

    public String getDishimg() {
        return dishimg;
    }

    public void setDishimg(String dishimg) {
        this.dishimg = dishimg == null ? null : dishimg.trim();
    }

    public Integer getDishnum() {
        return dishnum;
    }

    public void setDishnum(Integer dishnum) {
        this.dishnum = dishnum;
    }

    public Integer getDishtype() {
        return dishtype;
    }

    public void setDishtype(Integer dishtype) {
        this.dishtype = dishtype;
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