<template>
    <div class="container" id="AddGoal">
        <div class="row">
            <input type="number" v-model="goal.amount" aria-describedby="amountHelp"
                   placeholder="Amount">
            <b-button @click="send()" variant="outline-primary">Add Goal</b-button>
            <p>{{message}}</p>
        </div>
        <p></p>
        <div class="row">
            <p>This months goal is {{ setGoal }}</p>
        </div>
        <div class="row">
            <p>You have left to spend {{ setGoal - leftToSpend }}</p>
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
                },
                setGoal: {
                    name: '',
                    amount: 0
                },
                leftToSpend: {
                    amount: 0
                }
            }
        },
        methods: {
            send() {
                axios.post(process.env.VUE_APP_API + '/goal', {month: 1, amount: this.goal.amount})
                    .then(response => (this.success = response.data.success, this.getGoal(), this.getLeftToSpend() ));
            },
            getGoal() {
                axios.get(process.env.VUE_APP_API + '/goal').then(response => (
                    // console.log(response.data);
                    this.setGoal = response.data));
            },
            getLeftToSpend() {
                axios.get(process.env.VUE_APP_API + '/goalleft').then(response => (this.leftToSpend = response.data));
            }

        },
        mounted() {

            axios.get(process.env.VUE_APP_API + '/goal').then(response => (
                this.setGoal = response.data));
            axios.get(process.env.VUE_APP_API + '/goalleft').then(response => (this.leftToSpend = response.data));

        }
    }
</script>

<style scoped>
</style>