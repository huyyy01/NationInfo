package com.nationinfo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NationAdapter extends BaseAdapter {
    //đối tượng hiển thị của adapter
    private List<NationInfo> nationList;
    //khai báo để hệ thống thấy và thực hiện
    private Activity activity;

    public NationAdapter(List<NationInfo> nationList, Activity activity){
        this.activity = activity;
        this.nationList = nationList;
    }

    //override lại các phương thức của BaseAdapter
    @Override
    public int getCount() {
        return nationList.size();
    }

    @Override
    public Object getItem(int i) {
        return nationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //thực hiện lấy view để hiển thị
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.nation_layout, null);
        //dùng thư viện Picasso để hiển thị hình ảnh quốc gia và hiển thị tên quốc gia
        Picasso.get().load(nationList.get(i).countryCode).into((ImageView) view.findViewById(R.id.nation_image));
        TextView nationName = (TextView) view.findViewById(R.id.nation_name);
        nationName.setText(nationList.get(i).countryName);
        return view;
    }
}
