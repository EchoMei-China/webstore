<template>
    <div class="shoppingcart-area">
        <div class="shoppingcart-area-closebox" @click="_closeShoppingcartBox()">X</div>
        <div class="shoppingcart-area-head">
            <div class="t-checkbox">
                <input type="checkbox" name="" id="" class="checkall"> 全选
            </div>
            <div class="t-goods">商品</div>
            <div class="t-price">单价</div>
            <div class="t-num">数量</div>
            <div class="t-sum">小计</div>
            <div class="t-action">操作</div>
        </div>
        <div class="shoppingcart-area-body">
            <ul class="shoppingcart-area-body-ul">
                <li v-for="item in shoppingcarts" :key="item.sid">
                    <div class="shoppingcart-body-item" :sid="item.sid">
                        <div class="t-checkbox-j">
                            <input type="checkbox" name="" id="" class="j-checkbox">
                        </div>
                        <div class="t-goods">
                            <!-- <div class="t-goods-picture"> -->
                            <img :src="item.book.bimage" alt="" class="t-goods-picture">
                            <!-- </div> -->
                            <div class="t-goods-title"> {{ item.book.bname }} </div>
                        </div>
                        <div class="t-price">
                            <div class="t-price-price">¥&nbsp; <span> {{ item.book.bnowprice }} </span></div>
                        </div>
                        <div class="t-num">
                            <div class="quantity-form">
                                <a href="javascript:;" class="decrement" :sid="item.sid">-</a>
                                <input type="text" class="itxt" v-model="item.quantity" :sid="item.sid">
                                <a href="javascript:;" class="increment" :sid="item.sid">+</a>
                            </div>
                        </div>
                        <div class="t-sum">
                            <div class="t-sum-count">¥&nbsp; <span class="shoppingcart-totalCount"> {{ (item.quantity*item.book.bnowprice).toFixed(2) }} </span> </div>
                        </div>
                        <div class="t-action">
                            <div class="t-action-delete">
                                <a href="javascript:;" class="shoppingcart-delete" :sid="item.sid">删除</a>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="shoppingcart-area-foot">
            <div class="select-all">
                <input type="checkbox" name="" id="" class="checkall">全选
            </div>
            <div class="operation">
                <a href="javascript:;" class="remove-batch"> 清空购物车</a>
            </div>
            <div class="amount-sum">已经选&nbsp;<span> {{ total }} </span>&nbsp;件商品</div>
            <div class="price-sum">总价： <span>￥ {{ totalPrice.toFixed(2) }} </span></div>
            <div class="btn-area" @click="_settlement(totalPrice.toFixed(2))">去结算</div>
        </div>
    </div>
</template>

<script>
import qs from "qs";
import axios from "axios";

