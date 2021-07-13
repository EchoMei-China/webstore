<template>
  <div class="dropdown">
    <div class="dt">全部书籍分类</div>
    <div class="dd">
      <ul class="show-kind">
        <li v-for="item in lis" :key="item.kid" @mouseover="showTypes(item.kid)">
          {{ item.kname }}
        </li>
      </ul>
      <div class="show-type" @click="hideTypes()">
        <ul>
          <li v-for="item in types" :key="item.tid">
            <a href="javascript:;" @click="_getList(item.tid, item.tname)">{{ item.tname }}</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "DropDown",
  data() {
    return {
      lis: [],
      types: [],
      tkid:'', //booktype里的kid
    };
  },
  //页面创建时调用方法
  created() {
    let postData = this.qs.stringify({
      lis: this.lis,
    });
    this.axios({
      method: "post",
      url: "/ssm_project_war_exploded/bookkind/obtainKind",
      data: postData,
    })
      .then((response) => {
        this.lis = response.data.lis;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    showTypes(val) {
      const showType =document.querySelector(".show-type");
      showType.style.display="block";

      let postData = this.qs.stringify({
        types: this.types,
        tkid: val,
      });
      
      this.axios({
        method: "post",
        url: "/ssm_project_war_exploded/booktype/obtainType",
        data: postData,
      })
      .then((response) => {
        this.types = response.data.types;
      })
      .catch((error) => {
        console.log(error);
      });
    },

    hideTypes() {
      const showType =document.querySelector(".show-type");
      showType.style.display="none";
    },

    _getList(val1, val2) {
      let routeUrl = this.$router.resolve({
        path: '/list',
        query: {tid: val1, tname: val2}
      });
      window.open(routeUrl.href, '_blank');
    }
  },
};
</script>

<style>
.dropdown {
  width: 210;
  height: 45px;
}

.dropdown .dt {
  width: 210px;
  height: 45px;
  background-color: rgb(68, 170, 68);
  color: white;
  font-size: 18px;
  text-align: center;
  line-height: 45px;
}

.dropdown .dd {
  display: flex;
  position: relative;
  margin-top: 2px;
}

.dropdown .dd .show-kind li {
  width: 210px;
  height: 39px;
  border-bottom: 1px solid rgb(241, 252, 240);
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  background-color: rgb(207, 243, 200);
}

.dropdown .dd .show-kind li:hover {
  color: rgb(114, 201, 97);
  background-color: rgb(241, 252, 240);
}

.dropdown .dd .show-kind li::after {
  display: inline-block;
  margin-left: 20px;
  content: "";
  font-family: "icomoon";
  font-size: 14px;
}

.dropdown .dd .show-type{
  display: none;
  position: absolute;
  left: 210px;
  width: 730px;
  height: 100%;
  background-color: rgb(241, 252, 240);
  z-index: 1;
}

.dropdown .dd .show-type ul li {
  height: 30px;
  /* background-color: pink; */
  text-align: center;
  line-height: 30px;
  font-size: 14px;
}

</style>