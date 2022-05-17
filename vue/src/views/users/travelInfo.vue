<template>
    <div>
        <el-steps :active="active" finish-status="success">
            <el-step title="步骤 1"></el-step>
            <el-step title="步骤 2"></el-step>
            <el-step title="步骤 3"></el-step>
        </el-steps>

        <div v-show="active===1">
            <el-form  label-width="100px" size="small" :model="form" :rules="addFormRules" ref="ruleForm" class="myForm">
                <el-form-item label="出行工具" prop="travel_tool">
                    <el-input v-model="form.travel_tool" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="目的地" prop="destination">
                    <el-input v-model="form.destination" autocomplete="off"></el-input>
                </el-form-item>
                <!--出行人数验证不为空-->
                <el-form-item label="出行人数" prop="totalman">
                    <el-input v-model.number="form.totalman" autocomplete="off"></el-input>
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
            return{
                active:1,

                name: "",
                address: "",
                //嵌套表单
                form:{},
                addFormRules:{
                    travelTool:[
                        {required:true,message:'请输入出入工具',trigger:'blur'},
                        {min:1,max:10,message: '长度在1到10个字符',trigger: 'blur'}
                    ],
                    destination:[
                        {required:true,message:'请输入目的地',trigger:'blur'},
                        {min:1,max:50,message: '长度在1到50个字符',trigger: 'blur'}
                    ],
                    totalman:[
                        {required:true,type:'number',validator:checkAge,trigger:'blur'},

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
            this.getResidentInfo()
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
                        request.post("/travel/save2",this.form).then(res =>{
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

            getResidentInfo(){
                request.get('/resident/selectResidentStatus',{params:{id:this.form.uid}}).then(res=>{
                    console.log('出行填报',this.id)
                    // this.form=res
                    this.form.rid = res.id
                    console.log('出行填报',res)
                    // 0未提交
                    // if (res===0){
                    //     this.active=1
                    //     // 1提交了，但在审核
                    // }else if (res===1){
                    //     this.active=2
                    //     //2 审核成功
                    // }else if(res===2){
                    //     this.active=3
                    // }
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