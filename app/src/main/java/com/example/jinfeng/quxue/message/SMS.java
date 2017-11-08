package com.example.jinfeng.quxue.message;

import android.app.Notification;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static cn.smssdk.SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE;


/**
 * Created by alienware on 2017/8/8.
 */

public class SMS {
    public boolean issuccess=true;//GetMessage方法中用于返给调用者判断是否成功
    public boolean ismatched=false;//compareSMScode中用于返给调用者判断是否成功
    public EventHandler GetMessageEventHandler;//发送短信的监听器(方法GetMessage)
    public EventHandler compareSMScodeEventHandler;//提交验证码的监听器(方法compareSMScode)
    public String consoleGetMessage;//服务器端返给我们的错误（json数据）
    public String consolecompareSMScode;//服务器返给我们的错误（json数据）
    public String returndata;//验证通过后回返给我们手机号和国家代码，在第二个方法中已注释

    public boolean GetMessage(String telenumber){
        SMSSDK.getVerificationCode("86",telenumber);//发短信,调试的时候不要摁的太凶，一天只有20条短信调试
        // 创建EventHandler对象
        GetMessageEventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {//参数详情：http://wiki.mob.com/android-%E7%9F%AD%E4%BF%A1sdk%E6%93%8D%E4%BD%9C%E5%9B%9E%E8%B0%83/
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable)data;
                    consoleGetMessage = throwable.getMessage();
                    issuccess=false;
                } else {

                }
            }

        };

        SMSSDK.registerEventHandler(GetMessageEventHandler); //注册短信回调

        return issuccess;
    }


    public boolean compareSMScode(String telenumber,String smscode){//验证code是否正确
        SMSSDK.submitVerificationCode("86",telenumber,smscode);

        compareSMScodeEventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {//参数详情：http://wiki.mob.com/android-%E7%9F%AD%E4%BF%A1sdk%E6%93%8D%E4%BD%9C%E5%9B%9E%E8%B0%83/
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        returndata=data.toString();
                        ismatched=true;
                        /*
                        * 验证成功会返回：
                        * {
                        * phone=18367025336
                        * country=86
                        * }
                        * */
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    Throwable throwable = (Throwable)data;
                    consolecompareSMScode = throwable.getMessage();
                    //此语句代表接口返回失败
                    //获取验证码失败。短信验证码验证失败（用flage标记来判断）
                }
            }
        };
        SMSSDK.registerEventHandler(compareSMScodeEventHandler); //注册短信回调
        return ismatched;
    }


}
