<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>{{course.name}}</span>
    </div>
    <el-form label-position="left" inline class="demo-table-expand tl">
      <el-form-item label="课程id">
        <span>{{course.id}}</span>
      </el-form-item>
      <el-form-item label="教师姓名">
        <span>{{course.teacher.name}}</span>
      </el-form-item>
      <el-form-item label="课程描述">
        <span>{{course.description}}</span>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
  export default {
    name: "CourseInfo",
    props: ["course"],
  }
</script>

<style scoped>


</style>
