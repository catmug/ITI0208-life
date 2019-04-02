<template>
    <div id="login">
        <h1 class="my-2">Login</h1>
        <input type='text' placeholder='username' v-model='username'>
        <input type='password' placeholder='password' v-model='password'>
        <button type="submit" class="ui primary button" v-on:click="login">Submit</button>
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
                    this.$router.push('/expense');
                });
            }
        }
    }
</script>

<style scoped>

</style>