package com.service;

import java.util.List;

import com.pojo.Sensor;

public interface SensorService {

	public Sensor getSensor(String sensor_id);

	public List<Sensor> getSensorAll();

	public void updataSensor(String sensor_id, String sensor_data);
}
