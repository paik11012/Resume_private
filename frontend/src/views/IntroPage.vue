<template>
  <div>
    <a href="/"><img src="@/assets/intropng.png" alt="introduction" class="intro"></a>
    </div>
</template>

<script>
export default {

}
</script>

<style>
.intro{
    margin-left: 0;
    width: 99%;
    border-radius:20px;
    margin-top:8vw;
}
@media screen and (max-width:550px) {
  .intro {
    width: 100%;
    margin-left:0;
  }
}
@media screen and (max-width:800px) {
  .intro {
    width: 100%;
    margin-left:0;
    margin-top:17vw;
  }
}
</style>