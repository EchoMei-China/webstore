<template>
  <div class="popular">
    <div class="popular-title">
      <h3>热书推荐</h3>
    </div>
    <ul class="popular-goods">
      <li class="goods" v-for="item in books" :key="item.bid">
        <a href="javascript:;" @click="ReceiveDetails(item.bid, item.bname, item.bwriter, item.bprice, item.bnowprice, item.bimage, item.tid, item.btranslate, item.bpublishing, item.bisbn, item.bsuit, item.tryreading);Addclicknum(item.bid)">
          <div class="picture">
            <img :src="item.bimage" alt=""/>
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
import {ReceiveDetails, Addclicknum} from '@/assets/behaviors.js';

export default {
  name: "PopularRecommendation",

  data() {
    return {
    //   test: require(images),
      books: [],
    //   imageUrl: require('../../../static/'),
      images: [],
      imageUrl: '',
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
      url: "/ssm_project_war_exploded/book/obtainFivePopularBook",
      data: postData,
    })
      .then((response) => {
        this.books = response.data.books;
        // const {bimage} = this.books
        console.log(this.books);
        console.log(11111111111111111111111);
        // console.log(this.books.length);
        // for (var i=0;i<this.books.length;i++) {
        //     this.images[i]=this.books[i].bimage
        // }
        // console.log(this.images);
      })
      .catch((error) => {
        console.log(error);
        // console.log("1222212222");
      });
  },
};
</script>

<style>
.popular {
  margin-top: 50px;
  height: 408px;
  background-color: rgb(245, 250, 232);
}

.popular .popular-title {
  padding: 17px 20px;
  /* background-color: blueviolet; */
}

.popular-title h3 {
  height: 22px;
  line-height: 22px;
  color: rgb(98, 199, 169);
}

.popular-title h3::before {
  display: inline-block;
  margin-right: 12px;
  content: " ";
  width: 4px;
  height: 16px;
  background-color: rgb(98, 199, 169);
}

.popular-goods {
  display: flex;
}

.popular-goods .goods {
  flex: 1;
  margin-left: -1px;
  margin-top: -1px;
  height: 352px;
  border: 1px solid rgb(192, 224, 192);
}

.popular-goods .goods:hover {
  transform: translateY(-10px);
  transition: all 0.3s;
  border: 1px solid rgb(85, 163, 68);
  z-index: 1;
}

.goods .picture {
  margin: 20px auto 5px auto;
  width: 197px;
  height: 197px;
  /* border: 1px solid rgb(254, 255, 251); */
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
  color: rgb(112, 145, 82);
}

.title .title-writer {
  color: rgb(129, 148, 112);
  text-align: center;
  font-size: 12px;
  /* background-color: azure; */
  /* overflow: hidden; */
}

.goods .price {
  margin-left: 20px;
  margin-top: 40px;
  width: 200px;
  height: 40px;
  /* background-color: rgb(253, 255, 251); */
  color: rgb(71, 163, 46);
  font-size: 30px;
  line-height: 40px;
}

.goods .price::before {
  display: inline-block;
  content: "¥";
  font-size: 14px;
}
</style>