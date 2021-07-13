<template>
  <!-- 购物车详情 -->
    <div class="product">
      <!-- 图片 -->
      <div class="product-preview">
        <div class="picture">
          <img :src="shoppingcart.bookimage" alt="" />
        </div>
      </div>
      <!-- 购物信息 -->
      <div class="product-detail">
        <div class="title">
          {{ shoppingcart.bookname }}&nbsp;&nbsp;&nbsp;&nbsp;{{ shoppingcart.bookwriter }}
        </div>
        <div class="price">
          <div class="price-row">
            <div class="ptext">建议售价:</div>
            <div class="pprice">{{ shoppingcart.bookprice }}</div>
          </div>
          <div class="price-row">
            <div class="ptext">现价：</div>
            <div class="pnowprice">{{ shoppingcart.booknowprice }}</div>
          </div>
        </div>
        <div class="logistics">
          <div class="ptext">配送:</div>
          <p>江苏南京</p>
          <p class="pstyle">至</p>
          <div class="linkage">
            <select v-model="provinceItem" name="province" id="province" @change="_selectProvince(provinceItem)">
              <option v-for="item in provinces" :key="item.pid"> {{ item.pname }} </option>
            </select>
            <select v-model="cityItem" name="city" id="city" @change="_selectCity(cityItem)">
              <option v-for="item in citys" :key="item.cid"> {{ item.cname }} </option>
            </select>
            <select v-model="districtItem" name="district" id="district" @change="_selectDistrict(districtItem)">
              <option v-for="item in districts" :key="item.did"> {{ item.dname }} </option>
            </select>
          </div>
          <p class="pstyle">快递：</p>
          <p>{{ shipping }}</p>
          <p class="pstyle">元</p>
        </div>
        <div class="number">
          <div class="ptext">数量:</div>
          <div class="choosebtns">
            <input type="text" v-model="quantity" />
            <div class="operate">
              <a href="javascript:;" @click="_add()"><div class="add">+</div></a>
              <a href="javascript:;" @click="_minus()"><div class="minus">-</div></a>
            </div>
          </div>
          <a href="javascript:;"><div class="addCar" @click="_addToShoppingcart(shoppingcart.bookid, quantity)">加入购物车</div></a>
          <a href="javascript:;"><div class="buyNow">立即购买</div></a>
        </div>
      </div>
    </div>
</template>

<script>
import qs from 'qs';
import axios from 'axios';
import {Addfavorites} from '@/assets/behaviors.js';

export default {
    name: 'DetailShoppingCart',

    props: {
      shoppingcart: {
        bookid: Number,
        bookimage: String,
        bookname: String,
        bookwriter: String,
        bookprice: String,
        booknowprice: String,
      }
    },

    data() {
      return {
        provinces: [],
        citys: [],
        districts: [],
        shipping: '',
        provinceItem: '',
        cityItem: '',
        districtItem: '',
        quantity: 1
      }
    },

    methods: {
      Addfavorites,
      /**
       * 根据所选择省份获取城市
       */
      _selectProvince(val) {
        // alert(this.provinceItem);
        let postData = this.qs.stringify({
          citys: this.citys,
          pname: val
        })
        this.axios({
          method: 'post',
          url: '/ssm_project_war_exploded/city/obtainCity',
          data: postData
        }).then((response) => {
          this.citys = response.data.citys;
          this.districtItem = '';
          this.shipping = '';
          // console.log(this.citys);
        }).catch((error) => {
          console.log(error);
        })
      },

      /**
       * 根据所选择城市获取地区
       */
      _selectCity(val) {
        // console.log(this.cityItem);
        let postData = this.qs.stringify({
          districts: this.districts,
          cname: val
        })
        this.axios({
          method: 'post',
          url: '/ssm_project_war_exploded/district/obtainDistrict',
          data: postData
        }).then((response) => {
          this.districts = response.data.districts;
          this.shipping = '';
          // console.log(this.districts);
        }).catch((error) => {
          console.log(error);
        })
      },

      /**
       * 根据所选择地区，获取相应的邮费
       */
      _selectDistrict(val) {
        // const that = this;
        let postData = this.qs.stringify({
          districts: this.districts,
          dname: val
        })
        this.axios({
          method: 'post',
          url: '/ssm_project_war_exploded/district/obtainShippingByDname',
          data: postData
        }).then((response) => {
          // var shipping;
          this.shipping = response.data.district[0].shipping;
          console.log(111111111111);
          console.log(response.data.district[0].shipping);
          // console.log(this.districts);
        }).catch((error) => {
          console.log(error);
        })
      },

      /**
       * 加号按钮
       */
      _add() {
        if( this.quantity < 999) {
          this.quantity++;
        }
      },

      /**
       * 减号按钮
       */
      _minus() {
        if (this.quantity > 1) {
          this.quantity--;
        } 
      },

      /**
       * 加入购物车
       */
      _addToShoppingcart(bidVal, quantityVal) {
        let that = this;
        let user_nickname = sessionStorage.getItem('userNickName');
        console.log(user_nickname);
        if (user_nickname == null) {
          this.$message.warning("请登陆");
        } else {
            this.Addfavorites(bidVal);
            let postData1 = this.qs.stringify({
            user_nickname: user_nickname
          })
          this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/user/botainUserIdByNickname',
            data: postData1
          }).then(response => {
            let user_id = response.data.user.userId;
            // 根据用户id和书id先进行判断，如果表中已经有这样的数据，则进行更新
            let postData = qs.stringify({
              user_id: user_id,
              bid: bidVal
            })
            axios({
              method: 'post',
              url: '/ssm_project_war_exploded/shoppingcart/judgeShoppingcart',
              data: postData
            }).then(response => {
              console.log(response.data);
              if (response.data == 0) {
                let postData2 = qs.stringify({
                  user_id: user_id,
                  bid: bidVal,
                  quantity: quantityVal
                })
                axios({
                  method: 'post',
                  url: '/ssm_project_war_exploded/shoppingcart/insertShoppingcart',
                  data: postData2
                }).then(response => {
                  console.log(response);
                  this.$message.success("加入购物车成功")
                }).catch(error => {
                  console.log(error);
                  this.$message.warning("加入购物车失败")
                })
              } else {
                let postData3 = qs.stringify({
                  user_id: user_id,
                  bid: bidVal,
                  quantity: quantityVal
                })
                axios({
                  method: 'post',
                  url: '/ssm_project_war_exploded/shoppingcart/updateShoppingcart',
                  data: postData3
                }).then(response => {
                  console.log(response);
                  this.$message.success("加入购物车成功")
                }).catch(error => {
                  console.log(error);
                  this.$message.warning("加入购物车失败")
                })
              }
            }).catch(error => {
              console.log(error);
            })
          })
        }        
      }
    },

    created() {
      let postData = this.qs.stringify({
        provinces: this.provinces
      })
      this.axios({
        method: 'post',
        url: '/ssm_project_war_exploded/province/obtainProvince',
        data: postData
      }).then((response) => {
        this.provinces = response.data.provinces;
      }).catch((error) => {
        console.log(error);
      })
    },

    mounted() {
      let choosebtns = document.querySelector(".choosebtns");
      let ipt = choosebtns.querySelector("input");
      ipt.addEventListener('blur', function() {
        if (ipt.value <1 || ipt.value >= 1000) {
          alert("请输入大于1且小于1000的数字")
        }
      })
    }
}
</script>

