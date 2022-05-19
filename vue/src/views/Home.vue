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
                        {{this.inmormalTotal}}
                    </div>

                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card style="color: #409eff">
                    <div >正常人数</div>

                    <div style="padding: 10px 0;text-align: center;font-weight: bold">
                        {{this.normalTotal}}
                    </div>

                </el-card>
            </el-col>
        </el-row>

        <!-- 折线图，饼图 -->
        <el-row>
            <el-col :span="12">
                <div id="travelToolChart"></div>

            </el-col>

            <el-col :span="12">
                <div id="residentHealChart"></div>

            </el-col>
        </el-row>

        <!-- 折线图，饼图 -->
        <el-row>
            <el-col :span="12">
                <div id="tempCountChart"></div>

            </el-col>

            <el-col :span="12">
                <div id="volAddressChart"></div>

            </el-col>
        </el-row>




    </div>
</template>

<script>
    import ChinaMap from "../components/ChinaMap";
    import request from "../utils/request";
    import {Pie,Scatter,Line} from '@antv/g2plot';


    export default {
        name: "Home",
        data(){
            return{
                residentTotal:0,
                outsiderTotal:0,
                normalTotal:0,
                inmormalTotal:0,

                pageNum:1,
                pageSize:20,
                name:"",
                address:"",
            }
        },
        components:{ChinaMap},
        created(){
            this.getData();
            console.log('created======',this.residentTotal,this.outsiderTotal,this.normalTotal)


        },
        mounted() {//页面渲染完成之后触发
            //折线图

            console.log('mounted======',this.residentTotal,this.outsiderTotal,this.normalTotal)

            this.travelToolChart()
            this.residentHealChart()
            this.tempCountChart()
            this.volAddressChart()




        },
        methods:{
            getData(){
                this.request.get("/resident/getTypeOfPeople").then(res =>{
                    this.residentTotal=res[0].countTypeOfPeople
                    this.outsiderTotal=res[1].countTypeOfPeople

                    console.log('local',this.residentTotal)

                })
            //     //
            //     this.request.get("/resident/nonLocal",{
            //             params:{
            //                 pageNum:this.pageNum,
            //                 pageSize:this.pageSize,
            //             }
            //         }
            //     ).then(res =>{
            //         // console.log('residentres',res)
            //         // this.tableData=res.records;
            //         this.outsiderTotal=res.records.length;
            //         console.log('outsiderTotal',this.outsiderTotal)
            //
            //     })
            //     request.get('/health/getAllHealth',{
            //                 params:{
            //                     pageNum:this.pageNum,
            //                     pageSize:this.pageSize,
            //                 }
            //     }).then(res=>{
            //         this.normalTotal=res.records.length
            //         console.log('normalTotal',this.normalTotal)
            //
            //     })
            },

            //2.出行工具的使用人数的环图
            travelToolChart(){
                request.get('/travel/getTravelToolCount').then(res=>{
                    const data = res
                    console.log('出行工具的使用人数的环图====数据',data)
                    const piePlot = new Pie('travelToolChart', {
                        appendPadding: 10,
                        data,
                        angleField: 'countNum',
                        colorField: 'travelTool',
                        radius: 1,
                        innerRadius: 0.64,
                        meta: {
                            value: {
                                formatter: (v) => `¥ ${v}`,
                            },
                        },
                        label: {
                            type: 'inner',
                            offset: '-50%',
                            autoRotate: false,
                            style: { textAlign: 'center' },
                            formatter: ({ percent }) => `${(percent * 100).toFixed(0)}%`,
                        },
                        statistic: {
                            title: {
                                offsetY: -8,
                            },
                            content: {
                                offsetY: -4,
                            },
                        },
                        // 添加 中心统计文本 交互
                        interactions: [
                            { type: 'element-selected' },
                            { type: 'element-active' },
                            {
                                type: 'pie-statistic-active',
                                cfg: {
                                    start: [
                                        { trigger: 'element:mouseenter', action: 'pie-statistic:change' },
                                        { trigger: 'legend-item:mouseenter', action: 'pie-statistic:change' },
                                    ],
                                    end: [
                                        { trigger: 'element:mouseleave', action: 'pie-statistic:reset' },
                                        { trigger: 'legend-item:mouseleave', action: 'pie-statistic:reset' },
                                    ],
                                },
                            },
                        ],
                    });

                    piePlot.render();
                })
                // const data = [
                //     { type: '分类一', value: 27 },
                //     { type: '分类二', value: 25 },
                //     { type: '分类三', value: 18 },
                //     { type: '分类四', value: 15 },
                //     { type: '分类五', value: 10 },
                //     { type: '其他', value: 5 },
                // ];



            },

            //小区人员与健康饼图
            residentHealChart(){
                var data1,data2;

                const data = [
                    { type: '分类一', value: 27 },
                    { type: '分类二', value: 25 },
                    { type: '分类三', value: 18 },
                    { type: '分类四', value: 15 },
                    { type: '分类五', value: 10 },
                    { type: '其他', value: 5 },
                ];

                    request.get('/health/getHealthSituation').then(res=>{
                        const data = res
                        this.normalTotal = res[0].countNormalNum
                        this.inmormalTotal = res[1].countNormalNum
                        console.log('residentHealChart',data)
                        const piePlot = new Pie('residentHealChart', {
                            appendPadding: 10,
                            data,
                            angleField: 'countNormalNum',
                            colorField: 'normal',
                            radius: 0.9,
                            label: {
                                type: 'inner',
                                offset: '-30%',
                                content: ({ percent }) => `${(percent * 100).toFixed(0)}%`,
                                style: {
                                    fontSize: 14,
                                    textAlign: 'center',
                                },
                            },
                            interactions: [{ type: 'element-active' }],
                        });

                        piePlot.render();

                })






            },

            //1.体温人数的散点图
            tempCountChart(){
                request.get('/health/getTempCountChart').then(res=>{
                    const data = res
                    const scatterPlot = new Scatter('tempCountChart', {
                        data,
                        xField: 'temperature',
                        yField: 'countTempNum',
                        size: 5,
                        pointStyle: {
                            stroke: '#777777',
                            lineWidth: 1,
                            fill: '#5B8FF9',
                        },
                        regressionLine: {
                            type: 'quad', // linear, exp, loess, log, poly, pow, quad
                        },
                    });
                    scatterPlot.render();

                })
                // const data = [
                //     { x: 1, y: 4.181 },
                //     { x: 2, y: 4.665 },
                //     { x: 3, y: 5.296 },
                //     { x: 4, y: 5.365 },
                //     { x: 5, y: 5.448 },
                //     { x: 6, y: 5.744 },
                //     { x: 7, y: 5.653 },
                //     { x: 8, y: 5.844 },
                //     { x: 9, y: 6.362 },
                //     { x: 10, y: 6.38 },
                //     { x: 11, y: 6.311 },
                //     { x: 12, y: 6.457 },
                //     { x: 13, y: 6.479 },
                //     { x: 14, y: 6.59 },
                //     { x: 15, y: 6.74 },
                //     { x: 16, y: 6.58 },
                //     { x: 17, y: 6.852 },
                //     { x: 18, y: 6.531 },
                //     { x: 19, y: 6.682 },
                //     { x: 20, y: 7.013 },
                //     { x: 21, y: 6.82 },
                //     { x: 22, y: 6.647 },
                //     { x: 23, y: 6.951 },
                //     { x: 24, y: 7.121 },
                //     { x: 25, y: 7.143 },
                //     { x: 26, y: 6.914 },
                //     { x: 27, y: 6.941 },
                //     { x: 28, y: 7.226 },
                //     { x: 29, y: 6.898 },
                //     { x: 30, y: 7.392 },
                //     { x: 31, y: 6.938 },
                // ];

            },

            //3.工作地点志愿者人数折线图
            volAddressChart(){

                request.get('/volunteer/getVolAddressChart').then(res=>{
                    const data = res
                    const line = new Line('volAddressChart', {
                        data,
                        padding: 'auto',
                        xField: 'workAddress',
                        yField: 'countAddressNum',
                        xAxis: {
                            // type: 'timeCat',
                            tickCount: 5,
                        },
                        smooth: true,
                    });

                    line.render();
                })
            }


        }
    }
</script>

<style scoped>

</style>