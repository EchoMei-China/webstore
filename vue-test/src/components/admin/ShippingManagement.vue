<template>
    <div class="shipping-management">
        <div class="shipping-management-title">
            <input type="text" placeholder="根据省份查询" v-model="searchInformation" class="shipping-management-title-input">
        </div>
        <div class="shipping-management-body">
            <div class="shippingmanagement-body-title">
                <div class="shippingmanagement-body-pid">编号</div>
                <div class="shippingmanagement-body-pname">省份</div>
                <div class="shippingmanagement-body-cname">城市</div>
                <div class="shippingmanagement-body-dname">地区</div>
                <div class="shippingmanagement-body-shipping">邮费</div>
                <div class="shippingmanagement-body-operator">操作</div>
            </div>
            <div class="shippingmanagement-body-item" v-for="item in districts" :key="item.did">
                <div class="shippingmanagement-body-pid"> {{item.city.province.pid}} </div>
                <div class="shippingmanagement-body-pname"> {{item.city.province.pname}} </div>
                <div class="shippingmanagement-body-cname"> {{item.city.cname}} </div>
                <div class="shippingmanagement-body-dname"> {{item.dname}} </div>
                <div class="shippingmanagement-body-shipping"> {{item.shipping}} </div>
                <div class="shippingmanagement-body-operator">
                    <a href="javascript:;" @click="_modifyShipping(item.city.province.pid, item.city.province.pname)">修改邮费</a>
                </div>
            </div>
            <div class="shippingmanagement-body-modalbox">
                <div class="shippingmanagementbody-modal-close" @click="_closeModalBox()">X</div>
                <div class="shippingmanagementbody-modal-title"> {{Pname}} </div>
                <div class="shippingmanagementbody-modal-row">
                    <div class="shippingmanagementbody-modal-left">邮费</div>
                    <div class="shippingmanagementbody-modal-right">
                        <input type="text" v-model="Shipping">
                    </div>
                </div>
                <div class="shippingmanagement-modal-btn" @click="_confimModify()">确认修改</div>
            </div>
            <el-pagination
                class="shipping-management-body-pagination"
                layout="prev, pager, next"
                :current-page.sync="currentPage"
                :pager-count="fold"
                :hide-on-single-page="hide"
                :page-size="pageSize"
                :total="total"
                @current-change="_handlePageChange()">
            </el-pagination>
        </div>
    </div>
</template>

<script>
export default {
    name: 'ShippingManagement',

    inject:['reload'], //调用刷新

    data() {
        return {
            searchInformation: null,
            districts: [],
            pageSize: 10,
            currentPage: 1,
            total: 0,
            hide: false,
            fold: 5,
            Pname: '',
            Shipping: null,
        }
    },

    created() {
        /**
         * 获取全部数据
         */
        let postData = this.qs.stringify({
            districts: this.districts
        }) 
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/district/obtainAllDistricts',
            data: postData
        }).then((response) => {
            this.districts = response.data.districts;
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获取总数
         */
        let postData1 = this.qs.stringify({
            districts: this.districts
        }) 
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/district/obtainAllCount',
            data: postData1
        }).then((response) => {
            this.total = response.data;
            console.log(this.total);
        }).catch((error) => {
            console.log(error);
        });
    },

    mounted() {
        let that = this;
        let search = document.querySelector(".shipping-management-title-input");
        search.addEventListener("keyup", function(e) {
            if (e.keyCode == 13) {
                // console.log(that.searchInformation);
                let postData = that.qs.stringify({
                    searchInformation: that.searchInformation
                })
                that.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/district/searchDistricts',
                    data: postData
                }).then((response) => {
                    that.districts = response.data.districts;
                }).catch((error) => {
                    console.log(error);
                });
            }
        })
    },

    methods: {
        /**
         * 分页
         */
        _handlePageChange() {
            let postData1 = this.qs.stringify({
                startIndex: (this.currentPage - 1)*this.pageSize, 
                pageSize: this.pageSize,
            });
            console.log(postData1);
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/district/ManageCurrentContentShipping',
                data: postData1
            }).then(response => {
                this.districts = response.data.districts;
            }).catch(error => {
                console.log(error);
            });
        },

        /**
         * 修改邮费
         */
        _modifyShipping(pid, pname) {
            let modalBox = document.querySelector(".shippingmanagement-body-modalbox");
            modalBox.style.display = 'block';
            modalBox.setAttribute('pid', pid);
            this.Pname = pname;
        },

        /**
         * 关闭模态框
         */
        _closeModalBox() {
            let modalBox = document.querySelector(".shippingmanagement-body-modalbox");
            modalBox.style.display = 'none';
        },

        /**
         * 确认修改
         */
        _confimModify() {
            let modalBox = document.querySelector(".shippingmanagement-body-modalbox");
            let pid = modalBox.getAttribute('pid');
            let postData = this.qs.stringify({
                pid: pid,
                shipping: this.Shipping
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/district/changeShipping',
                data: postData
            }).then((response) => {
                this.$message.success("修改成功");
                this.reload();
            }).catch((error) => {
                console.log(error);
                this.$message.warning("修改失败")
            });
        },

        // /**
        //  * 搜索
        //  */
        // _searchPRovince() {
        //     let postData = this.qs.stringify({
        //         searchInformation: this.searchInformation
        //     })
        //     this.axios({
        //         method: 'post',
        //         url: '/ssm_project_war_exploded/district/searchDistricts',
        //         data: postData
        //     }).then((response) => {
        //         this.districts = response.data.districts;
        //     }).catch((error) => {
        //         console.log(error);
        //     });
        // }
    }
}
</script>

