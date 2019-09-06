package cn.controller;

import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

import cn.pojo.app_info;
import cn.pojo.app_version;
import cn.pojo.dev_user;
import cn.service.info.InfoService;
import cn.service.version.versionService;

@Controller
@RequestMapping("/version")
public class versionController {
	private Logger logger=Logger.getLogger(versionController.class);

	@Autowired
	private versionService version;
	
	@Autowired
	private InfoService infoservice;
	
	@RequestMapping(value="/appversionadd",method=RequestMethod.GET)
	public String add(@RequestParam Integer id,Model model){
		List<app_version> list =null;
		list = version.getid(id);
		model.addAttribute("appVersionList", list);
		model.addAttribute("appVersion",id);
		return "/developer/appversionadd";
	}
	
	@RequestMapping(value="/appversionmodify",method=RequestMethod.GET)
	public String modify(@RequestParam Integer vid,Integer aid,Model model){
		List<app_version> list =null;
		list = version.getid(aid);
		app_version list2 = null;
		list2 = version.getBYid(aid);
		model.addAttribute("appVersionList", list);
		model.addAttribute("appVersion", list2);
		return "/developer/appversionmodify";
	}
	
	@RequestMapping(value="/appview/{id}",method=RequestMethod.GET)
	public String view(@PathVariable Integer id,Model model){
		app_info list =null;
		list = infoservice.getid2(id);
		List<app_version> list2 = null;
		list2 = version.getid(id);
		model.addAttribute("appVersionList", list2);
		model.addAttribute("appInfo", list);
		return "/developer/appinfoview";
	}
	
	@RequestMapping(value="/appversionmodifysave",method=RequestMethod.POST)
	public String modify(app_version ver,HttpSession session){
		ver.setModifyBy(((dev_user)session.getAttribute("devUser")).getId());
		ver.setModifyDate(new Date());
		if(version.mofidy(ver)){
			return"redirect:/app/list";
		}
		return "/developer/appversionmodify";
	}
	
	@RequestMapping(value="/addversionsave",method=RequestMethod.POST)
	public String addrr(app_version ver,HttpSession sission,HttpServletRequest request,
			@RequestParam(value="a_downloadLink",required=false)MultipartFile attach){
		String fileUploadError = null;		//��Ƭ��·��
		String apkFileName = null;
		String downloadLink =null;
		//�ж��ļ��Ƿ�Ϊ��
		if(!attach.isEmpty()){
			//������Ҫ�����·��
			String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
			logger.debug("path========>" + path);
			String oldFileName = attach.getOriginalFilename();		//ԭ�ļ���
			logger.debug("oldFileName========>" + oldFileName);
			String prefix = FilenameUtils.getExtension(oldFileName);	//Դ�ļ���׺
			
			int filesize = 600000;
			logger.debug("oldFileName========>" + attach.getSize());
			if(attach.getSize() > filesize){
				request.setAttribute("uploadFileError", "�ϴ���С���ܳ���600KB");
				System.out.println("apk����");
				return "/developer/appversionadd";
			} else if(prefix.equalsIgnoreCase("apk")){
				//�ļ���������
				String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.apk";
				logger.debug("fileName========>" + fileName);
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()){
					targetFile.mkdirs();
				}
				//����
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("uploadFileError", "�ļ��ϴ�ʧ�ܣ�");
					System.out.println("apk�ϴ�ʧ��");
					return "/developer/appversionadd";
				}
				//����Ҫ���浽���ݿ��
				fileUploadError = path+File.separator + fileName;
				apkFileName = "com.bithack.apparatus-"+ver.getVersionNo()+".apk";
				downloadLink = request.getContextPath() + "/statics/" + fileName;
			} else {
				request.setAttribute("uploadFileError", "�ļ���ʽ����ȷ��");
				System.out.println("apk��ʽ����ȷ");
				return "/developer/appversionadd";
			}
		}
		ver.setCreatedBy(((dev_user)sission.getAttribute("devUser")).getId());
		ver.setCreationDate(new Date());
		ver.setDownloadLink(fileUploadError);
		ver.setDownloadLink(downloadLink);
		ver.setApkFileName(apkFileName);
			if(version.add(ver)){
				infoservice.modifyid(ver.getAppId());
				return "redirect:/app/list";
			}
			System.out.println("�������");
			return "developer/appversionadd";
	}
	
	@RequestMapping(value="/delfile")
	@ResponseBody
	public Object deletePhoto(@RequestParam BigInteger id){
		HashMap<String, String> reHashMap = new HashMap<String,String>();
		if(id == null){
			reHashMap.put("result", "0.0");
		}else{
		   boolean i = infoservice.modifyPhoto(id);
		   if(i){
			   reHashMap.put("delResult", "success");
			}else{
			reHashMap.put("delResult", "failed");
			}
		}
		return JSONArray.toJSONString(reHashMap);
	}
}
