<template>
    <div>
        <select @change="onChange" v-model="selected">
            <option v-for="option in categories" :value="option.name">{{ option.name }}</option>
            <!--<option v-for="option in categories" :value="option">{{ option }}</option>-->
        </select>
        <!--<p>{{ categories }}</p>-->
        <p>{{ selected }}</p>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "CategoryDropdown",
        data() {
            return {
                categories: [],
                selected: ''
            }
        },
        methods: {
            get_categories() {
                axios.get('http://localhost:8080/api/expense').then(response => (this.categories = response.data));
            },
            onChange() {
                this.$emit("on-change", this.selected)
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/category').then(response => (this.categories = response.data));
        }
    }
</script>

<style scoped>

</style>