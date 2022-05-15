<!--国外疫情数据-->
<template>
    <div>
        <strong>海外疫情</strong>
        <p style="color: #999999;font-size:12px;margin-top: 6px">统计截至 {{formatDate(worldNum.desc.modifyTime)}}</p>
        <table style="font-size: 12px;text-align: center;border-spacing:17px 10px " >
            <tr>
                <td>累计确诊</td>
                <td>累计治愈</td>
                <td>累计死亡</td>
            </tr>
            <tr style="font-weight: bold;font-size: 28px">
                <td style="color: #FF3535">{{worldNum.desc.foreignStatistics.confirmedCount |capitalize}}</td>
                <td style="color: #FE9885">{{worldNum.desc.foreignStatistics.curedCount |capitalize}}</td>
                <td style="color: #A36FFF">{{worldNum.desc.foreignStatistics.deadCount |capitalize}}</td>

            </tr>
            <tr>
                <td><span class="text">较昨日</span><span class="data">+{{worldNum.desc.foreignStatistics.confirmedIncr}}</span></td>
                <td><span class="text">较昨日</span><span class="data">+{{worldNum.desc.foreignStatistics.curedIncr}}</span></td>
                <td><span class="text">较昨日</span><span class="data">+{{worldNum.desc.foreignStatistics.deadIncr}}</span></td>

            </tr>
        </table>

    </div>

</template>

<script>
    export default {
        name: "WorldData",
        props: {
            worldNum:{},
        },
        filters: {
            capitalize: function (val) {
                return (+val || 0).toFixed(0).replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,')
            }
        },
        methods: {
            // 格式化日期和时间
            formatDate(date){
                var date = new Date();
                var YY = date.getFullYear()+"-";
                var MM = (date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) + "-" : date.getMonth() + "-";
                var DD = date.getDate() < 10 ? "0" + date.getDate() + "  " : date.getDate() + "  ";
                var hh = date.getHours() < 10 ? "0" + date.getHours() + ":" : date.getHours() + ":";
                var mm = date.getMinutes() <  10 ? "0" + date.getMinutes() : date.getMinutes() ;
                var ss = date.getSeconds() <  10 ? "0" + date.getSeconds() : date.getSeconds();

                return YY +  MM + DD + hh + mm +":"+ ss;
            }
        }



    }
</script>

<style scoped>
.text{
    color: #999999;
    font-size:12px;
}
div{
    width: 600px;
}
.data{
    margin-left: 2px;
    font-weight: bold;
    color: #666;
}
</style>