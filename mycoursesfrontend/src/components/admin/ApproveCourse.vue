<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>审批课程</span>
    </div>
    <course-list v-bind:courseList="courseList"></course-list>
  </el-card>
</template>

<script>
  import CourseList from "../util/CourseList";

  export default {
    name: "ApproveCourse",
    components: {CourseList},
    data() {
      return {
        courseList: {}
      }
    },
    beforeCreate: function () {
      this.axios.post("/backend/getToBeApproveCourse")
        .then((res) => {
            if(res.data.code!==0){
              this.$Message.error("出错了");
            }else{
              this.courseList=res.data.data;
            }
        })
    }
  }
</script>

<style scoped>

</style>
