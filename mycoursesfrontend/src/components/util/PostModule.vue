<template>

  <el-card shadow="always" class="tl">
    <div slot="header" class="cf f3 tl">
      <span>讨论区</span>
    </div>
    <a v-for="item in postList"
       :key="item.id"
       v-on:click="transfer(item)"
       class="f4 db">{{item.title}}</a>
    <el-form :model="postForm" class="mt6 bt b--mid-gray">
      <el-form-item label="主题">
        <el-input v-model="postForm.title" autocomplete="off" placeholder="请输入主题"></el-input>
      </el-form-item>
      <el-form-item label="正文">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2}"
          placeholder="请输入内容"
          v-model="postForm.text">
        </el-input>
      </el-form-item>
      <el-form-item class="tr">
        <el-button type="primary" @click="post">发 帖</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
  export default {
    name: "PostModule",
    data() {
      return {
        postList: [],
        postForm: {
          title: "",
          text: ""
        }
      }
    },
    computed: {
      course() {
        return this.$store.state.course;
      }
    },
    beforeCreate() {
      this.axios.post("/backend/getPostList", {courseId: this.$store.state.course.id})
        .then(res => {
          if (res.data.code === 0) {
            this.postList = res.data.data;
          } else {
            this.$message.error("获取论坛列表失败")
          }
        })
    },
    methods: {
      refresh() {
        this.axios.post("/backend/getPostList", {courseId: this.course.id})
          .then(res => {
            if (res.data.code === 0) {
              this.postList = res.data.data;
            } else {
              this.$message.error("获取论坛列表失败")
            }
          })
      },
      transfer(post) {
        this.$store.commit('set', {type: "post", data: post});
        this.$router.push('/post')
      },
      post() {
        this.postForm.courseId = this.course.id;
        this.axios.post("/backend/post", this.postForm)
          .then(res => {
            if (res.data.code === 0) {
              this.refresh();
              this.$message.success("发帖成功");
            }
          })
      }
    }
  }
</script>

<style scoped>

</style>
