<template>
    <b-container lg="12" class="my-5">
        <b-row align-h="center" class="mb-3">
            <h2>Register</h2>
        </b-row>
        <b-row align-h="center">
            <p v-if="errors.length">
                <b>Please correct the following error(s):</b>
            <ul>
                <li v-for="error in errors" v-bind:key="error">{{ error }}</li>
            </ul>
            </p>
        </b-row>
        <b-row align-h="center">
            <b-col lg="6">
                <b-form id="login" v-on:submit.prevent="checkForm">
                    <b-form-group>
                        <b-form-input type='text' placeholder='username' v-model='username' required/>
                    </b-form-group>

                    <b-form-group>
                        <b-form-input type='password' placeholder='password' v-model='password' required/>
                    </b-form-group>

                    <div class="text-center">
                        <b-button class="mb-3" type="submit" variant="primary" block>Register</b-button>
                    </div>
                </b-form>
            </b-col>
        </b-row>
    </b-container>
</template>


<script>
    import {AUTH_REGISTER} from "../store/constants";
    import axios from "axios";

    export default {
        name: 'register',
        data() {
            return {
                errors: [],
                username: '',
                password: ''

            }
        },
        methods: {
            checkForm: function (e) {
                this.errors = [];

                if (!this.password) {
                    this.errors.push('Password required.');
                } else if (this.password.length < 6 || this.password.length > 20) {
                    this.errors.push('The password should be between 6 to 20 characters.')
                }

                if (!this.username) {
                    this.errors.push("Username required.");
                } else if (this.username.match("^\w+$")) {
                    this.errors.push('The username may only include letters and digits.')
                } else {
                    axios.get('http://localhost:8080/api/user/exists/' + this.username).then(response => {
                        if (response.data === true) {
                            this.errors.push('The username is already taken!')
                        }
                    });
                }

                if (this.errors.length === 0) {
                    this.register();
                }

                e.preventDefault();
            },
            register() {
                const {username, password} = this;
                this.$store.dispatch(AUTH_REGISTER, {username, password}).then(() => {
                    this.$router.push('/login');
                });
            }
        }
    }
</script>

<style scoped>

</style>