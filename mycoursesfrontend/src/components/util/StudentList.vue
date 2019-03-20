<template>
  <el-table
    :data="studentList"
    style="width: 100%">
    <el-table-column
      prop="studentNumber"
      label="学号"
      sortable
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="姓名"
      sortable
      width="180">
    </el-table-column>
  </el-table>
</template>

<script>
    export default {
        name: "StudentList",
      props:["studentList"]
    }
</script>

<style scoped>

</style>
