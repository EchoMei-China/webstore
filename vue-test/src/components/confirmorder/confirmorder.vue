<template>
    <div>
        <LoginTopNavigation></LoginTopNavigation>
        <RegisteredHead></RegisteredHead>
        <ChooseAddress @shipping="getValue($event)" @adid="getValue2($event)"></ChooseAddress>
        <ConfirmGoods :goods="goods" :totalPrice="totalPrice" :shipping="shipping" :adid="adid"></ConfirmGoods>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '../home/Footer';
import ConfirmGoods from '../confirmorder/ConfirmGoods';
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue';
import RegisteredHead from '@/components/registered/RegisteredHead.vue';
import ChooseAddress from '../confirmorder/ChooseAddress';


export default {
    name: 'ConfirmOrder',

    components: {
        Footer,
        ConfirmGoods,
        ChooseAddress,
        RegisteredHead,
        LoginTopNavigation
    },

    data() {
        return {
            goods: [],
            totalPrice: null,
            shipping: null,
            adid: null
        }
    },

    created() {
        // this.goods = this.$route.query.goods;
        if (typeof(this.$route.query.goods) == 'string') {
            this.goods.push(this.$route.query.goods)
        } else {
            this.goods = this.$route.query.goods;
        }
        // console.log(this.goods);
        this.totalPrice = this.$route.query.totalPrice;
    },

    methods: {
        getValue(data) {
            this.shipping = data;
        },

        getValue2(data) {
            this.adid = data;
        }
    }
}
</script>

<style>

</style>