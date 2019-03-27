<template>
    <div>
        <div class="pb-3">
            <b-navbar type="light" variant="light">
                <b-navbar-nav>
                    <b-navbar-brand href="#">Expense tracker</b-navbar-brand>
                </b-navbar-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-item-dropdown text="User" left>
                        <b-dropdown-item href="#">Settings</b-dropdown-item>
                        <b-dropdown-item href="#" @click="logout">Logout</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-navbar>
        </div>
        <b-card no-body class="shadow-sm border-0 bg-white">
            <b-tabs lazy pills card vertical nav-wrapper-class="w-25">
                <b-tab title="Add expense" active><expense-input-plain></expense-input-plain></b-tab>
                <b-tab title="View expenses" ><expenses-table></expenses-table></b-tab>
                <b-tab title="Add Category"><add-category></add-category></b-tab>
                <b-tab title="Settings"><rename-category></rename-category></b-tab>
            </b-tabs>
        </b-card>
    </div>
</template>

<script>
    import ExpenseInputPlain from "./ExpenseInputPlain";
    import ExpensesTable from "./ExpensesTable";
    import RenameCategory from "./RenameCategory";
    import AddCategory from "./AddCategory";
    import {AUTH_LOGOUT} from "../store/constants";

    export default {
        name: "ExpenseView",
        components: {
            ExpensesTable,
            ExpenseInputPlain,
            RenameCategory,
            AddCategory
    },
        data() {
            return {
                hello: null
            }
        },
        methods: {
            logout: function () {
                this.$store.dispatch(AUTH_LOGOUT).then(() => {
                    this.$router.push("/login");
                })
            }
        },
        mounted () {
        }
    }
</script>

<style scoped>

</style>