$(document).ready(function () {
    $('.btn-filter').on('click', function () {
      var $target = $(this).data('target');
      if ($target != 'all') {
        $('.gift-management tr').css('display', 'none');
        $('.gift-management tr[data-status="' + $target + '"]').fadeIn('slow');
      } else {
        $('.gift-management tr').css('display', 'none').fadeIn('slow');
      }
    });

//  $('.gift-status .never button').on('click',
//    function(){
//      $(this).next().show();
//      $(this).next().next().show();
//      $(this).css('background-color','#DDD');
//    });

 });