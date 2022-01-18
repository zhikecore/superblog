$(function () {
    // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.

    refreshWords();
    $('#huan').click(function () {
        refreshWords();
    })
});


jQuery(function () {

    //loadHotActivityData();
    //loadTopActivityData();
});

function loadTopActivityData() {
    $.ajax({
        url: "/Activity/GetTopActivities",
        type: "get",
        dataType: "json",
        beforeSend: function (jqXHR) {
            //loadingNo();
            //jqXHR.setRequestHeader('secretKey', key);
            //jqXHR.setRequestHeader('appKey', 'appKey');
        },
        success: function (data) {
            if (data && data.data && data.data.length > 0) {
                $('#list-activity').html('');
                $('#activity-main-tpl').tmpl(data.data).appendTo('#list-activity');

            }
        }
    });
}

function loadHotActivityData() {

    $.ajax({
        url: "/Activity/GetHotActivities",
        type: "get",
        dataType: "json",
        beforeSend: function (jqXHR) {
            //loadingNo();
            //jqXHR.setRequestHeader('secretKey', key);
            //jqXHR.setRequestHeader('appKey', 'appKey');
        },
        success: function (data) {
            if (data && data.data && data.data.length > 0) {
                $('#carousel_listbox').html('');
                $('#carousel-main-tpl').tmpl(data.data).appendTo('#carousel_listbox');

            }
        }
    });
}

function refreshWords() {
    $.ajax({
        url: "/HotWord/GetTops",
        type: "get",
        dataType: "json",
        beforeSend: function (jqXHR) {
            //loadingNo();
            //jqXHR.setRequestHeader('secretKey', key);
            //jqXHR.setRequestHeader('appKey', 'appKey');
        },
        success: function (data) {
            $('#tagcloud').empty();
            $("#tagcloud").jQCloud(data);
        }
    });
}