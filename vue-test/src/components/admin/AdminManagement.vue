<template>
    <div class="admin-management">
         <div class="admin-management-title">
            <input type="text" placeholder="根据管理员名查询" v-model="searchInformation" class="admin-management-title-input">
            <div class="admin-management-title-div"><a href="javascript:;" @click="_addAdmin()">新增管理员</a></div>
        </div>
        <div class="admin-management-body">
            <div class="adminmanagement-body-title">
                <div class="adminmanagement-body-aid">管理员编号</div>
                <div class="adminmanagement-body-aname">管理员姓名</div>
                <div class="adminmanagement-body-password">管理员登陆密码</div>
                <div class="adminmanagement-body-operator">操作</div>
            </div>
            <div class="adminmanagement-body-item" v-for="item in admins" :key="item.aid">
                <div class="adminmanagement-body-aid"> {{item.aid}} </div>
                <div class="adminmanagement-body-aname"> {{item.aname}} </div>
                <div class="adminmanagement-body-password"> ****** </div>
                <div class="adminmanagement-body-operator">
                    <div><a href="javascript:;" @click="_modifyAdmin(item.aid)">修改</a></div>
                    <div><a href="javascript:;">删除</a></div>
                </div>
            </div>
            <div class="adminmanagemnent-body-modal" ref="AdminModalBox">
                <div class="adminmanagement-modalbox-close" @click="_closeModal()">X</div>
                <div class="adminmanagement-modalbox-row">
                    <div class="adminmanagement-modalbox-left">管理员名</div>
                    <div class="adminmanagement-modalbox-right">
                        <input type="text" v-model="Aname">
                    </div>
                </div>
                <div class="adminmanagement-modalbox-row">
                    <div class="adminmanagement-modalbox-left">管理员密码</div>
                    <div class="adminmanagement-modalbox-right">
                        <input type="password" v-model="Password">
                    </div>
                </div>
                <div class="adminmanagement-modalbox-btn" @click="_submitChange()">提交</div>
            </div>
            <el-pagination
                class="admin-management-body-pagination"
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
    name: 'AdminManagement',

    inject:['reload'], //调用刷新

    data() {
        return {
            searchInformation: null,
            admins: [],
            pageSize: 10,
            currentPage: 1,
            total: 0,
            hide: false,
            fold: 5,
            Aname: null,
            Password: null
        }
    },

    created() {
        /**
         * 获得全部记录
         */
        let postData = this.qs.stringify({
            admins: this.admins
        })
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/admin/obtainAllAdmins',
            data: postData
        }).then((response) => {
            this.admins = response.data.admins;
            console.log(this.admins);
        }).catch((error) => {
            console.log(error);
        });

        /**
         * 获得全部记录数
         */
        this.axios({
            method: 'post',
            url: '/ssm_project_war_exploded/admin/obtainAllAdminsCount',
            data: postData
        }).then((response) => {
            this.total = response.data
        }).catch((error) => {
            console.log(error);
        });
    },

    mounted() {
        let that = this;
        let searchIpt = document.querySelector(".admin-management-title-input");
        searchIpt.addEventListener('keyup', function(e) {
            if (e.keyCode == 13) {
                let postData = that.qs.stringify({
                    searchInformation: that.searchInformation
                })
                that.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/admin/obtainAdminBysearch',
                    data: postData
                }).then((response) => {
                    that.admins = response.data.admins
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
                url: '/ssm_project_war_exploded/admin/ManageCurrentAdmins',
                data: postData1
            }).then(response => {
                this.admins = response.data.admins;
            }).catch(error => {
                console.log(error);
            });
        },

        /**
         * 修改
         */
        _modifyAdmin(aid) {
            // let modalBox = document.querySelector(".adminmanagemnent-body-modal");
            let modalBox = this.$refs.AdminModalBox;
            modalBox.style.display = 'block';
            modalBox.setAttribute('aid', aid);
            let postData = this.qs.stringify({
                aid: aid
            })
            this.axios({
                method: 'post',
                url: '/ssm_project_war_exploded/admin/obtainAdminByAid',
                data: postData
            }).then((response) => {
                this.Aname = response.data.admin.aname;
                this.Password = response.data.admin.password;
            }).catch((error) => {
                console.log(error);
            });
        },

        /**
         * 关闭模态框
         */
        _closeModal() {
            // let modalBox = document.querySelector(".adminmanagemnent-body-modal");
            let modalBox = this.$refs.AdminModalBox;
            modalBox.style.display = 'none';
            this.Aname = null;
            this.Password = null;
        },

        /**
         * 新增
         */
        _addAdmin() {
            // let modalBox = document.querySelector(".adminmanagemnent-body-modal");
            let modalBox = this.$refs.AdminModalBox;
            modalBox.style.display = 'block';
        },

        /**
         * 提交
         */
        _submitChange() {
            let that = this;
            // let modalBox = document.querySelector(".adminmanagemnent-body-modal");
            let modalBox = this.$refs.AdminModalBox;
            let aid =  modalBox.getAttribute('aid');
            if (aid == null) {
                // 说明是新增
                let postData = this.qs.stringify({
                    aname: this.Aname,
                    password: this.Password
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/admin/insertNewAdmin',
                    data: postData
                }).then((response) => {
                    this.$message.success("新增成功");
                    this.reload();
                }).catch((error) => {
                    console.log(error);
                });
            } else {
                // 说明是修改
                let postData1 = this.qs.stringify({
                    aid: aid,
                    aname: this.Aname,
                    password: this.Password
                })
                this.axios({
                    method: 'post',
                    url: '/ssm_project_war_exploded/admin/changeAdminByAid',
                    data: postData1
                }).then((response) => {
                    this.$message.success("修改成功")
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
.admin-management {
    margin-bottom: 100px;
    width: 970px;
}

.admin-management-title {
    display: flex;
    height: 44px;
    background-color: rgb(125, 206, 149);
}

.admin-management-title input {
    margin: 4px 0 4px 15px;
    padding: 0 8px;
    width: 200px;
    height: 34px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.admin-management-body {
    position: relative;
    margin: 35px 0 0 0;
}

.adminmanagement-body-title {
    display: flex;
    font-size: 14px;
    height: 35px;
    line-height: 35px;
    text-align: center;
    background-color: rgb(194, 224, 180);
}

.adminmanagement-body-aid {
    width: 240px;
}

.adminmanagement-body-aname {
    width: 250px;
}

.adminmanagement-body-password {
    width: 240px;
}

.adminmanagement-body-operator {
    width: 240px;
}

.adminmanagement-body-item {
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

.admin-management-title-div {
    margin-left: 30px;
    font-size: 16px;
    line-height: 44px;
}

.admin-management-body-pagination {
    margin-top: 30px;
    text-align: center;
}

.adminmanagemnent-body-modal {
    display: none;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -150px;
    margin-left: -200px;
    width: 400px;
    height: 300px;
    background-color: rgb(115, 202, 115);
    border-radius: 5px;
    z-index: 99;
}

.adminmanagement-modalbox-row {
    display: flex;
    margin-top: 35px;
    height: 40px;
    line-height: 40px;
}

.adminmanagement-modalbox-left {
    font-size: 16px;
    width: 120px;
    text-align: right;
}

.adminmanagement-modalbox-right {
    margin-left: 30px;
}

.adminmanagement-modalbox-right input {
    padding: 0 8px;
    width: 120px;
    height: 35px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.adminmanagement-modalbox-close {
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

.adminmanagement-modalbox-btn {
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