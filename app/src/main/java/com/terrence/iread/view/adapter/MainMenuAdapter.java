package com.terrence.iread.view.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.terrence.iread.R;
import com.terrence.iread.model.MainMenuBean;
import com.terrence.iread.utils.DimenUtils;

import java.util.List;


public class MainMenuAdapter extends BaseQuickAdapter<MainMenuBean,BaseViewHolder> {


    public MainMenuAdapter(@Nullable List<MainMenuBean> data) {
        super(R.layout.adapter_main_menu,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainMenuBean item) {
        TextView mTvName=helper.getView(R.id.tv_name);
        mTvName.setText(item.getName());
        mTvName.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(item.getIcon()),null,null,null);
        mTvName.setCompoundDrawablePadding(DimenUtils.dp2px(10));
    }
}
