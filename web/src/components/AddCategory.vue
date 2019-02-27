<template>
    <div>
        <input v-model="category.name" type="text">
        <p></p>
        <b-button @click="send()" variant="outline-primary">Add category</b-button>
        <p>{{message}}</p>
    </div>
</template>

<script>

    import axios from "axios";

    export default {
        name: "AddCategory",
        data() {
            return {
                message: '',
                category: {
                    name: '',
                    userId: 1
                }
            }
        },
        methods: {
            send() {
                axios.post('http://localhost:8080/api/category', this.category)
                    .then(response => (this.success = response.data));
                this.message = 'The category ' + this.category.name + ' has been added!'
            }
        }
    }
</script>

<style scoped>

</style>