var ajaxRunning = false;
String.prototype.endsWith = function(suffix) {
    return this.indexOf(suffix, this.length - suffix.length) !== -1;
};
function validURL(url) {
	return /^((https?|ftp):\/\/)?(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(url);
}
function validImageType(file) {
	return file.endsWith('.jpg') || file.endsWith('png') | file.endsWith('.gif');
}
$(document).ready(function() {
	$('.tree-directory').click(function(event) {
		var ul = $(this).children('ul:first-child');
		event.stopPropagation();
		if (ul.is(":visible")) {
			ul.hide();
		} else {
			ul.show();
		}
	});
	$('.tree-directory a').click(function(event) {
		event.stopPropagation();
	});
	$('ul.tree .current').parents('ul').show();
    $('#kaptchaImage').click(function() { 
    	$(this).attr('src', '/kaptcha.jpg?' + Math.floor(Math.random() * 100)); 
    });
    if (!String.prototype.format) {
    	String.prototype.format = function() {
    		var args = arguments;
    		return this.replace(/{(\d+)}/g, function(match, number) { 
    			return typeof args[number] != 'undefined'
    				? args[number] : match;
    		});
    	};
    }
    $('.submit-form').click(function() {
    	$(this).closest('form').submit();
    });
});

function showMessage(type, message) {
	var dialog = $('#response-message');
	dialog.removeClass();
	message = "<strong>" + message + "</strong>";
	var container = "";
	dialog.addClass("alert");
	if (type == 'info') {
		dialog.addClass("alert-info");
	} else if (type == 'success') {
		dialog.addClass("alert-success");
	} else if (type == 'warning') {
		dialog.addClass("alert-warning");
	} else {
		dialog.addClass("alert-danger");
	}
	dialog.html(message);
	var left = (($(window).width() - dialog.outerWidth()) / 2) + $(window).scrollLeft();
	dialog.css({left : left + 'px'});
	dialog.slideDown(2000).delay(2000).slideUp(2000);
}

function tabToggle(current, id, cssClass) {
    $('.' + cssClass).hide();
    $('#' + id).show();
    $(current).parent().siblings().removeClass('active');
    $(current).parent().addClass('active');
}

function formDataToArray(form) {
	var data = {};
	form.find("input, textarea, select, checkbox, radio").each(function() {
		data[$(this).attr('name')] = $(this).val();
	});
	return data;
}

function url(url) {
	window.location = url;
}
function gotoPage(element, pageIndex) {
	console.log($(element).html());
	$(element).closest('form').find("input[name='pageIndex']").val(pageIndex);
	$(element).closest('form').submit();
}
function submitForm(form) {
	$('#' + form).submit();
}
function getFormatedDate(dateValue, format) {
	var d = new Date(dateValue);
	var year = d.getFullYear();
	var month = d.getMonth() + 1;
	var date = d.getDate();
	var hour = d.getHours();
	var minute = d.getMinutes();
	var second = d.getSeconds();
	month = month < 10 ? "0" + month : month;
	date = date < 10 ? "0" + date : date;
	hour = hour < 10 ? "0" + hour : hour;
	minute = minute < 10 ? "0" + minute : minute;
	second = second < 10 ? "0" + second : second;
	return year % 2000 + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}
function LightenDarkenColor(col, amt) {
	var usePound = false;
	col = rgb2hex(col);
  
	if (col[0] == "#") {
		col = col.slice(1);
		usePound = true;
	}
 
	var num = parseInt(col,16);
 
	var r = (num >> 16) + amt;
 
	if (r > 255) r = 255;
	else if  (r < 0) r = 0;
 
	var b = ((num >> 8) & 0x00FF) + amt;
 
	if (b > 255) b = 255;
	else if  (b < 0) b = 0;
 
	var g = (num & 0x0000FF) + amt;
 
	if (g > 255) g = 255;
	else if (g < 0) g = 0;
 
	return (usePound?"#":"") + (g | (b << 8) | (r << 16)).toString(16);
}
var hexDigits = new Array("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"); 

function rgb2hex(rgb) {
	rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
	return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
}

function hex(x) {
	return isNaN(x) ? "00" : hexDigits[(x - x % 16) / 16] + hexDigits[x % 16];
}
function float2int (value) {
    return value | 0;
}
var MINUTE_ = 60;
var HOUR_ = 3600;
var DAY_ = 86400;
var WEEK_ = 604800;
var MONTH_ = 2592000;
var YEAR_ = 31536000;
var DIFF_M = 60000;

var MONTH = "month";
var MONTHS = "months";
var DAY = "day";
var DAYS = "days";
var WEEK = "week";
var WEEKS = "weeks";
var HOUR = "hour";
var HOURS = "hours";
var MINUTE = "minute";
var MINUTES = "minutes";
var RECENT = "recently";

var MINUTE_DIFF = 1;
var HOUR_DIFF = 60;
var DAY_DIFF = 1440;
var WEEK_DIFF = 10080;
var MONTH_DIFF = 43200;

function getTimeString(time) {
	var current = new Date().getTime();
	if (time === undefined || time == 0) {
		return "None";
	}
	var diff = (current - time) / DIFF_M;
	var stringFormat = "{0} {1} ago";
	
	if (diff >= MONTH_DIFF * 4) {
		return getFormatedDate(time, "yyyy-MM-dd HH:mm:ss");
	} else if (diff >= MONTH_DIFF * 2) {
		return stringFormat.format(float2int(diff / MONTH_DIFF), MONTHS);
	} else if (diff >= MONTH_DIFF) {
		return stringFormat.format(1, MONTH);
	} else if (diff >= WEEK_DIFF * 2) {
		return stringFormat.format(float2int(diff / WEEK_DIFF), WEEKS);
	} else if (diff >= WEEK_DIFF) {
		return stringFormat.format(1, WEEK);
	} else if (diff >= DAY_DIFF * 2) {
		return stringFormat.format(float2int(diff / DAY_DIFF), DAYS);
	} else if (diff >= DAY_DIFF) {
		return stringFormat.format(1, DAY);
	} else if (diff >= HOUR_DIFF * 2) {
		return stringFormat.format(float2int(diff / HOUR_DIFF), HOURS);
	} else if (diff >= HOUR_DIFF) {
		return stringFormat.format(1, HOUR);
	} else if (diff >= MINUTE_DIFF * 2) {
		return stringFormat.format(float2int(diff / MINUTE_DIFF), MINUTES);
	} else if (diff >= MINUTE_DIFF) {
		return stringFormat.format(1, MINUTE);
	} else {
		return RECENT;
	}
}
function increase(value) {
	return parseInt(value) + 1;
}
function decrease(value) {
	return parseInt(value) - 1;
}