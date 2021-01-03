<%--
  Created by IntelliJ IDEA.
  User: zyjba
  Date: 2020/12/31
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <script src="<%=path%>/js/jquery-3.3.1.min.js"></script>
    <style>
        div{
            float: left;
            margin-top: 20px;
        }
    </style>
</head>
<script src="<%=path %>/auser/Statement/echarts.min.js"></script>
<body>
    <div id="income" style="width: 350px;height:500px;" ></div>
    <div id="expense" style="width: 350px;height:500px;" ></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('income'));
        $(function () {
            var btn = $("#btn1");
            btn.click(function(){
                //显示加载动画
                myChart.showLoading();
                $.ajax({
                    url:'<%=path %>/StatementServlet?userId=${sessionScope.user.userId}&type=income',
                    type:'post',
                    data:{"time":$("#time").val()},
                    dataType:'json',
                    success:function (result) {
                        myChart.hideLoading();
                        myChart.setOption({
                            title: {
                                text: '该月收入报表'
                            },
                            tooltip: {},
                            grid: {
                                left: '3%',
                                right: '4%',
                                bottom: '3%',
                                containLabel: true
                            },
                            xAxis: {
                                data: ["本人","配偶","爸爸","妈妈","子女"]
                            },
                            yAxis: {},
                            series: [{
                                color:'#e5803d',
                                type: 'bar',
                                data: [result.num1, result.num2, result.num3, result.num4,result.num5]
                            }]
                        });
                    }
                });
            });
        })
    </script>
</body>
</html>
