<template>
    <b-container fluid>
        <b-row>
            <b-col>
                <b-form>
                    <b-form-group>
                        <b-form-input
                                type="text"
                                placeholder="Category name"
                                v-model="category.name"
                                v-validate="'required|min:3|alpha_num'"
                                name="category name"
                                :class="{ 'is-invalid': submitted && errors.has('category name') }"
                        />
                    </b-form-group>
                    <div v-if="submitted && errors.has('category name')">
                        <p class="text-danger">{{ errors.first('category name') }}</p>
                    </div>
                </b-form>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <p class="text-success" id="msg">{{message}} </p>
            </b-col>
            <b-col class="justify-content-end">
                <b-button class="float-right" @click="validateForm" variant="outline-primary">Add category</b-button>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>

    import axios from "axios"

    export default {
        name: "AddCategory",
        data() {
            return {
                submitted: false,
                success: '',
                message: '',
                category: {
                    name: '',
                }
            }
        },
        methods: {
            validateForm() {
                this.submitted = true;
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
                window.setTimeout(this.closeMsg, 3000);

            },

            closeMsg() {
                document.getElementById("msg").style.display = " none";
                this.submitted = false;
                this.category.name = '';
            },


    }
    }
</script>

<style scoped>

</style>