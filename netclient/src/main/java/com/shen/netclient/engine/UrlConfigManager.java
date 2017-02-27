package com.shen.netclient.engine;

import android.content.res.XmlResourceParser;

import com.shen.netclient.bean.UrlData;
import com.shen.netclient.util.LogUtils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 表示使用mock数据时的管理器
 */
public class UrlConfigManager {

	public static boolean MockServiceEnable = false;

	private  ArrayList<UrlData> urlList;
	private int mConfigFileId = -1;

	private static UrlConfigManager mUrlConfigManager;

	private UrlConfigManager(){

	}
	public static UrlConfigManager getUrlConfigManager(){
		if(null == mUrlConfigManager){
			mUrlConfigManager = new UrlConfigManager();
		}
		return mUrlConfigManager;
	}

	public void initUrlConfigManager(int configId){
		this.mConfigFileId = configId;
		MockServiceEnable = true;
		fetchUrlDataFromXml();
	}

	private void fetchUrlDataFromXml() {
		if(-1 == mConfigFileId ){
			LogUtils.w("配置文件id不正确");
		}

		if(null == urlList){
			urlList = new ArrayList<UrlData>();
		}

		final XmlResourceParser xmlParser = NetClientLib.getLibInstance().getMobileContext()
				.getResources().getXml(mConfigFileId);

		int eventCode;
		try {
			eventCode = xmlParser.getEventType();
			while (eventCode != XmlPullParser.END_DOCUMENT) {
				switch (eventCode) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if ("Node".equals(xmlParser.getName())) {
						final String key = xmlParser.getAttributeValue(null,
								"Key");
						final UrlData urlData = new UrlData();
						urlData.setKey(key);
						urlData.setExpires(Long.parseLong(xmlParser
								.getAttributeValue(null, "Expires")));
						urlData.setNetType(xmlParser.getAttributeValue(null,
								"NetType"));
						urlData.setMockClass(xmlParser.getAttributeValue(null,
								"MockClass"));
						urlData.setUrl(xmlParser.getAttributeValue(null, "Url"));
						urlList.add(urlData);
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				default:
					break;
				}
				eventCode = xmlParser.next();
			}
		} catch (final XmlPullParserException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			xmlParser.close();
		}
	}

	public UrlData findURL(final String findKey) {
		// 如果urlList还没有数据（第一次），或者被回收了，那么（重新）加载xml
		if (urlList == null || urlList.isEmpty()){
			fetchUrlDataFromXml();
		}

		for (UrlData data : urlList) {
			if (findKey.equals(data.getKey())) {
				return data;
			}
		}
		return null;
	}
}