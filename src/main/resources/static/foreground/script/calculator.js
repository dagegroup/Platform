function Calculator(mode){
    if(mode ==='jy' || mode === '129' || mode === '189'){
        this.mode = mode;
    } else {
        this.mode = 'normal';
    }
}
Calculator.prototype.M = 3;
Calculator.prototype.NORMAL = 'normal';
Calculator.prototype.JY = 'jy';
Calculator.prototype.JY_129 = '129'; //129
Calculator.prototype.JY_189 = '189'; //189
Calculator.prototype.INTEREST_RATE_DICT = {12: 0.1, 18: 0.105, 24: 0.11, 36: 0.12, 48: 0.125}; //费率

//-------线上费率-------
Calculator.prototype.M_FEE_RATE_129_DICT = {12:0.00062, 18:0.00269, 24:0.00359, 36:0.00421, 48:0.00446}; //129平台费率
Calculator.prototype.M_FEE_RATE_189_DICT = {12:0.008, 18:0.009, 24:0.009, 36:0.009, 48:0.0095}; //189平台费率

Calculator.prototype.PRE_FEE_RATE_78_DICT = {12:0.0353, 18:0.0484, 24:0.0578, 36:0.0664, 48:0.0718}; //78前期服务费率
Calculator.prototype.PRE_FEE_RATE_129_DICT = {12:0.08, 18:0.08, 24:0.08, 36:0.08, 48:0.08};//129前期服务费率
Calculator.prototype.PRE_FEE_RATE_189_DICT = {12:0.06, 18:0.07, 24:0.08, 36:0.09, 48:0.09}; //189前期服务费率

Calculator.prototype.ADVANCE_REPAYMENT_RATE_78_DICT = {12:0.030, 18:0.035, 24:0.035, 36:0.035, 48:0.035}; //78保底费率
Calculator.prototype.ADVANCE_REPAYMENT_RATE_129_DICT = {12:0.045, 18:0.045, 24:0.040, 36:0.040, 48:0.040}; //129保底费率
Calculator.prototype.ADVANCE_REPAYMENT_RATE_189_DICT = {12:0.06, 18:0.07, 24:0.08, 36:0.09, 48:0.09}; //189保底费率

//-------线下费率-------
Calculator.prototype.M_FEE_RATE_129_LINE_DICT = {12:0, 18:0, 24:0, 36:0, 48:0}; //129平台费率
Calculator.prototype.M_FEE_RATE_189_LINE_DICT = {12:0, 18:0, 24:0, 36:0, 48:0}; //189平台费率

Calculator.prototype.PRE_FEE_RATE_78_LINE_DICT = {12:0.0353, 18:0.0484, 24:0.0578, 36:0.0664, 48:0.0718}; //78前期服务费率
Calculator.prototype.PRE_FEE_RATE_129_LINE_DICT = {12:0.08, 18:0.08, 24:0.08, 36:0.08, 48:0.08};//129前期服务费率
Calculator.prototype.PRE_FEE_RATE_189_LINE_DICT = {12:0.1380, 18:0.1815, 24:0.2235, 36:0.2895, 48:0.3405}; //189前期服务费率

