package com.effective.mvc.demo;

import com.effective.mvc.annotation.Autowired;
import com.effective.mvc.annotation.Controller;
import com.effective.mvc.annotation.RequestMapping;
import com.effective.mvc.annotation.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:
 * Created by spark on 17-11-27.
 */
@Controller
@RequestMapping("/web")
public class DemoAction {
    @Autowired
    private DemoService demoService;

    public void query(HttpServletRequest request, HttpServletResponse response, @RequestParams String name){

    }
}
