package com.example.administrator.chuanmei;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class DengluActivity extends AppCompatActivity {
    TextView tv_wjmm;
    TextView tv_ljzc;
    Button btndl;
    EditText edt_yh;
    EditText edt_mm;
    OkHttpClient okHttpClient = new OkHttpClient();
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        initDate();

        //忘记密码页面跳转
        tv_wjmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DengluActivity.this,WangJIMiMaActivity.class);
                startActivity(intent);
            }
        });
        //立即注册页面跳转
        tv_ljzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DengluActivity.this,ZhuCeActivity.class);
                startActivity(intent1);
            }
        });

        //登录
        btndl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取输入框的值
                String phone=edt_yh.getText().toString();
                Log.i("DengluActivity", "onClick: -----"+phone);
                String password=edt_mm.getText().toString();
                Log.i("DengluActivity", "onClick: -----"+password);
                String url = "http://php.helpkao.com/api/v2.1/login.php";//接口
                FormBody.Builder formBodyBuild=new FormBody.Builder();
                formBodyBuild.add("phone",phone);
                formBodyBuild.add("password",password);


                Request request=new Request.Builder().url(url).post(formBodyBuild.build()).build();
                Call call=okHttpClient.newCall(request);
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
                        Log.i("DengluActivity", "onResponse: 66666666666666"+result);
                        if (loginBean.getStatus()==1){
                            Log.i("TAG", "onResponse3:5555555 " + loginBean.getMessage());
                            String message= loginBean.getMessage();
                            showToast(message);
                            Intent intent = new Intent(DengluActivity.this,MainActivity.class);
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
    //找控件
    public void initDate(){
        tv_wjmm= (TextView) findViewById(R.id.tv_wanjimima);
        tv_ljzc=(TextView) findViewById(R.id.tv_lijizhuce);
        btndl= (Button) findViewById(R.id.btn_dl);
        edt_yh= (EditText) findViewById(R.id.edt_yonghu);
        edt_mm= (EditText) findViewById(R.id.edt_mima);
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
