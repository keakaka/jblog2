<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<script>
			$(function(){
				$('#join').hide();
				$('#id').change(function(){
					$('#btn-checkId').show();
					$('#img-checkId').hide();
					$('#join').hide();
				});
				$('#btn-checkId').click(function(){
					var id = $('#id').val();
					if(id == ''){
						return;
					}
					
					$.ajax({
						url: "${pageContext.request.contextPath }/user/checkId?id="+id,
						type: "get",
						dataType: "json",
						data: "",
						success: function(result){
							console.log(typeof(result));
							console.log(result);
							if(result === "2"){
								alert('중복된 아이디 입니다.');
								$('#id').focus();
								$('#id').val("");
								return;
							}
							$('#btn-checkId').hide();
							$('#img-checkId').show();
							$('#join').show();
						},
						error: function(xhr, error){
							console.error("error " + error);
						}
					});
				});
			});
			</script>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/common/menu.jsp" />
		<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath }/user/join">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="id" name="id" type="text"> 
			<input id="btn-checkId" type="button" value="id 중복체크">
			
			<img id="img-checkId" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="pwd" type="password" />

			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input id="join" type="submit" value="가입하기">

		</form>
	</div>
</body>
</html>
