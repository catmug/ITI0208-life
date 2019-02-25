<template>
  <div class="container" id="app">
    <div>
      <b-card no-body>
        <b-tabs pills card vertical nav-wrapper-class="w-25">
          <b-tab title="Add expense" active><expense-input-plain></expense-input-plain></b-tab>
          <b-tab title="View expenses"><expenses-table></expenses-table></b-tab>
          <b-tab title="Settings">Todo settings</b-tab>
        </b-tabs>
      </b-card>
    </div>
  </div>
</template>

<script>
    import ExpenseInputPlain from "./components/ExpenseInputPlain";
    import ExpensesTable from "./components/ExpensesTable";

    export default {
        name: 'app',
        components: {
            ExpensesTable,
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