export default {
    name: 'ShoppingcartArea',

    inject:['reload'], //调用刷新

    data() {
        return {
            shoppingcarts: [],
            total: 0,
            totalPrice: 0,
            // bcount: 0
        }
    },

    methods: {
        /**
         * 关闭按钮
         */
        _closeShoppingcartBox() {
            this.$router.push('/home');
        },

        /**
         * 结算按钮
         */
        _settlement(totalPriceVal) {
            let goodsArr = new Array();
            let jcheckboxs = document.querySelectorAll(".j-checkbox");
            let selectItems =document.querySelectorAll(".shoppingcart-body-item");
            for (var i=0; i<jcheckboxs.length; i++) {
                jcheckboxs[i].setAttribute('index', i);
                if (jcheckboxs[i].checked) {
                    let index = jcheckboxs[i].getAttribute('index');
                    let sid = selectItems[index].getAttribute("sid");
                    goodsArr.push(sid);
                }
            }
            console.log(goodsArr);
            let routeUrl = this.$router.resolve({
                path: "/confirmorder",
                query: {
                  goods: goodsArr,
                  totalPrice: totalPriceVal
                },
            });
            window.open(routeUrl.href, "_blank");
        }
    },

    created() {
        let user_nickname = sessionStorage.getItem('userNickName');
        let postData = this.qs.stringify({
            user_nickname: user_nickname
        });
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/shoppingcart/obtainByUsernickname',
            data: postData
        }).then(response => {
            this.shoppingcarts = response.data.shoppingcarts
        }).catch(error => {
            console.log(error);
        })
    },

    mounted() {
        setTimeout(() => {
            let checkalls = document.querySelectorAll(".checkall");
            let jcheckboxs = document.querySelectorAll(".j-checkbox");
            let selectItems =document.querySelectorAll(".shoppingcart-body-item");
            let itxts = document.querySelectorAll(".itxt");
            let increments = document.querySelectorAll(".increment");
            let decrements = document.querySelectorAll(".decrement");
            let sumPrices = document.querySelectorAll(".shoppingcart-totalCount");
            let deletes = document.querySelectorAll(".shoppingcart-delete");
            let removeAllShoppingcart = document.querySelector(".remove-batch");
            let that = this;

            /**
             * 给全选框绑定事件
             */
            for (var i=0; i<checkalls.length; i++) {
                checkalls[i].addEventListener("change", function() {
                    // 给单独的选择框赋值全选框的值
                    for (var i=0; i<jcheckboxs.length; i++) {
                        jcheckboxs[i].checked = this.checked;
                    }
                    for (var i=0; i<checkalls.length; i++) {
                        checkalls[i].checked = this.checked;
                    }
                    if (this.checked) {
                        // 添加选中的书的背景颜色
                        for (var i=0; i<selectItems.length; i++) {
                            selectItems[i].style.backgroundColor = 'rgb(249, 255, 247)';
                        }
                        // 更改选中的总计数量
                        that.total = 0;
                        for (var i=0; i<itxts.length; i++) {
                            that.total += Number(itxts[i].value);
                        }
                        // 更改选中的总价
                        that.totalPrice = 0;
                        for (var i=0; i<sumPrices.length; i++) {
                            // console.log(sumPrices[i].innerHTML);
                            that.totalPrice += Number(sumPrices[i].innerHTML)
                        }
                    } else {
                        for (var i=0; i<selectItems.length; i++) {
                            selectItems[i].style.backgroundColor = 'white';
                        }
                        that.total = 0;
                        that.totalPrice = 0;
                    }
                })
            }

            /**
             * 给单选框绑定事件
             */
            for (var i=0; i<jcheckboxs.length; i++) {
                jcheckboxs[i].setAttribute('index', i);
                jcheckboxs[i].addEventListener("change", function() {
                    // that.$forceUpdate()
                    let index = this.getAttribute('index');
                    if (this.checked) {
                        // 给选中的书更改背景颜色
                        selectItems[index].style.backgroundColor = 'rgb(249, 255, 247)';
                        // 更改选中的总计数量
                        that.total += Number(itxts[index].value) ;
                        that.totalPrice += Number(sumPrices[index].innerHTML);
                    } else {
                        selectItems[index].style.backgroundColor = 'white';
                        that.total -= Number(itxts[index].value) ;
                        that.totalPrice -= Number(sumPrices[index].innerHTML);
                    }
                })
            }

            /**
             * 加号按钮绑定事件
             */
            for (var i=0; i<increments.length; i++) {
                increments[i].setAttribute('index', i);
                increments[i].addEventListener("click", function() {
                    let index = this.getAttribute('index');
                    let sid = this.getAttribute('sid');
                    if (itxts[index].value < 999) {
                      itxts[index].value = Number(itxts[index].value) +1;
                      let quantity = Number(itxts[index].value);
                      let postData = qs.stringify({
                          quantity: quantity,
                          sid: sid
                      })
                      axios({
                          method: 'post',
                          url: '/ssm_project_war_exploded/shoppingcart/updateQuantity',
                          data: postData
                      }).then(response => {
                          console.log(response);
                          that.reload()
                      }).catch(error => {
                          console.log(error);
                      })
                    }
                })
            }

            /**
             * 减号按钮绑定事件
             */
            for (var i=0; i<decrements.length; i++) {
                decrements[i].setAttribute('index', i);
                decrements[i].addEventListener("click", function() {
                    let index = this.getAttribute('index');
                    let sid = this.getAttribute('sid');
                    // alert(sid);
                    if (itxts[index].value > 1) {
                        itxts[index].value = Number(itxts[index].value) -1;
                        let quantity = Number(itxts[index].value);
                        let postData = qs.stringify({
                          quantity: quantity,
                          sid: sid
                        })
                        axios({
                          method: 'post',
                          url: '/ssm_project_war_exploded/shoppingcart/updateQuantity',
                          data: postData
                        }).then(response => {
                          console.log(response);
                          that.reload();
                        }).catch(error => {
                          console.log(error);
                        })
                    }
                })
            }

            /**
             * 给输入框绑定事件
             */
            for (var i=0; i<itxts.length; i++) {
                itxts[i].addEventListener("change", function() {
                    if (this.value<999 && this.value >1) {
                        let sid = this.getAttribute('sid');
                        // alert(this.value)
                        let postData = qs.stringify({
                              quantity: this.value,
                              sid: sid
                        })
                        axios({
                          method: 'post',
                          url: '/ssm_project_war_exploded/shoppingcart/updateQuantity',
                          data: postData
                        }).then(response => {
                          console.log(response);
                          that.reload();
                        }).catch(error => {
                          console.log(error);
                        })
                    } else {
                        alert("请输入1-999的数字");
                    }                   
                })
            }

            /**
             * 给每个删除按钮添加事件
             */
            for (var i=0; i<deletes.length; i++) {
                deletes[i].addEventListener("click", function() {
                    let sid = this.getAttribute('sid');
                    let postData = qs.stringify({
                        sid: sid
                    })
                    axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/shoppingcart/deleteShoppingcartBySid',
                        data: postData
                    }).then(response => {
                        console.log(response);
                        that.reload();
                    }).catch(error => {
                        console.log(error);
                    })
                })
            }

            /**
             * 给清空购物车按钮绑定事件
             */
            removeAllShoppingcart.addEventListener("click", function() {
                let user_nickname = that.$route.query.user_nickname;
                let postData = qs.stringify({
                    user_nickname: user_nickname
                })
                axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/user/botainUserIdByNickname',
                    data: postData
                }).then(response => {
                    let user_id = response.data.user.userId;
                    let postData1 = qs.stringify({
                        user_id: user_id
                    })
                    axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/shoppingcart/deleteAllByUserNickname',
                        data: postData1
                    }).then(response => {
                        console.log(response);
                        that.reload();
                    }).catch(error => {
                        console.log(error);
                    })
                }).catch(error => {
                    console.log(error);
                })
            })
        },100)
    }
}
</script>

