<template>
    <div>
        <input v-model="expense.amount">
        <button @click="send">save</button>
        <p>
            {{all}}
        </p>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "ExpenseInputPlain",
        data: function() {
            return {
                all: {},
                expense: {
                    amount: 0,
                    comment: '',
                    category: 0
                }
            }
        },
        methods: {
            send() {
                axios.post('http://localhost:8080/api/expense',
                    this.expense
                ).then(response => (this.success = response.data));
                axios.get('http://localhost:8080/api/expense').then(response=> (this.all = response.data));
            }
        }
    }
</script>

<style scoped>

</style>