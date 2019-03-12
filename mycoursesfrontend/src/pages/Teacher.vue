<template>
  <el-row :gutter="20">
    <el-col :span="16">
        <router-view>
        </router-view>
    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <router-button v-bind:path="'/teacher/createCourse'">创建课程</router-button>
        <router-button v-bind:path="'/teacher/courseList'">发布课程</router-button>
        <!--<router-button v-bind:path="/statistics">查看统计</router-button>-->
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
  import PublishList from "../components/util/PublishList";
  import RouterButton from "../components/util/RouterButton";

  export default {
    name: "TeacherHome",
    components: {RouterButton, PublishList},
    data() {
      return {
      }
    },

  }
</script>

<style scoped>

</style>
