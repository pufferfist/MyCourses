<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <el-row>
        <course-info v-bind:course="course"></course-info>
      </el-row>
      <el-row class="pt3">
        <el-card>
          <div slot="header" class="cf f3 tl">
            <span>历史发布</span>
          </div>
          <semester-publish-list v-bind:publish-list="publishList"></semester-publish-list>
        </el-card>
      </el-row>
    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <button-item v-if="!course.approved" v-on:click="approve">通过审批</button-item>
        <button-item v-on:click="goBack()">返回</button-item>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
  import ButtonItem from "../components/util/ButtonItem";
  import CourseInfo from "../components/util/CourseInfo";
  import PublishList from "../components/util/PublishList";
  import SemesterPublishList from "../components/util/SemesterPublishList";

  export default {
    name: "AdminCourseInfo",
    components: {SemesterPublishList, PublishList, CourseInfo, ButtonItem},
    data(){
      return{
        publishList:[]
      }
    },
    beforeCreate: function () {
      this.axios.post("/backend/coursePublishList",{courseId:this.course.id})
        .then(res=>{
          if (res.data.code === 0) {
            this.publishList=res.data.data;
          }else {
            this.$message.error("获取发布列表错误");
          }
        })
    },
    computed: {
      course() {
        return this.$store.state.course;
      }
    },
    methods: {
      approve() {
        this.axios.post("/backend/approveCourse", {courseId: this.course.id})
          .then(res => {
            if (res.data.code === 0) {
              this.$message.success("审批成功");
              this.course.approved = true
            } else {
              this.$message.warning("审批失败");
            }
          })
      }
    }
  }
</script>

<style scoped>

</style>
