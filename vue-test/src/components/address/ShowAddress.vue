<template>
    <div class="show-address">
        <div class="show-address-head">已保存&nbsp; {{ totalAddress }} &nbsp;条收货地址，还可以保存&nbsp; {{ 20 - totalAddress }} &nbsp;条收货地址 </div>
        <div class="show-address-body">
            <ul>
                <li class="address-first-li">
                    <div class="address-first-div address-first-div1">收货人</div>
                    <div class="address-first-div address-first-div2">所在地区</div>
                    <div class="address-first-div address-first-div3">详细地址</div>
                    <div class="address-first-div address-first-div4">邮政编码</div>
                    <div class="address-first-div address-first-div5">手机号码</div>
                    <div class="address-first-div address-first-div6">操作</div>
                </li>
                <li v-for="item in addresses" :key="item.adid" class="address-for-li">
                    <div class="address-first-div1"> {{ item.uname }} </div>
                    <div class="address-first-div2"> {{ item.pname }} &nbsp; {{ item.cname }} &nbsp; {{ item.dname }} </div>
                    <div class="address-first-div3"> {{item.area}} </div>
                    <div class="address-first-div4"> {{item.postalcode}} </div>
                    <div class="address-first-div5"> {{ item.uphone }} </div>
                    <div class="address-first-div6">
                        <a href="javascript:;" @click="_modifyAddress(item.adid)">修改</a>
                        <span class="address-span">/</span>
                        <a href="javascript:;" @click="_deleteAddress(item.adid)">删除</a>
                    </div>
                </li>
            </ul>
        </div>

        <!-- 修改模态框 -->
        <div class="address-modal-box">
            <div class="close-modal-box" @click="_closeModify()">X</div>
            <div class="address-row">
                <div class="address-row-left">地址信息：</div>
                <div class="address-row-right">
                    省：
                    <select v-model="provinceItem" name="province1" id="province1" @change="_selectProvince(provinceItem)">
                      <option selected> {{ address.pname }} </option>
                      <option v-for="item in provinces" :key="item.pid"> {{ item.pname }} </option>
                    </select>
                    市：
                    <select v-model="cityItem" name="city1" id="city1" @change="_selectCity(cityItem)">
                      <option selected> {{ address.cname }} </option>
                      <option v-for="item in citys" :key="item.cid"> {{ item.cname }} </option>
                    </select>
                    区：
                    <select v-model="districtItem" name="district1" id="district1" @change="_selectDistrict(districtItem)">
                      <option selected> {{ address.dname }} </option>
                      <option v-for="item in districts" :key="item.did"> {{ item.dname }} </option>
                    </select>
                </div>
                <span>X&nbsp;请选择地址</span>
            </div>
            <div class="address-row">
                <div class="address-row-left">详细地址：</div>
                <div class="address-row-right">
                    <textarea v-model="address.area" cols="20" rows="2" id="area1"></textarea>
                </div>
                <span>X&nbsp;请输入正确的地址</span>
            </div>
            <div class="address-row">
                <div class="address-row-left">邮政编码：</div>
                <div class="address-row-right">
                    <input v-model="address.postalcode" type="text" id="postalcode1">
                </div>
                <span>X&nbsp;请输入正确的邮政编码</span>
            </div>
            <div class="address-row">
                <div class="address-row-left">收货人姓名：</div>
                <div class="address-row-right">
                    <input v-model="address.uname" type="text" id="uname1">
                </div>
                <span>X&nbsp;请输入正确的收货人姓名</span>
            </div>
            <div class="address-row">
                <div class="address-row-left">手机号码：</div>
                <div class="address-row-right">
                    <input v-model="address.uphone" type="text" id="uphone1">
                </div>
                <span>X&nbsp;请输入正确的手机号码</span>
            </div>
            <div class="address-row">
                <div class="address-bth1" @click="_updateAddress(provinceItem, cityItem, districtItem, address.area, address.postalcode, address.uname, address.uphone)">保存</div>
            </div>
        </div>
    </div>
</template>

<script>
import qs from 'qs';
import axios from "axios";

