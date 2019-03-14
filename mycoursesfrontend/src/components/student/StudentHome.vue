<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>课程概览</span>
    </div>
    <publish-list v-bind:publishList="publishList"></publish-list>
  </el-card>
</template>

<script>
  import PublishList from "../util/PublishList";

  export default {
    name: "StudentHome",
    components: {PublishList},
    data() {
      return {
        publishList: {},
      }
    },
    beforeCreate: function () {
      this.axios.post("/backend/electivedCourseList")
        .then((res) => {
          this.publishList = res.data.data;
        })
    }
  }
</script>

<style scoped>

</style>
