<template>
    <div class="container" id="AddGoal">
        <div class="row">
            <input type="number" v-model="goal.amount" aria-describedby="amountHelp"
                   placeholder="Amount">
            <b-button @click="send()" variant="outline-primary">Add Goal</b-button>
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
                axios.post('http://localhost:8080/api/goal', {month: 1, amount: this.goal.amount})
                    .then(response => (this.success = response.data.success ));
            }
        }
    }
</script>

<style scoped>

</style>