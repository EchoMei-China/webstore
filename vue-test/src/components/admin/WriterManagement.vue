<template>
    <div class="writer-management">
        <div class="writer-management-title">
            <input type="text" placeholder="根据作者名查询" v-model="searchInformation" class="writer-management-title-input">
            <div class="writer-management-title-div"><a href="javascript:;" @click="_addWriter()">新增新作者信息</a></div>
        </div>
        <div class="writer-management-body">
            <div class="writermanagement-body-title">
                <div class="writermanagement-body-writer">作者</div>
                <div class="writermanagement-body-introduction">简介</div>
                <div class="writermanagement-body-masterprice">代表作</div>
                <div class="writermanagement-body-operator">操作</div>
            </div>
            <div class="writermanagement-body-item" v-for="item in writers" :key="item.wid">
                <div class="writermanagement-body-writer"> {{item.bwriter}} </div>
                <div class="writermanagement-body-introduction"> {{item.wintroduction}} </div>
                <div class="writermanagement-body-masterprice"> {{item.wmasterpiece}} </div>
                <div class="writermanagement-body-operator">
                    <div><a href="javascript:;" @click="_modifyWriter(item.wid)">修改</a></div>
                </div>
            </div>
            <div class="writermanagement-body-modal">
                <div class="writermanagement-modalbox-close" @click="_closeModal()">X</div>
                <div class="writermanagement-modalbox-row" v-if="WriterName != null">
                    <div class="writermanagement-modalbox-left">{{WriterName}}</div>
                </div>
                <div class="writermanagement-modalbox-row" v-else>
                    <div class="writermanagement-modalbox-left">作者</div>
                    <div class="writermanagement-modalbox-right">
                        <input type="text" v-model="Bwriter">
                    </div>
                </div>
                <div class="writermanagement-modalbox-row">
                    <div class="writermanagement-modalbox-left">简介</div>
                    <div class="writermanagement-modalbox-right">
                        <textarea v-model="wIntroduction" name="introduction" cols="40" rows="15"></textarea>
                    </div>
                </div>
                <div class="writermanagement-modalbox-row">
                    <div class="writermanagement-modalbox-left">代表作</div>
                    <div class="writermanagement-modalbox-right">
                        <textarea v-model="wMasterpiece" name="masterpieces" cols="40" rows="12"></textarea>
                    </div>
                </div>
                <div class="writermanagement-modalbox-btn" @click="_submitWriter()">提交</div>
            </div>
            <el-pagination
                class="writer-management-body-pagination"
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
</template>

