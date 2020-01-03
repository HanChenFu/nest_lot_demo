package com.hc.pojo.reqBean;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class TestReqBean {
	//限购：1～5
    @Min(value = 1, message = "min Value is above zero")
    @Max(value = 10, message = "最大值不超过10")
	private Integer tbId;
    @NotBlank
    private String tbTitle;
    private String tbType;
    private Date releaseTime;
    private Date createTime;
    private Date updateTime;
    private String tbContent;
}
