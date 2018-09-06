package com.example.oauth.templates.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.oauth.domain.Msg;


@Controller
public class TemplatesController {
	/**
	 * 动态跳转
	 * 跳转到templates目录下的html
	 * @param request
	 * @return
	 */
	@GetMapping("/templates")
	String test(HttpServletRequest request){
		request.setAttribute("key", "hello world");//向页面传参数
		return "/index";
	}
	
	/**
	 * 动态跳转
	 * 跳转到templates目录下的login
	 * @param request
	 * @return
	 */
	@RequestMapping("/uaa/login")
	String login(HttpServletRequest request){
		if(request.getAttribute("loginError") != null){
			request.setAttribute("key", request.getAttribute("loginError"));//向页面传参数
		}else{
			request.setAttribute("key", "Hello World");//向页面传参数
		}
		return "/login";
	}
	/**
	 * 静态跳转
	 * 跳转到static目录下的html
	 * @return
	 */
	@GetMapping("/html")
	public String html(){
		return "redirect:/index.html";
	}
	
	/**
	 * 动态跳转
	 * 跳转到templates目录下的html
	 * @param request
	 * @return
	 */
	@GetMapping("/admin/index")
	String adminIndex(HttpServletRequest request){
		request.setAttribute("key", "hello world");//向页面传参数
		return "/admin/index";
	}
	
	
	@RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","额外信息，只对管理员显示");
        model.addAttribute("msg", msg);
        return "home";
    }
	
}
