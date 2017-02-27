package com.shen.netclient.bean;

/**
 * 与配置文件 xml目录中结构对应
 */
public class UrlData {

	/**
	 * 对应请求的url的key部分
	 */
	private String key;
	private long expires;
	private String netType;
	private String url;
	/**
	 * 对应的mockservice类
	 */
	private String mockClass;

	public UrlData() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMockClass() {
		return mockClass;
	}

	public void setMockClass(String mockClass) {
		this.mockClass = mockClass;
	}
}
