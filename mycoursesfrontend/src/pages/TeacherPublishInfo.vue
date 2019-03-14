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
        <el-row class="pt3">
          <grade-table v-if="showGradeTable"
                       v-bind:grade-data="gradeData"></grade-table>
        </el-row>
      </el-col>
      <el-col :span="8">
        <el-card shadow="always">
          <button-item v-on:click="homeworkDialog=true">发布作业</button-item>
          <button-item v-on:click="gradeDialog=true">发布成绩</button-item>
          <button-item v-if="publish.gradesFilePath!==null" v-on:click="showGrade">查看成绩</button-item>
          <button-item v-on:click="mailDialog=true">群发邮件</button-item>
          <button-item v-on:click="goBack()">返回</button-item>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="发布作业" :visible.sync="this.homeworkDialog">
      <el-form :model="homeworkForm">
        <el-form-item label="作业名称">
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
            :on-remove="homeworkRemove"
            :on-change="homeworkChange"
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
    <el-dialog title="上传成绩" :visible.sync="this.gradeDialog">
      <el-form>
        <el-form-item label="上传文件">
          <el-upload
            class="upload-demo"
            drag
            ref="upload"
            action="whatever"
            :on-remove="publishRemove"
            :on-change="publishChange"
            :auto-upload="false">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="gradeDialog = false">取 消</el-button>
        <el-button type="primary" @click="publishGrades">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="群发邮件" :visible.sync="this.mailDialog">
      <el-form :model="mailForm">
        <el-form-item label="主题">
          <el-input v-model="mailForm.title" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="正文">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2}"
            placeholder="请输入内容"
            v-model="mailForm.text">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="mailDialog = false">取 消</el-button>
        <el-button type="primary" @click="groupEmail">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import PublishInfo from "../components/util/PublishInfo";
  import ButtonItem from "../components/util/ButtonItem";
  import AssignmentList from "../components/util/AssignmentList";
  import GradeTable from "../components/util/GradeTable";

  export default {
    name: "TeacherPublishInfo",
    components: {GradeTable, AssignmentList, ButtonItem, PublishInfo},
    data() {
      return {
        homeworkDialog: false,
        homeworkForm: {
          name: "",
          description: "",
          deadline: "",
          file: {}
        },
        gradeDialog: false,
        showGradeTable: false,
        gradeForm: {
          file: {},
        },
        change: false,
        gradeData: [],
        mailDialog:false,
        mailForm:{
          title:"",
          text:""
        }
      }
    },
    computed: {
      publish() {
        return this.$store.state.publish;
      }
    },
    methods: {
      homeworkChange(file, fileList) {
        this.homeworkForm.file = file.raw;
        if (fileList.length > 1) {
          fileList.shift()
        }
      },
      homeworkRemove(file, fileList) {
        this.homeworkForm.file = {}
      },
      publishChange(file, fileList) {
        this.gradeForm.file = file.raw;
        if (fileList.length > 1) {
          fileList.shift()
        }
      },
      publishRemove(file, fileList) {
        this.gradeForm.file = {}
      },
      publishAssignment() {
        let formData = new FormData();
        formData.append("publishId", this.publish.id);
        formData.append("name", this.homeworkForm.name);
        formData.append("description", this.homeworkForm.description);
        formData.append("deadline", this.homeworkForm.deadline);
        formData.append("file", this.homeworkForm.file);
        this.axios.post("/backend/publishAssignment", formData)
          .then(res => {
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
      },
      showGrade() {
        if (this.showGradeTable) {
          this.showGradeTable = false;
          return
        }
        this.axios.post("/backend/getCourseGrade", {publishId: this.publish.id})
          .then(res => {
            this.gradeData = this.processCSV(res.data);
            this.showGradeTable = true;
          })
      },
      publishGrades() {
        if(this.isEmptyObject(this.gradeForm.file)){
          this.$message.warning("必须选择一个文件");
          return
        }
        let formData = new FormData();
        formData.append("publishId", this.publish.id);
        formData.append("file", this.gradeForm.file);
        this.axios.post("/backend/publishCourseGrades", formData)
          .then(res => {
            if (res.data.code === 0) {
              this.gradeDialog = false;
              this.$Message.success({
                content: "上传成功",
                duration: 1
              });
            } else {
              this.$Message.error({
                content: res.data.msg,
                duration: 1
              });
            }
          })
      },
      groupEmail(){
        this.mailForm.publishId=this.publish.id;
        this.axios.post("/backend/groupEmail",this.mailForm)
          .then(res=>{
            if (res.data.code === 0) {
              this.mailDialog=false;
              this.$message.success("发送成功")
            }else {
              this.$message.error("发送失败")
            }
          })
      }
    },
  }
</script>

<style scoped>

</style>
