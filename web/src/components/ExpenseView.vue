<template>
    <div>
        <div class="pb-3">
            <b-navbar type="light">
                <b-navbar-nav>
                    <b-navbar-brand href="#">
                        <b-img src="../src/assets/logo.png" alt="Expense Tracker">
                        </b-img></b-navbar-brand>
                </b-navbar-nav>
                <!--<span left>Your goal: {{goal}} </span>-->
                <b-navbar-nav class="ml-auto">
                    <b-nav-item @click="logout">Logout</b-nav-item>
                </b-navbar-nav>
            </b-navbar>
        </div>
        <b-card no-body class="shadow-sm border-0 bg-white">
            <b-tabs lazy pills card vertical nav-wrapper-class="w-25">
                <b-tab title="Add Expense" active><expense-input-plain></expense-input-plain></b-tab>
                <b-tab title="View Expenses" ><expenses-table></expenses-table></b-tab>
                <b-tab title="Add Category"><add-category></add-category></b-tab>
                <b-tab title="Rename Category"><rename-category></rename-category></b-tab>
                <b-tab title="Add Goal"><add-goal></add-goal></b-tab>
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
    import $store from "../store/modules/auth";
    import addGoal from "./AddGoal"
    import axios from "axios";

    export default {
        name: "ExpenseView",
        components: {
            ExpensesTable,
            ExpenseInputPlain,
            RenameCategory,
            AddCategory,
            addGoal
    },
        data() {
            return {
                hello: null,
                goal: 0
            }
        },
        methods: {
            logout: function () {
                this.$store.dispatch(AUTH_LOGOUT).then(() => {
                    this.$router.push("/login");
                })
            }
        },
        beforeUpdate () {
            axios.get("http://localhost:8080/api/goal")
                .then(response => (this.goal = response.data));
        }
    }
</script>

<style scoped>

</style>