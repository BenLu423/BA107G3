<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	
	<title>header</title>
		
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/index_chat.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/front_end/js/chat.js"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/header.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_friends.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_diary.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/index_chat.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/gift.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/css/event.css">
</head>
<body>
<!-- header -->
<nav class="navbar navbar-default navbar-fixed-top mynavbar">
<div class="container-fluid">      
<div class="row">
     <div class="col-xs-12 col-sm-1">
     </div>
     <div class="col-xs-12 col-sm-2">
		<div class="logo">
             <a href="index.html">
             <img src="<%=request.getContextPath()%>/front_end/res/img/logo/Logo.gif">
             </a>
         </div>
     </div>
     <div class="col-xs-12 col-sm-9">
         <div class="row">
             <div class="col-xs-12 col-sm-3 col-sm-offset-9 login">
                     <div class="btn-group btn-group-justified mymenu-icon">
                         <div class="btn-group">
                             <button type="button" class="btn btn-default mybutton-icon">
                                 	登出
                             </button>
                         </div>
                         <div class="btn-group">
                             <button class="btn btn-default dropdown-toggle mybutton-icon" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                             購物車 <span class="badge">2</span>
                             </button>
                             <ul class="dropdown-menu agile_short_dropdown dropdown-menu-right" aria-labelledby="dropdownMenu1">
                                 <li><a href="#">flower</a></li>
                                 <li><a href="#">car</a></li>
                                 <li role="separator" class="divider"></li>
                                 <li>total: $888</li>
                             </ul>                                
                         </div>
                         
                         <div class="btn-group">
                             <button class="btn btn-default dropdown-toggle mybutton-icon" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                             通知 <span class="badge">888</span>
                             </button>
                             <ul class="dropdown-menu agile_short_dropdown dropdown-menu-right" aria-labelledby="dropdownMenu2" style="text-align: left">
                                    <li><a href="#">　 劉德華　　請求加為好友</a></li>
                                    <li><a href="#">　 周子瑜　　已成好友</a></li>
                                    <li><a href="#">　蠟筆小新　 已新增留言</a></li>
                                </ul>                                
                            </div>
                            <div class="btn-group btn-group-menu">
                                <button class="btn btn-default dropdown-toggle mybutton-icon" type="button" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <div class="menu-icon"></div>
                                <div class="menu-icon"></div>
                                <div class="menu-icon"></div>
                                </button>
                                <ul class="dropdown-menu agile_short_dropdown dropdown-menu-right" aria-labelledby="dropdownMenu3">
                                    <li><a href="#">帳號設定</a></li>
                                    <li><a href="#">好友管理</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">收禮清單</a></li>
                                    <li><a href="#">贈禮管理</a></li>
                                    <li><a href="#">加值服務</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">我的日記</a></li>
                                    <li><a href="#">我的活動</a></li>
                                </ul>                                
                            </div>
                        </div>



                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu4">
  <li><a href="#">Regular link</a></li>
  <li class="disabled"><a href="#">Disabled link</a></li>
  <li><a href="#">Another link</a></li>
</ul>                       
                </div>
                <div class="col-xs-12 col-sm-12">
                    <div class="collapse navbar-collapse">
                        <nav class="menu menu--iris">
                        <ul class="nav navbar-nav menu__list">
                        	 <!-- menu__item--current -->
                    	<li class="menu__item"><a href="#" class="news.html">最新公告</a></li>
                    	<li class="menu__item"><a href="#" class="friends.html">交友搜尋</a></li> 
                        <li class="menu__item"><a href="diary.html" class="menu__link">日記專欄</a></li>
                        <li class="menu__item"><a href="event.html" class="menu__link">活動特輯</a></li>
                        <li class="menu__item"><a href="gift.html" class="menu__link">禮物商城</a></li>
                        <li class="menu__item"><a href="chat.html" class="menu__link">聊天室</a></li>         
                    </ul>
                    </nav>
                </div> 
                
            </div>  					
        </div>

    </div>
</div>

</div>
</nav>   
<!-- //header-->
</body>
</html>