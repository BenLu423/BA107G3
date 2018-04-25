<%@ page contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%  
	EventService eventSvc = new EventService();
    List<EventVO> listOFF = eventSvc.getAll_sts_off();
    pageContext.setAttribute("listOFF",listOFF);
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/back_end/header.jsp"></jsp:include>

	
	
<div class="col-xs-4 col-sm-4 cont">
	   		

<div style="height: 70px"></div>
   
<div class="container">
<div class="col-12" >
<div style=border-style:double;background:rgba(255,220,220,1);width:1500px>
	


		 <h4><a href="event_title.jsp">回首頁</a></h4>
	 

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th><font size="6">活動編號</font></th>
		<th><font size="6">分類編號</font></th>
		<th><font size="6">活動名稱</font></th>
		<th><font size="6">開始時間</font></th>
		<th><font size="6">結束時間</font></th>
		
		<th style="width:150px;"><font size="6">活動詳情</font></th>
		<th><font size="6">活動圖片</font></th>
		<th><font size="6">活動名額</font></th>
		<th><font size="6">活動地點</font></th>
		<th><font size="6">活動費用</font></th>
		<th><font size="6">活動狀態</font></th>
		<th><font size="6">活動修改</font></th>
		<th><font size="6">活動狀態</font></th>
		<th><font size="6">參加名單</font></th>
	</tr>

<%  int rowsPerPage1 = 3;  //每頁的筆數    
    int rowNumber1=0;      //總筆數
    int pageNumber1=0;     //總頁數      
    int whichPage1=1;      //第幾頁
    int pageIndexArray1[]=null;
    int pageIndex1=0; 
%>

<%  
    rowNumber1=listOFF.size();
    if (rowNumber1%rowsPerPage1 !=0)
         pageNumber1=rowNumber1/rowsPerPage1 + 1;
    else pageNumber1=rowNumber1/rowsPerPage1;    

    pageIndexArray1=new int[pageNumber1]; 
    for (int i=1 ; i<=pageIndexArray1.length ; i++)
         pageIndexArray1[i-1]=i*rowsPerPage1-rowsPerPage1;
%>

<%  try {
       whichPage1 = Integer.parseInt(request.getParameter("whichPage1"));
       pageIndex1=pageIndexArray1[whichPage1-1];
    } catch (NumberFormatException e) { //第一次執行的時候
       whichPage1=1;
       pageIndex1=0;
    } catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
         if (pageNumber1>0){
              whichPage1=pageNumber1;
              pageIndex1=pageIndexArray1[pageNumber1-1];
         }
    } 
%>

<%if (pageNumber1>0){%>
  <b><font color=red>第<%=whichPage1%>/<%=pageNumber1%>頁</font></b>
<%}%>

<b>●符 合 查 詢 條 件 如 下 所 示: 共<font color=red><%=rowNumber1%></font>筆</b>
	<c:forEach var="eventVO" items="${listOFF}" begin="<%=pageIndex1%>" end="<%=pageIndex1+rowsPerPage1-1%>">
		
		<tr>
			<td><font size="5">${eventVO.eve_no}</font></td>
			<td><font size="5">${eventVO.evec_no}</font></td>
			<td><font size="5">${eventVO.eve_name}</font></td>
			<td><font size="5">${eventVO.eve_start}</font></td>
			<td><font size="5">${eventVO.eve_end}</font></td>
			
			<td style="width:150px;"><font size="5">${eventVO.eve_cnt}</font></td>
			<td><img style="width:150px;" src="<%=request.getContextPath()%>/imagesServlet?action=event&no=${eventVO.eve_no}"></td>
			<td><font size="5">${eventVO.eve_quota}</font></td>
			<td><font size="5">${eventVO.eve_site}</font></td>
			<td><font size="5">${eventVO.eve_regfee}</font></td>
			<td><font size="5">${eventVO.eve_sts}</font></td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update2"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="上架中">
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action" value="offToOn"></FORM>
			     
			</td>
			
			
		</tr>
	</c:forEach>
</table>
</div>
</div>


  <%if (rowsPerPage1<rowNumber1) {%>
    <%if(pageIndex1>=rowsPerPage1){%>
        <A href="<%=request.getRequestURI()%>?whichPage1=1">至第一頁</A>&nbsp;
        <A href="<%=request.getRequestURI()%>?whichPage1=<%=whichPage1-1%>">上一頁 </A>&nbsp;
    <%}%>
  
    <%if(pageIndex1<pageIndexArray1[pageNumber1-1]){%>
        <A href="<%=request.getRequestURI()%>?whichPage1=<%=whichPage1+1%>">下一頁 </A>&nbsp;
        <A href="<%=request.getRequestURI()%>?whichPage1=<%=pageNumber1%>">至最後一頁</A>&nbsp;
    <%}%>
  <%}%>  

<br><br>

  <%if (pageNumber1>1) {%>
    <FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">   
       <select size="1" name="whichPage1">
         <%for (int i=1; i<=pageNumber1; i++){%>
            <option value="<%=i%>">跳至第<%=i%>頁
         <%}%> 
       </select>
       <input type="submit" value="確定" >  
    </FORM>
  <%}%>
</div>
</div>
</body>
</html>