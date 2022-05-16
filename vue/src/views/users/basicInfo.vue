<template>
    <div>
        <el-steps :active="active" finish-status="success">
            <el-step title="步骤 1"></el-step>
            <el-step title="步骤 2"></el-step>
            <el-step title="步骤 3"></el-step>
        </el-steps>

        <div v-show="active===1">
                <el-form  label-width="100px" size="small" :model="form" :rules="addFormRules" ref="ruleForm" class="myForm">
                    <el-form-item label="姓名" prop="name" size="large">
                        <el-input v-model="form.name" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex" size="large">
                        <el-radio-group v-model="form.sex">
                            <el-radio label="男" value="男"></el-radio>
                            <el-radio label="女" value="女"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                    <!--年龄验证不为空-->
                    <el-form-item label="年龄" prop="age" size="large">
                        <el-input v-model.number="form.age" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="地址" prop="address" size="large">
                        <el-input v-model="form.address" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="电话" prop="tel" size="large">
                        <el-input v-model="form.tel" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="是否为本小区人员" prop="local_people" size="large">
                        <el-radio-group v-model="form.local_people">
                            <el-radio label="是" value="是"></el-radio>
                            <el-radio label="否" value="否"></el-radio>
                        </el-radio-group>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer" id="footer">
                    <!--            <el-button @click="dialogFormVisible = false" size="large">取 消</el-button>-->
                    <el-button type="primary" @click="onSubmit" :loading="verifyStatus" size="large" >提 交</el-button>
                </div>
                <span style="color: red">{{verifyText}}</span>
            </div>

        <div v-show="active===2">等待审核

        </div>
        <div v-show="active===3">审核成功</div>

<!--        <el-button v-if="active < 4" style="margin-top: 12px" @click="next">下一步</el-button>-->
<!--        <el-button v-if="active > 1" style="margin-top: 12px" @click="pre">上一步</el-button>-->


    </div>
</template>

<script>
    import request from "../../utils/request";

    export default {
        name: "basicInfo",
        data(){
            //验证年龄
            let checkAge = (rule, value, callback) => {
                if (!Number.isInteger(value)) {
                    callback(new Error('请输入数字值'));
                }else {
                    callback()
                }
            };
            //验证电话
            let checkPhone = (rule, value, callback) => {
                let reg = /^1[345789]\d{9}$/
                if (!reg.test(value)) {
                    callback(new Error('请输入11位手机号'))
                } else {
                    callback()
                }
            }
            return{
                active:parseInt(localStorage.getItem('active')),
                name: "",
                address: "",
                //嵌套表单
                form:{},
                addFormRules:{
                    name:[
                        {required:true,message:'请输入姓名',trigger:'blur'},
                        {min:2,max:10,message: '长度在2到10个字符',trigger: 'blur'}
                    ],
                    sex:[
                        {required:true,message:'请选择性别',trigger:'change'},
                    ],
                    age:[
                        {required:true,type:'number',validator:checkAge,trigger:'blur'},
                    ],
                    address:[
                        {required:true,message:'请输入地址',trigger:'blur'},
                        {min:3,max:50,message: '长度在3到50个字符',trigger: 'blur'}
                    ],
                    tel:[
                        {required:true,type:'number',validator:checkPhone,trigger:'blur'}
                    ],
                    localPeople:[
                        {required:true,message:'请选择类别',trigger:'change'},

                    ]


                },
                // 审核状态 按钮
                verifyStatus:false,
                // 审核 页面显示
                verifyText:'未提交',

            }
        },
        created() {
            if (localStorage.getItem('active')===null){
                localStorage.setItem('active',1)
            }
            let userInfo = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
            this.form.uid = userInfo.id
            console.log('created==========',userInfo.id)
        },
        methods:{
            // 步骤条下一步的方法
            next() {
                if (this.active++ > 3) this.active = 1
            },
            // 步骤条上一步的方法
            pre() {
                if (this.active-- < 2) this.active = 1
            },

            onSubmit(){
                //表单校验
                this.$refs['ruleForm'].validate(valid=>{
                    console.log('表单校验',valid)
                    if(valid){
                        console.log('表单合法！',this.form)
                        request.post("/resident/save2",this.form).then(res =>{
                            //判断是否保存成功
                            console.log('post后端')
                            if(res) {
                                console.log('post后端返回值',res)
                                this.$message.success("保存成功!")
                                this.next()
                            }else{
                                this.$message.error("保存失败!")
                            }
                        })
                    }else{
                        console.log("表单格式非法!")

                        // this.$nextTick(()=>{
                        //     this.scrollToTop(this.$refs['ruleForm'.$el])
                        // })
                    }
                })

            },

        }
    }
</script>

<style scoped>
    #container {
        margin: 0 350px;
    }
    #footer{
        text-align: center;
    }

.el-input{
    width: 500px;
}

</style>