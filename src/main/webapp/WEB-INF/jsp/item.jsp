<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
<title>商品详情页</title>
<style type="text/css">
li{padding-top:10px;}
</style>
</head>
<body>
<ul>
	<form id="iteminfo">
		<li>商品名称：<input id="itemName" name="itemName" type="text" value="iphoneX"></li>
		<li>商品价格：<input id="price" name="price" type="text" value="8455"></li>
		<li>商品编号：<input id="itemNo" name="itemNo" type="text" value="0983"></li>
		<li>数量：<input id="num" name="num" type="text" value="1"></li>
		<li><input type="button" id="btnsb" value="立即购买"></li>
	</form>
		
</ul>
<script type="text/javascript">
$("#btnsb").click(function(){
	$.ajax({
	    type: "POST",
	    url:"http://localhost:8080/api/order/createOrder",
	    data:$('#iteminfo').serialize(),
	    async: false,
	    error: function(request) {
	        alert("Connection error");
	    },
	    success: function(data) {
	        window.location.href="http://localhost:8080/p/order?orderNo="+data.orderno;
	    }
	}); 	
});
		

 
/* $("#iteminfo").ajaxForm(function(data){
	alert(data.result);
}); */
</script>
</body>
</html>