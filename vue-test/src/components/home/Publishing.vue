<template>
  <div class="publishing">
    <ul>
      <li><a href="#"><img src="../../assets/upload/rmwx.jpeg"></a></li>
      <li><a href="#"><img src="../../assets/upload/rmjy.jpeg"></a></li>
      <li><a href="#"><img src="../../assets/upload/gdjy.jpeg"></a></li>
      <li><a href="#"><img src="../../assets/upload/yl.png"></a></li>
      <li><a href="#"><img src="../../assets/upload/rmyd.jpeg"></a></li>
      <li><a href="#"><img src="../../assets/upload/hxgy.png"></a></li>
      <li><a href="#"><img src="../../assets/upload/cq.jpeg"></a></li>
      <li><a href="#"><img src="../../assets/upload/zhsj.png"></a></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Publishing",

  mounted() {
      let publishing = document.querySelector('.publishing');
      let ul = publishing.querySelector('ul');
      let lis = ul.querySelectorAll('li');
      for (var i =0; i < lis.length; i ++) {
          lis[i].addEventListener('mouseenter',function() {
              for (var j=0;j<lis.length;j++) {
                  lis[j].className='other';
              }
              this.className='current';
          })
          lis[i].addEventListener('mouseleave',function() {
              for (var j=0;j<lis.length;j++) {
                  lis[j].className='current';
              }
          })
      }
  }
};
</script>

<style>
.publishing {
  width: 250px;
  height: 452px;
  background-color: black;
}

.publishing ul {
  display: flex;
  flex-wrap: wrap;
}

.publishing ul li {
  width: 123px;
  height: 111px;
  border: 1px solid rgb(243, 248, 239);
}

.publishing .current {
    opacity: 1;
}

.publishing .other {
    opacity: 0.5;
}
</style>