<template>
    <b-container lg="12" class="my-5">

        <b-row align-h="center">
            <b-col lg="6">
                <b-form id="login" v-on:submit.prevent="login">
                    <b-form-group>
                        <b-form-input type='text' placeholder='username' v-model='username' required/>
                    </b-form-group>

                    <b-form-group>
                        <b-form-input type='password' placeholder='password' v-model='password' required/>
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
                username: '',
                password: ''
            }
        },
        methods: {
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