
/**
* Helper类
* 功能:通用的JS方法
*/
var Helpers = (function () {
    var _helpers = {};

    //测试
    _helpers.sayHi = function (name) {
        alert(name);
    }

    //json日期格式转换为正常格式
    _helpers.JsonDateFormat = function (jsonDate) {

        try {
            var date = new Date(parseInt(jsonDate.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            var hours = date.getHours();
            var minutes = date.getMinutes();
            var seconds = date.getSeconds();
            var milliseconds = date.getMilliseconds();
            var t = date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds + "." + milliseconds;
            return date.getFullYear() + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds + "." + milliseconds;
        } catch (ex) {
            return "";
        }
    }

    return _helpers;
})();