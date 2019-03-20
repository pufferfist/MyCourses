<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>本学期课程列表</span>
    </div>
    <publish-list v-bind:publishList="publishList"></publish-list>
  </el-card>
</template>

<script>
  import PublishList from "../util/PublishList";

  export default {
    name: "allPublishList",
    components: {PublishList},
    data() {
      return {
        publishList: {},
      }
    },
    beforeCreate: function () {
      this.axios.post("/backend/allPublishList")
        .then((res) => {
          this.publishList = res.data.data;
        })
    }
  }
</script>

<style scoped>

</style>
