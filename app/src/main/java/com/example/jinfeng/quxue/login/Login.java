package com.example.jinfeng.quxue.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.jinfeng.quxue.ActivityCollector;
import com.example.jinfeng.quxue.BaseActivity;
import com.example.jinfeng.quxue.MainActivity;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import android.widget.ImageView;
import com.example.jinfeng.quxue.R;
public class Login extends BaseActivity {
    private static final String TAG = "Login";
    private static final String APP_ID = "1106223283";
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private Button loginWithMobile;
    private Button loginWithQQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            // 透明状态栏
            getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        ImageView backColorChange =(ImageView) findViewById(R.id.back_color_change);
        Animation backColorAnim = AnimationUtils.loadAnimation(Login.this,R.anim.background_color_change);
        backColorAnim.setFillAfter(true);
        backColorChange.setAnimation(backColorAnim);

        loginWithQQ = (Button) findViewById(R.id.login_with_qq);
        loginWithMobile =(Button)findViewById(R.id.login_with_mobile);

        setButtonAnim(loginWithMobile,10);
        setButtonAnim(loginWithQQ,250);

        loginWithMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Login.this,LoginWithMobile.class);
                startActivity(intent);


            }
        });

        mTencent = Tencent.createInstance(APP_ID,Login.this.getApplicationContext());//传入参数APPID和全局Context上下文
        loginWithQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
                 官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
                 第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
                mIUiListener = new BaseUiListener();//all表示获取所有权限
                mTencent.login(Login.this,"all", mIUiListener);
                mTencent=Tencent.createInstance("1106223283",Login.this);//初始化QQ操作主对象

            }
        });

    }
    public void setButtonAnim(Button btn,int Offtime){
        Animation buttonAnim = AnimationUtils.loadAnimation(Login.this,R.anim.button_push_in);
        buttonAnim.setStartOffset(Offtime);
        btn.setAnimation(buttonAnim);
    }
    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener{
        @Override
        public void onComplete(Object response) {//这里的数据是正常返回的
            //Toast.makeText(Login.this, response.toString(), Toast.LENGTH_LONG).show();
            //Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            /*response toString后的Json示例：
            {
	            "ret":0,
	            "openid":"",
	            "access_token":"",
	            "pay_token":"",
                "expires_in":"",
                "pf":"",
                "pfkey":"",
                "msg":"",
                "login_cost":153,
                "query_authority_cost":192,
                "authority_cost":2480
            } */
            try {
                String openID = obj.getString(Constants.PARAM_OPEN_ID);   // 作为判断用户登录的唯一值
                String accessToken = obj.getString(Constants.PARAM_ACCESS_TOKEN);
                String expires = obj.getString(Constants.PARAM_EXPIRES_IN);
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                //QQToken qqToken = mTencent.getQQToken();
                //mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo=new UserInfo(Login.this,mTencent.getQQToken());
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    /**
                     * 返回用户信息样例
                     *
                     * {"is_yellow_year_vip":"0",
                     * "ret":0,
                     * "figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/40",
                     * "figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
                     * "nickname":"攀爬←蜗牛",
                     * "yellow_vip_level":"0",
                     * "is_lost":0,"msg":"",
                     * "city":"黄冈","
                     * figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/50",
                     * "vip":"0",
                     * "level":"0",
                     * "figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
                     * "province":"湖北",
                     * "is_yellow_vip":"0",
                     * "gender":"男",
                     * "figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/30"}
                     */
                    public void onComplete(Object response) {
                        try{

                            JSONObject jo=(JSONObject) response;
                            int ret=jo.getInt("ret");
                            String printmsg="";
                            String nickname=jo.getString("nickname");
                            String gender=jo.getString("gender");
                            String head=jo.getString("figureurl_2");
                            String province=jo.getString("province");
                            String city=jo.getString("city");
                            printmsg=nickname+gender+city+province;
                            SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                            editor.clear();
                            editor.putString("nickname",nickname);
                            editor.putString("headurl",head);
                            editor.putString("gender",gender);
                            editor.apply();
                            boolean ifFirstLogin=false;
                            if(ifFirstLogin){
                                Intent intent =new Intent(Login.this,Interest_select.class);  /**  这里作做数据库的操作  在数据库观察是否为d第一次登录*/
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                      //      Toast.makeText(Login.this,printmsg,Toast.LENGTH_LONG).show();
                        }catch(Exception e){
                        }
                    }
                    @Override
                    public void onError(UiError uiError) {
                        Toast.makeText(Login.this,"错误"+uiError.toString(),Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onCancel() {
                        Toast.makeText(Login.this,"登陆取消",Toast.LENGTH_LONG).show();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



        @Override
        public void onError(UiError uiError) {
            Toast.makeText(Login.this, "授权失败", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {
            Toast.makeText(Login.this, "授权取消", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(mTencent!=null){
            mTencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
    }
}

