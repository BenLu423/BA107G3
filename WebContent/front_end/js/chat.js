$(document).ready(function(){
	//取聊天室間數
	$("#area-all").append("共"+$("#area tr").size()+"間");
    $("#sports-all").append("共"+$("#sports tr").size()+"間");
    $("#casual-all").append("共"+$("#casual tr").size()+"間");
    var count = $("#area tr").size()+$("#sports tr").size()+$("#casual tr").size();
    $("#total").append("共"+count+"間");//全部

//    $("#allType").append($("#area tr")).append($("#sports tr")).append($("#casual tr"));


});