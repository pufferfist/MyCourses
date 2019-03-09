<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <el-card shadow="always">
        <publish-list v-bind:publishList="publishList"></publish-list>
      </el-card>
    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <button-item v-bind:path="/createCourse">创建课程</button-item>
        <button-item v-bind:path="/publishCourse">发布课程</button-item>
        <button-item v-bind:path="/statistics">查看统计</button-item>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
  import ButtonItem from "../components/home/ButtonItem";
  import PublishList from "../components/home/PublishList";

  export default {
    name: "TeacherHome",
    components: {PublishList, ButtonItem},
    data() {
      return {
        publishList: {}
      }
    },
    beforeCreate:function () {
      this.axios.post("/backend/publishList")
        .then((res)=>{
          this.publishList=res.data.data;
        })
    }
  }
</script>

<style scoped>

</style>
