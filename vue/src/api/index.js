import axios from '../utils/request'
import base from './base'

const api = {
    getNcov(params) {
        // console.log(base.baseUrl + base.ncov); //http://iwenwiki.com/wapicovid19/ncov.php
        // console.log(params); //{key: '62e34ad34025d5d5127135efa58d4ca8'}
        return axios.get(base.baseUrl + base.ncov, {
            params
        });
    },


    // getNcov() {
    //
    //     return axios.get(base.baseUrl);
    // },

    /**
     * 各个省市疫情数据
     */
    // getProvinceNcov() {
    //     return axios.get(base.baseUrl + base.provinceNcov);
    // },
};

export default api;