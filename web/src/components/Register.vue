<template>
    <div>
        <h2>Register</h2>
        <form id="register" v-on:submit.prevent="register">
            <input type='text' placeholder='username' v-model='username'>
            <input type='password' placeholder='password' v-model='password'>
            <button class="ui primary button">Register</button>
        </form>
    </div>
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