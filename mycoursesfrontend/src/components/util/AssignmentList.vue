<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>作业</span>
    </div>
    <a v-for="assignment in assignmentList"
       :key="assignment.id"
       class="pv2 db"
       v-on:click="transfer(assignment)">{{assignment.name}}</a>
  </el-card>
</template>

<script>
  export default {
    name: "AssignmentList",
    props: ["change"],
    data() {
      return {
        assignmentList: {},
      }
    },
    methods: {
      transfer(assignment) {
        this.$store.commit('set', {type: "assignment", data: assignment});
        this.$router.push('/' + this.$store.state.type + '/assignmentInfo')
      }
    },
    beforeCreate: function () {
      let publishId = this.$store.state.publish.id;
      this.axios.post("/backend/assignmentList", {publishId: publishId})
        .then(res => {
          if (res.data.code === 0) {
            this.assignmentList = res.data.data;
          } else {
            this.$message.error("获取作业列表出错");
          }
        })
    },
    watch: {
      change: function () {
        let publishId = this.$store.state.publish.id;
        this.axios.post("/backend/assignmentList", {publishId: publishId})
          .then(res => {
            if (res.data.code === 0) {
              this.assignmentList = res.data.data;
            } else {
              this.$message.error("获取作业列表出错");
            }
          })
      }
    }
  }
</script>

<style scoped>

</style>
