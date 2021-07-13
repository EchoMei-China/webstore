<template>
    <div class="book-management">
        <div class="book-management-title">
            <input type="text" placeholder="根据内容查询" v-model="Snippet" class="book-management-title-input" @keyup="_searchBook()">
            <div class="book-management-title-div"><a href="javascript:;" @click="_insertBook()">添加新书</a></div>
        </div>
        <div class="book-management-body">
            <div class="bookmanagement-body-title">
                <div class="bookmanagement-title-bimage">图片</div>
                <div class="bookmanagement-title-bname">书名</div>
                <div class="bookmanagement-title-bwriter">作者</div>
                <div class="bookmanagement-title-btranslate">译者</div>
                <div class="bookmanagement-title-bpublishing">出版社</div>
                <div class="bookmanagement-title-bprice">原价</div>
                <div class="bookmanagement-title-bnowprice">现价</div>
                <div class="bookmanagement-title-binventory">库存</div>
                <div class="bookmanagement-title-bisbn">ISBN</div>
                <div class="bookmanagement-title-bsuit">套装</div>
                <div class="bookmanagement-title-tryreading">试阅地址</div>
                <div class="bookmanagement-title-operator">操作</div>
            </div>
            <div class="bookmanagement-body-body">
                <div class="bookmanagement-body-item" v-for="item in books" :key="item.bid">
                    <div class="bookmanagement-title-bimage">
                        <img :src="item.bimage" alt="">
                    </div>
                    <div class="bookmanagement-title-bname"> {{item.bname}} </div>
                    <div class="bookmanagement-title-bwriter"> {{item.bwriter}} </div>
                    <div class="bookmanagement-title-btranslate"> {{item.btranslate}} </div>
                    <div class="bookmanagement-title-bpublishing"> {{item.bpublishing}} </div>
                    <div class="bookmanagement-title-bprice"> {{item.bprice}} </div>
                    <div class="bookmanagement-title-bnowprice"> {{item.bnowprice}} </div>
                    <div class="bookmanagement-title-binventory"> {{item.binventory}} </div>
                    <div class="bookmanagement-title-bisbn"> {{item.bisbn}} </div>
                    <div class="bookmanagement-title-bsuit"> {{item.bsuit}} </div>
                    <div class="bookmanagement-title-tryreading"> {{item.tryreading}} </div>
                    <div class="bookmanagement-title-operator">
                        <div><a href="javascript:;" @click="_modifyBook(item.bid)">修改</a></div>
                        <div><a href="javascript:;" @click="_deleteBook(item.bid)">删除</a></div>
                    </div>
                </div>
                <div class="bookmanagementbody-body-modalbox">
                    <div class="bookmanagementbody-modalbox-close" @click="_closeMoalbox()">X</div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">上传图片</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <img :src="bImage" alt="" v-if="bImage != null && bImage != ''" class="bookmanagementbody-modalbox-image">
                            <input v-else type="file" id="imageFile" name="imageFile" accept="image/*" enctype="multipart/form-data" class="bookmanagementbody-modalbox-picture">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">书名</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bName">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">作者</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bWriter">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">译者</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bTranslate">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">出版社</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bPublishing">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">原价</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bPrice">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">现价</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bNowprice">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">库存</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bInventory">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">ISBN</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bIsbn">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">是否套装</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="bSuit">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-left">试阅地址</div>
                        <div class="bookmanagementbody-modalbox-right">
                            <input type="text" v-model="tryReading">
                        </div>
                    </div>
                    <div class="bookmanagementbody-modalbox-row">
                        <div class="bookmanagementbody-modalbox-btn" @click="_confirmSubmit()">确认提交</div>
                    </div>
                </div>
                <el-pagination
                    class="book-management-body-pagination"
                    layout="prev, pager, next"
                    :current-page.sync="currentPage"
                    :pager-count="fold"
                    :hide-on-single-page="hide"
                    :page-size="pageSize"
                    :total="total"
                    @current-change="_handlePageChange()">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import qs from 'qs';
import axios from "axios";

