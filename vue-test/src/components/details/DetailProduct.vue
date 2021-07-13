<template>
    <!-- 详细信息部分 -->
    <div class="detail-product">
        <div class="aside">
            <div class="aside-tablist">
                <ul>
                    <li class="current">最热推荐</li>
                    <li>最多收藏</li>
                </ul>
            </div>
            <div class="aside-tabcon">
                <div class="tabcon-content">
                    <ul>
                        <li v-for="item in clickbooks" :key="item.bid">
                            <a href="javascript:;" @click="ReceiveDetails(item.bid, item.bname, item.bwriter, item.bprice, item.bnowprice, item.bimage, item.tid, item.btranslate, item.bpublishing, item.bisbn, item.bsuit, item.tryreading);Addclicknum(item.bid)">
                                <div class="picture">
                                    <img :src="item.bimage" alt="">
                                </div>
                                <div class="title">
                                    <div class="name"> {{ item.bname }} 【正品保证】</div>
                                    <div class="nowprice"> {{ item.bnowprice }}</div>
                                    <div class="nowclicknum">
                                        <p>受欢迎&nbsp;</p>
                                        <p>{{ item.clicknum }}</p>
                                        <p>&nbsp;次</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="tabcon-content" style="display:none">
                    <ul>
                        <li v-for="item in favoritesbooks" :key="item.bid">
                            <a href="javascript:;" @click="ReceiveDetails(item.bid, item.bname, item.bwriter, item.bprice, item.bnowprice, item.bimage, item.tid, item.btranslate, item.bpublishing, item.bisbn, item.bsuit, item.tryreading);Addclicknum(item.bid)">
                                <div class="picture">
                                    <img :src="item.bimage" alt="">
                                </div>
                                <div class="title">
                                    <div class="name"> {{ item.bname }} 【正品保证】</div>
                                    <div class="nowprice"> {{ item.bnowprice }}</div>
                                    <div class="nowclicknum">
                                        <p>被收藏&nbsp;</p>
                                        <p>{{ item.favorites }}</p>
                                        <p>&nbsp;次</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tabaside">
            <div class="tabaside-tablist">
                <ul>
                    <li class="current">基本信息</li>
                    <li>内容试读</li>
                    <li>作者简介</li>
                    <li>查看评价</li>
                </ul>
            </div>
            <div class="tabaside-tabcon">
                <div class="tabaside-tabcon-item" style="display: block">
                    <div class="basic-title">基本信息</div>
                    <div class="product-row">
                        <div class="product-title">书名:&nbsp;&nbsp;</div>
                        <div class="product-information"> {{shoppingcart.bookname}} </div>
                    </div>
                    <div class="product-row">
                        <div class="product-title">作者:&nbsp;&nbsp;</div>
                        <div class="product-information"> {{shoppingcart.bookwriter}} </div>
                    </div>
                    <div class="product-row">
                        <div class="product-title">译者:&nbsp;&nbsp;</div>
                        <div class="product-information"> {{shoppingcart.booktranslate}} </div>
                    </div>
                    <div class="product-row">
                        <div class="product-title">出版社:&nbsp;&nbsp;</div>
                        <div class="product-information"> {{ shoppingcart.bookpublishing }}</div>
                    </div>
                    <div class="product-row">
                        <div class="product-title">书号:&nbsp;&nbsp;</div>
                        <div class="product-information"> {{ shoppingcart.bookisbn }} </div>
                    </div>
                    <div class="product-row">
                        <div class="product-title">是否套装:&nbsp;&nbsp;</div>
                        <div class="product-information"> {{ shoppingcart.booksuit }} </div>
                    </div>
                    <div class="product-row">
                        <div class="product-title">定价:&nbsp;&nbsp;</div>
                        <div class="product-information"> {{ shoppingcart.bookprice }} 元</div>
                    </div>
                    <div class="product-row">
                        <div class="product-title">现价:&nbsp;&nbsp;</div>
                        <div class="product-information"> {{ shoppingcart.booknowprice }} 元</div>
                    </div>
                </div>
                <div class="tabaside-tabcon-item">
                    <div class="basic-title">内容试读</div>
                    <div class="brief-introduction">{{text}}</div>
                </div>
                <div class="tabaside-tabcon-item">
                    <div class="basic-title">作者简介</div>
                    <div class="basic-introduction"> {{writer.bwriter}} </div>
                    <div class="basic-introduction"> {{ writer.wintroduction }} </div>
                    <div class="basic-introduction"> 代表作{{ writer.wmasterpiece}} </div>
                </div>
                <div class="tabaside-tabcon-item">
                    <div class="basic-title">评论详情</div>
                    <div class="commentul">
                        <ul>
                            <li v-for="item in comments" :key="item.cid">
                                <div class="commentcontent"> {{item.ccontent}} </div>
                                <div class="commenttime"> {{ item.ctime }} </div>
                            </li>
                        </ul>
                    </div>
                    <el-pagination
                        class="pagination"
                        layout="prev, pager, next"
                        :current-page.sync="currentPage"
                        :pager-count="fold"
                        :hide-on-single-page="hide"
                        :page-size="pageSize"
                        :total="total"
                        @current-change="_handlePageChange()">
                    </el-pagination>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {ReceiveDetails, Addclicknum} from '@/assets/behaviors.js';

