<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="16">
        <el-row>
          <assignment-info v-bind:assignment="assignment"></assignment-info>
        </el-row>
        <el-row class="pt3">
          <grade-table v-if="showGradeTable"
                       v-bind:grade-data="gradeData"></grade-table>
        </el-row>
        <el-row class="pt3" v-if="!isEmptyObject(this.uploadAssignment)">
          <el-card shadow="always">
            <div slot="header" class="cf f3 tl">
              <span>已上传作业</span>
            </div>
            <a :href="'http://localhost:8088'+this.getPath(this.uploadAssignment.filePath)"
               class="pv2 db">{{this.uploadAssignment.filePath.slice(this.uploadAssignment.filePath.lastIndexOf("/")+1)}}</a>
          </el-card>
        </el-row>
        <el-row class="pt3">
          <el-card shadow="always">
            <div slot="header" class="cf f3 tl">
              <span>上传作业</span>
            </div>
            <el-form>
              <el-form-item >
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
              <el-form-item>
                <el-button type="primary" @click="upload">上传</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </el-row>

      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <button-item v-if="assignment.gradesFilePath!==null" v-on:click="showGrade">查看成绩</button-item>
          <button-item v-on:click="goBack()">返回</button-item>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import AssignmentInfo from "../components/util/AssignmentInfo";
  import GradeTable from "../components/util/GradeTable";
  import ButtonItem from "../components/util/ButtonItem";

  export default {
    name: "StudentAssignmentInfo",
    components: {ButtonItem, GradeTable, AssignmentInfo},
    data() {
      return {
        file: {},
        gradeData: [],
        showGradeTable: false,
        uploadAssignment: {},
      }
    },
    beforeMount() {
      this.axios.post("/backend/getUploadedAssignment", {assignmentId: this.$store.state.assignment.id})
        .then(res => {
          if (res.data.code === 0) {
            this.uploadAssignment = res.data.data;
          }
        })
    },
    computed: {
      assignment() {
        return this.$store.state.assignment;
      }
    },
    methods: {
      showGrade() {
        if (this.showGradeTable) {
          this.showGradeTable = false;
          return
        }
        this.axios.post("/backend/getAssignmentGrade", {assignmentId: this.assignment.id})
          .then(res => {
            this.gradeData = this.processCSV(res.data);
            this.showGradeTable = true;
          })
      },
      handleChange(file, fileList) {
        this.file = file.raw;
        if (fileList.length > 1) {
          fileList.shift()
        }
      },
      handleRemove(file, fileList) {
        this.file = {}
      },
      upload() {
        if (this.isEmptyObject(this.file)) {
          this.$message.warning("必须选择一个文件");
          return
        }
        let formData = new FormData();
        formData.append("assignmentId", this.assignment.id);
        formData.append("file", this.file);
        this.axios.post("/backend/uploadAssignment", formData)
          .then(res => {
            if (res.data.code === 0) {
              this.$Message.success({
                content: "上传成功",
                duration: 1
              });
              this.axios.post("/backend/getUploadedAssignment", {assignmentId: this.$store.state.assignment.id})
                .then(res => {
                  if (res.data.code === 0) {
                    this.uploadAssignment = res.data.data;
                  }
                })
            } else {
              this.$Message.error({
                content: res.data.msg,
                duration: 1
              });
            }
          })
      }
    }
  }
</script>

<style scoped>

</style>
