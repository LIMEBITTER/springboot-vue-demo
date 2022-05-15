<template>
    <div>
        <!--概览-->
        <el-row :gutter="10" style="margin-bottom: 60px">
            <el-col :span="6">
                <el-card style="color: #909399">
                    <div >
                        <!--这边i标签实现字体图标-->
                        <i class=""></i>
                        小区居住人数
                    </div>

                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        {{this.residentTotal}}
                    </div>

                </el-card>

            </el-col>
            <el-col :span="6">
                <el-card style="color: #67c23a">
                    <div>非小区人员数</div>
                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        {{this.outsiderTotal}}
                    </div>

                </el-card>
            </el-col>

            <el-col :span="6">
                <el-card style="color: #e6a230">
                    <div >隔离人数</div>

                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        2
                    </div>

                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card style="color: #409eff">
                    <div >正常人数</div>

                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        8
                    </div>

                </el-card>
            </el-col>
        </el-row>

        <!-- 折线图，饼图 -->
        <el-row>
            <el-col :span="12">
                <div id="main" style="width: 500px; height: 400px"></div>

            </el-col>

            <el-col :span="12">
                <div id="pie" style="width: 500px; height: 400px"></div>

            </el-col>
        </el-row>




    </div>
</template>

<script>
    import * as echarts from 'echarts'
    import ChinaMap from "../components/ChinaMap";
    import request from "../utils/request";

    export default {
        name: "Home",
        data(){
            return{
                residentTotal:0,
                outsiderTotal:0,
                normalTotal:0,

                pageNum:1,
                pageSize:5,
                name:"",
                address:"",
            }
        },
        components:{ChinaMap},
        created(){
          this.getData();
        },
        mounted() {//页面渲染完成之后触发
            //折线图
            var color = ["#CC00FF","#FFC0CB","#FF69B4","#DDA0DD","#FF00FF","#9400D3","#8A2BE2"]
            var option = {
                title: {
                    text: '小区疫情',
                    subtext: 'Fake Data',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                xAxis: {
                    type: 'category',
                    data: ['小区居住人数', '非小区人数', '隔离人数', '正常人数']
                },
                yAxis: {
                    type: 'value'
                },
                series: [

                    {
                        data: [6,4,2,8],
                        type: 'bar',
                        itemStyle: {
                            color: function (params) {
                                return color[params.dataIndex];
                            }
                        }
                    },
                ]
            };
            var chartDom = document.getElementById('main');
            var myChart = echarts.init(chartDom);
            myChart.setOption(option);

            //饼图
            var pieOption = {
                title: {
                    text: 'Referer of a Website',
                    subtext: 'Fake Data',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: '60%',

                        label:{            //饼图图形上的文本标签
                            normal:{
                                show:true,
                                position:'inner', //标签的位置
                                textStyle : {
                                    fontWeight : 300 ,
                                    fontSize : 14,    //文字的字体大小
                                    color: "#fff"
                                },
                                formatter:'{d}%'
                            }
                        },

                        data: [
                            { value: 6, name: '小区居住人数' },
                            { value: 4, name: '非小区人数' },
                            { value: 2, name: '隔离人数' },
                            { value: 8, name: '正常人数' },
                        ],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            var pieDom = document.getElementById('pie');
            var pieChart = echarts.init(pieDom)
            pieChart.setOption(pieOption);

        },
        methods:{
            getData(){
                this.request.get("/resident/local",{
                        params:{
                            pageNum:this.pageNum,
                            pageSize:this.pageSize,

                        }
                    }
                ).then(res =>{
                    this.residentTotal=res.total;
                    console.log('home',res)

                })

                this.request.get("/resident/nonLocal",{
                        params:{
                            pageNum:this.pageNum,
                            pageSize:this.pageSize,
                        }
                    }
                ).then(res =>{
                    // console.log('residentres',res)
                    // this.tableData=res.records;
                    this.outsiderTotal=res.total;
                    console.log('home',res)

                })

            }



        }
    }
</script>

<style scoped>

</style>