<%@ page language="java" contentType="text/html; charset=UTF-8"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no">
    <title>播放器</title>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="msapplication-tap-highlight" content="no">
    <style>
        body{
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="video-container" style="margin: 0px auto;">
<p>
       目前写死内容，后续动态加载
</p>
</div>
<script src="//imgcache.qq.com/open/qcloud/video/vcplayer/TcPlayer-2.2.0.js"></script>
<script>
    (function(){
        function getParams(name) {
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return decodeURIComponent(r[2]);
            }
            return null;
        }
        var rtmp = getParams('rtmp'),
            flv  = getParams('flv'),
            m3u8 = getParams('m3u8'),
            mp4  = getParams('mp4'),
            live = (getParams('live') == 'true' ? true : false),
            coverpic = getParams('coverpic'),
            width = getParams('width'),
            height = getParams('height'),
            autoplay = (getParams('autoplay') == 'true' ? true : false);
        /**
         * 视频类型播放优先级
         * mobile ：m3u8>mp4
         * PC ：RTMP>flv>m3u8>mp4
         */

        /**
         * 属性说明：
         *
         * coverpic  {Object|String} src:图片地址，style：default 居中1:1显示 stretch 拉伸铺满，图片可能会变形 cover 等比横向铺满，图片某些部分可能无法显示在区域内
         *  封面在 ios10 暂时无法生效。
         */
        var options = {
            rtmp: rtmp,
            flv: flv ,
            mp4 : mp4 || 'http://1253668508.vod2.myqcloud.com/32d70eabvodgzp1253668508/c9a4a4e09031868223010453695/f0.mp4',
            coverpic: coverpic || {style:'cover', src:'http://1253668508.vod2.myqcloud.com/32d70eabvodgzp1253668508/c9a517589031868223010454065/9031868223010454066.jpg'},
            autoplay: autoplay ? true : false,
            live: live,
            width : width || '480',
            height : height || '320'
        };

        var player = new TcPlayer('video-container', options);
        window.qcplayer  = player;
    })();


</script>
</body>
</html><!--[if !IE]>|xGv00|2efb8ad21db06fa5ed35b57c7e345d06<![endif]-->