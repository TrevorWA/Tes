package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.Setting;

import com.service.SettingService;

import wsq.SsmResult;

@Controller
public class SettingContraller {
	@Autowired
	private SettingService SettingServiceImpl;

	@RequestMapping("updataSetting")
	@ResponseBody
	public void updataLogin(int id, int smart, float water, float food, float o2) {
		SettingServiceImpl.updataSetting(id, smart, water, food, o2);
	}

	@RequestMapping("getSettingAll")
	@ResponseBody
	public SsmResult getLogin() {
		List<Setting> list = SettingServiceImpl.getSettingAll();
		return SsmResult.ok(list);
	}
}
