package cn.service.version;

import java.math.BigInteger;
import java.util.List;

import cn.pojo.app_version;

public interface versionService {
	public boolean add(app_version version);
	
	public List<app_version> getid(Integer appId);
	
	public boolean mofidy(app_version version);
	
	public app_version getBYid(Integer appId);

	public boolean mofidyzip(BigInteger id);
}
