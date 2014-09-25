function searchUser() {
	var query = $('#ajax-search-user').val();
	if (ajaxRunning) {
		return;
	}
	if (query.length >= 2) {
		$('.ajax-progressing').show();
		$('.ajax-result-content').hide();
		$('.ajax-no-result').hide();
		ajaxRunning = true;
		$.ajax({
			url: 'user/search',
			type: "GET",
			data: { query : query },
			success: function(data, status, xhr) {
				if (!data.success) {
					$('.ajax-no-result').show();
					$('.ajax-progressing').hide();
					ajaxRunning = false;
					return;
				}
				$('.ajax-result-content tbody').html("");
				var result = "";
				var verified = "<span class='aui-icon aui-icon-small aui-iconfont-success verified' title='Verified User'></span>";
				$.each(data.content, function(index, value) {
					result += "<tr>";
					result += "<td><img src='image/u/" + value.profile + "'/></td>";
					if (value.verified) {
		                result += "<td><a href='coop/u/" + value.path + "'>" + value.account + "&nbsp;" + verified + "</a></td>";
					} else {
		                result += "<td><a href='coop/u/" + value.path + "'>" + value.account + "&nbsp;</a></td>";
					}
					result += "<td>" + value.nFans + "</td>";
					result += "<td>" + value.nFriends + "</td>";
					result += "<td><span class='aui-lozenge aui-lozenge-subtle'><a href='javascript:addFriend('" + value.path + "');' id='user-" + value.path + "'>Add Friend</a></span></td>";
					result += "</tr>";
				});
				$('.ajax-result-content tbody').html(result);
				$('.ajax-progressing').hide();
				$('.ajax-result-content').show();
				ajaxRunning = false;
			},
			error: function(data, status, xhr) {
				$('.ajax-no-result').show();
				$('.ajax-progressing').hide();
				ajaxRunning = false;
			}
		});
	}
}
function addFriend(path) {
	var button = $('#user-' + path);
	$.ajax({
		url: 'coop/relationship/add',
		type: 'POST',
		data : { path : path },
		success: function(data, status, xhr) {
			if (data.success) {
				button.attr('aria-disabled', 'true');
				button.text('User Added');
				showMessage('confirm', 'User added.');
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			  console.log(statux, error);
		}
	});
}
function removeFriend(path, type) {
	if (!confirm('Remove friend?')) {
		return;
	}
	var data = type == 1 ? { type : 'friend', path : path } : { type : 'fans', path : path };
	$.ajax({
		url: 'coop/relationship/remove',
		type: 'POST',
		data : data,
		success: function(data, status, xhr) {
			if (data.success) {
				$('#user-' + path).fadeOut(300, function(){ $(this).remove(); });
				showMessage('confirm', 'User has been removed.');
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			console.log(statux, error);
		}
	});
}
function replyComment(post, account) {
	$('#comment-' + post).val('@' + account + " :");
	$('#comment-' + post).focus();
}
function like(post) {
	$.ajax({
		url: 'coop/post/' + post + "/like",
		type: 'POST',
		success: function(data, status, xhr) {
			if (data.success) {
				$('#n-like-' + post).text(increase($('#n-like-' +  post).text()));
				var link = $('#post-' + post + ' .like-link');
				link.removeClass('negative').addClass('positive');
				link.attr('href', "javascript:dislike(" + post + ")");
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			console.log(statux, error);
		}
	});
}
function dislike(post) {
	$.ajax({
		url: 'coop/post/' + post + '/dislike',
		type: 'POST',
		success: function(data, status, xhr) {
			if (data.success) {
				$('#n-like-' + post).text(decrease($('#n-like-' +  post).text()));
				var link = $('#post-' + post + ' .like-link');
				link.removeClass('positive').addClass('negative');
				link.attr('href', "javascript:like(" + post + ")");
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			console.log(statux, error);
		}
	});
}
var LIKE_SIZE = 6;
var COMMENT_SIZE = 6;
var POST_SIZE = 6;
function showPosts(path) {
	var key = 'post';
	if (pageMapper[key] === undefined) {
		pageMapper[key] = 1;
	}
	$.ajax({
		url: 'coop/u/' + path + '/posts',
		type: 'GET',
		data : { page : pageMapper[key] },
		success: function(data, status, xhr) {
			if (data.success) {
				var posts = "";
				var link = $('#show-post').parent();
				$.each(data.content, function(index, value) {
					posts += buildPost(value);
				});
				$('.post:last').after(posts);
				if (data.content.length < POST_SIZE) {
					link.remove();
				}
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			console.log(statux, error);
		}
	});
	pageMapper[key] = pageMapper[key] + 1;
}
function showLikes(post) {
	var key = 'post-l-' + post;
	if (pageMapper[key] === undefined) {
		pageMapper[key] = 1;
	}
	$.ajax({
		url: 'coop/post/' + post + '/likes',
		type: 'GET',
		data : { page : pageMapper[key] },
		success: function(data, status, xhr) {
			if (data.success) {
				var list = $('#post-' + post + ' .post-like');
				var link = $('#show-like-' + post);
				var likes = "";
				$.each(data.content, function(index, value) {
					likes += buildLike(value);
				});
				list.append(likes);
				link.remove();
				if (data.content.length == LIKE_SIZE) {
					link.insertAfter(list);
				}
			} else {
				$('#show-like').remove();
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			console.log(statux, error);
		}
	});
	pageMapper[key] = pageMapper[key] + 1;	
}
function showComments(post) {
	var key = 'post-c-' + post;
	if (pageMapper[key] === undefined) {
		pageMapper[key] = 1;
	}
	$.ajax({
		url: 'coop/post/' + post + '/comments',
		type: 'GET',
		data : { page : pageMapper[key] },
		success: function(data, status, xhr) {
			if (data.success) {
				var comments = "";
				var link = $('#show-comment-' + post).parent();
				$.each(data.content, function(index, value) {
					comments += buildComment(post, value, true);
				});
				$('#post-' + post + ' .post-comment-i:last').after(comments);
				link.remove();
				if (data.content.length == COMMENT_SIZE) {
					link.insertAfter($('#post-' + post + ' .post-comment-i:last'));
				}
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			console.log(statux, error);
		}
	});
	pageMapper[key] = pageMapper[key] + 1;
}
function post() {
	$.ajax({
		url: 'coop/post',
		data: { content : $('#post').val() },
		type: 'POST',
		success: function(data) {
			if (data.success) {
				var post = buildPost(data.content);
				$('#post').val("");
				$('.post').first().before(post);
				showMessage('confirm', 'Post created');
			} else {
				showMessage('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			console.log(statux, error);
		}
	});
}
function comment(post) {
	var comment = $('#comment-' + post);
	if (comment.val() && comment.val().length > 0) {
		$.ajax({
			url: 'coop/post/' + post + '/comment',
			type: 'POST',
			data : { comment : comment.val() },
			success: function(data, status, xhr) {
				if (data.success) {
					var commentHtml = buildComment(post, data.content, false);
					comment.val("");
					var element = $('#post-' + post + ' .post-comment-i');
					if (element.length > 0) {
						element.first().addClass('border-top');
						element.first().before(commentHtml);
					} else {
						$('#post-' + post + ' .post-comment-l').html(commentHtml);
					}
					$('#n-comment-' + post).text(increase($('#n-comment-' +  post).text()));
				} else {
					showMessage('warning', data.message);
				}
			},
			error: function(xhr, statux, error) {
				console.log(statux, error);
			}
		});
	} else {
		showMessage('warning', 'Comment may not be empty.');
	}
}
function buildComment(post, data, addBorder) {
	var comment = "<div class='post-comment-i " + (addBorder ? 'border-top' : '') + "'>";
	comment += "<div class='post-comment-profile'>";
	comment += "<a href='coop/u/" + data.user.path + "'><img class='profile-medium' src='image/u/" + data.user.profile + "'/></a>";
	comment += "</div>";
	comment += "<div class='post-comment-content'>";
	comment += "<span>" + data.content + "</span><br/>";
	comment += "<span class='post-comment-info'>";
	comment += "<a href='coop/u/" + data.user.path + "'>" + data.user.account + "</a> ";
	comment += "<span class='tab'></span> ";
	comment += "<a href=\"javascript:replyComment(" + post + ",'" + data.user.account + "')\" title='Reply' class='aui-icon aui-icon-small aui-iconfont-quote'> </a> ";
	comment += " <span>" + getTimeString(data.create) + "</span>";
	comment += "</span></div><div class='clearfix'></div></div>";
	return comment;
}
function buildPost(data) {
	var post = "<div class='post' id='post-" + data.id + "'>";
	post += "<div class='post-profile'>";
	post += "<a href='coop/u/" + data.user.path + "'><img class='profile-default' src='image/u/" + data.user.profile + "'></a>";
	post += "<div>" + data.user.account + "</div>";
	post += "</div>";
	post += "<div class='post-body'>";
	post += "<div class='post-content'>" + data.content + "</div>";
	post += "<div class='post-info'>";
	post += "<span class='aui-icon aui-icon-small aui-iconfont-time'></span> ";
	post += " <span>" + getTimeString(data.create) + "</span>";
	post += " <span class='tab'></span><span class='aui-icon aui-icon-small aui-iconfont-comment' title='Comments'></span> ";
	post += " <span id='n-comment-" + data.id + "'>" + data.nComment + "</span> <span class='tab'></span>";
	post += " <a href='javascript:" + (data.liked ? 'dislike' : 'like') + "(" + data.id + ")' title='Likes' class='aui-icon aui-icon-small aui-iconfont-like like-link " + (data.liked ? 'positive' : 'negative') + "'></a> ";
	post += " <span id='n-like-" + data.id + "'>" + data.nLike + "</span><span class='tab'></span></div>";
	post += "<div class='post-like'>";
	$.each(data.likes, function(index, value) {
		post += buildLike(value);
	});
	if (post.nLikes > LIKE_SIZE) {
		post += "<a href='javascript:showLikes(" + data.id + ")' class='heavy' id='show-like-" + data.id + "' title='see more'>...</a>";
	}
	post += "</div>";
	post += "<div class='border-top'></div><div class='post-comment-l'>";
	$.each(data.comments, function(index, value) {
		post += buildComment(data.id, value, index != 0);
	});
	post += "</div><div class='comment-form'>";
	post += "<input type='text' class='text long-field' id='comment-" + data.id + "' name='' placeholder='Comment here...'/> ";
	post += " <a href='javascript:comment(" + data.id + ")'>Comment</a></div></div><div class='clearfix'></div></div>";
	return post;
}
function buildLike(data) {
	return "<a href='coop/u/" + data.path + "' title='" + data.account + "'><img class='profile-small' src='image/u/" + data.profile + "'></a>";
}
function addLink() {
	//AJS.$('#add-link-dialog').show();	
}
function closeLink() {
	//AJS.$('#add-link-dialog').hide();
}
function addImage() {
	$('#upload').click();
}
function confirmLink() {
	var link = $('#link');
	if (validURL(link.val())) {
		$('#post').val($('#post').val() + '[url]' + link.val() + '[/url]');
		link.val("");
		//AJS.$('#add-link-dialog').hide();
	} else {
		showMessage('warning', 'Url is invalid.');
	}
}
$(document).ready(function() {
	//var link = new AJS.Dialog({
	//    width: 420, 
	//    height: 70, 
	//    id: "add-link-dialog", 
	//    closeOnOutsideClick: true
	//});
	//link.addPanel("Panel", "<input type='text' class='text long-field' name='link' id='link' style='width:250px;'/><span class='tab'></span><a href='javascript:confirmLink();'>Add</a><span class='tab'></span><a href='javascript:closeLink();'>Cancel</a>", "panel-body");
	//$('#link').keyup(function(e) {
	//	if (e.keyCode == 13) {
	//		confirmLink();
	//	}
	//});
	$('#upload').change(function(event) {
		var formData = new FormData();
		$.each(event.target.files, function(key, value) {
			console.log(key + "::" + value);
			formData.append("file", value);
		});
		$.ajax({
			url: 'image/upload/o',
			data: formData,
			type: 'POST',
		    processData: false,
		    contentType: false,
			success: function(data) {
				if (data.success) {
					$('#post').val($('#post').val() + '[img]' + data.content + '[/img]');
				} else {
					showMessage('warning', data.message);
				}
			},
			error: function(xhr, statux, error) {
				console.log(statux, error);
			}
		});
	});
});