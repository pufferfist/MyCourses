<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <router-view>
      </router-view>
    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <router-button v-bind:path="'/admin/home'">查看统计</router-button>
        <router-button v-bind:path="'/admin/approveCourse'">审批课程创建</router-button>
        <router-button v-bind:path="'/admin/approvePublish'">审批课程发布</router-button>
        <router-button v-bind:path="'/admin/cutOff'">选课截止</router-button>
        <button-item @click="semesterDialog=true">创建新学期</button-item>
      </el-card>
    </el-col>
    <el-dialog title="创建新学期" :visible.sync="this.semesterDialog">
      <el-form>
        <el-form-item label="学期名">
          <el-input v-model="newSemester" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="semesterDialog = false">取 消</el-button>
        <el-button type="primary" @click="createSemester">确 定</el-button>
      </div>
    </el-dialog>
  </el-row>
</template>

<script>
    import RouterButton from "../components/util/RouterButton";
    import ButtonItem from "../components/util/ButtonItem";
    export default {
        name: "Admin",
      components: {ButtonItem, RouterButton},
      data(){
          return{
            semesterDialog:false,
            newSemester:""
          }
      },
      methods:{
        createSemester(){
          this.axios.post("/backend/newSemester",{semester:this.newSemester})
            .then(res=>{
              if (res.data.code === 0) {
                this.$message.success("创建新学期成功");
                this.semesterDialog=false;
              }else{
                this.$message.error("新学期格式错误")
              }
            })
        }
      }
    }
</script>

<style scoped>

</style>
