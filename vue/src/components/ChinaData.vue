<!--国内疫情数据-->
<template>
    <div>
        <strong>国内疫情</strong>
        <p style="color: #999999;font-size:12px;margin-top: 6px">统计截至 {{caseNum.lastUpdateTime}}</p>
        <table style="font-size: 12px;text-align: center;border-spacing:17px 10px " >
            <tr>
                <td>现存本土确诊</td>
                <td>现存确诊</td>
                <td>现存无症状</td>
                <td>现存疑似</td>
            </tr>
            <tr style="font-weight: bold;font-size: 28px">
                <td style="color: #B8540E">{{caseNum.chinaTotal.localConfirmH5}}</td>
                <td style="color: #FF3535">{{caseNum.chinaTotal.nowConfirm}}</td>
                <td style="color: #FE9885">{{caseNum.chinaTotal.noInfect}}</td>
                <td style="color: #A36FFF">{{caseNum.chinaTotal.suspect}}</td>

            </tr>
            <tr>
                <td><span class="text">较昨日</span><span class="data">{{numZore(caseNum.chinaAdd.localConfirmH5)}}</span></td>
                <td><span class="text">较昨日</span><span class="data">{{numZore(caseNum.chinaAdd.nowConfirm)}}</span></td>
                <td><span class="text">较昨日</span><span class="data">{{numZore(caseNum.chinaAdd.noInfect)}}</span></td>
                <td><span class="text">较昨日</span><span class="data">{{numZore(caseNum.chinaAdd.suspect)}}</span></td>
            </tr>
            <tr  >
                <td>累计确诊</td>
                <td>累计境外输入</td>
                <td>累计治愈</td>
                <td>累计死亡</td>
            </tr>
            <tr style="font-weight: bold;font-size: 28px">
                <td style="color: #B10000">{{caseNum.chinaTotal.confirm}}</td>
                <td style="color: #FE6B22">{{caseNum.chinaTotal.importedCase}}</td>
                <td style="color: #13B593">{{caseNum.chinaTotal.heal}}</td>
                <td style="color: #666666">{{caseNum.chinaTotal.dead}}</td>

            </tr>
            <tr>
                <td><span class="text">较昨日</span><span class="data">+{{caseNum.chinaAdd.confirm}}</span></td>
                <td><span class="text">较昨日</span><span class="data">+{{caseNum.chinaAdd.importedCase}}</span></td>
                <td><span class="text">较昨日</span><span class="data">+{{caseNum.chinaAdd.heal}}</span></td>
                <td><span class="text">较昨日</span><span class="data">+{{caseNum.chinaAdd.dead}}</span></td>

            </tr>
        </table>

    </div>

</template>

<script>
    export default {
        name: "DoecData",
        props: {
            caseNum:{},
        },
        mounted() {
            console.log('DoecData',this.caseNum)
            //符号正负问题
        },
        methods: {
            //处理正负问题
            numZore(num){
                return num >= 0 ? "+" + num : num;
            },

        }
    }
</script>

<style scoped>
.text{
    color: #999999;
    font-size:12px;
}

div{
    width: 500px;
    margin-bottom: 50px;
}
.data{
    margin-left: 2px;
    font-weight: bold;
    color: #666;
}
</style>