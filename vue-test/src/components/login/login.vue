<template>
  <div class="login">
    <el-form
      class="login-form"
      label-position="left"
      label-width="80px"
      :model="formdata"
      :rules="loginFormRules"
      ref="loginFormRef"
    >
      <h2>欢迎登录</h2>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="formdata.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="formdata.password" show-password></el-input>
      </el-form-item>
      <el-button type="success" plain class="login-btn" @click="handleLogin()"
        >登录</el-button
      >
    </el-form>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      formdata: {
        username: "",
        password: "",
      },
      msg: "",
      info: "",
      // 验证用户名密码是否合法
      loginFormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在3-10个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在6-15个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    handleLogin() {
      let postData = this.qs.stringify({
        aname: this.formdata.username,
        password: this.formdata.password,
      });
      this.axios({
        method: "post",
        url: "/ssm_project_war_exploded/admin/login",
        data: postData,
      })
        .then((response) => {
          this.info = response.data.msg;
          if (this.info === "success") {
            this.$router.push({
              path: '/admin',
              query: {
                username: this.formdata.username
              }
            });
            // window.open(routeUrl.href, '_blank');
            this.$message.success("登录成功");
          } else {
            this.$message.warning("用户名或者密码错误");
          }
        })
        .catch((error) => {
      console.log(error);
      this.$message.warning("用户名或者密码错误");
      });
    },
  },
};
</script>

<style>
.login {
  display: flex;
  height: 500px;
  justify-content: center;
  align-items: center;
}

.login-form {
  width: 600px;
  background-color: rgb(184, 243, 184);
  color: rgb(49, 82, 7);
  border-radius: 5px;
  padding: 30px;
}

.login-btn {
  width: 100%;
}
</style>