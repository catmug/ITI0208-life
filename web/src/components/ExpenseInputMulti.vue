<template>
    <div class="expense-input form-group">
            <input type="number" v-model="expense.amount" aria-describedby="amountHelp" class="form-control"
                   placeholder="Amount" data-cy="expense-amount">
            <small id="amountHelp" class="form-text text-muted">How much you spent</small>
            <input v-model="expense.comment" type="text" class="form-control" placeholder="Comment" data-cy="expense-comment">
            <CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown>
        <button class="btn btn-outline-primary" data-cy="submit" @click="send">save</button>
        <p>{{message}}</p>
    </div>
</template>

<script>
    export default {
        name: "ExpenseInputMulti",
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
                this.message =  'New Expenses have been added';
            },
            getSelectedCategory(e) {
                this.expense.categoryId = e;
            }
        }
    }
</script>

<style scoped>

</style>