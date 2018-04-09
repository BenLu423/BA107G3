$(document).ready(
		function() {
			$('#giftAdd').click(function(){
				var card = $('.gift-add-card:first').clone();
				$(card).find('input').val('');
				$(card).find('textarea').val('');
				$(card).find('img').attr('src','');
				$(card).insertAfter($('.gift-add-card:last'));
			});
			
			$('body').on('click', '.gift-add-close',function() {
				if($('.gift-add-card').length > 1){
					$(this).parents('.gift-add-card').remove();
				}
			});

			$(".form-group .gift-add-pic").change(function() {
				var inputPos = this;
				if (this.files && this.files[0]) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$(inputPos).next().attr('src', e.target.result);
					}

					reader.readAsDataURL(this.files[0]);
				}
			});
			
			$(".form-group .gift-edit-pic").change(function() {
				var inputPos = this;
				if (this.files && this.files[0]) {
					var reader = new FileReader();
					reader.onload = function(e) {
						$(inputPos).prev().attr('src', e.target.result);
					}

					reader.readAsDataURL(this.files[0]);
				}
			});

			$("#selectWord").change(function() {
				var word = $("#selectWord").val();
				$('#searchContext').attr("name", word);
			});

			$('.btn-filter').on(
					'click',
					function() {
						var $target = $(this).data('target');
						if ($target != 'all') {
							$('.gift-management tr').css('display', 'none');
							$(
									'.gift-management tr[data-status="'
											+ $target + '"]').fadeIn('slow');
						} else {
							$('.gift-management tr').css('display', 'none')
									.fadeIn('slow');
						}
					});

			$('.gift-status button').hover(
					function() {
						$(this).parent().children("button.currentlyStatus")
								.css('color', '#1E90FF').css('font-weight',
										'bolder');
						$(this).css('background-color', '#AAA');
						var button = $(this).parent().children("button");
						if (!button.show()) {
							button.show();
						}

					},
					function() {
						$(this).parent().children("button.currentlyStatus")
								.css('color', '#000').css('font-weight',
										'normal');
						$(this).css('background-color', '#FFF');
						$(this).parent().children("button.currentlyStatus")
								.css('background-color', '#FFF');
						$(this).parent().children("button").not(
								".currentlyStatus").css('display', 'none');
					});

			$('.gift-status button').click(
					function() {
						var ori = $(this).parent().children(
								"button.currentlyStatus");
						ori.css('background-color', '#FFF')
								.css('color', '#000').css('font-weight',
										'normal');
						ori.removeClass("currentlyStatus");

						$(this).addClass("currentlyStatus");
						$(this).css('background-color', '#AAA').css('color',
								'#1E90FF').css('font-weight', 'bolder');

					});

		});