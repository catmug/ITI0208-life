<template>
    <div>
        <b-button @click="updateTable">All</b-button>
        <b-button @click="getLastWeekExpenses">Last week</b-button>
        <b-button @click="getLastMonthExpenses">Last month</b-button>
        <label class="text-info ml-1"> Sort by category</label>
        <category-dropdown @on-change="getExpensesByCategory"></category-dropdown>
<!--        <date-picker v-model="dates" @change="testDate()" type="date" range lang="en" format="YYYY-MM-DD" confirm></date-picker>-->
<!--        <datepicker v-model="temp">Choose a date</datepicker>-->
        <p>{{ temp }}</p>
<!--        <p>{{ dates }}</p>-->
        <b-table
                id="expenseTable"
                striped
                hover
                responsive
                :items="expenses"
                :fields="fields"
                :per-page="perPage"
                :current-page="currentPage"
                small
        >
            <template slot="actions" slot-scope="row">
                <b-button size="sm" @click="info(row.item, row.index, $event.target)">
                    Edit
                </b-button>
                <b-button size="sm" class="ml-1" @click="deleteExpense(row.item)">
                    Remove
                </b-button>
            </template>
        </b-table>
        <b-pagination
                v-model="currentPage"
                :total-rows="rows"
                :per-page="perPage"
                aria-controls="expenseTable"
        />
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
            <p>{{message}}</p>

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
    import DatePicker from 'vue2-datepicker'
    import Datepicker from 'vuejs-datepicker';
    import VCalendar from "v-calendar/src/components/Calendar";

    export default {
        name: "ExpensesTable",
        components: {
            VCalendar,
            CategoryDropdown,
            DatePicker,
            Datepicker
        },
        data() {
            return {
                fields: [
                    {key: 'amount', label: 'Amount', sortable: true, sortDirection: 'desc'},
                    {key: 'insertTime', label: 'Insertion time', sortable: true, class: 'text-center'},
                    {key: 'actions', label: ''}
                ],
                expenses: [],
                modalInfo: {title: '', content: ''},
                category: {
                    categoryId: 0,
                },
                message: '',
                perPage: 10,
                currentPage: 1,
                dates: [],
                temp: null
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
                this.message = "The expense has been updated!"
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
                axios.get('http://localhost:8080/api/expense/' + id)
                    .then(response => (this.expenses = response.data));
            },
            getLastWeekExpenses() {
                axios.get('http://localhost:8080/api/expense/week')
                    .then(response => (this.expenses = response.data));
            },
            getLastMonthExpenses() {
                axios.get('http://localhost:8080/api/expense/month')
                    .then(response => (this.expenses = response.data));
            },
            testDate() {
                console.log(this.dates);
                axios.get('http://localhost:8080/api/expense/custom', {
                    params: {
                        start: this.dates[0],
                        end: this.dates[1]
                    }
                }).
                then(response => (this.expenses = response.data));
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/expense').then(response => (this.expenses = response.data));
        },
        computed: {
            rows() {
                return this.expenses.length
            }
        }
    }
</script>

<style scoped>

</style>