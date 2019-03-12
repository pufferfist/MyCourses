<template>
  <el-container>
    <el-header class="tr">
      <span class="fl f2">
        MyCourses
      </span>
      <el-dropdown trigger="click">
          <span class="pointer f3">
            {{id}}
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>
            <router-link to="/userInfo">
              个人资料
            </router-link>
          </el-dropdown-item>
          <el-dropdown-item @click.native="logout">登出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>
    <el-main>
      <el-row>
        <el-col :span="20" :offset="2">
          <router-view/>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
  export default {
    name: "FatherPage",
    methods:{
      logout(){
        this.axios.post("/backend/user/logout")
          .then((res)=>{
            if(res.data.code===0){
              this.$router.push("/login");
            }
          });
      }
    },
    computed:{
      id(){
        return this.$store.state.id;
      }
    }
  }
</script>

<style scoped>
  .pull-left{
    left: 0;
    right: auto;
  }
</style>
