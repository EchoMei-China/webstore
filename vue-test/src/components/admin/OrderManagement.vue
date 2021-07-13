<template>
    <div class="order-management">
        <div class="order-management-title">
            <input type="text" placeholder="根据订单号查询" v-model="oidSnippet" class="order-management-title-input">
        </div>
        <div class="order-management-body">
            <div class="ordermanage-body-title">
                <div class="ordermanage-body-ordernumber">订单号</div>
                <div class="ordermanage-body-username">用户名</div>
                <div class="ordermanage-body-address">收货地址</div>
                <div class="ordermanage-body-status">订单状态</div>
                <div class="ordermanage-body-shipping">发货操作</div>
                <div class="ordermanage-body-modify">订单操作</div>
            </div>
            <div class="ordermanage-body-item" v-for="item in orders" :key="item.oid">
                <div class="ordermanage-body-ordernumber">{{item.oid}}</div>
                <div class="ordermanage-body-username">{{item.user.userNickname}}</div>
                <div class="ordermanage-body-address">{{item.useraddress}}</div>
                <div class="ordermanage-body-status">
                    <span v-show="item.opay == 0">待付款</span>
                    <span v-show="item.opay == 1 && item.oshipping == 0">待发货</span>
                    <span v-show="item.oshipping == 1 && item.osignfor == 0">待签收</span>
                    <span v-show="item.osignfor == 1 && item.ocomment == 0">待评价</span>
                    <span v-show="item.ocomment == 1">已完成</span>
                </div>
                <div class="ordermanage-body-shipping">
                    <div class="ordermanage-body-shipping-btn" v-show="item.opay == 1 && item.oshipping == 0" @click="_manageShipping(item.oid)">发货</div>
                </div>
                <div class="ordermanage-body-modify">
                    <div><a href="javascript:;" @click="_modifyOrder(item.oid)">修改</a></div>
                    <div><a href="javascript:;" @click="_deleteOrder(item.oid)">删除</a></div>
                </div>
            </div>
            <div class="ordermanage-body-modify-box">
                <div class="ordermanagebody-modifybox-close" @click="_closeModifyBox()">X</div>
                <div class="ordermanagebody-modifybox-row">
                    <div class="ordermanagebody-modifybox-left">更新地址</div>
                    <div class="ordermanagebody-modifybox-right">
                        <textarea name="" id="" cols="20" rows="6" class="ordermanagebody-modify-textarea" v-model="newAddress"></textarea>
                    </div>
                </div>
                <div class="ordermanagebody-modifybox-btn" @click="_submitChange()">提交</div>
            </div>
            <el-pagination
                class="order-management-body-pagination"
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
import qs from 'qs';
import axios from "axios";

