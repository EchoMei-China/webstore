<template>
    <div class="shoppingcart">
        <LoginTopNavigation></LoginTopNavigation>
        <RegisteredHead></RegisteredHead>
        <ShoppingcartArea></ShoppingcartArea>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '../home/Footer';
import ShoppingcartArea from '@/components/shoppingcart/ShoppingcartArea.vue';
import RegisteredHead from '@/components/registered/RegisteredHead.vue';
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue';

export default {
    name: 'ShoppingCart',

    components: {
        Footer,
        ShoppingcartArea,
        RegisteredHead,
        LoginTopNavigation
    }
}
</script>

<style>

</style>