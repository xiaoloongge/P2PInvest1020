package com.p2pinvest1020.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.p2pinvest1020.R;
import com.p2pinvest1020.bean.InvestAllBean;
import com.p2pinvest1020.ui.MyProgress;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14.
 */

public abstract class BaseInvestAllAdapter<T> extends BaseAdapter {

    public List<T> list = new ArrayList<>();

    public BaseInvestAllAdapter(List<T> list) {
        if (list != null && list.size() > 0) {
            this.list.clear();
            this.list.addAll(list);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = getChildView(position,convertView,parent);
        return view;
    }

    public abstract View getChildView(int position, View convertView, ViewGroup parent);

}
