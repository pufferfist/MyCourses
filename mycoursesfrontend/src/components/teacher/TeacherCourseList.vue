<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>课程列表</span>
    </div>
    <course-list v-bind:course-list="courseList"></course-list>
  </el-card>
</template>

<script>
  import CourseList from "../util/CourseList";

  export default {
    name: "TeacherCourseList",
    components: {CourseList},
    data() {
      return {
        courseList: {},
      }
    },
    beforeCreate: function () {
      this.axios.post("/backend/courseList")
        .then((res) => {
          this.courseList = res.data.data;
        })
    }
  }
</script>

<style scoped>

</style>
