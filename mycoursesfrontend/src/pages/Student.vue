<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <router-view>
      </router-view>
    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <router-button v-bind:path="'/student/home'">已选课程</router-button>
        <router-button v-bind:path="'/student/withdraw'">退选课程</router-button>
        <router-button v-bind:path="'/student/allPublishList'">所有课程</router-button>
        <!--<router-button v-bind:path="/statistics">查看统计</router-button>-->
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
  import RouterButton from "../components/util/RouterButton";

  export default {
    name: "StudentHome",
    components: {RouterButton},
  }
</script>

<style scoped>

</style>
