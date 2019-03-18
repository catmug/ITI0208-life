<template>
    <span>
        <select @change="onChange" v-model="selected">
            <option v-for="option in categories" :value="option" :key="option.categoryId">{{ option.name }}</option>
        </select>
    </span>
</template>

<script>
    import axios from "axios";

    export default {
        name: "CategoryDropdown",
        data() {
            return {
                categories: [],
                selected: 0
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