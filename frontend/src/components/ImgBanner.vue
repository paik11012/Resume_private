<template>
  <div>
    <img class="banner" src="@/assets/banner.jpg"/>
  </div>
</template>

<script>
export default {
  name: "ImgBanner",
  props: {
    imgSrc: { type: String },
    text: { type: String }
  },
  methods: {}
};
</script>
<style>
.text-shadow {
  text-shadow: 0 0 15px rgb(255, 255, 255);
}
.banner{
  width:100%;
}
</style>
