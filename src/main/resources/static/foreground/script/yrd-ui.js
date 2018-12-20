;(function($){
    $.fn.extend({
        f2eUItab:function(){
            return this.each(function(){
                var $this = $(this),
                    $tabT = $this.children('.m-tabT'),
                    $tabTA = $tabT.children('a'),
                    $tabC = $this.children('.m-tabC');

                $tabTA.click(function(){
                    var tabIndex = $(this).index();
                    $tabTA.removeClass('m-tabTActive');
                    $(this).addClass('m-tabTActive');
                    $tabC.hide();
                    $tabC.eq(tabIndex).show();
                });
            });
        },
        f2eUIholder:function(options){
            return this.each(function(){
                var $this = $(this),
                    $input = $this.find(".m-holderInput"),
                    inputH = $input.height(),
                    $holder = $this.find(".m-holderTxt").height(inputH).css('lineHeight',inputH+'px');
//                $input.val('');
               // $this.width($input.width()).click(toNull);
               // $holder.click(toNull);
               // $input.click(toNull);
               /* $this.focusin(function(){
                    $holder.hide();
                });
                $this.focusout(function(){
                    var ifInput = $input.val();
                    if(ifInput == ''){
                        $holder.show();
                    }
                });*/
                if($input.val()==""){
                	$holder.show();
                }else{
                	$holder.hide();
                }
                $input.click(toNull);
                $holder.click(toNull);
                $input.focusin(function(){
                    $holder.hide();
                });
                $input.focusout(function(){
                    var ifInput = $input.val();
                    if(ifInput == ''){
                        $holder.show();
                    }
                });
                function toNull(){
                    var ifInput = $input.val();
                    if(!ifInput){
                        $holder.hide();
                        $input.focus();
                    }
                }
            });
        },
        f2eUIdrop:function(options){
            return this.each(function(){
                var $this = $(this),
                    $hid = $this.find(".m-dropHid"),
                    $list = $this.find(".m-dropList"),
                    $li = $this.find(".m-dropList li"),
                    liH = $li.height(),
                    liNum = $li.length,
                    holder = $hid.attr('data'),
                    thisZIndex = $this.css('zIndex');
                $list.width($this.width());
                $this.prepend('<div class="m-dropInput">'+holder+'</div><a class="m-dropIcon" href="javascript:void(0);"></a>');
                var $icon = $this.find(".m-dropIcon"),
                    $input = $this.find(".m-dropInput").width($this.width()-$icon.width()-5);
                var $selected = $list.find('.selected'),
                    selectedVal = $selected.attr('data'),
                    selectedTxt = $selected.html();
                if(selectedVal != undefined){
                    $input.html(selectedTxt);
                }else{
                    var hidValDefault = $hid.val();
                    $li.each(function(){
                        var thisData = $(this).attr('data');
                        if(hidValDefault == thisData){
                            $(this).addClass('selected')
                        }
                    });
                }

                //
                var dropH = $this.height();
                $list.css('top',dropH+'px');
                $input.height(dropH-2).css('lineHeight',(dropH-2)+'px');
                $icon.height(dropH).width(dropH-1);

                // 修改input的value 对应显示选项
                var optionTxt = $list.find("li[data='"+$hid.val()+"']").html();
                $input.html(optionTxt);

                // list li > 4 高度固定为4行
                if(liNum > 4){var listH = (liH+1)*4;} else {var listH = (liH+1)*liNum;}
                $list.height(listH);
                // 鼠标滑动时，list 距底边高度小于 listH ，list朝上显示
                $(window).scroll(function(){
                    var dropSet = $this.offset(),
                        dropTop = dropSet.top,
                        winHeight = $(window).height(),
                        scrollHeight = $(document).scrollTop(),
                        btmHeight = winHeight - (dropTop - scrollHeight) - dropH;
                    if(btmHeight < listH){
                        $list.css('top','-'+listH+'px');
                    } else {
                        $list.css('top',dropH+'px');
                    }
                });

                var zIndex = $this.index('.m-drop');

                $this.find('.m-dropInput,.m-dropIcon').click(function(){
                    $li = $this.find(".m-dropList li");
                    liH = $li.height();
                    liNum = $li.length;
                    // list li > 4 高度固定为4行
                    if(liNum > 4){var listH = (liH+1)*4;} else {var listH = (liH+1)*liNum;}
                    $list.height(listH);
                    if(!($(this).closest(".m-drop").hasClass("m-dropUn"))){
                        $list.show();
                        $icon.addClass('on');
                        $this.css('z-index',1000);
                    }else{
                        $this.css('z-index',thisZIndex);
                    }

                    //
                    $li.hover(
                        function(){$(this).addClass('active');},
                        function(){$(this).removeClass('active');}
                    );

                    $li.click(function(){
                        var liTxt = $(this).text(),
                            liData = $(this).attr('data');
                        $input.text(liTxt).css('color','#000');
                        $hid.val(liData).change();
                        $list.hide();
                        $icon.removeClass('on');
                        $li.removeClass('selected');
                        $(this).addClass('selected');
                    });

                });




                $('body').click(function(e){
                    var target = $(e.target);
                    var x = target.closest($this).length;
                    if(x==0){
                        $list.hide();
                        $icon.removeClass('on');
                        $this.css('z-index',thisZIndex);
                    }
                });

            });
        },
        dropControl:function(options){
            var defaults = {
                disable: 0
            };
            var opts = $.extend(defaults, options);

            var $drop = $(this).closest('.m-drop'),
                $list = $drop.find('.m-dropList'),
                $li = $list.find('li'),
                $input = $drop.find('.m-dropInput');
            if(opts.disable == 1){
                $drop.addClass('m-dropUn');
            }else{
                $drop.removeClass('m-dropUn');
            }

            if(opts.val != undefined){
                $(this).val(opts.val);
                var $li = $list.find("li[data='"+opts.val+"']");
                $li.addClass("selected");
                var optionTxt = $li.html();
                $input.html(optionTxt);
            }
        },
        dropCity:function(){
            var $this = $(this),
                $input = $this.find(".m-dropHid").eq(0),
                $ul = $input.closest(".m-drop").find(".m-dropList"),
                $input2 = $this.find(".m-dropHid").eq(1),
                $ul2 = $input2.closest(".m-drop").find(".m-dropList"),
                $input3 = $this.find(".m-dropHid").eq(2),
                $ul3 = $input3.closest(".m-drop").find(".m-dropList");

            if(typeof(provinces)!= "undefined"){
                $.each(provinces,function(i,n){
                    $ul.append("<li data='"+n.value+"'>"+n.name+"</li>");
                });
            }


            $input.change(function(){
                var drop1val = $(this).val();
                $.each(provinces,function(i,n){
                    if(drop1val == n.value){
                        $ul2.empty();
                        $.each(n.subs,function(j,m){
                            if(j==0){
                                $input2.dropControl({'val': m.value});
                                $input2.closest(".m-drop").find(".m-dropInput").html(m.name);
                            }
                            $ul2.append("<li data='"+m.value+"'>"+m.name+"</li>");
                            var drop2val =$input2.val();
                            $.each(n.subs,function(j,m){
                                if(drop2val == m.value){
                                    $ul3.empty();
                                    $.each(m.subs,function(k,l){
                                        if(k==0){
                                            $input3.dropControl({'val': l.value});
                                            $input3.closest(".m-drop").find(".m-dropInput").html(l.name);
                                        }
                                        $ul3.append("<li data='"+l.value+"'>"+l.name+"</li>");
                                    });
                                }
                            });
                        });

                        $input2.change(function(){
                            var drop2val = $(this).val();
                            $.each(n.subs,function(j,m){
                                if(drop2val == m.value){
                                    $ul3.empty();
                                    $.each(m.subs,function(k,l){
                                        if(k==0){
                                            $input3.dropControl({'val': l.value});
                                            $input3.closest(".m-drop").find(".m-dropInput").html(l.name);
                                        }
                                        $ul3.append("<li data='"+l.value+"'>"+l.name+"</li>");
                                    });
                                }
                            });
                        });
                    }
                });
            });
        },
        f2eUItextfocus:function(options){
            return this.each(function(){
                var $this = $(this);
                $this.focus(function(){
                    $this.addClass('m-textFocusActive');
                });
                $this.focusout(function(){
                    $this.removeClass('m-textFocusActive');
                })
            })
        },
        f2eUIcheckbox:function(options){
            return this.each(function(){
                var $this = $(this),
                    $inputHid = $this.children('.m-checkboxHid'),
                    $label = $this.closest('label'),
                    $labelTxt = $label.find('span'),
                    labelFor = $label.attr('for'),
                    thisId = $inputHid.attr('id');

                function checkfun(){
                    if($this.hasClass('m-checkboxChecked')){
                        $this.removeClass('m-checkboxChecked');
                        $inputHid.attr('data',0);
                    } else{
                        $this.addClass('m-checkboxChecked');
                        $inputHid.attr('data',1);
                    }
                }
                // 对不同浏览器兼容
                if(($.browser.webkit)||(($.browser.msie)&&($.browser.version=='6.0'))||(($.browser.msie)&&($.browser.version=='7.0'))||(($.browser.msie)&&($.browser.version=='8.0'))){
                    $labelTxt.click(function(){
                        if((labelFor!=undefined)&&(thisId!=undefined)&&(labelFor==thisId)){
                            checkfun()
                        }
                    })
                }
                // if($.browser.msie){
                // 	alert($.browser.version)
                // }
                $this.click(function(){
                    checkfun()
                })

            })
        },
        f2eUIcheckbox2:function(options){
            return this.each(function(){
                var $this = $(this),
                    $inputHid = $this.children('.m-checkboxHid'),
                    $label = $this.closest('label'),
                    $labelTxt = $label.find('span'),
                    labelFor = $label.attr('for'),
                    thisId = $inputHid.attr('id');

                function checkfun(){
                    if($this.hasClass('m-checkboxChecked')){
                        $this.removeClass('m-checkboxChecked');
                        $inputHid.removeAttr('checked');
                    } else{
                        $this.addClass('m-checkboxChecked');
                        $inputHid.attr('checked', 'checked');
                    }
                }
                // 对不同浏览器兼容
                if(($.browser.webkit)||(($.browser.msie)&&($.browser.version=='6.0'))||(($.browser.msie)&&($.browser.version=='7.0'))||(($.browser.msie)&&($.browser.version=='8.0'))){
                    $labelTxt.click(function(){
                        if((labelFor!=undefined)&&(thisId!=undefined)&&(labelFor==thisId)){
                            checkfun();
                        }
                    })
                }
                // if($.browser.msie){
                // 	alert($.browser.version)
                // }
                $this.click(function(){
                    checkfun();
                });

            });
        },
        checkboxControl:function(options){
            // var defaults = {};
            // var opts = $.extend(defaults, options);
            if((options.name!=undefined)&&(options.val==undefined)){
                var str='';
                $("input[name='"+options.name+"'][data=-1]").each(function(){
                    str += $(this).val()+',';
                });
                $("input[name='"+options.name+"'][data=1]").each(function(){
                    str += $(this).val()+',';
                });
                return str;
            }

            if((options.name!=undefined)&&(options.val!=undefined)){
                var myStr = options.val,
                    myArr = myStr.split(','),
                    arrlength = myArr.length,
                    $checkbox = $("input[name='"+options.name+"']");
                $.each(myArr,function(key,val){
                    for(var i=0;i<arrlength;i++){
                        if(val == $checkbox.eq(i).val()){
                            $checkbox.eq(i).attr('data',1);
                            $checkbox.eq(i).closest('.m-checkbox').addClass('m-checkboxChecked');
                        }
                    }
                });
            }

            if((options.id!=undefined)&&(options.disable!=undefined)){
                var $hidInput = $("#"+options.id),
                    $checkbox = $hidInput.closest(".m-checkbox");
                if(options.disable){
                    $checkbox.addClass('m-checkboxDisabled');
                    $hidInput.attr({
                        data: -1
                    });
                }else{
                    $checkbox.removeClass('m-checkboxDisabled');
                    $hidInput.attr({
                        data: 0
                    });
                }

            }
        },
        f2eUIradio:function(options){
        	return this.each(function(){
                var $this = $(this),
                    $inputHid = $this.find('.m-radioHid'),
                    aName = $this.attr('name'),
                    $aName = $("a[name='"+aName+"']"),
                    thisId = $inputHid.attr('id'),
                    label = $inputHid.parents('label'),
                    labelFor = label.attr('for');
                if((thisId == labelFor)&&(labelFor!=undefined)&&(thisId!=undefined)){
                	label.click(function(){
                		$aName.each(function(){
                			if($(this).hasClass('m-radioChecked')){
                				$(this).removeClass('m-radioChecked')
                				$(this).find('.m-radioHid').val(0);
                			}
                		});
                        $(this).find('a.m-radio').addClass('m-radioChecked').find('.m-radioHid').val(1); 
                    });
                }
            });
        },
        f2eUIradio2:function(options){
            return this.each(function(){
                var $this = $(this),
                    $inputHid = $this.children('.m-radioHid'),
                    aName = $this.attr('name'),
                    $aName = $("a[name='"+aName+"']"),
                    thisId = $inputHid.attr('id'),
                    labelFor = $inputHid.closest('label').attr('for');
                if((thisId == labelFor)&&(labelFor!=undefined)&&(thisId!=undefined)){
                    $inputHid.closest('label').click(function(){
                        $this.trigger("click");
                    });
                }
                $this.click(function(){
                    $aName
                        .removeClass('m-radioChecked')
                        .children('input').attr("checked", false);
                    var i = $(this).index("a[name='"+aName+"']");
                    $aName.eq(i).addClass('m-radioChecked');
                    $(this).children('input').attr("checked", true);

                });
            });
        },
        //仿TITLE功能
        f2eUItooltips:function(str){
            return this.each(function(){
                var $this = $(this),$parentEleObj = $this.parent();
                var $tooltips = $('<p class="tooltips"></p>');
                var $tooltipsArrow = $('<p class="tooltips-arrow png"></p>');
                var $tooltipsDiv=$('<p></p>');
                $tooltipsDiv.addClass('tips tips-warning').css("width","245px");
                $tooltipsDiv.css('position','relative');
                $tooltipsDiv.text(str);
                $tooltipsDiv.append($tooltipsArrow);
                $tooltips.append($tooltipsDiv);
                $parentEleObj.css('position','relative');
                $parentEleObj.append($tooltips);
                var h = $tooltips.height();
                var offsetLeft = this.offsetLeft;
                var offsetTop = this.offsetTop;
                $tooltips.css({
                    'position':'absolute',
                    'display':'none',
                    'z-index':'9999',
                    'left':'0',
                    'top':-h-7+offsetTop+'px'
                });
                $tooltipsArrow.css('left',offsetLeft);
                $this.on('mouseover',function(){
                    $this.siblings('.tooltips').show()
                }).on('mouseout',function(){
                        $this.siblings('.tooltips').hide()
                    });
            });
        }
    })
    //下面是隐藏和显示表格
    $(".flex_table .flex_tr").click(function(){
        if($(this).find(".flex_a").hasClass("flex_plus")&&$(this).next().hasClass("detail_tr")){
            $(this).find(".flex_a").removeClass("flex_plus");
            $(this).find(".flex_a").addClass("flex_minus");
            $(this).next().show();
        }else if($(this).find(".flex_a").hasClass("flex_minus")&&$(this).next().hasClass("detail_tr")){
            $(this).find(".flex_a").removeClass("flex_minus");
            $(this).find(".flex_a").addClass("flex_plus");
            $(this).next().hide();
        }
    });	
})(jQuery);


$(function(){
    // tab
    $(".m-tab,.m-tab2").f2eUItab();
    // holder

    $(".m-holderBox").f2eUIholder();
    // drop
    $(".m-drop").f2eUIdrop();

    if($(".m-dropHid").length>0){
        $(".m-dropHid").each(function(){
            var $dropInput = $(this).closest(".m-drop").find(".m-dropInput");
            if(($(this).val()=='')||($(this).val()==undefined)){
                $dropInput.css("color","#aaa");
            }else{
                $dropInput.css("color","#000");
            }
        })
    }

    // textFocus
    $(".m-textFocus").f2eUItextfocus();
    // checkbox
    $(".m-checkbox").f2eUIcheckbox();
    $(".m-checkbox2").f2eUIcheckbox2();
    // radio
    $(".m-radio").f2eUIradio();
    $(".m-radio2").f2eUIradio2();

//顶部
    $(".navMobile").hover(
        function(){
            $(".navMobileBox").show();
        },
        function(){
            $(".navMobileBox").hide();
        }
    );
    $(".navMobileBox").hover(
        function(){
            $(this).show();
        },
        function(){
            $(this).hide();
        }
    );


});
