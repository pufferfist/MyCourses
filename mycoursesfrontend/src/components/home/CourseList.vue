<template>
    <div>
      <course-item v-for="courseItem in courseList" v-bind:course="courseItem"></course-item>
    </div>
</template>

<script>
    import CourseItem from "./CourseItem";
    export default {
        name: "CourseList",
      components: {CourseItem},
      props:["courseList"]
    }
</script>

<style scoped>

</style>
