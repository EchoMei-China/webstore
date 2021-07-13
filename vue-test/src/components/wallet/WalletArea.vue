<template>
    <div class="wallet-area">
        <div class="wallet-area-top">
            <ul>
                <li class="wallet-area-change wallet-area-current">零钱</li>
                <li class="wallet-area-bankcard">银行卡</li>
                <li class="wallet-area-mobilepayment">移动支付</li>
                <li class="wallet-area-paypoints">积分</li>
            </ul>
        </div>

        <div class="wallet-area-bottom">
            <div class="wallet-area-div" style="display: block">
                <div class="wallet-change-logo"></div>
                <div class="wallet-change-area">
                    <h3>我的零钱</h3>
                    <h1> {{change}} </h1>
                </div>
            </div>
            <div class="wallet-area-div">
                <div class="wallet-bankcard-logo"></div>
                <div class="wallet-bankcard-area">
                    <h3>我的银行卡</h3>
                    <ul>
                        <li v-for="item in banks" :key="item.wid">
                            <div class="wallet-bankcard-detail" @click="_bankcardDetail(item.bkid, item.bank.bkimage, item.bank.bkname, item.cardtype, item.cardnumber, item.bank.dailylimit, item.bank.singlelimit)">
                                <div class="bankcard-detail-logo">
                                    <img :src="item.bank.bkimage" alt="">
                                </div>
                                <div class="bankcard-detail-info">
                                    <div class="detail-info-bankname">{{item.bank.bkname}}</div>
                                    <div class="detail-info-cardtype"> {{ item.cardtype }} </div>
                                    <div class="detail-info-cardno"> {{ item.cardnumber }} </div>
                                </div>
                            </div>
                        </li>
                        <li class="wallet-bankcard-detail-li">
                            <div class="wallet-bankcard-add" @click="_addBankCard()">
                                <h2>+&nbsp;&nbsp;&nbsp;添加银行卡</h2>
                            </div>
                        </li>
                    </ul>
                    <!-- 银行卡细节 -->
                    <div class="bankcard-moadl-box">
                        <div class="close-bankcard-moadlbox" @click="_closeModalBox()">X</div>
                        <div class="bankcard-modalbox-image">
                            <img :src="bkimageDetail" alt="">
                        </div>
                        <div class="bankcard-modalbox-title">{{ bknameDetail }}{{ cardtypeDetail }}</div>
                        <div class="bankcard-modalbox-cardnum">{{ cardnumberDetail }}</div>
                        <div class="bankcard-modalbox-limit">
                            <div class="modalbox-limit-title">支付限额</div>
                            <div class="modalbox-limit-singlelimit">
                                <div class="modalbox-limit-left">单笔限额</div>
                                <div class="modalbox-limit-right">¥ {{ singlelimitDetail }}</div>
                            </div>
                            <div class="modalbox-limit-dailylimit">
                                <div class="modalbox-limit-left">每日限额</div>
                                <div class="modalbox-limit-right">¥ {{ dailylimitDetail }}</div>
                            </div>
                        </div>
                        <div class="modalbox-contact-binding" @click="_contactBinding(cardnumberDetail)">解除绑定</div>
                    </div>
                    <!-- 添加银行卡 -->
                    <div class="bankcard-add-modalbox">
                        <div class="close-bankcard-add" @click="_closeAddCard()">X</div>
                        <div class="bankcard-add-row">
                            <div class="bandcard-row-left">请选择银行</div>
                            <div class="bankcard-row-right">
                                <select v-model="bankItem" name="bankItem" id="bankItem">
                                    <option v-for="item in banknames" :key="item.bkid"> {{ item.bkname }} </option>
                                </select>
                            </div>
                        </div>
                        <div class="bankcard-add-row">
                            <div class="bandcard-row-left">请选择银行卡类型</div>
                            <div class="bankcard-row-right">
                                <select v-model="cardtypeItem" name="cardtypeItem" id="cardtypeItem">
                                    <option>储蓄卡</option>
                                    <option>信用卡</option>
                                </select>
                            </div>
                        </div>
                        <div class="bankcard-add-row">
                            <div class="bandcard-row-left">持卡人</div>
                            <div class="bankcard-row-right">
                                <span class=""> {{ user_name }} </span>
                            </div>
                        </div>
                        <div class="bankcard-add-row">
                            <div class="bandcard-row-left">请输入银行卡号</div>
                            <div class="bankcard-row-right">
                                <input type="text" v-model="cardNumber">
                            </div>
                        </div>
                        <div class="bankcard-add-row">
                            <div class="bandcard-row-left">请输入银行卡密码</div>
                            <div class="bankcard-row-right">
                                <input type="password" v-model="cardPsd" maxlength="6">
                            </div>
                        </div>
                        <div class="bankcard-add-btn" @click="_addBtn(bankItem, cardtypeItem, cardNumber, cardPsd)">添加</div>
                    </div>
                </div>
            </div>
            <div class="wallet-area-div">
                <div class="wallet-mobilepayment-logo"></div>
                <div class="wallet-mobilepayment-area">
                    <h3>移动支付</h3>
                    <div class="wallet-mobilepayment-alipay" v-show="alipay != null">
                        <img :src="alipayImg" alt="">
                    </div>
                    <div class="wallet-mobilepayment-wechat" v-show="wechat != null">
                        <img :src="wechatImg" alt="">
                    </div>
                </div>
            </div>
            <div class="wallet-area-div">
                <div class="wallet-integral-logo"></div>
                <div class="wallet-integral-area">
                    <h3>我的积分</h3>
                    <h1> {{integral}} </h1>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import qs from 'qs';
