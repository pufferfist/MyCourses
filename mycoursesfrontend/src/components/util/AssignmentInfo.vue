<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>作业要求</span>
    </div>
    <el-form label-position="left"  class="demo-table-expand tl">
      <el-form-item label="作业说明">
        <a :href="'http://localhost:8088'+this.getPath(assignment.requirementFilePath)">{{assignment.name}}</a>
      </el-form-item>
      <el-form-item label="作业id">
        <span>{{assignment.id}}</span>
      </el-form-item>
      <el-form-item label="作业描述">
        <el-input
          type="textarea"
          :disabled="true"
          :autosize="{ minRows: 2}"
          v-model="assignment.description">
        </el-input>
      </el-form-item>
      <el-form-item label="截止时间">
        <span>{{assignment.deadLine}}</span>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
    export default {
        name: "AssignmentInfo",
      props:["assignment"]
    }
</script>

<style scoped>

</style>
