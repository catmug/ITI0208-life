<template>
  <div class="container" id="app">
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
  @import "./assets/style-example.module.css";
</style>
