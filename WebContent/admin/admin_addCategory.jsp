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
			<td><label for="category">种类编号</label></td>
			<td><input type="text" name="category" id="category"></td>
		</tr>
		<tr>
			<td><label for="categoryName">种类名称</label></td>
			<td><input type="text" name="categoryName" id="categoryName"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="提交" id="sub"></td>
		</tr>
	</table>
	<script src="../js/jquery.min.js"></script>
	<script src="../js/layer/layer.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#sub").click(function() {
				var category = $("#category").val();
				var categoryName = $("#categoryName").val();
				$.post("AdminAddCategoryServlet", {
					category : category,
					categoryName : categoryName
				}, function(data) {
					if (data == "OK") {
						layer.msg("添加成功");
						setTimeout("move()", 2000);
					}
					if (data == "Error") {
						layer.msg("添加失败");
					}
					if (data == "Error2") {
						layer.msg("您的输入有误,编号只能为整数");
					}
					if (data == "Error3") {
						layer.msg("种类编号或种类名已存在");
					}
				})
			})
		})
		function move() {
			window.location.href = "AdminCategoryServlet2";
		}
	</script>
</body>
</html>