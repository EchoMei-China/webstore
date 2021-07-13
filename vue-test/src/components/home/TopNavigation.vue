<template>
  <div class="top-bar">
    <slot>
      <div class="login-top-left" v-if="userNickName == null">
        <div class="top-left">
          <div class="shoten">SHOTEN~欢迎选购</div>
          <div><a href="javascript:;" @click="_login()">请登录</a></div>
          <div>
            <a href="javascript:;" @click="_registered()">免费注册</a>
          </div>
        </div>
        <div class="top-right">
          <div><a href="javascript:;" @click="_nologin()">我的订单</a></div>
          <div>
            <a href="javascript:;" @click="_nologin()">我的购物车</a>
          </div>
          <div><a href="javascript:;" @click="_nologin()">我的钱包</a></div>
          <div><a href="javascript:;" @click="_nologin()">我的地址</a></div>
          <div><a href="javascript:;" @click="_nologin()">我的主页</a></div>
        </div>
      </div>

      <div class="login-top-left-two" v-else>
        <div class="top-left">
          <div class="shoten">SHOTEN~欢迎选购</div>
          <div>
            <a href="javascript:;" :user="userNickName"> {{ userNickName }} </a>
          </div>
        </div>
        <div class="top-right">
          <div><a href="javascript:;" @click="_order(userNickName)">我的订单</a></div>
          <div>
            <a href="javascript:;" @click="_shoppingcart(userNickName)"
              >我的购物车</a
            >
          </div>
          <div><a href="javascript:;" @click="_wallet(userNickName)">我的钱包</a></div>
          <div>
            <a href="javascript:;" @click="_address(userNickName)">我的地址</a>
          </div>
          <div><a href="javascript:;" @click="_user(userId)">我的主页</a></div>
        </div>
      </div>

      <!-- 登录模态框 -->
      <div class="loginbox">
        <div class="loginbox-close" @click="_closeIoginBox()">X</div>
        <div class="loginbox-top">
          <ul class="login-ul">
            <li class="logincurrent">&nbsp;&nbsp;密码登录&nbsp;&nbsp;</li>
            <!-- <li>&nbsp;&nbsp;短信登录&nbsp;&nbsp;</li> -->
          </ul>
        </div>
        <div class="login-bottom">
          <div class="login-row">
            <div class="login-left">用户名</div>
            <input
              type="text"
              v-model="userIpt"
              placeholder="请输入用户名/手机号"
            />
          </div>
          <div class="login-row">
            <div class="login-left">密码</div>
            <input type="password" v-model="userPsd" placeholder="请输入密码" />
          </div>
          <div class="login-row">
            <div
              class="login-button"
              @click="_verifyUsernameLogin(userIpt, userPsd)"
            >
              立即登录
            </div>
          </div>
        </div>
        <!-- <div class="login-bottom" style="display: none">
          <div class="login-row">
            <div class="login-left">手机号</div>
            <input type="text" v-model="userPhone" placeholder="+86" />
          </div>
          <div class="login-row">
            <div class="login-left">验证码</div>
            <input
              class="verification-ipt"
              type="text"
              v-model="verificationCode"
              placeholder="验证码"
            />
            <div class="login-verification">获取验证码</div>
          </div>
          <div class="login-row">
            <div class="login-button">立即登录</div>
          </div>
        </div> -->
      </div>
    </slot>
  </div>
</template>

<script>
import qs from 'qs'
import axios from 'axios'

