<template>
    <div id="login">
        <h1 class="my-2">Login</h1>
        <input type='text' class="text-field" placeholder='username' v-model='username'>
        <input type='password' class="text-field ml-1" placeholder='password' v-model='password'>
        <button class="btn-primary ml-1" v-on:click="login">Submit</button>
    </div>
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