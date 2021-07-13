<template>
    <div class="goods-area">
        <div class="goods-area-body">
            <ul>
                <li v-for="item in books" :key="item.bid">
                    <div class="goodsarea-body-picture">
                      <img :src="item.bimage" alt="" />
                    </div>
                    <div class="goodsarea-body-title">
                      <div class="goodsarea-body-title-name">
                        {{ item.bname }}
                      </div>
                      <div class="goodsarea-body-title-writer">{{ item.bwriter }}&nbsp;著</div>
                    </div>
                    <div class="goodsarea-body-price">
                      {{ item.bnowprice }}
                    </div>
                    <div class="goodsarea-body-btn" @click="ReceiveDetails(item.bid, item.bname, item.bwriter, item.bprice, item.bnowprice, item.bimage, item.tid, item.btranslate, item.bpublishing, item.bisbn, item.bsuit, item.tryreading);Addclicknum(item.bid)">立即购买</div>
                </li>
            </ul>
        </div>
        <el-pagination
            class="goodsarea-body-pagination"
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
import {ReceiveDetails,Addclicknum} from '@/assets/behaviors.js';

export default {
    name: 'GoodsArea',

    data() {
        return {
            books: [],
            pageSize: 12,
            currentPage: 1,
            total: 0,
            hide: false,
            fold: 5,
            searchInformation: null,
        }
    },

    created() {
        this.searchInformation = this.$route.query.searchInformation;
        let postData = this.qs.stringify({
            searchInformation: this.searchInformation
        })
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/book/obtainBooksBySearch',
            data: postData
        }).then((response) => {
            this.books = response.data.books
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获取总条目数
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/book/obtainTotalBooksBySearch',
            data: postData
        }).then(response => {
            this.total = response.data;
        }).catch(error => {
            console.log(error);
        })
    },

    methods: {
        ReceiveDetails,

        Addclicknum,

        _handlePageChange() {
        let postData = this.qs.stringify({
            pageSize: this.pageSize,
            startIndex: (this.currentPage - 1)*this.pageSize, 
            searchInformation: this.searchInformation
        });
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/book/obtainCurrentContentBySearch',
            data: postData
        }).then(response => {
            this.books = response.data.books;
        }).catch(error => {
            console.log(error);
        });
      }
    }
}
</script>

<style>
.goods-area {
    margin: 35px 0 150px 0;
}

.goods-area-body ul {
    display: flex;
    flex-wrap: wrap;
}

.goods-area-body ul li {
  position: relative;
  box-sizing: border-box;
  flex: 0 0 300px;
  margin-left: -1px;
  margin-top: 25px;
  height: 420px;
  border: 1px solid rgb(195, 212, 155);
}

.goodsarea-body-picture {
  margin: 5px auto;
  width: 280px;
  height: 280px;
}

.goodsarea-body-picture img {
  width: 100%;
  height: 100%;
}

.goodsarea-body-title {
  display: flex;
  margin-left: 8px;
  width: 280px;
  height: 30px;
  line-height: 30px;
  word-break: normal;
}

.goodsarea-body-title div {
  flex: 1;
}

.goodsarea-body-title .goodsarea-body-title-name {
  font-size: 18px;
  color: rgb(125, 148, 42);
}

.goodsarea-body-title .goodsarea-body-title-writer {
  color: rgb(93, 100, 71);
  text-align: center;
  font-size: 12px;
}

.goodsarea-body-price {
  margin-left: 8px;
  margin-top: 5px;
  width: 280px;
  height: 40px;
  color: rgb(86, 133, 34);
  font-size: 30px;
  line-height: 40px;
  /* background-color: blueviolet; */
}

.goodsarea-body-price::before {
  display: inline-block;
  content: "¥";
  font-size: 14px;
}

.goodsarea-body-btn {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 300px;
  height: 45px;
  color: rgb(195, 212, 155);
  background-color: rgb(67, 163, 47);
  text-align: center;
  line-height: 45px;
  font-size: 22px;
}

.goodsarea-body-btn:hover {
  color: rgb(67, 163, 47);
  background-color: rgb(195, 212, 155);
}

.goodsarea-body-pagination {
  margin-top: 30px;
  text-align: center;
}
</style>