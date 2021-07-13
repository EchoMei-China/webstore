<template>
    <div class="order-list">
        <div class="order-list-top">
            <div class="orderlist-top orderlist-current">所有订单</div>
            <div class="orderlist-top">待付款</div>
            <div class="orderlist-top">待发货</div>
            <div class="orderlist-top">待收货</div>
            <div class="orderlist-top">待评价</div>
        </div>
        <div class="order-list-body">
            <div class="orderlist-body" style="display: block">
                <div class="orderlist-body-item" v-for="item in allorders" :key="item.oid" @click="_obtainAllOrderdetails(item.oid)">
                    <div class="orderlist-item-title">
                        <span>订单号：&nbsp;</span>
                        <span> {{item.oid}} </span>
                    </div>
                </div>
                <el-pagination
                    class="orderlist-body-pagination"
                    layout="prev, pager, next"
                    :current-page.sync="currentPage1"
                    :pager-count="fold"
                    :hide-on-single-page="hide"
                    :page-size="pageSize"
                    :total="total1"
                    @current-change="_handlePageChange1()">
                </el-pagination>
                <div class="orderlist-body-title">
                    <div class="orderlistbody-good">商品</div>
                    <div class="orderlistbody-price">单价</div>
                    <div class="orderlistbody-quantity">数量</div>
                    <div class="orderlistbody-status">交易状态</div>
                    <div class="orderlistbody-operator">交易操作</div>
                    <div class="orderlistbody-goodoperator">商品操作</div>
                </div>
                <div class="orderlist-item-body" v-for="item in allorderdetails" :key="item.odid">
                        <div class="orderlistbody-good">
                            <img :src="item.book.bimage" alt="">
                            <div class="orderlistbody-good-margin"> {{item.book.bname}} </div>
                        </div>
                        <div class="orderlistbody-price">
                            <div class="orderlistbody-good-margin"> {{item.book.bnowprice}} </div>
                        </div>
                        <div class="orderlistbody-quantity">
                            <div class="orderlistbody-good-margin"> {{item.odquantity}} </div>
                        </div>
                        <div class="orderlistbody-status" :opay="item.order.opay" :oshipping="item.order.oshipping" :osignfor="item.order.osignfor" :ocomment="item.order.ocomment">
                            <div class="orderlistbody-good-margin"> 
                                <div v-if="item.order.opay == 1">已付款 </div>
                                <div v-else> 未付款 </div>
                                <div v-if="item.order.oshipping == 1"> 已发货 </div>
                                <div v-else> 未发货 </div>
                                <div v-if="item.order.osignfor == 1"> 已收货 </div>
                                <div v-else> 未收货 </div>
                                <div v-if="item.order.ocomment == 1"> 已评价 </div>
                                <div v-else> 未评价 </div>
                            </div>
                        </div>
                        <div class="orderlistbody-operator">
                            <div class="orderlistbody-good-margin">
                                <div class="orderlistbody-good-pay" v-show="item.order.opay == 0" @click="_immediatePayment(item.order.oid, item.order.oprice)">立即付款</div>
                                <div class="orderlistbody-good-signfor" v-show="item.order.oshipping == 1 && item.order.osignfor == 0" @click="_signforOrder(item.order.oid)">确认收货</div>
                                <div class="orderlistbody-good-comment" v-show="item.order.osignfor == 1 && item.order.ocomment == 0" @click="_openCommentBox(item.book.bid, item.order.oid, $event)">立即评价</div>
                                <div style="margin-top: 10px" v-show="item.order.opay == 0"><a href="javascript:;" @click="_cancelOrder(item.order.oid)">取消订单</a></div>
                                <div  v-show="item.order.ocomment == 1"><a href="javascript:;" @click="_cancelOrder(item.order.oid)">删除订单</a></div>
                            </div>
                        </div>
                        <div class="orderlistbody-goodoperator">
                            <div class="orderlistbody-good-margin">
                                <div v-show="item.order.osignfor == 1">
                                    <div><a href="javascript:;">退货</a></div>
                                    <div style="margin-top: 10px"><a href="javascript:;">换货</a></div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
            <div class="orderlist-body">
                <div class="orderlist-body-item" v-for="item in payorders" :key="item.oid" @click="_obtainPayOrderdetails(item.oid)">
                    <div class="orderlist-item-title">
                        <span>订单号：&nbsp;</span>
                        <span> {{item.oid}} </span>
                    </div>
                </div>
                <el-pagination
                    class="orderlist-body-pagination"
                    layout="prev, pager, next"
                    :current-page.sync="currentPage2"
                    :pager-count="fold"
                    :hide-on-single-page="hide"
                    :page-size="pageSize"
                    :total="total2"
                    @current-change="_handlePageChange2()">
                </el-pagination>
                <div class="orderlist-body-title">
                    <div class="orderlistbody-good">商品</div>
                    <div class="orderlistbody-price">单价</div>
                    <div class="orderlistbody-quantity">数量</div>
                    <div class="orderlistbody-status">交易状态</div>
                    <div class="orderlistbody-operator">交易操作</div>
                    <div class="orderlistbody-goodoperator">商品操作</div>
                </div>
                <div class="orderlist-item-body" v-for="item in payorderdetails" :key="item.odid">
                    <div class="orderlistbody-good">
                        <img :src="item.book.bimage" alt="">
                        <div class="orderlistbody-good-margin"> {{item.book.bname}} </div>
                    </div>
                    <div class="orderlistbody-price">
                        <div class="orderlistbody-good-margin"> {{item.book.bnowprice}} </div>
                    </div>
                    <div class="orderlistbody-quantity">
                        <div class="orderlistbody-good-margin"> {{item.odquantity}} </div>
                    </div>
                    <div class="orderlistbody-status" :opay="item.order.opay" :oshipping="item.order.oshipping" :osignfor="item.order.osignfor">
                        <div class="orderlistbody-good-margin"> 
                            <span>等待买家付款</span>
                        </div>
                    </div>
                    <div class="orderlistbody-operator">
                        <div class="orderlistbody-good-margin">
                            <div class="orderlistbody-good-pay" @click="_immediatePayment(item.order.oid, item.order.oprice)">立即付款</div>
                            <div style="margin-top: 10px" v-show="item.order.opay == 0"><a href="javascript:;" @click="_cancelOrder(item.order.oid)">取消订单</a></div>
                        </div>
                    </div>
                    <div class="orderlistbody-goodoperator"></div>
                </div>
            </div>
            <div class="orderlist-body">
                <div class="orderlist-body-item" v-for="item in shippingorders" :key="item.oid" @click="_obtainShippingOrderdetails(item.oid)">
                    <div class="orderlist-item-title">
                        <span>订单号：&nbsp;</span>
                        <span> {{item.oid}} </span>
                    </div>
                </div>
                <el-pagination
                    class="orderlist-body-pagination"
                    layout="prev, pager, next"
                    :current-page.sync="currentPage3"
                    :pager-count="fold"
                    :hide-on-single-page="hide"
                    :page-size="pageSize"
                    :total="total3"
                    @current-change="_handlePageChange3()">
                </el-pagination>
                <div class="orderlist-body-title">
                    <div class="orderlistbody-good">商品</div>
                    <div class="orderlistbody-price">单价</div>
                    <div class="orderlistbody-quantity">数量</div>
                    <div class="orderlistbody-status">交易状态</div>
                    <div class="orderlistbody-operator">交易操作</div>
                    <div class="orderlistbody-goodoperator">商品操作</div>
                </div>
                <div class="orderlist-item-body" v-for="item in shippingorderdetails" :key="item.odid">
                    <div class="orderlistbody-good">
                        <img :src="item.book.bimage" alt="">
                        <div class="orderlistbody-good-margin"> {{item.book.bname}} </div>
                    </div>
                    <div class="orderlistbody-price">
                        <div class="orderlistbody-good-margin"> {{item.book.bnowprice}} </div>
                    </div>
                    <div class="orderlistbody-quantity">
                        <div class="orderlistbody-good-margin"> {{item.odquantity}} </div>
                    </div>
                    <div class="orderlistbody-status" :opay="item.order.opay" :oshipping="item.order.oshipping" :osignfor="item.order.osignfor" :ocomment="item.order.ocomment">
                        <div class="orderlistbody-good-margin"> 
                            <span>等待卖家发货</span>
                        </div>
                    </div>
                    <div class="orderlistbody-operator">
                        <div class="orderlistbody-good-margin">
                            <div><a href="javascript:;" @click="_cancelOrder(item.order.oid)">取消订单</a></div>
                        </div>
                    </div>
                    <div class="orderlistbody-goodoperator"></div>
                </div>
            </div>
            <div class="orderlist-body">
                <div class="orderlist-body-item" v-for="item in signfororders" :key="item.oid" @click="_obtainSignforOrderdetails(item.oid)">
                    <div class="orderlist-item-title">
                        <span>订单号：&nbsp;</span>
                        <span> {{item.oid}} </span>
                    </div>
                </div>
                <el-pagination
                    class="orderlist-body-pagination"
                    layout="prev, pager, next"
                    :current-page.sync="currentPage4"
                    :pager-count="fold"
                    :hide-on-single-page="hide"
                    :page-size="pageSize"
                    :total="total4"
                    @current-change="_handlePageChange4()">
                </el-pagination>
                <div class="orderlist-body-title">
                    <div class="orderlistbody-good">商品</div>
                    <div class="orderlistbody-price">单价</div>
                    <div class="orderlistbody-quantity">数量</div>
                    <div class="orderlistbody-status">交易状态</div>
                    <div class="orderlistbody-operator">交易操作</div>
                    <div class="orderlistbody-goodoperator">商品操作</div>
                </div>
                <div class="orderlist-item-body" v-for="item in signfororderdetails" :key="item.odid">
                    <div class="orderlistbody-good">
                        <img :src="item.book.bimage" alt="">
                        <div class="orderlistbody-good-margin"> {{item.book.bname}} </div>
                    </div>
                    <div class="orderlistbody-price">
                        <div class="orderlistbody-good-margin"> {{item.book.bnowprice}} </div>
                    </div>
                    <div class="orderlistbody-quantity">
                        <div class="orderlistbody-good-margin"> {{item.odquantity}} </div>
                    </div>
                    <div class="orderlistbody-status" :opay="item.order.opay" :oshipping="item.order.oshipping" :osignfor="item.order.osignfor">
                        <div class="orderlistbody-good-margin"> 
                            <span>等待买家收货</span>
                        </div>
                    </div>
                    <div class="orderlistbody-operator">
                        <div class="orderlistbody-good-margin">
                            <div class="orderlistbody-good-signfor" @click="_signforOrder(item.order.oid)">确认收货</div>
                        </div>
                    </div>
                    <div class="orderlistbody-goodoperator"></div>
                </div>
            </div>
            <div class="orderlist-body">
                <div class="orderlist-body-item" v-for="item in commentorders" :key="item.oid" @click="_obtainCommentOrderdetails(item.oid)">
                    <div class="orderlist-item-title">
                        <span>订单号：&nbsp;</span>
                        <span> {{item.oid}} </span>
                    </div>
                </div>
                <el-pagination
                    class="orderlist-body-pagination"
                    layout="prev, pager, next"
                    :current-page.sync="currentPage5"
                    :pager-count="fold"
                    :hide-on-single-page="hide"
                    :page-size="pageSize"
                    :total="total5"
                    @current-change="_handlePageChange5()">
                </el-pagination>
                <div class="orderlist-body-title">
                    <div class="orderlistbody-good">商品</div>
                    <div class="orderlistbody-price">单价</div>
                    <div class="orderlistbody-quantity">数量</div>
                    <div class="orderlistbody-status">交易状态</div>
                    <div class="orderlistbody-operator">交易操作</div>
                    <div class="orderlistbody-goodoperator">商品操作</div>
                </div>
                <div class="orderlist-item-body" v-for="item in commentorderdetails" :key="item.odid">
                    <div class="orderlistbody-good">
                        <img :src="item.book.bimage" alt="">
                        <div class="orderlistbody-good-margin"> {{item.book.bname}} </div>
                    </div>
                    <div class="orderlistbody-price">
                        <div class="orderlistbody-good-margin"> {{item.book.bnowprice}} </div>
                    </div>
                    <div class="orderlistbody-quantity">
                        <div class="orderlistbody-good-margin"> {{item.odquantity}} </div>
                    </div>
                    <div class="orderlistbody-status" :opay="item.order.opay" :oshipping="item.order.oshipping" :osignfor="item.order.osignfor" :ocomment="item.order.ocomment">
                        <div class="orderlistbody-good-margin"> 
                            <span>等待买家评价</span>
                        </div>
                    </div>
                    <div class="orderlistbody-operator">
                        <div class="orderlistbody-good-margin">
                            <div class="orderlistbody-good-comment" @click="_openCommentBox(item.book.bid, item.order.oid, $event)">立即评价</div>
                        </div>
                    </div>
                    <div class="orderlistbody-goodoperator">
                        <div class="orderlistbody-good-margin">
                            <div v-show="item.order.osignfor == 1">
                                <div><a href="javascript:;">退货</a></div>
                                <div style="margin-top: 10px"><a href="javascript:;">换货</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="orderlist-body-modalbox">
                <div class="orderlistbody-modal-close" @click="_closeModalbox()">X</div>
                <div class="orderlistbody-modalbox-title">发表评价</div>
                <div class="orderlistbody-modalbox-row" v-for="item in comorders" :key="item.odid">
                    <div>{{item.book.bname}}</div>
                    <textarea :bid="item.book.bid" cols="60" rows="6" class="orderlistbody-modal-textarea"></textarea>
                </div>
                <div class="orderlistbody-modalbox-btn" @click="_comment($event)">立即提交</div>
            </div>
        </div>
    </div> 
