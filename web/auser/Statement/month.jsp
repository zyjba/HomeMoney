<%--
  Created by IntelliJ IDEA.
  User: zyjba
  Date: 2020/12/30
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <script src="<%=path%>/js/jquery-3.3.1.min.js"></script>
    <title>text</title>
    <style>
    div{
        float: left;
        margin-top: 20px;
    }
</style>
</head>
<script src="<%=path %>/auser/Statement/echarts.min.js"></script>
<body>
    <input type="month" id="time" name="time" class="login_input"/>
    <input id="btn1" type="button" value="搜索收入" />
    <input id="btn2" type="button" value="搜索消费" /><br>
    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="income" style="width: 240px;height:500px;" ></div>
    <div id="expense" style="width: 550px;height:500px;" ></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('income'));
    $(function () {
        var btn = $("#btn1");
        btn.click(function(){
            //显示加载动画
           /* myChart.showLoading();*/
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
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('expense'));
    $(function () {
        var btn = $("#btn2");
        btn.click(function(){
            //显示加载动画
            /*myChart.showLoading();*/
            $.ajax({
                url:'<%=path %>/StatementServlet?userId=${sessionScope.user.userId}&type=expense',
                type:'post',
                data:{"time":$("#time").val()},
                dataType:'json',
                success:function (result) {
                    myChart.hideLoading();
                    myChart.setOption({
                        title: {
                            text: '该月消费报表'
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
