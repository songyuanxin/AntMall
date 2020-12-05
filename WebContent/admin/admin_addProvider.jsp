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
			<td><label for="provider">供应商编号</label></td>
			<td><input type="text" name="provider" id="provider"></td>
		</tr>
		<tr>
			<td><label for="providerName">供应商名称</label></td>
			<td><input type="text" name="providerName" id="providerName"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="提交" id="sub"></td>
		</tr>
	</table>
<script src="../js/jquery.min.js"></script>
<script src="../js/layer/layer.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#sub").click(function(){
			var provider = $("#provider").val();
			var providerName = $("#providerName").val();
			$.post("AdminAddProviderServlet",{
				provider:provider,
				providerName:providerName
			},function(data){
				if(data=="OK"){
					layer.msg("添加成功");
					setTimeout("move()",2000);
				}
				if(data=="Error"){
					layer.msg("添加失败");
				}
				if(data=="Error2"){
					layer.msg("您的输入有误，编号只能为整数");
				}
				if(data=="Error3"){
					layer.msg("供应商编号或供应商名称已存在");
				}
			})
		})
	})
	function move(){
		window.location.href="AdminProviderServlet";
	}
</script>
</body>
</html>