import axios from "axios";

export default {
    name: 'WalletArea',

    inject:['reload'], //调用刷新

    data() {
        return {
            integral: 0,
            change: 0,
            user_id: null, //用户id
            user_name: null, //用户真实姓名
            banks: [], //用户已经绑定的银行卡
            bknameDetail: null, //已经绑定银行卡的银行名
            cardtypeDetail: null, //已经绑定银行卡的类型
            cardnumberDetail: null, //已经绑定银行卡的号码
            bkimageDetail: null, //已经绑定银行卡的银行logo
            singlelimitDetail: null, //单笔限额
            dailylimitDetail: null, //每日限额
            bankItem: null, //选项--银行名
            cardtypeItem: null, //选项--银行卡类型
            cardNumber: null, //要绑定的银行卡号码,
            banknames: [],
            cardPsd: null, //要绑定的银行卡密码
            alipayImg: '../../../static/mobilepay/alipay.png',
            wechatImg: '../../../static/mobilepay/wechat.png',
            alipay: null,
            wechat: null,
        }
    },

    created() {
        /**
         * 获取用户id
         */
        let user_nickname = sessionStorage.getItem('userNickName');
        let postData1 = this.qs.stringify({
          user_nickname: user_nickname
        })
        this.axios({
          method: 'post',
          url: '/ssm_project_war_exploded/user/botainUserIdByNickname',
          data: postData1
        }).then(response => {
          this.user_id = response.data.user.userId;
          console.log(response.data.user.userId);

            /**
             *获取银行卡信息
             */
            let postData2 = this.qs.stringify({
                user_id: response.data.user.userId
            })
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/wallet/botainBankcardByUerId',
                data: postData2
            }).then(response => {
                console.log(response.data.banks);
                this.banks = response.data.banks
            }).catch(error => {
                console.log(error);
            });

            /**
             * 根据user_id获取change
             */
            let postData3 = this.qs.stringify({
                user_id: response.data.user.userId
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/user/obtainUserById',
                data: postData3
            }).then(response => {
                this.change = response.data.user.change;
            }).catch(error => {
                console.log(error);
            });

            /**
             * 根据user_id搜索alipay和wechat
             */
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/alipay/obtainAlipay',
                data: postData3
            }).then(response => {
                this.alipay = response.data.alipay
            }).catch(error => {
                console.log(error);
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/wechat/obtainWechat',
                data: postData3
            }).then(response => {
                this.wechat = response.data.wechat
            }).catch(error => {
                console.log(error);
            })


        }).catch(error => {
            console.log(error);
        });

        
    },

    mounted() {
        setTimeout( () => {
            /**
             * 给不同银行卡添加背景颜色
             */
            let boxs = document.querySelectorAll(".detail-info-bankname");
            for (var i=0; i<boxs.length; i++) {
                if (boxs[i].innerHTML == '中国银行') {
                    boxs[i].parentNode.parentNode.className = 'wallet-bankcard-detail boc'
                }
                if (boxs[i].innerHTML == '中国建设银行') {
                    boxs[i].parentNode.parentNode.className = 'wallet-bankcard-detail ccb'
                }
                if (boxs[i].innerHTML == '中国农业银行') {
                    boxs[i].parentNode.parentNode.className = 'wallet-bankcard-detail abc'
                }
                if (boxs[i].innerHTML == '中国工商银行') {
                    boxs[i].parentNode.parentNode.className = 'wallet-bankcard-detail icbc'
                }
            }

            /**
             * tab栏切换
             */
            let lis = document.querySelector(".wallet-area-top").querySelectorAll("li");
            let walletAreaDivs = document.querySelectorAll(".wallet-area-div");
            for (var i=0; i<lis.length; i++) {
                lis[i].setAttribute('index', i);
                lis[i].addEventListener('click', function() {
                    for (var i=0; i<lis.length; i++) {
                        lis[i].className = ''
                    }
                    this.className = 'wallet-area-current';
                    let index = this.getAttribute('index');
                    for (var i=0; i<walletAreaDivs.length; i++) {
                        walletAreaDivs[i].style.display = 'none';
                    }
                    walletAreaDivs[index].style.display = 'block';
                })
            }

        }, 300)
    },

    methods: {
        /**
         * 银行卡细节
         */
        _bankcardDetail(bkidVal, bkimageVal, bknameVal, cardtypeVal, cardnumberVal, dailylimitVal, singlelimitVal) {
            let modalbox = document.querySelector(".bankcard-moadl-box");
            modalbox.setAttribute('bkid', bkidVal);
            modalbox.style.display = 'block';
            if (bkidVal == 1) {
                modalbox.style.backgroundColor = 'rgb(224, 44, 44)';
            }
            if (bkidVal == 2) {
                modalbox.style.backgroundColor = 'rgb(123, 147, 255)';
            }
            if (bkidVal == 3) {
                modalbox.style.backgroundColor = 'rgb(98, 172, 80)';
            }
            if (bkidVal == 4) {
                modalbox.style.backgroundColor = 'rgb(236, 117, 117)';
            }
            this.bknameDetail = bknameVal;
            this.cardtypeDetail = cardtypeVal;
            this.cardnumberDetail = cardnumberVal;
            this.bkimageDetail = bkimageVal;
            this.singlelimitDetail = singlelimitVal;
            this.dailylimitDetail = dailylimitVal;

        },

        /**
         * 关闭银行卡细节按钮
         */
        _closeModalBox() {
            let modalbox = document.querySelector(".bankcard-moadl-box");
            modalbox.style.display = 'none';
        },

        /**
         * 添加银行卡
         */
        _addBankCard() {
            let addModalbox = document.querySelector(".bankcard-add-modalbox");
            addModalbox.style.display = 'block';
            /**
             * 根据user_id,获取用户身份证号码、真实姓名
             */
            let postData = this.qs.stringify({
                user_id: this.user_id
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/user/obtainUserById',
                data: postData
            }).then(response => {
                this.user_name = response.data.user.userName;
            }).catch(error => {
                console.log(error);
            });
            /**
             * 获得银行选项
             */
            let postData1 = this.qs.stringify({
                banknames: this.banknames
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/bank/obtainBanks',
                data: postData1
            }).then(response => {
                this.banknames = response.data.banknames
            }).catch(error => {
                console.log(error);
            })
        },

        /**
         * 关闭'添加银行卡'模态框
         */
        _closeAddCard() {
            let addModalbox = document.querySelector(".bankcard-add-modalbox");
            addModalbox.style.display = 'none';
        },

        /**
         * 添加按钮
         */
        _addBtn(bankItemVal, cardtypeItemVal, cardNumberVal, cardPsdVal) {
            let that = this;
            /**
             * 根据bkname，获取bkid
             */
            let postData = this.qs.stringify({
                bkname: bankItemVal
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/bank/obtainBankByBkame',
                data: postData
            }).then(response => {
                let bkid = response.data.bank.bkid;
                let postData2 = qs.stringify({
                    bkid: bkid,
                    cardnumber: cardNumberVal,
                })
                axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/wallet/obtaniWalletByBkidAndCardno',
                    data: postData2
                }).then(response => {
                    if (response.data != 0) {
                        this.$message.warning("您已绑定此银行卡");
                    } else {
                        let postData1 = qs.stringify({
                            user_id: that.user_id,
                            bkid: bkid,
                            cardtype: cardtypeItemVal,
                            cardnumber: cardNumberVal,
                            cardpsd: cardPsdVal
                        });
                        axios({
                            method: 'post',
                            url: '/ssm_project_war_exploded/wallet/insertWallet',
                            data: postData1
                        }).then(response => {
                            console.log(response);
                            this.reload();
                        }).catch(error => {
                            console.log(error);
                        })
                    }
                }).catch(error => {
                    console.log(error);
                })
            }).catch(error => {
                console.log(error);
            })
        },

        /**
         * 解除绑定
         */
        _contactBinding(cardnumberDetailVal) {
            let moadlBox = document.querySelector(".bankcard-moadl-box");
            let bkid = moadlBox.getAttribute('bkid');
            let postData = this.qs.stringify({
                bkid: bkid,
                cardnumber: cardnumberDetailVal
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/wallet/deleteWallet',
                data: postData
            }).then(response => {
                this.reload();
            }).catch(error => {
                console.log(error);
            })
        },

        /**
         * 提现
         */
        _withdraw() {
            alert(this.user_id)
        }
    }


}
</script>

