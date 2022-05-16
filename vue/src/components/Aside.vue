<!--导航菜单-->
<template>
    <!--router 模式：转至子项路由-->
    <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
             background-color="rgb(48, 65, 86)"
             text-color="#fff"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
             router
    >
        <div style="height: 60px; line-height: 60px; text-align: center">
<!--            <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">-->
            <b style="color: white" v-show="logoTextShow">小区疫情管理系统</b>
        </div>

        <el-submenu index="1">
            <template slot="title">
                <i class="el-icon-menu"></i>
                <span>用户</span>
            </template>
            <el-menu-item index="/person">
                <span slot="title">用户信息</span>
            </el-menu-item>
        </el-submenu>

        <el-submenu index="2" v-if="user_role.isAdmin===1">
            <template slot="title">
                <i class="el-icon-menu"></i>
                <span slot="title">人员管理</span>
            </template>
            <el-submenu index="2-1">
                <template slot="title">信息管理</template>
<!--                <el-menu-item index="/admin/user">-->
<!--                    <i class="el-icon-s-custom"></i>-->
<!--                    <span slot="title">后台管理人员</span>-->
<!--                </el-menu-item>-->
                <el-menu-item index="/admin/resident">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">小区居住人员</span>
                </el-menu-item>
                <el-menu-item index="/admin/outsider">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">非小区人员</span>
                </el-menu-item>
            </el-submenu>

            <el-submenu index="2-2">
                <template slot="title">出入管理</template>
                <el-menu-item index="/admin/residentTravel">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">小区人员出入</span>
                </el-menu-item>
                <el-menu-item index="/admin/outsiderTravel">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">非小区人员出入</span>
                </el-menu-item>
            </el-submenu>

            <el-submenu index="2-3">
                <template slot="title">健康管理</template>
                <el-menu-item index="/admin/health">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">人员健康信息</span>
                </el-menu-item>
            </el-submenu>

            <el-submenu index="2-4">
                <template slot="title">志愿者值班管理</template>
                <el-menu-item index="/admin/volunteer">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">小区志愿者人员</span>
                </el-menu-item>
            </el-submenu>
        </el-submenu>

        <el-submenu index="3" v-if="user_role.isAdmin===0">
            <template slot="title">
                <i class="el-icon-menu"></i>
                <span slot="title">信息填报</span>
            </template>


                <el-menu-item index="/users/basicInfo">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">基本信息填报</span>
                </el-menu-item>
                <el-menu-item index="/users/travelInfo">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">出入申报</span>
                </el-menu-item>
                <el-menu-item index="/users/HealthInfo">
                    <i class="el-icon-s-custom"></i>
                    <span slot="title">健康申报</span>
                </el-menu-item>
            </el-submenu>




        <el-submenu index="4">
            <template slot="title">
                <i class="el-icon-menu"></i>
                <span>可视化</span>
            </template>
            <el-menu-item index="/realTimeData">
                <i class="el-icon-s-custom"></i>
                <span slot="title">全国疫情</span>
            </el-menu-item>
            <el-menu-item index="/">
                <i class="el-icon-s-custom"></i>
                <span slot="title">小区疫情</span>
            </el-menu-item>
        </el-submenu>

    </el-menu>
</template>

<script>
    export default {
        name: "Aside",
        data(){
            return{
                user_role: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},

            }
        },
        // created() {
        //     console.log('user_role',this.user_role.isAdmin)
        // },
        props: {
            isCollapse: Boolean,
            logoTextShow: Boolean
        }
    }
</script>

<style scoped>

</style>
