/**
 * 初始化 goup 插件
 */
$(document).ready(function(){
    $('.to-top').toTop({
        //options with default values
        autohide: true,
        offset: 420,
        speed: 500,
        position: true,
        right: 15,
        bottom: 30
    });
    // $('pre').addClass("line-numbers").css("white-space", "pre-wrap");
});