export default {
    name: 'shoppingcart',

    props: {
        shoppingcart: {
            bookid: Number,
            bookname: String,
            bookwriter: String,
            booktranslate: String,
            bookpublishing: String,
            bookisbn: String,
            booksuit: String,
            bookprice: String,
            booknowprice: String
        }
    },

    data() {
        return {
            clickbooks: [],
            favoritesbooks: [],
            text: '',
            writer: [],
            comments: [],
            pageSize: 10,
            currentPage: 1,
            total: 0,
            hide: true,
            fold: 5

        }
    },

    methods: {
        ReceiveDetails,

        Addclicknum,

        /**
         * 评论详情分页
         */
        _handlePageChange() {
            // alert(this.currentPage);
            let postData = this.qs.stringify({
                    pageSize: this.pageSize,
                    startIndex: (this.currentPage - 1)*this.pageSize, 
                    cbid: this.shoppingcart.bookid
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/comment/obtainCurrentContent',
                data: postData
            }).then(response => {
                this.comments = response.data.comments;
            }).catch(error => {
                console.log(error);
            });
        }
    },

    created() {
        /**
         * 获取最热推荐
         */
        let tryreading = this.$route.query.tryreading;
        console.log(tryreading);
        let postData1 = this.qs.stringify({
            clickbooks: this.clickbooks,
        });
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/book/obtainFivePopularBook',
            data: postData1
        }).then((response) => {
            this.clickbooks = response.data.books;
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获取最多收藏
         */
        let postData2 = this.qs.stringify({
            favoritesbooks: this.favoritesbooks,
        });
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/book/obtainFiveFavoritesBook',
            data: postData2
        }).then((response) => {
            this.favoritesbooks = response.data.books;
            console.log(this.favoritesbooks);
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获取试阅读
         */
        let postData3 = this.qs.stringify({
            http: tryreading,
        });
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/WebCrawler/getTrialReading1',
            data: postData3,
        }).then((response) => {
            this.text = response.data.text;
        }).catch((error) => {
            console.log(error);
            console.log(22222222222);
        });

        /**
         * 获取评论
         */
        let postData5 = this.qs.stringify({
            comments: this.comments,
            cbid: this.shoppingcart.bookid
        });
        console.log(postData5);
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/comment/botainCommentByBid',
            data: postData5
        }).then((response) => {
            this.comments = response.data.comments;
        }).catch((error) => {
            console.log(error);
        })

        /**
         * 获取总条目数
         */
        let postData6 = this.qs.stringify({
            pageSize: this.pageSize,
            total: this.total,
            cbid: this.shoppingcart.bookid,
        });
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/comment/botainTotal',
            data: postData6
        }).then((response) => {
            this.total = response.data
            console.log(this.total);
        }).catch((error) => {
            console.log(error);
        })

        /**
         * 获取作者简介
         */
        let postData4 = this.qs.stringify({
            writer: this.writer,
            wname: this.shoppingcart.bookwriter
        });
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/writer/botainAboutAuthor',
            data: postData4
        }).then((response) => {
            this.writer = response.data.writer;
            if (this.writer == null) {
                this.writer = [
                    {
                        bwriter: '',
                        wintroduction: '',
                        wmasterpiece: ''
                    }
                ]
            }
        }).catch((error) => {
            console.log(error);
        })

    },

    mounted() {
        /**
         *  左侧tab栏切换
         */
        let asideTablist = document.querySelector('.aside-tablist');
        let lis = asideTablist.querySelectorAll('li');
        let tabconContents = document.querySelectorAll('.tabcon-content');
        
        for (var i=0; i<lis.length; i++) {
            lis[i].setAttribute('index', i);
            lis[i].addEventListener('click', function(){
                for (var i=0; i<lis.length; i++) {
                    lis[i].className='';
                }
                this.className = 'current';
                let index = this.getAttribute('index');
                for (var i=0; i<tabconContents.length; i++) {
                    tabconContents[i].style.display = 'none';
                }
                tabconContents[index].style.display='block'
            })
        }

        /**
         * 右侧上方tab切换
         */
        let tabasideTablist = document.querySelector('.tabaside-tablist');
        let lis2 = tabasideTablist.querySelectorAll('li');
        let tabasideTabconItems = document.querySelectorAll('.tabaside-tabcon-item');

        for (var i=0; i<lis2.length; i++) {
            lis2[i].setAttribute('index', i);
            lis2[i].addEventListener('click', function() {
                for (var i=0; i<lis2.length; i++) {
                    lis2[i].className = '';
                }
                this.className = 'current';
                let index = this.getAttribute('index');
                for (var i=0; i<tabasideTabconItems.length; i++) {
                    tabasideTabconItems[i].style.display = 'none';
                }
                tabasideTabconItems[index].style.display = 'block';
            })
        }
    }



}
</script>

