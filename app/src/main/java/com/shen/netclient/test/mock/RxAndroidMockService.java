package com.shen.netclient.test.mock;

import com.google.gson.Gson;
import com.shen.netclient.net.MockService;
import com.shen.netclient.test.bean.BaseData;
import com.shen.netclient.test.bean.Test;
import com.shen.netclient.test.bean.TestData;
import com.shen.netclient.util.LogUtils;


/**
 * Created by shenjianli on 2016/7/8.
 * 测试使用的本地MockService用来返回请求的json字符串
 */
public class RxAndroidMockService extends MockService {
    @Override
    public String getJsonData() {

        BaseData<Test> baseData = new BaseData<>();
        //创建测试数据对象
        TestData testData = new TestData();
        //进行想着的赋值
        testData.setCity("taiyuan");
        testData.setCityid("33333");

        //测试数据传测试对象中
        Test test = new Test();
        test.setTestData(testData);

        baseData.setData(test);
        baseData.setReqCode(BaseData.REQ_SUCC);
        baseData.setMsg("请求成功");
        //使用Gson把对象转化为Json字符串
        String resultStr =  new Gson().toJson(baseData);
        LogUtils.i("获得的json字符串为：" + resultStr);

        //返回json字符串
        return resultStr;
    }
}
