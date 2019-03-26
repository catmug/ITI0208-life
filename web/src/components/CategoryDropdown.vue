<template>
    <select class="form-control form-select-button" @change="onChange" v-model="selected">
    <option value="" disabled selected hidden>Please Choose</option>
    <option v-for="option in categories" :value="option.categoryId" :key="option.categoryId">{{ option.name }}</option>
    </select>
    <!--<div class="form-control form-select-button">-->
        <!--<multiselect-->
                <!--v-model="selected"-->
                <!--:options="categories"-->
                <!--track-by="categoryId"-->
                <!--label="name"-->
                <!--:searchable="false"-->
                <!--:close-on-select="true"-->
                <!--:show-labels="false"-->
                <!--@select="onChange"-->
                <!--placeholder="Pick a value">-->
        <!--</multiselect>-->
    <!--</div>-->
</template>

<script>
    import axios from "axios";
    import Multiselect from 'vue-multiselect'

    export default {
        name: "CategoryDropdown",
        components: {
            Multiselect
        },
        data() {
            return {
                categories: [],
                selected: 1
            }
        },
        methods: {
            onChange() {
                this.$emit("on-change", this.selected);
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/category').then(response => (this.categories = response.data));
            // npm install vue-multiselect --save
        }
    }
</script>

<style scoped>

</style>