package com.bajin.pojo;

import java.util.Date;

public class Dishtype {
    private Integer id;

    private String typename;

    private Date createtime;

    private Date updatetime;

    public Dishtype(Integer id, String typename, Date createtime, Date updatetime) {
        this.id = id;
        this.typename = typename;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Dishtype() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
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