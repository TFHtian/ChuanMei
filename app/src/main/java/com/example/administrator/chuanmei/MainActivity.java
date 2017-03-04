package com.example.administrator.chuanmei;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.administrator.chuanmei.adapter.MyAdapter;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    CheckBox rbbz;
    CheckBox rbsp;
    CheckBox cktt;
    CheckBox ckyx;
    SliderLayout slide;
    List<String> popContents = new ArrayList<String>();
    MyAdapter mAdapter;
    int newIndex=-1;
    int oldIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate();
        findView();
        initView();
        //备考的对话框点击事件
        rbbz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               if (isChecked){
                   cancelSelect();
                   rbbz.setChecked(true);
                   initPopupWindow(compoundButton);
               }
            }
        });

        //视频点击事件
        rbsp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    cancelSelect();
                    rbsp.setChecked(true);
                    initPopupWindow(compoundButton);
                }
            }
        });

        //头条点击事件
        cktt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    cancelSelect();
                    cktt.setChecked(true);
                }
            }
        });

        //院校点击事件
        ckyx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    cancelSelect();
                    ckyx.setChecked(true);
                }
            }
        });
    }
    //控件
   public void initDate() {
        rbbz= (CheckBox) findViewById(R.id.id_bk);
        rbsp= (CheckBox) findViewById(R.id.id_sp);
        cktt= (CheckBox) findViewById(R.id.id_tt);
        ckyx= (CheckBox) findViewById(R.id.id_yx);
        popContents.add("综合");
        popContents.add("编导");
        popContents.add("播音");
        popContents.add("表演");
    }

    //建popupwindpw
    public void initPopupWindow(View v) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.lv_zonghe_paixu, null);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, 400);
        //设置数据源
        ListView lv = (ListView) view.findViewById(R.id.lv_zonghe_paixu);
        mAdapter = new MyAdapter(MainActivity.this, popContents, popupWindow);
        lv.setAdapter(mAdapter);
        //点击其他地方消失
        popupWindow.setFocusable(true);//设置可获得焦点
        popupWindow.setOutsideTouchable(true);//设置可触摸
        popupWindow.setBackgroundDrawable(new BitmapDrawable());// 设置非PopupWindow区域可触摸
        //显示在windown下
        popupWindow.showAsDropDown(v);
    }
    private void cancelSelect(){
        ckyx.setChecked(false);
        cktt.setChecked(false);
        rbsp.setChecked(false);
        rbbz.setChecked(false);
    }
    //图片的轮播
    public void findView(){
        slide= (SliderLayout) findViewById(R.id.sl_lb);
    }

    public void initView(){  // 将 指示器 和 轮播图 加入到 SlideLayout 中
        TextSliderView textSliderView=new TextSliderView(this);
        textSliderView.description("11111111").image("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488212961063&di=975af236411a5908109dda10b172097f&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb21c8701a18b87d6dcf88618050828381f30fd7b.jpg");//图片链接和文字叙述
        TextSliderView textSliderView2=new TextSliderView(this);
        textSliderView2.description("22222222").image("http://img1.36706.com/lipic/allimg/150108/1T2125063-1.jpg");
        TextSliderView textSliderView3=new TextSliderView(this);
        textSliderView3.description("33333333").image("http://upload.yoyojie.com/2015/0505/1430795943491.png");
        slide.addSlider(textSliderView);
        slide.addSlider(textSliderView2);
        slide.addSlider(textSliderView3);
        slide.setDuration(3000);

    }
    @Override
    protected void onStop() {
        super.onStop();
        slide.stopAutoCycle();//避免内存泄漏
    }
}
