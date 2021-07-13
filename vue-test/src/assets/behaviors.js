export function ReceiveDetails(bidVal, bnameVal, bwriterVal, bpriceVal, bnowpriceVal, bimageVal, tidVal, btranslateVal, bpublishingVal, bisbnVal, bsuitVal, tryreadingVal) {
    let routeUrl = this.$router.resolve({
      path: '/details',
      query: {
        bid: bidVal,
        bname: bnameVal, 
        bwriter: bwriterVal,
        bprice: bpriceVal,
        bnowprice: bnowpriceVal,
        bimage: bimageVal,
        tid: tidVal,
        btranslate: btranslateVal,
        bpublishing: bpublishingVal,
        bisbn: bisbnVal, 
        bsuit: bsuitVal,
        tryreading: tryreadingVal
      }
    });
    window.open(routeUrl.href, '_blank');
}

export function Addclicknum(bid) {
  let postData = this.qs.stringify({
    bid: bid
  })
  this.axios({
    method: 'post',
    url: '/ssm_project_war_exploded/book/addClicknum',
    data: postData
  }).then((response) => {
    console.log(response.data);
  }).catch((error) => {
    console.log(error);
  });
}

export function Addfavorites(bid) {
  let postData = this.qs.stringify({
    bid: bid
  })
  this.axios({
    method: 'post',
    url: '/ssm_project_war_exploded/book/addFavorites',
    data: postData
  }).then((response) => {
    console.log(response.data);
  }).catch((error) => {
    console.log(error);
  });
}