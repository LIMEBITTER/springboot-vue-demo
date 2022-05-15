<!-- 用户管理表格 -->

<template>
  <div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入目的地" suffix-icon="el-icon-message" class="ml-5" v-model="destination"></el-input>
      <el-input style="width: 200px" placeholder="请输入随行人数" suffix-icon="el-icon-position" class="ml-5" v-model="totalman"></el-input>
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
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="120">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="120">
      </el-table-column>
      <el-table-column prop="travelTool" label="出行工具" width="120">
      </el-table-column>
      <el-table-column prop="destination" label="目的地" width="120">
      </el-table-column>
      <el-table-column prop="totalman" label="出行人数">
      </el-table-column>
      <!--            <el-table-column prop="tel" label="电话">-->
      <!--            </el-table-column>-->

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
        <el-form-item label="姓名" prop="name">
          <el-select v-model="form.name" placeholder="外来人员姓名" @change="getOptionValue">
            <el-option v-for="(item, index) in residentSourceDic"
                       :key="index"
                       :value="item.value"
                       :label="item.label"

            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio label="男" value="男"></el-radio>
            <el-radio label="女" value="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <!--年龄验证不为空-->
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="form.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出行工具" prop="travelTool">
          <el-input v-model="form.travelTool" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="目的地" prop="destination">
          <el-input v-model="form.destination" autocomplete="off"></el-input>
        </el-form-item>
        <!--出行人数验证不为空-->
        <el-form-item label="出行人数" prop="totalman">
          <el-input v-model.number="form.totalman" autocomplete="off"></el-input>
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
  name: "ResidentTravel",
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

      //用户数据
      tableData:[],
      total:0,
      pageNum:1,
      pageSize:5,
      name: "",
      destination: "",
      totalman: "",
      headerBg: 'headerBg',
      //嵌套表单
      dialogFormVisible:false,
      form:{},
      //批量删除属性
      multipleSelection:[],
      residentSourceDic:[],

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


      }
    }
  },
  created(){
    this.load()
    // this.tableData=res.data
    // this.total=res.total
    //请求数据
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
        console.log('表单校验',valid)
        if(valid){
          console.log('表单合法！')
          request.post("/travel",this.form).then(res =>{
            //判断是否保存成功
            console.log('post后端')
            if(res) {
              console.log('post后端返回值',res)
              this.$message.success("保存成功!")
              this.dialogFormVisible=false
              this.load()
            }else{
              this.$message.error("保存失败!")
            }
          })
        }else{
          console.log("表单格式非法!")

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
          this.name="",
          this.destination= "",
          this.totalman = "",
          this.load()
    },

    //加载用户信息
    load(){
      //如果模糊查询框值有一个不为空，则只执行模糊查询
      if (this.name == ""&&this.destination==""&&this.totalman==""){
        this.request.get("/travel/nonLocalTravel",{
          params:{
            pageNum:this.pageNum,
            pageSize:this.pageSize,
          }
        }).then(res =>{
          // console.log('residentres',res)

          this.tableData=res.records;
          this.total = res.total
          console.log('res.records-local',res)

        })
      }else {
        //执行模糊查询
        this.request.get("/travel/NonLocalTravelPage",{
              params:{
                pageNum:this.pageNum,
                pageSize:this.pageSize,
                name:this.name,
                destination:this.destination,
                totalman:this.totalman
              }
            }
        ).then(res =>{
          // console.log('residentres',res)
          this.tableData=res.records;
          this.total = res.total
          console.log('res.records-resident',res)

        })
      }

      request.get("/resident/nonLocal",{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,

        }
      })
          .then(res =>{
            // this.tableData1 = res
            console.log('res',res.records)

            for (let result of res.records){
              // console.log('table',table.name)
              console.log('res',res.records)
              this.residentSourceDic.push({
                value:result.id,
                label:result.name
              })
            }
          })
    },
    getOptionValue(v){
      console.log('当前下拉框的值',v)
      request.get("/resident/getResident/"+v)
          .then(res =>{

            console.log('下拉框返回的resident',res)
            this.form = res
            console.log('下拉框返回的resident',this.form)

          })

    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>