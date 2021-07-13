<template>
    <div class="kindlist-body">
        <div class="kindlist-title">
            <h2> {{ BookKind }} </h2>
        </div>
        <div class="kindlist-body-goods">
            <ul>
                <li v-for="item in books" :key="item.bid">
                  <a href="javascript:;">
                    <div class="kindlist-body-picture">
                      <img :src="item.bimage" alt="" />
                    </div>
                    <div class="kindlist-body-title">
                      <div class="kindlist-body-title-name">
                        {{ item.bname }}
                      </div>
                      <div class="kindlist-body-title-writer">{{ item.bwriter }}&nbsp;著</div>
                    </div>
                    <div class="kindlist-body-price">
                      {{ item.bnowprice }}
                    </div>
                    <div class="kindlist-body-btn" @click="ReceiveDetails(item.bid, item.bname, item.bwriter, item.bprice, item.bnowprice, item.bimage, item.tid, item.btranslate, item.bpublishing, item.bisbn, item.bsuit, item.tryreading);Addclicknum(item.bid)">立即购买</div>
                  </a>
                </li>
            </ul>
        </div>
        <el-pagination
            class="kindlist-body-pagination"
            layout="prev, pager, next"
            :current-page.sync="currentPage"
            :pager-count="fold"
            :hide-on-single-page="hide"
            :page-size="pageSize"
            :total="total"
            @current-change="_handlePageChange()">
        </el-pagination>
    </div>
</template>

<script>
import qs from 'qs';
import axios from "axios";
import {ReceiveDetails,Addclicknum} from '@/assets/behaviors.js';

export default {
    name: 'KindListBody',

    methods: {
        ReceiveDetails,

        Addclicknum,

        _handlePageChange() {
            let postData = this.qs.stringify({
                pageSize: this.pageSize,
                startIndex: (this.currentPage - 1)*this.pageSize, 
                kid: this.kid
            });
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/book/obtainCurrentContent',
                data: postData
            }).then(response => {
                this.books = response.data.books;
            }).catch(error => {
                console.log(error);
            });
        }
    },

    data() {
        return {
            BookKind: '',
            books: [],
            pageSize: 12,
            currentPage: 1,
            total: 0,
            hide: true,
            fold: 5,
            kid: 0
        }
    },

    created() {
        /**
         * 获取kid
         */
        let kname = this.$route.query.kname;
        this.BookKind = kname;
        let postData1 = this.qs.stringify({
            kname: kname
        })
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/bookkind/obtainKidByKname',
            data: postData1
        }).then(response => {
            let kinds = response.data.kinds;
            this.kid = kinds.kid;
            /**
             * 获取书本
             */
            let postData2 = qs.stringify({
                kid: kinds.kid
            })
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/book/obtainBookByKid',
                data: postData2
            }).then(response => {
                this.books = response.data.books
            }).catch(error => {
                console.log(error);
            })

            /**
             * 获取总条目数
             */
            let postData3 = qs.stringify({
                kid: kinds.kid,
                pageSize: this.pageSize,
            })
            axios({
                method: 'post',
                url: '/ssm_project_war_exploded/book/obtainTotalBooks',
                data: postData3
            }).then(response => {
                this.total = response.data;
                console.log(response.data);
            }).catch(error => {
                console.log(error);
            })
        }).catch(error => {
            console.log(error);
        })
    }
}
</script>

<style scoped>
.kindlist-body {
    margin-top: 20px;
    background-color: rgb(227, 255, 234);
}

.kindlist-title {
    color: rgb(19, 53, 16);
    text-align: center;
    /* background-color: pink; */
}

.kindlist-title h2 {
    font-size: 24px;
}

.kindlist-title h2::before {
    display: inline-block;
    margin-right: 10px;
    content: "";
    font-family: "icomoon";
    font-size: 18px;
}

.kindlist-body-goods ul {
  margin-left: 2px;
  display: flex;
  flex-wrap: wrap;
}

.kindlist-body-goods ul li {
  position: relative;
  box-sizing: border-box;
  flex: 0 0 300px;
  margin-left: -1px;
  margin-top: 25px;
  height: 420px;
  border: 1px solid rgb(195, 212, 155);
}

.kindlist-body-picture {
  margin: 5px auto;
  width: 280px;
  height: 280px;
}

.kindlist-body-picture img {
  width: 100%;
  height: 100%;
}

.kindlist-body-title {
  display: flex;
  margin-left: 8px;
  width: 280px;
  height: 30px;
  line-height: 30px;
  word-break: normal;
}

.kindlist-body-title div {
  flex: 1;
}

.kindlist-body-title .kindlist-body-title-name {
  font-size: 18px;
  color: rgb(125, 148, 42);
  overflow: hidden;
}

.kindlist-body-title .kindlist-body-title-writer {
  color: rgb(93, 100, 71);
  text-align: center;
  font-size: 12px;
}

.kindlist-body-price {
  margin-left: 8px;
  margin-top: 5px;
  width: 280px;
  height: 40px;
  color: rgb(86, 133, 34);
  font-size: 30px;
  line-height: 40px;
  /* background-color: blueviolet; */
}

.kindlist-body-price::before {
  display: inline-block;
  content: "¥";
  font-size: 14px;
}

.kindlist-body-btn {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 300px;
  height: 45px;
  color: rgb(195, 212, 155);
  background-color: rgb(113, 168, 102);
  text-align: center;
  line-height: 45px;
  font-size: 22px;
}

.kindlist-body-btn:hover {
  color: rgb(113, 168, 102);
  background-color: rgb(195, 212, 155);
}

.kindlist-body-pagination {
  margin-top: 30px;
  text-align: center;
  /* background-color: pink; */
}
</style>