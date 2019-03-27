<template>
    <div class="container" id="app">
        <div>
            <router-link to="/login">Login</router-link>
        </div>
        <div>
            <router-link to="/expense">Expense adding</router-link>
        </div>
        <div>
            <router-link to="/register">Register</router-link>
        </div>
        <router-view></router-view>
    </div>
</template>

<script>
    import './style/custom.scss'


    export default {
        name: 'app',
    }

</script>

<style>
    @import "./assets/style-example.module.css";
</style>
