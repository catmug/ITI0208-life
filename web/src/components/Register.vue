<template>
    <b-container lg="12" class="mb-5">
        <b-row>
            <b-col>
                <b-navbar type="light" class="pb-0 ml-0 pl-0">
                    <b-navbar-nav class="col-lg-2">
                        <b-navbar-brand href="#">
                            <img src="../assets/banner.jpg" alt="Expense Tracker" class="img navbar__img">
                        </b-navbar-brand>
                    </b-navbar-nav>
                    <!--<span left>Your goal: {{goal}} </span>-->
                    <b-navbar-nav class="ml-auto align-bottom">
                        <router-link to="/login">
                            <h6 style="font-size: 17px">Log In</h6>
                        </router-link>
                    </b-navbar-nav>
                </b-navbar>
            </b-col>
        </b-row>
        <b-row align-h="center" class="mb-3">
            <h2>Register</h2>
        </b-row>
        <b-row align-h="center">
            <b-col lg="6">
                <b-form v-on:submit.prevent="validateForm">
                    <b-form-group>
                        <b-form-input
                                type='text'
                                v-validate="'required|alpha_num|min:4|max:30'"
                                placeholder='username' name="username" id="username"
                                v-model='username'
                                :class="{ 'is-invalid': submitted && errors.has('username') }"/>
                        <div
                                v-if="submitted && errors.has('username')"
                                class="invalid-feedback"><p class="mb-0">{{ errors.first('username') }}</p></div>
                        <div v-if="submitted">
                            <p>{{message}}</p>
                        </div>

                    </b-form-group>

                    <b-form-group>
                        <b-form-input
                                type='password'
                                v-validate="'required|min:8|max:20'"
                                id="password" name="password" placeholder='password'
                                v-model='password' :class="{ 'is-invalid': submitted && errors.has('password') }"/>
                        <div
                                v-if="submitted && errors.has('password')"
                                class="invalid-feedback"><p>{{ errors.first('password') }}</p></div>
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
                submitted: false,
                username: '',
                password: '',
                message: ''
            }
        },
        methods: {
            validateForm: function (e) {
                this.submitted = true;
                this.$validator.validate().then(valid => {

                    axios.post(process.env.VUE_APP_API + '/user/exists/' + this.username).
                    then(resp => {
                        this.message = resp.data;
                        if (valid && this.message.length === 0) {
                            this.register();
                        }
                    });


                });
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