<template>
    <div>
        <!-- <LoginTopNavigation></LoginTopNavigation> -->
        <RegisteredHead></RegisteredHead>
        <UserDetail></UserDetail>
        <Footer></Footer>
    </div>
</template>

<script>
import Footer from '../home/Footer';
import UserDetail from '../user/UserDetail';
import RegisteredHead from '../registered/RegisteredHead';
import LoginTopNavigation from '../home/LoginTopNavigation';

export default {
    name: 'User',

    components: {
        Footer,
        UserDetail,
        LoginTopNavigation,
        RegisteredHead
    }
}
</script>

<style>

</style>