<template>
  <div id="login">
    <Row class="middle">
      <i-col>
        <div id="form" class="login-form animated fadeInLeft tc">
          <div>
            <span>注册帐号</span>
          </div>
          <el-form :model="form" status-icon :rules="rules" ref="signupForm">
            <el-form-item label="帐号" prop="username" required>
              <el-input v-model="form.username"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name" required>
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password" required>
              <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass" required>
              <el-input type="password" v-model="form.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('signupForm')">提交</el-button>
              <el-button @click="resetForm('signupForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </i-col>
    </Row>
  </div>
</template>

<script>
  export default {
    name: "SignUp",
    data() {
      const validateId = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入邮箱'));
        } else {
          if ((/[0-9a-zA-Z]+@163.com$/i).test(value) || (/[0-9a-zA-Z]+@nju.edu.cn$/i).test(value)) {
            callback();
          } else {
            callback(new Error('邮箱格式错误'));
          }
        }
      };
      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.form.checkPass !== '') {
            this.$refs.signupForm.validateField('checkPass');
          }
          callback();
        }
      };
      const validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        form: {
          username: "",
          password: "",
          checkPass: "",
          name:""
        },
        rules: {
          username: [
            {validator: validateId, trigger: 'blur'}
          ],
          password: [
            {validator: validatePass, trigger: 'blur'}
          ],
          checkPass: [
            {validator: validatePass2, trigger: 'blur'}
          ],
          name:[
            { required: true, message: '请输入真实姓名', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post("/backend/user/signUp",this.form)
              .then(res=>{
                if (res.data.code === 0) {
                  this.$router.push("/login")
                  this.$message.success("注册成功,请至邮箱查收验证邮件");
                }else{
                  this.$message.error("注册失败");
                }
              })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<style scoped>
  .blur {
    filter: blur(50px);
    -webkit-filter: blur(50px);
    -moz-filter: blur(50px);
    -ms-filter: blur(50px);
    -o-filter: blur(50px);
  }

  #login {
    background-image: url("../../static/login.jpeg");
    background-size: 1920px 1200px;
    background-repeat: no-repeat;
    height: 1080px;
  }

  .login-form {
    padding: 30px;
    -webkit-box-shadow: -4px 7px 46px 2px rgba(0, 0, 0, 0.1);
    -moz-box-shadow: -4px 7px 46px 2px rgba(0, 0, 0, 0.1);
    -o-box-shadow: -4px 7px 46px 2px rgba(0, 0, 0, 0.1);
    box-shadow: -4px 7px 46px 2px rgba(0, 0, 0, 0.1);
    background: #eeeeeebb;
    border-radius: 5%;
    width: 30%;
    margin: auto;
    min-width: 350px;
  }

  #login {
    position: relative;
  }

  .middle {
    position: fixed;
    top: 25%;
    bottom: 0;
    left: 0;
    right: 0;
  }

  @media screen and (max-width: 976px) {
    .login-form {
      width: 60%;
    }
  }

  @media screen and (max-width: 500px) {
    .login-form {
      width: 70%;
    }
  }

</style>