<style>
.product {
  display: flex;
  /* background-color: rgb(218, 184, 189); */
}

.product-preview {
  width: 400px;
  height: 420px;
  /* background-color: aquamarine; */
}

.product-detail {
  width: 800px;
  /* background-color: rgb(211, 179, 179); */
}

.picture {
  height: 398px;
  border: 1px solid #ccc;
}

.picture img {
  width: 398px;
  height: 398px;
}

.title {
  margin-left: 40px;
  /* background-color: aquamarine; */
  font-size: 20px;
  font-weight: 700;
}

.price {
  margin-left: 30px;
  margin-right: 30px;
  margin-top: 10px;
  padding: 10px;
  background-color: rgb(249, 254, 241);
}

.price-row {
  display: flex;
  height: 35px;
  line-height: 35px;
}

.ptext {
  width: 70px;
  font-size: 14px;
  color: rgb(108, 139, 83);
}

.pprice {
  /* margin-left: 10px; */
  font-size: 13px;
  font-weight: 550;
  text-decoration: line-through;
}

.pprice::before {
  display: inline-block;
  content: "¥";
  font-size: 13px;
}

.pnowprice {
  /* margin-left: 10px; */
  color: rgb(223, 58, 74);
  font-size: 30px;
  font-weight: 700;
}

.pnowprice::before {
  display: inline-block;
  content: "¥";
  font-size: 18px;
}

.logistics {
  display: flex;
  margin-top: 10px;
  margin-left: 40px;
  height: 40px;
  line-height: 40px;
  /* background-color: pink; */
}

.pstyle {
  margin-left: 10px;
  margin-right: 10px;
  font-weight: 600;
}

.number {
  display: flex;
  margin-left: 40px;
  margin-top: 20px;
  height: 46px;
  line-height: 46px;
  /* background-color: pink; */
}

.choosebtns {
  display: flex;
  width: 50px;
  height: 46px;
  /* background-color: wheat; */
  /* box-sizing: border-box; */
}

.choosebtns input {
  border: 1px solid #ccc;
  width: 33px;
  height: 44px;
  text-align: center;
  line-height: 44px;
}

.operate {
  display: flex;
  flex-direction: column;
  /* background-color: blueviolet; */
}

.choosebtns .operate div {
  width: 15px;
  height: 21px;
  border: 1px solid #ccc;
  text-align: center;
  line-height: 22px;
  font-size: 12px;
  font-weight: 500;
  cursor: pointer;
}

.addCar,
.buyNow {
  margin-left: 20px;
  width: 142px;
  height: 46px;
  font-size: 15px;
  font-weight: 400px;
  text-align: center;
  border: 1px solid rgb(18, 89, 18);
  color: rgb(18, 89, 18);
  box-sizing: border-box;
  background-color: rgb(213, 245, 204);
  cursor: pointer;
}

.addCar {
  color: white;
  background-color: rgb(18, 89, 18);
}
</style>