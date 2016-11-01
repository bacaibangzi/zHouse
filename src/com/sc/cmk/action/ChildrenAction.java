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

import com.sc.cmk.pojo.Children;
import com.sc.cmk.service.ChildrenService;
import com.sc.framework.base.action.BaseAction;
import com.sc.framework.vo.ConditionVO;
import com.sc.framework.vo.Page;

@Controller
@RequestMapping("/children")
public class ChildrenAction extends BaseAction{

	@Autowired
	ChildrenService childrenService;

	/**
	 * 儿童页面
	 * @return
	 */
	@RequestMapping(value = "/main.htm", method = RequestMethod.GET)
	public String toMain(HttpServletRequest request) {
		//Map<Long,String> user_sh = new HashMap<Long,String>(); 
		return "cmk/childrenMain";
	}

	/**
	 * 加载儿童信息
	 * @param vo
	 * @param page
	 * @param response
	 */
	@RequestMapping(value = "/list.htm", method = RequestMethod.POST)
	@ResponseBody
	public void list(@ModelAttribute ConditionVO vo,
			@ModelAttribute Page<Children> page, HttpServletResponse response) throws Exception {
		super.setPageInfo(page);
		Page<Children> list = childrenService.queryChildrensForPage(vo, page);
		super.readerPage2Json(list, response);

	}

	/**
	 * Children编辑页面queryChildrensPublishForPage
	 * @param vo
	 * @param Children
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/eidt.htm", method = RequestMethod.GET)
	public String eidt(@ModelAttribute ConditionVO vo,@ModelAttribute("form") Children Children,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		if(vo.getEntityId()!=null){
			BeanUtils.copyProperties(childrenService.getChildrenById(vo),Children);
		}else{ 
		}
		return "cmk/childrenEidt";
	}
	
	/**
	 * Children详细信息页面
	 * @param vo
	 * @param Children
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public String detail(@ModelAttribute ConditionVO vo,@ModelAttribute("form") Children Children,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		BeanUtils.copyProperties(childrenService.getChildrenById(vo),Children);
		return "cmk/childrenDetail";
	}
	
	/**
	 * 保存Children信息
	 * @param vo
	 * @param Children
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save.htm", method = RequestMethod.POST)
	public String save(@ModelAttribute ConditionVO vo,@ModelAttribute Children Children,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		childrenService.saveOrUpdateChildrenInfo(Children);
		return "cmk/childrenMain";
	}
	
	/**
	 * 删除Children信息
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete.htm", method = RequestMethod.GET)
	public String delete(@ModelAttribute ConditionVO vo,HttpServletRequest request) throws Exception{
		request.setAttribute("vo", vo);
		childrenService.deleteChildrenById(vo);
		return "cmk/childrenMain";
	}

}
