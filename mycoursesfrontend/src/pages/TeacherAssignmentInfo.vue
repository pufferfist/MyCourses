<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16">
        <el-row>
          <assignment-info v-bind:assignment="assignment"></assignment-info>
        </el-row>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <button-item v-on:click="downloadAssignment">下载作业</button-item>
          <button-item v-on:click="gradeDialog=true">发布成绩</button-item>
          <button-item v-if="assignment.gradesFilePath!==null" v-on:click="showGrade">查看成绩</button-item>
          <button-item v-on:click="goBack()">返回</button-item>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="上传成绩" :visible.sync="this.gradeDialog">
      <el-form>
        <el-form-item label="上传文件">
          <el-upload
            class="upload-demo"
            drag
            ref="upload"
            action="whatever"
            :on-remove="handleRemove"
            :on-change="handleChange"
            :auto-upload="false">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="gradeDialog = false">取 消</el-button>
        <el-button type="primary" @click="publishAssignmentGrades">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import AssignmentInfo from "../components/util/AssignmentInfo";
    import ButtonItem from "../components/util/ButtonItem";
    export default {
        name: "TeacherAssignmentInfo",
      components: {ButtonItem, AssignmentInfo},
      data(){
          return{
            gradeDialog:false,
            file:{}
          }
      },
      computed: {
        assignment() {
          return this.$store.state.assignment;
        }
      },
      methods:{
        downloadAssignment(){

        },
        publishAssignmentGrades(){
          let formData=new FormData();
          formData.append("assignmentId",this.assignment.id);
          formData.append("file",this.file);
          this.axios.post("/backend/publishAssignmentGrades",formData)
            .then(res=>{
              if (res.data.code===0){
                this.gradeDialog = false;
                this.$Message.success({
                  content: "上传成功",
                  duration: 1
                });
              }else{
                this.$Message.error({
                  content: res.data.msg,
                  duration: 1
                });
              }
            })
        },
        handleChange(file, fileList) {
          this.file = file.raw;
          if (fileList.length > 1) {
            fileList.shift()
          }
        },
        handleRemove(file, fileList){
          this.file={}
        },
      }
    }
</script>

<style scoped>

</style>
