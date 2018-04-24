<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/card/card.css">
<title>加值服務</title>
</head>
<body>
<jsp:include page="/front_end/header.jsp"></jsp:include>

<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/gift.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/card/card.js"></script>

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <div class="content-top"></div>  
			<div class="col-xs-12 col-sm-12">
				
				<div class="demo-container">
			        <div class="card-wrapper"></div>
			        <div class="form-container active">
			            <form action="">
			                <input placeholder="Card number" type="text" name="number">
			                <input placeholder="Full name" type="text" name="name">
			                <input placeholder="MM/YY" type="text" name="expiry">
			                <input placeholder="CVC" type="text" name="cvc">
			            </form>
			        </div>
			    </div>

	    	</div>    
		<div class="col-xs-12 col-sm-1"></div>
	</div>    
</div>	      
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
<style>
	.demo-container {
        width: 100%;
        max-width: 350px;
        margin: 50px auto;
    }

    form {
        margin: 30px;
    }
    input {
        width: 200px;
        margin: 10px auto;
        display: block;
    }

</style>
<script>
$('.active form').card({
    container: $('.card-wrapper')
})
</script>

</html>