<template>
    <div>
        <LoginTopNavigation></LoginTopNavigation>
        <Header></Header>
        <GoodsArea></GoodsArea>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '../home/Footer';
import GoodsArea from '../goods/GoodsArea';
import Header from '../home/Header';
import LoginTopNavigation from "../home/LoginTopNavigation";

export default {
    name: 'Goods',

    components: {
        Footer,
        GoodsArea,
        Header,
        LoginTopNavigation
    }
}
</script>

<style>

</style>