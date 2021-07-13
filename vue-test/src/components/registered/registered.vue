<template>
    <div class="registered">
        <RegisteredHead></RegisteredHead>
        <RegisteredArea></RegisteredArea>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '@/components/home/Footer.vue';
import RegisteredHead from '@/components/registered/RegisteredHead.vue';
import RegisteredArea from '@/components/registered/RegisteredArea.vue'

export default {
    name: 'Registered',

    components: {
        Footer,
        RegisteredArea,
        RegisteredHead
    }
}
</script>

<style>

</style>