<template>
    <div>
        <b-table striped hover :items="expenses" />
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "ExpensesTable",
        data() {
            return {
                expenses: []
            }
        },
        mounted () {
            axios.get('http://localhost:8080/api/expense').then(response=> (this.expenses = response.data));
        }
    }
</script>

<style scoped>

</style>