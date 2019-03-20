<template>
    <div class="container">
        <div class="row pb-1">
            <input class="form-control" v-model="category.name" type="text">
        </div>
        <div class="row">
            <b-button @click="send()" variant="outline-primary">Add category</b-button>
            <p>{{message}}</p>
        </div>
    </div>
</template>

<script>

    import axios from "axios";

    export default {
        name: "AddCategory",
        data() {
            return {
                success: '',
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
                    .then(response => (this.success = response.data.success));
                this.message = 'The category ' + this.category.name + ' has been added!'
            }
        }
    }
</script>

<style scoped>

</style>