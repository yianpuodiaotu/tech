package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.TestEntity;
import com.example.demo.rest.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(value="测试")
@Controller
@RequestMapping("test")
public class TestController {
	protected static final Logger logger = LoggerFactory.getLogger(TestController.class);
	
	
	@ApiOperation(value = "测试", notes = "测试", produces = "application/json")
    @RequestMapping(value = "/test", method = {RequestMethod.POST})
    public @ResponseBody
    BaseResponse test(
    		 @ApiParam(value = "添加测试") @RequestBody TestEntity test
    		) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(test);
        return baseResponse;
    }

}
