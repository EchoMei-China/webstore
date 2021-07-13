<template>
    <div class="middle-nav">
        <DropDown></DropDown>
        <div class="nav-row">
            <ul>
                <li class="a1"><a href="javascript:;">最热图书</a></li>
                <li class="a2"><a href="javascript:;">最热作家</a></li>
                <li class="a3"><a href="javascript:;">最热标签</a></li>
                <li class="a4"><a href="javascript:;">最新上架</a></li>
            </ul>
        </div>
    </div>
</template>

<script>
import DropDown from "./DropDown.vue";

export default {
    name: 'MiddleNav',
    components: {
        DropDown,
    }
}
</script>

<style>
    .middle-nav {
        display: flex;
        height: 45px;
        border-bottom: 2px solid rgb(207, 243, 200);
    }

    .middle-nav .nav-row ul {
        display: flex;
    }

    .nav-row ul li {
        margin-left: 50px;
        width: 100px;
        height: 45px;
        font-size: 16px;
        text-align: center;
        line-height: 55px;
        /* background-color: pink; */
    }

    .middle-nav .nav-row ul .a1 a {
        color: rgb(102, 196, 247);
    }

    .middle-nav .nav-row ul .a2 a {
        color: rgb(136, 95, 248);
    }

    .middle-nav .nav-row ul .a3 a {
        color: rgb(132, 136, 252);
    }
    
    .middle-nav .nav-row ul .a4 a {
        color: rgb(189, 123, 255);
    }

</style>