package cn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.pojo.app_version;
import cn.service.version.versionService;

@Controller
@RequestMapping("/version")
public class versionController {

	@Autowired
	private versionService version;
	
	@RequestMapping(value="/appversionadd/{id}",method=RequestMethod.GET)
	public String add(@PathVariable Integer id,Model model){
		List<app_version> list =null;
		list = version.getid(id);
		model.addAttribute("appVersionList", list);
		return "/developer/appversionadd";
	}
}
