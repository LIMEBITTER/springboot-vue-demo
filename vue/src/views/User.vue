<!-- 用户管理表格 -->

<template>
    <div>

        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="username"></el-input>
            <el-input style="width: 200px" placeholder="请输入昵称" suffix-icon="el-icon-message" class="ml-5" v-model="nickName"></el-input>
            <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5" v-model="address"></el-input>
            <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
            <el-button class="ml-5" type="warning" @click="reset">重置</el-button>
        </div>

        <div style="margin: 10px 0">
            <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm
                    class="ml-5"
                    confirm-button-text='您确认批量删除?'
                    cancel-button-text='我再想想'
                    icon="el-icon-info"
                    icon-color="red"
                    title="您确定删除吗？"
                    @confirm="delBatch">
                <el-button type="danger" slot="reference" class="ml-5">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>

            <!--导入导出-->
            <el-upload  class="upload-demo" action="http://localhost:9090/user/import"
                        style="display: inline-block"
                        :on-success="handleImportSuccess"
                        :show-file-list="false"
                        accept=".xls,.xlsx"
                        :on-error="handleImportError"
            >
                <el-button type="primary"  class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
            </el-upload>
            <el-button type="primary"  @click="exp"   class="ml-5">导出 <i class="el-icon-top"></i></el-button>
        </div>

        <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"  @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column prop="id" label="id" width="80">
            </el-table-column>
            <el-table-column prop="username" label="用户名" width="120">
            </el-table-column>
            <el-table-column prop="nickName" label="昵称" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
            <el-table-column label="操作"  width="200" align="center">
                <template slot-scope="scope">
                    <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                    <el-popconfirm
                            class="ml-5"
                            confirm-button-text='确认删除'
                            cancel-button-text='我再想想'
                            icon="el-icon-info"
                            icon-color="red"
                            title="您确定删除吗？"
                            @confirm="handleDelete(scope.row.id)">
                        <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
                    </el-popconfirm>

                </template>
            </el-table-column>




        </el-table>

        <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[5, 10, 15, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>

        <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="35%">
            <el-form  label-width="100px" size="small" :model="form" :rules="addFormRules" ref="ruleForm">
                <el-form-item label="用户名" disabled="true" prop="username" style="margin-bottom: 30px">
                    <el-input  v-model="form.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="昵称" prop="nickName" style="margin-bottom: 30px">
                    <el-input v-model="form.nickName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address" style="margin-bottom: 30px">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleSave">确 定</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    import request from "../utils/request.js";

    export default {
        name: "User",
        data(){
            return{

                //用户数据
                tableData:[],
                total:0,
                pageNum:1,
                pageSize:5,
                username: "",
                nickName: "",
                address: "",
                headerBg: 'headerBg',
                //嵌套表单
                dialogFormVisible:false,
                form:{},
                //批量删除属性
                multipleSelection:[],
                addFormRules:{
                    username:[
                        {required:true,message:'请输入用户名',trigger:'blur'},
                        {min:1,max:10,message: '长度在1到10个字符',trigger: 'blur'}
                    ],
                    nickName:[
                        {required:true,message:'请输入昵称',trigger:'blur'},
                        {min:1,max:10,message: '长度在1到10个字符',trigger: 'blur'}
                    ],
                    address:[
                        {required:true,message:'请输入地址',trigger:'blur'},
                        {min:3,max:50,message: '长度在3到50个字符',trigger: 'blur'}
                    ],
                }
            }
        },
        created(){
            this.load()
            // this.tableData=res.data
            // this.total=res.total
            //请求数据
        },
        mounted(){
            console.log('handleCurrentChange',this.pageNum)
            console.log('handleSizeChange',this.pageSize)

            console.log('total',this.total)
        },
        methods:{
            //导出按钮
            exp(){
                window.open("http://localhost:9090/user/export")
            },
            //导入成功钩子
            handleImportSuccess(){
                this.$message.success("导入成功!")
                this.load()
            },
            //导入失败钩子
            handleImportError(){
                this.$message.error("导入失败,请检查导入文件格式并重新尝试!")
                this.load()
            },


            //批量删除按钮
            handleSelectionChange(val){
                this.multipleSelection = val
            },

            //批量删除方法
            delBatch(){
                let ids =this.multipleSelection.map(v =>v.id) //由纯对象数组转为ids数组[1,2,3]
                request.post("/user/del/batch",ids).then(res =>{
                    //判断是否保存成功
                    if(res) {
                        this.$message.success("批量删除成功!")
                        this.dialogFormVisible=false
                        this.load()
                    }else{
                        this.$message.error("批量删除失败!")
                    }
                })
            },


            //用户删除按钮
            handleDelete(id){
                request.delete("/user/"+ id).then(res =>{
                    //判断是否保存成功
                    if(res) {
                        this.$message.success("删除成功!")
                        this.dialogFormVisible=false
                        this.load()
                    }else{
                        this.$message.error("删除id失败!")
                    }
                })
            },

            //用户编辑按钮
            handleEdit(row){
                this.form=Object.assign({},row)//将行对象的数据赋予到弹窗中
                this.dialogFormVisible =  true
            },

            //用户新增按钮
            handleAdd(){
                this.dialogFormVisible=true,
                    this.form={}
            },

            //用户新增按钮-确定按钮
            handleSave(){
                //表单校验
                this.$refs['ruleForm'].validate(valid=>{
                    if(valid){
                        request.post("/user",this.form).then(res =>{
                            console.log('user_res',res)

                            //判断是否保存成功
                            if(res) {
                                this.$message.success("保存成功!")
                                this.dialogFormVisible=false
                                this.load()
                            }else{
                                this.$message.error("保存失败!")
                            }
                        })
                    }else{
                        this.$nextTick(()=>{
                            this.scrollToTop(this.$refs['ruleForm'.$el])
                        })
                    }
                })

            },

            //分页信息
            handleSizeChange(pageSize){
                this.pageSize=pageSize
                this.load()
            },
            handleCurrentChange(pageNum){
                this.pageNum=pageNum

                this.load()
            },

            //重置按钮
            reset(){
                this.username="",
                    this.nickName="",
                    this.address= "",
                    this.load()
            },

            //加载用户信息
            load(){
                request.get("/user/page",{
                        params:{
                            pageNum:this.pageNum,
                            pageSize:this.pageSize,
                            username:this.username,
                            nickName:this.nickName,
                            address:this.address,
                        }
                    }
                ).then(res =>{
                    // console.log('user',res)
                    this.tableData=res.records;
                    console.log('res.records-user',res)
                    this.total=res.total;
                })

            },

        }
    }
</script>

<style scoped>
    .headerBg {
        background: #eee!important;
    }

</style>