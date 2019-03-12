<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>审批课程发布</span>
    </div>
    <publish-list v-bind:courseList="publishList"
                 v-bind:type="'standard'"></publish-list>

  </el-card>
</template>

<script>
    import PublishList from "../util/PublishList";
    export default {
        name: "ApprovePublish",
      components: {PublishList},
      data() {
        return {
          publishList: {}
        }
      },
      beforeCreate: function () {
        this.axios.post("/backend/getToBeApprovePublish")
          .then((res) => {
            if(res.data.code!==0){
              this.$Message.error("出错了");
            }else{
              this.publishList=res.data.data;
            }
          })
      }
    }
</script>

<style scoped>

</style>
