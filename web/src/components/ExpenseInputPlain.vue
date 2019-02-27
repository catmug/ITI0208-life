<template>
    <span>
        <input v-model="expense.amount">
        <p></p>
        <CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown>
        <p></p>
        <input v-model="expense.comment" type="text">
        <p></p>
        <button @click="send">save</button>
    </span>
</template>

<script>
    import axios from "axios";
    import CategoryDropdown from "./CategoryDropdown";

    export default {
        name: "ExpenseInputPlain",
        components: {CategoryDropdown},
        data: function () {
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
            },
            getSelectedCategory(e) {
                this.expense.category = e;
            }
        }
    }
</script>

<style scoped>

</style>