<style>
.shoppingcart-area {
    position: relative;
    margin-top: 20px;
    /* background-color: pink; */
}

.shoppingcart-area-closebox {
    position: absolute;
    right: -15px;
    top: -15px;
    width: 30px;
    height: 30px;
    font-size: 18px;
    text-align: center;
    line-height: 30px;
    border-radius: 50%;
    border: 1px solid rgb(17, 70, 17);
    background-color: rgb(216, 253, 208);
    cursor: pointer;
}

.shoppingcart-area-head {
    display: flex;
    height: 35px;
    line-height: 35px;
    color: rgb(17, 70, 17);
    background-color: rgb(216, 253, 208);
}

.t-checkbox {
    margin-top: 2px;
    padding-top: 7px;
    height: 18px;
    line-height: 18px;
    width: 111px;
    padding-left: 11px;
    /* background-color: pink; */
}

.t-checkbox input {
    vertical-align: middle;
}

.t-goods {
    display: flex;
    width: 400px;
    /* background-color: plum; */
}

.t-price {
    width: 120px;
    padding-right: 40px;
    text-align: right;
    /* background-color: palevioletred; */
}

.t-num {
    width: 150px;
    text-align: center;
    /* background-color: skyblue; */
}

.t-sum {
    width: 100px;
    text-align: right;
    /* background-color: royalblue; */
}

.t-action {
    width: 130px;
    text-align: right;
    /* background-color: rosybrown; */
}

/* .shoppingcart-area-body { */
    /* margin-top: 10px; */
    /* background-color: white; */
/* } */

.shoppingcart-body-item {
    display: flex;
    height: 177px;
    /* line-height: 177px; */
    border: 1px solid #ccc;
    border-top: 2px solid rgb(86, 185, 86);
}

.t-checkbox-j {
    margin-top: 40px;
    height: 18px;
    line-height: 18px;
    padding-top: 7px;
    width: 111px;
    padding-left: 11px;
    /* background-color: pink; */
}

.t-goods-picture {
    margin-top: 40px;
    width: 92px;
    height: 92px;
    border: 1px solid #ccc;
}

.t-goods-title {
    margin: 40px 0 0 15px;
    /* height: 40px; */
    /* font-size: 16px; */
    /* background-color: pink; */
}

.t-price-price {
    margin-top: 40px;
    /* background-color: pink; */
}

.quantity-form {
    display: flex;
    margin-top: 40px;
    margin-left: 35px;
    width: 80px;
    height: 22px;
}

.decrement,
.increment {
    border: 1px solid #cacbcb;
    height: 18px;
    line-height: 18px;
    padding: 1px 0;
    width: 16px;
    text-align: center;
    color: #666;
    margin: 0;
    background: #fff;
    margin-left: -1px;
}

.itxt {
    border: 1px solid #cacbcb;
    width: 42px;
    height: 18px;
    line-height: 18px;
    text-align: center;
    padding: 1px;
    margin: 0;
    margin-left: -1px;
    font-size: 12px;
    font-family: verdana;
    color: #333;
    -webkit-appearance: none;
}

.t-sum-count {
    margin-top: 40px;
}

.t-action-delete {
    margin-top: 40px;
}

.shoppingcart-area-body-ul li {
    margin-top: 10px;
}

.shoppingcart-area-foot {
    display: flex;
    margin-top: 15px;
    height: 52px;
    background-color: rgb(216, 253, 208);
}

.select-all {
    height: 18px;
    line-height: 18px;
    padding: 16px 0 16px 9px;
    white-space: nowrap;
}

.select-all input {
    display: inline-block;
    margin-right: 5px;
    vertical-align: middle;
}

.operation {
    margin-left: 40px;
    margin-top: 15px;
    width: 480px;
    /* background-color: pink; */
}

.amount-sum {
    margin-top: 15px;
    width: 250px;
    text-align: center;
    /* background-color: pink; */
}

.amount-sum span,
.price-sum span {
    font-size: 15px;
    font-weight: 600;
    color: rgb(209, 70, 70);
}

.price-sum {
    margin-top: 15px;
    width: 220px;
    /* background-color: plum; */
}

.btn-area {
    width: 158px;
    font-size: 18px;
    color: white;
    font-weight: 700;
    text-align: center;
    line-height: 52px;
    background-color: green;
    cursor: pointer;
}

.check-cart-item {
    background-color: rgb(249, 255, 247);
}
</style>