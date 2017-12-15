<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
<title>确认订单</title>
</head>
<body>
		<li>订单编号:${orderNo}</li>
		<li>商品名称：${itemName}</li>
		<li>商品总额：${totalFee}</li>
		<li>商品编号：${itemNo}</li>
		<li>数量：${num}</li>
		<li><a href="http://localhost:8080/api/pay/alipay?orderNo=${orderNo}&clientType=1" id="btnsb"  target="_blank">立即支付</a></li>
	
<script type="text/javascript">
function comfirmfun(){
	var c = confirm("是否支付成功");
	if(c==true){
		alert("谢谢光顾");
	}else{
		alert("请重试");
	}
}
		

 
/* $("#iteminfo").ajaxForm(function(data){
	alert(data.result);
}); */
</script>
</body>
</html>