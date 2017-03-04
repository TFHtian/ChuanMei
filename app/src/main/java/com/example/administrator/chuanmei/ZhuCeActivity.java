package com.example.administrator.chuanmei;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.chuanmei.entity.LoginBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZhuCeActivity extends AppCompatActivity {
    EditText edt_yh;
    EditText edt_yzm;
    EditText edt_mm;
    EditText edt_cmm;
    Button but_yzm;
    Button but_zc;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        initDate();
        //验证码按钮获取验证码
        but_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //获取输入框号码值
            String mb=edt_yh.getText().toString();
                Log.i("ZhuCeActivity", "onClick: "+mb);
            String url = "http://php.helpkao.com/api/v2.1/code.php";//接口
            final Request request=new Request.Builder().url(url+"?phone="+mb).build();
                OkHttpClient client=new OkHttpClient();
                Call call=client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("TAG", "onFailure: 失败原因: " + e.getMessage());
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result=response.body().string();
                        //json解析
                        Gson gson=new Gson();
                        LoginBean loginBean=gson.fromJson(result,LoginBean.class);
                        String message= loginBean.getMessage();
                        showToast(message);
                    }
                });

            }
        });

        //注册
        but_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框的值
                String phone=edt_yh.getText().toString();
                Log.i("ZhuCeActivity", "onClick: ------"+phone);
                String code=edt_yzm.getText().toString();
                String password=edt_mm.getText().toString();
                String re_pssword=edt_cmm.getText().toString();
                String url = "http://phptest.helpkao.com/api/v2.1/register.php";//接口
                FormBody.Builder formBodyBuild=new FormBody.Builder();
                formBodyBuild.add("password",password);
                formBodyBuild.add("re_password",re_pssword);
                formBodyBuild.add("phone",phone);
                formBodyBuild.add("code",code);
                Request request=new Request.Builder().url(url).post(formBodyBuild.build()).build();
                OkHttpClient client=new OkHttpClient();
                Call call=client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // 请求失败执行某些操作
                        Log.i("TAG", "onFailure: 失败原因: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        // 响应成功； 得到返回的 JSon 数据
                        String result=response.body().string();
                        //json解析
                        Gson gson=new Gson();
                        LoginBean loginBean=gson.fromJson(result,LoginBean.class);
                        if (loginBean.getStatus()==1){
                            String message= loginBean.getMessage();
                            showToast(message);
                            Log.i("TAG", "onResponse1: " + loginBean.getMessage());
                            Intent intent = new Intent(ZhuCeActivity.this,DengluActivity.class);
                            startActivity(intent);
                        }else if (loginBean.getStatus()==0){
                            String message= loginBean.getMessage();
                            showToast(message);
                            Log.i("TAG", "onResponse1: " + loginBean.getMessage());
                        }

                    }
                });

            }
        });

    }
    public void initDate(){
        edt_yh= (EditText) findViewById(R.id.edt_mb);
        edt_yzm= (EditText) findViewById(R.id.edt_yzm);
        edt_mm= (EditText) findViewById(R.id.edt_mm);
        edt_cmm= (EditText) findViewById(R.id.edt_cmm);
        but_yzm= (Button) findViewById(R.id.but_ma);
        but_zc= (Button) findViewById(R.id.but_zc);
    }


    public void showToast(final String message) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),message ,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
