<template>
  <div id="AppUser">
    <header-bar></header-bar>
    <router-view />
  </div>
</template>

<script>
import HeaderBar from "@/components/TheHeader";

export default {
  components: {
    HeaderBar,
  },
};
</script>

<style scoped>
</style>