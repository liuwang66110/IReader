package com.terrence.iread.view.activity.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.terrence.iread.IReaderApplication;
import com.terrence.iread.R;
import com.terrence.iread.db.helper.UserHelper;
import com.terrence.iread.model.AppUpdateBean;
import com.terrence.iread.utils.LoadingHelper;
import com.terrence.iread.utils.SharedPreUtils;
import com.terrence.iread.view.activity.ISetting;
import com.terrence.iread.view.base.BaseActivity;
import com.terrence.iread.viewmodel.activity.VMSettingInfo;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements ISetting {

    @BindView(R.id.btn_out)
    Button mBtnOut;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    private VMSettingInfo mModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new VMSettingInfo(this, this);
        setBinddingView(R.layout.activity_setting, NO_BINDDING, mModel);
    }


    @Override
    protected void initView() {
        super.initView();
        initThemeToolBar("设置");
        mTvVersion.setText("版本号：v." + IReaderApplication.packageInfo.versionName);
    }

    @OnClick({R.id.btn_out, R.id.rl_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_out:
                new MaterialDialog.Builder(this)
                        .title("退出登录")
                        .content("是否退出登录?")
                        .positiveText("确定")
                        .onPositive((dialog, which) -> {
                            UserHelper.getsInstance().removeUser();
                            SharedPreUtils.getInstance().sharedPreRemove("username");
                            finish();
                        })
                        .negativeText("取消")
                        .onNegative((dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
                break;
            case R.id.rl_version:
                mModel.appUpdate(true);
                break;
        }

    }

    @Override
    public void appUpdate(AppUpdateBean appUpdateBean) {
//        AppUpdateUtils.getInstance().appUpdate(this, appUpdateBean);
    }


    @Override
    public void showLoading() {
        LoadingHelper.getInstance().showLoading(mContext);
    }

    @Override
    public void stopLoading() {
        LoadingHelper.getInstance().hideLoading();
    }
}
