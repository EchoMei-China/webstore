<template>
  <div class="spot">
    <div class="spot-title">
      <h3>少量现货</h3>
    </div>
    <ul class="spot-goods">
      <li class="goods" v-for="item in books" :key="item.bid">
        <a href="javascript:;" @click="ReceiveDetails(item.bid, item.bname, item.bwriter, item.bprice, item.bnowprice, item.bimage, item.tid, item.btranslate, item.bpublishing, item.bisbn, item.bsuit, item.tryreading);Addclicknum(item.bid)">
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
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import {ReceiveDetails,Addclicknum} from '@/assets/behaviors.js';

export default {
  name: "SmallSpot",

  data() {
    return {
      books: [],
    };
  },

  methods: {
    ReceiveDetails,
    Addclicknum
  },

  created() {
    let postData = this.qs.stringify({
      books: this.books,
    });
    this.axios({
      method: "post",
      url: "/ssm_project_war_exploded/book/obtainFiveSmallSpotBook",
      data: postData,
    })
      .then((response) => {
        this.books = response.data.books;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style>
.spot {
  margin-top: 40px;
  height: 408px;
  background-color: rgb(243, 248, 218);
}

.spot .spot-title {
  padding: 17px 20px;
  /* background-color: blueviolet; */
}

.spot-title h3 {
  height: 22px;
  line-height: 22px;
  color: rgb(151, 151, 73);
}

.spot-title h3::before {
  display: inline-block;
  margin-right: 12px;
  content: " ";
  width: 4px;
  height: 16px;
  background-color: rgb(151, 151, 73);
}

.spot-goods {
  display: flex;
}

.spot-goods .goods {
  flex: 1;
  margin-left: -1px;
  margin-top: -1px;
  height: 352px;
  border: 1px solid rgb(203, 235, 153);
}

.spot-goods .goods:hover {
  transform: translateY(-10px);
  transition: all 0.3s;
  border: 1px solid rgb(131, 180, 82);
  z-index: 1;
}

.goods .picture {
  margin: 20px auto 5px auto;
  width: 197px;
  height: 197px;
}

.picture img {
  width: 195px;
  height: 195px;
}

.goods .title {
  display: flex;
  flex-wrap: wrap;
  margin-left: 20px;
  width: 200px;
  height: 30px;
  line-height: 30px;
  /* background-color: pink; */
  /* white-space:nowrap; */
  /* overflow: hidden; */
  word-break: normal;
}

.title div {
  flex: 1;
}

.title .title-name {
  font-size: 18px;
  color: rgb(148, 179, 40);
}

.title .title-writer {
  color: rgb(139, 148, 112);
  text-align: center;
  font-size: 12px;
}

.goods .price {
  margin-left: 20px;
  margin-top: 40px;
  width: 200px;
  height: 40px;
  color: rgb(86, 133, 34);
  font-size: 30px;
  line-height: 40px;
}

.goods .price::before {
  display: inline-block;
  content: "¥";
  font-size: 14px;
}
</style>