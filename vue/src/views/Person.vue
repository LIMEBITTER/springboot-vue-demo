<template>
  <el-card style="width: 500px;">

    <el-form label-width="80px" size="small">
<!--      <el-upload-->
<!--          class="avatar-uploader"-->
<!--          :action="'http://' + serverIp +':9090/file/upload'"-->
<!--          :show-file-list="false"-->
<!--          :on-success="handleAvatarSuccess"-->
<!--      >-->
<!--        <img v-if="form.avatar" v-model="form.avatar" class="avatar">-->
<!--        <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--      </el-upload>-->

<!--      <el-form-item label="用户名">-->
<!--        <el-input disabled v-model="form.username" autocomplete="off"></el-input>-->
<!--      </el-form-item>-->
      <el-form-item label="姓名">
        <el-input disabled v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
        <el-form-item label="性别">
            <el-input disabled v-model="form.sex" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
            <el-input disabled v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
            <el-input type="textarea" disabled v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="是否为本地居民">
            <el-input disabled v-model="form.localPeople" autocomplete="off"></el-input>
        </el-form-item>

    </el-form>
  </el-card>
</template>

<script>
import {serverIp} from "../../public/config";
import request from "../utils/request";

export default {
  name: "Person",
  data() {
    return {
      serverIp: serverIp,
      form: {},
        id:null,
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    //请求数据（后台接口）

    this.getUser().then(res => {
      console.log('person',res)
      this.form = res
    })
      let userInfo = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
      this.id = userInfo.id
  },

  mounted(){
    this.form = this.user

    console.log('formuser',this.form)
    console.log('user1',JSON.stringify(this.user))
      this.getResidentInfo()

  },
  methods: {
    async getUser() {
      return (await this.request.get("/user/")).data
    },
    save() {
      this.request.post("/user", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")

          // 触发父级更新User的方法
          this.$emit("refreshUser")

          // 更新浏览器存储的用户信息
          this.getUser().then(res => {
            res.token = JSON.parse(localStorage.getItem("user")).token
            localStorage.setItem("user", JSON.stringify(res))
          })

        } else {
          this.$message.error("保存失败")
        }
      })
    },

    getResidentInfo(){
        request.get('/resident/bindUserResident',{params:{id:this.id}}).then(res=>{
            console.log('用户信息绑定',this.id)
            this.form=res
            console.log(res)
        })
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res
    }
  }
}
</script>

<style>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}
</style>
