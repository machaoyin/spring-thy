package com.mcy.springthy.controller;

import com.mcy.springthy.entity.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    //映射“/”请求
    @RequestMapping("/")
    public String index(){
        System.out.println("index方法被调用。。。。");
        //根据Thymeleaf默认模板，将返回resources/templates/index.html
        return "index";
    }

    //登录请求
    @PostMapping("login")
    public String login(String username, String password){
        System.out.println("login方法被调用。。。。");
        System.out.println("login登录名："+username+", 密码："+password);
        //重定向到main请求
        return "redirect:/main";
    }

    //跳转登录成功后页面请求
    @RequestMapping(value="/main")
    public String main(){
        System.out.println("main方法被调用。。。。");
        //返回main页面
        return "main";
    }

    //将表达式保存到作用域，用于测试Thymeleaf表达式访问数据
    @RequestMapping(value = "/regexptest")
    public String regexptest(HttpServletRequest request, HttpSession session){
        //将数据保存到request作用域中
        request.setAttribute("book", "springboot整合Thymeleaf");
        //将数据保存到session作用域中
        session.setAttribute("belong", "spring");
        //将数据保存到servletContext(application)作用域当中
        request.getServletContext().setAttribute("name", "Thymeleay模板");
        return "success";
    }

    //将数据保存到作用域，用于测试Thymeleaf的条件判断
    @RequestMapping(value = "/iftest")
    public String iftest(WebRequest webRequest, ModelMap map){
        //将数据保存到request作用域，SpringMVC推荐使用WebRequest或者ModelMap
        webRequest.setAttribute("username", "fkit", webRequest.SCOPE_REQUEST);
        webRequest.setAttribute("age", 21, webRequest.SCOPE_REQUEST);
        //ModelMap传递
        map.put("role", "admin");
        return "success2";
    }

    //将数据保存到作用域，用于测试Thymeleaf的循环获取数据
    @RequestMapping(value = "/eachtest")
    public String eachtest(ModelMap map){
        //模拟数据库数据保存到list集合
        List<Book> book = new ArrayList<>();
        book.add(new Book(1, "HTML5+CSS3", "张三", "20"));
        book.add(new Book(2, "JavaScript", "李四", "30"));
        book.add(new Book(3, "java编程思想", "王五", "40"));
        //将数据保存到Request作用域中
        map.put("book", book);
        return "success3";
    }
}
