/**
 * Created by Li He on 2015/4/13.
 */

(function () {
    var allInputs = $(':input'),
        eventMap = {
            change: true,
            focus: true,
            blur: false
            // add any other events according to your requirements
        },
        timer = {},
        parsedCookie,

        handler,
        signaler,
        parseCookie;

    parseCookie = function (cookie) {
        var ret = {},
            arr,
            i;

        arr = cookie.split('; ');
        $.each(arr, function (index, value) {
            i = value.indexOf('=');
            if (i !== -1) {
                ret[value.substring(0, i)] = value.substring(i+1);
            }
        });
        return ret;
    };

    parsedCookie = parseCookie(document.cookie);

    handler = function (event) {
        var name = event.target.name;

        if (timer[name]) {
            clearTimeout(timer[name]);
            delete timer[name];
        }
        timer[name] = setTimeout(function () {
            signaler(event);
            delete timer[name];
        }, 300);
    };

    signaler = function (event) {
        // jquery's param method will automatically execute encodeURIComponent method upon the arg.
        var params = $.param({
                formName: event.target.form.name,
                inputName: event.target.name,
                inputTag: event.target.tagName.toUpperCase(),
                inputValue: event.target.value,
                eventType: event.type,
                eventTime: event.timeStamp,
                yrd_cid: parsedCookie['yrd_cid'],
                yrd_uid: parsedCookie['yrd_uid']
            });

        $.ajax({
            data: btoa(params),
            //data: params,
            url: 'http://form.yirendai.com/',
            dataType:'jsonp',
            jsonp: false,
            cache: true
        });
    };

    for(var event in eventMap){
        if (eventMap.hasOwnProperty(event) && eventMap[event]) {
            allInputs.on(event, handler);
        }
    }
})();