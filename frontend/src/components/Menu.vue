<template>
  <v-layout align-center justify-center fill-height @click="dis" oncontextmenu="return false">
    <transition name="modal">
      <div class="modal-mask">
        <div class="modal-wrapper">
          <div class="modal-container">
            <p class="title" style="margin-bottom:0px;">제목</p>
            <hr>
            <div class="modal-header" style="padding:0px;">
              <p>페이지</p>
              <p>페이지</p>
            </div>
          </div>
        </div>
      </div>
    </transition>
  </v-layout>

</template>

<script>
/* eslint-disable */
export default {
  methods:{
    dis(){
      this.$emit('dis')
    }
  }
}
</script>

<style>
/* .modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
} */

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  width: 100px;
  margin: 0px auto;
  padding: 1px 30px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, .33);
  transition: all .3s ease;
  font-family: Helvetica, Arial, sans-serif;
}

.modal-header > p{
  margin-top: 30px;
  margin-bottom: 30px;
}
.titlebox {
  width:100%;
}
.bodybox{
  width: 100%;
  max-height: 40vh;
  overflow: visible;

}
.modal-body {
  margin: 0;
  margin-top:20px;
  margin-bottom:5px;
  border:none;
}


/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>