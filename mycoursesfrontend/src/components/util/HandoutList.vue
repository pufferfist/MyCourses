<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>课件列表</span>
    </div>
    <a v-for="handout in handoutList"
       :key="handout.id"
       :href="'http://localhost:8088'+getPath(handout.filePath)"
       class="pv2 db">{{handout.name}}</a>
  </el-card>
</template>

<script>
  export default {
    name: "HandoutList",
    props: ["change"],
    data() {
      return {
        handoutList: {},
      }
    },
    beforeCreate: function () {
      let courseId = this.$store.state.course.id;
      this.axios.post("/backend/handoutList", {courseId: courseId})
        .then(res => {
          if (res.data.code === 0) {
            this.handoutList = res.data.data;
          } else {
            this.$message.error("获取课件列表出错");
          }
        })
    },
    methods: {
      getPath(serverPath) {
        let index = serverPath.indexOf("static") + 6;
        return serverPath.slice(index);
      }
    },
    watch: {
      change: function () {
        let courseId = this.$store.state.course.id;
        this.axios.post("/backend/handoutList", {courseId: courseId})
          .then(res => {
            if (res.data.code === 0) {
              this.handoutList = res.data.data;
            } else {
              this.$message.error("获取课件列表出错");
            }
          })
      }
    }

  }
</script>

<style scoped>

</style>
