<template>
    <div class="header">
        <!-- logo -->
        <div class="header-logo">
            <h1>
                <a href="javascript:;" class="logo" title="SHOTEN欢迎您！" @click="_returnHome()">SHOTEN欢迎您！</a>
            </h1>
        </div>

        <!-- search -->
        <div class="header-search">
            <input v-model="searchInformation" type="text" class="search-text" placeholder="搜索 书名/作者/出版社">
            <button class="search-btn" @click="_search()">搜索</button>
        </div>

        <!-- right -->
        <div class="header-right"></div>
    </div>
</template>

<script>
export default {
    name: 'Header',

    data() {
        return {
            searchInformation: null
        }
    },

    mounted() {
        let that = this;
        let searchBox = document.querySelector(".search-text");
        searchBox.addEventListener('keyup', function(event) {
            if (event.keyCode == 13) {
                if (that.searchInformation == '' || that.searchInformation == null) {
                    that.$message.warning("请输入搜索关键字")
                } else {
                    let routeUrl = this.$router.resolve({
                      path: '/goods',
                      query: {
                        searchInformation: that.searchInformation
                      }
                    });
                    window.open(routeUrl.href, '_blank');
                }
            }
        })
    },

    methods: {
        /**
         * 按图标
         */
        _returnHome() {
            this.$router.push({ path: 'home' })
        },

        /**
         * 搜索
         */
        _search() {
            let that = this;
            if (this.searchInformation == '' || this.searchInformation == null) {
                this.$message.warning("请输入搜索关键字")
            } else {
                let routeUrl = this.$router.resolve({
                  path: '/goods',
                  query: {
                    searchInformation: that.searchInformation
                  }
                });
                window.open(routeUrl.href, '_blank');
            }
        }
    } 
}
</script>

<style>
    .header {
        display: flex;
        height: 105px;
    }

    .header-logo {
        width: 300px;
        height: 105px;
    }

    .header-logo .logo {
        display: block;
        margin: 0 auto;
        width:105px;
        height: 105px;
        background-image: url("../../assets/img/logo.png");
        background-size: 105px 105px;
        overflow: hidden;
        text-indent: -999px;
    }

    .header-search {
        display: flex;
        flex: 1;
    }

    .header-search .search-text {
        margin: 34px 0;
        width: 445px;
        height: 32px;
        border: 2px solid rgb(68, 170, 68);
        padding-left: 10px;
        border-radius: 15px 0 0 15px;
    }

    .header-search .search-btn {
        margin: 34px 0;
        width: 82px;
        height: 36px;
        border: 0;
        font-size: 16px;
        color: white;
        background-color: rgb(68, 170, 68);
        border-radius: 0 15px 15px 0;
    }

    .header-right {
        width: 300px;
        height: 105px;
    }

</style>