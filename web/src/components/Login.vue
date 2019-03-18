<template>
    <form id="login" v-on:submit.prevent="login">
        <input type='text' placeholder='username' v-model='username'>
        <input type='password' placeholder='password' v-model='password'>
        <button class="ui primary button">Submit</button>
    </form>
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
                    this.router.push('/');
                });
            }
        }
    }
</script>

<style scoped>

</style>