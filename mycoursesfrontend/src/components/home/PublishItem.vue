<template>
  <div class="pt2 pb2 ba-ns mb3">
    <router-link to="/publishInfo">{{publish.course.name}}</router-link>
    //这里考虑一下是否需要传publish的信息给父组件
  </div>
</template>

<script>
  export default {
    name: "PublishItem",
    props: ["publish"]
  }
</script>

<style scoped>

</style>
