<template>
  <el-row :gutter="20">
    <el-col :span="16">
      <el-row>
        <el-card shadow="always">
          <div slot="header" class="cf f3 tl">
            <span>{{post.title}}</span>
          </div>
          <div class="postBlock mb4 tl">
            <div class="pa2">
              <p>由 {{post.user.name}} 发布于 {{post.time}}</p>
              <br/>
              <p>{{post.text}}</p>
            </div>
          </div>
          <div v-for="item in followUpList"
               :key="item.id"
               class="postBlock mb4 tl">
            <div class="pa2">
              <p>由 {{item.user.name}} 发布于 {{item.time}}</p>
              <br/>
              <p>{{item.text}}</p>
            </div>
          </div>
        </el-card>
      </el-row>
      <el-row class="pt3">
        <el-card shadow="always">
          <div slot="header" class="cf f3 tl">
            <span>回帖</span>
          </div>
          <el-form>
            <el-form-item label="正文">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2}"
                placeholder="请输入内容"
                v-model="text">
              </el-input>
            </el-form-item>
            <el-form-item class="tr">
              <el-button type="primary" @click="followUp">发 帖</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-row>
    </el-col>
    <el-col :span="8">
      <el-card shadow="always">
        <button-item v-on:click="goBack()">返回</button-item>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
  import ButtonItem from "../components/util/ButtonItem";

  export default {
    name: "PostPage",
    components: {ButtonItem},
    data() {
      return {
        followUpList: [],
        text: ""
      }
    },
    computed: {
      post() {
        return this.$store.state.post;
      }
    },
    beforeCreate() {
      this.axios.post("/backend/getFollowUp", {postId: this.$store.state.post.id})
        .then(res => {
          if (res.data.code === 0) {
            this.followUpList = res.data.data;
          } else {
            this.$message.error("获取回复列表失败")
          }
        })
    },
    methods: {
      refresh() {
        this.axios.post("/backend/getFollowUp", {postId: this.$store.state.post.id})
          .then(res => {
            if (res.data.code === 0) {
              this.followUpList = res.data.data;
            } else {
              this.$message.error("获取回复列表失败")
            }
          })
      },
      followUp() {
        this.axios.post("/backend/followup", {postId: this.post.id, text: this.text})
          .then(res => {
            if (res.data.code === 0) {
              this.refresh();
              this.$message.success("发帖成功");
            } else {
              this.$message.error("跟帖失败")
            }
          })
      }
    }
  }
</script>

<style scoped>
  .postBlock {
    background-color: #F5F5F5;
    border-style: solid;
    border-width: 1px;
    border-color: RGB(0, 0, 0, 0.2);
    border-radius: 5px;
    font-size: 16px;
  }
</style>
