package com.sc.account.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sc.framework.base.action.BaseAction;
import com.sc.framework.security.util.AccountInfo;
import com.sc.framework.session.util.SessionConstants;
import com.sc.framework.utils.StringUtil;
import com.sc.framework.vo.ConditionVO;
import com.sc.merchantset.service.MerchantserService;
import com.sc.system.pojo.UserInfo;
import com.sc.system.service.UserInfoService;

/**
 * 
 * 账户管理action
 * 
 */
@Controller
@RequestMapping("/acountManage")
public class AccountManageAction extends BaseAction {
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	MerchantserService merchantserService;
	/**
	 * 用户信息界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userDetail", method = RequestMethod.GET)
	public String userDetail(@ModelAttribute ConditionVO vo,@ModelAttribute("form") UserInfo userInfo,
			HttpServletRequest request)throws Exception {
		request.setAttribute("vo", vo);
		vo.setEntityId(((AccountInfo)request.getSession().getAttribute("accountInfo")).getUserId().toString());
		BeanUtils.copyProperties(userInfoService.getUserInfoById(vo),userInfo);
		return "merchantcenter/user/userDetail";
	}
	 
	/**
	 * 用户密码修改页面
	 */
	@RequestMapping(value = "/passwordEidt", method = RequestMethod.GET)
	public String passwordEidt() {
		return "merchantcenter/user/passwordEidt";
	}
	
	/**
	 * 用户密码修改
	 * 
	 * @param vo
	 * @param userInfo
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String save(@ModelAttribute ConditionVO vo, @RequestParam String oldPassword,
			@ModelAttribute("form") UserInfo userInfo,HttpServletRequest request)  throws Exception{
		request.setAttribute("vo", vo);
		userInfo.setUiId(((AccountInfo)request.getSession().getAttribute("accountInfo")).getUserId());
		userInfo.setUiLoginName(((AccountInfo)request.getSession().getAttribute("accountInfo")).getUsername());
		vo.setEntityId(((AccountInfo)request.getSession().getAttribute("accountInfo")).getUserId().toString());
		UserInfo user = userInfoService.getUserInfoById(vo);
		
		
		if(!oldPassword.equals(user.getUiPassword())){ 
			vo.setErrMsg("The old password is wrong！");
			return "merchantcenter/user/passwordEidt"; 
		}
		try {
			userInfo.setUiPassword(StringUtil.encodeStr(userInfo.getUiPassword()));
			merchantserService.saveOrUpdateMerchantserInfo(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
			vo.setErrMsg("Error！");
			return "merchantcenter/user/passwordEidt"; 
		}	vo.setErrMsg("Success！");
		
		return "merchantcenter/user/passwordEidt";
	}

}

