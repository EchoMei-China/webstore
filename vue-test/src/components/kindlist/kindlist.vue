<template>
    <div class="kindlist">
        <LoginTopNavigation></LoginTopNavigation>
        <KindListBody></KindListBody>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '@/components/home/Footer.vue'
import KindListBody from './KindListBody.vue';
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue'

export default {
    name: 'KindList',

    components: {
        Footer,
        KindListBody,
        LoginTopNavigation
    }
}
</script>

<style scoped>

</style>