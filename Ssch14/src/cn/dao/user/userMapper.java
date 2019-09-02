package cn.dao.user;

import org.apache.ibatis.annotations.Param;

import cn.pojo.dev_user;

public interface userMapper {
	public dev_user login(@Param("devName")String devName,@Param("devPassword")String devPassword);
}
