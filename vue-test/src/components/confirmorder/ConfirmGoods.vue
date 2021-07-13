<template>
    <div class="confirmgoods">
        <h2>确认订单信息</h2>
        <div class="confirmgoods-title">
            <div class="confirmgoods-goods-btitle">商品</div>
            <div class="confirmgoods-goods-bprice">原价</div>
            <div class="confirmgoods-goods-bnowprice">现价</div>
            <div class="confirmgoods-goods-bnumber">数量</div>
            <div class="confirmgoods-goods-bcount">小计</div>
        </div>
        <div class="confirmgoods-area">
            <ul>
                <li v-for="item in shoppingcarts" :key="item.bid">
                    <div class="confirmgoods-goods-btitle">
                        <img :src="item.book.bimage" alt="">
                        <span>{{ item.book.bname }}</span>
                    </div>
                    <div class="confirmgoods-goods-bprice">
                        <span>¥&nbsp;{{ item.book.bprice }}</span>
                    </div>
                    <div class="confirmgoods-goods-bnowprice">
                        <span>¥&nbsp;{{ item.book.bnowprice }}</span>
                    </div>
                    <div class="confirmgoods-goods-bnumber">
                        <span>{{ item.quantity }}</span>
                    </div>
                    <div class="confirmgoods-goods-bcount">
                        <span>¥&nbsp;{{ (item.quantity*item.book.bnowprice).toFixed(2) }}</span>
                    </div>
                </li>
            </ul>
            <div class="confirmgoods-goods-totalprice">商品总价：&nbsp;&nbsp;¥&nbsp;
                <span>{{totalPrice}}</span>
            </div>
            <div class="confirmgoods-goods-shipping">运费：&nbsp;&nbsp;¥&nbsp;
                <span>{{shipping}}</span>
            </div>
            <div class="confirmgoods-goods-change">
                <input type="checkbox" name="confirmgoods-change-Box" id="confirmgoodschangebox" class="confirmgoods-change-box">
                <p>使用零钱抵扣：&nbsp;&nbsp;¥&nbsp;</p>
                <span>-{{change}}</span>
            </div>
            <div class="confirmgoods-goods-information">
                <div class="confirmgoods-information-price">实际付款：&nbsp;&nbsp;¥&nbsp;
                    <span>{{ actualPrice }}</span>
                </div>
                <div class="confirmgoods-information-address">寄送至：&nbsp;&nbsp;
                    <span v-show="address != null">{{ address.pname }}&nbsp;{{ address.cname }}&nbsp;{{ address.dname }}&nbsp;{{ address.area }}&nbsp;</span>
                </div>
                <div class="confirmgoods-information-people">收货人：&nbsp;&nbsp;
                    <span v-show="address != null">{{ address.uname }}&nbsp;{{ address.uphone }} </span>
                </div>
            </div>
            <div class="confirmgoods-information-submit" @click="_submitOrder(actualPrice)">提交订单</div>
        </div>
    </div>
</template>

<script>
import qs from 'qs'
import axios from 'axios'

