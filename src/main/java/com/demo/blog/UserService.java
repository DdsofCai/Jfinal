package com.demo.blog;

import com.demo.common.model.User;
import com.demo.utils.SnowflakeIdUtils;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

public class UserService {

    private User userDao = new User().dao();


    /**
     * 用户登录
     */
    public boolean login(String username, String password) {
        User result = userDao.findFirst("SELECT * FROM user WHERE username = ?", username);
        if (result == null) return false;
        if (result.getStr("password").equals(password)) {
            //登录成功
            return true;
        }
        return false;
    }

    /**
     * 分页
     */
    public Page<User> pageList(int page, int size) {
        return userDao.paginate(page, size, "SELECT *", "FROM user ");
    }

    /**
     * getById
     */
    public User getById(int id) {
        return userDao.findById(id);
    }

    /**
     * 根据id删除
     */
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    /**
     * 新增用户
     */
    public boolean addUser(String username, String password) {
        List<User> users = userDao.find("SELECT username FROM user WHERE username = ?", username);
        if (users.size()>1){
            return false;
        }

        SnowflakeIdUtils idWorker = new SnowflakeIdUtils(3, 1);
        new User().set("id", idWorker.nextId()).set("username",username).set("password",password).save();
        return true;
    }
}
