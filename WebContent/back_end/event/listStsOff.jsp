<%@ page contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.event.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>
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
	


		 <h4><a href="event_title.jsp">�^����</a></h4>
	 

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th><font size="6">���ʽs��</font></th>
		<th><font size="6">�����s��</font></th>
		<th><font size="6">���ʦW��</font></th>
		<th><font size="6">�}�l�ɶ�</font></th>
		<th><font size="6">�����ɶ�</font></th>
		
		<th style="width:150px;"><font size="6">���ʸԱ�</font></th>
		<th><font size="6">���ʹϤ�</font></th>
		<th><font size="6">���ʦW�B</font></th>
		<th><font size="6">���ʦa�I</font></th>
		<th><font size="6">���ʶO��</font></th>
		<th><font size="6">���ʪ��A</font></th>
		<th><font size="6">���ʭק�</font></th>
		<th><font size="6">���ʪ��A</font></th>
		<th><font size="6">�ѥ[�W��</font></th>
	</tr>

<%  int rowsPerPage1 = 3;  //�C��������    
    int rowNumber1=0;      //�`����
    int pageNumber1=0;     //�`����      
    int whichPage1=1;      //�ĴX��
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
    } catch (NumberFormatException e) { //�Ĥ@�����檺�ɭ�
       whichPage1=1;
       pageIndex1=0;
    } catch (ArrayIndexOutOfBoundsException e) { //�`���Ƥ��~�����~����
         if (pageNumber1>0){
              whichPage1=pageNumber1;
              pageIndex1=pageIndexArray1[pageNumber1-1];
         }
    } 
%>

<%if (pageNumber1>0){%>
  <b><font color=red>��<%=whichPage1%>/<%=pageNumber1%>��</font></b>
<%}%>

<b>���� �X �d �� �� �� �p �U �� ��: �@<font color=red><%=rowNumber1%></font>��</b>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="eve_no"  value="${eventVO.eve_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update2"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/event/event.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�W�[��">
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
        <A href="<%=request.getRequestURI()%>?whichPage1=1">�ܲĤ@��</A>&nbsp;
        <A href="<%=request.getRequestURI()%>?whichPage1=<%=whichPage1-1%>">�W�@�� </A>&nbsp;
    <%}%>
  
    <%if(pageIndex1<pageIndexArray1[pageNumber1-1]){%>
        <A href="<%=request.getRequestURI()%>?whichPage1=<%=whichPage1+1%>">�U�@�� </A>&nbsp;
        <A href="<%=request.getRequestURI()%>?whichPage1=<%=pageNumber1%>">�̫ܳ�@��</A>&nbsp;
    <%}%>
  <%}%>  

<br><br>

  <%if (pageNumber1>1) {%>
    <FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">   
       <select size="1" name="whichPage1">
         <%for (int i=1; i<=pageNumber1; i++){%>
            <option value="<%=i%>">���ܲ�<%=i%>��
         <%}%> 
       </select>
       <input type="submit" value="�T�w" >  
    </FORM>
  <%}%>
</div>
</div>
</body>
</html>