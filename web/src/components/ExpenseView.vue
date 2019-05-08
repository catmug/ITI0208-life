<template>
    <div>
        <div>
            <b-navbar type="light" class="pb-0 ml-0 pl-0">
                <b-navbar-nav class="col-lg-2">
                    <b-navbar-brand href="#" >
                        <img src="../assets/banner.jpg" alt="Expense Tracker" class="img navbar__img">
                    </b-navbar-brand>
                </b-navbar-nav>
                <!--<span left>Your goal: {{goal}} </span>-->
                <b-navbar-nav class="ml-auto align-bottom">
                    <b-nav-item @click="logout"><h6 style="font-size: 17px">Logout</h6></b-nav-item>
                </b-navbar-nav>
            </b-navbar>
        </div>
        <b-card no-body class="shadow-sm border-0 bg-white">
            <b-tabs lazy pills card vertical nav-wrapper-class="w-25">
                <b-tab title="Add Expense" active>
                    <expense-input-plain></expense-input-plain>
                </b-tab>
                <b-tab title="View Expenses">
                    <expenses-table></expenses-table>
                </b-tab>
                <b-tab title="Add Category">
                    <add-category></add-category>
                </b-tab>
                <b-tab title="Rename Category">
                    <rename-category></rename-category>
                </b-tab>
                <b-tab title="Add Goal">
                    <add-goal></add-goal>
                </b-tab>
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
            axios.get(process.env.VUE_APP_API + "/goal")
                .then(response => (this.goal = response.data));
        }
    }
</script>

<style scoped>

</style>