package com.service;

import java.util.List;


import com.pojo.Setting;

public interface SettingService {

	public Setting getSetting(int id);

	public List<Setting> getSettingAll();

	public void updataSetting(int id,int smart,int water,int wendu,int o2);
}
