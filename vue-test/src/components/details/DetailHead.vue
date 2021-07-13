<template>
  <div class="detail-head">
    <ul>
      <li class="a1"><a href="javascript:;">最热图书</a></li>
      <li class="a2"><a href="javascript:;">最热作家</a></li>
      <li class="a3"><a href="javascript:;">最热标签</a></li>
      <li class="a4"><a href="javascript:;">最新上架</a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "DetailHead",
};
</script>

<style>
.detail-head {
  width: 30;
  border-bottom: 2px solid rgb(97, 173, 53);
  /* background-color: rgb(240, 252, 233); */
}

ul {
  display: flex;
}

ul li {
    flex: 1;
  /* margin-left: 50px; */
  height: 30px;
  font-size: 16px;
  text-align: center;
  line-height: 30px;
}

.a1 a {
  color: rgb(102, 196, 247);
}

.a2 a {
  color: rgb(136, 95, 248);
}

.a3 a {
  color: rgb(132, 136, 252);
}

.a4 a {
  color: rgb(189, 123, 255);
}
</style>