<style>
.wallet-area {
    margin-top: 30px;
    /* background-color: pink; */
}

.wallet-area-top {
    height: 45px;
    /* background-color: aquamarine; */
}

.wallet-area-top ul {
    display: flex;
}

.wallet-area-top ul li {
    flex: 1;
    font-size: 16px;
    line-height: 45px;
    text-align: center;
    cursor: pointer;
}

.wallet-area-change,
.wallet-area-bankcard,
.wallet-area-paypoints {
    color: rgb(12, 53, 12);
    cursor: pointer;
}

.wallet-area-current {
    color: rgb(115, 202, 57)
}

.wallet-area-bottom {
    margin-top: 40px;
}

.wallet-area-div {
    display: none;
    /* background-color: pink; */
}

.wallet-mobilepayment-logo,
.wallet-change-logo,
.wallet-bankcard-logo,
.wallet-integral-logo {
    height: 55px;
    font-size: 45px;
    font-family: 'icomoon';
    text-align: center;
    line-height: 55px;
}

.wallet-change-logo {
    color: rgb(252, 160, 55);
}

.wallet-bankcard-logo {
    color: brown;
}

.wallet-integral-logo {
    color: rgb(130, 130, 230);
}

.wallet-mobilepayment-logo {
    color: rgb(153, 204, 228);
}

