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
        <script language="javascript">
           function userDel(userId)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/userDel.action?userId="+userId;
               }
           }
           function userEditPre(userId)
           {
                   window.location.href="<%=path %>/userEditPre.action?userId="+userId;
           }
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

	<body leftmargin="2" topmargin="2" background='<%=path %>/img/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1"  align="center" style="margin-top:8px">
<%--				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="8" background="<%=path %>/img/tbg.gif">&nbsp;用户管理&nbsp;</td>
				</tr>--%>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">ID</td>
					<td width="15%">账号</td>
					<td width="15%">密码</td>
					<td width="15%">注册时间</td>
					<td width="10%">操作</td>
		        </tr>
				<c:forEach items="${requestScope.listUser}" var="user">
					<tr>
						<td align="center">${user.userId}</td>
						<td align="center">${user.user_account}</td>
						<td align="center">${user.user_password}</td>
						<td align="center">${user.user_registerDate}</td>
						<td  align="center">
							<a href="/HomeMoney/DeleteServlet?method=deleteUser&userId=${user.userId}" onclick="userDel()" class="pn-loperator">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<hr class="hr"/>
				<div id="pageControl">
					<div class="pageControl_item">每页<font id="dataPrePage">8</font>条数据</div>
					<div class="pageControl_item" id="last">最后一页</div>
					<div class="pageControl_item" id="next">下一页</div>
					<div class="pageControl_item"><font id="currentPage">1</font>/<font id="pages">1</font></div>
					<div class="pageControl_item" id="previous">上一页</div>
					<div class="pageControl_item" id="first">首页</div>
				</div>
			</div>
	</body>
</html>
