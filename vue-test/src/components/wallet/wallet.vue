<template>
    <div>
        <LoginTopNavigation></LoginTopNavigation>
        <WalletArea></WalletArea>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '../home/Footer';
import WalletArea from '../wallet/WalletArea'
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue';

export default {
    name: 'Wallet',

    components: {
        Footer,
        WalletArea,
        LoginTopNavigation
    }
}
</script>

<style>

</style>