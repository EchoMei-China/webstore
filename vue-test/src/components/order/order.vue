<template>
    <div class="order">
        <LoginTopNavigation></LoginTopNavigation>
        <RegisteredHead></RegisteredHead>
        <OrderList></OrderList>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '../home/Footer';
import OrderList from '../order/OrderList';
import RegisteredHead from '@/components/registered/RegisteredHead.vue';
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue';

export default {
    name: 'Order',

    components: {
        Footer,
        OrderList,
        RegisteredHead,
        LoginTopNavigation
    }
}
</script>

<style>

</style>