Calculator.prototype.ADVANCE_REPAYMENT_RATE_78_LINE_DICT = {12:0.030, 18:0.035, 24:0.035, 36:0.035, 48:0.035}; //78保底费率
Calculator.prototype.ADVANCE_REPAYMENT_RATE_129_LINE_DICT = {12:0.045, 18:0.045, 24:0.040, 36:0.040, 48:0.040}; //129保底费率
Calculator.prototype.ADVANCE_REPAYMENT_RATE_189_LINE_DICT = {12:0.060, 18:0.055, 24:0.050, 36:0.045, 48:0.040}; //189保底费率
//flag标记线上还是线下 ：online线上 line线下
Calculator.prototype.calculate = function(principal, period, rate,flag){
	
    if(!/^\d+$/.test(principal)){
        throw "principal value error!";
    }
    if((!/^\d+$/.test(period))||period<1||period>48){
        throw "period value error!"
    }
    if(rate == null || rate == ''){
    	rate = Calculator.prototype.INTEREST_RATE_DICT[period];
    }
    if(rate>0.2||rate<0.1){
        throw "interest rate value error!"
    }
    var preFeeRate = this._getPreFeeRate(period,flag) //前期服务费率
        , contractAmount = give(principal/(1-preFeeRate)) //合同金额
        , mr = rate/12 //月利率
        , mp = this._monthPay(contractAmount, period, mr) //月还
        , total = mp * period //还款总额(本金+利息)
        , interest = total - contractAmount //总利息
        , preFee = parseInt(contractAmount) - parseInt(principal);
    var plan = {
        principal : principal,//申请金额
        period :period,//借款期限
        rate : rate,//年利率
        mrate: mr,//月利率
        total: total,//还款总额(本金+利息)
        interest :round(interest),//总利息
        mode : this.mode,
        month_pay : mp,//月还(本金+利息)
        contractAmount : contractAmount,//合同金额
        preFee : preFee,//前期服务费
        preFeeRate : preFeeRate,//前期服务费率
    };
    plan = this._genfare(plan,flag);
    return this._genPlan(plan);
};
Calculator.prototype._monthPay = function(principal, period, mr){
    var v1 = Math.pow(mr+1, period), v2 = principal * v1 * mr, v3 = v1- 1, result = v2/v3;
    return result;
};
Calculator.prototype._genfare = function(plan,flag){
    switch(plan.mode){
        case this.JY:
        	//078都是趸交
        	plan.platformRate = 0;//平台费率
        	if(flag == 'online'){
        		 plan.advanceRepayRate = Calculator.prototype.ADVANCE_REPAYMENT_RATE_78_DICT[plan.period];
             } else if(flag == 'line'){
            	 plan.advanceRepayRate = Calculator.prototype.ADVANCE_REPAYMENT_RATE_78_LINE_DICT[plan.period];
             }
        	plan.mfare = plan.platformRate * plan.contractAmount; //月平台费
        	plan.month_pay_total = round(plan.month_pay + plan.mfare); //月还(不包括前期服务费)
            plan.loan = plan.principal; //申请金额
            plan.fare = plan.mfare * plan.period + plan.preFee; //总平台费(前期服务费+月平台费总和)
            plan.balance = plan.total + plan.fare - plan.preFee; //总金额(合同金额+利息+平台费)
            plan.totalFee = round(plan.preFee + plan.interest + plan.mfare * plan.period);//借款总成本
            break;
        case this.JY_129:
        	if(flag == 'online'){
        		plan.platformRate = Calculator.prototype.M_FEE_RATE_129_DICT[plan.period];//平台费率
        		plan.advanceRepayRate = Calculator.prototype.ADVANCE_REPAYMENT_RATE_129_DICT[plan.period];
	       	} else if(flag == 'line'){
	       		plan.platformRate = Calculator.prototype.M_FEE_RATE_129_LINE_DICT[plan.period];//平台费率
	       		plan.advanceRepayRate = Calculator.prototype.ADVANCE_REPAYMENT_RATE_129_LINE_DICT[plan.period];
	       	}
        	plan.mfare = round(plan.platformRate * plan.contractAmount);
        	plan.month_pay_total = round(plan.month_pay + plan.mfare);
            plan.loan = plan.principal;
            plan.fare = plan.mfare * plan.period + plan.preFee;
            plan.balance = round((plan.total + plan.fare - plan.preFee));//借款总成本
            plan.totalFee = round(plan.preFee + plan.interest + plan.mfare * plan.period);
            break;
        case this.JY_189:
        	if(flag == 'online'){
        		plan.platformRate = Calculator.prototype.M_FEE_RATE_189_DICT[plan.period];//平台费率
        		plan.advanceRepayRate = Calculator.prototype.ADVANCE_REPAYMENT_RATE_189_DICT[plan.period];
	       	} else if(flag == 'line'){
	       		plan.platformRate = Calculator.prototype.M_FEE_RATE_189_LINE_DICT[plan.period];//平台费率
	       		plan.advanceRepayRate = Calculator.prototype.ADVANCE_REPAYMENT_RATE_189_LINE_DICT[plan.period];
	       	}
            plan.mfare = plan.platformRate * plan.contractAmount;
            plan.month_pay_total = round(plan.month_pay + plan.mfare);
            plan.loan = plan.principal;
            plan.fare = plan.mfare * plan.period + plan.preFee;
            plan.balance = plan.total + plan.fare - plan.preFee;
            plan.totalFee = round(plan.preFee + plan.interest + plan.mfare * plan.period);//借款总成本
            break;
        case this.NORMAL:
        default :
        	//以下是出借计算器内容
        	plan.month_pay = this._monthPay(plan.principal, plan.period,  plan.mrate);
        	plan.total = plan.month_pay * plan.period;
        	plan.fare = ((plan.period)>2?(plan.principal)*(0.04+((plan.period)-2)*0.002):(plan.principal)*0.04);
	        plan.mfare = 0;
	        plan.loan = plan.principal - plan.fare;
	        plan.balance = round(plan.total);
	        plan.interest = round(plan.total - plan.principal);
	        plan.contractAmount = plan.principal;
    }
    plan.total = round(plan.total + plan.fare);
    plan.fare = round(plan.fare);
    return plan;
};
Calculator.prototype._genPlan = function(plan){
    var pay_unit = [], p_balance = plan.contractAmount, i_balance = plan.interest;
    for(var i=1;i<=plan.period;i++){
        var unit = {};
        unit.index = i;
        unit.fare = plan.mfare;
        if(i == plan.period){
            unit.interest = round(i_balance);
            unit.principal = round(p_balance);
            //最后一期 月还金额 - (月还x期数  - 总费用 - 用申请金额) 注：月还减去的是一个负值  这个负值是 月还x期数 损失的精度
            unit.total = round(plan.month_pay + unit.fare) -  round(round(round(plan.month_pay + unit.fare) * plan.period) - plan.totalFee - plan.principal);
            unit.balance = 0;
            p_balance = 0;
            i_balance = 0;
        } else {
            unit.total = round(plan.month_pay + unit.fare);
            unit.interest = round(p_balance * plan.mrate);
            unit.principal = round(plan.month_pay - unit.interest);
            unit.balance = round((plan.balance - (unit.fare + plan.month_pay) * i));
            p_balance -= unit.principal;
            i_balance -= unit.interest;
        }
        var a;
        var b;
        if(i <= Calculator.prototype.M){
        	a = plan.preFee - (plan.contractAmount *  plan.advanceRepayRate);
        	b = plan.contractAmount * ( plan.preFeeRate - plan.advanceRepayRate);
        } else {
        	a = (plan.preFee - (plan.contractAmount *  plan.advanceRepayRate)) * (1 - (i-3)/(plan.period-3));
        	b = plan.contractAmount * ( plan.preFeeRate - plan.advanceRepayRate) * (1 - (i-3)/(plan.period-3));
        }
        a = Math.abs(a);
        b = Math.abs(b);
        unit.a = a;
        unit.b = b;
        if(a > b){
    		unit.advanceRepayM= round(b);
    	} else {
    		unit.advanceRepayM = round(a);
    	}
        unit.p_balance = p_balance;
        unit.advanceRepayAMT = round(plan.month_pay_total + p_balance - unit.advanceRepayM + p_balance * 0.005);//提前还款结清金额
        pay_unit.push(unit);
    }
    plan.pay_unit = pay_unit;
    plan.month_pay = pay_unit[0].total;
    return plan;
};
//获取前期服务费率
Calculator.prototype._getPreFeeRate = function (period,flag) {
	var preFeeRate;
    switch(this.mode){
    case this.JY:
    	if(flag == 'online'){
    		preFeeRate = Calculator.prototype.PRE_FEE_RATE_78_DICT[period];
    	} else if(flag == 'line'){
    		preFeeRate = Calculator.prototype.PRE_FEE_RATE_78_LINE_DICT[period];
    	}
        break;
    case this.JY_129:
    	if(flag == 'online'){
    		preFeeRate = Calculator.prototype.PRE_FEE_RATE_129_DICT[period];
    	} else if(flag == 'line'){
    		preFeeRate = Calculator.prototype.PRE_FEE_RATE_129_LINE_DICT[period];
    	}
        break;
    case this.JY_189:
    	if(flag == 'online'){
    		preFeeRate = Calculator.prototype.PRE_FEE_RATE_189_DICT[period];
    	} else if(flag == 'line'){
    		preFeeRate = Calculator.prototype.PRE_FEE_RATE_189_LINE_DICT[period];
    	}
        break;
    case this.NORMAL:
    default :
    	preFeeRate = 0;
	}
    return preFeeRate;
};
//进行百位舍去
function give(x) {
	return parseInt(x/100)*100;
};
function round(x){
    return Math.round(x*100)/100;
}