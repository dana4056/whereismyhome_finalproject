<template>
    <b-nav-item-dropdown text="전체공지사항" class="dropdown">
        <table class="table table-hover">
            <!-- <thead>
            <tr class="table-secondary">
                <th scope="col">번호</th>
                <th scope="col">제목</th>
                <th scope="col">조회수</th>
            </tr>
            </thead> -->

            <tbody id="noticeBody">
                <tr v-for="(notice, index) in this.$store.getters.allPinNotice" :key="notice.id" @click="movePage(notice.id, $event)">
                    <td style="color:red">{{index+1}}</td>
                    <td style="color:red">{{notice.title}}</td>
                    <td style="color:red">{{notice.view}}</td>
                </tr>
            </tbody>
        </table>  
    </b-nav-item-dropdown>
</template>

<script>
export default {

    data(){
        return { 
        } 
    },
    created(){
        this.$store.dispatch('getPinNoticeList');
    },
    computed:{
        fetchPinNotice(){
            return this.$store.getters.allPinNotice;
        },
    },
    methods: {
        movePage(id){
            this.$router.push(`/notice/view/${id}`)
        }, 
    }

}
</script>

<style scoped>
.dropdown{
    width: fit-content;
}
.dropdown-menu{
    --bs-dropdown-min-width: 18rem;
}

</style>