export default {
  name: "TopNavigation",

  inject:['reload'], //调用刷新

  data() {
    return {
      userIpt: "",
      userPsd: "",
      userPhone: "",
      verificationCode: "",
      userNickName: null,
      info: "",
      user: [],
      userId: null,
    };
  },

  created() {
    let user_id = sessionStorage.getItem('userId');
    let postData6 = this.qs.stringify({
      user_id: user_id
    })
    this.axios({
      method: 'post',
      url: '/ssm_project_war_exploded/user/obtainUserById',
      data: postData6
    }).then((response) => {
      let nickname = response.data.user.userNickname;
      sessionStorage.setItem("userNickName", nickname);
      this.userNickName = nickname;
      // this.reload();
    }).catch((error) => {
      console.log(error);
    });
  },

  methods: {
    /**
     * 点击登录按钮
     */
    _login() {
      let loginbox = document.querySelector(".loginbox");
      loginbox.style.display = "block";
    },

    /**
     * 退出登录
     */
    _clearSession() {
      sessionStorage.removeItem("userNickName");
      sessionStorage.removeItem('userId');
      this.userNickName = null;
      this.userId = null;
      this.reload();
    },

    /**
     * 验证用户名/手机号登录
     */
    _verifyUsernameLogin(iptVal1, iptVal2) {
      let postData = this.qs.stringify({
        userIpt: iptVal1,
        userPsd: iptVal2,
      });
      this.axios({
        method: "post",
        url: "/ssm_project_war_exploded/user/obtainLoginMessage",
        data: postData,
      })
        .then((response) => {
          console.log(response.data.user);
          this.info = response.data.msg;
          this.user = response.data.user;
          if (this.info === "success") {
            this.$message.success("登录成功");
            // this.userNickName = this.user.userNickname;
            this.userId = this.user.userId;
            // sessionStorage.setItem("userNickName", this.userNickName);
            sessionStorage.setItem("userId", this.userId);
            let postData6 = qs.stringify({
              user_id: this.userId
            })
            axios({
              method: 'post',
              url: '/ssm_project_war_exploded/user/obtainUserById',
              data: postData6
            }).then((response) => {
              let nickname = response.data.user.userNickname;
              sessionStorage.setItem("userNickName", nickname);
              this.userNickName = nickname;
              this.reload();
            }).catch((error) => {
              console.log(error);
            });
            let loginbox = document.querySelector(".loginbox");
            loginbox.style.display = "none";
          } else {
            this.$message.warning("用户名或者密码错误");
          }
        })
        .catch((error) => {
          console.log(error);
          this.$message.warning("用户名或者密码错误");
        });
    },

    /**
     * 点击登录界面的关闭按钮
     */
    _closeIoginBox() {
      let loginbox = document.querySelector(".loginbox");
      loginbox.style.display = "none";
      (this.userIpt = ""), (this.userPsd = "");
    },

    /**
     * 点击免费注册按钮
     */
    _registered() {
      this.$router.push("/registered");
    },

    /**
     * 登陆前点击按钮
     */
    _nologin() {
      this._login();
    },

    /**
     * 登陆后点击购物车按钮
     */
    _shoppingcart(val) {
      let routeUrl = this.$router.resolve({
        path: "/shoppingcart",
        query: {
          user_nickname: val,
        },
      });
      window.open(routeUrl.href, "_blank");
    },

    /**
     * 登陆后点击地址按钮
     */
    _address(val) {
      let routeUrl = this.$router.resolve({
        path: "/address",
        query: {
          user_nickname: val,
        },
      });
      window.open(routeUrl.href, "_blank");
    },

    /**
     * 登陆后点击钱包按钮
     */
    _wallet(val) {
      let routeUrl = this.$router.resolve({
        path: "/wallet",
        query: {
          user_nickname: val,
        },
      });
      window.open(routeUrl.href, "_blank");
    },

    /**
     * 登陆后点击订单按钮
     */
    _order(val) {
      let routeUrl = this.$router.resolve({
        path: "/order",
        query: {
          user_nickname: val,
        },
      });
      window.open(routeUrl.href, "_blank");
    },

    /**
     * 我的主页按钮
     */
    _user(val) {
      let routeUrl = this.$router.resolve({
        path: '/user',
        query: {
            user_id: val
        }
      });
      window.open(routeUrl.href, '_blank'); 
    }
  },

  mounted() {
    let loginUl = document.querySelector(".login-ul");
    let lis = loginUl.querySelectorAll("li");
    let loginBottoms = document.querySelectorAll(".login-bottom");
    for (var i = 0; i < lis.length; i++) {
      lis[i].setAttribute("index", i);
      lis[i].addEventListener("click", function () {
        for (var i = 0; i < lis.length; i++) {
          lis[i].className = "";
        }
        this.className = "logincurrent";
        let index = this.getAttribute("index");
        for (var i = 0; i < loginBottoms.length; i++) {
          loginBottoms[i].style.display = "none";
        }
        loginBottoms[index].style.display = "block";
      });
    }
  },
};
</script>

