<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>成绩</span>
    </div>
    <el-table
      :data="gradeData"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="学号"
        sortable
        width="180">
      </el-table-column>
      <el-table-column
        prop="grade"
        label="成绩"
        sortable
        width="180">
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
  export default {
    name: "GradeTable",
    props: ["gradeData"]
  }
</script>

<style scoped>

</style>
