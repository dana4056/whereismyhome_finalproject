<template>
  <div id="YoutubePage">
    <header-bar></header-bar>
    <h1>Youtube 리스트</h1>

    <!-- <iframe allowfullscreen src="https://www.youtube.com/embed/dOgh5tvbTj0" frameborder="0"></iframe> 
    &nbsp;
    <iframe allowfullscreen src="https://www.youtube.com/embed/dOgh5tvbTj0" frameborder="0"></iframe>
    &nbsp;
    <iframe allowfullscreen src="https://www.youtube.com/embed/dOgh5tvbTj0" frameborder="0"></iframe>
 -->

    <div class="video" v-for="(video, index) in videos.items" :key="index">
      <iframe
        allowfullscreen
        :src="`https://www.youtube.com/embed/${video.id.videoId}`"
        frameborder="0"
      ></iframe>
      <small>{{ video.id.videoId }}</small>
      &nbsp;
    </div>
  </div>
</template>

<script>
import HeaderBar from "@/components/TheHeader";
import http from "@/api/http";

export default {
  data() {
    return {
      search: encodeURI("싸피"),

      videos: [],
    };
  },

  components: {
    HeaderBar,
  },

  created() {
    const url =
      `https://www.googleapis.com/youtube/v3/search?part=snippet&q=` +
      this.search +
      `&key=AIzaSyDAj5c37qKNPkaGj6Dua0OF7_AVkQ6dLI4&maxResults=3&type=video`;
    http.get(url).then(({ data }) => {
      this.videos = data;
      console.log("youtube", this.videos);
    });
  },
};
</script>

<style scoped>
input,
textarea,
.view {
  width: 50%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: medium;
}
</style>