<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>发布课程</span>
    </div>
    <el-form :label-position="labelPosition" label-width="80px" :model="form">
      <el-form-item label="名称">
        {{course.name}}
      </el-form-item>
      <el-form-item label="学期名">
        2019年春
      </el-form-item>
      <el-form-item label="课时数">
        <el-input
          placeholder="请输入内容"
          v-model="form.classHours">
        </el-input>
      </el-form-item>
      <el-form-item label="节数">
        <el-input
          placeholder="请输入内容"
          v-model="form.classOrder">
        </el-input>
      </el-form-item>
      <el-form-item label="日期">
        <el-input
          placeholder="请输入内容"
          v-model="form.dayOfWeek">
        </el-input>
      </el-form-item>
      <el-form-item label="开始周">
        <el-input
          placeholder="请输入内容"
          v-model="form.startWeek">
        </el-input>
      </el-form-item>
      <el-form-item label="周数">
        <el-input
          placeholder="请输入内容"
          v-model="form.weekNumber">
        </el-input>
      </el-form-item>
      <el-form-item label="教室名">
        <el-input
          placeholder="请输入内容"
          v-model="form.classroom">
        </el-input>
      </el-form-item>
      <el-form-item label="最大选课人数">
        <el-input
          placeholder="请输入内容"
          v-model="form.maxStudentNumber">
        </el-input>
      </el-form-item>
      <el-form-item label="班级号">
        <el-input
          placeholder="请输入内容"
          v-model="form.classNumber">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button @click="goBack">取消</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
  export default {
    name: "CreatePublish",
    props: ["course"],
    data() {
      return {
        form: {
          courseId:this.course.id,
          semester: "",
          classHours: "",
          classOrder: "",
          dayOfWeek: "",
          startWeek: "",
          weekNumber: "",
          classroom: "",
          maxStudentNumber: "",
          currentStudentNumber: "",
          classNumber: "",
        },
        labelPosition: 'right'
      }
    },
    methods: {
      onSubmit() {
        this.axios.post("/backend/createPublish", this.form)
          .then((res) => {
            if (res.data.code === 0) {
              this.$Message.success({
                content: "创建成功",
                duration: 0.5
              });
              this.$router.push("/teacher/home")
            } else {
              this.$Message.error("课程创建出现错误");
            }
          })
      },
      goBack() {
        this.$router.go(-1);
      }
    }
  }
</script>

<style scoped>

</style>