<script>
export default {
    name: 'WriterManagement',

    inject:['reload'], //调用刷新

    data() {
        return {
            searchInformation: null,
            writers: [],
            pageSize: 10,
            currentPage: 1,
            total: 0,
            hide: false,
            fold: 5,
            WriterName: null,
            Bwriter: null,
            wIntroduction: null,
            wMasterpiece: null
        }
    },

    created() {
        /**
         * 获取所有作者
         */
        let postData = this.qs.stringify({
            writers: this.writers
        })
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/writer/obtianAllWriters',
            data: postData
        }).then((response) => {
            this.writers = response.data.writers
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获取总数
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/writer/obtianAllWritersCount',
            data: postData
        }).then((response) => {
            this.total = response.data
        }).catch((error) => {
            console.log(error);
        });
    },

    mounted() {
        /**
         * 查找
         */
        let that = this;
        let search = document.querySelector(".writer-management-title-input");
        search.addEventListener("keyup", function(e) {
            if (e.keyCode == 13) {
                let postData = that.qs.stringify({
                    searchInformation: that.searchInformation
                })
                that.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/writer/obtainWriterBysearch',
                    data: postData
                }).then((response) => {
                    that.writers = response.data.writers
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
                url: '/ssm_project_war_exploded/writer/ManageCurrentWriters',
                data: postData1
            }).then(response => {
                this.writers = response.data.writers;
            }).catch(error => {
                console.log(error);
            });
        },

        /**
         * 关闭
         */
        _closeModal() {
            let modalbox= document.querySelector(".writermanagement-body-modal");
            modalbox.style.display = 'none';
            this.WriterName = null;
            this.Bwriter = null;
            this.wIntroduction = null;
            this.wMasterpiece = null;
        },

        /**
         * 新增
         */
        _addWriter() {
            let modalbox= document.querySelector(".writermanagement-body-modal");
            modalbox.style.display = 'block';
        },

        /**
         * 修改
         */
        _modifyWriter(wid) {
            let modalbox= document.querySelector(".writermanagement-body-modal");
            modalbox.style.display = 'block';
            modalbox.setAttribute("wid",wid);
            let postData = this.qs.stringify({
                wid: wid
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/writer/obtainWriterByWid',
                data: postData
            }).then((response) => {
                this.WriterName = response.data.writer.bwriter;
                this.wIntroduction = response.data.writer.wintroduction;
                this.wMasterpiece = response.data.writer.wmasterpiece
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 提交
         */
        _submitWriter() {
            let modalbox= document.querySelector(".writermanagement-body-modal");
            let wid = modalbox.getAttribute("wid");
            if (wid == null) {
                // 说明是新增
                let postData = this.qs.stringify({
                    bwriter: this.Bwriter,
                    wintroduction: this.wIntroduction,
                    wmasterpiece: this.wMasterpiece
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/writer/insertNewWriter',
                    data: postData
                }).then((response) => {
                    this.$message.success("添加成功");
                    this.reload();
                }).catch((error) => {
                    console.log(error);
                });
            } else {
                // 说明是更新
                let postData1 = this.qs.stringify({
                    wid: wid,
                    wintroduction: this.wIntroduction,
                    wmasterpiece: this.wMasterpiece
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/writer/updateWriterByWid',
                    data: postData1
                }).then((response) => {
                    this.$message.success("修改成功");
                    this.reload();
                }).catch((error) => {
                    console.log(error);
                });
            }
        }

    }
}
</script>

<style>
.writer-management {
    margin-bottom: 100px;
    width: 970px;
}

.writer-management-title {
    display: flex;
    height: 44px;
    background-color: rgb(125, 206, 149);
}

.writer-management-title input {
    margin: 4px 0 4px 15px;
    padding: 0 8px;
    width: 200px;
    height: 34px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.writer-management-title-div {
    margin-left: 30px;
    font-size: 16px;
    line-height: 44px;
}

.writer-management-body {
    position: relative;
    margin: 35px 0 0 0;
}

.writermanagement-body-title {
    display: flex;
    font-size: 14px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    background-color: rgb(194, 224, 180);
}

.writermanagement-body-writer {
    width: 200px;
    overflow: hidden;
}

.writermanagement-body-introduction {
    width: 330px;
    overflow: hidden;
}

.writermanagement-body-masterprice {
    width: 330px;
    overflow: hidden;
}

.writermanagement-body-operator {
    width: 110px;
    overflow: hidden;
}

.writermanagement-body-item {
    display: flex;
    margin: 10px 0 0 0;
    height: 60px;
    font-size: 14px;
    line-height: 20px;
    word-break: break-all;
    /* overflow: hidden; */
    text-align: center;
    border-bottom: 1px solid #ccc;
}

.writer-management-body-pagination {
    margin-top: 30px;
    text-align: center;
}

.writermanagement-body-modal {
    display: none;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -350px;
    margin-left: -300px;
    width: 600px;
    height: 700px;
    background-color: rgb(148, 226, 152);
    border-radius: 5px;
}

.writermanagement-modalbox-row {
    display: flex;
    margin-top: 30px;
}

.writermanagement-modalbox-left {
    width: 120px;
    font-size: 16px;
    text-align: right;
}

.writermanagement-modalbox-right {
    margin-left: 30px;
}

.writermanagement-modalbox-right input {
    padding: 0 8px;
    width: 120px;
    height: 35px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.writermanagement-modalbox-right textarea {
    padding: 8px 15px;
    resize: none;
    border-radius: 5px;
    border: 1px solid #ccc;
}

.writermanagement-modalbox-close {
    position: absolute;
    top: 0;
    right: 0;
    width: 40px;
    height: 40px;
    font-size: 20px;
    font-weight: 500;
    text-align: center;
    line-height: 40px;
    cursor: pointer;
}

.writermanagement-modalbox-btn {
    margin: 30px 0 0 150px;
    width: 120px;
    height: 35px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
    line-height: 35px;
    color: white;
    background-color: green;
    border-radius: 5px;
    cursor: pointer;
}
</style>