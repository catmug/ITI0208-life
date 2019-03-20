<template>
    <div>
        <b-card no-body>
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
        mounted () {
        }
    }
</script>

<style scoped>

</style>