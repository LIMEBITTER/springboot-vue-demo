<!--世界疫情地图-->
<template>
    <div class='wrapper'>
        <div class='chart' id='chart'></div>
    </div>
</template>

<script>
    import axios from 'axios'
    import echarts from 'echarts'
    import '../assets/world'
    import jsonp from 'jsonp'
    export default {
        data () {
            return {}
        },
        props:{
            msg:{
                type:Object,
                default:() => {}
            }
        },
        watch:{
            msg:{
                deep: true,
                handler(val) {
                    this.getWorld(val)
                }
            }
        },
        mounted () {
            // this.getWorld(this.msg)
            jsonp('https://interface.sina.cn/news/wap/fymap2020_data.d.json?_=1580892522427',{},(err,data)=>{
                if(!err){
                    // 代表请求数据成功
                    console.log(data);
                    let list = data.data.otherlist.map(item=>({name:item.name,value:item.value}))
                    console.log('chinamap',list);
                    // option.series[0].data = list;
                    this.getWorld(list)
                    // this.mychart.setOption(option);
                    // 这行代码能执行的前提是 DOM已经渲染完成，只有DOM渲染完成才能够执行 echarts初始化
                }
            })
        },
        methods: {
            getWorld (data) {
                var that = this
                axios.get('/world.json').then(function (res) {
                    let namemap = res.data.namemap
                    let dataArr = data
                    // let dataArr = data.data
                    that.drawChart(namemap, dataArr)
                })
            },
            drawChart (name, data) {
                // 基于准备好的dom，初始化echarts实例
                let chart = echarts.init(document.getElementById('chart'))
                // 监听屏幕变化自动缩放图表
                window.addEventListener('resize', function () {
                    chart.resize()
                })
                // 绘制图表
                chart.setOption({
                    // 图表主标题
                    // title: {
                    //   text: '世界地图', // 主标题文本，支持使用 \n 换行
                    //   top: 10, // 定位 值: 'top', 'middle', 'bottom' 也可以是具体的值或者百分比
                    //   left: 'center', // 值: 'left', 'center', 'right' 同上
                    //   textStyle: { // 文本样式
                    //     fontSize: 24,
                    //     fontWeight: 600,
                    //     color: '#000'
                    //   }
                    // },
                    title:{
                        text:"全球疫情累计确诊图",
                        // link:"https://blog.csdn.net/image_fzx",
                        subtext:"数据来源---微博api(不包含中国)",
                        // sublink:"https://blog.csdn.net/image_fzx"
                    },
                    grid: {
                        width:'100%',
                        height:'100%',
                        left: '0%',
                        right: '0%',
                        bottom: '0%',
                        containLabel: true
                    },
                    // 提示框组件
                    tooltip: {
                        trigger: 'item', // 触发类型, 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用
                        // 提示框浮层内容格式器，支持字符串模板和回调函数两种形式
                        // 使用函数模板  传入的数据值 -> value: number | Array
                        formatter: function (val) {
                            if(val.data == null) return ;
                            return val.data.name + ': ' + val.data.value
                        }
                    },
                    // 视觉映射组件
                    visualMap: {
                        type:'piecewise',
                        show:true,
                        splitNumber:8,
                        pieces:[           // 分段
                            {min:10000000},
                            {min:5000000,max:10000000},
                            {min:1000000,max:5000000},
                            {min:100000,max:1000000},
                            {min:10000,max:100000},
                            {min:5000,max:9999},
                            {min:1,max:4999},
                            {min:0,max:0}
                        ],
                        inRange:{
                            symbol:'rect',
                            color:['white','#9c0505']   //   浅红~~深红色
                        },                    },
                    series: [
                        {
                            type: 'map', // 类型
                            // 系列名称，用于tooltip的显示，legend 的图例筛选 在 setOption 更新数据和配置项时用于指定对应的系列
                            name: '世界地图',
                            mapType: 'world', // 地图类型
                            // 是否开启鼠标缩放和平移漫游 默认不开启 如果只想要开启缩放或者平移，可以设置成 'scale' 或者 'move' 设置成 true 为都开启
                            roam: true,
                            // 图形上的文本标签
                            label: {
                                show: false // 是否显示对应地名
                            },
                            zoom: 1.2,
                            // 地图区域的多边形 图形样式
                            itemStyle: {
                                // areaColor: '#7B68EE', // 地图区域的颜色 如果设置了visualMap，areaColor属性将不起作用
                                borderWidth: 0.5, // 描边线宽 为 0 时无描边
                                borderColor: '#000', // 图形的描边颜色 支持的颜色格式同 color，不支持回调函数
                                borderType: 'solid' // 描边类型，默认为实线，支持 'solid', 'dashed', 'dotted'
                            },
                            // 高亮状态下的多边形和标签样式
                            emphasis: {
                                label: {
                                    show: true, // 是否显示标签
                                    color: 'black' // 文字的颜色 如果设置为 'auto'，则为视觉映射得到的颜色，如系列色
                                },
                                itemStyle: {
                                    areaColor: '#FF6347' // 地图区域的颜色
                                }
                            },
                            // 自定义地区的名称映射
                            nameMap: name,
                            // 地图系列中的数据内容数组 数组项可以为单个数值
                            data: data
                        }
                    ]
                })
            }
        }
    }
</script>
<style scoped>
    .wrapper {
        /*width: 100%;*/
        /*width: 800px;*/
    }
    .wrapper .chart {
        width: 100%;
        margin:0 auto;
        height: 600px;
        border: 1px solid #eeeeee;
        /* background: url(../../public/static/bg.png) no-repeat; 背景图设置*/
        background-color: white;
        background-size: 100% 100%;
    }
</style>