export default {
    name: 'ConfirmGoods',

    inject:['reload'], //调用刷新

    props: {
        goods: Array,
        totalPrice: String,
        shipping: Number,
        adid: String
    },

    data() {
        return {
            shoppingcarts: [],
            change: null,
            actualPrice: null,
            address: {
                pname: '',
                cname: '',
                dname: '',
                uname: '',
                uphone: ''
            },
            user_id: null
        }
    },

    created() {
        /**
         * 列举商品
         */
        for (var i=0; i<this.goods.length; i++) {
            let postData = this.qs.stringify({
                sid: this.goods[i]
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/shoppingcart/obtainBySid',
                data: postData
            }).then(response => {
                this.shoppingcarts.push(response.data.shoppingcart)
            }).catch(error => {
                console.log(error);
            })
        }

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
           * 根据user_id获取change
           */
          let postData2 = qs.stringify({
              user_id: response.data.user.userId
          })
          axios({
              method: 'post',
              url: '/ssm_project_war_exploded/user/obtainUserById',
              data: postData2
          }).then(response => {
              this.change = response.data.user.change
          }).catch(error => {
              console.log(error);
          })
        }).catch(error => {
          console.log(error);
        });
    },

    watch: {
        shipping(val) {
            let that = this;
            let jBox = document.querySelector(".confirmgoods-change-box");
            if (jBox.checked) {
                that.actualPrice = (Number(that.totalPrice) + Number(val) - Number(that.change)).toFixed(2);
            } else {
                that.actualPrice = (Number(that.totalPrice) + Number(val)).toFixed(2);
            }
            jBox.addEventListener("change", function() {
                if (this.checked) {
                    if (that.adid == null) {
                        that.$message.warning("请选择收货地址");
                        that.reload();
                    } else {
                        that.actualPrice = (Number(that.totalPrice) + Number(val) - Number(that.change)).toFixed(2);
                    }
                } else {
                    that.actualPrice = (Number(that.totalPrice) + Number(val)).toFixed(2);
                }
            })
        },

        adid(val) {
            /**
             * 根据adid去获取地址信息
             */
            let postData4 = this.qs.stringify({
                adid: Number(val)
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/address/obtainAddressByAdid',
                data: postData4
            }).then(response => {
                this.address = response.data.address
                console.log(this.address);
            }).catch(error => {
                console.log(error);
            });
        }
    },

    methods: {
        /**
         * 提交订单
         */
        _submitOrder(actualPriceVal) {
            let that = this;
            console.log(actualPriceVal);
            if (actualPriceVal == null) {
                this.$message.warning("请选择收货地址")
            } else {
                let jBox = document.querySelector(".confirmgoods-change-box");
                if (jBox.checked) {
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
                      let user_id = response.data.user.userId;
                      
                      /**
                       * 根据user_id修改change
                       */
                      let postData5 = qs.stringify({
                        user_id: user_id
                      })
                      axios({
                          method: 'post',
                          url: '/ssm_project_war_exploded/user/updateChange',
                          data: postData5
                      }).then(response => {
                          console.log(response.data);
                      }).catch(error => {
                          console.log(error);
                      });

                    }).catch(error => {
                      console.log(error);
                    });                 
                }

                /**
                 * 获取当前时间
                 */
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
                let day = nowtime.getDay();
                let time = year + month + date + day + hour + minute + second;
                // console.log(time);

                /**
                 * 根据adid查询address、shipping，把address、shipping、user_id，当前时间，插入到order中
                 */
                let postData6 = this.qs.stringify({
                    adid: this.adid
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/address/obtainAddressByAdid',
                    data: postData6
                }).then((response) => {
                    let useraddress = response.data.address.pname + response.data.address.cname + response.data.address.dname + response.data.address.area + response.data.address.uname + response.data.address.uphone;
                    
                    /**
                     * 根据dname查询shipping
                     */
                    let postData7 = qs.stringify({
                        dname: response.data.address.dname
                    })
                    axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/district/obtainShippingByDname',
                        data: postData7
                    }).then((response) => {
                        let shipping = response.data.district[0].shipping;

                        let postData8 = qs.stringify({
                            oid: time,
                            user_id: that.user_id,
                            useraddress: useraddress,
                            otime: time,
                            oprice: actualPriceVal,
                            shippingpay: shipping
                        })
                        axios({
                            method: 'post',
                            url: '/ssm_project_war_exploded/order/insertOrder',
                            data: postData8
                        }).then((response) => {
                            console.log(response.data);
                        }).catch((error) => {
                            console.log(error);

                        });


                    }).catch((error) => {
                        console.log(error);
                    });
                }).catch((error) => {
                    console.log(error);
                });

                /**
                 * 根据goods里的sid，查询shoppingcart，获得bid、quantity，插入到orderdetail里
                 */
                for (var i=0; i<this.shoppingcarts.length; i++) {
                    let postData10 = qs.stringify({
                        oid: time,
                        bid: this.shoppingcarts[i].book.bid,
                        odquantity: this.shoppingcarts[i].quantity,
                        odprice: (Number(this.shoppingcarts[i].book.bnowprice) * Number(this.shoppingcarts[i].quantity)).toFixed(2)
                    })
                    axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/orderdetail/insertOrderdetail',
                        data: postData10
                    }).then((response) => {
                        console.log(response.data);
                    }).catch((error) => {
                        console.log(error);
                    });

                    /**
                     * 根据bid，odquantity，去book表减少对应的库存;增加销售量
                     */
                    let postData11 = qs.stringify({
                        bid: this.shoppingcarts[i].book.bid,
                        odquantity: this.shoppingcarts[i].quantity,
                    })
                    axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/book/reduceBinventory',
                        data: postData11
                    }).then((response) => {
                        console.log(response.data);
                    }).catch((error) => {
                        console.log(error);
                    });

                    axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/book/addSalenum',
                        data: postData11
                    }).then((response) => {
                        console.log(response.data);
                    }).catch((error) => {
                        console.log(error);
                    });
                }

                /**
                 * 根据sid删除shopping cart中的记录
                 */
                for (var i=0; i<that.goods.length; i++) {
                    let postData11 = this.qs.stringify({
                        sid: that.goods[i]
                    })
                    this.axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/shoppingcart/deleteShoppingcartBySid',
                        data: postData11
                    }).then((response) => {
                        console.log(response.data);
                    }).catch((error) => {
                        console.log(error);
                    });
                }
                

                /**
                 * 跳转到payment页面
                 */
                let routeUrl = this.$router.resolve({
                    path: '/payment',
                    query: {
                        adid: this.adid,
                        user_id: this.user_id,
                        actualPrice: actualPriceVal,
                        oid: time
                    }
                });
                window.open(routeUrl.href, '_blank');
            }
        }
    }
}
</script>

