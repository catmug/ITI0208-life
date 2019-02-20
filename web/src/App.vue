<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">
    <expense-input-plain></expense-input-plain>
  </div>
</template>

<script>
import ExpenseInputPlain from "./components/ExpenseInputPlain";

export default {
  name: 'app',
  components: {
      ExpenseInputPlain
  },
  data() {
    return {
      hello: null
    }
  },
  mounted () {
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
