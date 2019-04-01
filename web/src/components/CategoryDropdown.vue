<template>
    <select class="form-control form-select-button" @change="onChange" v-model="selected">
        <option value="" disabled selected hidden>Choose category</option>
        <option v-for="option in categories" :value="option.categoryId" :key="option.categoryId">{{ option.name }}</option>
    </select>
</template>

<script>
    import axios from "axios";

    export default {
        name: "CategoryDropdown",
        data() {
            return {
                categories: [],
                selected: ""
            }
        },
        methods: {
            onChange() {
                this.$emit("on-change", this.selected);
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/category').then(response => (this.categories = response.data));
        }
    }
</script>

<style scoped>

</style>