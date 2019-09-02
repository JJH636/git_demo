package cn.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.user.userMapper;
import cn.pojo.dev_user;

@Service("userService")
public class userServiceImpl implements userService {

	@Autowired
	private userMapper usermapper;
	
	@Override
	public dev_user login(String devName, String devPassword) {
		// TODO Auto-generated method stub
		return usermapper.login(devName, devPassword);
	}

}
