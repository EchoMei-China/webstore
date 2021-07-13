<template>
    <div>
        <LoginTopNavigation></LoginTopNavigation>
        <GoodsList></GoodsList>
        <Footer></Footer>
    </div>
</template>

<script>
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue';
import Footer from '@/components/home/Footer.vue';
import GoodsList from '@/components/list/GoodsList.vue'

export default {
    name: 'List',
    components: {
        GoodsList,
        Footer,
        LoginTopNavigation
    }
}
</script>

<style>

</style>