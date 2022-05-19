<template>
    <div>
        <el-steps :active="active" finish-status="success">
            <el-step title="步骤 1"></el-step>
            <el-step title="步骤 2"></el-step>
            <el-step title="步骤 3"></el-step>
        </el-steps>

        <div v-show="active===1">
            <el-form  label-width="100px" size="small" :model="form" :rules="addFormRules" ref="ruleForm" class="myForm">
                <el-form-item label="状态" prop="normal">
                    <el-radio-group v-model="form.normal">
                        <el-radio label="正常" value="正常"></el-radio>
                        <el-radio label="隔离" value="隔离"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <!--体温验证不为空...-->
                <el-form-item label="体温" prop="temperature">
                    <el-input v-model.number="form.temperature" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="是否已接种疫苗" prop="vaccines">
                    <el-radio-group v-model="form.vaccines">
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
        name: "healthInfo",
        data(){
            //验证温度
            let checkTemp = (rule, value, callback) => {
                if (!Number.isInteger(value)) {
                    callback(new Error('请输入数字值'));
                }else if (value<=35||value>=43){
                    callback(new Error('请输入合理的温度值'));
                } else {
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
                    normal:[
                        {required:true,message:'请选择状态',trigger:'change'},
                    ],
                    temperature:[
                        {required:true,type:'number',validator:checkTemp,trigger:'blur'},

                    ],
                    vaccines:[
                        {required:true,message:'请选择接种状态',trigger:'change'},

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
            this.getRidByUid()
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
                        request.post("/health/save2",this.form).then(res =>{
                            //判断是否保存成功
                            console.log('post后端',res)
                            if(res) {
                                console.log('post后端返回值',res)
                                this.$message.success("提交成功，请等待审核！")
                                this.next()
                            }else{
                                this.$message.error("提交失败!")
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

            getRidByUid(){
                request.get('/resident/selectResidentStatus',{params:{id:this.form.uid}}).then(res=>{
                    console.log('出行填报',this.id)
                    // this.form=res
                    this.form.rid = res.id
                    console.log('出行填报',res)

                    request.get('/health/selectHealthById',{params:{id:this.form.rid}}).then(res=>{
                        // 0未提交
                        if (res===null){
                            this.active=1
                            // 1提交了，但在审核
                        }else if (res===1){
                            this.active=2
                            //2 审核成功
                        }else if(res===2){
                            this.active=3
                        }
                    })

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