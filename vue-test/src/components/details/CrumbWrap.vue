<template>
  <!-- 面包屑导航 -->
  <div class="crumb_wrap">
    <a href="javascript:;" class="bookkind" @click="_transferKname(crumb.title)">{{ crumb.title }}</a>
    <a href="javascript:;" class="booktype" @click="_transferTname(crumb.subtitle)">{{ crumb.subtitle }}</a>
    <a href="javascript:;" class="bookname">{{ crumb.detail }}</a>
  </div>
</template>

<script>
export default {
  name: "CrumbWrap",

  props: {
    crumb: {
      title: String,
      subtitle: String,
      detail: String,
    },
  },

  methods: {
    /**
     * 点击title
     */
    _transferKname(knameVal) {
      let routeUrl = this.$router.resolve({
        path: '/kindlist',
        query: {
          kname: knameVal
        }
      });
      window.open(routeUrl.href, '_blank');
    },

    /**
     * 点击subtitle
     */
    _transferTname(tnameVal) {
      // alert(tnameVal)
      let postData = this.qs.stringify({
        tname: tnameVal
      })
      this.axios({
        method: 'post',
        url: '/ssm_project_war_exploded/booktype/obtainTidByTname',
        data: postData
      }).then(response => {
        console.log(response.data.booktype.tid);
        let tid = response.data.booktype.tid;
        let routeUrl = this.$router.resolve({
          path: '/list',
          query: {
            tid: tid,
            tname: tnameVal
          }
        });
        window.open(routeUrl.href, '_blank');
      }).catch(error => {
        console.log(error);
      })
    }
  }
};
</script>

<style>
.crumb_wrap {
  /* margin-top: 10px; */
  height: 30px;
  line-height: 30px;
  font-size: 16px;
  /* background-color: pink; */
}

.bookkind::after {
  display: inline-block;
  margin: 0px 5px;
  content: "";
  font-family: "icomoon";
  font-size: 16px;
  /* background-color: aquamarine; */
}

.booktype::after {
  display: inline-block;
  margin: 0px 5px;
  content: "";
  font-family: "icomoon";
  font-size: 16px;
}
</style>