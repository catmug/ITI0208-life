<template>

    <b-container fluid>
        <b-row>
            <b-col>
                <b-form>
                    <b-form-group>
                        <CategoryDropdown @on-change="getSelectedCategory"></CategoryDropdown>
                    </b-form-group>
                    <b-form-group>
                        <b-form-input
                                type="text"
                                v-model="category.name"
                                v-validate="'required|min:3|alpha_num'"
                                name="category name"
                                :class="{ 'is-invalid': submitted && errors.has('category name') }"/>
                        <div
                                v-if="submitted && errors.has('category name')"
                                class="invalid-feedback">
                            <p class="mb-0">{{ errors.first('category name') }}</p>
                        </div>
                    </b-form-group>
                </b-form>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <div>
                    <b-button class="float-right" @click="validateForm" variant="outline-primary">Rename Category</b-button>
                </div>
                <p class="text-success">{{message}}</p>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>

    import CategoryDropdown from "./CategoryDropdown";
    import axios from "axios"

    export default {
        name: "RenameCategory",
        success: false,
        data() {
            return {
                submitted: false,
                message: '',
                category: {
                    categoryId: 0,
                    name: ''
                }
            }
        },
        components: {CategoryDropdown},
        methods: {
            validateForm: function () {
                this.submitted = true;
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.editCategory();
                    }
                });
            },
            editCategory() {
                axios.post(process.env.VUE_APP_API + '/category/rename', this.category)
                    .then(response => (this.message = response.data));
                window.setTimeout(this.closeMsg, 3000);
            },

            getSelectedCategory(e) {
                this.category.categoryId = e;
            },

            closeMsg() {
                document.getElementById("msg").style.display = " none";
                this.submitted = false;
                this.category.categoryId = 0;
                this.category.name = '';
            },
        },
    }
</script>

<style scoped>

</style>