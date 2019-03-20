<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16">
        <el-row>
          <course-info v-bind:course="course"></course-info>
        </el-row>
        <el-row class="pt3">
          <handout-list class="tl" v-bind:change="change"></handout-list>
        </el-row>
        <el-row class="pt3">
          <el-card>
            <div slot="header" class="cf f3 tl">
              <span>历史发布</span>
            </div>
            <semester-publish-list v-bind:publish-list="publishList"></semester-publish-list>
          </el-card>
        </el-row>
        <el-row class="pt3">
          <post-module v-if="postModule"></post-module>
        </el-row>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <router-button v-bind:path="'/teacher/createPublish'">发布新学期</router-button>
          <button-item v-on:click="dialogVisible = true">发布课件</button-item>
          <button-item v-if="!postModule" v-on:click="postModule = true">查看论坛</button-item>
          <button-item v-if="postModule" v-on:click="postModule = false">关闭论坛</button-item>
          <button-item v-on:click="goBack()">返回</button-item>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="发布课件" :visible.sync="this.dialogVisible">
    <el-form :model="handoutForm">
      <el-form-item label="作业名称" :label-width="formLabelWidth">
        <el-input v-model="handoutForm.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="上传文件" :label-width="formLabelWidth">
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
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="uploadHandout">确 定</el-button>
    </div>
  </el-dialog>
  </div>
</template>

<script>
  import ButtonItem from "../components/util/ButtonItem";
  import RouterButton from "../components/util/RouterButton";
  import PublishList from "../components/util/PublishList";
  import CourseInfo from "../components/util/CourseInfo";
  import HandoutList from "../components/util/HandoutList";
  import SemesterPublishList from "../components/util/SemesterPublishList";
  import PostModule from "../components/util/PostModule";

  export default {
    name: "TeacherCourseInfo",
    components: {PostModule, SemesterPublishList, HandoutList, CourseInfo, PublishList, RouterButton, ButtonItem},
    beforeCreate: function () {
     this.axios.post("/backend/coursePublishList",{courseId:this.$store.state.course.id})
       .then(res=>{
         if (res.data.code === 0) {
           this.publishList=res.data.data;
         }else {
           this.$message.error("获取发布列表错误");
         }
       })
    },
    data() {
      return {
        formLabelWidth: '120px',
        dialogVisible: false,
        handoutForm: {
          name: "",
          file: {}
        },
        change: false,
        publishList: [],
        postModule:false
      }
    },
    computed: {
      course() {
        return this.$store.state.course;
      }
    },
    methods: {
      handleChange(file, fileList) {
        this.handoutForm.file = file.raw;
        if (fileList.length > 1) {
          fileList.shift()
        }
      },
      handleRemove(file, fileList){
        this.handoutForm.file={}
      },
      uploadHandout() {
        if(this.isEmptyObject(this.handoutForm.file)){
          this.$message.warning("必须选择一个文件");
          return
        }

        let formData = new FormData();
        formData.append("file", this.handoutForm.file);
        formData.append("name", this.handoutForm.name);
        formData.append("courseId", this.course.id);
        this.axios.post("/backend/uploadHandout", formData)
          .then(res => {
            if (res.data.code === 0) {
              this.dialogVisible = false;
              this.change = !this.change;
              this.$Message.success({
                content: "上传成功",
                duration: 1
              });
            } else {
              this.$message.error("上传出错");
            }
          });

      }
    }
  }
</script>

<style scoped>

</style>
