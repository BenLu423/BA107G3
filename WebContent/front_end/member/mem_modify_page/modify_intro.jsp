<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
		     <div id="intro_area" style="display: none">
           	   <form name = "form" action="/BA107G3/member/mem.do" method="post">
		        	<textarea name="mem_intro" id="content" rows="10" cols="80"></textarea>
		            <div>
		          		  <input type = 'submit' value = '提交' class="btn btn-info">
					</div>
					<div>
							<input type="hidden" name="action" value = "getintro_judge">
					</div>
	      		  </form>
              </div>
</body>
</html>