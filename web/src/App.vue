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
        <!--<span v-if="isLoggedIn"><a @click="logout()">Logout</a></span>-->
        <router-view></router-view>
    </div>
</template>

<script>
    import './style/custom.scss'


    export default {
        name: 'app',
        /*computed: {
            isLoggedIn: function () {
                return this.$store.getters.isAuthenticated;
            }
        },
        methods: {
            logout: function () {
                this.$store.dispatch("logout").then(() => {
                    this.$router.push("/login");
                })
            }
        }*/
    }

</script>

<style>
    @import "./assets/style-example.module.css";
</style>
