<template>
  <el-row :gutter="20">
    <el-col :span="16" >
      <el-card shadow="always">
        <div slot="header" class="cf">
          <span>创建课程</span>
        </div>
        <el-input></el-input>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
    export default {
        name: "createCourse"
    }
</script>

<style scoped>

</style>
