<template>
    <div>
        <category-dropdown @on-change="getExpensesByCategory"></category-dropdown>
        <b-table
                striped
                hover
                responsive
                :items="expenses"
                :fields="fields"
        >
            <template slot="actions" slot-scope="row">
                <b-button size="sm" @click="info(row.item, row.index, $event.target)">
                    Edit
                </b-button>
                <b-button size="sm" @click="deleteExpense(row.item)">
                    Remove
                </b-button>
            </template>
        </b-table>
        <b-modal id="modalInfo" @hide="resetModal" :title="modalInfo.title" hide-footer>
                <!--<div>-->
                <div class="form-group">
                    <input type="number" v-model="modalInfo.amount" aria-describedby="amountHelp" class="form-control"
                           placeholder="Amount">
                    <small id="amountHelp" class="form-text text-muted">How much you spent</small>
                </div>
                <div class="form-group">

                    <input v-model="modalInfo.comment" type="text" class="form-control" placeholder="Comment">

                </div>
                <div class="form-group">
                    <CategoryDropdown class="form-control" @on-change="getSelectedCategory"></CategoryDropdown>
                </div>
                <!--<button class="btn btn-primary" @click="send">save</button>-->
                <b-button class="mt-3" variant="success" block @click="send">Save</b-button>

                <!--<input v-model="modalInfo.amount">-->
                    <!--<CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown>-->
                    <!--<input v-model="modalInfo.comment" type="text">-->
                    <!--<button @click="send">save</button>-->
                <!--</div>-->

        </b-modal>
    </div>
</template>

<script>
    import axios from "axios";
    import CategoryDropdown from "./CategoryDropdown";

    export default {
        name: "ExpensesTable",
        components: {
            CategoryDropdown,
        },
        data() {
            return {
                fields: [
                    {key: 'amount', label: 'Amount', sortable: true, sortDirection: 'desc'},
                    {key: 'insertTime', label: 'Inseriton time', sortable: true, class: 'text-center'},
                    {key: 'actions', label: ''}
                ],
                expenses: [],
                modalInfo: {title: '', content: ''},
                category: {
                    categoryId: 0,
                }
            }
        },
        methods: {
            info(item, index, button) {
                this.modalInfo.title = item.comment;
                this.modalInfo.content = JSON.stringify(item, null, 2);
                this.modalInfo.expenseId = item.expenseId;
                this.modalInfo.amount = item.amount;
                this.modalInfo.category = item.category;
                this.modalInfo.comment= item.comment;

                this.$root.$emit('bv::show::modal', 'modalInfo', button)
            },
            resetModal() {
                this.modalInfo.title = '';
                this.modalInfo.content = '';
                this.modalInfo.id = '';
                this.modalInfo.amount = '';
                this.modalInfo.category = '';
                this.modalInfo.comment= '';

            },
            getSelectedCategory(e) {
                this.modalInfo.category = e;
                this.category.categoryId = e;
            },
            send() {
                axios.post('http://localhost:8080/api/expense/edit',
                    this.modalInfo
                ).then(response => (this.success = response.data,
                                            this.updateTable()));
            },
            updateTable() {
                axios.get('http://localhost:8080/api/expense')
                    .then(response => (this.expenses = response.data));
            },
            deleteExpense(item) {
                axios.delete("http://localhost:8080/api/expense/" + item.expenseId)
                    .then(response => (this.success = response.data.success,
                                            this.updateTable()));
            },
            getExpensesByCategory(id) {
                axios.get("http://localhost:8080/api/expense/" + id)
                    .then(response => (this.expenses = response.data));
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/expense').then(response => (this.expenses = response.data));
        }
    }
</script>

<style scoped>

</style>