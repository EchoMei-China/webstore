<template>
    <div>
        <div class="admin-title">
            <div> {{username}} </div>
            <div style="cursor: pointer" @click="_signOut()">退出登陆</div>
        </div>
        <RegisteredHead></RegisteredHead>
        <div class="admin-body">
            <div class="admin-body-left">
                <div class="adminbody-left-item adminbody-left-item-current">订单管理</div>
                <div class="adminbody-left-item">图书管理</div>
                <div class="adminbody-left-item">运费管理</div>
                <div class="adminbody-left-item">用户管理</div>
                <div class="adminbody-left-item">管理员管理</div>
                <div class="adminbody-left-item">作者管理</div>
            </div>
            <div class="admin-body-right">
                <OrderManagement class="admin-body-right-item" style="display: block"></OrderManagement>
                <BookManagement class="admin-body-right-item"></BookManagement>
                <ShippingManagement class="admin-body-right-item"></ShippingManagement>
                <UserHome class="admin-body-right-item"></UserHome>
                <AdminManagement class="admin-body-right-item"></AdminManagement>
                <WriterManagement class="admin-body-right-item"></WriterManagement>
            </div>
        </div>
    </div>
</template>

<script>
import WriterManagement from '../admin/WriterManagement';
import AdminManagement from '../admin/AdminManagement';
import ShippingManagement from '../admin/ShippingManagement';
import BookManagement from '../admin/BookManagement';
import OrderManagement from '../admin/OrderManagement';
import UserHome from '../UserHome';
import RegisteredHead from '../registered/RegisteredHead';

export default {
    name: 'Admin',

    components: {
        WriterManagement,
        AdminManagement,
        ShippingManagement,
        BookManagement,
        OrderManagement,
        UserHome,
        RegisteredHead
    },

    data() {
        return {
            username: null
        }
    },

    created() {
        this.username = this.$route.query.username;
    },

    mounted() {
        let tabs = document.querySelectorAll(".adminbody-left-item");
        let tabbodys = document.querySelectorAll(".admin-body-right-item");
        for (var i=0; i<tabs.length; i++) {
            tabs[i].setAttribute('index', i);
            tabs[i].addEventListener("click", function() {
                for (var i=0; i<tabs.length; i++) {
                    tabs[i].className = "adminbody-left-item"
                }
                this.className = "adminbody-left-item adminbody-left-item-current";
                let index = this.getAttribute('index');
                for (var i=0; i<tabbodys.length; i++) {
                    tabbodys[i].style.display = 'none'
                }
                tabbodys[index].style.display = 'block'
            })
        }
    },

    methods: {
        /**
         * 退出登陆
         */
        _signOut() {
            this.$router.push({ path: '/home' });
        }
    }
}
</script>

<style>
.admin-title {
    display: flex;
    height: 30px;
    /* font-size: 16px; */
    font-weight: 500;
    color: white;
    background-color: green;
}

.admin-title div {
    margin: 0 0 0 30px;
    line-height: 30px;
}

.admin-body {
    display: flex;
    margin-top: 30px;
}

.admin-body-left {
    width: 200px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
}

.adminbody-left-item {
    margin: 0 0 10px 0;
    height: 40px;
    line-height: 40px;
    color: white;
    background-color: green;
    /* border-bottom: 1px solid white; */
    cursor: pointer;
    border-radius: 8px;
}

.adminbody-left-item:hover {
    color: green;
    background-color: white;
}

.adminbody-left-item-current {
    color: green;
    background-color: white;
    /* border: 1px solid green; */
}

.admin-body-right {
    margin-left: 30px;
}

.admin-body-right-item {
    display: none;
}
</style>