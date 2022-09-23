package com.example.a739connect;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.net.URL;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showToast(String message){
        //只能线程里面调用,函数弃用
        Looper.prepare();
        Toast success=Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG);
        success.show();
        Looper.loop();
    }
    public void showToast1(String message){
        //只能主函数调用
        Toast success=Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG);
        success.show();
    }
    public void send_Print_Mes(String mes){
        //打印在屏幕上，主线程才能调用
        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setText(mes);
    }
    public void onClick(View v) throws IOException {
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText("点击了按钮");
        Handler handler = new Handler() {
            //test
            public void handleMessage(Message mes){
                super.handleMessage(mes);
                String print_Mes=(String) mes.obj;
                TextView textView=(TextView) findViewById(R.id.textView);
                textView.setText(print_Mes);
            }
        };
        Handler handler_Show_Toast=new Handler(){
            public void handleMessage(Message mes){
                super.handleMessage(mes);
                String show_Mes=(String) mes.obj;
                showToast1(show_Mes);
            }
        };
        //showToast("点击了按钮");
        Calendar a = Calendar.getInstance();
        int day = a.get(Calendar.DAY_OF_MONTH);//获取日期，day
        textView.setText("日期："+String.valueOf(day));
        ///System.out.println(day);

        //showToast("号数："+String.valueOf(day));
//        String[] pwd = {
        //lqy的密码
//                "b74173ecb70acdd9",
//                "c285e72bcf0588a5",
//                "ddece0ddc51df519",
//                "ef5de658c563a4bf",
//                "c48e163e095938e7",
//                "9daaa7de33a8b581",
//                "6e3a0af029b8859b",
//                "d1eaed0fcd4b4114",
//                "8194d9d30f51dcfe",
//                "378dceb0009886e8",
//                "fba53519389e86ad",
//                "1a1be2ad357ebde6",
//                "3ed5df8ef565dd61",
//                "c6d167033330f2fa",
//                "8875cb6ec680280c",
//                "f2544e3272d604f3",
//                "d835ad69d84aaa72",
//                "b148d1f3c340301d",
//                "65aa8c6e832c29a9",
//                "12c4af27aea553e8",
//                "7ed43a12ae1fda89",
//                "e26ffe523751817f",
//                "f2f40b2db653cf1b",
//                "bd414088b25a7a2e",
//                "00ede862fdbea028",
//                "6d50195c46fe4491",
//                "367db3a9a72ea6af",
//                "a0c00a21fde8940f",
//                "18a26c64ed55c5d5",
//                "21fe1121bd0cc2c5",
//                "1ba6b7e0bb2aae4c"
//        };
        String[] pwd = {
                //2f45c8d5208cc9f4
        //wjk的密码
                "b74173ecb70acdd9",
                "c285e72bcf0588a5",
                "ddece0ddc51df519",
                "ef5de658c563a4bf",
                "c48e163e095938e7",
                "9daaa7de33a8b581",
                "6e3a0af029b8859b",
                "d1eaed0fcd4b4114",
                "8194d9d30f51dcfe",
                "378dceb0009886e8",
                "fba53519389e86ad",
                "1a1be2ad357ebde6",
                "3ed5df8ef565dd61",
                "c6d167033330f2fa",
                "8875cb6ec680280c",
                "f2544e3272d604f3",
                "d835ad69d84aaa72",
                "b148d1f3c340301d",
                "65aa8c6e832c29a9",
                "12c4af27aea553e8",
                "7ed43a12ae1fda89",
                "e26ffe523751817f",
                "f2f40b2db653cf1b",
                "bd414088b25a7a2e",
                "00ede862fdbea028",
                "6d50195c46fe4491",
                "367db3a9a72ea6af",
                "a0c00a21fde8940f",
                "18a26c64ed55c5d5",
                "21fe1121bd0cc2c5",
                "1ba6b7e0bb2aae4c"
        };
        final String user = "173******35";//lqy
        //final String user = "158******92";//wjk
        final String password = pwd[day - 1];
        final String[] status = {""};
        final int[] test = {0};//如果test==1则有网
        //test[0]=0;

//        Thread testNet=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    String testURL="https://www.baidu.com/";
//                    Connection.Response conn=Jsoup.connect(testURL).execute();
//                    if(conn.statusCode()==200){
//                        test[0] =1;
//                        System.out.println("连上网了");
//                    }else{
//                        test[0] =0;
//                        System.out.println("未连上网络");
//                    }
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        testNet.start();
        //showToast("测试一下");
        Thread thread=new Thread(new Runnable() {

            public void send_Print_Mes(String mes){
                //将消息打印在屏幕上，线程用
                Message message=handler.obtainMessage();
                message.obj=mes;
                handler.sendMessage(message);
                return;
            }
            public void send_Show_Toast(String mes){
                //用于showToast,线程调用即可
                Message message=handler_Show_Toast.obtainMessage();
                message.obj=mes;
                handler_Show_Toast.sendMessage(message);
                return;
            }
            @Override
            public void run() {
                TextView textView=(TextView) findViewById(R.id.textView);
                String testURL="https://www.baidu.com/";
                String redictURL="http://www.msftconnecttest.com/redirect";
                redictURL="http://baidu.com/";
                redictURL="http://100.64.0.1/";
                //testURL="http://baidu.com/";
                send_Print_Mes("开始测试网络,(https访问百度)");
                //Connection.Response response=Jsoup.connect(testURL).execute();
                //Connection.Response response=Jsoup.connect(testURL).timeout(2000).execute();//这行语句会抛异常
                int test_Code=-1;//测试百度网址所用
                try{
                    Connection.Response response=Jsoup.connect(testURL).timeout(2000).execute();
                    test_Code=response.statusCode();
                    send_Print_Mes("访问百度的Status code:"+String.valueOf(test_Code));
                }catch(IOException e){
                    e.printStackTrace();
                }
                if(test_Code==-1){
                    send_Print_Mes("目前未联网！");
                }
                else if(test_Code==200){
                    //textView.setText("你已经连上网络了嗷");
                    //setText("你已经连上网络了嗷");
                    send_Print_Mes("你已经连上网络了嗷");
                    test[0]=1;
                    return;
                }
                try{
                    //开始连接电信的ap
                    Connection response001=Jsoup.connect(redictURL).followRedirects(true);
                    Connection.Response response=response001.execute();
                    send_Print_Mes("连接电信ap状态码："+String.valueOf(response.statusCode()));//电信ap情况是200
                    String testUrl=String.valueOf(response.url());
                    send_Print_Mes("测试连接的url:"+testUrl);
                    //System.out.println(testUrl);
                    int judge=testUrl.length();
                    send_Print_Mes("重定向网址长度："+String.valueOf(judge));
                    if(judge<=25){
                        //这里是http://100.64.0.1的长度
                        send_Print_Mes("你已经连上网络了呢");
                        //setText("你已经连上网络了呢");
                        test[0]=1;
                        return;
                    }
                    send_Print_Mes("response url:"+String.valueOf(response.url()));
                    URL oneURL=response.url();
                    //http://58.53.199.144:8001?userip=100.64.41.104&wlanacname=&nasip=59.172.216.49&usermac=20-0d-b0-38-08-a7
                    String useURL=String.valueOf(response.url());//get userip and MAC address
                    Pattern ip_pattern= Pattern.compile("userip=(.*?)&wlanacname");
                    Matcher ip_matcher=ip_pattern.matcher(useURL);
                    String userip="";//获取路由器的ip
                    while(ip_matcher.find()){
                        userip=String.valueOf(ip_matcher.group(1));
                        System.out.println("User IP:"+userip);
                        //setText("用户ip:"+userip);
                        send_Print_Mes("用户ip:"+userip);
                    }
                    Pattern mac_pattern=Pattern.compile("usermac=(.*)");
                    Matcher mac_matcher=mac_pattern.matcher(useURL);
                    String usermac="";//获取路由器MAC
                    while(mac_matcher.find()){
                        usermac=String.valueOf(mac_matcher.group(1));
                        System.out.println("User MAC:"+usermac);
                        //setText("用户mac:"+usermac);
                        send_Print_Mes("用户mac:"+usermac);
                    }
                    String remoteip="";//获取登陆远程ip
                    Pattern remoteip_pattern=Pattern.compile("nasip=(.*?)&usermac");
                    Matcher remoteip_matcher=remoteip_pattern.matcher(useURL);
                    while(remoteip_matcher.find()){
                        remoteip=String.valueOf(remoteip_matcher.group(1));
                        System.out.println("Remote IP:"+remoteip);
                        //setText("电信远程登录地址:"+remoteip);
                        send_Print_Mes("电信远程登录地址:"+remoteip);
                    }

                    String url1="http://58.53.199.144:8001/?userip="+userip+"&wlanacname=&nasip="+remoteip+"&usermac="+usermac+"&aidcauthtype=0";
                    String url2="http://58.53.199.144:8001/wispr_auth.jsp";

                    String User_Agent="CDMA+WLAN(Mios)";
                    String Content_Type="application/x-www-form-urlencoded";

                    Connection conn1=Jsoup.connect(url1);
                    conn1.header("User-Agent",User_Agent);

                    Document doc2=conn1.get();
                    System.out.println(doc2);
                    // <loginurl><![CDATA[http://58.53.199.144:8001/wispr_auth.jsp?sessionid=970958396&aidcauthtype=0&usermac=20-0d-b0-38-08-a7&wlanacname=059.172.216.049&wlanuserip=100.64.41.104]]>
                    //        </loginurl>
                    //System.out.println("test");
                    Elements ele2=doc2.getElementsByTag("loginurl");
                    Elements ele_time=doc2.getElementsByTag("AidcAuthAttr1");
                    String now_time=String.valueOf(ele_time);//获得时间，第二次登录用
                    now_time=now_time.replace("\n","");
                    now_time=now_time.replace(" ","");
                    now_time=now_time.replace("<aidcauthattr1>","");
                    now_time=now_time.replace("</aidcauthattr1>","");
                    System.out.println("NowTime:"+now_time);
                    String loginurl=String.valueOf(ele2);
                    System.out.println("未处理的Loginurl"+loginurl);
                    Pattern loginurl_pattern=Pattern.compile("CDATA(.*?)]]>");
                    Matcher loginurl_matcher=loginurl_pattern.matcher(loginurl);
                    String LoginURL="";
                    while(loginurl_matcher.find()){
                        LoginURL=String.valueOf(loginurl_matcher.group(1));
                        //System.out.println("Login URL:"+LoginURL);
                    }
                    LoginURL=LoginURL.replace("[","");

                    //开始第二次登录
                    String UserHard1="!^Iqnd0";
                    String UserHard2="!^Adcm0";
                    //这里使用userhard2
                    System.out.println("this");
//                    String data="UserName=" + UserHard2 + user + "&Password=" + password + "&AidcAuthAttr1=" + now_time +
//                    "&AidcAuthAttr3=keuyGQlK&AidcAuthAttr4=zrDgXllCChyJHjwkcRwhygP0&AidcAuthAttr5=kfe1GQhXdGqOFDteego5zwP9IsNoxX7djTWspPrYm1A%3D%3D&" +
//                    "AidcAuthAttr6=5Ia4cQhDfXSFbTtUDGY1yx8%3D&AidcAuthAttr7=6ZWiVlwdNiHMXCpOagQv2w2MQs0ohTWJnTu8qK5OibhCydTpTxkI88wadKPWby%2F2PKCVaZUxglbBs96%2FtmLE89M8AJ6y28o7qolpFep%2FcYFFRLd7H4MAMrDUMRO0F%2B93jh14fiAZYmtk9hdp%2BZ5w%2BjMQUoV4TCtM9VJ07XQwxlMVg%2F0YKrS1s3hXAstdQ1fvdSn3nAVGgdxc%2BJQDrQ%3D%3D    "
//                    &AidcAuthAttr8=jPSyBQxVaXWTQWUaakluj06scJ98nyqCyX7y%2FLUk1OkXiNjkXhVGvJhyTuLDaCPhK%2FOFJttlxxiVqNKupnDXkp9%2BR9D9j8p2j5h8FOxoatMaGu0oRdk%3D&createAuthorFlag=0";
                    Map<String,String> header=new HashMap<String,String>();
                    header.put("User-Agent",User_Agent);
                    header.put("Content-Type",Content_Type);
                    System.out.println("LOgin url:"+LoginURL);
                    Connection conn3=Jsoup.connect(LoginURL);
                    conn3.headers(header);
                    //conn3.data(data);
                    Map<String,String> data=new HashMap<String,String>();
                    data.put("UserName",UserHard2 + user);
                    data.put("Password",password);
                    data.put("AidcAuthAttr1",now_time);
                    data.put("AidcAuthAttr3","keuyGQlK");
                    data.put("AidcAuthAttr4","zrDgXllCChyJHjwkcRwhygP0");
                    data.put("AidcAuthAttr5","kfe1GQhXdGqOFDteego5zwP9IsNoxX7djTWspPrYm1A%3D%3D");
                    data.put("AidcAuthAttr6","5Ia4cQhDfXSFbTtUDGY1yx8%3D");
                    data.put("AidcAuthAttr7","6ZWiVlwdNiHMXCpOagQv2w2MQs0ohTWJnTu8qK5OibhCydTpTxkI88wadKPWby%2F2PKCVaZUxglbBs96%2FtmLE89M8AJ6y28o7qolpFep%2FcYFFRLd7H4MAMrDUMRO0F%2B93jh14fiAZYmtk9hdp%2BZ5w%2BjMQUoV4TCtM9VJ07XQwxlMVg%2F0YKrS1s3hXAstdQ1fvdSn3nAVGgdxc%2BJQDrQ%3D%3D");
                    data.put("AidcAuthAttr8","jPSyBQxVaXWTQWUaakluj06scJ98nyqCyX7y%2FLUk1OkXiNjkXhVGvJhyTuLDaCPhK%2FOFJttlxxiVqNKupnDXkp9%2BR9D9j8p2j5h8FOxoatMaGu0oRdk%3D");
                    data.put("createAuthorFlag","0");
                    conn3.data(data);
                    Document doc3=conn3.post();
                    System.out.println(doc3);
                    //ReplyMessage
                    //String status="";
                    status[0] =String.valueOf(doc3.getElementsByTag("ReplyMessage"));
                    System.out.println(status[0]);
                /*
                <replymessage>
 50：认证成功
</replymessage>
                * */
                    String successShow=status[0].replace("<replymessage>","");
                    successShow=successShow.replace("</replymessage>","");
                    //showToast(successShow);
                    send_Print_Mes(successShow);

                }catch(IOException e){
                    send_Print_Mes("连接电信ap失败！");
                    e.printStackTrace();
                }
            }
        });

        if(test[0]==0){
            send_Print_Mes("开始调用线程");
            showToast1("开始调用线程");
            thread.start();
        }else{
            showToast1("你已经连上网了哟");
        }

    }
    public void logout(View v) throws IOException{
        showToast1("功能开发中.........");
    }
}
