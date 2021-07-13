<template>
    <div class="payment-area">
        <div class="payment-area-title">
            <h2>我的收银台</h2>
        </div>
        <div class="payment-area-detail">
            请支付：&nbsp;&nbsp;¥&nbsp;
            <span> {{actualPrice}} </span>
        </div>
        <div class="payment-area-way">
            <h4>请选择支付方式</h4>
            <div class="payment-payway-box">
                <div class="paymeny-payway-row" v-for="item in banks" :key="item.wid">
                    <input type="checkbox" class="chooseBankCard" :wid="item.wid">
                    <img :src="item.bank.bkimage" :title="item.cardnumber">
                </div>
                <div class="paymeny-payway-row" v-show="alipay != null">
                    <input type="checkbox" id="chooseAlipay">
                    <img src="../../../static/mobilepay/alipay.png" title="alipay">
                </div>
                <div class="paymeny-payway-row" v-show="wechat != null">
                    <input type="checkbox" id="chooseWechat">
                    <img src="../../../static/mobilepay/wechat.png" title="wechat">
                </div>
            </div>
        </div>
        <div class="payment-area-password">
            <div class="payment-password-box">
                <div class="payment-password-area">
                    <input type="password" maxlength="1" class="payment-password-item" id="payment-psd-1">
                    <input type="password" maxlength="1" class="payment-password-item" id="payment-psd-2">
                    <input type="password" maxlength="1" class="payment-password-item" id="payment-psd-3">
                    <input type="password" maxlength="1" class="payment-password-item" id="payment-psd-4">
                    <input type="password" maxlength="1" class="payment-password-item" id="payment-psd-5">
                    <input type="password" maxlength="1" class="payment-password-item" id="payment-psd-6">
                </div>
                <div class="payment-password-box-span">请输入6位支付密码</div>
                <div class="payment-password-submit-btn" @click="_confirmPay()">确认付款</div>
            </div>
        </div>
        
    </div>
</template>

<script>
import qs from 'qs';
import axios from "axios";

export default {
    name: 'PaymentArea',

    inject:['reload'], //调用刷新

    data() {
        return {
            actualPrice: null,
            banks: [],
            alipay: null,
            wechat: null,
            user_id: null,
            adid: null,
            psd: null,
            wid: null,
            apid: null,
            wcid: null,
            oid: null,
        }
    },

    created() {
        this.user_id = this.$route.query.user_id;
        this.adid = this.$route.query.adid;
        this.actualPrice = this.$route.query.actualPrice;
        this.oid = this.$route.query.oid;
        console.log(this.oid);

        /**
         * 根据user_id查询已经绑定的银行卡
         */
        let postData = this.qs.stringify({
            user_id: this.user_id
        })
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/wallet/botainBankcardByUerId',
            data: postData
        }).then((response) => {
            this.banks = response.data.banks
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 根据user_id查询alipay
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/alipay/obtainAlipay',
            data: postData
        }).then((response) => {
            this.alipay = response.data.alipay
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 根据user_id查询wechat
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/wechat/obtainWechat',
            data: postData
        }).then((response) => {
            this.wechat = response.data.wechat
        }).catch((error) => {
            console.log(error);
        });
    },

    mounted() {
        setTimeout(() => {  
            let that = this;

            /**
             * 点击银行卡复选框
             */
            let chooseBankCards = document.querySelectorAll(".chooseBankCard");
            for (var i=0 ;i<chooseBankCards.length; i++) {
                chooseBankCards[i].setAttribute('index', i);
                chooseBankCards[i].addEventListener('change', function() {
                    if (this.checked) {
                        that.wid = this.getAttribute('wid');
                    }
                })
            }

            /**
             * 点击支付宝复选框
             */
            let chooseAlipay = document.querySelector("#chooseAlipay");
            chooseAlipay.addEventListener('change', function() {
                if (chooseAlipay.checked) {
                    that.apid = that.alipay.apid;
                }
            })

            /**
             * 点击wechat复选框
             */
            let chooseWechat = document.querySelector("#chooseWechat");
            chooseWechat.addEventListener('change', function() {
                if (chooseWechat.checked) {
                    that.wcid = that.wechat.wcid;
                }
            })

            /**
             * 输入密码时光标自动向后移动
             */
            let passwordArr = [];
            let passworditems = document.querySelectorAll(".payment-password-item");
            for (var i =0; i<passworditems.length; i++) {
                passworditems[i].setAttribute('index', i);
                passworditems[i].addEventListener('keyup', function(event) {          
                    passwordArr.push(this.value);
                    let nextindex = Number(this.getAttribute('index')) + 1;
                    if (nextindex <= 5) {                
                        this.blur();
                        passworditems[nextindex].focus()
                    } else if (nextindex == 6) {
                        this.blur();
                        that.psd = passwordArr.join("");
                    }
                });
            }


        }, 500)
    },

    methods: {
        /**
         * 确认付款
         */
        _confirmPay() {
            console.log(this.wid, this.apid, this.wcid, this.psd, this.oid);
            let that = this;
            if (this.wid != null) {
                let postData2 = this.qs.stringify({
                    wid: this.wid
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/wallet/confirmBankCard',
                    data: postData2
                }).then((response) => {
                    console.log(response.data.wallet);
                    let cardpsd = response.data.wallet.cardpsd;
                    if (this.psd == cardpsd) {
                        let postData5 = qs.stringify({
                            oid: that.oid
                        })
                        axios({
                            method: 'post',
                            url: '/ssm_project_war_exploded/order/changeOpay',
                            data: postData5
                        }).then((response) => {
                            console.log(response.data);
                            this.$message.success("付款成功");
                            this.reload();
                        }).catch((error) => {
                            console.log(error);
                            this.$message.warning("付款失败");
                            this.reload();
                        });      
                    } else {
                        this.$message.warning("付款失败");
                        this.reload();
                    }
                }).catch((error) => {
                    console.log(error);
                });
            }

            if (this.apid != null) {
                let postData3 = this.qs.stringify({
                    apid: this.apid
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/alipay/confirmAlipay',
                    data: postData3
                }).then((response) => {
                    console.log(response.data.alipay);
                    let appaypsd = response.data.alipay.appaypsd;
                    if (this.psd == appaypsd) {
                        let postData5 = qs.stringify({
                            oid: that.oid
                        })
                        axios({
                            method: 'post',
                            url: '/ssm_project_war_exploded/order/changeOpay',
                            data: postData5
                        }).then((response) => {
                            console.log(response.data);
                            this.$message.success("付款成功");
                            this.reload();
                        }).catch((error) => {
                            console.log(error);
                            this.$message.warning("付款失败");
                            this.reload();
                        });                       
                    } else {
                        this.$message.warning("付款失败");
                        this.reload();
                    }
                }).catch((error) => {
                    console.log(error);
                });
            }

            if (this.wcid != null) {
                let postData4 = this.qs.stringify({
                    wcid: this.wcid
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/wechat/confirmWechat',
                    data: postData4
                }).then((response) => {
                    console.log(response.data.wechat);
                    let wcpaypsd = response.data.wechat.wcpaypsd;
                    if (this.psd == wcpaypsd) {
                        let postData5 = qs.stringify({
                            oid: that.oid
                        })
                        axios({
                            method: 'post',
                            url: '/ssm_project_war_exploded/order/changeOpay',
                            data: postData5
                        }).then((response) => {
                            console.log(response.data);
                            this.$message.success("付款成功");
                            this.reload();
                        }).catch((error) => {
                            console.log(error);
                            this.$message.warning("付款失败");
                            this.reload();
                        });      
                    } else {
                        this.$message.warning("付款失败");
                        this.reload();
                    }
                }).catch((error) => {
                    console.log(error);
                });
            }
        }
    }
}
</script>

