<template>
    <div class="add-address">
        <div class="add-address-head">新增收货地址</div>
        <div class="add-address-body">
            <div class="address-row">
                <div class="address-row-left">地址信息：</div>
                <div class="address-row-right">
                    省：
                    <select v-model="provinceItem" name="province" id="province" @change="_selectProvince(provinceItem)">
                      <option v-for="item in provinces" :key="item.pid"> {{ item.pname }} </option>
                    </select>
                    市：
                    <select v-model="cityItem" name="city" id="city" @change="_selectCity(cityItem)">
                      <option v-for="item in citys" :key="item.cid"> {{ item.cname }} </option>
                    </select>
                    区：
                    <select v-model="districtItem" name="district" id="district" @change="_selectDistrict(districtItem)">
                      <option v-for="item in districts" :key="item.did"> {{ item.dname }} </option>
                    </select>
                </div>
                <span>X&nbsp;请选择地址</span>
            </div>
            <div class="address-row">
                <div class="address-row-left">详细地址：</div>
                <div class="address-row-right">
                    <textarea v-model="area" cols="20" rows="2" placeholder="请输入详细地址信息，如道路、门牌号、小区、楼栋号、单元等信息" id="area"></textarea>
                </div>
                <span>X&nbsp;请输入正确的地址</span>
            </div>
            <div class="address-row">
                <div class="address-row-left">邮政编码：</div>
                <div class="address-row-right">
                    <input v-model="postalcode" type="text" placeholder="请填写邮政编码" id="postalcode">
                </div>
                <span>X&nbsp;请输入正确的邮政编码</span>
            </div>
            <div class="address-row">
                <div class="address-row-left">收货人姓名：</div>
                <div class="address-row-right">
                    <input v-model="uname" type="text" placeholder="长度不超过20个字符" id="uname">
                </div>
                <span>X&nbsp;请输入正确的收货人姓名</span>
            </div>
            <div class="address-row">
                <div class="address-row-left">手机号码：</div>
                <div class="address-row-right">
                    <input v-model="uphone" type="text" placeholder="请填写手机号码" id="uphone">
                </div>
                <span>X&nbsp;请输入正确的手机号码</span>
            </div>
            <div class="address-row">
                <div class="address-bth" @click="_insertAddress(provinceItem, cityItem, districtItem, area, postalcode, uname, uphone)">保存</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'AddAddress',

    inject:['reload'], //调用刷新

    data() {
        return {
            provinces: [],
            citys: [],
            districts: [],
            provinceItem: null,
            cityItem: null,
            districtItem: null,
            area: null,
            uname: null,
            postalcode: null,
            uphone: null,
            user_id: null,
            shipping: null
        }
    },

    props: {
      totalAddress: Number
    },

    methods: {
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
         * 提交按钮
         */
        _insertAddress(provinceItemVal, cityItemVal, districtItemVal, areaVal, postalcodeVal, unameVal, uphoneVal) {
          if (this.totalAddress >= 20) {
            this.$message.warning("地址数量已达上限");
            this.reload();
          } else if (provinceItemVal == null || cityItemVal == null || districtItemVal == null || areaVal == null || postalcodeVal == null || unameVal == null || uphoneVal == null) {
            this.$message.warning("请填写完整信息")
            } else {
              let postData3 = this.qs.stringify({
              user_id: this.user_id,
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
                url: '/ssm_project_war_exploded/address/insertNewAddress',
                data: postData3
              }).then(response => {
                this.$message.success("添加收货地址成功");
                this.reload();
              }).catch(error => {
                console.log(error);
                this.$message.warning("添加收货地址失败");
                this.reload();
              })
            }         
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
        console.log(response.data.user);
        this.user_id = response.data.user.userId;
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

    mounted() {
      let regUname = /^[\u4E00-\u9FA5A-Za-z0-9_]{3,20}$/;  //收货人姓名验证
      let regUphone = /^1[3|4|5|7|8]\d{9}$/;  //收货人手机号码验证
      let regPostalcode = /^[1-9]\d{5}$/; //邮政编码
      let uname = document.querySelector("#uname");
      let uphone = document.querySelector("#uphone");
      let postalcode = document.querySelector("#postalcode");
      let area = document.querySelector("#area");
      let province = document.querySelector("#province");
      let city = document.querySelector("#city");
      let district = document.querySelector("#district");
      let addressBth = document.querySelector(".address-bth");
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
.add-address {
    margin: 30px 40px;
    /* background-color: rgb(234, 250, 238); */
}

.add-address-head {
    margin-bottom: 15px;
    padding-left: 15px;
    height: 30px;
    font-size: 15px;
    font-weight: 600;
    color: rgb(31, 114, 31);
    line-height: 30px;
    background-color: rgb(234, 250, 238);
}

.address-row {
    display: flex;
}

input,
textarea {
    padding: 0 5px;
    width: 300px;
    height: 30px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

textarea {
    height: 70px;
    resize: none;
    overflow: hidden;
}

.address-row-left {
    margin-bottom: 25px;
    width: 120px;
    height: 30px;
    font-size: 14px;
    line-height: 30px;
    text-align: right;
    /* background-color: pink; */
}

.address-row-left::before {
    display: inline-block;
    margin-right: 2px;
    content: '*';
    color: red;
}

.address-row-right {
    margin: 0 0 30px 10px;
    /* background-color: blanchedalmond; */
}

.address-bth {
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

.address-row span {
  display: none;
  margin-left: 20px;
  height: 3px;
  color: rgb(248, 41, 41);
  line-height: 30px;
}
</style>