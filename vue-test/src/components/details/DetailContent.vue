<template>
  <div class="drtailcontent">
    <!-- 面包屑导航 -->
    <CrumbWrap v-bind:crumb="book"></CrumbWrap>

    <!-- 商品上半部分购物车 -->
    <DetailShoppingCart v-bind:shoppingcart="bookdetail"></DetailShoppingCart>

    <!-- 商品下半部分详情 -->
    <DetailProduct v-bind:shoppingcart="bookdetail"></DetailProduct>
  </div>
</template>

<script>
import DetailProduct from "@/components/details/DetailProduct.vue";
import DetailShoppingCart from "@/components/details/DetailShoppingCart.vue";
import CrumbWrap from "@/components/details/CrumbWrap.vue";

export default {
  name: "DetailContent",

  components: {
    DetailProduct,
    DetailShoppingCart,
    CrumbWrap,
  },

  data() {
    return {
      book: {
        title: "",
        subtitle: "",
        detail: "",
      },
      bookdetail: {
        // test: "8.00",
        bookid: "",
        bookimage: "",
        bookname: "",
        bookwriter: "",
        bookprice: "",
        booknowprice: "",
        booktranslate: "",
        bookpublishing: "",
        bookisbn: "",
        booksuit: "",
      },
      //   btid: '',     //book中的tid，用这个在booktype表中查tname
      //   tkid: '',     //booktype中的kid,用这个在bookkind表中查kname
    };
  },

  created() {
    let bid = this.$route.query.bid;
    let bname = this.$route.query.bname;
    let bwriter = this.$route.query.bwriter;
    let bprice = this.$route.query.bprice;
    let bnowprice = this.$route.query.bnowprice;
    let bimage = this.$route.query.bimage;
    let tid = this.$route.query.tid;
    let btranslate = this.$route.query.btranslate;
    let bpublishing = this.$route.query.bpublishing;
    let bisbn = this.$route.query.bisbn;
    let bsuit = this.$route.query.bsuit;
    console.log(this.$route.query.bisbn);
    // console.log(this.$route.query.tid);
    //   let kname = this.$route.query.kname;
    // console.log(tid);
    this.bookdetail.bookid = bid;
    this.bookdetail.bookimage = bimage;
    this.bookdetail.bookname = bname;
    this.bookdetail.bookwriter = bwriter;
    this.bookdetail.bookprice = bprice;
    this.bookdetail.booknowprice = bnowprice;
    this.bookdetail.booktranslate = btranslate;
    this.bookdetail.bookpublishing = bpublishing;
    this.bookdetail.bookisbn = bisbn;
    this.bookdetail.booksuit = bsuit
    // this.book.title = kname;
    // this.book.subtitle = tname;
    this.book.detail = bname;
    // var tkid = '';

    let postData1 = this.qs.stringify({
      btid: tid,
    });
    this.axios({
      method: "post",
      url: "/ssm_project_war_exploded/booktype/obtainBookTypeName",
      data: postData1,
    })
      .then((response) => {
        this.types = response.data.types;
        // console.log(this.types.kid);
        this.book.subtitle = this.types.tname;
        this.tkid = this.types.kid;

        let postData2 = this.qs.stringify({
          tkid: this.tkid,
        });
        this.axios({
          method: "post",
          url: "/ssm_project_war_exploded/bookkind/obtainBookKindName",
          data: postData2,
        })
          .then((response) => {
            this.kinds = response.data.kinds;
            console.log(this.kinds);
            this.book.title = this.kinds.kname;
          })
          .catch((error) => {
            console.log(error);
          });
      })
      .catch((error) => {
        console.log(error);
      });

    // console.log(this.tkid);

    // let postData2 = this.qs.stringify({
    //     tkid: this.tkid,
    // });
    // this.axios({
    //   method: "post",
    //   url: "/ssm_project_war_exploded/bookkind/obtainBookKindName",
    //   data: postData2,
    // }).then((response) => {
    //     this.kinds = response.data.kinds;
    //     console.log(this.kinds);
    //     this.book.title = this.kinds.kname;
    //   })
    //   .catch((error) => {
    //     console.log(error);
    //   });
  },
};
</script>

<style>

</style>