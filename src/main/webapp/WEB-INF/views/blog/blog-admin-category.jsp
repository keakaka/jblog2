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
		$('.delButton').click(function(){
			var check = confirm('작성 된 포스트가 있는 카테고리를 삭제할 시 해당 포스트는 미분류 카테고리로 이동합니다. 삭제하시겠습니까?');
			var $cateNo = $(this).parent().parent().children('input').val();
			var $cateName = $(this). parent().parent().children('td').eq(1).text();
			if(!check){
				return;
			}
			if($cateName === '미분류'){
				alert('미분류 카테고리는 삭제하실 수 없습니다.');
				return;
			}
			
			/* ajax 통신 */
			$.ajax({
				url: "${pageContext.request.contextPath }/${map.categoryList[0].id }/admin/category/delete",
				type: "POST",
				dataType: "json",
				data: {$cateNo : $cateNo},
				success: function(result){
					location.reload();
				},
				error: function(xhr, error){
					console.error("error" + error);
				}
			});
		});
	});
</script>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/common/header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${map.categoryList[0].id }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${map.categoryList[0].id }/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					<c:forEach items='${map.categoryList }' var='category' varStatus='status'>
					<tr>
						<input type="hidden" name="categoryNo" value="${category.no }"/>
						<td>${status.index+1 }</td>
						<td>${category.name }</td>
						<td>${map.postCount[status.index] }</td>
						<td>${category.comment }</td>
						<td><button type="button" class="delButton"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></button></td>
					</tr> 
					</c:forEach>
					
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" id="newCateName"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc" id="newCateComment"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><button type="button" class="addButton">카테고리 추가</button></td>
		      		</tr>      		      		
		      	</table> 
		      	<script>
		      		$(function(){
		      			$('.addButton').click(function(){
		      				var name = $('#newCateName').val();
		      				var comment = $('#newCateComment').val();
		      				
		      				$.ajax({
		      					url: "${pageContext.request.contextPath }/${map.categoryList[0].id }/admin/category/insert",
		      					type: "POST",
		      					dataType: "json",
		      					data: {name : name, 
		      							comment : comment},
		      					success: function(result){
		      						location.reload();
		      					},
		      					error: function(xhr, error){
		      						console.error("error" + error);
		      					}
		      				});
		      				
		      			});
		      			
		      		});
		      		
		      	</script>
			</div>
		</div>
		<c:import url="/WEB-INF/views/common/footer.jsp"/>
	</div>
</body>
</html>