<style>
.shipping-management {
    margin-bottom: 100px;
    width: 970px;
}

.shipping-management-title {
    display: flex;
    height: 44px;
    background-color: rgb(175, 206, 125);
}

.shipping-management-title input {
    margin: 4px 0 4px 15px;
    padding: 0 8px;
    width: 200px;
    height: 34px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.shipping-management-body {
    position: relative;
    margin: 35px 0 0 0;
}

.shippingmanagement-body-title {
    display: flex;
    font-size: 14px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    background-color: rgb(180, 224, 191);
}

.shippingmanagement-body-pid {
    width: 110px;
}

.shippingmanagement-body-pname {
    width: 200px;
}

.shippingmanagement-body-cname {
    width: 200px;
}

.shippingmanagement-body-dname {
    width: 200px;
}

.shippingmanagement-body-shipping {
    width: 120px;
}

.shippingmanagement-body-operator {
    width: 140px;
}

.shippingmanagement-body-item {
    display: flex;
    margin-top: 10px;
    text-align: center;
    height: 35px;
    line-height: 20px;
    border-bottom: 1px solid #ccc;
}

.shipping-management-body-pagination {
    margin-top: 30px;
    text-align: center;
}

.shippingmanagement-body-modalbox {
    display: none;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -200px;
    margin-left: -200px;
    width: 400px;
    height: 250px;
    border-radius: 5px;
    color: white;
    background-color: green;
    z-index: 99;
}

.shippingmanagementbody-modal-close {
    position: absolute;
    top: 0;
    right: 0;
    width: 40px;
    height: 40px;
    font-size: 20px;
    font-weight: 500;
    text-align: center;
    line-height: 40px;
    color: white;
    cursor: pointer;
}

.shippingmanagementbody-modal-title {
    margin-top: 30px;
    margin-left: 30px;
    font-size: 14px;
    font-weight: 500;
}

.shippingmanagementbody-modal-row {
    display: flex;
    margin-top: 30px;
    height: 40px;
}

.shippingmanagementbody-modal-left {
    width: 100px;
    font-size: 16px;
    line-height: 40px;
    text-align: right;
}

.shippingmanagementbody-modal-right {
    margin-left: 30px;
}

.shippingmanagementbody-modal-right input {
    padding: 0 8px;
    width: 200px;
    height: 35px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.shippingmanagement-modal-btn {
    margin: 30px 0 0 130px;
    width: 120px;
    height: 35px;
    line-height: 35px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
    color: green;
    background-color: white;  
    border-radius: 5px; 
    cursor: pointer;
}
</style>