import Vue from 'vue';
import Router from 'vue-router';
// import UserHome from '@/components/UserHome';
import Login from '@/components/login/login.vue';
import Home from '@/components/home/home.vue';
import List from '@/components/list/list.vue';
import Details from '@/components/details/details.vue';
import Registered from '@/components/registered/registered.vue';
import ShoppingCart from '@/components/shoppingcart/shoppingcart.vue';
import KindList from '@/components/kindlist/kindlist.vue';
import Address from '@/components/address/address.vue';
import Wallet from '@/components/wallet/wallet.vue';
import ConfirmOrder from '@/components/confirmorder/confirmOrder.vue';
import Payment from '@/components/payment/payment.vue';
import Order from '@/components/order/order.vue';
import User from '@/components/user/user.vue';
import Goods from '@/components/goods/goods.vue';
import Admin from '@/components/admin/admin.vue';

Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/UserHome',
    //   name: 'UserHome',
    //   component: UserHome
    // },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/home',
      name: "home",
      component: Home
    },
    {
      path: '/list',
      name: "list",
      component: List
    },
    {
      path: '/details',
      name: "details",
      component: Details
    },
    {
      path: '/registered',
      name: "registered",
      component: Registered
    },
    {
      path: '/shoppingcart',
      name: "shoppingcart",
      component: ShoppingCart
    },
    {
      path: '/kindlist',
      name: "kindlist",
      component: KindList
    },
    {
      path: '/address',
      name: "address",
      component: Address
    },
    {
      path: '/wallet',
      name: "wallet",
      component: Wallet
    },
    {
      path: '/confirmorder',
      name: 'confirmorder',
      component: ConfirmOrder
    },
    {
      path: '/payment',
      name: 'payment',
      component: Payment
    },
    {
      path: '/order',
      name: 'order',
      component: Order
    },
    {
      path: '/user',
      name: 'user',
      component: User
    },
    {
      path: '/goods',
      name: 'goods',
      component: Goods
    },
    {
      path: '/admin',
      name: 'admin',
      component: Admin
    }
  ]
})
