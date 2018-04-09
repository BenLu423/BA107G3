<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.MemberService" %>
<%@ page import="com.member.model.MemberVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

    <title>Title Page</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
        
        <script type="text/javascript" src="js/index_chat.js"></script>
        <script type="text/javascript" src="js/chat.js"></script>

        <link rel="stylesheet" type="text/css" href="css/header.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <link rel="stylesheet" type="text/css" href="css/index_friends.css">
        <link rel="stylesheet" type="text/css" href="css/index_diary.css">
        <link rel="stylesheet" type="text/css" href="css/index_chat.css">
        <link rel="stylesheet" type="text/css" href="css/gift.css">
        <link rel="stylesheet" type="text/css" href="css/event.css">
        <link rel="stylesheet" type="text/css" href="css/personal_page.css">

    <style type="text/css">
    		 .set-col-sm-bgcolor{
      background-color: rgba(255,220,220,1);
    
    }
  .set-col-sm-font{
      margin-bottom: 15px;
      font-size: 18px;
      letter-spacing: 5px;
  }
  

      
     .set-title{
      background-color: rgb(255,220,220);
      padding-right: 10px;
      padding-left: 10px;
      padding-top: 20px;
      padding-bottom: 20px;
      border-radius: 5px;

    }
       /*大頭貼*/
    .set-photostick{
      width: 320px;
      height: 299px;
      border-radius: 30px;
      border: 1.5px solid;
      margin-top: 45px;
    }

    .item-imgarea{
        /*padding: 49.5px;*/
        padding-top: 33.5px;
        padding-bottom: 43.5px;
        padding-left: 5px;
    }
    .set-icon{
      margin-top: 15px;
      margin-left: 10px;
      width: 65px;
      /*height: 8%;*/
    }
    
      /*按鈕*/
    .item-imgarea img:hover {
      background: #000;
      color: #fff;
      /*transition:0.5s all;*/
    }


    
    /*家好友左邊margin*/
    .set-icon-left{
      margin-left: 40px;
    }
    
    .set-page{
      margin-top: 20px;
      margin-right: 0px;
      margin-left: 0px;
      margin-bottom: 0px;
    }


    /*表格格式*/
    .set-table th{
      height: 45px;
      width: 35%;
      
    }
  

    
    .set-tabs-margin{
      padding: 15px;
    }

    /*固定內容面版大小*/
    #tab-control-panle{
      margin-top: 15px;
      margin-left: 15px;
      margin-left: 15px;
      width: 100%;
      height: 100%;
    }

    body{
      font-family: "微軟正黑體";
    }
    
    </style>
  
  </head>
    <body>
    <%
    	String mem_no = request.getParameter("mem_no");
    	MemberService ms = new MemberService();
    	MemberVO getoneMemberData = ms.getOneMem(mem_no);
    	request.setAttribute("getoneMemberData", getoneMemberData);
    	
    	if(mem_no == null){
    		response.sendRedirect(request.getContextPath()+"/front_end/index.jsp");
    	}
    %>

<!-- header -->
    <jsp:include page="/front_end/header.jsp"></jsp:include>
<!-- //header-->
    <div class="container-fluid">
        <div class="row">
            <div class="col-xs-12 col-sm-1"></div>
            <div class="col-xs-12 col-sm-10 content">
                <div class="content-top"></div>
                
                <div class="col-xs-12 col-sm-1"></div> 
                <div class="col-xs-12 col-sm-10 col-sm-offset-1">

                       
                       <div class="col-xs-12 col-sm-4 set-col-sm-font">
                              <div class="item item-imgarea">
                                 <img src="<%=request.getContextPath()%>/memgetpic/mem.do?mem_no=${getoneMemberData.mem_no}" class="set-photostick">
								
                                 <img src="btnimg/add-friend.png" class=" btn set-icon set-icon-left">
                                 <img src="btnimg/gift.png" class=" btn set-icon">
                                 <img src="btnimg/cancel.png" class=" btn set-icon">
                              </div>
                        </div>

                       <div class="col-xs-12 col-sm-6 set-col-sm-font">
                            <div>
                            <h1 class="set-title">個人基本資料</h1>
                            </div>
                                <div class="item">
                                  <table class="set-table">
                                    <tbody>
                                    <tr>
                                      <th>姓名</th>
                                      <td>${getoneMemberData.mem_name}</td>
                                    </tr>
                                    <tr>
                                      <th>性別</th>
                                      <td>${getoneMemberData.mem_gender}</td>
                                     </tr>
                                     <tr>
                                      <th>生日</th>
                                      <td>${getoneMemberData.mem_birthday}</td>
                                     </tr>
                                     <tr>
                                      <th>血型</th>
                                      <td>${getoneMemberData.mem_bloodtype}型</td>
                                     </tr>
                                     <tr>
                                      <th>身高</th>
                                      <td>${getoneMemberData.mem_height}</td>
                                     </tr>
                                     <tr>
                                      <th>體重</th>
                                      <td>${getoneMemberData.mem_weight}</td>
                                     </tr>
                                     <tr>
                                      <th>地址</th>
                                      <td>${getoneMemberData.mem_county}</td>
                                     </tr>
                                     <tr>
                                      <th>感情</th>
                                      <td>${getoneMemberData.mem_emotion}</td>
                                     </tr>
                                    </tbody>
                                    
                                  </table>
                                </div>
                       </div>

                    

                    <div class="col-xs-12 col-sm-10 set-col-sm-bgcolor set-col-sm-font">
                                <div role="tabpanel">
                          <!-- 標籤面板：標籤區 -->
                          <ul class="nav nav-tabs " role="tablist">
                              <li role="presentation" class="active">
                                  <a class="link-font-color" href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"><p class="p-font-color">自我介紹</p></a>
                              </li>
                              <li role="presentation">
                                  <a class="link-font-color" href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab"><p class="p-font-color">我的興趣</p></a>
                              </li>
                              <li role="presentation">
                                  <a class="link-font-color" href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab"><p class="p-font-color">收到禮物</p></a>
                              </li>
                              <li role="presentation">
                                  <a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab"><p class="p-font-color">我的日記</p></a>
                              </li>

                          </ul>

                          <!-- 標籤面板：內容區 -->
                          <div class="tab-content " id="tab-control-panle">
                              <div role="tabpanel" class="tab-pane active" id="tab1">
								<div>
									<p>${getoneMemberData.mem_intro}</p>
								</div>
                              </div>
                              <div role="tabpanel" class="tab-pane" id="tab2">
                            	<c:forEach var="interest" items="${getoneMemberData.mem_interest}">
                            		${interest}
                            	</c:forEach>
                            	
                              </div>
                              <div role="tabpanel" class="tab-pane" id="tab3">Lorem ipsum dolor sit amet.</div>
                               <div role="tabpanel" class="tab-pane" id="tab4">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Facere, ab!</div>

                           </div>
                           
                        </div>
                


                    </div>
                    <div class="col-xs-12 col-sm-1"></div> 

            </div>
			      <div class="col-xs-12 col-sm-1"></div>
		    </div> 		
  		</div>
  	</div>
    



<!-- FOOTER -->
<jsp:include page="/front_end/footer.jsp"></jsp:include>
<!-- FOOTER END-->
	</body>
</html>