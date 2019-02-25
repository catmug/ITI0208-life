<template>
    <div>
        <b-table striped hover :items="expenses" :fields="fields"/>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "ExpensesTable",
        data() {
            return {
                fields: {
                    amount: {
                        label: 'Amount',
                        sortable: true
                    },
                    insertTime: {
                        label: 'Time added',
                        sortable: true
                    }
                },
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