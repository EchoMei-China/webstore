<template>
    <div class="registered-head">
        <!-- logo -->
        <div class="head-logo">
            <h1>
                <a href="/#/home" class="logoo" title="SHOTEN欢迎您！">SHOTEN欢迎您！</a>
            </h1>
        </div>
    </div>
</template>

<script>
export default {
    name: 'RegisteredHead'
}
</script>

<style>
.registered-head {
    /* background-color: pink; */
    border-bottom: 2px solid green;
}

.head-logo {
    width: 300px;
    height: 105px;
}

.head-logo .logoo {
        display: block;
        width:105px;
        height: 105px;
        background-image: url("../../assets/img/logo.png");
        background-size: 105px 105px;
        overflow: hidden;
        text-indent: -999px;
    }
</style>