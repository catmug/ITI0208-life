<template>
    <b-container lg="12" class="mb-5">
        <b-row>
            <b-col>
                <b-navbar type="light" class="pb-0 ml-0 pl-0">
                    <b-navbar-nav class="col-lg-2">
                        <b-navbar-brand href="#" >
                            <img src="../assets/banner.jpg" alt="Expense Tracker" class="img navbar__img">
                        </b-navbar-brand>
                    </b-navbar-nav>
                    <!--<span left>Your goal: {{goal}} </span>-->
                </b-navbar>
            </b-col>
        </b-row>
        <b-row align-h="center" class="mb-3">
            <h2>Login</h2>
        </b-row>
        <b-row align-h="center">
            <b-col lg="6">
                <b-form id="login" v-on:submit.prevent="validateForm">
                    <b-form-group>
                        <b-form-input
                                type='text'
                                placeholder='username'
                                v-model='username'
                                name="username"
                                v-validate="'required'"
                                :class="{ 'is-invalid': submitted && errors.has('username') }"/>
                        <div
                                v-if="submitted && errors.has('username')"
                                class="invalid-feedback">
                            <p class="mb-0">{{ errors.first('username') }}</p>
                        </div>
                    </b-form-group>

                    <b-form-group>
                        <b-form-input
                                type='password'
                                placeholder='password'
                                v-model='password'
                                name="password"
                                v-validate="'required'"
                                :class="{ 'is-invalid': submitted && errors.has('password') }"/>

                        <div
                                v-if="submitted && errors.has('username')"
                                class="invalid-feedback">
                            <p class="mb-0">{{ errors.first('password') }}</p>
                        </div>
                    </b-form-group>

                    <div class="text-center">
                        <b-button class="mb-3" type="submit" variant="primary" block>Sign In</b-button>
                        <router-link to="/register">Sign up?</router-link>
                    </div>
                </b-form>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import {AUTH_REQUEST} from "../store/constants";

    export default {
        name: 'login',
        data() {
            return {
                submitted: false,
                username: '',
                password: '',

            }
        },
        methods: {
            validateForm: function (e) {
                this.submitted = true;
                this.$validator.validate().then(valid => {
                    if (valid) {
                        this.login();
                    }
                });
            },
            login () {
                const { username, password } = this;
                this.$store.dispatch(AUTH_REQUEST, {username, password}).then(() => {
                });
            }
        }
    }
</script>

<style scoped>

</style>