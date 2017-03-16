package com.shen.netclient.test.bean;

import java.util.List;

public class PlatformData {
	
	private List<Product> Platform;
	private String moreImgUrl;
	private String gesturePwdOpenFlag;
	private String newQrCodeSwitch;
	
	public List<Product> getPlatform() {
		return Platform;
	}

	public void setPlatform(List<Product> platform) {
		Platform = platform;
	}

	public String getMoreImgUrl() {
		return moreImgUrl;
	}

	public void setMoreImgUrl(String moreImgUrl) {
		this.moreImgUrl = moreImgUrl;
	}

	public String getGesturePwdOpenFlag() {
		return gesturePwdOpenFlag;
	}

	public void setGesturePwdOpenFlag(String gesturePwdOpenFlag) {
		this.gesturePwdOpenFlag = gesturePwdOpenFlag;
	}

	public String getNewQrCodeSwitch() {
		return newQrCodeSwitch;
	}

	public void setNewQrCodeSwitch(String newQrCodeSwitch) {
		this.newQrCodeSwitch = newQrCodeSwitch;
	}
}
