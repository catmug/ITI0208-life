<template>
    <div>
        <div class="form-group">
            <label>Select category to rename:</label>
            <CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown>
        </div>
        <div class="form-group">
            <input class="form-control" v-model="category.name" type="text">
        </div>
        <div class="form-group">
            <b-button @click="editCategory" variant="outline-primary">Rename Category</b-button>
        </div>
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
                    name: ''
                }
            }
        },
        components: {CategoryDropdown},
        methods: {
            editCategory() {
                axios.post('http://localhost:8080/api/category/rename', this.category)
                    .then(response => (this.success = response.data.success));
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