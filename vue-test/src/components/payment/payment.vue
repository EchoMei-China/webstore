<template>
    <div class="payment">
        <LoginTopNavigation></LoginTopNavigation>
        <RegisteredHead></RegisteredHead>
        <PaymentArea></PaymentArea>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '../home/Footer';
import PaymentArea from '../payment/PaymentArea';
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue';
import RegisteredHead from '@/components/registered/RegisteredHead.vue';

export default {
    name: 'Payment',

    components: {
        Footer,
        PaymentArea,
        RegisteredHead,
        LoginTopNavigation
    }
}
</script>

<style>

</style>