export default {
    name: 'ShowAddress',

    inject:['reload'], //调用刷新

    data() {
        return {
            totalAddress: null,
            remainingAddress: null,
            addresses: [],
            user_id: null,
            provinces: [],
            citys: [],
            districts: [],
            provinceItem: null,
            cityItem: null,
            districtItem: null,
            address: []
        }
    },

    created() {
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
           * 获取用户地址
           */
          let postData = qs.stringify({
              addresses: this.addresses,
              user_id: this.user_id
          })
          axios({
              method: 'post',
              url: '/ssm_project_war_exploded/address/obtainAddressByUserId',
              data: postData
          }).then(response => {
              this.addresses = response.data.addresses;
          }).catch(error => {
              console.log(error);
          })

          /**
           * 获取用户地址数量
           */
          axios({
              method: 'post',
              url: '/ssm_project_war_exploded/address/obtainAddressCount',
              data: postData
          }).then(response => {
              this.totalAddress = response.data;
              this.$emit('totalAddress', this.totalAddress);
          }).catch(error => {
            console.log(error);
          })
        }).catch(error => {
          console.log(error);
        })

        /**
         * 获取省份信息
         */
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

    methods: {
        /**
         * 删除按钮
         */
        _deleteAddress(adidVal) {
            let postData = this.qs.stringify({
                adid: adidVal
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/address/deleteAddress',
                data: postData
            }).then(response => {
                console.log(response.data);
                this.reload();
            }).catch(error => {
                console.log(error);
            })
        },

        /**
         * 修改按钮
         */
        _modifyAddress(adidVal) {
            let modalBox = document.querySelector(".address-modal-box");  
            modalBox.style.display = 'block';
            modalBox.setAttribute('adid', adidVal);
            let postData = this.qs.stringify({
                adid: adidVal
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/address/obtainAddressByAdid',
                data: postData
            }).then(response => {
                this.address = response.data.address
            }).catch(error => {
                console.log(error);
            })
        },

        /**
         * 保存修改按钮
         */
        _updateAddress (provinceItemVal, cityItemVal, districtItemVal, areaVal, postalcodeVal, unameVal, uphoneVal) {
            let modalBox = document.querySelector(".address-modal-box"); 
            let adid = modalBox.getAttribute('adid');
            if (provinceItemVal == null || cityItemVal == null || districtItemVal == null || areaVal == null || postalcodeVal == null || unameVal == null || uphoneVal == null) {
                this.$message.warning("请填写完整信息")
            } else {
                let postData = this.qs.stringify({
                    adid: adid,
                    uname: unameVal,
                    uphone: uphoneVal,
                    pname: provinceItemVal,
                    cname: cityItemVal,
                    dname: districtItemVal,
                    area: areaVal,
                    postalcode: postalcodeVal
                });
                this.axios({
                  method: 'post',
                  url: '/ssm_project_war_exploded/address/updateAddress',
                  data: postData
                }).then(response => {
                  this.$message.success("修改收货地址成功");
                  this.reload();
                }).catch(error => {
                  console.log(error);
                  this.$message.warning("修改收货地址失败");
                  this.reload();
                })
            }
        },

        /**
         * 根据所选择省份获取城市
         */
        _selectProvince(val) {
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
          }).catch((error) => {
            console.log(error);
          })
        },
  
        /**
         * 根据所选择城市获取地区
         */
        _selectCity(val) {
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
          }).catch((error) => {
            console.log(error);
          })
        },
  
        /**
         * 根据所选择地区，获取相应的邮费
         */
        _selectDistrict(val) {
          let postData = this.qs.stringify({
            districts: this.districts,
            dname: val
          })
          this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/district/obtainShippingByDname',
            data: postData
          }).then((response) => {
            this.shipping = response.data.district[0].shipping;
            console.log(response.data.district[0].shipping);
          }).catch((error) => {
            console.log(error);
          })
        },

        /**
         * 关闭按钮
         */
        _closeModify() {
            let modalBox = document.querySelector(".address-modal-box");
            modalBox.style.display = 'none';
        }
    },

    mounted() {
      let regUname = /^[\u4E00-\u9FA5A-Za-z0-9_]{3,20}$/;  //收货人姓名验证
      let regUphone = /^1[3|4|5|7|8]\d{9}$/;  //收货人手机号码验证
      let regPostalcode = /^[1-9]\d{5}$/; //邮政编码
      let uname = document.querySelector("#uname1");
      let uphone = document.querySelector("#uphone1");
      let postalcode = document.querySelector("#postalcode1");
      let area = document.querySelector("#area1");
      let province = document.querySelector("#province1");
      let city = document.querySelector("#city1");
      let district = document.querySelector("#district1");
      let addressBth = document.querySelector(".address-bth1");
      uname.addEventListener("blur", function() {
        if ( !regUname.test(this.value) || this.value == '' ) {
          this.parentNode.parentNode.lastChild.style.display = 'block';
          addressBth.style.pointerEvents = 'none';
        } else {
          this.parentNode.parentNode.lastChild.style.display = 'none';
          addressBth.style.pointerEvents = 'auto';
        }
      });
      uphone.addEventListener("blur", function() {
        if ( !regUphone.test(this.value) || this.value == '' ) {
          this.parentNode.parentNode.lastChild.style.display = 'block';
          addressBth.style.pointerEvents = 'none';
        } else {
          this.parentNode.parentNode.lastChild.style.display = 'none';
          addressBth.style.pointerEvents = 'auto';
        }
      });
      postalcode.addEventListener("blur", function() {
        if ( !regPostalcode.test(this.value) || this.value == '') {
          this.parentNode.parentNode.lastChild.style.display = 'block';
          addressBth.style.pointerEvents = 'none';
        } else {
          this.parentNode.parentNode.lastChild.style.display = 'none';
          addressBth.style.pointerEvents = 'auto';
        }
      });
      area.addEventListener("blur", function() {
        if (this.value == '') {
          this.parentNode.parentNode.lastChild.style.display = 'block';
          addressBth.style.pointerEvents = 'none';
        } else {
          this.parentNode.parentNode.lastChild.style.display = 'none';
          addressBth.style.pointerEvents = 'auto';
        }
      });
      province.addEventListener("blur", function() {
        if (city.value == '' || district.value == '') {
          this.parentNode.parentNode.lastChild.style.display = 'block';
          addressBth.style.pointerEvents = 'none';
        } 
        city.addEventListener("blur", function() {
          if (district.value == '') {
            this.parentNode.parentNode.lastChild.style.display = 'block';
            addressBth.style.pointerEvents = 'none';
          }
          district.addEventListener("blur", function() {
            if (district.value != '') {
              this.parentNode.parentNode.lastChild.style.display = 'none';
              addressBth.style.pointerEvents = 'auto';
            }
          })
        })
      })
    }
}
</script>

