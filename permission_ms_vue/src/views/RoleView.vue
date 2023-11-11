<template>
  <div>
    <h1>角色管理</h1>
    <!--添加角色按钮-->
    <el-button type="primary" style="float: left" @click="dialogFormVisible = true">添加角色</el-button>
    <!--添加角色弹出对话框-->
    <el-dialog title="角色信息" :visible.sync="dialogFormVisible">
      <el-form :model="role">
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="role.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述" :label-width="formLabelWidth">
          <el-input v-model="role.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="站点ID" :label-width="formLabelWidth">
          <el-input v-model="role.siteId" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="empty">取 消</el-button>
        <el-button type="primary" @click="saveStudent">确 定</el-button>
      </div>
    </el-dialog>
    <!--权限设置弹出对话框-->
    <el-dialog title="权限设置" :visible.sync="dialogTreeVisible">
      <el-tree
          :data="permissions"
          show-checkbox
          default-expand-all
          node-key="id"
          ref="tree"
          highlight-current
          :props="defaultProps">
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTreeVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateRolePermissions">确 定</el-button>
      </div>
    </el-dialog>
    <!--列表-->
    <el-table
        :data="roles"
        style="width: 100%">
      <el-table-column
          label="ID"
          width="auto">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="名称"
          width="auto">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="描述"
          width="auto">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.description }}</span>
        </template>
      </el-table-column>
      <el-table-column
          label="站点id"
          width="auto">
        <template slot-scope="scope">
          <span style="margin-left: 10px">{{ scope.row.siteId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">

          <el-button
              size="mini"
              @click="showRole(scope.row)">编辑
          </el-button>
          <el-button
              slot="reference"
              size="mini"
              type="danger"
              @click="open(scope.row.id)">删除
          </el-button>
          <el-button size="mini" @click="showPermssionTree(scope.row.id)">设置权限</el-button>
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
        @current-change="loadRoles">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "RoleView",
  data() {
    return {
      roles: null,               //角色集合
      permissions: null,         //所有权限集合
      current: 1,               //当前页数
      size: 10,                  //每页显示条数
      pages: 0,                 //分页数量
      role: {                   //当前角色
        id: "0",
        name: "",
        description: "",
        siteid: ""
      },
      dialogTreeVisible: false, //权限树对话框的隐藏和显示
      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      defaultProps: {
        children: 'childPermissions',   //子节点集合
        label: 'name'                   //节点名
      },
      roleId: 0,//当前选中的角色id
    }
  },

  methods: {
    //分页查询 权限树
    loadRoles(current) {
      this.current = current;
      //axios调用分页查询接口
      this.axios.get("rolesPage?current=" + this.current + "&size=" + this.size)
          .then(result => {  //请求成功的回调
            console.log("role", result);
            this.roles = result.data.data.records;
            this.pages = result.data.data.pages;
          });

      //查询权限树
      this.axios.get("permission-tree")
          .then(result => {
            console.log("permssion", result);
            this.permissions = result.data.data;
          });
    },

    //显示角色
    showRole(role) {
      this.role = JSON.parse(JSON.stringify(role));
      this.dialogFormVisible = true
    },

    //显示角色设置权限对话框
    showPermssionTree(roleId) {
      this.dialogTreeVisible = true;
      this.roleId = roleId;
      //根据角色id查询权限id
      this.axios.get("permission-ids/" + roleId)
          .then(result => {
            console.log(result.data)
            if (result.data.code == 200) {
              //选中角色的权限
              this.$refs.tree.setCheckedKeys(result.data.data)
            }
          })
    },

    //更新角色权限
    updateRolePermissions() {
      console.log(this.$refs.tree.getCheckedKeys());
      this.axios.put("role-permissions", "roleId=" + this.roleId + "&permissionIds=" + this.$refs.tree.getCheckedKeys())
          .then(result => {
            console.log(result.data)
          })
      this.dialogTreeVisible = false
    },

    //删除
    deleteStudent(id) {
      this.axios.delete("role/" + id)
          .then(result => {
            console.log(result.data);
            if (result.data.code == 200) {
              this.loadRoles(this.current);
            }
          });
    },

    //保存角色  添加 更新
    saveStudent() {
      this.dialogFormVisible = false
      //通过学生id是否是0，判断添加还是更新
      if (this.role.id == 0) {
        //添加
        this.axios.post("role", this.role)
            .then(result => {
              console.log(result.data);
              if (result.data.code == 200) {
                this.loadRoles(this.current);
              }
              this.$message({
                type: 'success',
                message: '添加成功！'
              })
            })

      } else {

        //更新
        this.axios.put("role", this.role)
            .then(result => {
              console.log(result.data);
              if (result.data.code == 200) {
                this.loadRoles(this.current);
              }
              this.$message({
                type: 'success',
                message: '修改成功！'
              });
            })
      }
      this.role = {
        id: "0",
        name: "",
        description: "",
        siteid: ""
      };
    },

    //表单置空
    empty() {
      this.role = {
        id: "0",
        name: "",
        description: "",
        siteid: ""
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
        this.deleteStudent(id);
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
    this.loadRoles(1);
  }
}
</script>

<style scoped>

</style>