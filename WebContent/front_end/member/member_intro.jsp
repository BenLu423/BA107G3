<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script src="<%=request.getContextPath()%>/front_end/member/mem_modify_page/ckeditor/ckeditor.js"></script>
<title>Insert title here</title>
</head>
<style>
.panel-warning>.set-panel{
background: rgba(255,220,220);
}
</style>
<body>
							<!-- 自我介紹-->
                          <div id="intro_area">
                           <form name = "form" action="<%=request.getContextPath()%>/member/mem.do" method="post">

									<div class="panel panel-warning">
										<div class="panel-heading set-panel">
											<h3 class="panel-title">自我介紹</h3>
										</div>
										
							        	<textarea name="mem_intro" id="content" rows="10" cols="80">${memSelf.mem_intro}</textarea>
							     
							            <div>
							            <input type = 'submit' value = '提交' class="btn btn-default set-login-btn">
										</div>
								
										<div>
										<input type="hidden" name="action" value = "getintro_judge">
										</div>
									</div>
<!-- 								</div> -->
								
					        </form>
                          </div>
				      <script>
						CKEDITOR.replace( 'content', {});
					</script>
</body>
</html>