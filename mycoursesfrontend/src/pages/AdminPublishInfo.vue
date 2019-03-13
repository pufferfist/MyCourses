<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <el-row>
        <publish-info v-bind:publish="publish"></publish-info>
      </el-row>
    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <button-item v-if="!publish.approved" v-on:click="approve">通过审批</button-item>
        <button-item v-if="publish.approved&&!publish.cutOffed" v-on:click="cutOff">选课截止</button-item>
        <button-item v-on:click="goBack">返回</button-item>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
    import PublishInfo from "../components/util/PublishInfo";
    import ButtonItem from "../components/util/ButtonItem";
    export default {
        name: "AdminPublishInfo",
      components: {ButtonItem, PublishInfo},
      computed:{
        publish(){
          return this.$store.state.publish;
        }
      },
      methods:{
        approve(){
          this.axios.post("/backend/approvePublish",{publishId:this.publish.id})
            .then(res=>{
              if(res.data.code===0){
                this.$message.success("审批成功");
                this.publish.approved=true
              }else {
                this.$message.warning("审批失败");
              }
            })
        },
        cutOff(){
          this.axios.post("/backend/cutOffElection",{publishId:this.publish.id})
            .then(res=>{
              if(res.data.code===0){
                this.$message.success("截止成功");
                this.publish.cutOffed=true
              }else {
                this.$message.warning("截止失败");
              }
            })
        },
        goBack(){
          this.$router.go(-1);
        }
      }
    }
</script>

<style scoped>

</style>
