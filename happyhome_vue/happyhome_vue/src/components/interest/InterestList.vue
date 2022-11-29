<template>
  <div class="InterestList">
    <h3>관심 지역 목록</h3>

    <table class="table table-hover">
        <thead>
          <tr class="table-secondary">
              <th scope="col">관심지역</th>
              <th scope="col">삭제</th>
          </tr>
        </thead>

        <tbody id="InterestBody">
            <tr v-for="(interest, index) in fetchInterest" :key="index">
                <td>{{interest.area}}</td>
                <td><b-button @click="interestDelete(interest.id, interest.dongCode)"> 삭제 </b-button></td>
            </tr>
        </tbody>
    </table>



  </div>
</template>

<script>



export default {

    computed: {
        fetchUser() {
            return this.$store.getters.user;
        },
        fetchInterest() {
          return this.$store.getters.allInterestArea;
        },
    },

    created() {
      this.$store.dispatch("getInterestAreaList", this.fetchUser.id);
    },

    methods: {
      interestDelete(id, dongCode) {
        if(confirm("정말 삭제하시겠습니까?")) {
          const interest = {
            id: id,
            dongCode: dongCode
          }
          this.$store.dispatch('interestDelete', interest);

          this.$store.dispatch("getInterestAreaList", this.fetchUser.id);
        }
      }
    },



    



}
</script>

<style scoped>

</style>