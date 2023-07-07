/**
 * articleDetail.js
 */
 $('#share').share({sites: ['qzone', 'qq', 'weibo','wechat']});
 $("#like").click(function () {

                if(this.className=="grey")
                {
                    addLikePost(this);
                }else if(this.className=="red")
                {
                    unLikePost(this);
                }
            });

function addLikePost(self){
     var data = {articleId: $('#articleId').val()};
     var option = {
                   url: '/articleLike/like',
                   type: 'POST',
                   data: JSON.stringify(data),
                   dataType: "json",
                   contentType: 'application/json',
                   success: function (result)
                   {
                        if(result.success)
                        {
                          originLikeNum=parseInt(self.children[1].textContent);
                          newLikeNum=originLikeNum+1;
                          self.children[1].setHTML(newLikeNum);
                          self.className="red";

                        }else
                        {
                           $('#errorMsg').html(result.msg);
                           $('#errorMsg').show();
                        }
                   }
               };
    $.ajax(option);
}

function unLikePost(self){
     var data = {articleId: $('#articleId').val()};
     var option = {
                   url: '/articleLike/unlike',
                   type: 'POST',
                   data: JSON.stringify(data),
                   dataType: "json",
                   contentType: 'application/json',
                   success: function (result)
                   {
                        if(result.success)
                        {
                          originLikeNum=parseInt(self.children[1].textContent);
                          newLikeNum=originLikeNum-1;
                          self.children[1].setHTML(newLikeNum);
                          self.className="grey";
                        }else
                        {
                           $('#errorMsg').html(result.msg);
                           $('#errorMsg').show();
                        }
                   }
               };
    $.ajax(option);
}