<style>
.show-address {
    position: relative;
    margin: 0 40px;;
}

.show-address-head {
    margin-bottom: 15px;
    padding-left: 15px;
    height: 30px;
    font-size: 15px;
    font-weight: 600;
    color: rgb(31, 114, 31);
    line-height: 30px;
    background-color: rgb(234, 250, 238);
}

.show-address-body {
    margin-top: 15px;
    /* background-color: rgb(233, 245, 220); */
}

.show-address-body ul li {
    display: flex;
}
.show-address-body ul div {
    border: 2px solid rgb(172, 199, 172);
    margin-top: -2px;
    margin-left: -2px;
}

.address-first-li {
    height: 45px;
}

.address-first-div {
    font-size: 16px;
    font-weight: 500;
    text-align: center;
    line-height: 45px;
    background-color: rgb(206, 250, 206);
}

.address-first-div1 {
    width: 160px;
}

.address-first-div2 {
    width: 240px;
}

.address-first-div3 {
    width: 300px;
}

.address-first-div4 {
    width: 120px;
}

.address-first-div5 {
    width: 160px;
}

.address-first-div6 {
    width: 140px;
}

.address-for-li {
    height: 70px;
    /* line-height: 30px; */
    font-size: 14px;
    text-align: center;
    word-break: break-all;
}

.address-span {
    margin: 0 8px;
}

.address-modal-box {
    display: none;
    position: absolute;
    top: -100px;
    left: 50%;
    padding: 40px;
    transform: translateX(-50%);
    width: 640px;
    height: 420px;
    background-color: rgba(226, 255, 209, 0.76);
    border-radius: 20px;
}

.address-bth1 {
    margin-left: 130px;
    width: 80px;
    height: 30px;
    font-size: 18px;
    text-align: center;
    line-height: 30px;
    color: white;
    background-color: rgb(34, 95, 34);
    border-radius: 5px;
    cursor: pointer;
}

.close-modal-box {
    position: absolute;
    right: 15px;
    top: 15px;
    width: 30px;
    height: 30px;
    font-size: 18px;
    line-height: 30px;
    text-align: center;
    /* background-color: pink; */
    border-radius: 50%;
    border: 1px solid green;
    cursor: pointer;
}
</style>