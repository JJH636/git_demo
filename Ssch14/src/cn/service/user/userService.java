package cn.service.user;

import cn.pojo.dev_user;

public interface userService {
	public dev_user login(String devName,String devPassword);
}
