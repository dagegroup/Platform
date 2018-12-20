;(function(){var c=function(){var e=[].slice.call(arguments);e.push(c.options);if(e[0].match(/^\s*#([\w:\-\.]+)\s*$/igm)){e[0].replace(/^\s*#([\w:\-\.]+)\s*$/igm,function(h,i){var f=document;var g=f&&f.getElementById(i);e[0]=g?(g.value||g.innerHTML):h;});}if(arguments.length==1){return c.compile.apply(c,e);}if(arguments.length>=2){return c.to_html.apply(c,e);}};var d={escapehash:{"<":"&lt;",">":"&gt;","&":"&amp;",'"':"&quot;","'":"&#x27;","/":"&#x2f;"},escapereplace:function(e){return d.escapehash[e];},escaping:function(e){return typeof(e)!=="string"?e:e.replace(/[&<>"]/igm,this.escapereplace);},detection:function(e){return typeof(e)==="undefined"?"":e;}};var b=function(e){if(typeof(console)!=="undefined"){if(console.warn){console.warn(e);return;}if(console.log){console.log(e);return;}}throw (e);};var a=function(h,f){h=h!==Object(h)?{}:h;if(h.__proto__){h.__proto__=f;return h;}var g=function(){};var j=Object.create?Object.create(f):new (g.prototype=f,g);for(var e in h){if(h.hasOwnProperty(e)){j[e]=h[e];}}return j;};c.__cache={};c.version="0.6.5-stable";c.settings={};c.tags={operationOpen:"{@",operationClose:"}",interpolateOpen:"\\${",interpolateClose:"}",noneencodeOpen:"\\$\\${",noneencodeClose:"}",commentOpen:"\\{#",commentClose:"\\}"};c.options={cache:true,strip:true,errorhandling:true,detection:true,_method:a({__escapehtml:d,__throw:b,__juicer:c},{})};c.tagInit=function(){var f=c.tags.operationOpen+"each\\s*([^}]*?)\\s*as\\s*(\\w*?)\\s*(,\\s*\\w*?)?"+c.tags.operationClose;var h=c.tags.operationOpen+"\\/each"+c.tags.operationClose;var i=c.tags.operationOpen+"if\\s*([^}]*?)"+c.tags.operationClose;var j=c.tags.operationOpen+"\\/if"+c.tags.operationClose;var n=c.tags.operationOpen+"else"+c.tags.operationClose;var o=c.tags.operationOpen+"else if\\s*([^}]*?)"+c.tags.operationClose;var k=c.tags.interpolateOpen+"([\\s\\S]+?)"+c.tags.interpolateClose;var l=c.tags.noneencodeOpen+"([\\s\\S]+?)"+c.tags.noneencodeClose;var m=c.tags.commentOpen+"[^}]*?"+c.tags.commentClose;var g=c.tags.operationOpen+"each\\s*(\\w*?)\\s*in\\s*range\\(([^}]+?)\\s*,\\s*([^}]+?)\\)"+c.tags.operationClose;var e=c.tags.operationOpen+"include\\s*([^}]*?)\\s*,\\s*([^}]*?)"+c.tags.operationClose;c.settings.forstart=new RegExp(f,"igm");c.settings.forend=new RegExp(h,"igm");c.settings.ifstart=new RegExp(i,"igm");c.settings.ifend=new RegExp(j,"igm");c.settings.elsestart=new RegExp(n,"igm");c.settings.elseifstart=new RegExp(o,"igm");c.settings.interpolate=new RegExp(k,"igm");c.settings.noneencode=new RegExp(l,"igm");c.settings.inlinecomment=new RegExp(m,"igm");c.settings.rangestart=new RegExp(g,"igm");c.settings.include=new RegExp(e,"igm");};c.tagInit();c.set=function(f,j){var h=this;var e=function(i){return i.replace(/[\$\(\)\[\]\+\^\{\}\?\*\|\.]/igm,function(l){return"\\"+l;});};var k=function(l,m){var i=l.match(/^tag::(.*)$/i);if(i){h.tags[i[1]]=e(m);h.tagInit();return;}h.options[l]=m;};if(arguments.length===2){k(f,j);return;}if(f===Object(f)){for(var g in f){if(f.hasOwnProperty(g)){k(g,f[g]);}}}};c.register=function(g,f){var e=this.options._method;if(e.hasOwnProperty(g)){return false;}return e[g]=f;};c.unregister=function(f){var e=this.options._method;if(e.hasOwnProperty(f)){return delete e[f];}};c.template=function(e){var f=this;this.options=e;this.__interpolate=function(g,l,i){var h=g.split("|"),k=h[0]||"",j;if(h.length>1){g=h.shift();j=h.shift().split(",");k="_method."+j.shift()+".call({}, "+[g].concat(j)+")";}return"<%= "+(l?"_method.__escapehtml.escaping":"")+"("+(!i||i.detection!==false?"_method.__escapehtml.detection":"")+"("+k+")) %>";};this.__removeShell=function(h,g){var i=0;h=h.replace(c.settings.forstart,function(n,k,m,l){var m=m||"value",l=l&&l.substr(1);var j="i"+i++;return"<% ~function() {for(var "+j+" in "+k+") {if("+k+".hasOwnProperty("+j+")) {var "+m+"="+k+"["+j+"];"+(l?("var "+l+"="+j+";"):"")+" %>";}).replace(c.settings.forend,"<% }}}(); %>").replace(c.settings.ifstart,function(j,k){return"<% if("+k+") { %>";}).replace(c.settings.ifend,"<% } %>").replace(c.settings.elsestart,function(j){return"<% } else { %>";}).replace(c.settings.elseifstart,function(j,k){return"<% } else if("+k+") { %>";}).replace(c.settings.noneencode,function(k,j){return f.__interpolate(j,false,g);}).replace(c.settings.interpolate,function(k,j){return f.__interpolate(j,true,g);}).replace(c.settings.inlinecomment,"").replace(c.settings.rangestart,function(m,l,n,k){var j="j"+i++;return"<% ~function() {for(var "+j+"="+n+";"+j+"<"+k+";"+j+"++) {{var "+l+"="+j+"; %>";}).replace(c.settings.include,function(l,j,k){return"<%= _method.__juicer("+j+", "+k+"); %>";});if(!g||g.errorhandling!==false){h="<% try { %>"+h;h+='<% } catch(e) {_method.__throw("Juicer Render Exception: "+e.message);} %>';}return h;};this.__toNative=function(h,g){return this.__convert(h,!g||g.strip);};this.__lexicalAnalyze=function(k){var j=[];var o=[];var n="";var g=["if","each","_","_method","console","break","case","catch","continue","debugger","default","delete","do","finally","for","function","in","instanceof","new","return","switch","this","throw","try","typeof","var","void","while","with","null","typeof","class","enum","export","extends","import","super","implements","interface","let","package","private","protected","public","static","yield","const","arguments","true","false","undefined","NaN"];var m=function(r,q){if(Array.prototype.indexOf&&r.indexOf===Array.prototype.indexOf){return r.indexOf(q);}for(var p=0;p<r.length;p++){if(r[p]===q){return p;}}return -1;};var h=function(p,i){i=i.match(/\w+/igm)[0];if(m(j,i)===-1&&m(g,i)===-1&&m(o,i)===-1){if(typeof(window)!=="undefined"&&typeof(window[i])==="function"&&window[i].toString().match(/^\s*?function \w+\(\) \{\s*?\[native code\]\s*?\}\s*?$/i)){return p;}if(typeof(global)!=="undefined"&&typeof(global[i])==="function"&&global[i].toString().match(/^\s*?function \w+\(\) \{\s*?\[native code\]\s*?\}\s*?$/i)){return p;}if(typeof(c.options._method[i])==="function"||c.options._method.hasOwnProperty(i)){o.push(i);return p;}j.push(i);}return p;};k.replace(c.settings.forstart,h).replace(c.settings.interpolate,h).replace(c.settings.ifstart,h).replace(c.settings.elseifstart,h).replace(c.settings.include,h).replace(/[\+\-\*\/%!\?\|\^&~<>=,\(\)\[\]]\s*([A-Za-z_]+)/igm,h);for(var l=0;l<j.length;l++){n+="var "+j[l]+"=_."+j[l]+";";}for(var l=0;l<o.length;l++){n+="var "+o[l]+"=_method."+o[l]+";";}return"<% "+n+" %>";};this.__convert=function(h,i){var g=[].join("");g+="'use strict';";g+="var _=_||{};";g+="var _out='';_out+='";if(i!==false){g+=h.replace(/\\/g,"\\\\").replace(/[\r\t\n]/g," ").replace(/'(?=[^%]*%>)/g,"\t").split("'").join("\\'").split("\t").join("'").replace(/<%=(.+?)%>/g,"';_out+=$1;_out+='").split("<%").join("';").split("%>").join("_out+='")+"';return _out;";return g;}g+=h.replace(/\\/g,"\\\\").replace(/[\r]/g,"\\r").replace(/[\t]/g,"\\t").replace(/[\n]/g,"\\n").replace(/'(?=[^%]*%>)/g,"\t").split("'").join("\\'").split("\t").join("'").replace(/<%=(.+?)%>/g,"';_out+=$1;_out+='").split("<%").join("';").split("%>").join("_out+='")+"';return _out.replace(/[\\r\\n]\\s+[\\r\\n]/g, '\\r\\n');";return g;};this.parse=function(h,g){var i=this;if(!g||g.loose!==false){h=this.__lexicalAnalyze(h)+h;}h=this.__removeShell(h,g);h=this.__toNative(h,g);this._render=new Function("_, _method",h);this.render=function(k,j){if(!j||j!==f.options._method){j=a(j,f.options._method);}return i._render.call(this,k,j);};return this;};};c.compile=function(g,f){if(!f||f!==this.options){f=a(f,this.options);}try{var h=this.__cache[g]?this.__cache[g]:new this.template(this.options).parse(g,f);if(!f||f.cache!==false){this.__cache[g]=h;}return h;}catch(i){b("Juicer Compile Exception: "+i.message);return{render:function(){}};}};c.to_html=function(f,g,e){if(!e||e!==this.options){e=a(e,this.options);}return this.compile(f,e).render(g,e._method);};typeof(module)!=="undefined"&&module.exports?module.exports=c:this.juicer=c;})();
var miniHtml = '<script type="text/template" id="jy-minitemplate"><table cellspacing="0" cellpadding="0"><tr><th width="50%">申请金额</th><td>{{principal}}元</td></tr><tr><th>借款总成本(利息+平台服务费)</th><td>{{totalFee}}元</td></tr><tr><th>月还款额</th><td><strong>{{month_pay}}元</strong></td></tr></table><a href="index-18.htm"/*tpa=http://www.yirendai.com/borrowguide/*/  class="gray_btn counter_button cms_jk" id="borrow_but">我要借款</a></script><script type="text/template" id="lender-minitemplate"><table cellspacing="0" cellpadding="0"><tr><th>出借金额(本金)</th><td>{{principal}}元</td></tr><tr><th>收获利息(利息)</th><td>{{interest}}元</td></tr><tr><th>回款总额</th><td><strong>{{repay}}</strong>元</td></tr></table><a href="1-1.htm"/*tpa=http://www.yirendai.com/loan/list/1*/ class="gray_btn counter_button cms_cj" id="lend_but">我要出借</a></script><!-- calculator mini default style="width:267px/703px;display:none;"--><div id="LRfloater0"><div class="calmini clearfix" style="width:267px;"><div class="calminiL" style="width:29px;"><a href="javascript:void(0);" class="calminiLBar defaultOn"><span class="png"></span></a><div class="calminiLM" style="display:none;"><div class="calminiLMT clearfix"><strong>计算结果</strong><span class="l">不够详细？<a class="calResultUrl" href="javascript:void(0);">点击这里</a></span></div><div class="calminiLMTable"></div></div></div><div class="calminiR"><div class="calminiRTabC"><form><div class="calminiRTabC_t"><ul><li id="result0">借款金额：<span>0</span>元</li><li id="result1">借款期限：<span>0</span>期</li></ul></div><div class="calminiRTabC_m"><input id="miniInput0" class="miniText principal" value="输入借款金额" type="text" maxlength="7" /><div id="miniSel0" class="miniSel"><div class="miniSelT clearfix"><input class="miniSelInput period" type="text" value="选择借款期数"/><a class="miniSelIcon" href="javascript:void(0);"></a></div><div class="miniSelList" style="display:none;"><ul><li><span>12</span>期</li><li><span>18</span>期</li><li><span>24</span>期</li><li><span>36</span>期</li><li><span>48</span>期</li></ul></div></div><input class="rate" id="rate" type="hidden" value="" /><input class="mode" type="hidden" value="jy" /><a class="miniBtn" id="jyd" href="javascript:void(0);">计算</a></div></form></div><div class="calminiRTabC" style="display:none;"><form><div class="calminiRTabC_t"><ul><li id="result2">出借金额：<span>0</span>元</li><li id="result3">出借利率：<span>0</span>%</li><li id="result4">出借期限：<span>0</span>期</li></ul></div><div class="calminiRTabC_m"><input id="miniInput1" class="miniText principal" type="text" value="输入借款金额" maxlength="7"/><div id="miniSel1" class="miniSel"><div class="miniSelT clearfix"><input class="miniSelInput rate" id="lRate" type="text" value="选择借款期数"/><a class="miniSelIcon" href="javascript:void(0);"></a></div><div class="miniSelList" style="display:none;"><ul><li><span>10</span>%</li><li><span>10.5</span>%</li><li><span>11</span>%</li><li><span>12</span>%</li></ul></div></div><div id="miniSel2" class="miniSel"><div class="miniSelT clearfix"><input class="miniSelInput period" id="lPeriod" type="text" value="选择借款期数"/><a class="miniSelIcon" href="javascript:void(0);"></a></div><div id="miniSelscroll" class="miniSelList" style="display:none;"><ul><li><span>12</span>期</li><li><span>18</span>期</li><li><span>24</span>期</li><li><span>36</span>期</li><li><span>48</span>期</li><li><span>1</span>期</li><li><span>2</span>期</li><li><span>3</span>期</li><li><span>4</span>期</li><li><span>5</span>期</li><li><span>6</span>期</li><li><span>7</span>期</li><li><span>8</span>期</li><li><span>9</span>期</li><li><span>10</span>期</li><li><span>11</span>期</li><li><span>13</span>期</li><li><span>14</span>期</li><li><span>15</span>期</li><li><span>16</span>期</li><li><span>17</span>期</li><li><span>19</span>期</li><li><span>20</span>期</li><li><span>21</span>期</li><li><span>22</span>期</li><li><span>23</span>期</li><li><span>25</span>期</li><li><span>26</span>期</li><li><span>27</span>期</li><li><span>28</span>期</li><li><span>29</span>期</li><li><span>30</span>期</li><li><span>31</span>期</li><li><span>32</span>期</li><li><span>33</span>期</li><li><span>34</span>期</li><li><span>35</span>期</li></ul></div></div><input class="mode" type="hidden" value="lender" /><a class="miniBtn" id="normalCal2" href="javascript:void(0);">计算</a></div></form></div><div class="calminiRT"><a class="calminiClose" href="javascript:void(0);"></a><div class="calminiRTabT"><a class="curr" href="javascript:void(0);">借款计算</a><a href="javascript:void(0);">出借计算</a></div></div></div><div class="calminiArrow"></div></div></div>';
$(function(){
    //侧栏隐藏/显示
    var isIE=!!window.ActiveXObject;
    var isIE6 = isIE&&!window.XMLHttpRequest;
    $("#hideAside").on('click',function(){
        if(isIE6){
            $(".aside,.asideBg").hide();
            $("#asideHide").show();
        }
        else {
            $(".aside,.asideBg").animate({
                right: "-40px"
            }, 700, function () {
                $("#asideHide").animate({
                    right: "0"
                }, 700);
            })
        }
    });
    $("#asideHide").on('click',function(){
        if(isIE6){
            $(".aside,.asideBg").show();
            $("#asideHide").hide();
        }
        else {
            $("#asideHide").animate({
                right: "-76px"
            }, 700, function () {
                $(".aside,.asideBg").animate({
                    right: "0"
                }, 700);
            })
        }
    })
    juicer.set({
        'tag::interpolateOpen': '{{',
        'tag::interpolateClose': '}}'
    });
    var calj = new Calculator('jy'),
        caln = new Calculator('normal');

    function getCal(mode){
        if(mode==='jy'){
            return calj;
        } else {
            return caln;
        }
    }

    var rate_dic = {12:10, 18: 10.5, 24:11, 36:12,48:12.5};

    // 选择下拉菜单传值
    $("body").on("click", ".miniSelList ul li", function(){
        var mode = $(this).closest(".calminiRTabC_m").find(".mode").val();
        if(mode == 'jy'){
            $(this).closest(".calminiRTabC_m").find(".rate").val(rate_dic[$(this).find("span").text()]);
        }
    })

    // 提交计算按钮
    $("body").on("click", ".miniBtn", function(){

        // element define
        var $this = $(this),
            $con = $this.closest('.calminiRTabC_m');
        // data define
        var principal = $.trim($con.find('.principal').val()),
            period = $con.find('.period').val(),
            rate = $con.find('.rate').val(),
            mode = $con.find('.mode').val(),
            range_dic = {'jy':{'min':10000, 'max':500000}, 'normal':{'min':1000, 'max':500000}, 'lender':{'min':100,'max':5000000}};

        var modeShow = (mode==='lender'?"出借":"借款");

        // to do
        // principal
        if(principal.length==0||(principal=='"输入'+modeShow+'金额"')){
            $con.find('.principal').focus().animate({
                backgroundColor:'#ecbebf',
                borderColor:'#cc1115'
            });
            return;
        } else if(!/^[0-9]+(.[0-9]{1,2})?$/.test(principal)){
            $con.find('.principal').toFA();
            return;
        } else if(principal<range_dic[mode]['min']||principal>range_dic[mode]['max']){

            if(mode==='lender'){
                $('#result2').find('span').text('100元-500万').css('color','#f00');
            } else {
                $('#result0').find('span').text('1万-50万').css('color','#f00');
            }

            $con.find('.principal').focus().toFA();
            return;
        } else{
            $con.find('.principal').toFAC({bgCol:'#F1F2F1',lineCol:'#7E8A8E'});
        }

        // rate
        if(mode == 'lender'){
            if((rate.length==0)||(rate=='选择出借利率')){
                $con.find('.rate').toFAC();
                return;
            } else {
                $con.find('.rate').toFAC({bgCol:'#F1F2F1',lineCol:'#7E8A8E'});
            }
        }

        // period
        if((period.length==0)||(period=='选择'+modeShow+'期数')){
            $con.find('.period').toFAC();
            return;
        } else {
            $con.find('.period').toFAC({bgCol:'#F1F2F1',lineCol:'#7E8A8E'});
        }

        // 
        var plan = getCal(mode).calculate(principal, period, rate/100,"online");
        plan['mode'] = mode;
        plan['repay'] = round(parseFloat(plan.principal) + parseFloat(plan.interest));
        // 利息+平台费
        plan['total2'] = round(parseFloat(plan.total) - parseFloat(plan.principal));
        var htmls = juicer($('#' + mode + '-minitemplate').html(), plan);
        $con.closest(".calmini").find(".calminiLMTable").html(htmls);
        $con.closest(".calmini").animate({width:703},function(){
            $(this).find(".calminiL").width('');
            $(this).find(".calminiLM").fadeIn();
            $(this).find(".calminiLBar").removeClass("defaultOn").addClass("barOn");
            // bar default
            $('.calminiLBar').on('click',openBar);
        });
    })

    //计算器
  //  var isOpenFlag = false;
    $(".aside").append(miniHtml);
    $(".calculator").on('click',function(){

            $(".calmini").show();

    })
    $(".calminiClose").on("click",function(){
        $(".calmini").hide();
    })
    //意见弹框
    $(".suggestion").on("click",function(){
        var loginName = $("#userCnname").attr("title");
        art.dialog.data("loginname",loginName);
        art.dialog.open(base_url+'/feedback_dialog',{
            id:'feedbackWindow',
            title: '宜人贷意见反馈',
            resize:false,//可拉伸弹出框开关
            fixed:true,
            lock:true,//锁屏
            opacity:.7,//锁屏背景透明度
            background:'#000',//锁屏背景颜色
            drag:false,//拖动开关
            width:700,
            height:430
        });
        return false;
    });
    //二维码浮层
    $(".qrCode").toggle(function(){
        $(".qrCode-tips").show();
    },function(){
        $(".qrCode-tips").hide();
    })
    //APP下载跳转新窗口
    $(".downloadApp").on("click",function(){
        window.open( "http://app.yirendai.com/","_blank");
        return false;
    })
    //返回顶部

    $(".goTop").click(function(){
        $('html,body').animate({scrollTop:0},700);
    })
    $(".cms_jk").live('click',function(){
        setjkurl();
    })
    $(".cms_cj").live('click',function(){
        setjkurl();
    })

    $('body').on('click','.calResultUrl',function(){
        var m = $('http://www.yirendai.com/static/js/aside/.calminiRTabT a.curr').index();
        if(!m){
            var pr = $("#result0 span").text(),
                pe = $("#result1 span").text();
            var rPart = '';
        } else {
            var pr = $("#result2 span").text(),
                ra = $("#result3 span").text(),
                pe = $("#result4 span").text();
            var rPart = '&ra='+ra;
        }
        $(this).attr('href',base_url + '/support/others/newjsq/?m='+m+'&pr='+pr+'&pe='+pe+rPart);
    });

    // 
    $('body').on('click','.calminiPic',function(){
        $(".calmini").show();
        $(".calminiLBar").off('click').addClass('defaultOn');
    })

    $('.calminiLBar').click(function(){
        if(!$(this).hasClass("defaultOn")){
            openBar();
        }
    })

    // calminiRTab
    $(".calminiRTabT a").click(function(){
        var $bar = $('.calminiLBar'),
            $calmini = $bar.closest(".calmini"),
            $calminiL = $calmini.find(".calminiL"),
            $calminiLM = $calmini.find(".calminiLM"),
            calmini_width = $calmini.width();
        $calmini.animate({width:267},function(){
            $calminiL.width(29);
            $calminiLM.hide();
            $bar.removeClass("barOn");
            $(".calminiLBar").addClass('defaultOn').off('click');
        })


        var aIndex = $(this).index();
        var $tabC = $(this).closest(".calminiR").find(".calminiRTabC");

        $(".calminiRTabT a").removeClass("curr");
        $(this).addClass("curr");
        $tabC.hide();
        $tabC.eq(aIndex).show();
    })

    // close calmini 
    $(".calminiClose").click(function(){
        $(this).closest(".calmini").hide();
        var $bar = $('.calminiLBar'),
            $calmini = $bar.closest(".calmini"),
            $calminiL = $calmini.find(".calminiL"),
            $calminiLM = $calmini.find(".calminiLM"),
            calmini_width = $calmini.width();
        $calmini.animate({width:267},function(){
            $calminiL.width(29);
            $calminiLM.hide();
            $bar.removeClass("barOn");
            $(".calminiLBar").off('click');
        })
        $calmini.find('form')[0].reset();
        $calmini.find('form')[1].reset();
        $(".calminiRTabC").hide();
        $(".calminiRTabC").first().show();
        $(".calminiRTabT a").removeClass("curr").first().addClass("curr");
        $(".calminiRTabC_t").find('li').find('span').text('');
        $(".calmini").find('input').css({'border':'1px solid #7E8A8E','backgroundColor':'#F1F2F1'})
    })

    // miniSel
    $("#miniSel0").miniSel({id:'result1'});
    $("#miniSel1").miniSel({id:'result3'});
    $("#miniSel2").miniSel({id:'result4'});
    $("#miniInput0").miniText({id:'result0'});
    $("#miniInput0,#miniInput1").on("keyup",function(){
        amount(this);
    })
    $("#miniInput1").miniText({id:'result2'});
    $("#miniSel0").miniText();
    $("#miniSel1").miniText();
    $("#miniSel2").miniText();

    $("#miniInput0").clearDefault({txt: '输入借款金额'});
    $("#miniSel0 .miniSelInput").clearDefault({txt: '选择借款期数', readonly:1});
    $("#miniInput1").clearDefault({txt: '输入出借金额'});
    $("#miniSel1 .miniSelInput").clearDefault({txt: '选择出借利率', readonly:1});
    $("#miniSel2 .miniSelInput").clearDefault({txt: '选择出借期数', readonly:1});

    // 解决ie6子菜单覆盖问题
    $("#miniSel1").css('z-index',10);
    $("#miniSel2").css('z-index',0);
    // calminiPic click
// $('').appendTo('body');
// $(".calmini").load('http://www.yirendai.com/static/js/aside/calculatorMini.html');

    //$("#miniSelscroll").perfectScrollbar();

})

// pluyin
$.fn.extend({

    // miniSel
    miniSel:function(options){
        var defaults = {
            id:''
        }
        var opts = $.extend(defaults,options);


        // var $miniSelList = $(this).find(".miniSelList");
        var $miniSelIconLi = $(this).find(".miniSelList").find("li"),
            $miniSelIcon = $(this).find(".miniSelIcon"),
            $miniSelInput = $(this).find(".miniSelInput");
        // show list
        function showSellist(){
            $("#miniSelscroll").scrollTop(0);
            //$("#miniSelscroll").perfectScrollbar('update');
            var $miniSelList = $(this).closest(".miniSel").find(".miniSelList"),
                $thisIconA = $(this).closest(".miniSelT").find(".miniSelIcon"),
                isCurr = $thisIconA.hasClass("curr");
            $xrail = $miniSelList.find('.ps-scrollbar-x-rail');
            $yrail = $miniSelList.find('.ps-scrollbar-y-rail');

            $(".miniSelList").hide();
            $(".miniSelIcon").removeClass("curr");
            $xrail.css('bottom',3);
            $yrail.css('top',3);

            if(!isCurr){
                $thisIconA.addClass("curr");
                $miniSelList.show();
            } else{
                $thisIconA.removeClass("curr");
                $miniSelList.hide();
            }
        }
        $miniSelIcon.click(showSellist);
        $miniSelInput.click(showSellist);

        // list hover
        $miniSelIconLi.hover(
            function(){
                $(this).addClass("on");
            },
            function(){
                $(this).removeClass("on");
            }
        )

        // list click
        $miniSelIconLi.click(function(){
            var $thisSelList = $(this).closest(".miniSelList");
            var $thisIconA = $(this).closest(".miniSel").find(".miniSelIcon");
            var $thisInput = $(this).closest(".miniSel").find(".miniSelInput");
            var spanVal = $(this).find("span").text();

            // $('#'+opts.id).find("span").text(spanVal);
            $('#'+opts.id).toValue(spanVal);
            $thisInput.val(spanVal);
            $thisIconA.removeClass("curr");
            $thisSelList.hide();
        })
    },

    // miniText
    miniText:function(options){
        var defaults = {
            id: '',
            txt: ''
        }
        var opts = $.extend(defaults,options);
        var $this = $(this);
        var x = $this.val();
        $this.focus(function(){
            var y = $this.val();
            if(x == y){
                $this.val('');
            }
        })

        $this.blur(function(){
            var y = $this.val();
            if(y == ''){
                $this.val(x);
            }
        })

        $(this).keyup(function(){
            var textVal = $(this).val();
            $('#'+opts.id).toValue(textVal);
        })
    },

    // toValue
    toValue:function(options){$(this).find("span").text(options).css('color','#fff');},

    // toDefault input
    clearDefault:function(options){
        var defaults = {
            txt: '',
            readonly: 0
        }
        var opts = $.extend(defaults,options);

        $(this).val(opts.txt);
        $(this).focus(function(){
            if($(this).val() == opts.txt){
                $(this).val('');
            }
        })
        $(this).blur(function(){
            if($(this).val() == ''){
                $(this).val(opts.txt);
            }
        })

        if(opts.readonly == 1){
            $(this).attr({'readonly':'readonly'});
        } else if(opts.disabled == 0){
            $(this).attr({'readonly':''});
        }

        // $(this).attr('placeholder',opts.txt);
    },

    // help_jyd_count3.js
    toFA:function(){
        $(this).focus().animate({
            backgroundColor:'#ecbebf',
            borderColor:'#cc1115'
        },function(){
            $(this).animate({
                backgroundColor:'#f78080',
                borderColor:'#cc1115'
            },500,function(){
                $(this).animate({
                    backgroundColor:'#ecbebf',
                    borderColor:'#cc1115'
                })
            })
        });
    },

    toFAC:function(options){
        var defaults = {
            bgCol: '#ecbebf',
            lineCol: '#cc1115'
        }
        var opts = $.extend(defaults,options);
        $this = $(this);
        $this.focus().animate({
            backgroundColor: opts.bgCol,
            borderColor: opts.lineCol
        })
    }
})

function openBar(){
    var $bar = $('.calminiLBar'),
        $calmini = $bar.closest(".calmini"),
        $calminiL = $calmini.find(".calminiL"),
        $calminiLM = $calmini.find(".calminiLM"),
        calmini_width = $calmini.width();

    // calminiL 29/473 calmini 256/703
    if(calmini_width == 267){
        $calmini.animate({width:703},function(){
            $calminiL.width('');
            $calminiLM.fadeIn();
            $bar.addClass("barOn");
        })
    } else if(calmini_width == 703){
        $calmini.animate({width:267},function(){
            $calminiL.width(29);
            $calminiLM.hide();
            $bar.removeClass("barOn");
        })

    }
};

function Calculator(mode){
    if(mode=='jy'){
        this.mode = 'jy';
    } else {
        this.mode = 'normal';
    }
}

Calculator.prototype.NORMAL = 'normal';
Calculator.prototype.JY = 'jy';
Calculator.prototype.calculate = function(principal, period, rate){
    if(!/\d+/.test(principal)){
        throw "principal value error!";
    }
    if((!/\d+/.test(period))||(period*10)%10!=0||period<1||period>48){
        throw "period value error!"
    }
    if(rate>0.2||rate<0.1){
        throw "interest rate value error!"
    }
    var mr = rate/12
        , mp = this._monthPay(principal, period, mr)
        , total = mp * period
        , interest = total - principal;
    var plan = {
        principal : principal,
        period :period,
        rate : rate,
        mrate: mr,
        total: total,
        interest :round(interest),
        mode : this.mode,
        month_pay : round(mp)
    }
    plan = this._genfare(plan);
    return this._genPlan(plan);
}
Calculator.prototype._monthPay = function(principal, period, mr){
    var v1 = Math.pow(mr+1, period), v2 = principal * v1 * mr, v3 = v1- 1, result = v2/v3;
    return result;
}
Calculator.prototype._genfare = function(plan){
    if(plan.mode == this.NORMAL){
        plan.fare = ((plan.period)>2?(plan.principal)*(0.04+((plan.period)-2)*0.002):(plan.principal)*0.04);
        plan.mfare = 0;
        plan.loan = plan.principal - plan.fare;
        plan.balance = round(plan.total)
    } else if(plan.mode == this.JY){
        var rate_dic = {12:0.0033, 18:0.00307, 24:0.00284, 36:0.00235,48:0.002},
            rate = rate_dic[plan.period];
        plan.mfare = round(rate * plan.principal);
        plan.fare = plan.mfare * plan.period;
        plan.loan = plan.principal
        plan.balance = round((plan.total + plan.fare ));
    }
    plan.total = round((plan.total + plan.fare ));
    plan.fare = round(plan.fare);
    return plan;
}
Calculator.prototype._genPlan = function(plan){
    var pay_unit = new Array(), p_balance = plan.principal, i_balance = plan.interest;

    for(var i=1;i<=plan.period;i++){
        var unit = {};
        unit.index = i;
        unit.fare = plan.mfare;
        if(i == plan.period){
            unit.total = round((plan.balance - (plan.month_pay + plan.mfare)*(plan.period-1)));
            unit.interest = round(i_balance);
            unit.principal = round(p_balance);
            unit.balance = 0;
        } else {
            unit.total = round((plan.month_pay + unit.fare));
            unit.interest = round(p_balance * plan.mrate);
            unit.principal = round((plan.month_pay - unit.interest));
            unit.balance = round((plan.balance - (unit.fare + plan.month_pay) * i));
            p_balance -= unit.principal;
            i_balance -= unit.interest;
        }
        pay_unit.push(unit);
    }
    plan.pay_unit = pay_unit;
    plan.month_pay = pay_unit[0].total;
    return plan;
}
function round(x){
    return Math.round(x*100)/100;
}
function amount(th){
    var regStrs = [
        ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
        ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
        ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
        ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
    ];
    for(i=0; i<regStrs.length; i++){
        var reg = new RegExp(regStrs[i][0]);
        th.value = th.value.replace(reg, regStrs[i][1]);
    }
}
