$(document).ready(function() {
	 var len = 35;
	 $(".JQellipsis").each(function(i){
		 if($(this).text().length>len){
			 $(this).attr("title",$(this).text());
	         var text=$(this).text().substring(0,len-1)+"...";
	         $(this).text(text);
	     }
	 });
});