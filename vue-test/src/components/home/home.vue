<template>
  <div>
    <LoginTopNavigation></LoginTopNavigation>
    <Header></Header>
    <MiddleNav></MiddleNav>
    <div class="middleContent">
      <div class="middle-left"></div>
      <Carousel></Carousel>
      <Publishing></Publishing>
    </div>
    <PopularRecommendation></PopularRecommendation>
    <SmallSpot></SmallSpot>
    <SoonScheduled></SoonScheduled>
    <Footer></Footer>
  </div>
</template>

<script>
import SoonScheduled from "./SoonScheduled.vue";
import SmallSpot from "./SmallSpot.vue";
import PopularRecommendation from "./PopularRecommendation.vue";
import Publishing from "./Publishing.vue";
import Carousel from "./Carousel.vue";
import Footer from "./Footer.vue";
import MiddleNav from "./MiddleNav.vue";
import Header from './Header.vue';
import LoginTopNavigation from "./LoginTopNavigation.vue";

export default {
  name: "Home",

  components: {
    SoonScheduled,
    SmallSpot,
    PopularRecommendation,
    Publishing,
    Carousel,
    Footer,
    MiddleNav,
    LoginTopNavigation,
    Header,
  },

  data() {
    return {
      user: null
    }
  },

  mounted() {
    this.user = sessionStorage.getItem('userNickName')
  }
};
</script>

<style>
.middleContent {
  display: flex;
  margin-top: 10px;
  height: 455px;
}

.middleContent .middle-left {
  width: 210px;
}

</style>