<template>
    <div class="footer">
        <div class="service">
            <div>正品保障</div>
            <div>七天退换</div>
            <div>物流迅速</div>
            <div>贴心服务</div>
        </div>
        <div class="modlinks">
            <ul>
                <li class="li1"><a href="#" target = _blank>关于我们</a></li>
                <li><a href="#" target= _blank>联系我们</a></li>
                <li><a href="#" target= _blank>联系客服</a></li>
                <li><a href="#" target= _blank>错误报修</a></li>
                <li><a href="#" target= _blank>加入我们</a></li>
                <li class="li6"><a href="/#/login" target = _blank>管理员登录</a></li>
            </ul>
        </div>
        <div class="copyright">
            地址：江苏省南京市江宁区淳化街道弘景大道3601 邮编：210000 电话：+86-18151568916 邮箱：1595918301@qq.com<br>
            苏ICP备 08888888号 苏公网安备 110888888888
        </div>
    </div>
</template>

<script>
export default {
    name: 'Footer'
}
</script>

<style>
.footer {
    /* position: absolute;
    left: 0;
    bottom: 0; */
    margin-top: 30px;
    padding-top: 30px;
    width: 100%;
    height: 256px;
    background-color: rgb(207, 243, 200);
}

.footer .service {
    display: flex;
    height: 78px;
    border-bottom: 2px solid rgb(241, 252, 240);
}

.footer .service div {
    flex: 1;
    color: rgb(7, 43, 7);
    font-size: 18px;
    text-align: center;
    line-height: 79px;
}

.footer .service div:nth-child(1):before {
    display: inline-block;
    margin-right: 10px;
    content: '';
    color: rgb(68, 170, 68);
    font-family: 'icomoon';
    font-size: 30px;
}

.footer .service div:nth-child(2):before {
    display: inline-block;
    margin-right: 10px;
    content: '';
    color: rgb(68, 170, 68);
    font-family: 'icomoon';
    font-size: 30px;
}

.footer .service div:nth-child(3):before {
    display: inline-block;
    margin-right: 10px;
    content: '';
    color: rgb(68, 170, 68);
    font-family: 'icomoon';
    font-size: 30px;
}

.footer .service div:nth-child(4):before {
    display: inline-block;
    margin-right: 10px;
    content: '';
    color: rgb(68, 170, 68);
    font-family: 'icomoon';
    font-size: 30px;
}

.footer .modlinks {
    margin-top: 40px;
    height: 30px;
    /* background-color: pink; */
}

.footer .modlinks ul {
    display: flex;
}

.footer .modlinks ul li {
    flex: 1;
    border-left: 1px solid rgb(241, 252, 240);
    border-right: 1px solid rgb(241, 252, 240);
    font-size: 14px;
    text-align: center;
    line-height: 30px;
}

.footer .modlinks ul .li1 {
    border-left: none;
}

.footer .modlinks ul .li6 {
    border-right: none;
}

.footer .copyright {
    margin-top: 25px;
    font-size: 12px;
    text-align: center;
    /* background-color: pink; */

}

</style>