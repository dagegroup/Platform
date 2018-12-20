/**
 * Created by yaojian on 14-5-7. 相关页面：借款流程-填写借款信息 功能：页面验证 & 提示信息交互
 */
var form = $("#borrowInfoForm");
$(function() {

	$("#fysm_dialog_btn").click(function() {
		art.dialog({
			title : '费用说明',
			content : '<div class="fysm_dialog_cont"><h4>1、借款成本的费用组成如下：</h4><p><strong>利息：</strong>借款人需支付的借款利息<br /><strong>平台服务费：</strong>即平台向借款人收取的服务总费用，其中包含前期服务费、分期服务费两部分（信用资质良好的用户可免收分期服务费）。</p><h4>2、平台服务费的高低取决于借款人的信用资质：</h4><p>·优质用户可享受相对较低的平台服务费且在放款之前一次性扣除<br />·普通用户则需要缴纳相对较高的平台服务费，该费用在放款前会扣除一部分，其余部分需要在还款过程中按月缴纳。</p><h4>3、其他名词解释：</h4><p><strong>申请金额：</strong>借款人最终实际拿到的金额<br /><strong>合同金额：</strong>申请金额与前期服务费的总和</p></div>',
			resize : false,// 可拉伸弹出框开关
			fixed : true,
			lock : true,// 锁屏
			opacity : .7,// 锁屏背景透明度
			background : '#000',// 锁屏背景颜色
			drag : false,// 拖动开关
			width : 550,
			height : 150,
			okVal : "我知道了",
			ok : function() {
				art.dialog.close();
			}
		});
	});
	// 优惠码 显示隐藏
	$(".myCodeA").click(function() {
		var $this = $(this), $myCode = $this.closest('.myCode'), $myCodeCon = $myCode
				.find('.myCodeCon'), $icon = $this.find('i'), ifClick = $icon
				.hasClass('myCodeShow');
		if (!ifClick) {
			$icon.addClass('myCodeShow');
			$myCodeCon.show();
		} else {
			$icon.removeClass('myCodeShow');
			$myCodeCon.hide();
		}
	})

	// form jquery validate 表单验证
	function btnFun() {
		return true;
		$("#btnSave").click(function() {
					return false;
				});
	}
	// 优惠码校验
	$("#detect").click(function() {

		var $codeInput = $("#discountCode"), $error = $(this).closest(".m-row")
				.next(".m-row-error"), code = $.trim($codeInput.val());
		if (code === '') {
			$error.html("请输入优惠码再检测，如无可不填");
		} else if (!/^\d{7,12}$/.test(code)) {
			$error.html("优惠码格式错误");
		} else {
			$.get(base_url + "/borrower/apply/basic/checkcode", {
						discountCode : code
					}, function(data) {
						if (data.status == 'true') {
							$error.html('检测成功，该优惠码可用');
						} else if (data.status == 'false') {
							$codeInput.val('');
							$error.html('您输入的优惠码不存在或已失效');
						}
					})
		}
	});
	// 下一步，表单验证
	var maxLoanAmt = $("#maxLoanAmt").val();
	var minLoanAmt = $("#minLoanAmt").val();
	$("#borrowInfoForm").validate({
				rules : {
					borUse : {
						required : function() {
							btnFun();
						}
					},
					borNum : {
						required : function() {
							btnFun();
						},
						digits : true,
						range : [minLoanAmt, maxLoanAmt],
						checkAmt : true
					},
					borDeadline : {
						required : function() {
							btnFun();
						}
					},
					borDes : {
						required : function() {
							btnFun();
						},
						rangelength : [20, 1000]
					},
					borBigmoney : {
						required : function() {
							btnFun();
						},
						digits : true,
						range : [500, 500000],
						maxlength : 6,
						removeZero : true

					},
					borrowerQQ : {
						required : function() {
							btnFun();
						},
						digits : true,
						rangelength : [4, 13],
						checkQQ : true
					},
					borrowerMobile : {
						required : function() {
							btnFun();
						},
						digits : true,
						maxlength : 11,
						checkMobile : true
					}
				},
				messages : {
					borUse : {
						required : "请选择借款用途"
					},
					borNum : {
						required : "请输入申请金额",
						digits : "这里只能输入正整数",
						range : "申请金额应在" + minLoanAmt + "-" + maxLoanAmt
								+ "元之间",
						maxlength : "请输入申请金额",
						checkAmt : "请输入100的倍数"
					},
					borDeadline : {
						required : "请选择借款期限"
					},
					borDes : {
						required : "请描述借款用途",
						rangelength : "借款描述长度为20～1000个字符"
					},
					borBigmoney : {
						required : "请填写月最高还款额",
						digits : "请输入正确的格式",
						range : "请输入500-500000的正整数",
						maxlength : "您输入的金额过大",
						removeZero : "请输入合法的QQ号码1"
					},
					borrowerQQ : {
						required : "请填写QQ号码",
						digits : "请输入合法的QQ号码",
						rangelength : "请输入合法的QQ号码",
						checkQQ : "请输入合法的QQ号码"
					},
					borrowerMobile : {
						required : "请填写手机号码",
						digits : "请输入合法的手机号码",
						maxlength : "手机号码长度为11个数字",
						checkMobile : "请输入合法的手机号码"
					}
				},
				errorPlacement : function(error, element) {
					element.closest(".m-row").next(".m-row-error").html(error);
				}
			})

	// form 表单其他自定义 交互

	// 去掉数字前面无用的0
	$.validator.addMethod("removeZero", function(value, element) {
				value = parseInt(value);
				$(element).val(value);
				return true;
			});
	// 验证申请金额
	$.validator.addMethod("checkAmt", function(value, element) {
				if ((value % 100 != 0) || (value == 0)) {
					$("#borNum").val('')
					return false;
				} else {
					return true;
				}
			});
	// 验证qq
	$.validator.addMethod("checkQQ", function(value, element) {
				if (($.trim(value).length > 0) && (/^[0-9]{4,14}$/.test(value))) {
					return true;
				} else {
					return false;
				}
			});
	// 验证手机号
	$.validator.addMethod("checkMobile", function(value, element) {
				if (($.trim(value).length > 0)
						&& (/^1[3|4|5|7|8][0-9]{9}$/.test(value))) {
					return true;
				} else {
					return false;
				}
			})
	// 增加判断借款用途为买房、投资时，借款详情提示文字有增加：注：此描述在借款页面不会显示。
	$("#borUse").change(function() {
				var $this = $(this), thisVal = $this.val(),

				text = "可以输入20-1000个文字，您可以描述一下您的借款原因等等";
				if ((thisVal == 1) || (thisVal == 11)) {
					text += "，注：此描述在借款页面不会显示。";
				}
				$("#borDes").attr('placeholder', text);
			})

	//
	$("#borNum").blur(function() {
		var borNumVal = $(this).val();
		if (borNumVal == '') {
			var $m = $("#borDeadline").closest(".m-drop");
			$m.find(".m-dropHid").val('');
			$m.find(".m-dropInput").text("请选择");
		}
		$.ajax({
			url : base_url + "/borrower/apply/basic/getperiod?borNum="
					+ borNumVal + "&borrowRate=10",
			type : "get",
			success : function(data) {
				var $list = $("#borDeadline").siblings(".m-dropList").html('');
				$.each(data, function(index, value) {
							var dataVal = value.substring(0, 2);
							$list.append('<li class=\" \" data=\"' + dataVal
									+ '\">' + value + '</li>')
						});
				var $li = $("#borDeadlineList").find("li"), $input = $("#borDeadlineList")
						.siblings("div.m-dropInput"), $hid = $("#borDeadline"), $icon = $("#borDeadlineList")
						.siblings("div.m-dropIcon");
				if ($li.length != 0) {
					$li.hover(function() {
								$(this).addClass('active');
							}, function() {
								$(this).removeClass('active');
							});

					$li.click(function() {
								var liTxt = $(this).text(), liData = $(this)
										.attr('data');
								$input.text(liTxt).css('color', '#000');
								$hid.val(liData).change();
								$list.hide();
								$icon.removeClass('on');
								$li.removeClass('selected');
								$(this).addClass('selected');
							});
				}
				$(".m-dropList").css("height", "auto");
				// placeholder
				$("#borNum").placeholder();
			},
			error : function(data) {

			}
		});
	});
	// placeholder
	
	$("#borNum").placeholder();
	$("#borDes").placeholder()

	$("#borDeadline").closest(".m-drop").click(function() {
		var borNumVal = $("#borNum").val();
		if (borNumVal == '') {
			alert('您未填写申请金额');
		}
			// $(this).find(".m-dropHid").val('');
			// $(this).find(".m-dropInput").text("请选择");
		})

	// 保存按钮 ajax
	$("#btnSave").click(function() {
				var errorLength = 0;
				$(".m-row-error .error").each(function() {
							var errorShow = $(this).css("display")
							if (errorShow == "inline") {
								errorLength++;
							}
						})
				if (errorLength == 0) {
					$.ajax({
								url : base_url + "/borrower/apply/save/basic",
								data : $("#borrowInfoForm").serialize(),
								type : 'post',
								success : function(data) {
									alert(data.msg);
									location.reload();
								}
							});
				}
			});
	// 计算 ajax
	$("#borrowPeriodList").click(function() {
				var val1 = $("#borNum").val();
				var val2 = $("#borDeadline").val();
				alert(val2)
				$("#totalFee").empty();
				$("#monthlyRePayAmt").empty();
				$("#payallMoney").empty();
				if ((val1 == '') || (val2 == '')) {
					$("#counterNote").hide();
				} else {
					$.ajax({
								url : base_url
										+ '/borrower/apply/basic/monthrepay',
								type : 'post',
								data : {
									'borNum' : val1,
									'borDeadline' : val2,
									'borrowRate' : 10
								},
								success : function(json) {
									$("#totalFee").append(json.totalFee);
									$("#monthlyRePayAmt")
											.append(json.payMonthMoney);
									$("#payallMoney").append(json.payallMoney);
									$("#counterNote").show();
								}
							});
				}
			});
});

function createRepayment(obj) {
	$("#allRePayAmt").empty();
	$("#monthlyRePayAmt").empty();
	var borrowAmt = $("#borrowAmt").val();
	var borrowPeriod = $("#borrowPeriod").val();
	alert(borrowPeriod)
	if (borrowAmt == null || borrowAmt == '') {
		alert('您未填写申请金额');
		return false;
	}
	if (isNaN(borrowAmt) == true) {
		return false;
	}
	var options = {
		url : base_url + '/borrower/apply/basic/monthrepay',
		type : 'post',
		success : function(data) {
			try {
				var json = $.parseJSON(data);
				if (json.status == 'success') {
					$("#monthlyRePayAmt").append(json.payMonthMoney);
					$("#allRePayAmt").append(json.payallMoney);
				}
			} catch (e) {
				alert("数据有误,请稍后重试");
				window.location.reload();
			}
		},
		error : function(xhr) {
			alert('失败：xhr.status=' + xhr.status + ', responseText='
					+ xhr.responseText);
		}
	};
	$("#form").ajaxSubmit(options);
}
