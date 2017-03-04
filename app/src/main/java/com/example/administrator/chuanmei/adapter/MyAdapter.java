package com.example.administrator.chuanmei.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.chuanmei.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    PopupWindow popupWindow;
    private List<String> datas;
    private Context mContext;

    public MyAdapter(Context mContext, List<String> datas, PopupWindow popupWindow) {
        this.mContext = mContext;
        this.datas = datas;
        this.popupWindow = popupWindow;
    }

    @Override
    public int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_item_gouxuan, null);    // item 的布局文件
            viewHolder = new ViewHolder();
            viewHolder.linearLayout = (RelativeLayout) convertView.findViewById(R.id.rel_xuan);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_gx);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.im_gx);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(datas.get(position));   // 给item设置值
        // 相当于是 item的点击事件，这样在Activity中就不用写ListView.setOnItemClickListener()了
        // 主要用于点击的时候显示ImageView，注意开始先得隐藏掉其他的

        hintImageView(viewHolder);
        final View finalConvertView = convertView;
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.i("TAG", "onClick: " + getItem(position).toString());
                v.setVisibility(View.VISIBLE);
                if (v.getVisibility() == View.VISIBLE) {
                    // 明明表示显示了，为什么界面上不显示呢？？
               Log.i("TAG", "onClick: 可见啊; current location:" + position);

                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
                popupWindow.setFocusable(false);
                popupWindow.dismiss();    // 点击完item后让popupWindow消失
                viewHolder.imageView.setVisibility(View.VISIBLE);  // popupWindow消失了，重新隐藏掉勾选

            }
        });

        return convertView;
    }

    private void hintImageView(ViewHolder viewHolder) {
        viewHolder.imageView.setVisibility(View.GONE );

    }

    class ViewHolder {
        RelativeLayout linearLayout;  // 相当于是Item
        TextView textView;  // item中的TextView
        ImageView imageView;  // 用于显示勾选的ImageView
    }
}
