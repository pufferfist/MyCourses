<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>创建课程</span>
    </div>
    <el-form :label-position="labelPosition" label-width="80px" :model="form">
      <el-form-item label="名称">
        <el-input
          placeholder="请输入课程名称"
          v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="课程描述">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2}"
          placeholder="请输入内容"
          v-model="form.description">
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
    name: "createCourse",
    data() {
      return {
        form: {
          name: "",
          description: ""
        },
        labelPosition: 'right'
      }
    },
    methods: {
      onSubmit() {
        this.axios.post("/backend/createCourse", this.form)
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
