<template>
    <div class="user-detail">
        <div class="user-detail-top">用户信息</div>
        <div class="user-detail-body">
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">昵称</div>
                <div class="userdetailbody-row-center">
                    <input type="text" class="userdetailbody-row-input" v-model="user_nickname" id="ucName">
                </div>
                <div class="userdetailbody-row-right">用户名不可用</div>
            </div>
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">真实姓名</div>
                <div class="userdetailbody-row-center">
                    <input type="text" class="userdetailbody-row-input" v-model="user_name" id="urNAme">
                </div>
                <div class="userdetailbody-row-right">请填写真实姓名</div>
            </div>
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">证件类型</div>
                <div class="userdetailbody-row-center">
                    <div class="userdetailbody-row-center-div">
                        <select name="" id="selectCardType" v-model="card_type">
                            <option selected :label="card_type"></option>
                            <option label="身份证" value="身份证"></option>
                            <option label="军官证" value="军官证"></option>
                            <option label="护照" value="护照"></option>
                            <option label="港澳居民通行证" value="港澳居民通行证"></option>
                            <option label="台湾往来大陆通行证" value="台湾往来大陆通行证"></option>
                        </select>
                    </div>
                </div>
                <div class="userdetailbody-row-right">请选择证件类型</div>
            </div>
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">证件号码</div>
                <div class="userdetailbody-row-center">
                    <input type="text" class="userdetailbody-row-input" v-model="card_no" id="cardNo">
                </div>
                <div class="userdetailbody-row-right">请填写证件号码</div>
            </div>
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">手机号</div>
                <div class="userdetailbody-row-center">
                    <input type="text" class="userdetailbody-row-input" v-model="user_phone" id="ucPhone">
                </div>
                <div class="userdetailbody-row-right">手机号不可用</div>
            </div>
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">性别</div>
                <div class="userdetailbody-row-center">
                    <div class="userdetailbody-row-center-div">
                        <input type="radio" name="sex" id="male">男&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="sex" id="female">女
                    </div>
                </div>
            </div>
             <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">年龄</div>
                <div class="userdetailbody-row-center">
                    <input type="text" class="userdetailbody-row-input" v-model="user_age">
                </div>
            </div>
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">身份类型</div>
                <div class="userdetailbody-row-center">
                    <div class="userdetailbody-row-center-div">
                        <select name="" id="" v-model="user_role">
                            <option selected :label="user_role" :value="user_role"></option>
                            <option label="国家机关、党群组织、企业、事业单位负责人" value="国家机关、党群组织、企业、事业单位负责人"></option>
                            <option label="专业技术人员" value="专业技术人员"></option>
                            <option label="办事人员和有关人员" value="办事人员和有关人员"></option>
                            <option label="商业、服务业人员" value="商业、服务业人员"></option>
                            <option label="农、林、牧、渔、水利业生产人员" value="农、林、牧、渔、水利业生产人员"></option>
                            <option label="生产、运输设备操作人员及有关人员" value="生产、运输设备操作人员及有关人员"></option>
                            <option label="军人" value="军人"></option>
                            <option label="不便分类的其他从业人员" value="不便分类的其他从业人员"></option>
                            <option label="未知" value="未知"></option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">支付宝</div>
                <div class="userdetailbody-row-center">
                    <div class="userdetailbody-row-center-div"> 
                        <span> {{alipay.apnum}} </span>
                    </div>
                </div>
                <div class="userdetailbody-row-right-operator" v-show="alipay.apid != null"><a href="javascript:;" @click="_cancelAlipay(alipay.apid)">解绑</a></div>
                <div class="userdetailbody-row-right-operator" v-show="alipay.apid == null"><a href="javascript:;" alipayattr="true" @click="_showBindBox1($event)">绑定支付宝</a></div>
            </div>
            <div class="userdetail-body-row">
                <div class="userdetailbody-row-left">微信</div>
                <div class="userdetailbody-row-center">
                    <div class="userdetailbody-row-center-div">
                        <span> {{wechat.wcnum}} </span>
                    </div>
                </div>
                <div class="userdetailbody-row-right-operator" v-show="wechat.wcid != null"><a href="javascript:;" @click="_cancelWechat(wechat.wcid)">解绑</a></div>
                <div class="userdetailbody-row-right-operator" v-show="wechat.wcid == null"><a href="javascript:;" wechatattr="true" @click="_showBindBox2($event)">绑定微信</a></div>
            </div>
            <div class="userdetail-body-bind">
                <div class="userdetailbody-bind-close" @click="_closeBindBox()">X</div>
                <div class="userdetailbody-bind-row">
                    <div class="userdetailbody-bind-left">账号</div>
                    <div class="userdetailbody-bind-right">
                        <input type="text" v-model="accountNumber">
                    </div>
                </div>
                <div class="userdetailbody-bind-row">
                    <div class="userdetailbody-bind-left">密码</div>
                    <div class="userdetailbody-bind-right">
                        <input type="password" v-model="accountPassword">
                    </div>
                </div>
                <div class="userdetailbody-bind-row">
                    <div class="userdetailbody-bind-left">支付密码</div>
                    <div class="userdetailbody-bind-right">
                        <input type="password" v-model="payPassword">
                    </div>
                </div>
                <div class="userdetailbody-bind-btn" @click="_confirmBind()">确认绑定</div>
            </div>
            <div class="orderdetail-body-btnrow">
                <div class="userdetail-body-release-btn" @click="_release()">取消</div>
                <div class="userdetail-body-btn" @click="_saveChange(user_nickname, user_name, card_type, card_no, user_phone, user_age, user_role, user_sex)">保存</div>
            </div>
        </div>
    </div>
