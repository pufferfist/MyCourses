<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <router-view>
      </router-view>
    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <router-button v-bind:path="'/admin/approveCourse'">审批课程创建</router-button>
        <router-button v-bind:path="'/admin/approvePublish'">审批课程发布</router-button>
        <router-button v-bind:path="'/admin/cutOff'">选课截止</router-button>
        <router-button v-bind:path="'/admin/statistics'">查看统计</router-button>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
    import RouterButton from "../components/util/RouterButton";
    export default {
        name: "Admin",
      components: {RouterButton}
    }
</script>

<style scoped>

</style>
