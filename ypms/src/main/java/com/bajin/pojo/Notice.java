package com.bajin.pojo;

import java.util.Date;

public class Notice {
    private Integer id;

    private String tittle;

    private String content;

    private Date createtime;

    private Date updatetime;

    public Notice(Integer id, String tittle, String content, Date createtime, Date updatetime) {
        this.id = id;
        this.tittle = tittle;
        this.content = content;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Notice() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle == null ? null : tittle.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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