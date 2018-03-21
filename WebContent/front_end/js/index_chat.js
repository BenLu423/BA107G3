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