<style>
.payment-area {
    margin-top: 25px;
}

.payment-area-title {
    padding: 0 10px;
    height: 35px;
    line-height: 35px;
    background-color: rgb(223, 238, 214);
}

.payment-area-detail {
    margin-top: 30px;
    padding: 0 10px;
    height: 40px;
    line-height: 40px;
    font-size: 16px;
    font-weight: 600;
    /* border-bottom: 2px solid green; */
}

.payment-area-detail span {
    font-size: 24px;
    color: red;
}

.payment-area-way {
    padding: 10px 0;
    margin-top: 30px;  
    /* background-color: pink; */
    border-top: 2px dashed rgb(63, 126, 52);
}

.payment-payway-box {
    display: flex;
    padding: 0 15px;
    margin: 15px 0;
    flex-wrap: wrap;
    /* height: 160px; */
}

.paymeny-payway-row {
    display: flex;
    margin: 5px 5px;
    height: 80px;
    /* border: 1px solid #ccc; */
}

.paymeny-payway-row img {
    margin: 0 5px;
    width: 50px;
    height: 50px;
    border-radius: 5px
}

.payment-area-password {
    margin-top: 20px;
    margin-bottom: 100px;
}

.payment-password-box {
    margin-top: 5px;
    padding: 0 20px;
}

.payment-password-box-span {
    margin-top: 8px;
    line-height: 20px;
    font-size: 14px;
    color: rgb(146, 146, 146);
}

.payment-password-area {
    display: flex;
    width: 220px;
    height: 40px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background: rgb(238, 238, 238);
}

.payment-password-item {
    margin: 5px 2px;
    width: 28px;
    height: 28px;
    line-height: 28px;
    text-align: center;
    border: 1px solid #ccc;
    background-color: white;
    border-radius: 4px;
}

.payment-password-item:nth-child(1) {
    margin-left: 10px;
}

.payment-password-item:nth-child(6) {
    margin-right: 10px;
}

.payment-password-submit-btn {
    margin-top: 20px;
    width: 140px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    font-size: 16px;
    font-weight: 500;
    color: white;
    border-radius: 5px;
    background-color: green;
    cursor: pointer;
}
</style>