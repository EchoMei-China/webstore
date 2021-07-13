<template>
    <div>
        <TopNavigation>
                <div v-if="user != null">
                    <div class="top-left">
                        <div class="shoten">SHOTEN~欢迎选购</div>
                        <div class="logintop-div">
                             <div>
                                {{ user }}
                            </div>
                            <div>
                                <a href="javascript:;" @click="_clearSession()">
                                    退出登录
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="top-right">
                        <div><a href="javascript:;" @click="_order(user)">我的订单</a></div>
                        <div><a href="javascript:;" @click="_shoppingcart(user)">我的购物车</a></div>
                        <div><a href="javascript:;" @click="_wallet(user)">我的钱包</a></div>
                        <div><a href="javascript:;" @click="_address(user)">我的地址</a></div>
                        <div><a href="javascript:;" @click="_user(userId)">我的主页</a></div>
                    </div>
                </div>
        </TopNavigation>
    </div>
</template>

<script>
import TopNavigation from '@/components/home/TopNavigation.vue';

export default {
    name: 'LoginTopNavigation',

    inject:['reload'], //调用刷新

    components: {
        TopNavigation
    },

    data() {
        return {
            user: null,
            userId: null
        }
    },

    created() {
        this.userId = sessionStorage.getItem('userId');
        let postData6 = this.qs.stringify({
          user_id: this.userId
        })
        this.axios({
          method: 'post',
          url: '/ssm_project_war_exploded/user/obtainUserById',
          data: postData6
        }).then((response) => {
          let nickname = response.data.user.userNickname;
          sessionStorage.setItem("userNickName", nickname);
        }).catch((error) => {
          console.log(error);
        });
    },

    mounted() {
        this.user = sessionStorage.getItem('userNickName');
    },

    methods: {
        /**
         * 退出登录
         */
        _clearSession() {
            sessionStorage.removeItem('userNickName');
            sessionStorage.removeItem('userId');
            this.user = null;
            this.userId = null;
            this.reload();
        },

        /**
         * 登陆后点击’购物车‘按钮
         */
        _shoppingcart(val) {
            let routeUrl = this.$router.resolve({
                path: '/shoppingcart',
                query: {
                    user_nickname: val
                }
            });
            window.open(routeUrl.href, '_blank');
        },

        /**
         * 登录后点击’我的地址‘按钮
         */
        _address(val) {
            let routeUrl = this.$router.resolve({
                path: '/address',
                query: {
                    user_nickname: val
                }
            });
            window.open(routeUrl.href, '_blank');
        },

        /**
         * 登陆后点击‘我的钱包’按钮
         */
        _wallet(val) {
            let routeUrl = this.$router.resolve({
                path: '/wallet',
                query: {
                    user_nickname: val
                }
            });
            window.open(routeUrl.href, '_blank');
        },

        /**
         * 点击后登陆‘我的订单’按钮
         */
        _order(val) {
            let routeUrl = this.$router.resolve({
                path: '/order',
                query: {
                    user_nickname: val
                }
            });
            window.open(routeUrl.href, '_blank');
        },

        /**
         * 我的主页按钮
         */
        _user(val) {
            let routeUrl = this.$router.resolve({
                path: '/user',
                query: {
                    user_id: val
                }
            });
            window.open(routeUrl.href, '_blank'); 
        }
    }
}
</script>

<style>
.logintop-div {
    display: flex;
}
</style>