<style>
.detail-product {
    display: flex;
    /* background-color: pink; */
}

.aside {
    width: 208px;
    height: 438px;
    border: 1px solid rgb(177, 193, 169);
}

.tabaside {
    margin-left: 10px;
    width: 978px;
    /* border: 1px solid rgb(177, 193, 169); */
}

.aside-tablist {
    height: 34px;
    /* background-color: rgb(177, 193, 169); */
}

.aside-tablist ul {
    display: flex;
}

.aside-tablist ul li {
    height: 34px;
    font-size: 14px;
    text-align: center;
    line-height: 34px;
    border-bottom: 1px solid rgb(177, 193, 169);
    background-color: rgb(225, 250, 215);
    cursor: pointer;
}

.aside-tablist ul li:nth-child(1) {
    border-right: 1px solid rgb(177, 193, 169);
}

.aside-tablist ul .current {
    color: rgb(65, 150, 23);
    background-color: white;
    border-bottom: none;
}

.tabcon-content ul {
    display: flex;
    flex-direction: column;
}

.tabcon-content ul li {
    /* height: 180px; */
    flex: 0 0 80px;
    /* background-color: rgb(240, 245, 237); */
    border-bottom: 1px solid rgb(109, 194, 53);
}

.tabcon-content a {
    display: flex;
}

.tabcon-content .picture {
    margin: 18px 10px;
    width: 42px;
    height: 42px;
    border: 1px solid #ccc;
}

.tabcon-content .picture img {
    width: 42px;
    height: 42px;
}

.tabcon-content .title {
    margin: 8px 0;
    width: 130px;
    height: 68px;
    /* background-color: pink; */
}

.title .name,
.title .nowprice,
.title .nowclicknum {
    height: 20px;
    font-size: 14px;
    font-weight: 500;
    text-align: left;
    line-height: 20px;
    /* background-color: antiquewhite; */
    overflow: hidden;
}

.title .nowprice {
    margin: 3px 0;
    color: rgb(207, 63, 63);
}

.title .nowprice::before {
    display: inline-block;
    content: '¥';
    font-size: 14px;
}

.title .nowclicknum {
    display: flex;
}

.nowclicknum p:nth-child(2) {
    font-size: 13px;
    font-weight: 800;
    overflow: hidden;
}

.tabaside-tablist {
    height: 38px;
    border: 1px solid #ccc;
    background-color: rgb(246, 252, 237);
}

.tabaside-tablist ul {
    display: flex;
}

.tabaside-tablist ul li {
    /* width: 200px; */
    flex: 0 0 200px;
    height: 38px;
    text-align: center;
    line-height: 38px;
    cursor: pointer;
}

.tabaside-tablist ul .current {
    color: white;
    background-color: rgb(81, 148, 58);
}

/* .tabaside-tabcon {
} */

.tabaside-tabcon-item {
    display: none;
}

.product-row {
    display: flex;
    margin: 10px 0 0 10px;
    height: 30px;
    line-height: 30px;
    /* background-color: pink; */
}

.basic-title {
    font-size: 16px;
    color: rgb(6, 24, 1);
}

.product-title {
    color: rgb(23, 61, 19);
    font-size: 15px;
}

.product-information {
    color: black;
    font-size: 13px;
}

.brief-introduction {
    margin-top: 15px;
    font-size: 14px;
}

.basic-introduction {
    margin: 15px 0 0 15px;
    /* height: 30px; */
    line-height: 30px;
    word-break: normal;
    font-size: 14px;
    color: black;
    /* background-color: pink; */
}

.commentul ul {
    display: flex;
    flex-direction: column;
}

.commentul ul li {
    margin-top: 20px;
    border-bottom: 1px solid #ccc;
    /* background-color: pink; */
}

.commentcontent {
    text-indent: 2em;
    text-align: left;
    word-break: normal;
    font-size: 15px;
}

.commenttime {
    text-align: left;
    font-size: 11px;
    color: rgb(181, 181, 181);
}

.pagination {
    margin-top: 30px;
    text-align: center;
    /* background-color: pink; */
}
</style>