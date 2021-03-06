package com.terrence.iread.view.activity.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.terrence.iread.R;
import com.terrence.iread.utils.BaseUtils;
import com.terrence.iread.utils.ToastUtils;
import com.terrence.iread.view.activity.IFeedBack;
import com.terrence.iread.view.base.BaseActivity;
import com.terrence.iread.viewmodel.activity.VMFeedBackInfo;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedBackActivity extends BaseActivity implements IFeedBack {

    @BindView(R.id.et_qq)
    EditText mEtQq;
    @BindView(R.id.et_feedback)
    EditText mEtFeedback;
    private VMFeedBackInfo mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new VMFeedBackInfo(this, this);
        setBinddingView(R.layout.activity_feed_back, NO_BINDDING, mModel);
        initThemeToolBar("意见反馈");
    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        if (TextUtils.isEmpty(mEtQq.getText())) {
            ToastUtils.show("请输入QQ号码");
            return;
        }
        if (TextUtils.isEmpty(mEtFeedback.getText())) {
            ToastUtils.show("请描述一下问题或者好的建议哟");
            return;
        }
        mModel.commitFeedBack(mEtQq.getText().toString(), mEtFeedback.getText().toString());
        BaseUtils.hideInput(mEtFeedback);
    }

    @Override
    public void feedBackSuccess() {
        mEtQq.setText("");
        mEtFeedback.setText("");
    }
}
