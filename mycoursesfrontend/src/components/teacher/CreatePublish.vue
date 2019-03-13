<template>
  <el-card shadow="always">
    <div slot="header" class="cf f3 tl">
      <span>发布课程</span>
    </div>
    <el-form :label-position="labelPosition" label-width="120px" :model="form" class="tl">
      <el-form-item label="名称" class="tl">
        {{course.name}}
      </el-form-item>
      <el-form-item label="学期名" class="tl">
        2019年春
      </el-form-item>
      <el-form-item label="课时数">
        <el-input
          placeholder="请输入内容"
          v-model="form.classHour">
        </el-input>
      </el-form-item>
      <el-form-item label="节数">
        <el-select v-model="form.classOrder" placeholder="请选择">
          <el-option
            v-for="item in classOrder.options"
            :key="item.value"
            :label="item.value"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期">
        <el-select v-model="form.dayOfWeek" placeholder="请选择">
          <el-option
            v-for="item in weekday.options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
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
    computed:{
      course(){
        return this.$store.state.course;
      }
    },
    data() {
      return {
        weekday:{
          options: [{
            value: 1,
            label: '周一'
          }, {
            value: 2,
            label: '周二'
          }, {
            value: 3,
            label: '周三'
          }, {
            value: 4,
            label: '周四'
          }, {
            value: 5,
            label: '周五'
          }, {
            value: 6,
            label: '周六'
          }, {
            value: 7,
            label: '周日'
          }]
        },
        classOrder:{options:[
            {
              value: "1",
            },{
              value: "2",
            },{
              value: "3",
            },{
              value: "4",
            },{
              value: "5",
            },{
              value: "6",
            },{
              value: "7",
            },{
              value: "8",
            },{
              value: "9",
            },{
              value: "10",
            },{
              value: "11"
            },
          ]},
        form: {
          courseId:"",
          semester: "2019年春",
          classHour: "",
          classOrder: "",
          dayOfWeek: "",
          startWeek: "",
          weekNumber: "",
          classroom: "",
          maxStudentNumber: "",
          currentStudentNumber: "",
          classNumber: "",
        },
          labelPosition: 'left'
      }
    },
    methods: {
      onSubmit() {
        this.form.courseId=this.course.id;
        this.axios.post("/backend/publishCourse", this.form)
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
