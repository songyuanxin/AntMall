<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        #myForm{
            background: rgb(198, 230, 255);
            width: 300px;
            height: 140px;
            margin: auto;
            margin-top: 200px;
            line-height: 32px;
            padding: 10px;
        }
        table{
            margin: auto;
        }
        .btn{
            background: white;
            border: 1px solid rgb(163, 163, 163);
            width: 50px;
            border-radius: 3px;
            height: 25px;
        }
        .btn:hover{
            background: rgb(199, 198, 198);
            border: 1px solid white;
        }
        .in{
            border: 1px solid rgb(163, 163, 163);
            border-radius: 3px;
            height: 20px;
        }
        .in:hover{
            border-color:lightsteelblue;
        }
        label{
            font-family: "幼圆";
            font-size: 16px;
        }
    </style>
</head>
<body>
    <form action="#" method="post" id="myForm">
        <table>
            <tr>
                <td><label>收件人:</label></td>
                <td><input class="in" type="text" id="reciever" value="${reciever }"></td>
            </tr>
            <tr>
                <td><label>地址：</label></td>
                <td><input class="in" type="text" id="address" value="${address }"></td>
            </tr>
            <tr>
                <td><label>电话：</label></td>
                <td><input class="in" type="text" id="phone" value="${phone }"></td>
            </tr>
            <tr>
            	<td colspan="2"><input type="hidden" id="orderId" value="${orderId }"></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align:center"><input class="btn" id="sub" type="button" value="提交"></td>
            </tr>
        </table>   
        
    </form>
    <script src="js/jquery.min.js"></script>
    <script src="js/layer/layer.js"></script>
<script>
	$(document).ready(function(){
		$("#sub").click(function(){
			var reciever = $("#reciever").val();
			var address = $("#address").val();
			var phone = $("#phone").val();
			var orderId = $("#orderId").val();
			$.post("EditOrderServlet",{
				reciever:reciever,
				address:address,
				phone:phone,
				orderId:orderId
			},function(data){
				if(data=="OK"){
					layer.msg('修改成功', {icon: 6});
					setTimeout("move()",2000);
				}
				if(data=="Error"){
					layer.msg('修改失败', {icon: 5});
				}
			})
		})
	})
	function move(){
		window.location.href="OrderServlet";
	}
</script>
</body>
</html>