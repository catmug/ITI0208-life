<template>
        <b-container lg="12" class="my-5">
            <b-row align-h="center" class="mb-3">
                <h2>Register</h2>
            </b-row>
            <b-row align-h="center">
                <b-col lg="6">
                    <b-form id="login" v-on:submit.prevent="register">
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

    export default {
        name: 'register',
        data() {
            return {
                username: '',
                password: ''

            }
        },
        methods: {
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