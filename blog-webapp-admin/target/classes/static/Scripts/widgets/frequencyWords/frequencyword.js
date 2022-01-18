var bcjson = ["#ff9900", "#3dbeb6", "#a3eee9", "#f8cd8c"];
var cjson = ["#fff", "black"];
$(function () {

    $(".list1").children("li").each(function () {
        $(this).css({
            "background-color": bcjson[Math.floor(Math.random() * 4)],
            "color": cjson[Math.floor(Math.random() * 2)]
        });
    })

    $("#huan").click(function () {
        $(".listchange" + listitem).siblings("ul").css("display", "none");
        $(".listchange" + listitem).css("display", "flex");
        if (listitem < listitemmax) {
            listitem++;
        } else {
            listitem = 1;
        }
    });

})
//代表第一次换的是第二组
var listitem = 2;

//这是要换的批数
var listitemmax = 4;
