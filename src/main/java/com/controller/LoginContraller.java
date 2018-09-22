package com.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import wsq.SsmResult;
import com.pojo.Login;
import com.service.LoginService;
import com.service.LoginServiceImpl;

@Controller
public class LoginContraller {
	@Autowired
	private LoginService loginServiceImpl;

	@RequestMapping("updataLogin")
	@ResponseBody
	public void updataLogin() {
		loginServiceImpl.updataLogin("wsq", "222222");

	}

	@RequestMapping("getLogin")
	@ResponseBody
	public SsmResult getLogin() {
		List<Login> list = loginServiceImpl.getLoginAll();
		return SsmResult.ok(list);
	}
}