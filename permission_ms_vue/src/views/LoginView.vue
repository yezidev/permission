<template>
  <el-card class="box-card">
    <el-form ref="form" :model="user" label-width="80px">
      <el-form-item label="账号">
        <el-input v-model="user.username" type="text"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="user.password" type="password"></el-input>
      </el-form-item>
      <span>{{ message }}</span>
      <el-form-item>
        <el-button type="primary" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
export default {
  name: "LoginView",
  data() {
    return {
      message: "",//错误信息
      user: {//账号密码
        username: "",
        password: ""
      }
    }
  },
  methods: {
    login() {
      console.log(this.user);
      //发送请求
      this.axios.post("login", "username=" + this.user.username + "&password=" + this.user.password)
          .then(result => {
            console.log(result.data);
            //将token保存到localStorage
            if (result.data.code == 200) {
              localStorage.setItem("token", result.data.data);
              localStorage.setItem("username",this.user.username)
              //跳转主页面
              this.$router.push({path: '/main'})
            } else {
              this.message = result.data.message
            }
          });
    }
  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
  padding-right: 50px;
  padding-top: 30px;
  margin: 100px auto;
}
</style>