(function (window) {
	'use strict';
	// Your starting point. Enjoy the ride!

	$(function () {
		getList();
	})

	$('.new-todo').on('change', function () {
		$.ajax({
			type: "PUT",
			url: "http://localhost:8080/api/todos/insert",
			contentType: 'application/json',
			data: JSON.stringify(
				{
					todo: $('.new-todo').val(),
					completed: false,
				}),
			success: function (data) {
				getList();
			},
		})
	});

	$('.clear-completed').on('click', function(){
			$.ajax({
				type : "DELETE",
				url : "http://localhost:8080/api/todos/deletecomplete",
				success: function (data) {
				getList();
			}
			})
	});

})(window);


function setList(data) {
	$('.todo-list').empty();
	var count = 0;
	for (var i = 0; i < data.length; i++) {
		var html = '';
		if (data[i].completed == false) {
			html += '<li id =' + data[i].id + '>';
			count++;
		}
		else {
			html += '<li class = "completed id ="' + data[i].id + '>';
		}
		html += '<div class="view">';
		html += '<input class="toggle" type="checkbox">';
		html += '<label>' + data[i].todo + '</label>';
		html += '<button class="destroy"></button>';
		html += '</div>';
		html += '<input class="edit" value="Rule the web">';
		html += '</li>';
		$('.todo-list').append(html);
	}
	$('strong').text(count);
	$(document).on('click', '.toggle', function () {
		var i = $('.toggle').index(this);
		$.ajax({
			type: "POST",
			url: "http://localhost:8080/api/todos/complete",
			contentType: "application/json",
			data: JSON.stringify({
				id: $('li').eq(i).attr('id'),
				completed: true,
			}),
			success: function () {
				//$('li').eq(i).attr('class', 'completed');
				getList();
			}
		});
	});

	$(document).on('click', '.destroy', function () {
		var i = $('.destroy').index(this);
		$.ajax({
			type: "DELETE",
			url: "http://localhost:8080/api/todos/delete",
			contentType: "application/json",
			data: JSON.stringify({
				id: $('li').eq(i).attr('id'),
				completed: true,
			}),
			success: function () {
				getList();
			}
		});
	});
}

function getList() {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/api/todos/getlist",
		success: function (data) {
			setList(data);
		}
	});
}

function filter(bool){
$.ajax({
		type: "POST",
		url: "http://localhost:8080/api/todos/getlistbycomplete",
			contentType: "application/json",
			data: JSON.stringify({
				completed : bool,
			}),
		success: function (data) {
			setList(data);
		}
	});
}