</template>

<script>
import qs from 'qs';
import axios from "axios";

export default {
    name: 'OrderList',

    inject:['reload'], //调用刷新

    data() {
        return {
            user_id: null,
            allorders: [],
            allorderdetails: [],
            payorders: [],
            payorderdetails: [],
            shippingorders: [],
            shippingorderdetails: [],
            signfororders: [],
            signfororderdetails: [],
            commentorders: [],
            commentorderdetails: [],
            opay: null,
            oshipping: null,
            osignfor: null,
            ocomment: null,
            ccontent: '',
            arr: [],
            comorders: [],
            pageSize: 5,
            currentPage1: 1,
            total1: 0,
            currentPage2: 1,
            total2: 0,
            currentPage3: 1,
            total3: 0,
            currentPage4: 1,
            total4: 0,
            currentPage5: 1,
            total5: 0,
            hide: false,
            fold: 5
        }
    },

    created() {
        const that = this;
        /**
         * 获取用户id
         */
        let user_nickname = sessionStorage.getItem('userNickName');
        let postData1 = this.qs.stringify({
          user_nickname: user_nickname
        })
        this.axios({
          method: 'post',
          url: '/ssm_project_war_exploded/user/botainUserIdByNickname',
          data: postData1
        }).then(response => {
          this.user_id = response.data.user.userId;

            /**
             * 获取allorders
             */
            let postData2 = qs.stringify({
                user_id: response.data.user.userId
            })
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/botainOrdersAll',
                data: postData2
            }).then((response) => {
                that.allorders = response.data.orders;
            }).catch((error) => {
                console.log(error);
            });
    
            /**
             * 获取payorders
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/botainOrdersPay',
                data: postData2
            }).then((response) => {
                that.payorders = response.data.orders;
            }).catch((error) => {
                console.log(error);
            });
    
            /**
             * 获取shippingorders
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/botainOrdersShipping',
                data: postData2
            }).then((response) => {
                that.shippingorders = response.data.orders;
            }).catch((error) => {
                console.log(error);
            });
    
            /**
             * 获取signfororders
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/botainOrdersSignfor',
                data: postData2
            }).then((response) => {
                that.signfororders = response.data.orders;
            }).catch((error) => {
                console.log(error);
            });
    
            /**
             * 获取commentorders
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/botainOrdersComment',
                data: postData2
            }).then((response) => {
                that.commentorders = response.data.orders;
            }).catch((error) => {
                console.log(error);
            });

            /**
             * 获取allorders总数
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obotainAllOrderCount',
                data: postData2
            }).then((response) => {
                that.total1 = response.data
            }).catch((error) => {
                console.log(error);
            });

            /**
             * 获取payorders总数
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obotainPayOrderCount',
                data: postData2
            }).then((response) => {
                that.total2 = response.data
            }).catch((error) => {
                console.log(error);
            });

            /**
             * 获取shippingorders总数
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obotainShippingOrderCount',
                data: postData2
            }).then((response) => {
                that.total3 = response.data
            }).catch((error) => {
                console.log(error);
            });

            /**
             * 获取signfororders总数
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obotainSignforOrderCount',
                data: postData2
            }).then((response) => {
                that.total4 = response.data
            }).catch((error) => {
                console.log(error);
            });

            /**
             * 获取commentorders总数
             */
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obotainCommentOrderCount',
                data: postData2
            }).then((response) => {
                that.total5 = response.data
            }).catch((error) => {
                console.log(error);
            });


        }).catch(error => {
          console.log(error);
        });

    },

    mounted() {
        setTimeout(() => {
            let orderlistTops = document.querySelectorAll(".orderlist-top");
            let orderlistBodys = document.querySelectorAll(".orderlist-body");
            for (var i=0; i<orderlistTops.length; i++) {
                orderlistTops[i].setAttribute('index', i);
                orderlistTops[i].addEventListener('click', function() {
                    for (var i=0; i<orderlistTops.length; i++) {
                        orderlistTops[i].className = 'orderlist-top'
                    }
                    this.className = 'orderlist-top orderlist-current';
                    let index = this.getAttribute('index');
                    for (var i=0; i<orderlistBodys.length; i++) {
                        orderlistBodys[i].style.display = 'none'
                    }
                    orderlistBodys[index].style.display = 'block'
                })
            }
        }, 500)
    },

    methods: {
        /**
         * 根据oid获取orderdetails
         */
        _obtainAllOrderdetails(val) {
            let postData = this.qs.stringify({
                oid: val
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/orderdetail/obotainAllOrderdetails',
                data: postData
            }).then((response) => {
                this.allorderdetails = response.data.orderdetails;
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * payorderdetails
         */
        _obtainPayOrderdetails(val) {
            let postData = this.qs.stringify({
                oid: val
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/orderdetail/obotainAllOrderdetails',
                data: postData
            }).then((response) => {
                this.payorderdetails = response.data.orderdetails;
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * shippingorderdetails
         */
        _obtainShippingOrderdetails(val) {
            let postData = this.qs.stringify({
                oid: val
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/orderdetail/obotainAllOrderdetails',
                data: postData
            }).then((response) => {
                this.shippingorderdetails = response.data.orderdetails;
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * signfororderdetails
         */
        _obtainSignforOrderdetails(val) {
            let postData = this.qs.stringify({
                oid: val
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/orderdetail/obotainAllOrderdetails',
                data: postData
            }).then((response) => {
                this.signfororderdetails = response.data.orderdetails;
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * commentorderdetails
         */
        _obtainCommentOrderdetails(val) {
            let postData = this.qs.stringify({
                oid: val
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/orderdetail/obotainAllOrderdetails',
                data: postData
            }).then((response) => {
                this.commentorderdetails = response.data.orderdetails;
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 取消订单
         */
        _cancelOrder(val) {
            let postData = this.qs.stringify({
                oid: val
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/deleteOrderByOid',
                data: postData
            }).then((response) => {
                this.reload();
            }).catch((error) => {
                console.log(error);
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/orderdetail/deleteOrderdetailByOid',
                data: postData
            }).then((response) => {
                this.reload();
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 立即付款
         */
        _immediatePayment(oidVal, priceVal) {
            let that = this;
            let routeUrl = this.$router.resolve({
              path: '/payment',
              query: {oid: oidVal, user_id: that.user_id, actualPrice: priceVal}
            });
            window.open(routeUrl.href, '_blank');
        },

        /**
         * 立即评价
         */
        _openCommentBox(bidVal, oidVal, event) {
            let modalbox = document.querySelector(".orderlist-body-modalbox");
            modalbox.style.display = 'block';
            modalbox.setAttribute('oid', oidVal);
            let postData1 = this.qs.stringify({
                oid: oidVal
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/orderdetail/obotainAllOrderdetails',
                data: postData1
            }).then((resopnse) => {
                this.comorders = resopnse.data.orderdetails;
            }).catch((error) => {
                console.log(error);
            });

        },

        /**
         * 关闭模态框
         */
        _closeModalbox() {
            let modalbox = document.querySelector(".orderlist-body-modalbox");
            modalbox.style.display = 'none';
        },

        /**
         * 评论成功，订单完成，增加零钱
         */
        _addChange() {
            let postData = this.qs.stringify({
                user_id: this.user_id
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/user/addChange',
                data: postData
            }).then((response) => {
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 提交评价
         */
        _comment(event) {
            let textareas = document.querySelectorAll(".orderlistbody-modal-textarea");
            console.log(textareas);
            let parentDiv = event.target.parentNode;
            // let bid = parentDiv.getAttribute('bid');
            let oid = parentDiv.getAttribute('oid');
            let that = this;
            // let postData1 = this.qs.stringify({
            //     oid: oid
            // });
            // this.axios({
            //     method: 'post',
            //     url: '/ssm_project_war_exploded/orderdetail/obotainAllOrderdetails',
            //     data: postData1
            // }).then((resopnse) => {
            //     console.log(resopnse.data.orderdetails);
            // }).catch((error) => {
            //     console.log(error);
            // });
            let nowtime = new Date();
            let year = nowtime.getFullYear();
            let month = nowtime.getMonth() + 1;
            if (month < 10) {
                month = '0' + month;
            }
            let date = nowtime.getDate();
            if (date < 10) {
                date = '0' + date;
            }
            let hour = nowtime.getHours();
            if (hour < 10) {
                hour = '0' + hour;
            }
            let minute = nowtime.getMinutes();
            if (minute < 10) {
                minute = '0' + minute;
            }
            let second = nowtime.getSeconds();
            if (second < 10) {
                second = '0' + second;
            }
            let ctime = year + '-' + month + '-' + date + '-' + hour + ':' + minute + ':' + second;
            let postData2 = this.qs.stringify({
                oid: oid
            })
            for (var i=0; i<textareas.length; i++) {
                let bid = textareas[i].getAttribute('bid');
                if (textareas[i].value == '') {
                    that.$message.warning("评论为空");
                } else {
                    let postData = this.qs.stringify({
                        bid: bid,
                        ccontent: textareas[i].value,
                        ctime: ctime
                    })
                    this.axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/comment/insertComment',
                        data: postData
                    }).then((response) => {
                        axios({
                            method: 'post',
                            url: '/ssm_project_war_exploded/order/changeOcomment',
                            data: postData2
                        }).then((response) => {
                            that._addChange();
                            that.$message.success("评论成功");
                            that.reload();
                        }).catch((error) => {
                            console.log(error);
                        });
                    }).catch((error) => {
                        console.log(error);
                        that.$message.warning("评论失败")
                    });
                }
            }
            
            
        },

        /**
         * 确认收货
         */
        _signforOrder(oidVal) {
            let postData = this.qs.stringify({
                oid: oidVal
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/changeOsignfor',
                data: postData
            }).then((response) => {
                this.$message.success("收货成功");
                this.reload();
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 分页
         */
        _handlePageChange1() {
            let postData = this.qs.stringify({
                pageSize: this.pageSize,
                startIndex: (this.currentPage1 - 1)*this.pageSize,
                user_id: this.user_id 
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obtainCurrentAllOrder',
                data: postData
            }).then(response => {
                this.allorders = response.data.orders;
            }).catch(error => {
                console.log(error);
            });
        },
        _handlePageChange2() {
            let postData = this.qs.stringify({
                pageSize: this.pageSize,
                startIndex: (this.currentPage2 - 1)*this.pageSize, 
                user_id: this.user_id 
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obtainCurrentPayOrder',
                data: postData
            }).then(response => {
                this.payorders = response.data.orders;
            }).catch(error => {
                console.log(error);
            });
        },
        _handlePageChange3() {
            let postData = this.qs.stringify({
                pageSize: this.pageSize,
                startIndex: (this.currentPage3 - 1)*this.pageSize, 
                user_id: this.user_id 
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obtainCurrentShippingOrder',
                data: postData
            }).then(response => {
                this.shippingorders = response.data.orders;
            }).catch(error => {
                console.log(error);
            });
        },
        _handlePageChange4() {
            let postData = this.qs.stringify({
                pageSize: this.pageSize,
                startIndex: (this.currentPage4 - 1)*this.pageSize, 
                user_id: this.user_id 
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obtainCurrentSignforOrder',
                data: postData
            }).then(response => {
                this.signfororders = response.data.orders;
            }).catch(error => {
                console.log(error);
            });
        },
        _handlePageChange5() {
            let postData = this.qs.stringify({
                pageSize: this.pageSize,
                startIndex: (this.currentPage5 - 1)*this.pageSize, 
                user_id: this.user_id 
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/order/obtainCurrentCommentOrder',
                data: postData
            }).then(response => {
                this.commentorders = response.data.orders;
            }).catch(error => {
                console.log(error);
            });
        }
    }
        
}
</script>

<style scoped>
.order-list {
    margin-top: 35px;
}

.order-list-top {
    display: flex;
    height: 35px;
    line-height: 35px;
    border-bottom: 2px solid rgb(221, 220, 220);
}

.orderlist-top {
    width: 120px;
    text-align: center;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;
}

.orderlist-current {
    color: rgb(158, 209, 64);
    border-bottom: 2px solid green;
}

.order-list-body {
    position: relative;
    margin-top: 20px;
    margin-bottom: 80px;
    /* background-color: pink; */
}

.orderlist-body {
    display: none;
}

.orderlist-body-title {
    display: flex;
    height: 45px;
    line-height: 45px;
    font-size: 15px;
    font-weight: 500;
    background-color: rgb(184, 207, 171);
}

.orderlistbody-good {
    padding-left: 35px;
}

.orderlist-body-title:nth-child(1) {
    text-align: center;
}

.orderlist-body-title:nth-child(n) {
    text-align: center;
}

.orderlist-body-item {
    margin: 10px 0;
}

.orderlist-item-title {
    height: 30px;
    line-height: 30px;
    color: black;
    background-color: rgb(198, 230, 177);
}

.orderlist-item-body {
    display: flex;
    margin-top: -1px;
    height: 120px;
    text-align: center;
    border: 1px solid rgb(198, 230, 177);
}

.orderlistbody-good {
    display: flex;
    width: 360px;
}

.orderlistbody-good img {
    margin: 20px 15px 0 40px;
    width: 35px;
    height: 35px;
}

.orderlistbody-price {
    width: 100px;
}

.orderlistbody-quantity {
    width: 98px;
}

.orderlistbody-status {
    width: 200px;
}

.orderlistbody-operator {
    width: 200px;
}

.orderlistbody-goodoperator {
    width: 200px;
}

.orderlistbody-good-margin {
    margin: 20px;
    font-size: 14px;
    line-height: 16px;
    word-break: break-all;
}

.orderlistbody-good-pay {
    /* display: none; */
    height: 20px;
    font-size: 16px;
    font-weight: 600;
    color: white;
    line-height: 20px;
    text-align: center;
    background-color: rgb(212, 95, 95);
    cursor: pointer;
    border-radius: 5px;
}

.orderlistbody-good-signfor {
    /* display: none; */
    height: 20px;
    font-size: 16px;
    font-weight: 600;
    color: white;
    line-height: 20px;
    text-align: center;
    background-color: rgb(150, 128, 223);
    cursor: pointer;
    border-radius: 5px;
}

.orderlistbody-good-comment {
    height: 20px;
    font-size: 16px;
    font-weight: 600;
    color: white;
    line-height: 20px;
    text-align: center;
    background-color: rgb(199, 126, 43);
    cursor: pointer;
    border-radius: 5px;
}

.orderlist-body-modalbox {
    display: none;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-300px, -150px);
    width: 600px;
    background-color: rgb(219, 235, 129);
    z-index: 99;
    border-radius: 8px;
}

.orderlistbody-modal-close {
    position: absolute;
    right: 0;
    top: 0;
    height: 40px;
    width: 40px;
    font-size: 20px;
    color: green;
    text-align: center;
    line-height: 40px;
    cursor: pointer;
}

.orderlistbody-modalbox-title {
    padding: 8px 20px;
    height: 35px;
    font-size: 16px;
    font-weight: 500;
    color: green;
    line-height: 35px;
}

.orderlistbody-modalbox-row {
    margin: 15px;
}

.orderlistbody-modal-textarea {
    padding: 8px 15px;
    margin: 0 37px;
    resize: none;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.orderlistbody-modalbox-btn {
    /* position: absolute; */
    margin: 0 37px 35px 363px;
    height: 35px;
    font-size: 16px;
    font-weight: 500;
    color: white;
    text-align: center;
    line-height: 35px;
    background-color: green;
    border-radius: 5px;
    cursor: pointer;
}

.orderlist-body-pagination {
    margin: 15px;
    text-align: right;
}
</style>