<template>
    <div>
        <LoginTopNavigation></LoginTopNavigation>
        <Header></Header>
        <DetailHead></DetailHead>
        <DetailContent></DetailContent>
        <Footer></Footer>
    </div>
</template>

<script>
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue';
import Header from '@/components/home/Header.vue';
import Footer from '@/components/home/Footer.vue';
import DetailHead from '@/components/details/DetailHead.vue';
import DetailContent from '@/components/details/DetailContent.vue';

export default {
    name: 'Details',

    data() {
        return {
            user: null
        }
    },

    components: {
        DetailContent,
        DetailHead,
        Footer,
        Header,
        LoginTopNavigation
    },

    mounted() {
        this.user = sessionStorage.getItem('userNickName');
    }
}
</script>

<style>

</style>