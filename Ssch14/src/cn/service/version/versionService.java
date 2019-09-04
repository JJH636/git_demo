package cn.service.version;

import java.util.List;

import cn.pojo.app_version;

public interface versionService {
	public boolean add(app_version version);
	
	public List<app_version> getid(Integer appId);

}
