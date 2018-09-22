package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.SensorMapper;
import com.pojo.Sensor;
import com.pojo.SensorExample;
import com.pojo.SensorExample.Criteria;

@Service
public class SensorServiceImpl implements SensorService {
	@Autowired
	private SensorMapper accountMapper;

	public List<Sensor> getSensorAll() {
		SensorExample accountExample = new SensorExample();
		Criteria criteria = accountExample.createCriteria();
		criteria.andSensorIdIsNotNull();
		List<Sensor> SensorList = accountMapper.selectByExample(accountExample);
		for (Sensor account : SensorList) {
			account.toString();
		}
		return SensorList;
	}

	public Sensor getSensor(String sensorId) {

		return accountMapper.selectByPrimaryKey(sensorId);
	}

	public void updataSensor(String sensorId, String sensorData) {

		Sensor record = new Sensor();
		record.setSensorId(sensorId);
		record.setSensorData(sensorData);

		accountMapper.updateByPrimaryKey(record);
	}
}
