package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.LoginMapper;
import com.pojo.Login;
import com.pojo.LoginExample;
import com.pojo.LoginExample.Criteria;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginMapper accountMapper;

	public List<Login> getLoginAll() {
		LoginExample accountExample = new LoginExample();
		Criteria criteria = accountExample.createCriteria();
		criteria.andNameIsNotNull();
		List<Login> loginList = accountMapper.selectByExample(accountExample);
		for (Login account : loginList) {
			account.toString();
		}
		return loginList;
	}

	public Login getLogin(String name) {
		return accountMapper.selectByPrimaryKey(name);
	}

	public void updataLogin(String name, String pwd) {

		Login record = new Login();
		record.setName(name);
		record.setPassword(pwd);

		accountMapper.updateByPrimaryKey(record);
	}

}