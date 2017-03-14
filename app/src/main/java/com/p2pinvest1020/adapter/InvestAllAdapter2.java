package com.p2pinvest1020.adapter;

import android.view.View;
import android.widget.TextView;

import com.p2pinvest1020.R;
import com.p2pinvest1020.bean.InvestAllBean;
import com.p2pinvest1020.utils.UiUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

public class InvestAllAdapter2 extends BaseInvestAllAdapter02<InvestAllBean.DataBean> {

    public InvestAllAdapter2(List<InvestAllBean.DataBean> list) {
        super(list);
    }

    @Override
    public View initView() {
        return UiUtils.getView(R.layout.adapter_invest_all);
    }

    @Override
    public void setData(InvestAllBean.DataBean dataBean, View view) {

        TextView pname = (TextView) view.findViewById(R.id.p_name);
        pname.setText(dataBean.getName());
    }
}
