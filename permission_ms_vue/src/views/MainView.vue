<template>
  <el-container style="height: 952px; border: 1px solid #eee">
    <el-aside width="250px" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>后台管理系统</template>
          <el-submenu v-for="(permission,index) in permissions" :key="index" :index="permission.id + '' ">
            <template slot="title">{{ permission.name }}</template>
            <el-menu-item v-for="child in permission.childPermissions" v-if="child.url">
              <router-link :to="child.url">{{ child.name }}</router-link>
            </el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>{{ username }}</span>
      </el-header>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "MainView",
  data() {
    return {
      username: "",
      permissions: [], //权限菜单
      isContentVisible: false,
    }
  },
  mounted() {
    this.username = localStorage.getItem("username");
    this.axios.get("user-permission-menus/" + this.username)
        .then(result => {
          if (result.data.code == 200) {
            this.permissions = result.data.data;
          }
        })
  },
}
</script>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>