</template>

<script>
import qs from 'qs'
import axios from 'axios'

export default {
    name: 'UserDetail',

    inject:['reload'], //调用刷新

    data() {
        return {
            accountNumber: null,
            accountPassword: null,
            payPassword: null,
            user_id: null,
            card_no: null,
            card_type: null,
            user_age: null,
            user_name: null,
            user_nickname: null,
            user_phone: null,
            user_role: null,
            user_sex: null,
            // wcid: null,
            // apid: 1,
            alipay: [],
            wechat: [],
            alipayattr: false,
            wechatattr: false,
            // apnum: null,
            // wcnum: null
        }
    },

    created() {
        /**
         * 获取用户信息
         */
        let that = this;
        this.user_id = sessionStorage.getItem('userId');
        let postData1 = this.qs.stringify({
          user_id: this.user_id
        })
        this.axios({
          method: 'post',
          url: '/ssm_project_war_exploded/user/obtainUserById',
          data: postData1
        }).then(response => {
            let user = response.data.user;
            this.user_id = user.userId;
            this.user_nickname = user.userNickname;
            this.user_name = user.userName;
            this.user_age = user.userAge;
            this.user_phone = user.userPhone;
            this.user_role = user.userRole;
            this.user_sex = user.userSex;
            this.card_type = user.cardType;
            this.card_no = user.cardNo;
        }).catch(error => {
          console.log(error);
        })

        /**
         * 获取alipay
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/alipay/obtainAlipay',
            data: postData1
        }).then((response) => {
            this.alipay = response.data.alipay;
            if (this.alipay == null) {
                that.alipay = [
                    {
                        apnum: ""
                    }
                ]
            }
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获取wechat
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/wechat/obtainWechat',
            data: postData1
        }).then((response) => {
            this.wechat = response.data.wechat;
            if (this.wechat == null) {
                that.wechat = [
                    {
                        wcnum: ""
                    }
                ]
            }
            // console.log(this.wechat);
            // console.log(this.wechat.wcnum);
        }).catch((error) => {
            console.log(error);
        });
        
    },

    mounted() {
        let that = this;
        let regName = /^[\u4E00-\u9FA5A-Za-z0-9_]{3,15}$/;  //用户名验证
        let regPhone = /^1[3|4|5|7|8]\d{9}$/;  //手机号码验证
        let uName = document.querySelector("#ucName");
        let uPhone = document.querySelector("#ucPhone");
        let urNAme = document.querySelector("#urNAme"); //真实姓名
        let cardNo = document.querySelector("#cardNo"); //证件号码
        urNAme.addEventListener('blur', function() {
            if (that.user_name == '' || that.user_name == null) {
                urNAme.parentNode.parentNode.lastChild.style.display = 'block';
            } else {
                urNAme.parentNode.parentNode.lastChild.style.display = 'none';
            }
        })
        cardNo.addEventListener('blur', function() {
            if (that.card_no == '' || that.card_no == null ) {
                cardNo.parentNode.parentNode.lastChild.style.display = 'block';
            } else {
                cardNo.parentNode.parentNode.lastChild.style.display = 'none';
            }
        })
        uPhone.addEventListener('blur', function() {
            if (regPhone.test(that.user_phone)) { 
                uPhone.parentNode.parentNode.lastChild.style.display = 'none'
                let postData2 = qs.stringify({
                    uPhone: that.user_phone
                })
                axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/user/checkUserPhone',
                    data: postData2
                }).then(response => {
                    if (response.data !=0) {
                        uPhone.parentNode.parentNode.lastChild.innerHTML = '手机号已被注册';
                        uPhone.parentNode.parentNode.lastChild.style.display = 'block';
                    } else {
                    }
                })
            } else {
                uPhone.parentNode.parentNode.lastChild.innerHTML = '手机号不可用';
                uPhone.parentNode.parentNode.lastChild.style.display = 'block';
            }
        })
        uName.addEventListener("blur", function() {
            if (regName.test(that.user_nickname)) { 
                uName.parentNode.parentNode.lastChild.style.display = 'none';
                let number;
                let postData1 = qs.stringify({
                    uName: that.user_nickname
                })
                axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/user/checkUserName',
                    data: postData1
                }).then(response => {
                    number = response.data;
                    console.log(number);
                    if (number != 0) {
                        uName.parentNode.parentNode.lastChild.innerHTML = '用户名已被注册';
                        uName.parentNode.parentNode.lastChild.style.display = 'block';
                    } else {
                    }
                }).catch(error => {
                    console.log(error);
                })
            } else {
                uName.parentNode.parentNode.lastChild.innerHTML = '用户名不可用';
                uName.parentNode.parentNode.lastChild.style.display = 'block'
            }
        })

    },

    methods: {
        /**
         * 保存
         */
        _saveChange(user_nickname, user_name, card_type, card_no, user_phone, user_age, user_role) {
            let that = this;
            let user_sex = null;
            let male = document.querySelector("#male");
            let female = document.querySelector("#female");
            let selectCardType = document.querySelector("#selectCardType"); //选择证件类型
            if (male.checked) {
                user_sex = '男'
            } else if (female.checked) {
                user_sex = '女'
            }
            console.log(user_nickname, user_name, card_type, card_no, user_phone, user_age, user_role, user_sex);
            if (user_nickname == null || user_name == null || card_type == null || card_no == null || user_phone == null || user_nickname == '' || user_name == '' || card_type == '' || card_no == '' || user_phone == '') {
                this.$message.warning("请填写完整信息")
            } else {
                let postData = qs.stringify({
                    userId: that.user_id,
                    userNickname: that.user_nickname,
                    userName: that.user_name,
                    userPhone: that.user_phone,
                    userAge: that.user_age,
                    userRole: that.user_role,
                    userSex: user_sex,
                    cardType: that.card_type,
                    cardNo: that.card_no
                })
                axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/user/updateUserById',
                    data: postData
                }).then((resopnse) => {
                    this.$message.success("修改成功");
                    // this.reload();
                }).catch((error) => {
                    console.log(error);
                });
            }
        },

        /**
         * 取消
         */
        _release() {
            this.reload();
        },

        /**
         * 解绑支付宝
         */
        _cancelAlipay(apid) {
            let postData = this.qs.stringify({
                apid: apid
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/alipay/deleteAlipay',
                data: postData
            }).then((response) => {
                this.$message.success("解绑成功");
                this.reload();
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 解绑微信
         */
        _cancelWechat(wcid) {
            let postData = this.qs.stringify({
                wcid: wcid
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/wechat/deleteWechat',
                data: postData
            }).then((response) => {
                this.$message.success("解绑成功");
                this.reload();
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 显示模态框
         */
        _showBindBox1(event) {
            let bindBox = document.querySelector(".userdetail-body-bind");
            bindBox.style.display = 'block';
            let alipayattr = event.target.getAttribute('alipayattr');
            this.alipayattr = alipayattr;
            // alert(alipayattr)
        },
        _showBindBox2(event) {
            let bindBox = document.querySelector(".userdetail-body-bind");
            bindBox.style.display = 'block';
            let wechatattr = event.target.getAttribute('wechatattr');
            // console.log(event.target);
            this.wechatattr = wechatattr;
            // alert(wechatattr)
        },

        /**
         * 关闭模态框
         */
        _closeBindBox() {
            let bindBox = document.querySelector(".userdetail-body-bind");
            bindBox.style.display = 'none'
        },

        /**
         * 确认绑定
         */
        _confirmBind() {
            if (this.wechatattr) {
                /**
                 * 绑定新微信
                 */
                let postData1 = this.qs.stringify({
                    user_id: this.user_id,
                    wcnum: this.accountNumber,
                    wcpsd: this.accountPassword,
                    wcpaypsd: this.payPassword,
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/wechat/insertWechat',
                    data: postData1
                }).then((response) => {
                    this.reload();
                    this.$message.success("绑定成功")
                }).catch((error) => {
                    console.log(error);
                    this.$$message.warning("绑定失败")
                });

            } else if (this.alipayattr) {
                /**
                 * 绑定新支付宝
                 */
                let postData2 = this.qs.stringify({
                    user_id: this.user_id,
                    apnum: this.accountNumber,
                    appsd: this.accountPassword,
                    appaypsd: this.payPassword,
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/alipay/insertAlipay',
                    data: postData2
                }).then((response) => {
                    this.reload();
                    this.$message.success("绑定成功")
                }).catch((error) => {
                    console.log(error);
                    this.$$message.warning("绑定失败")
                });
            }
        }
    }
}
</script>

<style>
.user-detail {
    margin-top: 15px;
    margin-bottom: 150px;
}

.user-detail-top {
    padding: 0 15px;
    height: 40px;
    font-size: 16px;
    font-weight: 500;
    color: green;
    line-height: 40px;
}

.user-detail-body {
    position: relative;
}

.userdetail-body-row {
    display: flex;
    height: 40px;
    margin: 20px 0;
    /* background-color: pink; */
}

.userdetailbody-row-left {
    width: 200px;
    text-align: right;
    line-height: 40px;
}

.userdetailbody-row-center {
    width: 400px;
}

.userdetailbody-row-input {
    margin: 2px 0 2px 15px;
    padding: 0 15px;
    width: 200px;
    height: 34px;
    line-height: 34px;
    border: 1px solid #ccc;
}

.userdetailbody-row-center-div {
    margin: 12px 0 0 15px;
}

.userdetailbody-row-right {
    display: none;
    width: 400px;
    line-height: 40px;
    color: rgb(243, 95, 95);
}

.userdetailbody-row-right-operator {
    width: 400px;
    line-height: 40px;
    /* color: rgb(243, 95, 95); */
}

.orderdetail-body-btnrow {
    display: flex;
}

.userdetail-body-btn {
    margin: 20px 0 0 0;
    width: 200px;
    height: 35px;
    font-size: 16px;
    font-weight: 500;    
    text-align: center;
    line-height: 35px;
    color: white;
    background-color: green;
    border-radius: 5px;
    cursor: pointer;
}

.userdetail-body-release-btn {
    margin: 20px 30px 0 215px;
    width: 198px;
    height: 33px;
    font-size: 16px;
    font-weight: 500;    
    text-align: center;
    line-height: 35px;
    color: green;
    border: 1px solid green;
    border-radius: 5px;
    cursor: pointer;
}

.userdetail-body-bind {
    display: none;
    position: absolute;
    top: 50%;
    left: 50%;
    /* margin-top: -200px; */
    margin-left: -200px;
    width: 400px;
    height: 360px;
    color: white;
    background-color: rgb(30, 73, 30);
    border-radius: 8px;
    z-index: 99;
}

.userdetailbody-bind-close {
    position: absolute;
    top: 0;
    right: 0;
    width: 40px;
    height: 40px;
    font-size: 20px;
    font-weight: 500;
    text-align: center;
    line-height: 40px;
    cursor: pointer;
    border-radius: 8px;
}

.userdetailbody-bind-row {
    display: flex;
    margin: 40px 0 0 0;
    height: 35px;
}

.userdetailbody-bind-left {
    width: 100px;
    font-weight: 500;
    text-align: right;
    line-height: 35px;
}

.userdetailbody-bind-right {
    margin-left: 20px;
    width: 220px;
}

.userdetailbody-bind-right input {
    padding: 0 15px;
    width: 168px;
    height: 33px;
    line-height: 33px;
    border: 1px solid white;
    border-radius: 5px;
}

.userdetailbody-bind-btn {
    margin: 40px 0 0 120px;
    width: 120px;
    height: 35px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
    line-height: 35px;
    border: 1px solid white;
    cursor: pointer;
    border-radius: 5px;
}
</style>