<template>
    <div class="container">
        <div class="row pb-1">
            <div class="col-8"><p class="text-danger">{{ errors.first('category name') }}&nbsp; </p></div>
        </div>
        <div class="row pb-1">
            <div class="col-4"><input class="form-control" v-validate.disable="'required|min:3'" name="category name"
                                      v-model="category.name" type="text">
            </div>
        </div>
        <div class="row my-2">
            <div class="col">
                <b-button @click="validateForm" variant="outline-primary">Add category</b-button>
            </div>
        </div>
        <div class="row my-2">
            <div class="col">
                <p class="text-success">{{message}}</p>
            </div>
        </div>
    </div>
</template>

<script>

    import axios from "axios"

    export default {
        name: "AddCategory",
        data() {
            return {
                success: '',
                message: '',
                category: {
                    name: '',
                }
            }
        },
        methods: {
            validateForm() {
                this.$validator.validateAll().then(valid => {
                    if (valid) {
                        this.send()
                    }
                });
            },
            send() {
                let req = axios.post('http://localhost:8080/api/category', this.category)
                    .then(response => (this.success = response.data.success));
                this.message = 'The category ' + this.category.name + ' has been added!';

            }
        }
    }
</script>

<style scoped>

</style>