package com.demo.blog;


import com.demo.common.model.User;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.plugin.activerecord.Page;
import jdk.nashorn.internal.ir.annotations.Ignore;


@Before(UserInterceptor.class)
public class UserController extends Controller {

    @Inject
    UserService userService;


    public void index(){
        render("login.html");
    }

    public void login(){
        User user = getModel(User.class);
        boolean result = userService.login(user.getStr("username"), user.getStr("password"));
        if (result) {
            render("user.html");
        } else {
            render("/common/false.html");
        }
    }

    public void download(){
        renderFile("file.txt");
    }

    public void pageList(){
        Integer page= getParaToInt("page");
        if (page==null) page=1;
        //默认10条
        setAttr("userPage",userService.pageList(getParaToInt(page,1), 10));
        render("userList.html");
    }

    public void addUser(){
        User model = getModel(User.class);
        boolean result = userService.addUser(model.getStr("username"), model.getStr("password"));
        if (result){
            renderText("add success");
        }else{
            renderText("add false");
        }
    }
}
