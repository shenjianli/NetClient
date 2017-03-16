package com.shen.netclient.test.bean;

import java.util.List;

public class PlatformRes {

	private PlatformData data;
	private String isReview;
	private String status;
	private String type;
	private String message;
	private List datas;
	private String moreImgUrl;
	
	public String getIsReview() {
		return isReview;
	}
	public void setIsReview(String isReview) {
		this.isReview = isReview;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public PlatformData getData() {
		return data;
	}
	public void setData(PlatformData data) {
		this.data = data;
	}
	public String getMoreImgUrl() {
		return moreImgUrl;
	}
	public void setMoreImgUrl(String moreImgUrl) {
		this.moreImgUrl = moreImgUrl;
	}
}
