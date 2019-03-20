<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <el-row>
        <el-card shadow="always">
          <div slot="header" class="cf f3 tl">
            <span>个人信息</span>
          </div>
          <el-form>
            <el-form-item label="账号名">
              <span>{{user.id}}</span>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="name"></el-input>
            </el-form-item>
            <el-form-item label="学号" v-if="user.type==='STUDENT'">
              <el-input v-model="studentNumber"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <span>{{user.email}}</span>
            </el-form-item>
            <el-form-item label="注册时间">
              <span>{{user.registerTime}}</span>
            </el-form-item>
            <el-form-item label="注册时间">
              <el-button @click="modify">确认修改</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-row>

    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <button-item v-if="user.type==='STUDENT'" v-on:click="deleteUser">注销帐号</button-item>
        <button-item v-on:click="goBack()">返回</button-item>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
  import ButtonItem from "../components/util/ButtonItem";

  export default {
    name: "UserInfoPage",
    components: {ButtonItem},
    data() {
      return {
        studentNumber: "",
        name: ""
      }
    },
    computed: {
      user() {
        return this.$store.state.user;
      }
    },
    beforeMount(){
      this.studentNumber=this.$store.state.user.studentNumber;
      this.name=this.$store.state.user.name;
    },
    methods: {
      deleteUser() {
        this.axios.post("/backend/user/delete")
          .then(res => {
            if (res.data.code === 0) {
              this.$message.success("注销成功");
              this.$router.push("/login");
            }
          })
      },
      modify(){
        let form={studentNumber:this.studentNumber,name:this.name};
        this.axios.post("/backend/user/modify",form)
          .then(res=>{
            if (res.data.code === 0) {
              this.$message.success("修改成功");
              this.axios.post("/backend/user/info")
                .then(res=>{
                  if(res.data.code===0){
                    this.$store.commit("set",{type:"user",data:res.data.data})
                  }
                })
            }else {
              this.$message.error("修改失败");
            }
          })
      }
    }

  }
</script>

<style scoped>

</style>
