package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.mapper.SettingMapper;

import com.pojo.Setting;
import com.pojo.SettingExample;
import com.pojo.SettingExample.Criteria;

@Service
public class SettingServiceImpl implements SettingService {

	@Autowired
	private SettingMapper accountMapper;
	
	@Override
	public Setting getSetting(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Setting> getSettingAll() {
		// TODO Auto-generated method stub
		SettingExample accountExample = new SettingExample();
		Criteria criteria = accountExample.createCriteria();
		criteria.andIdIsNotNull();
		List<Setting> SettingList = accountMapper.selectByExample(accountExample);
		for (Setting account : SettingList) {
			account.toString();
		}
		return SettingList;
	}

	@Override
	public void updataSetting(int id, int smart, float water, float food, float o2) {
		// TODO Auto-generated method stub
		
	}

}
