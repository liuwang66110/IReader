package com.terrence.iread.viewmodel.activity;

import android.content.Context;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.terrence.iread.utils.rxhelper.RxObserver;
import com.terrence.iread.api.UserService;
import com.terrence.iread.db.entity.UserBean;
import com.terrence.iread.db.helper.UserHelper;
import com.terrence.iread.utils.SharedPreUtils;
import com.terrence.iread.utils.ToastUtils;
import com.terrence.iread.view.activity.IUserInfo;
import com.terrence.iread.viewmodel.BaseViewModel;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class VMUserInfo extends BaseViewModel {
    IUserInfo iUserInfo;

    public VMUserInfo(Context mContext, IUserInfo iUserInfo) {
        super(mContext);
        this.iUserInfo = iUserInfo;
    }


    public void getUserInfo() {
        iUserInfo.showLoading();
        RxHttpUtils.getSInstance().addHeaders(tokenMap())
                .createSApi(UserService.class)
                .getUserInfo()
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<UserBean>() {
                    @Override
                    protected void onError(String errorMsg) {
                        iUserInfo.stopLoading();
                    }

                    @Override
                    protected void onSuccess(UserBean data) {
                        iUserInfo.stopLoading();
                        iUserInfo.userInfo(data);
                    }
                });
    }


    /**
     * 上传用户头像
     *
     * @param imagePath 图像本地路径
     */
    public void uploadAvatar(String imagePath) {
        iUserInfo.showLoading();
        String username = SharedPreUtils.getInstance().getString("username", "");
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
        RxHttpUtils.getSInstance().addHeaders(tokenMap())
                .createSApi(UserService.class)
                .avatar(body)
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<String>() {
                    @Override
                    protected void onError(String errorMsg) {
                        iUserInfo.stopLoading();
                    }

                    @Override
                    protected void onSuccess(String data) {
                        iUserInfo.stopLoading();
                        iUserInfo.uploadSuccess(data);
                        UserBean userBean = UserHelper.getsInstance().findUserByName(username);
                        userBean.setIcon(data);
                        UserHelper.getsInstance().updateUser(userBean);
                    }
                });
    }

    /**
     * 更改用户密码
     *
     * @param password 用户密码
     */
    public void updatePassword(String password) {
        iUserInfo.showLoading();
        RxHttpUtils.getSInstance().addHeaders(tokenMap())
                .createSApi(UserService.class)
                .updatePassword(password)
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<String>() {
                    @Override
                    protected void onError(String errorMsg) {
                        iUserInfo.stopLoading();
                    }

                    @Override
                    protected void onSuccess(String data) {
                        iUserInfo.stopLoading();
                        ToastUtils.show(data);
                    }
                });
    }


    /**
     * 更改用户密码
     *
     * @param nickname 用户昵称
     * @param brief    用户简介
     */
    public void updateUserInfo(String nickname, String brief) {
        iUserInfo.showLoading();
        String username = SharedPreUtils.getInstance().getString("username", "");
        RxHttpUtils.getSInstance().addHeaders(tokenMap())
                .createSApi(UserService.class)
                .updateUserInfo(nickname, brief)
                .compose(Transformer.switchSchedulers())
                .subscribe(new RxObserver<String>() {
                    @Override
                    protected void onError(String errorMsg) {
                        iUserInfo.stopLoading();
                    }

                    @Override
                    protected void onSuccess(String data) {
                        iUserInfo.stopLoading();
                        ToastUtils.show(data);
                        UserBean userBean = UserHelper.getsInstance().findUserByName(username);
                        userBean.setBrief(brief);
                        userBean.setNickname(nickname);
                        UserHelper.getsInstance().updateUser(userBean);
                    }
                });
    }

}