.wallet-mobilepayment-area,
.wallet-change-area,
.wallet-bankcard-area,
.wallet-integral-area {
    margin-top: 45px;
    text-align: center;
}

.wallet-mobilepayment-area,
.wallet-bankcard-area,
.wallet-change-area {
    position: relative;
}

.wallet-mobilepayment-area h3,
.wallet-change-area h3,
.wallet-bankcard-area h3,
.wallet-integral-area h3 {
    color: rgb(143, 143, 143);
}

.wallet-change-area h1 {
    font-size: 35px;
}

.wallet-change-area h1::before {
    display: inline-block;
    margin-right: 5px;
    content: '¥';
    font-size: 20px;
}

.wallet-withdraw, 
.wallet-recharge {
    margin: 25px auto;
    width: 180px;
    height: 35px;
    font-size: 18px;
    font-weight: 600;
    color: white;
    text-align: center;
    line-height: 35px;
    background-color: rgb(19, 110, 19);
    cursor: pointer;
    border-radius: 6px;
}

.wallet-withdraw {
    margin-top: 90px;
}

.wallet-bankcard-detail {
    margin: 5px auto;
    padding: 15px;
    display: flex;
    width: 310px;
    height: 70px;
    /* background-color: pink; */
    border-radius: 7px;
    cursor: pointer;
}

.wallet-bankcard-add {
    margin: 15px auto;
    padding: 15px;
    display: flex;
    width: 310px;
    height: 50px;
    /* text-align: center; */
    line-height: 50px;
    background-color: rgb(229, 238, 220);
    border-radius: 7px;
    cursor: pointer;
}

.bankcard-detail-logo {
    width: 40px;
    height: 40px;
    background-color: white;
    border-radius: 50%;
}

.bankcard-detail-logo img {
    margin: 5px auto;
    width: 30px;
    height: 30px;
}

.bankcard-detail-info {
    text-align: left;
    margin-left: 10px;
}

