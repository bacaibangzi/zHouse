<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:include page="/common/include/includejs.jsp" />
<%
	//新增按钮
	String addMenu = "7802783440559203414";
	//修改
	String updateMenu = "7291201182524030468";
	//删除
	String delMenu = "6610433833513736115";
	//查看
	
%>
<html>

<body>
	<input type="hidden" id="entityIds" name="entityIds" />
	<input type="hidden" id="code" value="${code}" />
    <div class="contenbox">
    	<!--  
    		<div class="grid_summay_opts" id="btns">
				<div class="left_opts">
						<a hideFocus="true" href="javascript:add('<%=basePath%>news/eidt.htm?orgCode=${sessionScope.accountInfo.orgCode}')" class="gray_radiu_btn" >
							<em class="gray_l"></em> 
							<em class="gray_r"></em> 
							新增
						</a>
						<a hideFocus="true" href="javascript:sh()" class="gray_radiu_btn" >
							<em class="gray_l"></em> 
							<em class="gray_r"></em> 
							发布
						</a>
				</div>
				<div class="right_opts">
					<sec:authorize ifAnyGranted="<%=delMenu %>">
						<a hideFocus="true" href="javascript:th();" class="gray_radiu_btn" >
							<em class="gray_l"></em> 
							<em class="gray_r"></em> 
							退回
						</a>
					</sec:authorize>
				</div>
			</div>
		-->	
			<div class="sprite_mod">
				<a class="Js_showSearch mod_opts" hidfocus="true" id="searchnav">展开查询条件</a>
			</div>
			<!-- 表格列表过滤条件设置 -->
			<div class="filter-mod">
				<ul class="filter-list">
					<li class="filter-item">
						<label class="filter-label">
							名称
						</label>
						<input type="text" class="filter-text" id="areaName_filter" value="${vo.nameFilter}"/>
					</li>
					<li class="filter-item filter-btns">
						<a hideFocus="true" class="gray_radiu_btn Js_searchTable">
							 <em class="gray_l"></em> 
							 <em class="gray_r"></em> 
							 查询
						</a>
						<a hideFocus="true" href="javascript:clearForm('filter-mod');" id="add" class="gray_radiu_btn Js_reLoadTable"> 
							<em class="gray_l"></em> 
							<em class="gray_r"></em> 
							清除
						 </a>
					</li>
				</ul> 
			</div>
			<table cellpadding="0" cellspacing="0" border="0" id="grid-table" width="100%" class="common_table Js_contextMenuOpt">
				<thead>
					<tr>
						<th><input type="checkbox" /></th>
						<th>序号</th>
						<th>电话</th>
						<th>邮件</th>
						<th>问题1</th>
						<th>问题2</th>
						<th>问题3</th>
						<th>问题4</th>
						<th>日期</th>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
    </div>
        <div class="rightMenu bodyMenu Js_tdMenu">
            <!--存储当前点击行的数据-->
            <input type="hidden" class="Js_curIndex" value="" />
            <ul>
                
               <!-- <li><a hideFocus="true" href="javascript:detail()">查看</a></li>  -->
            </ul>
        </div> 
	</body>
	    <!-- 日期控件 -->
	<script type="text/javascript" src="<%=path%>/common/js/datapicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var datatable ;
		$(document).ready(function(){
			var tableObj={}
			tableObj.url="<%=basePath%>parent/list.htm?orgCode=${sessionScope.accountInfo.userId}";
			tableObj.bPanination = false;
			tableObj.aoColumns=[
			    {"mDataProp":"","sDefaultContent":"<input type='checkbox' />","bSortable":false,"sWidth":"20px",sClass:"checkbox"},
			    {"mDataProp":"id","sDefaultContent": "","bSortable":false,"sClass":"text-align-mid","sWidth":"100"},
			    {"mDataProp":"phone","sDefaultContent": "","bSortable":false,"sClass":"text-align-mid","sWidth":"200"},
			    {"mDataProp":"mail","sDefaultContent": "","bSortable":false,"sClass":"text-align-mid","sWidth":"100"},
			    {"mDataProp":"answer1","sDefaultContent": "","bSortable":false,"sClass":"text-align-mid","sWidth":"100"},
			    {"mDataProp":"answer2","sDefaultContent": "","bSortable":false,"sClass":"text-align-mid","sWidth":"100"},
			    {"mDataProp":"answer3","sDefaultContent": "","bSortable":false,"sClass":"text-align-mid","sWidth":"100"},
			    {"mDataProp":"answer4","sDefaultContent": "","bSortable":false,"sClass":"text-align-mid","sWidth":"100"},
			    {"mDataProp":"date","sDefaultContent": "","bSortable":false,"sClass":"text-align-mid","sWidth":"100"}
			];
			//添加额外的参数DateString
			tableObj.fnServerParams= function ( aoData ) 
			{
    			aoData.push( { "name": "default_param", "value": "desc"} );
  			}
  			
  			datatable=datatableObj(tableObj);
  			//serach
  			$(".Js_searchTable").live("click",function()
  		  	{
				var url = highQuery();
				tableObj.url  = url;
				datatable = datatableObj(tableObj);
			})

			reLoad = function(){
  				var url = highQuery();
				tableObj.url  = url;
				datatable = datatableObj(tableObj);
  			}
		});

		/** 修改方法 **/
		function update()
		{
			var data=$(".Js_tdMenu").data("data");
			var selectId = data.idStr;
			
			
			window.location = "<%=basePath%>parent/eidt.htm?entityId="+selectId; 
			
		}
		
		/** 查看详情 **/
		function detail()
		{
			var data=$(".Js_tdMenu").data("data")
			var selectId = data.idStr;
			// 明细弹出框
			var commonDialog = commonOpenDialog("readDetail",'详细',700,450, '<%=basePath%>parent/detail.htm?entityId=' + selectId);
			commonDialog.addBtn("ok",'确定', commonDialog.cancel);
		}
		/** 按条件查询 **/
		function highQuery()
		{
			// 查询条件 区域名称
			var areaName_filter = '';
			var returnVal = '<%=basePath%>parent/list.htm?orgCode=${sessionScope.accountInfo.orgCode}';
			if(areaName_filter!=''){
				returnVal += '&nameFilter=' + encodeURI(encodeURI(areaName_filter));
			}

			var sh_filter = $.trim(document.getElementById('sh_filter').value);
			if(sh_filter!='-1')
			{
				returnVal += '&shFilter=' + sh_filter;
			}
			return returnVal;
		}
		
		/** 删除**/
		function deleteAll()
		{
			 
			var rows=getSeleteObjs(datatable);//alert(rows.length);
			if(rows.length!=1){
				fh.alert("请选择一条记录");
				return;
			}else{
				$.ajax({
					url:'<%=basePath%>apps/check.htm',
					type:'post',
					async: false,
					data:'entityId='+rows[0].idStr,
					success:function(data){
						if(data.state==1){
							fh.alert("当前APP已经提交申请");
							return;
						}
						if(data.state==2){
							fh.alert("当前APP已经审核通过");
							return;
						}
						
						
						var rows=getSeleteObjs(datatable);
						var actionUrl = "<%=basePath%>apps/delete.htm?code=${vo.code}&entityIds=";
						batchDelete( {rows:rows,idField:"idStr",actionUrl:actionUrl});
						
					}
				}); 
			}  
		}
		  
	</script>
		
</html>

<script language="javascript" type="text/javascript">
           
</script>

