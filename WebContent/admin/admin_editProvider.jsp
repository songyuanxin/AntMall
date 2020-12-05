<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
		<tr>
			<td><label>种类编号</label></td>
			<td><label>${provider.providerId }</label></td>
		</tr>
		<tr>
			<td><label for="providerName">种类名称</label></td>
			<td><input type="text" name="providerName" id="providerName" value="${provider.providerName }"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="保存" id="sub"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="hidden" value="${provider.providerId }" id="id"></td>
		</tr>	
	</table>
<script src="../js/jquery.min.js"></script>
<script src="../js/layer/layer.js"></script>
<script>
	$("#sub").click(function(){
		var id = $("#id").val();
		var providerName = $("#providerName").val();
		$.post("AdminUpdateProviderServlet",{
			id:id,
			providerName:providerName
		},function(data){
			if(data=="OK"){
				layer.msg("修改成功");
				setTimeout("move()",2000);
			}
			if(data=="Error2"){
				layer.alert("供应商名称重复");
			}
			if(data=="Error"){
				layer.msg("修改失败");
			}
		})
	})
	function move(){
		window.location.href="AdminProviderServlet";
	}
</script>
</body>
</html>