<style coped>
.top-bar {
  position: relative;
  display: flex;
  position: relative;
  height: 32px;
  background-color: rgb(207, 243, 200);
  line-height: 32px;
}

.top-left,
.top-left-logined {
  display: flex;
  position: absolute;
  top: 0;
  left: 0;
}

.shoten {
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
    "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
}

.top-left div,
.top-left-logined div {
  margin-left: 15px;
}

.top-right {
  display: flex;
  position: absolute;
  top: 0;
  right: 0;
}

.top-right div {
  margin-right: 20px;
}

.top-right div:nth-child(1)::before {
  display: inline-block;
  margin-right: 2px;
  content: "";
  font-family: "icomoon";
  font-size: 12px;
}

.top-right div:nth-child(2)::before {
  display: inline-block;
  margin-right: 2px;
  content: "";
  font-family: "icomoon";
  font-size: 12px;
}

.top-right div:nth-child(3)::before {
  display: inline-block;
  margin-right: 2px;
  content: "";
  font-family: "icomoon";
  font-size: 12px;
}

.top-right div:nth-child(4)::before {
  display: inline-block;
  margin-right: 2px;
  content: "";
  font-family: "icomoon";
  font-size: 12px;
}

.top-right div:nth-child(5)::before {
  display: inline-block;
  margin-right: 2px;
  content: "";
  font-family: "icomoon";
  font-size: 12px;
}

.top-right div:nth-child(6)::before {
  display: inline-block;
  margin-right: 2px;
  content: "";
  font-family: "icomoon";
  font-size: 12px;
}

.loginbox {
  display: none;
  position: absolute;
  top: 150px;
  left: 50%;
  margin-left: -180px;
  width: 360px;
  height: 330px;
  background-color: rgba(223, 220, 220, 0.817);
  border-radius: 15px;
  z-index: 999;
}

.loginbox-close {
  position: absolute;
  right: -12px;
  top: -12px;
  width: 25px;
  height: 25px;
  border-radius: 50%;
  color: rgb(28, 66, 20);
  font-size: 18px;
  text-align: center;
  line-height: 25px;
  border: 1px solid rgb(28, 66, 20);
  cursor: pointer;
  background-color: rgba(223, 220, 220, 0.817);
}

.loginbox-top {
  margin-top: 20px;
  /* background-color: blueviolet; */
}

.login-bottom {
  margin: 20px 20px;
  height: 250px;
  /* background-color: rgba(250, 255, 249, 0.824); */
}

.login-ul {
  display: flex;
  height: 50px;
  color: rgb(28, 66, 20);
  line-height: 50px;
  text-align: center;
  font-size: 16px;
  font-weight: 800;
  cursor: pointer;
}

.login-ul li {
  flex: 1;
}

.login-ul .logincurrent {
  text-decoration: underline;
  /* border-bottom: 1px solid black; */
}

.login-row {
  display: flex;
  margin-bottom: 35px;
  height: 36px;
  line-height: 36px;
  /* background-color: pink; */
}

.login-left {
  padding-left: 20px;
  width: 70px;
  color: rgb(28, 66, 20);
  font-size: 14px;
  text-align: left;
}

.login-row input {
  padding-left: 10px;
  width: 198px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

.login-button {
  margin: 0 auto;
  width: 260px;
  height: 36px;
  color: rgba(255, 255, 255, 0.954);
  text-align: center;
  line-height: 36px;
  font-size: 16px;
  font-weight: 700;
  background-color: rgb(28, 66, 20);
  border-radius: 8px;
}

.login-row .verification-ipt {
  width: 80px;
  border-radius: 8px 0 0 8px;
}

.login-verification {
  width: 118px;
  font-size: 14px;
  text-align: center;
  line-height: 36px;
  border: 1px solid #ccc;
  border-radius: 0 8px 8px 0;
  background-color: white;
  color: rgb(28, 66, 20);
  cursor: pointer;
}
</style>