<template>
    <div>
        <select>
            <option v-for="option in categories" :value="option">{{ option["name"] }}</option>
        </select>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "CategoryDropdown",
        data() {
            return {
                categories: []
            }
        },
        methods: {
            get_categories() {
                axios.get('http://localhost:8080/api/expense').then(response => (this.categories = response.data));
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/category').then(response => (this.categories = response.data));
        }
    }
</script>

<style scoped>

</style>