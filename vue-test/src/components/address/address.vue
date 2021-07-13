<template>
    <div>
        <LoginTopNavigation></LoginTopNavigation>
        <AddAddress v-bind:totalAddress="totalAddress"></AddAddress>
        <ShowAddress @totalAddress="getValue($event)"></ShowAddress>
        <Footer></Footer>
    </div>
</template>

<script>
import LoginTopNavigation from '@/components/home/LoginTopNavigation.vue';
import AddAddress from '@/components/address/AddAddress.vue'
import ShowAddress from '@/components/address/ShowAddress.vue';
import Footer from '@/components/home/Footer.vue';

export default {
    name: 'Address',

    components: {
        Footer,
        ShowAddress,
        AddAddress,
        LoginTopNavigation
    },

    data() {
        return {
            totalAddress: null
        }
    },

    methods: {
        getValue(data) {
            this.totalAddress = data;
        }
    }
}
</script>

<style>

</style>