<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16">
        <el-row>
          <publish-info v-bind:publish="publish"></publish-info>
        </el-row>
        <el-row class="pt3">
          <assignment-list v-bind:change="change"></assignment-list>
        </el-row>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <button-item v-on:click="homeworkDialog=true">发布作业</button-item>
          <button-item v-on:click="publishCourseGrades">发布成绩</button-item>
          <button-item v-on:click="groupEmail">群发邮件</button-item>
          <button-item v-on:click="goBack">返回</button-item>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="发布作业" :visible.sync="this.homeworkDialog">
      <el-form :model="homeworkForm">
        <el-form-item label="作业名称" >
          <el-input v-model="homeworkForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="作业描述">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2}"
            placeholder="请输入内容"
            v-model="homeworkForm.description">
          </el-input>
        </el-form-item>
        <el-form-item label="截止时间">
          <el-date-picker
            v-model="homeworkForm.deadline"
            type="date"
            placeholder="选择日期"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
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
        <el-button @click="homeworkDialog = false">取 消</el-button>
        <el-button type="primary" @click="publishAssignment">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import PublishInfo from "../components/util/PublishInfo";
  import ButtonItem from "../components/util/ButtonItem";
  import AssignmentList from "../components/util/AssignmentList";

  export default {
    name: "TeacherPublishInfo",
    components: {AssignmentList, ButtonItem, PublishInfo},
    data(){
      return{
        homeworkDialog:false,
        homeworkForm:{
          name:"",
          description:"",
          deadline:"",
          file:{}
        },
        change:false
      }
    },
    computed: {
      publish() {
        return this.$store.state.publish;
      }
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      handleChange(file, fileList) {
        this.homeworkForm.file = file.raw;
        if (fileList.length > 1) {
          fileList.shift()
        }
      },
      handleRemove(file, fileList){
        this.homeworkForm.file={}
      },
      publishAssignment(){
        let formData=new FormData();
        formData.append("publishId",this.publish.id);
        formData.append("name",this.homeworkForm.name);
        formData.append("description",this.homeworkForm.description);
        formData.append("deadline",this.homeworkForm.deadline);
        formData.append("file",this.homeworkForm.file);
        this.axios.post("/backend/publishAssignment", formData)
          .then(res=>{
            if (res.data.code === 0) {
              this.homeworkDialog = false;
              this.change = !this.change;
              this.$Message.success({
                content: "上传成功",
                duration: 1
              });
            } else {
              this.$message.error("上传出错");
            }
          })
      }
    }
  }
</script>

<style scoped>

</style>
