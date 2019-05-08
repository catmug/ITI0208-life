<template>

    <div>
        <div class="form-group">
            <input type="number" v-model="expense.amount" aria-describedby="amountHelp" class="form-control"
                   placeholder="Amount" data-cy="expense-amount" min="0">
            <small id="amountHelp" class="form-text text-muted">How much you spent*</small>
        </div>
        <div class="form-group">

            <input v-model="expense.comment" type="text" class="form-control" placeholder="Comment"
                   data-cy="expense-comment">

        </div>
        <div class="form-group">
            <CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown>
        </div>

        <button class="btn btn-outline-primary float-right mb-4" data-cy="submit" @click="send">Save</button>
        <p class="float-left text-success" id="msg">{{message}}</p>
    </div>

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
                axios.post(process.env.VUE_APP_API + '/expense', this.expense
                ).then(response => (this.success = response.data));
                this.message = 'New Expense has been added';
                window.setTimeout(this.closeMsg, 3000);
            },
            getSelectedCategory(e) {
                this.expense.categoryId = e;
            },
            closeMsg() {
                document.getElementById("msg").style.display = " none";
                this.submitted = false;
                this.expense.amount = 0;
                this.expense.comment = '';
                this.expense.categoryId = 0
            },
        }
    }
</script>

<style scoped>

</style>