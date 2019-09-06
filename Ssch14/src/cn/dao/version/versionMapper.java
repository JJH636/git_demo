package cn.dao.version;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_version;

public interface versionMapper {
	public int add(app_version version)throws Exception;
	
	public List<app_version> getid(@Param("appId")Integer appId)throws Exception;
	
	public int mofidy(app_version version)throws Exception;
	
	public app_version getBYid(@Param("appId")Integer appId)throws Exception;
	
	public int mofidyzip(@Param("id")BigInteger id)throws Exception;
}
