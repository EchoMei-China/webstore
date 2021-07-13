<template>
    <div class="chooseaddress">
        <h2>选择收货地址</h2>
        <ul>
            <li v-for="item in addresses" :key="item.adid">
                <div class="chooseaddress-div" :adid="item.adid" :dname="item.dname" :pname="item.pname" :cname="item.cname" :uname="item.uname" :uphone="item.uphone" :area="item.area" :postalcode="item.postalcode">
                    <div class="chooseaddress-div-title">
                        {{ item.pname }}{{ item.cname }}&nbsp;( {{item.uname}} 收)
                    </div>
                    <div class="chooseaddress-div-area">
                        {{ item.cname }}&nbsp;{{ item.dname }}&nbsp;{{ item.area }}<br>
                        {{ item.uphone }}<br>
                        {{ item.postalcode }}
                    </div>
                </div>
            </li>
        </ul>
        <div class="chooseaddress-use-newaddress" @click="_useNewAddress()">使用新地址</div>
        <div class="chooseaddress-add-modalbox">
            <div class="colse-chooseaddress-modalbox" @click="_closeModal()">X</div>
            <AddAddress v-bind:totalAddress="totalAddress"></AddAddress>
        </div>
    </div>
</template>

<script>
import qs from 'qs';
import axios from "axios";
import AddAddress from '../address/AddAddress';

export default {
    name: 'ChooseAddress',

    inject:['reload'], //调用刷新

    components: {
        AddAddress
    },

    data() {
        return {
            addresses: [],
            user_id: null,
            totalAddress: null,
            shipping: null,
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
          });

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
          });
        }).catch(error => {
            console.log(error);
        })
    },

    mounted() {
        setTimeout(() => {
            let that = this;
            let chooseaddressDivs = document.querySelectorAll(".chooseaddress-div");
            for (var i=0; i<chooseaddressDivs.length; i++) {
                chooseaddressDivs[i].addEventListener("click", function() {
                    for (var i=0; i<chooseaddressDivs.length; i++) {
                        chooseaddressDivs[i].style.border = '4px dashed #ccc';
                        chooseaddressDivs[i].style.backgroundColor = 'white'
                    }
                    this.style.border = '4px solid green';
                    this.style.backgroundColor = 'rgb(249, 255, 240)';
                    let dname = this.getAttribute('dname');
                    let adid = this.getAttribute('adid');
                    that.$emit('adid', adid);
                    /**
                     * 根据dname去查找邮费
                     */
                    let postData = qs.stringify({
                        dname: dname
                    })
                    axios({
                        method: 'post',
                        url: '/ssm_project_war_exploded/district/obtainShippingByDname',
                        data: postData
                    }).then(response => {
                        that.$emit('shipping', response.data.district[0].shipping);
                    }).catch(error => {
                        console.log(error);
                    })
                })
            }
        },800)
    },

    methods: {
        /**
         * 关闭新增地址模态框
         */
        _closeModal() {
            let addModal = document.querySelector(".chooseaddress-add-modalbox");
            addModal.style.display = 'none'
        },

        /**
         * 使用新地址
         */
        _useNewAddress() {
            if (this.totalAddress >= 20) {
                this.$message.warning("地址数量已达上限");
                this.reload();
            } else {
                let addModal = document.querySelector(".chooseaddress-add-modalbox");
                addModal.style.display = 'block'
            }
        }
    }
}
</script>

<style>
.chooseaddress {
    position: relative;
    margin-top: 30px;
    /* background-color: pink; */
}

h2 {
    height: 35px;
    line-height: 35px;
}

.chooseaddress ul {
    display: flex;
    flex-wrap: wrap;
}

.chooseaddress ul li {
    flex: 0 0 300px;
}

.chooseaddress-div {
    margin: 10px 10px;
    height: 120px;
    border: 4px dashed #ccc;
    border-radius: 5px;
    cursor: pointer;
    /* background-color: pink; */
}

.chooseaddress-div-title {
    padding: 0 8px;
    overflow: hidden;
    height: 30px;
    font-size: 13px;
    font-weight: 600;
    line-height: 30px;
    border-bottom: 1px solid rgb(216, 213, 213);
}

.chooseaddress-div-area {
    padding: 10px 8px;
    overflow: hidden;
    font-size: 13px;
    font-weight: 400;
}

.chooseaddress-use-newaddress {
    margin: 10px 10px;
    width: 100px;
    height: 30px;
    font-size: 13px;
    line-height: 30px;
    text-align: center;
    border: 1px solid #ccc;
    border-radius: 5px;
    cursor: pointer;
}

.chooseaddress-add-modalbox {
    display: none;
    position: absolute;
    top: 0;
    left: 50%;
    transform: translateX(-300px);
    width: 600px;
    height: 500px;
    background-color: rgb(190, 216, 161);
    border-radius: 5px;
    z-index: 99;
}

.colse-chooseaddress-modalbox {
    position: absolute;
    top: 0;
    right: 0;
    width: 35px;
    height: 35px;
    font-size: 18px;
    line-height: 35px;
    text-align: center;
    cursor: pointer;
}
</style>