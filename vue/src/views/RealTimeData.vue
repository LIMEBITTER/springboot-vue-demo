
<template>
    <div>



<!--        <world-map></world-map>-->
<!--        <case-num :case-num="caseNum"></case-num>-->

        <!--各省疫情-->
<!--        <province-list :provinceList="provinceList"></province-list>-->

        <el-row>
            <el-col :span="12">
                <!--国内疫情-->
                <ChinaData :case-num="caseNum"></ChinaData>
            </el-col>

            <el-col :span="12">
                <!--国外疫情-->
                <world-data :world-num="worldNum"></world-data>
            </el-col>
        </el-row>
                <!--疫情地图-->
                <region-list></region-list>


                <!--国外疫情-->
                <world-list></world-list>


    </div>
</template>

<script>
    import api from "../api";
    import ChinaData from "../components/ChinaData";
    import ChinaMap from "../components/ChinaMap";
    import WorldData from "../components/WorldData";
    import WorldMap from "../components/WorldMap";
    import RegionList from "../components/RegionList";
    import WorldList from "../components/WorldList";


    export default {
        name: "RealTimeData",
        data () {
            return {
                caseNum:{}, //国内疫情
                worldNum:{}, //世界疫情
                provinceList:{} //各省疫情
            }
        },
        mounted () {
            //调取腾讯api
            this.$axios.post('/api/g2/getOnsInfo?name=disease_h5')
                .then((res) => {
                    // console.log('腾讯api：',JSON.stringify(res.data.data.chinaTotal))

                    var jsonData = JSON.parse(JSON.stringify(res.data.data))
                    console.log('腾讯api',jsonData)
                    this.caseNum = jsonData
                    // this.provinceList = jsonData
                    // this.provinceList = jsonData1
                    // console.log('provinceList',jsonData1)
                    // console.log('res',res.data.data)
            })
            // console.log('provinceList',this.provinceList)

            //丁香医生api
            api.getNcov({
                key:"62e34ad34025d5d5127135efa58d4ca8"
            }).then(res=>{
                // console.log('丁香医生api：',res)
                if(res.code === 200){
                    this.worldNum = res.newslist[0]
                    console.log('丁香医生api：',this.worldNum)
                    console.log('...',res)
                }
            }).catch(error=>{
                console.log(error);
            })


            // this.$axios.get('/aki/wapicovid19/ncov.php?key=62e34ad34025d5d5127135efa58d4ca8')
            //     .then((res) => {
            //                 this.worldNum = res.newslist[0]
            //                 console.log('丁香医生api：',this.worldNum)
            //                 console.log('...',res)
            //     })





            //微博api
            // jsonp('https://interface.sina.cn/news/wap/fymap2020_data.d.json?_=1580892522427',{},(err,data)=>{
            //     if(!err){
            //         // 代表请求数据成功
            //         console.log(data);
            //         let list = data.data.list.map(item=>({name:item.name,value:item.value}))
            //         console.log(list);
            //         option.series[0].data = list;
            //         this.mychart.setOption(option);
            //         // 这行代码能执行的前提是 DOM已经渲染完成，只有DOM渲染完成才能够执行 echarts初始化
            //     }
            // })

        },

        methods:{

        },

        components:{ChinaMap,ChinaData,WorldData,WorldMap,RegionList,WorldList}

    }
</script>

<style scoped>

</style>