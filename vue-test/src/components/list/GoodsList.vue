<template>
  <div class="goodslist">
    <div class="goodstitle">
      <h2>{{ GoodsTitle }}</h2>
    </div>
    <div class="goods">
      <ul>
        <li v-for="item in books" :key="item.bid">
          <a href="javascript:;">
            <div class="picture">
              <img :src="item.bimage" alt="" />
            </div>
            <div class="title">
              <div class="title-name">
                {{ item.bname }}
              </div>
              <div class="title-writer">{{ item.bwriter }}&nbsp;著</div>
            </div>
            <div class="price">
              {{ item.bnowprice }}
            </div>
            <div class="btn" @click="ReceiveDetails(item.bid, item.bname, item.bwriter, item.bprice, item.bnowprice, item.bimage, item.tid, item.btranslate, item.bpublishing, item.bisbn, item.bsuit, item.tryreading);Addclicknum(item.bid)">立即购买</div>
          </a>
        </li>
      </ul>
    </div>
    <el-pagination
        class="goodslist-body-pagination"
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
  name: "GoodsList",

  data() {
    return {
      GoodsTitle: "",
      btid: "", //booktype里的kid
      books: [],
      pageSize: 12,
      currentPage: 1,
      total: 0,
      hide: false,
      fold: 5,
      tid: null
    };
  },

  methods: {
    ReceiveDetails,

    Addclicknum,

    _handlePageChange() {
        let postData = this.qs.stringify({
            pageSize: this.pageSize,
            startIndex: (this.currentPage - 1)*this.pageSize, 
            tid: this.tid
        });
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/book/obtainCurrentContentByTid',
            data: postData
        }).then(response => {
            this.books = response.data.books;
        }).catch(error => {
            console.log(error);
        });
    }
  },

  created() {
    this.tid = this.$route.query.tid;
    this.GoodsTitle = this.$route.query.tname;
    let postData = this.qs.stringify({
      books: this.books,
      btid: this.tid,
    });
    /**
     * 获取书本
     */
    this.axios({
      method: "post",
      url: "/ssm_project_war_exploded/book/obtainBookList",
      data: postData,
    })
    .then((response) => {
      this.books = response.data.books;
    })
    .catch((error) => {
      console.log(error);
    });

    /**
     * 获取总书数
     */
    let postData3 = this.qs.stringify({
        btid: this.tid,
        pageSize: this.pageSize,
    })
    this.axios({
        method: 'post',
        url: '/ssm_project_war_exploded/book/obtainTotalBooksByTid',
        data: postData3
    }).then(response => {
        this.total = response.data;
        console.log(response.data);
    }).catch(error => {
        console.log(error);
    })
  },
};
</script>

<style>
.goodslist {
  margin-top: 20px;
  background-color: rgb(238, 248, 227);
}

.goodstitle {
  color: rgb(19, 53, 16);
  text-align: center;
  /* background-color: pink; */
}

.goodstitle h2 {
  font-size: 24px;
}

.goodstitle h2::before {
  display: inline-block;
  margin-right: 10px;
  content: "";
  font-family: "icomoon";
  font-size: 18px;
}

.goods ul {
  margin-left: 2px;
  display: flex;
  flex-wrap: wrap;
}

.goods ul li {
  position: relative;
  box-sizing: border-box;
  flex: 0 0 300px;
  margin-left: -1px;
  margin-top: 25px;
  height: 420px;
  border: 1px solid rgb(195, 212, 155);
}

.picture {
  margin: 5px auto;
  width: 280px;
  height: 280px;
}

.picture img {
  width: 100%;
  height: 100%;
}

.title {
  display: flex;
  margin-left: 8px;
  width: 280px;
  height: 30px;
  line-height: 30px;
  word-break: normal;
}

.title div {
  flex: 1;
}

.title .title-name {
  font-size: 18px;
  color: rgb(125, 148, 42);
}

.title .title-writer {
  color: rgb(93, 100, 71);
  text-align: center;
  font-size: 12px;
}

.price {
  margin-left: 8px;
  margin-top: 5px;
  width: 280px;
  height: 40px;
  color: rgb(86, 133, 34);
  font-size: 30px;
  line-height: 40px;
  /* background-color: blueviolet; */
}

.price::before {
  display: inline-block;
  content: "¥";
  font-size: 14px;
}

.btn {
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

.btn:hover {
  color: rgb(67, 163, 47);
  background-color: rgb(195, 212, 155);
}

.pagination {
  margin: 40px auto;
  width: 508px;
  /* background-color: pink; */
}

.goodslist-body-pagination {
  margin-top: 30px;
  text-align: center;
}
</style>