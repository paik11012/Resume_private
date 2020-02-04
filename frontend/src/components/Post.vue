<template>
  <v-layout py-4 h-100>
    <v-flex row>
      <div class="caption">{{formatedDate}}</div>
      <h2 class="color-333 headline font-weight-light" id="posttitle">{{title}}</h2>
      <p class="mb-1 color-666 font-weight-light subheading" id="postbody">{{body}}</p>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
	name: 'Post',
	props: {
		date: {type: Date},
		title: {type: String},
		body: {type: String}
	},
  computed: {
		formatedDate() {
			return `${this.date.getFullYear()}년 ${this.date.getMonth()+1}월 ${this.date.getDate()}일`
    }
  }
}
</script>
<style>
  .color-666 {
    color: #666;
  }
  .color-333 {
    color: #333;
  }
  .h-100 {
    height: 100%;
  }
  @media screen and (max-width: 450px) {
  #bann {
    font-size: 20px;
  }
}
#posttitle{
display: -webkit-box;
-webkit-line-clamp: 1;
-webkit-box-orient: vertical;
overflow: hidden;
}
#postbody{
display: -webkit-box;
-webkit-line-clamp: 4;
-webkit-box-orient: vertical;
overflow: hidden;
}
</style>

