<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>{{publish.course.name}}</span>
    </div>
    <el-form label-position="left" inline class="demo-table-expand tl" label-width="120px">
      <el-form-item label="课程id">
        <span>{{publish.id}}</span>
      </el-form-item>
      <el-form-item label="教师姓名">
        <span>{{publish.course.teacher.name}}</span>
      </el-form-item>
      <el-form-item label="开课学期">
        <span>{{publish.semester}}</span>
      </el-form-item>
      <el-form-item label="上课时间">
        <span>{{classTime}}</span>
      </el-form-item>
      <el-form-item label="开课时间">
        <span>第{{publish.startWeek}}周</span>
      </el-form-item>
      <el-form-item label="课程时长">
        <span>{{publish.weekNumber}}周</span>
      </el-form-item>
      <el-form-item label="教室名">
        <span>{{publish.classroom}}</span>
      </el-form-item>
      <el-form-item label="课程最大人数">
        <span>{{publish.maxStudentNumber}}人</span>
      </el-form-item>
      <el-form-item label="当前选课人数">
        <span>{{publish.currentStudentNumber}}人</span>
      </el-form-item>
      <el-form-item label="班级号">
        <span>{{publish.classNumber}}班</span>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
    export default {
        name: "PublishInfo",
      props:["publish"],
      data(){
          return{
            weekdays:['周一','周二','周三','周四','周五','周六','周日']
          }
      },
      computed:{
          classTime(){
            let result=this.weekdays[this.publish.dayOfWeek];
            result+=this.publish.classOrder+'~';
            result+=(this.publish.classOrder+this.publish.classHours-1);
            result+='节';
            return result;
          }
      }
    }
</script>

<style scoped>

</style>
