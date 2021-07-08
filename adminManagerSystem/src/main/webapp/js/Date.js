/**
 * 时间工具类
 */

/**
 * 获取现在的时间戳（精确到s）
 * 使用：new Date().nowTimestamp
 * @type {number}
 */
Date.prototype.nowTimestamp = Date.parse(new Date()) / 1000;

/**
 * 时间戳转换成Date
 * 使用：new Date().timestampToDate(1493890419)
 * @param timestamp
 * @returns {*}
 */
Date.prototype.timestampToDate = function (timestamp) {
    timestamp = parseInt(timestamp);
    if (timestamp.toString().length != 10) {
        return null;
    }
    return new Date(timestamp * 1000);
}

/**
 * 格式化时间
 * 使用1：new Date().timestampToDate(1493890419).format("yyyy-MM-dd hh:mm:ss")
 * 使用2：new Date(1493890419 * 1000).format("yyyy-MM-dd hh:mm:ss")
 * @param format
 * @returns {*}
 */
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}