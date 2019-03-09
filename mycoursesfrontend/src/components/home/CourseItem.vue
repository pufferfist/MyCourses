<template>
  <div class="pt2 pb2 ba-ns mb3">
    <router-link to="/courseInfo">{{course.name}}</router-link>
  </div>
</template>

<script>
  export default {
    name: "CourseItem",
    props: ["course"]
  }
</script>

<style scoped>

</style>
