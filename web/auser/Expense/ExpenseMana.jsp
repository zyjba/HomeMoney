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
			//window.location="/HomeMoney/ShowServlet?type=expense";
           function ExpenseDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/ExpenseDel.action?id="+id;
               }
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

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1"  align="center" style="margin-top:8px">
				<%--<tr bgcolor="#E7E7E7">
					<td height="14" colspan="25" background="<%=path %>/images/tbg.gif">&nbsp;消费信息管理&nbsp;</td>
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
				<c:forEach items="${requestScope.listExpense}" var="expense">
				<tr>
					<td align="center">${expense.id}</td>
					<td align="center">${expense.time}</td>
					<td align="center">${expense.number}</td>
					<td align="center">${expense.member}</td>
					<td align="center">${expense.type}</td>
					<td align="center">${expense.remark}</td>
					<td  align="center">
						<a href="/HomeMoney/DeleteServlet?method=deleteExpense&expenseId=${expense.id}" onclick="ExpenseDel()" class="pn-loperator">删除</a>
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
