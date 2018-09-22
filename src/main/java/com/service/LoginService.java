package com.service;

import java.util.List;

import com.pojo.Login;

public interface LoginService {
	public Login getLogin(String name);

	public List<Login> getLoginAll();

	public void updataLogin(String name, String pwd);
}