.detail-info-bankname {
    font-size: 14px;
    color: rgb(243, 232, 232);
    font-weight: 600;
}

.detail-info-cardtype {
    margin-top: 5px;
    font-size: 12px;
    color: rgb(109, 109, 109);
}

.detail-info-cardno {
    margin-top: 5px;
    font-size: 18px;
    color: white;
    font-weight: 600;
}


.boc {
    background-color: rgb(224, 44, 44);
}

.ccb {
    background-color: rgb(123, 147, 255);
}

.abc {
    background-color: rgb(98, 172, 80);   
}

.icbc {
    background-color: rgb(236, 117, 117);
}

.bankcard-moadl-box {
    display: none;
    position: absolute;
    top: 0;
    left: 50%;
    transform: translateX(-170px);
    width: 340px;
    height: 340px;
    /* background-color: pink; */
}

.close-bankcard-moadlbox {
    position: absolute;
    top: 0;
    right: 0;
    width: 35px;
    height: 35px;
    line-height: 35px;
    font-size: 20px;
    cursor: pointer;
    color: white;
    /* background-color: white; */
}

.bankcard-modalbox-image {
    margin: 15px auto;
    width: 60px;
    height: 60px;
    background-color: white;
    border-radius: 50%;
}

.bankcard-modalbox-image img {
    margin: 5px auto;
    width: 50px;
    height: 50px;
}

.bankcard-modalbox-title {
    margin: 5px auto;
    font-size: 18px;
    color: white;
    font-weight: 600;
}

.bankcard-modalbox-cardnum {
    margin: 0 auto;
    font-size: 12px;
    color: white;
    font-weight: 300;
}

.bankcard-modalbox-limit {
    margin: 15px auto;
    width: 300px;
    height: 120px;
    background-color: white;
    border-radius: 8px;
}

.modalbox-limit-title {
    padding-left: 10px;
    height: 30px;
    font-size: 14px;
    text-align: left;
    line-height: 30px;
    background-color: rgb(231, 228, 228);
    border-radius: 8px 8px 0 0;
}

.modalbox-limit-singlelimit,
.modalbox-limit-dailylimit  {
    display: flex;
    height: 45px;
}

.modalbox-limit-left {
    width: 180px;
    line-height: 45px;
    font-size: 18px;
    /* background-color: burlywood; */
}

.modalbox-limit-right {
    width: 120px;
    line-height: 45px;
    font-size: 18px;
    color: rgb(146, 146, 146);
}

.modalbox-contact-binding {
    margin: 0 auto;
    width: 70px;
    height: 30px;
    line-height: 30px;
    font-size: 14px;
    /* background-color: white; */
    cursor: pointer;
}

.bankcard-add-modalbox {
    display: none;
    position: absolute;
    top: 0;
    left: 50%;
    transform: translateX(-200px);
    width: 400px;
    height: 450px;
    background-color: rgb(229, 238, 220);
    border-radius: 8px;
}

.close-bankcard-add {
    position: absolute;
    top: 0;
    right: 0;
    width: 30px;
    height: 30px;
    font-size: 18px;
    text-align: center;
    line-height: 30px;
    /* color: white; */
    /* background-color: white; */
    cursor: pointer;
}

.bankcard-add-row {
    margin: 30px 15px;
    display: flex;
    height: 35px;
    /* background-color: aquamarine; */
}

.bandcard-row-left {
    width: 140px;
    font-size: 16px;
    text-align: left;
    line-height: 35px;
    font-weight: 700;
    /* background-color: white; */
}

.bankcard-row-right input {
    padding: 0 8px;
    margin: 1px 0;
    width: 220px;
    height: 31px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.bankcard-row-right span {
    font-size: 14px;
    font-weight: 500;
    line-height: 35px;
}

.bankcard-add-btn {
    margin: 30px auto;
    height: 35px;
    width: 120px;
    font-size: 16px;
    line-height: 35px;
    color: white;
    background-color: green;
    border-radius: 8px;
    cursor: pointer;
}

.wallet-mobilepayment-wechat,
.wallet-mobilepayment-alipay {
    /* display: flex; */
    margin: 10px auto;
    /* width: 600px; */
    /* height: 200px; */
    /* background-color: pink; */
}

.wallet-mobilepayment-wechat img,
.wallet-mobilepayment-alipay img {
    width: 200px;
    height: 160px;
    border-radius: 8px;
    cursor: pointer;
}
</style>