package com.mapper;

import com.pojo.Sensor;
import com.pojo.SensorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SensorMapper {
    int countByExample(SensorExample example);

    int deleteByExample(SensorExample example);

    int deleteByPrimaryKey(String sensorId);

    int insert(Sensor record);

    int insertSelective(Sensor record);

    List<Sensor> selectByExample(SensorExample example);

    Sensor selectByPrimaryKey(String sensorId);

    int updateByExampleSelective(@Param("record") Sensor record, @Param("example") SensorExample example);

    int updateByExample(@Param("record") Sensor record, @Param("example") SensorExample example);

    int updateByPrimaryKeySelective(Sensor record);

    int updateByPrimaryKey(Sensor record);
}