export default {
    name: 'OrderManagement',

    inject:['reload'], //调用刷新

    data() {
        return {
            newAddress: null,
            oidSnippet: null,
            orders: [],
            pageSize: 10,
            currentPage: 1,
            total: 0,
            hide: false,
            fold: 5,
        }
    },

    created() {
        /**
         * 获取所有的订单
         */
        let postData = this.qs.stringify({
            orders: this.orders
        })
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/order/obtainManageAllOrders',
            data: postData
        }).then((response) => {
            this.orders = response.data.orders
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获取订单总数
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/order/obtainManageAllOrdersCount',
            data: postData
        }).then((response) => {
            this.total = response.data;
        }).catch((error) => {
            console.log(error);
        });
    },

    mounted() {
        let that = this;
        let input = document.querySelector(".order-management-title-input");
        input.addEventListener('keyup', function(event) {
            if (event.keyCode == 13) {
                let postData = qs.stringify({
                    oidSnippet: that.oidSnippet
                })
                axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/order/obtainLikeByOid',
                    data: postData
                }).then((response) => {
                    that.orders = response.data.orders
                }).catch((error) => {
                    console.log(error);
                });
            }
        })
    },

    methods: {
        /**
         * 删除订单
         */
        _deleteOrder(oid) {
            let postData = this.qs.stringify({
                oid: oid
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/deleteOrderByOid',
                data: postData
            }).then((response) => {
                this.$message.success("删除成功");
                this.reload();
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 分页
         */
        _handlePageChange() {
            // console.log((this.currentPage - 1)*this.pageSize);
            let postData1 = this.qs.stringify({
                startIndex: (this.currentPage - 1)*this.pageSize, 
                pageSize: this.pageSize,
            });
            console.log(postData1);
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/ManageCurrentContentOrders',
                data: postData1
            }).then(response => {
                this.orders = response.data.orders;
                console.log(this.orders);
            }).catch(error => {
                console.log(error);
            });
        },

        /**
         * 发货
         */
        _manageShipping(oid) {
            let postData = this.qs.stringify({
                oid: oid
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/changeOshipping',
                data: postData
            }).then((response) => {
                this.$message.success("发货成功")
                this.reload();
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 修改订单
         */
        _modifyOrder(oid) {
            let modifyBox = document.querySelector(".ordermanage-body-modify-box");
            modifyBox.style.display = 'block';
            modifyBox.setAttribute('oid', oid);
        },

        /**
         * 关闭修改
         */
        _closeModifyBox() {
            let modifyBox = document.querySelector(".ordermanage-body-modify-box");
            modifyBox.style.display = 'none';
        },

        /**
         * 提交
         */
        _submitChange() {
            let modifyBox = document.querySelector(".ordermanage-body-modify-box");
            let oid = modifyBox.getAttribute('oid');
            let useraddress = this.newAddress;
            let postData = this.qs.stringify({
                oid: oid,
                useraddress: useraddress
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/changeUserAddress',
                data: postData
            }).then((response) => {
                this.$message.success("修改地址成功");
                this.reload();
            }).catch((error) => {
                console.log(error);
                this.$message.warning("修改地址失败");
            });
        }
    }
}
</script>

<style>
.order-management {
    margin-bottom: 100px;
    width: 970px;
}

.order-management-title {
    height: 44px;
    background-color: rgb(137, 206, 73);
}

.order-management-title input {
    margin: 4px 0 4px 15px;
    padding: 0 8px;
    width: 200px;
    height: 34px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.order-management-body {
    position: relative;
    margin-top: 30px;
    /* background-color: pink; */
}

.ordermanage-body-title {
    display: flex;
    font-size: 16px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    background-color: rgb(230, 245, 206);
}

.ordermanage-body-ordernumber {
    width: 200px;
    text-align: center;
    overflow: hidden;
    word-break: break-all;
}

.ordermanage-body-username {
    width: 120px;
    text-align: center;
    overflow: hidden;
    word-break: break-all;
}

.ordermanage-body-address {
    width: 280px;
    text-align: center;
    overflow: hidden;
    word-break: break-all;
}

.ordermanage-body-status {
    width: 120px;
    text-align: center;
    overflow: hidden;
    word-break: break-all;
}

.ordermanage-body-shipping {
    width: 130px;
    text-align: center;
}

.ordermanage-body-modify {
    width: 120px;
    text-align: center;
}

.ordermanage-body-item {
    display: flex;
    margin: 10px 0 0 0;
    height: 45px;
    border-bottom: 1px solid rgb(230, 245, 206);
}

.ordermanage-body-item div {
    font-size: 14px;
    line-height: 20px;
}

.ordermanage-body-shipping .ordermanage-body-shipping-btn {
    margin: 0 auto;
    width: 100px;
    height: 30px;
    font-size: 14px;
    font-weight: 500;
    text-align: center;
    line-height: 30px;
    color: white;
    background-color: green;
    border-radius: 5px;
    cursor: pointer;
}

.order-management-body-pagination {
    margin-top: 30px;
    text-align: center;
}

.ordermanage-body-modify-box {
    display: none;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -150px;
    margin-left: -200px;
    width: 400px;
    height: 300px;
    color: white;
    background-color: rgb(62, 143, 62);
    border-radius: 8px;
    z-index: 99;
}

.ordermanagebody-modifybox-close {
    position: absolute;
    top: 0;
    right: 0;
    width: 45px;
    height: 45px;
    font-size: 18px;
    color: white;
    font-weight: 500;
    text-align: center;
    line-height: 45px;
    border-radius: 8px;
    cursor: pointer;
}

.ordermanagebody-modifybox-row {
    display: flex;
    margin-top: 40px;
}

.ordermanagebody-modifybox-left {
    width: 100px;
    font-size: 16px;
    text-align: right;
}

.ordermanagebody-modifybox-right {
    width: 220px;
}

.ordermanagebody-modify-textarea {
    padding: 8px 8px;
    margin: 0 37px;
    resize: none;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.ordermanagebody-modifybox-btn {
    margin-top: 30px;
    margin-left: 180px;
    width: 120px;
    height: 35px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
    line-height: 35px;
    color: green;
    background-color: greenyellow;
    cursor: pointer;
    border-radius: 8px;
}
</style>