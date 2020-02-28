package com.bajin.vo;

public class CarinfoVO {
    private Integer id;
    private String dishname;
    private Double price;
    private Integer carnum;

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

    public Integer getCarnum() {
        return carnum;
    }

    public void setCarnum(Integer carnum) {
        this.carnum = carnum;
    }

    public CarinfoVO() {
    }

    public CarinfoVO(Integer id, String dishname, Double price, Integer carnum) {
        this.id = id;
        this.dishname = dishname;
        this.price = price;
        this.carnum = carnum;
    }

    @Override
    public String toString() {
        return "CarinfoVO{" +
                "id=" + id +
                ", dishname='" + dishname + '\'' +
                ", price=" + price +
                ", carnum=" + carnum +
                '}';
    }
}
