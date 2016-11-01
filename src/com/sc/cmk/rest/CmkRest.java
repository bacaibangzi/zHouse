package com.sc.cmk.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sc.cmk.dao.ChildrenDao;
import com.sc.cmk.dao.ParentDao;
import com.sc.cmk.pojo.Parent;
import com.sc.framework.base.action.BaseAction;
import com.sc.framework.utils.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/cmkRest")
public class CmkRest  extends BaseAction{
	
	@Autowired
	ChildrenDao childrenMapper;
	@Autowired
	ParentDao parentMapper;
	
	
	@RequestMapping(value = "/dl-mobile", method = RequestMethod.POST)
	public void dl(@RequestBody String content,
			HttpServletRequest request,HttpServletResponse response){

		JSONObject jsonObject = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jsonObject = JSONObject.fromObject(content);
			String username = jsonObject.getString("username");
			String password = jsonObject.getString("password");
			
			if(username==null||password==null){
				throw new Exception();
			}
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			conditionMap.put("phone", username);
			conditionMap.put("password", StringUtil.encodeStr(password));
			List list1 = parentMapper.query(conditionMap);
			
			conditionMap.clear();
			conditionMap.put("mail", username);
			conditionMap.put("password", StringUtil.encodeStr(password));
			List list2 = parentMapper.query(conditionMap);
			
			 
			if((list1==null||list1.size()==0)&&(list2==null||list2.size()==0)){
				throw new Exception("用户密码错误");
			}
			map.put("success", true);
			response.setStatus(200);
		}catch(Exception err){ 
			err.printStackTrace();
			map.put("success", false);
		}
		
		renderJson(map,response);
		
	}
	
	
	@RequestMapping(value = "/zc-mobile", method = RequestMethod.POST)
	public void zc(@RequestBody String content,
			HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			jsonObject = JSONObject.fromObject(content);
			String username = jsonObject.getString("username");
			String password = jsonObject.getString("password");
			String mail = jsonObject.getString("mail");
			String answer1 = jsonObject.getString("answer1");
			String answer2 = jsonObject.getString("answer2");
			String answer3 = jsonObject.getString("answer3");
			
			if(username==null||password==null){
				throw new Exception();
			}
			
			Map<String, Object> conditionMap = new HashMap<String, Object>();
			conditionMap.put("phone", username);
			
			List<Parent> list = parentMapper.query(conditionMap);
			if(list!=null&&list.size()>0){
				throw new Exception("手机号码已经注册");
			}
			
			conditionMap.put("mail", mail);
			
			list = parentMapper.query(conditionMap);
			if(list!=null&&list.size()>0){
				throw new Exception("mail已经注册");
			}
			
			/*
			List<UserInfo> userInfoList = userInfoService.queryUserInfosByCondition(conditionMap);
			if(userInfoList==null||userInfoList.size()>0){
				throw new Exception();
			}
			
			UserInfo userInfo = new UserInfo();
			userInfo.setUiLoginName(username);
			userInfo.setUiPassword(password);
			
			userInfoService.saveOrUpdateUserInfoInfo(userInfo);
			*/
			Parent parent = new Parent();
			parent.setPhone(username);
			parent.setMail(mail);
			parent.setPassword( StringUtil.encodeStr(password));
			parent.setAnswer1(answer1);
			parent.setAnswer2(answer2);
			parent.setAnswer3(answer3);
			parent.setDate(new Date());
			
			parentMapper.insert(parent);
			
			
			map.put("success", true);
			response.setStatus(200);
		}catch(Exception err){ 
			err.printStackTrace();
			map.put("message", err.getMessage());
			map.put("success", false);
		}
		
		renderJson(map,response);
	
	}	
	
}
