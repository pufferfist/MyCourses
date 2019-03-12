<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>课程概览</span>
    </div>
    <publish-list v-bind:publishList="publishList"
                  v-bind:type="'standard'"></publish-list>
  </el-card>
</template>

<script>
  import PublishList from "../util/PublishList";

  export default {
    name: "TeacherHome",
    components: {PublishList},
    data() {
      return {
        publishList: {},
      }
    },
    beforeCreate: function () {
      this.axios.post("/backend/publishList")
        .then((res) => {
          this.publishList = res.data.data;
        })
    }
  }
</script>

<style scoped>

</style>
