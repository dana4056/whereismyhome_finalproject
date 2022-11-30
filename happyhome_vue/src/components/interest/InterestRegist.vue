<template>
  <div id="wrap">
    <div class="modal modal-signin position-static d-block bg-secondary py-5" tabindex="-1" role="dialog" id="modalSignin">
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-5 shadow">
          <div class="modal-header p-5 pb-4 border-bottom-0">
            <!-- <h5 class="modal-title">Modal title</h5> -->
            <h2 class="fw-bold mb-0">관심지역 설정</h2>
          </div>

          <div class="modal-body p-5 pt-0">
            <form class="" action="house" >
            <input type="hidden" name="action" value="interest">
            <input type="hidden" name="userid" value=${loginUser.id }>
              <select class="form-select p-2 mb-1" id="sido" name="sido">
                <option value="">시도선택</option>
              </select>
              <select class="form-select p-2 mb-1" id="gugun" name="gugun">
                <option value="">구군선택</option>
              </select>
              <select class="form-select p-2 mb-3" id="dong" name="dong">
                <option value="">동선택</option>
              </select>
              <input type="submit" value="선택">
              
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>

</style>