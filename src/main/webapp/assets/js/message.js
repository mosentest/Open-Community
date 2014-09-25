var message_id = 0;
function sendMessage() {
	var form = $('#write-message-form');
	$.ajax({
		url: form.attr('action'),
		type: 'POST',
		data: form.serialize(),
		success: function(data, status, xhr) {
			if (data.success) {
				showMessage('confirm', 'Message has been sent.');
				setTimeout(function() {
					url('coop/message/' + data.content);
				}, 3000);
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			  showMessage('warning', "Could not send message.");
		}
	});
}
function replyMessage() {
	var form = $('#write-message-form');
	$.ajax({
		url: form.attr('action'),
		type: 'POST',
		data: form.serialize(),
		success: function(data, status, xhr) {
			if (data.success) {
				showMessage('confirm', 'Message has been sent.');
				form.find('#message').val("");
				console.log(data.content.create);
				var html = "<div class='message-i-date hidden' id='message-d-" + message_id + "'>" + getFormatedDate(data.content.create, 'full') + "</div>";
				html += "<div class='message-i-l hidden' id='message-i-" + message_id + "'>";
				var profileLink = "<a href='coop'><img class='profile-default' src='image/u/" + config.user.profile + "'></img></a>";
				html += "<div class='message-i-profile'>" + profileLink + "</div>";
				html += "<div class='message-i-content'>" + data.content.message + "</div>";
				html += "<div class='clearfix'></div>";
				html += "</div>";
				$('.message-list').prepend(html);
				$('#message-d-' + message_id + ', #message-i-' + message_id).slideDown(1500).delay(2000).removeClass('hidden');
				message_id = message_id + 1;
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			  showMessage('warning', "Could not send message.");
		}
	});
}