<style scoped>
.confirmgoods {
    margin-top: 35px;
}

.confirmgoods h2 {
    height: 35px;
    line-height: 35px;
}

.confirmgoods-title {
    display: flex;
    height: 35px;
    font-size: 15px;
    line-height: 35px;
    text-align: center;
    color: rgb(19, 56, 19);
    border-bottom: 2px solid rgb(66, 134, 66);
}
.confirmgoods-goods-btitle {
    width: 400px;
}

.confirmgoods-goods-bprice {
    width: 200px;
    text-align: center;
}

.confirmgoods-goods-bnowprice {
    width: 200px;
    text-align: center;
}

.confirmgoods-goods-bnumber {
    width: 200px;
    text-align: center;
}

.confirmgoods-goods-bcount {
    width: 200px;
    text-align: center;
}

.confirmgoods-area {
    position: relative;
    margin-top: 20px;
    margin-bottom: 180px;
}

.confirmgoods-area ul li {
    display: flex;
    margin-bottom: 5px;
    height: 100px;
    line-height: 100px;
    background-color: rgb(241, 251, 230);
}

.confirmgoods-goods-btitle img {
    margin: 19px 30px;
    /* margin-right: 30px; */
    width: 60px;
    height: 60px;
    border: 1px solid #ccc;
}

.confirmgoods-goods-change,
.confirmgoods-goods-shipping,
.confirmgoods-goods-totalprice {
    margin-top: 10px;
    padding: 0 30px;
    height: 60px;
    text-align: right;
    font-size: 15px;
    font-weight: 600;
    line-height: 60px;
    background-color: rgb(241, 251, 230);
}

.confirmgoods-goods-totalprice span {
    font-size: 23px;
    color: red;
}

.confirmgoods-goods-shipping span {
    font-size: 20px;
    color: red;
}

.confirmgoods-goods-change {
    display: flex;
    justify-content: flex-end;
}

.confirmgoods-goods-change span {
    font-size: 18px;
    color: red;
}

.confirmgoods-goods-information {
    margin-left: 700px;
    margin-top: 20px;
    width: 496px;
    height: 180px;
    border: 2px solid green;
    border-radius: 5px;
}

.confirmgoods-information-price {
    padding: 10px 30px;
    text-align: right;
    font-size: 15px;
    font-weight: 600;
}

.confirmgoods-information-price span {
    font-size: 25px;
    color: red;
}

.confirmgoods-change-box {
    margin: 14px 10px;
    width: 16px;
}

.confirmgoods-information-people,
.confirmgoods-information-address {
    margin: 20px 30px;
    height: 40px;
    font-size: 15px;
    font-weight: 600;
    text-align: right;
    overflow: hidden;
}

.confirmgoods-information-people span,
.confirmgoods-information-address span {
    font-weight: 400;
}

.confirmgoods-information-submit {
    position: absolute;
    right: 0;
    width: 200px;
    height: 40px;
    font-size: 20px;
    font-weight: 600;
    text-align: center;
    line-height: 40px;
    color: white;
    background-color: green;
    cursor: pointer;
}

</style>