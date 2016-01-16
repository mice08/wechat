/**
 * Created by cely on 2016/1/12.
 */
var countdomn = {
    init: function (options) {
        var that = this;
        this.options = options;
        this.differ_time = options.time;
        this.time = setInterval(function () {
            if (that.differ_time <= 1000) {
                this.options.onStop(that);
            }
            that.update(that.differ_time);
            var data = {};
            if (that.dd > 99) {
                data.d = 99;
                data.h = 23;
                data.m = 59;
                data.s = 59;
            } else {
                data.d = that.dd < 10 ? '0' + that.dd : that.dd;
                data.h = that.hh < 10 ? '0' + that.hh : that.hh;
                data.m = that.mm < 10 ? '0' + that.mm : that.mm;
                data.s = that.seconds < 10 ? '0' + that.seconds : that.seconds;
            }
            that.options.onChange(data);
        }, 1000);
    },
    update: function (date) {
        this.differ_time = date = date - 1000;
        if (date <= 0) {
            clearInterval(this.time);
            this.options.onStop(this);
        }
        this.dd = this.getDays(date);
    },
    setTimer: function (time) {
        this.differ_time = time;
    },
    getDays: function (date) {
        var day = parseInt(date / (1000 * 60 * 60 * 24));
        if (day > 0) {
            date = date - 1000 * 60 * 60 * 24 * day;
        }
        this.hh = this.getHour(date);
        return day;
    },
    getHour: function (date) {
        var hours = parseInt(date / (1000 * 60 * 60));
        if (hours > 0) {
            date = date - 1000 * 60 * 60 * hours;
        }
        this.mm = this.getMinute(date);
        return hours;
    },
    getMinute: function (date) {
        var minute = parseInt(date / (1000 * 60));
        if (minute > 0) {
            date = date - 1000 * 60 * minute;
        }
        this.seconds = this.getSecond(date);
        return minute;
    },
    getSecond: function (date) {
        var seconds = parseInt(date / 1000);
        if (seconds > 0) {
            date = date - 1000 * seconds;
        }
        return seconds;
    }
}