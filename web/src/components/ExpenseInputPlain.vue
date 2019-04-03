<template>
    <div class="expense-input">
        <div class="form-group">
            <input type="number" v-model="expense.amount" aria-describedby="amountHelp" class="form-control"
                   placeholder="Amount" data-cy="expense-amount">
            <small id="amountHelp" class="form-text text-muted">How much you spent</small>
        </div>
        <div class="form-group">

            <input v-model="expense.comment" type="text" class="form-control" placeholder="Comment" data-cy="expense-comment">

        </div>
        <div class="form-group">
            <CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown>
        </div>
        <button class="btn btn-outline-primary" data-cy="submit" @click="send">save</button>
        <p>{{message}}</p>
    </div>

    <!--<div class="container">-->
    <!--<div class="row pb-1">-->
    <!--<input v-model="expense.amount" placeholder="Enter email"></div>-->
    <!--<div class="row pb-1">-->
    <!--<CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown></div>-->
    <!--<div class="row pb-1">-->
    <!--<input v-model="expense.comment" type="text">-->
    <!--</div>-->
    <!--<div class="row pb-1">-->
    <!--<button @click="send">save</button>-->
    <!--</div>-->
    <!--</div>-->
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
                    categoryId: 0
                },
                message: ''
            }
        },
        methods: {
            send() {
                axios.post('http://localhost:8080/api/expense',
                    this.expense
                ).then(response => (this.success = response.data));
                this.message =  'New Expense has been added';
            },
            getSelectedCategory(e) {
                this.expense.categoryId = e;
            }
        }
    }
</script>

<style scoped>

</style>