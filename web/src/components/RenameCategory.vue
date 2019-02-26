<template>
    <div>
        <p>Select category to rename:</p>
        <CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown>
        <p></p>
        <p>Rename category:</p>
        <input v-model="category.name" type="text">
        <p></p>
        <b-button @click="editCategory" variant="outline-primary">Rename Category</b-button>
        <p></p>
        <p>{{message}}</p>



    </div>
</template>

<script>

    import CategoryDropdown from "./CategoryDropdown";
    import axios from "axios"

    export default {
        name: "RenameCategory",
        success: false,
        data() {
            return {
                message: '',
                category: {
                    categoryId: 0,
                    name: '',
                    userId: 1
                },

            }
        },
        components: {CategoryDropdown},
        methods: {
            editCategory() {
                axios.post('http://localhost:8080/api/category/rename', this.category)
                    .then(response => (this.success = true));
                this.message = 'Category updated to ' + this.category.name
            },

            getSelectedCategory(e) {
                this.category.categoryId = e;
            }
        },
    }
</script>

<style scoped>

</style>