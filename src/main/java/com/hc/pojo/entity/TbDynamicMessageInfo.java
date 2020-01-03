package com.hc.pojo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TbDynamicMessageInfo {
    private Integer tbId;

    private String tbTitle;

    private String tbType;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String tbContent;

    public Integer getTbId() {
        return tbId;
    }

    public void setTbId(Integer tbId) {
        this.tbId = tbId;
    }

    public String getTbTitle() {
        return tbTitle;
    }

    public void setTbTitle(String tbTitle) {
        this.tbTitle = tbTitle;
    }

    public String getTbType() {
        return tbType;
    }

    public void setTbType(String tbType) {
        this.tbType = tbType;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTbContent() {
        return tbContent;
    }

    public void setTbContent(String tbContent) {
        this.tbContent = tbContent;
    }
}