<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>{{teacher.name}}</span>
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
      name: "TeacherPublishList",
      components: {PublishList},
      data() {
        return {
          publishList: {},
          teacher:{}
        }
      },
      beforeCreate: function () {
        this.axios.post("/backend/teacherPublishList",{teacherId:this.$route.params.teacherId})
          .then((res) => {
            this.publishList = res.data.data;
          });
        this.axios.post("/backend/user/info",{username:this.$route.params.teacherId})
          .then((res) => {
            this.teacher = res.data.data;
          });
      }
    }
</script>

<style scoped>

</style>
