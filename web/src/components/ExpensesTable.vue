<template>
    <div class="container">
        <div>
            <b-card no-body style="border: none">
                <b-tabs card>
                    <b-tab title="All" @click="updateTable" active></b-tab>
                    <b-tab title="By Category">
                        <b-card-text>
                            <category-dropdown @on-change="getExpensesByCategory"></category-dropdown>
                        </b-card-text>
                    </b-tab>
                    <b-tab title="By Period">
                        <b-card-text>
                            <div class="container">
                                <div class="row justify-content-center">

                            <v-date-picker
                                    mode='range'
                                    tint-color='#f142f4'
                                    v-model='selectedDate'
                                    :theme-styles='themeStyles'
                                    is-double-paned
                                    is-inline
                            >
                            </v-date-picker>
                            
                                </div>
                                <div class="row justify-content-center">
                                    <b-button class="float-righ my-3" @click="getByTimePeriod" variant="outline-primary">Submit</b-button>
                                </div>
                            </div>
                        </b-card-text>
                    </b-tab>
                </b-tabs>
            </b-card>
        </div>

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
                <b-button variant="primary" size="sm" class="icon--purple" @click="info(row.item, row.index, $event.target)">
                    <font-awesome-icon :icon="{ prefix: 'fas', iconName: 'edit'}" style="color: white"/>
                </b-button>
                <b-button size="sm" class="ml-1 btn-danger" @click="deleteExpense(row.item)">
                    <font-awesome-icon :icon="{ prefix: 'fas', iconName: 'trash'}" />
                </b-button>
            </template>
        </b-table>
        <b-pagination
                v-model="currentPage"
                :total-rows="rows"
                :per-page="perPage"
                aria-controls="expenseTable"
                class="justify-content-center"
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
                <CategoryDropdown :selectedCategory=modalInfo.category class="form-control"
                                  @on-change="getSelectedCategory"></CategoryDropdown>
            </div>
            <p class="text-success">{{message}}</p>
            <b-button class="mt-3" variant="primary" block @click="send">Save</b-button>


        </b-modal>
    </div>
</template>

<script>
    import axios from "axios";
    import CategoryDropdown from "./CategoryDropdown";
    import DatePicker from 'vue2-datepicker'
    import Datepicker from 'vuejs-datepicker';
    export default {
        name: "ExpensesTable",
        components: {
            CategoryDropdown,
        },
        data() {
            return {
                fields: [
                    {key: 'amount', label: 'Amount', sortable: true, sortDirection: 'desc'},
                    {key: 'insertTime', label: 'Insertion time', sortable: true, class: 'text-center'},
                    {key: 'categoryName', label: 'Category', sortable: true},
                    {key: 'actions', label: '', class: 'text-center'}
                ],
                expenses: [],
                modalInfo: {title: '', content: ''},
                category: {
                    categoryId: 0,
                },
                message: '',
                perPage: 5,
                currentPage: 1,
                selectedDate: null
            }
        },
        methods: {
            info(item, index, button) {
                this.modalInfo.title = item.comment;
                this.modalInfo.content = JSON.stringify(item, null, 2);
                this.modalInfo.expenseId = item.expenseId;
                this.modalInfo.amount = item.amount;
                this.modalInfo.categoryId = item.category;
                this.modalInfo.comment = item.comment;
                this.$root.$emit('bv::show::modal', 'modalInfo', button)
            },
            resetModal() {
                this.modalInfo.title = '';
                this.modalInfo.content = '';
                this.modalInfo.id = '';
                this.modalInfo.amount = '';
                this.modalInfo.categoryId = '';
                this.modalInfo.comment = '';
                this.message = '';
            },
            getSelectedCategory(e) {
                this.modalInfo.categoryId = e;
                this.category.categoryId = e;
            },
            send() {
                axios.post(process.env.VUE_APP_API + '/expense/edit',
                    this.modalInfo
                ).then(response => (this.success = response.data,
                    this.updateTable()));
                this.message = "The expense has been updated!"
            },
            updateTable() {
                axios.get(process.env.VUE_APP_API + '/expense')
                    .then(response => (this.expenses = response.data));
            },
            deleteExpense(item) {
                axios.delete(process.env.VUE_APP_API + "/expense/" + item.expenseId)
                    .then(response => (this.success = response.data.success,
                        this.updateTable()));
            },
            getExpensesByCategory(id) {
                axios.get(process.env.VUE_APP_API + "/expense/" + id)
                    .then(response => (this.expenses = response.data));
            },
            getByTimePeriod() {
                axios.get(process.env.VUE_APP_API + "/expense/period", {
                    params: {
                        start: this.selectedDate.start,
                        end: this.selectedDate.end
                    }
                })
                    .then(response => (this.expenses = response.data));
            }
        },
        mounted() {
            axios.get(process.env.VUE_APP_API + '/expense').then(response => (this.expenses = response.data));
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