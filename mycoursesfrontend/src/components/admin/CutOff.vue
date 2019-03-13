<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>选课截止</span>
    </div>
    <publish-list v-bind:publishList="publishList"></publish-list>
  </el-card>
</template>

<script>
    import PublishList from "../util/PublishList";
    export default {
        name: "CutOff",
      components: {PublishList},
      data() {
        return {
          publishList: {}
        }
      },
      beforeCreate: function () {
        this.axios.post("/backend/getToBeCutOffPublish")
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
