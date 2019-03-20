<template>
    <el-row :gutter="20">
      <el-col :span="16">
        <el-row>
          <publish-info v-bind:publish="publish"></publish-info>
        </el-row>
        <el-row class="pt3" v-if="electived">
          <assignment-list></assignment-list>
        </el-row>
        <el-row class="pt3">
          <grade-table v-if="showGradeTable"
                       v-bind:grade-data="gradeData"></grade-table>
        </el-row>
        <el-row class="pt3">
          <post-module v-if="postModule"></post-module>
        </el-row>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <button-item v-if="!electived&&!notCutoff" v-on:click="elective">选课</button-item>
          <div class="mt3 center" v-if="notCutoff">
            <el-button type="primary" round disabled>选课结果未公布</el-button>
          </div>
          <button-item v-if="electived&&publish.gradesFilePath!==null" v-on:click="showGrade">查看成绩</button-item>
          <button-item v-if="!postModule" v-on:click="postModule = true;showGradeTable=false">查看论坛</button-item>
          <button-item v-if="postModule" v-on:click="postModule = false">关闭论坛</button-item>
          <button-item v-if="electived" v-on:click="withdraw">退课</button-item>
          <button-item v-on:click="goBack()">返回</button-item>
        </el-card>
      </el-col>
    </el-row>
</template>

<script>
    import PublishInfo from "../components/util/PublishInfo";
    import AssignmentList from "../components/util/AssignmentList";
    import ButtonItem from "../components/util/ButtonItem";
    import GradeTable from "../components/util/GradeTable";
    import PostModule from "../components/util/PostModule";
    export default {
        name: "StudentPublishInfo",
      components: {PostModule, GradeTable, ButtonItem, AssignmentList, PublishInfo},
      data(){
        return{
          electived:false,
          notCutoff:false,
          gradeData: [],
          showGradeTable: false,
          postModule:false
        }
      },
      beforeMount(){
          this.axios.post("/backend/selected",{publishId:this.$store.state.publish.id})
            .then(res=>{
              if(res.data.code===5005){
                this.electived=false;
                this.notCutoff=false;
              }else if(res.data.code===5006){
                this.electived=false;
                this.notCutoff=true;
              }else if(res.data.code===0){
                this.electived=true;
                this.notCutoff=false;
              }
            })
      },
      computed: {
        publish() {
          return this.$store.state.publish;
        }
      },
      methods:{
        elective(){
          this.axios.post("/backend/electiveCourse",{publishId:this.publish.id})
            .then(res=>{
              if (res.data.code === 0) {
                this.$message.success("直选成功")
              }else if(res.data.code===1){
                this.$message.success("选课成功，请等待选课结果公布")
              }else if(res.data.code===7002){
                this.$message.error("已经选课，不要重复选择")
              }else if (res.data.code === 7001) {
                this.$message.error("达到人数上限，无法选课")
              }
            })
        },
        withdraw(){
          this.axios.post("/backend/withdrawCourse",{publishId:this.publish.id})
            .then(res=>{
              if (res.data.code===0){
                this.$message.success("退课成功");
                this.$router.go(-1);
              } else {
                this.$message.error("退课失败")
              }
            })
        },
        showGrade() {
          if (this.showGradeTable) {
            this.showGradeTable=false;
            return
          }
          this.axios.post("/backend/getCourseGrade", {publishId: this.publish.id})
            .then(res => {
              this.gradeData = this.processCSV(res.data);
              this.showGradeTable = true;
            })
        },
      },

    }
</script>

<style scoped>

</style>
