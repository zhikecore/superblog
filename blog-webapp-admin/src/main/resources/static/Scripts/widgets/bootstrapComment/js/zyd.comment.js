/**
 * bootstrap版评论框
 */
$.extend({
    comment: {
        submit: function (target) {
            var $this = $(target);
            //$this.button('loading');

			/*$('#detail-modal').modal('show');
			$(".close").click(function() {
				setTimeout(function() {
					$this.html("<i class='fa fa-close'></i>取消操作...");
					setTimeout(function() {
						$this.button('reset');
					}, 1000);
				}, 500);
			});*/
            // 模态框抖动
            //		$('#detail-modal .modal-content').addClass("shake");
			/*$("#detail-form-btn").click(function() {
				$.ajax({
					type: "get",
					url: "./server/comment.json",
					async: true,
					success: function(json) {
						if(json.statusCode == 200) {
							console.log(json.message);
						} else {
							console.error(json.message);
						}
						$('#detail-modal').modal('hide');

						setTimeout(function() {
							$this.html("<i class='fa fa-check'></i>" + json.message);
							setTimeout(function() {
								$this.button('reset');
								window.location.reload();
							}, 1000);
						}, 1000);
					},
					error: function(data) {
						console.error(data);
					}
				});
			});*/

            var questionId = $('#qId').val();
            var msg = filterXSS(editor.txt.html());
            addComment(questionId, msg);

        },
        reply: function (pid,commentId,toUserId, c) {
            console.log(pid);
            $('#comment-pid').val(pid);
            $('#cancel-reply').show();
            $('.comment-reply').show();
            $(c).hide();
            $(c).parents('.comment-body').append($('#comment-post'));
            //$(c).parent().parent().parent().append($('#comment-post'));

            //var msg = filterXSS(editor.txt.html());
            //addReply(commentId, toUserId, msg);

        },
        cancelReply: function (c) {
            $('#comment-pid').val("");
            $('#cancel-reply').hide();
            $(c).parents(".comment-body").find('.comment-reply').show();
            //			$(c).parent().parent().parent().find('.comment-reply').show();
            $("#comment-place").append($('#comment-post'));
        }
    }
});

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
    $("#comment-form-btn").click(function () {
        $.comment.submit($(this));
        //alert(filterXSS(editor.txt.html()));
    });
})

//添加到评论
function addComment(questionId,msg) {
    $.ajax({
        url: "/Comment/Create",
        type: "post",
        data: {
            questionId: questionId,
            msg: msg
        },
        dataType: "json",
        success: function (data) {
            $("#errormsg").html(data.Msg).show(300).delay(3000).hide(300);
            editor.txt.clear();

            //reload the comments

        }
    });
}

//添加到回复
function addReply(commentId,toUserId, msg) {
    $.ajax({
        url: "/Replay/Create",
        type: "post",
        data: {
            commentId: commentId,
            toUserId: toUserId,
            msg: msg
        },
        dataType: "json",
        success: function (data) {
            $("#errormsg").html(data.Msg).show(300).delay(3000).hide(300);
            editor.txt.clear();

            //reload the comments

        }
    });
}

function loadComment() {

    $.ajax({
        url: "/Comment/Create",
        type: "post",
        data: {
            questionId: questionId,
            msg: msg
        },
        dataType: "json",
        success: function (data) {
            $("#errormsg").html(data.Msg).show(300).delay(3000).hide(300);
            editor.txt.clear();

            //reload the comments

        }
    });
}
