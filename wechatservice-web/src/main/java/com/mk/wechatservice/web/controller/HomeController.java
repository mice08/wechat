package com.mk.wechatservice.web.controller;

import com.mk.wechatservice.biz.module.UMember;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
	private final Logger logger = Logger.getLogger(HomeController.class);


	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> check(HttpSession httpSession, UMember userCheckDto) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		result.put("check", "123");
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}


}
