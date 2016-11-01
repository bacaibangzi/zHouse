package com.sc.cmk.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.cmk.pojo.Parent;
import com.sc.cmk.service.ParentService;
import com.sc.framework.base.action.BaseAction;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Controller
@RequestMapping("/parent")
public class ParentAction extends BaseAction{

	@Autowired
	ParentService parentService;

	/**
	 * 家长页面
	 * @return
	 */
	@RequestMapping(value = "/main.htm", method = RequestMethod.GET)
	public String toMain(HttpServletRequest request) {
		//Map<Long,String> user_sh = new HashMap<Long,String>(); 
		return "cmk/parentMain";
	}

	/**
	 * 加载家长信息
	 * @param vo
	 * @param page
	 * @param response
	 */
	@RequestMapping(value = "/list.htm", method = RequestMethod.POST)
	@ResponseBody
	public void list(@ModelAttribute ConditionVO vo,
			@ModelAttribute Page<Parent> page, HttpServletResponse response) throws Exception {
		super.setPageInfo(page);
		Page<Parent> list = parentService.queryParentsForPage(vo, page);
		super.readerPage2Json(list, response);

	}

	/**
	 * Parent编辑页面queryParentsPublishForPage
	 * @param vo
	 * @param Parent
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/eidt.htm", method = RequestMethod.GET)
	public String eidt(@ModelAttribute ConditionVO vo,@ModelAttribute("form") Parent Parent,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		if(vo.getEntityId()!=null){
			BeanUtils.copyProperties(parentService.getParentById(vo),Parent);
		}else{ 
		}
		return "cmk/parentEidt";
	}
	
	/**
	 * Parent详细信息页面
	 * @param vo
	 * @param Parent
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detail(@ModelAttribute ConditionVO vo,@ModelAttribute("form") Parent Parent,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		BeanUtils.copyProperties(parentService.getParentById(vo),Parent);
		return "cmk/parentDetail";
	}
	
	/**
	 * 保存Parent信息
	 * @param vo
	 * @param Parent
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String save(@ModelAttribute ConditionVO vo,@ModelAttribute Parent Parent,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		parentService.saveOrUpdateParentInfo(Parent);
		return "cmk/parentMain";
	}
	
	/**
	 * 删除Parent信息
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String delete(@ModelAttribute ConditionVO vo,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		parentService.deleteParentById(vo);
		return "cmk/parentMain";
	}
}