export default {
    name: 'BookManagement',

    inject:['reload'], //调用刷新

    data() {
        return {
            Snippet: null,
            books: [],
            pageSize: 10,
            currentPage: 1,
            total: 0,
            hide: false,
            fold: 5,
            bName: null,
            bWriter: null,
            bTranslate: null,
            bPublishing: null,
            bPrice: null,
            bNowprice: null,
            bInventory: null,
            bIsbn: null,
            bSuit: null,
            tryReading: null,
            bImage: null
        }
    },

    created() {
        /**
         * 获取所有的书
         */
        let postData = this.qs.stringify({
            books: this.books
        })
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/book/obtainAllBooks',
            data:postData
        }).then((response) => {
            this.books = response.data.books
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获取书的总数
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/book/obtainAllbooksCount',
            data: postData
        }).then((response) => {
            this.total = response.data
        }).catch((error) => {
            console.log(error);
        });
    },

    mounted() {
        let chooseFile = document.querySelector(".bookmanagementbody-modalbox-picture");
        chooseFile.addEventListener('change', function(e) {
            let formData = new FormData();
            let temp = this.files[0];
            console.log(temp);
            console.log(window.URL.createObjectURL(temp));
            if (temp){
                formData.append('file',temp);
                // img.src = window.URL.createObjectURL(temp)
                axios({
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    },
                    method: 'post',
                    url:"/ssm_project_war_exploded/fileupload/BookPictureUpload",
                    data: formData,
                }).then((response) => {
                    this.$message.success("上传成功")
                    console.log(response);
                }).catch((error) => {
                    console.log(error);
                });
            }

        })
    },

    methods: {
        /**
         * 分页
         */
        _handlePageChange() {
            let postData1 = this.qs.stringify({
                startIndex: (this.currentPage - 1)*this.pageSize, 
                pageSize: this.pageSize,
            });
            console.log(postData1);
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/book/ManageCurrentContentBooks',
                data: postData1
            }).then(response => {
                this.books = response.data.books;
                console.log(this.orders);
            }).catch(error => {
                console.log(error);
            });
        },

        /**
         * 删除
         */
        _deleteBook(bid) {
            let postData = this.qs.stringify({
                bid: bid
            }) 
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/book/deleteBookByBid',
                data: postData
            }).then((response) => {
                this.$message.success("删除成功")
                this.reload();
            }).catch((error) => {
                console.log(error);
                this.$message.warning("删除失败")
            });
        },

        /**
         * 添加新书
         */
        _insertBook() {
            let modalbox = document.querySelector(".bookmanagementbody-body-modalbox");
            modalbox.style.display = 'block';
        },

        /**
         * 关闭模态框
         */
        _closeMoalbox() {
            let modalbox = document.querySelector(".bookmanagementbody-body-modalbox");
            modalbox.style.display = 'none';
            this.bName = null;
            this.bWriter = null;
            this.bTranslate = null;
            this.bPublishing = null;
            this.bPrice = null;
            this.bNowprice = null;
            this.bInventory = null;
            this.bIsbn = null;
            this.bSuit = null;
            this.tryReading = null;
            this.bImage = null;
        },

        /**
         * 修改图书
         */
        _modifyBook(bid) {
            let modalbox = document.querySelector(".bookmanagementbody-body-modalbox");
            modalbox.style.display = 'block';
            modalbox.setAttribute('bid', bid);
            let postData = this.qs.stringify({
                bid: bid
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/book/obtainBookByBid',
                data: postData
            }).then((response) => {
                this.bName = response.data.book.bname
                this.bWriter = response.data.book.bwriter
                this.bTranslate = response.data.book.btranslate
                this.bPublishing = response.data.book.bpublishing
                this.bPrice = response.data.book.bprice
                this.bNowprice = response.data.book.bnowprice
                this.bInventory = response.data.book.binventory
                this.bIsbn = response.data.book.bisbn
                this.bSuit = response.data.book.bsuit
                this.tryReading = response.data.book.tryreading
                this.bImage = response.data.book.bimage
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 确认提交
         */
        _confirmSubmit() {
            let modalbox = document.querySelector(".bookmanagementbody-body-modalbox");
            let bid = modalbox.getAttribute('bid');
            if (bid == null) {
                /**
                 * 说明是添加新图书
                 */
                let postData1 = this.qs.stringify({
                    bname: this.bName,
                    bwriter: this.bWriter,
                    btranslate: this.bTranslate,
                    bpublishing: this.bPublishing,
                    bprice: this.bPrice,
                    bnowprice: this.bNowprice,
                    binventory: this.bInventory,
                    bisbn: this.bIsbn,
                    bsuit: this.bSuit,
                    tryreading: this.tryReading,
                    bimage: this.bImage
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/book/addBook',
                    data: postData1
                }).then((response) => {
                    this.$message.success("添加成功");
                    this.reload();
                }).catch((error) => {
                    console.log(error);
                    this.$message.warning("添加失败")
                });
            } else {
                /**
                 * 说明是更新图书信息
                 */
                let postData = this.qs.stringify({
                    bid: bid
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/book/obtainBookByBid',
                    data: postData
                }).then((response) => {
                    this.$message.success("更新成功");
                    this.reload();
                }).catch((error) => {
                    console.log(error);
                    this.$message.warning("更新失败")
                });
            }
        },

        /**
         * 选择头像
         */
        // _choosePicture(file) {
        //     let formData = new FormData()
        //     let temp = file.files[0]
        //     console.log(temp);
        // },

        /**
         * 模糊查询
         */
        _searchBook() {
            let postData = this.qs.stringify({
                searchInformation: this.Snippet
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/book/obtainBooksBySearch',
                data: postData
            }).then((response) => {
                this.books = response.data.books
            }).catch((error) => {
                console.log(error);
            });
        }

    }
}
</script>

<style>
.book-management {
    margin-bottom: 100px;
    width: 970px;
}

.book-management-title {
    display: flex;
    height: 44px;
    background-color: rgb(140, 223, 151);
}

.book-management-title-div {
    margin-left: 30px;
    font-size: 16px;
    line-height: 44px;
}

.book-management-title-input {
    margin: 4px 0 4px 15px;
    padding: 0 8px;
    width: 200px;
    height: 34px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.bookmanagement-body-title {
    display: flex;
    font-size: 14px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    background-color: rgb(186, 224, 180);
}

.bookmanagement-title-bimage {
    width: 80px;
}

.bookmanagement-title-bname {
    width: 120px;
}

.bookmanagement-title-bwriter {
    width: 100px;
}

.bookmanagement-title-btranslate {
    width: 80px;
}

.bookmanagement-title-bpublishing {
    width: 80px;
}

.bookmanagement-title-bprice {
    width: 60px;
}

.bookmanagement-title-bnowprice {
    width: 60px;
}

.bookmanagement-title-binventory {
    width: 180px;
}

.bookmanagement-title-bisbn {
    width: 100px;
}

.bookmanagement-title-bsuit {
    width: 40px;
}

.bookmanagement-title-tryreading {
    width: 110px;
}

.bookmanagement-title-operator {
    width: 60px;
}

.book-management-body {
    margin-top: 35px;
}

.bookmanagement-body-body {
    position: relative;
    margin-top: 20px;
}

.bookmanagement-body-item {
    display: flex;
    margin: 10px 0 0 0;
    height: 60px;
    font-size: 14px;
    line-height: 20px;
    word-break: break-all;
    overflow: hidden;
    text-align: center;
    border-bottom: 1px solid #ccc;
}

.bookmanagement-title-bimage img {
    margin: 10px 15px;
    width: 38px;
    height: 38px;
    border: 1px solid #ccc;
}

.book-management-body-pagination {
    margin-top: 30px;
    text-align: center;
}

.bookmanagementbody-body-modalbox {
    display: none;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-left: -250px;
    margin-top: -400px;
    color: white;
    width: 500px;
    height: 750px;
    background-color: rgb(62, 143, 62);
    border-radius: 8px;
}

.bookmanagementbody-modalbox-close {
    position: absolute;
    top: 0;
    right: 0;
    width: 40px;
    height: 40px;
    font-size: 20px;
    font-weight: 500;
    text-align: center;
    line-height: 40px;
    color: white;
    cursor: pointer;
}

.bookmanagementbody-modalbox-row {
    display: flex;
    margin-top: 20px;
    height: 40px;
}

.bookmanagementbody-modalbox-left {
    width: 100px;
    text-align: right;
    font-size: 16px;
    line-height: 40px;
}

.bookmanagementbody-modalbox-right {
    margin-left: 30px;
    width: 300px;
}

.bookmanagementbody-modalbox-right input {
    padding: 0 8px;
    margin: 2px 0;
    width: 300px;
    height: 34px;
    overflow: hidden;
    border: 1px solid #ccc;
    border-radius: 8px;
}

.bookmanagementbody-modalbox-right .bookmanagementbody-modalbox-picture {
    width: 200px;
    height: 38px;
    border: none;
}

.bookmanagementbody-modalbox-btn {
    margin-left: 130px;
    width: 160px;
    height: 35px;
    line-height: 35px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
    cursor: pointer;
    color: green;
    background-color: rgb(235, 252, 189);
    border-radius: 5px;
}

.bookmanagementbody-modalbox-image {
    width: 35px;
    height: 35px;
}
</style>