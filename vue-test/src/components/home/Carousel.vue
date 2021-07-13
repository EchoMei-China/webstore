<template>
  <div class="middle-carousel">
    <!-- 左侧按钮 -->
    <a href="javascript:;" class="arrow-l" @click="_left()"></a>
    <!-- 右侧按钮 -->
    <a href="javascript:;" class="arrow-r" @click="_right()"></a>

    <!-- 图片滚动区域 -->
    <ul>
      <li>
        <a href="javascript:;">
          <img src="../../assets/upload/poyun.jpg" />
        </a>
      </li>
      <li>
        <a href="javascript:;">
          <img src="../../assets/upload/cai.jpg" />
        </a>
      </li>
      <li>
        <a href="javascript:;">
          <img src="../../assets/upload/dili.jpg" />
        </a>
      </li>
      <li>
        <a href="javascript:;">
          <img src="../../assets/upload/chan.jpg" />
        </a>
      </li>
      <li>
        <a href="javascript:;">
          <img src="../../assets/upload/vue.jpg" />
        </a>
      </li>
    </ul>

    <!-- 小圆点 -->
    <ol class="circle"></ol>
  </div>
</template>

<script>
export default {
  name: "Carousel",

  data() {
    return {
      num: 0,
      circleNum: 0,
      flag: true,
    };
  },

  methods: {
    Animate(ul, target, callback) {
      // 先清除以前的定时器，只保留当前的一个定时器执行
      clearInterval(ul.timer);
      ul.timer = setInterval(function () {
        // 步长值写到定时器的里面
        //offsetLeft 元素边框外侧到浏览器窗口左边的距离
        var step = (target - ul.offsetLeft) / 10;
        step = step > 0 ? Math.ceil(step) : Math.floor(step);
        if (ul.offsetLeft == target) {
          // 停止动画 本质是停止定时器
          clearInterval(ul.timer);
          // 回调函数写到定时器结束里面
          callback && callback();
        }
        // 把每次加1 这个步长值改为一个慢慢变小的值  步长公式：(目标值 - 现在的位置) / 10
        ul.style.left = ul.offsetLeft + step + "px";
      }, 15);
    },

    _left() {
      const that = this;
      if (this.flag) {
        //节流阀
        this.flag = false; //关闭节流阀
        let focus = document.querySelector(".middle-carousel");
        let ul = focus.querySelector("ul");
        let ol = focus.querySelector(".circle");
        let focusWidth = focus.offsetWidth;
        // 如果走到了最后一张，就迅速跳转回第一张
        if (this.num == 0) {
          this.num = ul.children.length - 1;
          ul.style.left = -(ul.children.length - 1) * focusWidth + "px";
        }
        this.num--;
        console.log("click按钮时的num" + this.num);
        this.circleNum--;
        this.Animate(ul, -this.num * focusWidth, function () {
          that.flag = true;
        });
        this.circleNum =
          this.circleNum < 0 ? ol.children.length - 1 : this.circleNum;
        // 先清除其他小圆圈的current类 留下当前小圆圈的current
        for (var i = 0; i < ol.children.length; i++) {
          ol.children[i].className = "";
        }
        ol.children[this.circleNum].className = "current";
      }
    },

    _right() {
      const that = this;
      if (this.flag) {
        //节流阀
        this.flag = false; //关闭节流阀
        let focus = document.querySelector(".middle-carousel");
        let ul = focus.querySelector("ul");
        let ol = focus.querySelector(".circle");
        let focusWidth = focus.offsetWidth;
        // 如果走到了最后一张，就迅速跳转回第一张
        if (this.num == ul.children.length - 1) {
          this.num = 0;
          ul.style.left = 0;
        }
        this.num++;
        console.log("click按钮时的num" + this.num);
        this.circleNum++;
        this.Animate(ul, -this.num * focusWidth, function () {
          that.flag = true; //用回调函数开启节流阀
        });
        this.circleNum =
          this.circleNum == ul.children.length - 1 ? 0 : this.circleNum;
        // 先清除其他小圆圈的current类 留下当前小圆圈的current
        for (var i = 0; i < ol.children.length; i++) {
          ol.children[i].className = "";
        }
        ol.children[this.circleNum].className = "current";
      }
    },
  },

  mounted() {
    // window.addEventListener("load", function () {
    let that = this;
    let arrow_l = document.querySelector(".arrow-l");
    let arrow_r = document.querySelector(".arrow-r");
    let focus = document.querySelector(".middle-carousel");
    //   鼠标经过显示左右按钮
    focus.addEventListener("mouseenter", function () {
      arrow_l.style.display = "block";
      arrow_r.style.display = "block";
      clearInterval(timer);
      timer = null;
    });
    //   鼠标离开隐藏左右按钮
    focus.addEventListener("mouseleave", function () {
      arrow_l.style.display = "none";
      arrow_r.style.display = "none";
      timer = window.setInterval(function () {
        that._right().click();
      }, 2000);
    });

    //   动态生成小圆圈
    let ul = focus.querySelector("ul");
    let ol = focus.querySelector(".circle");
    console.log(ul.children.length);
    for (var i = 0; i < ul.children.length; i++) {
      //  1.创建li
      let li = document.createElement("li");
      // 记录li的索引号
      li.setAttribute("index", i);
      //  2.把li插进ol里
      ol.appendChild(li);
      //  点击小圆圈，给当前小圆圈添加current类，其他的去除current
      //  排他思想
      //  3.在生成小圆圈时直接绑定点击事件
      li.addEventListener("click", function () {
        for (var i = 0; i < ol.children.length; i++) {
          ol.children[i].className = "";
        }
        this.className = "current";
        //   4.点击小圆圈移动图片,移动图片即 移动ul
        let focusWidth = focus.offsetWidth;
        let index = this.getAttribute("index");
        that.num = index;
        that.circleNum = index;
        console.log("点击li时的num" + that.num);
        let target = -index * focusWidth;
        //   console.log(focusWidth);
        //   console.log(index);
        that.Animate(ul, target);
      });
    }
    ol.children[0].className = "current";
    //   克隆第一张图片放到最后面
    var first = ul.children[0].cloneNode(true);
    ul.appendChild(first);

    // 自动播放轮播图
    var timer = window.setInterval(function () {
      // 相当于一直点击右侧按钮
      that._right().click();
    }, 2000);
  },
};
</script>

<style>
.middle-carousel {
  overflow: hidden;
  position: relative;
  margin: 0 10px;
  width: 720px;
  height: 455px;
  background-color: antiquewhite;
}

.middle-carousel .arrow-l,
.middle-carousel .arrow-r {
  display: none;
  position: absolute;
  top: 50%;
  margin-top: -20px;
  width: 24px;
  height: 40px;
  font-size: 20px;
  font-family: "icomoon";
  line-height: 40px;
  text-align: center;
  color: white;
  background-color: rgb(0, 0, 0, 0.3);
  z-index: 1;
}

.middle-carousel .arrow-l {
  border-radius: 0 15px 15px 0;
}

.middle-carousel .arrow-r {
  right: 0;
  border-radius: 15px 0 0 15px;
}

.middle-carousel ul {
  display: flex;
  position: absolute;
  top: 0;
  left: 0;
}

.circle {
  position: absolute;
  display: flex;
  padding: 3px 6px;
  bottom: 10px;
  left: 50px;
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: 8px;
}

.circle li {
  margin: 0 2px;
  width: 8px;
  height: 8px;
  border: 2px solid rgba(218, 250, 206, 0.5);
  border-radius: 50%;
}

.circle .current {
  background-color: rgb(218, 250, 206);
}
</style>