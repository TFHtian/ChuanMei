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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WangJIMiMaActivity extends AppCompatActivity {
    EditText edt_shouji;
    EditText edt_duanxin;
    EditText edt_mima;
    EditText edt_cmima;
    Button but_hq;
    Button but_tj;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_jimi_ma);
        initDate();

        //获取验证码
        but_hq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框号码值
                String phone=edt_shouji.getText().toString();
                String url = "http://php.helpkao.com/api/v2.1/code.php";//接口
                final Request request=new Request.Builder().url(url+"?phone="+phone).build();
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

        //提交
                  but_tj.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View view) {
                      //获取输入框的值
                          String phone=edt_shouji.getText().toString();
                          String code=edt_duanxin.getText().toString();
                          String passwopd=edt_mima.getText().toString();
                          String confirm=edt_cmima.getText().toString();
                          String url = "http://php.helpkao.com/api/v2.1/find_passwd.php";//接口
                          final Request request=new Request.Builder().url(url+"?phone="+phone+"&code="+code+"&new="+passwopd+"&confirm="+confirm).build();
                          OkHttpClient client=new OkHttpClient();
                          Call call=client.newCall(request);
                          call.enqueue(new Callback() {
                              @Override
                              public void onFailure(Call call, IOException e) {
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
                                      Log.i("TAG", "onResponse: --- " +loginBean.getMessage());
                                      String message= loginBean.getMessage();
                                      showToast(message);
                                      Intent intent = new Intent(WangJIMiMaActivity.this,DengluActivity.class);
                                      startActivity(intent);

                                  }else if (loginBean.getStatus()==0){
                                      Log.i("TAG", "onResponse1: " + loginBean.getMessage());

                                  }
                              }
                          });
                      }
                  });

            }

    //控件
    public void initDate(){
        edt_shouji= (EditText) findViewById(R.id.edt_shouji);
        edt_duanxin= (EditText) findViewById(R.id.edt_duanxin);
        edt_mima= (EditText) findViewById(R.id.edt_xmima);
        edt_cmima= (EditText) findViewById(R.id.edt_cmima);
        but_hq= (Button) findViewById(R.id.but_ma);
        but_tj= (Button) findViewById(R.id.but_tj);
    }
    public void showToast(final String message) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
