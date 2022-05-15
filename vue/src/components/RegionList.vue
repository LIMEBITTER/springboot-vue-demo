<template>
    <div style="width:700px; display: inline-block">
        <el-table
                :data="tableData"
                height="250"
                border
                style="width: 100%">
            <el-table-column
                    prop="province"
                    label="地区"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="nowConfirm"
                    label="现有"
                    sortable
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="confirm"
                    label="累计"
                    sortable
                    width="180">
                <template slot-scope="scope">
                    {{scope.row.confirm}}
                    <br>
                    <span>较昨日+{{scope.row.confirmAdd}}</span>

                </template>
            </el-table-column>
            <el-table-column
                    prop="confirmAdd"
                    label="较昨日增加"
                    sortable
                    v-if="false"
                    width="180">

            </el-table-column>
            <el-table-column
                    prop="heal"
                    label="治愈"
                    sortable
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="dead"
                    label="死亡"
                    sortable
                    >
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "RegionList",
        data(){
            return{
                tableData:[

                ]
            }
        },
        created() {
            this.getData()
        },
        methods:{
            getData(){
                this.$axios.post('/aki/newsqa/v1/query/inner/publish/modules/list?modules=statisGradeCityDetail,diseaseh5Shelf')
                    .then((res) => {
                        // var jsonData = JSON.parse(res.data)
                        let list = res.data.data.diseaseh5Shelf.areaTree[0].children.map(item=>({province:item.name,nowConfirm:item.total.nowConfirm,confirm:item.total.confirm,confirmAdd:item.today.confirm,heal:item.total.heal,dead:item.total.dead}))
                        this.tableData = list
                        // this.provinceList = jsonData
                        // this.provinceList = jsonData1
                        // console.log('provinceList',jsonData1)
                        console.log('regionlist：',res.data.data.diseaseh5Shelf.areaTree[0].children.map(item=>({province:item.name,nowConfirm:item.total.nowConfirm,confirm:item.total.confirm,confirmAdd:item.today.confirm,heal:item.total.heal,dead:item.total.dead})))
                        // console.log('res',res.data.data)
                    })
            }
            // //设置表格行的样式
            // tableRowStyle({row,rowIndex}){
            //     return 'background-color:pink;font-size:15px;'
            // },
            // //设置表头行的样式
            // tableHeaderColor({row,column,rowIndex,columnIndex}){
            //     return 'background-color:lightblue;color:#fff;font-wight:500;font-size:20px;text-align:center'
            //
            // }
        }

    }



</script>

<style>

    /*chrome滚动条颜色设置*/

    *::-webkit-scrollbar {width:7px; height:10px; background-color:transparent;} /*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
    *::-webkit-scrollbar-track {background-color:#f0f6ff;  } /*定义滚动条轨道 内阴影+圆角*/
    *::-webkit-scrollbar-thumb {background-color:#73abb1; border-radius:6px;} /*定义滑块 内阴影+圆角*/
    .scrollbarHide::-webkit-scrollbar{display: none}
    .scrollbarShow::-webkit-scrollbar{display: block}


</style>