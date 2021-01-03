<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		<link rel="stylesheet" type="text/css" href="<%=path %>/css/page.css" />
		<script src="<%=path%>/js/jquery-3.3.1.min.js"></script>
        <script language="javascript">
			function IncomeDel(id)
			{
				if(confirm('您确定删除吗？'))
				{
					window.location.href="<%=path %>/ExpenseDel.action?id="+id;
				}
			}
       </script>
		<script type="text/javascript">
			$(function(){
				$("#next").click(function(){
					var pages = parseInt($("#pages").html());
					var page = parseInt($("#currentPage").html());
					if(page == pages){
						return;
					}
					page++;
					location.href ='/HomeMoney/ShowServlet?type=income&page='+page;
				})

				$("#previous").click(function () {
					var page = parseInt($("#currentPage").html());
					if(page == 1){
						return;
					}
					page--;
					location.href = "/HomeMoney/ShowServlet?type=income&page="+page;
				})
				$("#first").click(function () {
					location.href = "/HomeMoney/ShowServlet?type=income&page=1";
				})

				$("#last").click(function(){
					var pages = parseInt($("#pages").html());
					location.href = "/HomeMoney/ShowServlet?type=income&page="+pages;
				})
			})
		</script>
		<style >
			table tr{
				cursor: pointer;
			}

			table tr:hover{
				background-color: #F0F0F0;
			}

			table td{
				height:40px;
				text-align: center;
			}
		</style>
	</head>

	<body leftmargin="2" topmargin="2" >
			<table width="98%" border="0" cellpadding="2" cellspacing="1"  align="center" style="margin-top:8px">
				<%--<tr bgcolor="#E7E7E7">
					<td height="10" colspan="25" background="<%=path %>/images/tbg.gif">&nbsp;收入信息管理&nbsp;</td>
				</tr>--%>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="5%">序号</td>
					<td width="10%">时间</td>
					<td width="10%">金额</td>
					<td width="10%">成员</td>
					<td width="10%">类型</td>
					<td width="10%">备注</td>
					<td width="10%">操作</td>
				</tr>
				<c:forEach items="${requestScope.listIncome}" var="income">
				<tr>
					<td align="center">${income.id}</td>
					<td align="center">${income.time}</td>
					<td align="center">${income.number}</td>
					<td align="center">${income.member}</td>
					<td align="center">${income.type}</td>
					<td align="center">${income.remark}</td>
					<td  align="center">
						<a href="/HomeMoney/DeleteServlet?method=deleteIncome&incomeId=${income.id}" onclick="IncomeDel()" class="pn-loperator">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
	<div>
		<hr class="hr"/>
		<div id="pageControl">
			<div class="pageControl_item">每页<font id="dataPrePage">${dataPrePage }</font>条数据</div>
			<div class="pageControl_item" id="last">最后一页</div>
			<div class="pageControl_item" id="next">下一页</div>
			<div class="pageControl_item"><font id="currentPage">${currentPage }</font>/<font id="pages">${pages }</font></div>
			<div class="pageControl_item" id="previous">上一页</div>
			<div class="pageControl_item" id="first">首页</div>
		</div>
	</div>
	</body>
</html>
