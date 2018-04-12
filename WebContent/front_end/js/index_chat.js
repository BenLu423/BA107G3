$(function() {
  $("#chatSwitchOpen").click(function(){
    $("#index-chat").animate({right:'1%'},"slow",function(){
      $("#chatSwitchOpen").hide("fast",function(){
        $("#chatSwitchClose").show("fast");
      });
    });
  });
});
$(function() {
  $("#chatSwitchClose").click(function(){
    $("#index-chat").animate({right:'-15%'},"slow",function(){
      $("#chatSwitchClose").hide("fast",function(){
        $("#chatSwitchOpen").show("fast");
      });
    });
  });
});


$(document).ready(function(){
	//滾輪置底
	var dh = $("div #chat-message").height();
	$("div #chat-message").scrollTop(dh);

});
