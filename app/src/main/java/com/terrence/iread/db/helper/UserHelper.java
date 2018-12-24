package com.terrence.iread.db.helper;

import com.terrence.iread.db.entity.UserBean;
import com.terrence.iread.db.gen.DaoSession;
import com.terrence.iread.db.gen.UserBeanDao;


public class UserHelper {
    private static volatile UserHelper sInstance;
    private static DaoSession daoSession;
    private static UserBeanDao userBeanDao;

    public static UserHelper getsInstance() {
        if (sInstance == null) {
            synchronized (UserHelper.class) {
                if (sInstance == null) {
                    sInstance = new UserHelper();
                    daoSession = com.terrence.iread.db.helper.DaoDbHelper.getInstance().getSession();
                    userBeanDao = daoSession.getUserBeanDao();
                }
            }
        }
        return sInstance;
    }

    /**
     * 保存用户
     *
     * @param userBean
     */
    public void saveUser(UserBean userBean) {
        userBeanDao.insertOrReplace(userBean);
    }

    /**
     * 更新用户信息
     *
     * @param userBean
     */
    public void updateUser(UserBean userBean) {
        userBeanDao.update(userBean);
    }


    /**
     * 删除用户
     */
    public void removeUser() {
        userBeanDao.deleteAll();
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    public UserBean findUserByName(String username) {
        return userBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq(username)).unique() != null
                ? userBeanDao.queryBuilder().where(UserBeanDao.Properties.Name.eq(username)).unique() : null;
    }


}
