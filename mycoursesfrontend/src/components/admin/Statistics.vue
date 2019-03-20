<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>审批课程发布</span>
    </div>
    <el-form label-position="left" class="demo-table-expand tl" label-width="120px">
      <el-form-item label="教师总数">
        <span>{{info.teacherNum}}</span>
      </el-form-item>
      <el-form-item label="学生总数">
        <span>{{info.studentNum}}</span>
      </el-form-item>
      <el-form-item label="当前在线教师数">
        <span>{{info.currentTeacherNum}}</span>
      </el-form-item>
      <el-form-item label="当前在线学生数">
      <span>{{info.currentStudentNum}}</span>
    </el-form-item>
      <el-form-item label="课程总数">
        <span>{{info.courseNum}}</span>
      </el-form-item>
      <el-form-item label="发布学期总数">
        <span>{{info.publishNum}}</span>
      </el-form-item>
      <el-form-item label="当前学期">
        <span>{{info.currentSemester}}</span>
      </el-form-item>
      <el-form-item label="历史总访问人数">
        <span>{{info.historyVisitNum}}</span>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
  export default {
    name: "statistics",
    data() {
      return {
        info: {}
      }
    },
    beforeCreate(){
      this.axios.post("/backend/getStatistic")
        .then(res=>{
          this.info=res.data.data;
        })
    }
  }
</script>

<style scoped>

</style>
