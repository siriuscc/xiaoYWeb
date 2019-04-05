/**
 * author:sirius
 * date:20180505
 */
(function ($) {

    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
    /**
     * 设置 键值对
     * @param name
     * @param value
     */
    $.setCookie = function (name, value) {
        var Days = 30;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    }

    /**
     * 将json对象转为字符串存入Cookies
     * @param name
     * @param value Json 对象
     */
    $.setCookieJson = function (name, value) {

        value=JSON.stringify(value);
        $.setCookie(name,value);
    }

    /**
     * 将json对象直接获取
     * @param name
     * @returns {*}
     */
    $.getCookie = function (name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg))
            return unescape(arr[2]);
        else
            return null;
    }

    /**
     * 将获取到的Json字符串转为Json对象返回
     * @param name
     * @returns Json
     */
    $.getCookieJson = function (name) {

        return $.parseJSON($.getCookie(name));
    }

    $.delCookie = function (name) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var cval = getCookie(name);
        if (cval != null)
            document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
    }

    $.log=function(){

        if(window.debug==true) {
            var args = arguments;
            console.log(args);
        }
    }
    $.alert=function(msg){

        if(window.debug==true){
            alert((msg));
        }
    }

    $.getClientInfo=function(){
        var userAgentInfo = navigator.userAgent;
        var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
        var agentinfo = null;
        for (var i = 0; i < Agents.length; i++) {
            if (userAgentInfo.indexOf(Agents[i]) > 0) { agentinfo = userAgentInfo; break; }
        }
        if(agentinfo){
            return agentinfo;
        }else{
            return "PC";
        }
    }

})(jQuery);
