package com.bajin.vo;

public class DishinfoVO {
    private Integer id;

    private String dishname;

    private Double price;

    private String dishdes;

    private String dishimg;

    private String typename;

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
        this.dishname = dishname;
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
        this.dishdes = dishdes;
    }

    public String getDishimg() {
        return dishimg;
    }

    public void setDishimg(String dishimg) {
        this.dishimg = dishimg;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public DishinfoVO() {
    }

    public DishinfoVO(Integer id, String dishname, Double price, String dishdes, String dishimg, String typename) {
        this.id = id;
        this.dishname = dishname;
        this.price = price;
        this.dishdes = dishdes;
        this.dishimg = dishimg;
        this.typename = typename;
    }

    @Override
    public String toString() {
        return "DishinfoVO{" +
                "id=" + id +
                ", dishname='" + dishname + '\'' +
                ", price=" + price +
                ", dishdes='" + dishdes + '\'' +
                ", dishimg='" + dishimg + '\'' +
                ", typename='" + typename + '\'' +
                '}';
    }
}
