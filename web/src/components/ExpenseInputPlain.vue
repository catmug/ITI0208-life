<template>
    <div id="greeting">
        <p>
            Hello {{username}}
        </p>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "ExpenseInputPlain",
        data: function () {
            return {
                username: ' '
            }
        },
        methods: {
            user: function () {
                return axios.get('http://localhost:8080/api/user').then(response => (this.username = response.data));
            }
        },
        mounted() {
            axios.get('http://localhost:8080/api/user').then(response => (this.username = response.data.username));
        }
    }

</script>

<style scoped>

</style>