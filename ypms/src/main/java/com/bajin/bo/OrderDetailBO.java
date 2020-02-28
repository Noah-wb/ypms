package com.bajin.bo;

public class OrderDetailBO {
    private Integer id;
    private String dishname;

    private Double price;
    private Integer ordernum;

    public OrderDetailBO() {
    }

    public OrderDetailBO(Integer id, String dishname, Double price, Integer ordernum) {
        this.id = id;
        this.dishname = dishname;
        this.price = price;
        this.ordernum = ordernum;
    }

    @Override
    public String toString() {
        return "OrderDetailBO{" +
                "id=" + id +
                ", dishname='" + dishname + '\'' +
                ", price=" + price +
                ", ordernum=" + ordernum +
                '}';
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
        this.dishname = dishname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }
}
