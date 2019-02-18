<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <HelloWorld v-bind:msg="hello"/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import axios from "axios";

export default {
  name: 'app',
  components: {
    HelloWorld
  },
  data() {
    return {
      hello: null
    }
  },
  mounted () {
    axios.get('http://localhost:8080/api').then(response => (this.hello = response.data));
  }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
