/**
 * Created by yaojian on 14-5-8.
 * 相关页面：借款流程-填写借款详情
 * 功能：显示隐藏选项
 */

$(function(){
	 
    $("#permanentAddr").dropCity();
    $("#nowAddress").dropCity();
    $("#workAddr").dropCity();
//   工作地址默认为空时 现居地址联动传值
    $("#nowAddress input").change(function(){
        var v1 = $("#workerCompanyProvince").val(),
            v2 = $("#workerCompanyCity").val(),
            v3 = $("#workerCompanyDistrict").val(),
            v4 = $("#workerDetailAddress").val();
        if(((v1=="")||(v1==undefined))||((v2=="")||(v2==undefined))||((v3=="")||(v3==undefined))||((v4=="")||(v4==undefined))){
            var addr11 = $("#homeProvince").val(),
                addr12 = $("#homeCity").val(),
                addr13 = $("#homeDistrict").val(),
                addr12Txt = $("#homeCity").closest(".m-drop").find(".m-dropInput").text(),
                addr13Txt = $("#homeDistrict").closest(".m-drop").find(".m-dropInput").text();
            $("#workerCompanyProvince").dropControl({'val': addr11});
            $("#workerCompanyCity").dropControl({'val': addr12});
            $("#workerCompanyCity").closest(".m-drop").find(".m-dropInput").text(addr12Txt);
            $("#workerCompanyDistrict").closest(".m-drop").find(".m-dropInput").text(addr13Txt);
            $("#workerCompanyDistrict").dropControl({'val': addr13});
        }
    })
    $("#homeDetailAddress").blur(function(){
        var v1 = $("#workerCompanyProvince").val(),
            v2 = $("#workerCompanyCity").val(),
            v3 = $("#workerCompanyDistrict").val(),
            v4 = $("#workerDetailAddress").val();
        if(((v1=="")||(v1==undefined))||((v2=="")||(v2==undefined))||((v3=="")||(v3==undefined))||((v4=="")||(v4==undefined))){
            var addr14 = $("#homeDetailAddress").val();
            if(addr14 != ''){
                $("#workerDetailAddress").val(addr14).closest(".m-holderBox").find("label").hide();
            }
        }
    })

//    地址默认传值
    var  detProvinceVal = $("#detProvince").val(),
         detCityVal = $("#detCity").val(),
         hukouDistrictVal = $("#hukouDistrict").val();
//    省市县都能获取到
    if((detProvinceVal!=undefined)&&(detCityVal!=undefined)&&(hukouDistrictVal!=undefined)){
    	var	$detCityTxt = $("#detCity").closest(".m-drop").find(".m-dropInput"),
				$hukouDistrictTxt = $("#hukouDistrict").closest(".m-drop").find(".m-dropInput"),
				$detCityList = $("#detCity").closest(".m-drop").find(".m-dropList"),
				$hukouDistrictList = $("#hukouDistrict").closest(".m-drop").find(".m-dropList");
				$("#detProvince").dropControl({'val': detProvinceVal});
				$.each(provinces,function(i,m){
					if(m.value == detProvinceVal){
						$detCityList.empty();
						$.each(m.subs,function(j,n){
							$detCityList.append("<li data='"+n.value+"'>"+n.name+"</li>");
							if(detCityVal == n.value){
								alert(detCityVal)
								$detCityTxt.text(n.name);
								$hukouDistrictList.empty();
								$.each(n.subs,function(k,l){
									$hukouDistrictList.append("<li data='"+l.value+"'>"+l.name+"</li>");
									if(hukouDistrictVal == l.value){
										$hukouDistrictTxt.text(l.name);
									}
								})
							}
						})
					}
				})
    	}
    
    var  homeProvinceVal = $("#homeProvince").val(),
    homeCityVal = $("#homeCity").val(),
    homeDistrictVal = $("#homeDistrict").val();
	if((homeProvinceVal!=undefined)&&(homeCityVal!=undefined)&&(homeDistrictVal!=undefined)){
	var	$homeCityTxt = $("#homeCity").closest(".m-drop").find(".m-dropInput"),
			$homeDistrictTxt = $("#homeDistrict").closest(".m-drop").find(".m-dropInput"),
			$homeCityList = $("#homeCity").closest(".m-drop").find(".m-dropList"),
			$homeDistrictList = $("#homeDistrict").closest(".m-drop").find(".m-dropList");
	
			$("#homeProvince").dropControl({'val': homeProvinceVal});
			$.each(provinces,function(i,m){
				if(m.value == homeProvinceVal){
					$homeCityList.empty();
					$.each(m.subs,function(j,n){
						$homeCityList.append("<li data='"+n.value+"'>"+n.name+"</li>");
						if(homeCityVal == n.value){
							$homeCityTxt.text(n.name)
							$homeDistrictList.empty();
							$.each(n.subs,function(k,l){
								$homeDistrictList.append("<li data='"+l.value+"'>"+l.name+"</li>");

								if(homeDistrictVal == l.value){
									$homeDistrictTxt.text(l.name);


								}
							})
						}
					})
				}
			})
	}
    
    var  workerCompanyProvinceVal = $("#workerCompanyProvince").val(),
    workerCompanyCityVal = $("#workerCompanyCity").val(),
    workerCompanyDistrictVal = $("#workerCompanyDistrict").val();
	if((workerCompanyProvinceVal!=undefined)&&(workerCompanyCityVal!=undefined)&&(workerCompanyDistrictVal!=undefined)){
	var	$workerCompanyCityTxt = $("#workerCompanyCity").closest(".m-drop").find(".m-dropInput"),
			$workerCompanyDistrictTxt = $("#workerCompanyDistrict").closest(".m-drop").find(".m-dropInput"),
			$workerCompanyCityList = $("#workerCompanyCity").closest(".m-drop").find(".m-dropList"),
			$workerCompanyDistrictList = $("#workerCompanyDistrict").closest(".m-drop").find(".m-dropList");
	
			$("#workerCompanyProvince").dropControl({'val': workerCompanyProvinceVal});
			$.each(provinces,function(i,m){
				if(m.value == workerCompanyProvinceVal){
					$workerCompanyCityList.empty();
					$.each(m.subs,function(j,n){
						$workerCompanyCityList.append("<li data='"+n.value+"'>"+n.name+"</li>");
						if(workerCompanyCityVal == n.value){
							$workerCompanyCityTxt.text(n.name);
							$workerCompanyDistrictList.empty();
							$.each(n.subs,function(k,l){
								$workerCompanyDistrictList.append("<li data='"+l.value+"'>"+l.name+"</li>");
								if(workerCompanyDistrictVal == l.value){
									$workerCompanyDistrictTxt.text(l.name);
								}
							})
						}
					})
				}
			})
	}
    
     $(".aaa").hide();
    $("#detJobdate").blur(function(){
    	
        var thisVal = $(this).val();
        if(thisVal != ''){
            $(".aaa").hide();
        }else{
            $(".aaa").show();
        }
    });
    $("#detJobdate").focus(function(){
        var thisVal = $(this).val();
        if(thisVal != ''){
            $(".aaa").hide();
        }else{
            $(".aaa").show();
        }
    });

//    复制户籍地址
    $(".copyAdd").click(function(){
        var $permanentAddr =  $("#permanentAddr");
        var addr11 = $("#detProvince").val(),
            addr12 = $("#detCity").val(),
            addr13 = $("#hukouDistrict").val(),
            addr14 = $("#hukouDetailAddress").val(),
            addr12Txt = $("#detCity").closest(".m-drop").find(".m-dropInput").text(),
            addr13Txt = $("#hukouDistrict").closest(".m-drop").find(".m-dropInput").text();
        $("#homeProvince").dropControl({'val': addr11});
        $("#homeCity").dropControl({'val': addr12});
        $("#homeCity").closest(".m-drop").find(".m-dropInput").text(addr12Txt);
        $("#homeDistrict").closest(".m-drop").find(".m-dropInput").text(addr13Txt);
        $("#homeDistrict").dropControl({'val': addr13});
        if(addr14 != ''){
            $("#homeDetailAddress").val(addr14).closest(".m-holderBox").find("label").hide();
        }

        //工作单位地址
        var addr31 = $("#workerCompanyProvince").val(),
            addr32 = $("#workerCompanyCity").val(),
            addr33 = $("#workerCompanyDistrict").val(),
            addr34 = $("#workerDetailAddress").val();
        if((addr31=='')&&(addr32=='')&&(addr33=='')&&(addr34=='')){
            $("#workerCompanyProvince").dropControl({'val': addr11});
            $("#workerCompanyCity").dropControl({'val': addr12});
            $("#workerCompanyCity").closest(".m-drop").find(".m-dropInput").text(addr12Txt);
            $("#workerCompanyDistrict").closest(".m-drop").find(".m-dropInput").text(addr13Txt);
            $("#workerDetailAddress").dropControl({'val': addr13});
            if(addr14 != ''){
                $("#workerDetailAddress").val(addr14).closest(".m-holderBox").find("label").hide();
            }
        }

    });
    
//    表单验证
    function btnFun(){
        return true;
        $("#btnSave").click(function(){
            return false;
        })
    }
    $("#borrowDetailForm").validate({
        rules:{
            detEducation:{
                required:btnFun()
            },
            detMarry:{
                required:btnFun()
            },
            detHouse:{
                required:btnFun()
            },
            workerCompanyType:{
                required:btnFun()
            },
            detCompanyname:{
                required:btnFun(),
                rangelength:[4,30],
                regABCh:true
            },
            workerCompanyTel:{
                required:btnFun(),
                checkWorkerCompanyTel:true
            },
            detJobdate:{
                required:btnFun()
            },
            department:{
                required:btnFun(),
                rangelength:[2,20],
                regABCh:true
            },
            position:{
                required:btnFun(),
                rangelength:[2,20],
                regABCh:true
            },
            homename:{
                required:btnFun(),
                rangelength:[2,20],
                regABCh:true
            },
            homerelation:{
                required:btnFun()
            },
            hometel:{
                required:btnFun(),
                rangelength:[10,18],
                regPhoneTel:true
            },
            jobname:{
                required:btnFun(),
                rangelength:[2,20],
                regABCh:true
            },
            jobrelation:{
                required:btnFun()
            },
            jobtel:{
                required:btnFun(),
                checkContactMobile:true,
                checkPhone:true
            },
            othername:{
                required:btnFun(),
                rangelength:[2,20],
                regABCh:true
            },
            otherrelation:{
                required:btnFun()
            },
            othertel:{
                required:btnFun(),
                checkContactMobile２:true,
                checkPhone:true
            },
            acceptMonthlyAmt:{
                required:btnFun(),
                digits:true,
                min:0,
                rangelength:[1,6]
            },
            detYearincome:{
            	required:btnFun(),
                digits:true,
                min:0,
                rangelength:[1,22],
                removeZero:true
            },
            limitCreditCard:{
            	required:btnFun(),
                digits:true,
                min:0,
                rangelength:[1,7],
                removeZero:true
            },
            hukouDetailAddress:{
                required:btnFun(),
                rangelength:[4,50],
                checkDetailAddress:true
            },
            homeDetailAddress:{
                required:btnFun(),
                rangelength:[4,50],
                checkDetailAddress:true
            },
            workerDetailAddress:{
                required:btnFun(),
                rangelength:[4,50],
                checkDetailAddress:true
            },
            detProvince:{
            	required:btnFun()
            },
            detCity:{
            	required:btnFun()
            },
            hukouDistrict:{
            	required:btnFun()
            },
            homeProvince:{
            	required:btnFun()
            },
            homeCity:{
            	required:btnFun()
            },
            homeDistrict:{
            	required:btnFun()
            },
            CompanyProvince:{
            	required:true
            	
            },
            CompanyCity:{
            	required:true
            },
           	CompanyDistrict:{
            	required:true
            }
        },
        messages:{
            detEducation:{
                required:"请选择最高学历"
            },
            detMarry:{
                required:"请选择婚姻状况"
            },
            detHouse:{
                required:"请选择房产状况"
            },
            detCompanynutare:{
                required:"请选择单位性质"
            },
            detCompanyname:{
                required:"请填写公司全称",
                rangelength:"公司名称长度为4～30个字母或者汉字",
                regABCh:"公司名称长度为4～30个字母或者汉字"
            },
            workerCompanyTel:{
                required:"请填写工作单位固话",
                checkWorkerCompanyTel:"请输入正确的座机电话，如010-12345678-9"
            },
            detJobdate:{
                required:"请填写入职时间"
            },
            department:{
                required:"请填写所在部门",
                rangelength:"部门长度为2～20个字母或者汉字",
                regABCh:"部门长度为2～20个字母或者汉字"
            },
            position:{
                required:"请填写您担任的职务",
                rangelength:"职位长度为2～20个字母或者汉字",
                regABCh:"职位长度为2～20个字母或者汉字"
            },
            homename:{
                required:"请填写家庭联系人信息",
                rangelength:"请正确填写家庭联系人信息",
                regABCh:"请正确填写家庭联系人信息"
            },
            homerelation:{
                required:"请填写家庭联系人信息"
            },
            hometel:{
                required:"请填写家庭联系人信息",
                rangelength:"请填写家庭联系人信息",
                regPhoneTel:"请正确填写家庭联系人信息"
            },
            jobname:{
                required:"请填写工作联系人信息",
                rangelength:"请正确填写工作联系人信息",
                regABCh:"请正确填写工作联系人信息"
            },
            jobrelation:{
                required:"请填写工作联系人信息"
            },
            jobtel:{
                required:"请填写工作联系人信息",
                checkContactMobile:"不能使用相同的手机号",
                checkPhone:"请正确填写工作联系人信息"
            },
            othername:{
                required:"请填写其他联系人信息",
                rangelength:"请正确填写其他联系人信息",
                regABCh:"请正确填写其他联系人信息"
            },
            otherrelation:{
                required:"请填写其他联系人信息"
            },
            othertel:{
                required:"请填写其他联系人信息",
                checkContactMobile２:"不能使用相同的手机号",
                checkPhone:"请正确填写其他联系人信息"
            },
            acceptMonthlyAmt:{
                required:"请填写可接受的月最高还款",
                digits:"可接受最高月还款长度为1～6个数字",
                min:"可接受最高月还款长度为1～6个数字",
                rangelength:"可接受最高月还款长度为1～6个数字"
            },
            detYearincome:{
            	required:"请填写年收入",
                digits:"可接受年收入长度为1～7个数字",
                min:"可接受年收入长度为1～7个数字",
                rangelength:"可接受年收入长度为1～7个数字",
                removeZero:"请填写有效数字"
            },
            limitCreditCard:{
            	required:"请填写信用卡最高额度",
                digits:"可接受信用卡最高额度长度为1～7个数字",
                min:"可接受信用卡最高额度长度为1～7个数字",
                rangelength:"可接受信用卡最高额度长度为1～7个数字",
                removeZero:"请填写有效数字"
            },
            hukouDetailAddress:{
                required:"请详细填写户籍地址",
                rangelength:"请用4-50个字书写地址信息",
                checkDetailAddress:"请输入有效描述文字"
            },
            homeDetailAddress:{
                required:"请详细填写户籍地址",
                rangelength:"请用4-50个字书写地址信息",
                checkDetailAddress:"请输入有效描述文字"
            },
            workerDetailAddress:{
                required:"请详细填写户籍地址",
                rangelength:"请用4-50个字书写地址信息",
                checkDetailAddress:"请输入有效描述文字"
            },
            detProvince:{
            	required:"请选择地址信息"
            },
            detCity:{
            	required:"请选择地址信息"
            },
            hukouDistrict:{
            	required:"请选择地址信息"
            },
            homeProvince:{
            	required:"请选择地址信息"
            },
            homeCity:{
            	required:"请选择地址信息"
            },
            homeDistrict:{
            	required:"请选择地址信息"
            },
            workerCompanyProvince:{
            	required:"请选择地址信息"
            },
            workerCompanyCity:{
            	required:"请选择地址信息"
            },
            workerCompanyDistrict:{
            	required:"请选择地址信息"
            }
        },
        errorPlacement:function(error,element) {
            element.closest(".m-row").next(".m-row-error").html(error);
        },
        focusCleanup:true
    });
    //去掉数字前面无用的0
    $.validator.addMethod("removeZero",function(value,element){
        value = parseInt(value);
        $(element).val(value);
        return true;
    });
    //详细地址信息校验
    $.validator.addMethod("checkDetailAddress",function(value,element){
        if(($.trim(value).length > 0)&&(/^[\u4e00-\u9fa50-9a-zA-Z-]+$/.test(value))){
            return true;
        }else{
            return false;
        }
    });
    //固话验证
    $.validator.addMethod("checkWorkerCompanyTel",function(value,element){
        if(checkHomeTel(value)){
            return true;
        }else{
            return false;
        }
    });
    //手机验证
    $.validator.addMethod("checkPhone",function(value,element){
        if(!checkPhone(value)){
            return true;
        }else{
            return false;
        }
    });
    //字母或汉字验证
    $.validator.addMethod("regABCh",function(value,element){
        var reg = /^[A-Za-z0-9\u4e00-\u9fa5]$/;
        if(!reg.test(value)){
            return true;
        }else{
            return false;
        }
    });
    //手机或固话
    $.validator.addMethod("regPhoneTel",function(value,element){
        if(!checkPhone(value)) {
            if(!checkHomeTel(value)){
                return true;
            }else{
                return  false;
            }
            return  false;
        }else{
            return true;
        }
    });
    //手机号不相等
    $.validator.addMethod("checkContactMobile",function(value,element){
        var phone1 = $("#hometel").val(),
            phone2 = $("#jobtel").val(),
            phone3 = $("#othertel").val();
        if((phone1 == phone2)||(phone2 == phone3)){
            return false;
        }else{
            return true;
        }
    });
    $.validator.addMethod("checkContactMobile２",function(value,element){
        var phone1 = $("#hometel").val(),
            phone2 = $("#jobtel").val(),
            phone3 = $("#othertel").val();
        if((phone2 == phone3)||(phone1 == phone3)){
            return false;
        }else{
            return true;
        }
    });

    //   提交按钮
    $("#borrowDetailForm").submit(function(){
        var errorLength = $(".hid1").find(".m-row-error .error").length;
        var errorLength2 = $(".hid2").find(".m-row-error .error").length;
        if(errorLength > 0){
            $(".hid1").find(".m-row-error .error").each(function(){
                var errorShow = $(this).css("display");
                if(errorShow == "inline"){
                    $(".hid1").show();
                }
            });
        }
        if(errorLength2 > 0){
            $(".hid2").find(".m-row-error .error").each(function(){
                var errorShow = $(this).css("display");
                if(errorShow == "inline"){
                    $(".hid2").show();
                }
            });
        }
    })

    //    保存按钮 ajax
    $("#btnSave").click(function(){
        var errorLength = 0;
        $(".m-row-error .error").each(function(){
            var errorShow = $(this).css("display")
            if(errorShow == "inline"){
                errorLength ++;
            }
        })
        if(errorLength == 0){
            $.ajax({
                //url:base_url+"/borrower/apply/detail/save",
                url:base_url+"/borrower/apply/save/detail",
                data: $("#borrowDetailForm").serialize(),
                type:'post',
                success:function(data){
                    alert(data.msg);
                    location.reload();
                }
            });
        }
    });

    // hid1: if has content show
    var hid1Input = 0;
    $(".hid1 input").each(function(){
        if($(this).val() != ''){
            hid1Input = 1;
            return false;
        }else{
            hid1Input = 0;
        }
    })
    if(hid1Input == 1){
        $(".hid1").show();
    }
 // hid2: if has content show
    var hid2Input = 0;
    $(".hid2 input").each(function(){
        if($(this).val() != ''){
            hid2Input = 1;
            return false;
        }else{
            hid2Input = 0;
        }
    })
    if(hid2Input == 1){
        $(".hid2").show();
    }
    
    
//  只能获取到省
    var ifVal = (detProvinceVal!=undefined)&&(detCityVal=="")&&(hukouDistrictVal=="");
    var $inputTxtBox = $("#permanentAddr .m-dropInput");
    var inputTxt0 = $inputTxtBox.eq(0).html();
    var inputTxt1 = $inputTxtBox.eq(1).html();
    var inputTxt2 = $inputTxtBox.eq(2).html();
//    alert(inputTxt0+","+inputTxt1+","+inputTxt2);
    var ifVal2 = ((inputTxt0!=undefined)&&((inputTxt1=="请选择")||(inputTxt2=="请选择")));
    if(ifVal||ifVal2){
    	$inputTxtBox.eq(1).closest(".m-drop").find(".m-dropHid").attr("value","");
    	$inputTxtBox.eq(2).closest(".m-drop").find(".m-dropHid").attr("value","");
        $("#detCity").change(function(){
            var drop2val = $(this).val();
            $.each(provinces,function(i,n){
                if(detProvinceVal == n.value){
                    $.each(n.subs,function(j,m){
                        if(drop2val == m.value){
                            var $ul3 = $("#detCounty").closest(".m-drop").find(".m-dropList");
                            $ul3.empty();
                            $.each(m.subs,function(k,l){
                                if(k==0){
                                    $("#detCounty").dropControl({'val': l.value});
                                    $("#detCounty").closest(".m-drop").find(".m-dropInput").html(l.name);
                                }
                                $ul3.append("<li data='"+l.value+"'>"+l.name+"</li>");
                            });
                        }
                    })
                }
            })
        })
    }
}) ;
function checkHomeTel(tel){
    var reg =/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})((-(\d{1,}))?)$/;
    if((reg.exec(tel))){
        return true;
    }else{
        return false;
    }
}
function checkPhone(value){
    var reg = /^1[3|4|5|7|8][0-9]{9}$/;
    if(reg.test(value)){
        return false;
    }else{
        return true;
    }
}