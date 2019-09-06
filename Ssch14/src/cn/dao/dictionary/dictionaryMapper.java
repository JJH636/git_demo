package cn.dao.dictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_info;
import cn.pojo.data_dictionary;

public interface dictionaryMapper {
	public List<data_dictionary> getlist1()throws Exception;
	
	public List<data_dictionary> getlist2()throws Exception;
	
	public List<data_dictionary> getlist3(@Param("typeCode")String typeCode)throws Exception;
}
