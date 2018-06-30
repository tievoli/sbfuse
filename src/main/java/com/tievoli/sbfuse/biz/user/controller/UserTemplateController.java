package com.tievoli.sbfuse.biz.user.controller;

import com.tievoli.sbfuse.framework.exception.AuthException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/userView")
@Api(tags = "测试模板引擎")
public class UserTemplateController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation("测试模板引擎")
    public ModelAndView testUserView() {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/error/4xx");
        mav.addObject("title","I am a template page...");

//        testHtmlException();

        return mav;
    }

    private void testHtmlException(){
        if(true){
            throw new AuthException("认证异常...");
        }
    }

}
