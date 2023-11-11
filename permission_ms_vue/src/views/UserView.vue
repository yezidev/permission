<template>
  <div>
    <h1>用户管理</h1>
    <!--添加角色按钮-->
    <!--    <el-button type="primary" style="float: left" @click="dialogFormVisible = true">添加用户</el-button>-->
    <!--添加角色弹出对话框-->
    <el-dialog title="角色信息" :visible.sync="dialogFormVisible">
      <el-form :model="user">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="user.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名" :label-width="formLabelWidth">
          <el-input v-model="user.realname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" :label-width="formLabelWidth">
          <el-input v-model="user.telephone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="empty">取 消</el-button>
        <el-button type="primary" @click="saveUser">确 定</el-button>
      </div>
    </el-dialog>
    <!--角色设置弹出对话框-->
    <el-dialog title="角色设置" :visible.sync="dialogTreeVisible">
      <el-tree
          :data="roles"
          show-checkbox
          default-expand-all
          node-key="id"
          ref="tree"
          highlight-current
          :props="defaultProps">
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTreeVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUserRoles">确 定</el-button>
      </div>
    </el-dialog>
    <!--列表-->
    <el-table
        :data="users"
        style="width: 100%">
      <el-table-column
          label="ID"
          width="auto">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="用户名"
          width="auto">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column-->
      <!--          label="密码"-->
      <!--          width="auto">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span style="margin-left: 10px">{{ scope.row.password }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column
          label="真实姓名"
          width="auto">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.realname }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="电话号码"
          width="auto">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.telephone }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">

          <el-button
              size="mini"
              @click="showUser(scope.row)">编辑
          </el-button>
          <el-button
              slot="reference"
              size="mini"
              type="danger"
              @click="open(scope.row.id)">删除
          </el-button>
          <el-button size="mini" @click="showRoleTree(scope.row.id)">设置角色</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--elementUI分页标签-->
    <el-pagination
        background
        :current-page="current"
        :page-size="size"
        :pager-count="pages"
        layout="prev, pager, next"
        :total="size * pages"
        @current-change="loadUsers">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "UserView",
  data() {
    return {
      users: null,               //用户集合
      roles: null,         //所有角色集合
      current: 1,               //当前页数
      size: 10,                  //每页显示条数
      pages: 0,                 //分页数量
      user: {                   //当前用户
        id: "0",
        username: "",
        realname: "",
        telephone: ""
      },
      dialogTreeVisible: false, //权限树对话框的隐藏和显示
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      defaultProps: {
        label: 'name'                   //节点名
      },
      userId: 0,//当前用户id
    }
  },
  methods: {

    //分页查询
    loadUsers(current) {
      this.current = current;
      //axios调用分页查询接口
      this.axios.get("usersPage?current=" + this.current + "&size=" + this.size)
          .then(result => {  //请求成功的回调
            console.log("user", result);
            this.users = result.data.data.records;
            this.pages = result.data.data.pages;
          });

      //查询角色树
      this.axios.get("roles")
          .then(result => {
            console.log("role", result);
            this.roles = result.data.data;
          });
    },

    //显示用户
    showUser(user) {
      this.user = JSON.parse(JSON.stringify(user));
      this.dialogFormVisible = true
    },

    //显示角色设置权限对话框
    showRoleTree(userId) {
      this.dialogTreeVisible = true;
      this.userId = userId;
      //根据用户id查询角色id
      this.axios.get("role-ids/" + userId)
          .then(result => {
            console.log(result.data)
            if (result.data.code == 200) {
              //选中角色的权限
              this.$refs.tree.setCheckedKeys(result.data.data)
            }
          })
    },

    //更新用户角色
    updateUserRoles() {
      console.log(this.$refs.tree.getCheckedKeys());
      this.axios.put("user-roles", "userId=" + this.userId + "&roleIds=" + this.$refs.tree.getCheckedKeys())
          .then(result => {
            console.log(result.data)
          })
      this.dialogTreeVisible = false
    },

    //删除
    deleteUser(id) {
      this.axios.delete("user/" + id)
          .then(result => {
            console.log(result.data);
            if (result.data.code == 200) {
              this.loadRoles(this.current);
            }
          });
    },

    //保存用户
    saveUser() {
      this.dialogFormVisible = false
      //通过学生id是否是0，判断添加还是更新
      if (this.user.id == 0) {
        //添加
        this.axios.post("role", this.user)
            .then(result => {
              console.log(result.data);
              if (result.data.code == 200) {
                this.loadUsers(this.current);
              }
              this.$message({
                type: 'success',
                message: '添加成功！'
              })
            })

      } else {

        //更新
        this.axios.put("user", this.user)
            .then(result => {
              console.log(result.data);
              if (result.data.code == 200) {
                this.loadUsers(this.current);
              }
              this.$message({
                type: 'success',
                message: '修改成功！'
              });
            })
      }
      this.user = {
        id: "0",
        username: "",
        realname: "",
        telephone: ""
      };
    },

    //表单置空
    empty() {
      this.user = {
        id: "0",
        username: "",
        realname: "",
        telephone: ""
      };
      this.dialogFormVisible = false;
    },

    //删除提示
    open(id) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteUser(id);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },

  //挂载
  mounted() {
    this.loadUsers(1);
  }
}
</script>

<style scoped>

</style>