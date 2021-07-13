<template>
    <div class="registered-area">
        <div class="registered-area-closed" @click="_closeRegistered()">X</div>
        <h3>注册新用户</h3>
        <div class="registered-content">
            <ul>
                <li>
                    <div>用户名：</div>
                    <input type="text" placeholder="3-15位可包含中文/英文/数字/下划线" v-model="userNickName" id="uName">
                    <span>X 用户名不可用</span>
                </li>
                <li>
                    <div>手机号：</div>
                    <input type="text" placeholder="请输入手机号" v-model="userPhone" id="uPhone" ref="uPhones">
                    <span>X 手机号错误</span>
                </li>
                <li>
                    <div>密码：</div>
                    <input type="password" placeholder="6-12位可包含数字/英文/下划线" v-model="userPassword" id="uPsd">
                    <span>X 密码格式不正确</span>
                </li>
                <li>
                    <div>确认密码：</div>
                    <input type="password" placeholder="请确认密码" id="uPsd2">
                    <span>X 两次密码不相同</span>
                </li>
            </ul>
            <div class="registered-content-btn" @click="_submitRegistered(userNickName, userPhone, userPassword)">立即注册</div>
        </div>
    </div>
</template>

<script>
import qs from "qs";
import axios from "axios";

export default {
    name: 'RegisteredArea',

    data() {
        return {
            userNickName: '',
            userPhone: '',
            userPassword: '',
        }
    },

    methods: {
        /**
         * 关闭注册页面
         */
        _closeRegistered() {
            this.$router.push('/home')
        },

        /**
         * 点击注册按钮
         */
        _submitRegistered(nicknameVal, phoneVal, psdVal) {
            let postData = this.qs.stringify({
                user_nickname: nicknameVal,
                user_phone: phoneVal,
                user_password: psdVal
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/user/registered',
                data: postData
            }).then(response => {
                console.log(response);
                if (response.data == 1) {
                    this.$message.success("注册成功");
                    this.$router.push({ path: "/home" });
                } else {
                    this.$message.warning("注册失败");
                }
            }).catch(error => {
                console.log(error);
            })
        }
    },

    mounted() {
        let regName = /^[\u4E00-\u9FA5A-Za-z0-9_]{3,15}$/;  //用户名验证
        let regPhone = /^1[3|4|5|7|8]\d{9}$/;  //手机号码验证
        let regPsd = /^[A-Za-z0-9]{6,12}$/; //密码验证
        // let uPhone = document.querySelector("#uPhone");
        let uPhone = this.$refs.uPhones;
        let uName = document.querySelector("#uName");
        let uPsd = document.querySelector("#uPsd");
        let uPsd2 = document.querySelector("#uPsd2");
        let submitRegistered = document.querySelector(".registered-content-btn");
        uPhone.addEventListener('blur', function() {
            if (regPhone.test(this.value)) { 
                this.parentNode.lastChild.style.display = 'none'
                let postData2 = qs.stringify({
                    uPhone: this.value
                })
                axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/user/checkUserPhone',
                    data: postData2
                }).then(response => {
                    if (response.data !=0) {
                        this.parentNode.lastChild.innerHTML = '手机号已被注册';
                        this.parentNode.lastChild.style.display = 'block';
                        submitRegistered.style.pointerEvents = 'none';
                    } else {
                        submitRegistered.style.pointerEvents = 'auto';
                    }
                }).catch(error => {
                    console.log(error);
                })
                submitRegistered.style.pointerEvents = 'auto';
            } else {
                this.parentNode.lastChild.style.display = 'block';
                submitRegistered.style.pointerEvents = 'none';
            }
        })
        uName.addEventListener("blur", function() {
            if (regName.test(this.value)) { 
                this.parentNode.lastChild.style.display = 'none';
                let number;
                let postData1 = qs.stringify({
                    uName: this.value
                })
                axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/user/checkUserName',
                    data: postData1
                }).then(response => {
                    number = response.data;
                    console.log(number);
                    if (number != 0) {
                        this.parentNode.lastChild.innerHTML = '用户名已被注册';
                        this.parentNode.lastChild.style.display = 'block';
                        submitRegistered.style.pointerEvents = 'none';
                    } else {
                        submitRegistered.style.pointerEvents = 'auto';
                    }
                }).catch(error => {
                    console.log(error);
                })
                submitRegistered.style.pointerEvents = 'auto';
            } else {
                this.parentNode.lastChild.style.display = 'block'
                submitRegistered.style.pointerEvents = 'none';
            }
        })
        uPsd.addEventListener("blur", function() {
            if (regPsd.test(this.value)) { 
                this.parentNode.lastChild.style.display = 'none'
            } else {
                this.parentNode.lastChild.style.display = 'block'
            }
        })

        uPsd2.addEventListener("blur", function() {
            if (this.value == uPsd.value) {
                this.parentNode.lastChild.style.display = 'none'
                submitRegistered.style.pointerEvents = 'auto';
            } else {
                this.parentNode.lastChild.style.display = 'block'
                submitRegistered.style.pointerEvents = 'none';
            }
        })
    }
}
</script>

<style>
.registered-area {
    position: relative;
    margin-top: 20px;
    height: 520px;
    border: 1px solid rgb(197, 216, 182);
}

h3 {
    padding-left: 10px;
    height: 40px;
    line-height: 40px;
    background-color: rgb(197, 216, 182);
}

.registered-area-closed {
    position: absolute;
    top: 0;
    right: 0;
    margin-right: -12px;
    margin-top: -12px;
    width: 25px;
    height: 25px;
    border-radius: 50%;
    font-size: 18px;
    text-align: center;
    line-height: 25px;
    cursor: pointer;
    /* background-color: pink; */
    border: 1px solid green;
}

.registered-content {
    margin: 40px auto;
    width: 600px;
    height: 420px;
    /* background-color: pink; */
}

.registered-content ul {
    display: flex;
    flex-direction: column;
    justify-content: left;
}

.registered-content ul li {
    display: flex;
    margin-bottom: 30px;
}

.registered-content div {
    width: 100px;
    height: 36px;
    line-height: 36px;
    text-align: right;
    color: rgb(21, 85, 21);
    /* background-color: antiquewhite; */
}

.registered-content input {
    padding-left: 8px;
    width: 238px;
    height: 34px;
    border: 1px solid rgb(197, 216, 182);
}

.registered-content span {
    display: none;
    margin-left: 15px;
    height: 34px;
    line-height: 34px;
    color: red;
}

.registered-content .registered-content-btn {
    margin-left: 100px;
    width: 200px;
    height: 40px;
    color: white;
    text-align: center;
    line-height: 40px;
    font-size: 18px;
    background-color: rgb(60, 131, 60);
    cursor: pointer;
    /* pointer-events: none; */
}
</style>