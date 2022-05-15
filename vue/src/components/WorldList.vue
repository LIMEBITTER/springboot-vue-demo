<template>
    <div style="width: 800px;display: inline-block;float: right;margin-right: 30px">
        <el-table
                :data="tableData"
                style="width: 100%;margin-bottom: 20px;"
                border
                height="250"
                default-expand-all
                :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
            <el-table-column
                    prop="name"
                    label="地区"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="confirmAdd"
                    label="新增确诊"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="confirm"
                    label="累计确诊"
                    sortable
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
                    label="死亡">
            </el-table-column>
        </el-table>

    </div>
</template>

<script>
    export default {
        name: "WorldList",
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
                this.$axios.post('/aki/newsqa/v1/automation/modules/list?modules=FAutoCountryConfirmAdd,WomWorld,WomAboard')
                    .then((res) => {
                        // var jsonData = JSON.parse(res.data)
                        let dataArr  = res.data.data.WomAboard.map(item=>(
                            {
                                continent:item.continent,
                                name:item.name,
                                confirm:item.confirm,
                                confirmAdd:item.confirmAdd,
                                heal:item.heal,
                                dead:item.dead,

                            }
                        ))

                        this.tableData = dataArr
                        // this.provinceList = jsonData
                        // this.provinceList = jsonData1
                        // console.log('provinceList',jsonData1)
                        // console.log('Worldlist：',JSON.stringify(dataArr))
                        // console.log('res',res.data.data)
                    })
            },
            combineArr(){
                var beforeData = dataArr;      //将dataArr赋值给beforeData  也可直接操作dataArr
                let tempArr = [];
                let afterData = [];//新数组
                for (let i = 0; i < beforeData.length; i++) {
                    if (tempArr.indexOf(beforeData[i].id) === -1) {
                        afterData.push({
                            id: beforeData[i].id,
                            score: beforeData[i].score,
                            name: [beforeData[i].name],
                        });
                        tempArr.push(beforeData[i].id);
                    } else {
                        for (let j = 0; j < afterData.length; j++) {
                            if (afterData[j].id == beforeData[i].id) {
                                afterData[j].name.push(beforeData[i].name);
                                break;
                            }
                        }
                    }
                }
                //循环afterData并且合并name值用逗号隔开
                afterData.map((item) => {
                    item.name= item.name.join(",");
                });
                console.log(afterData,'afterData')
            }
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