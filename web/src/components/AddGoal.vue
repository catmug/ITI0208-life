<template>
    <div class="container" id="AddGoal">
        <div class="row">
            <b-button @click="send()" variant="outline-primary">Add category</b-button>
            <p>{{message}}</p>
        </div>
    </div>
</template>

<script>

    import axios from "axios";

    export default {
        name: "AddGoal",
        data() {
            return {
                success: '',
                message: '',
                months: [
                    'Monday',
                    'Tuesday',
                    'Wednesday',
                    'Thursday',
                    'Friday',
                    'Saturday',
                    'Sunday'
                ],
                goal: {
                    name: '',
                    amount: 0
                }
            }
        },
        methods: {
            send() {
                axios.post('http://localhost:8080/api/goal', {month: 1, amount: 10})
                    .then(response => (this.success = response.data.success));
            }
        }
    }
</script>

<style scoped>

</style>