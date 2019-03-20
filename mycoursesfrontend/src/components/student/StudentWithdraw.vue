<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>退选课程</span>
    </div>
    <el-card v-for="item in publishList"
             :key="item.id"
             v-if="item.publishList.length!==0"
             shadow="always"
             class="mt3">
      <div slot="header" class="cf f3 tl">
        <span>{{item.semester}}</span>
      </div>
      <publish-list v-bind:publishList="item.publishList"></publish-list>
    </el-card>
  </el-card>
</template>

<script>
    import PublishList from "../util/PublishList";
    export default {
        name: "StudentWithdraw",
      components: {PublishList},
      data() {
        return {
          publishList: {},
        }
      },
      beforeCreate: function () {
        this.axios.post("/backend/withDrawCourseList")
          .then((res) => {
            this.publishList = res.data.data;
          })
      }
    }
</script>

<style scoped>

</style>
