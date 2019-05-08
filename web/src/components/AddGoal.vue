<template>
    <b-container fluid id="AddGoal">
        <b-row>
            <b-col>
                <b-form>
                    <b-form-group>
                        <b-form-input
                                type="number"
                                v-model="goal.amount"
                                aria-describedby="amountHelp"
                                placeholder="Amount">
                        </b-form-input>
                    </b-form-group>
                </b-form>
            </b-col>
        </b-row>
        <b-row>
            <b-col>
                <p class="float-left">{{message}}</p>
            </b-col>
            <b-col>
                <b-button class="float-right" @click="send()" variant="outline-primary">Add Goal</b-button>
            </b-col>
        </b-row>
    </b-container>
    <!--<div class="container" id="AddGoal">
        <div class="row">
            <div class="col">

            </div>
            <input type="number" v-model="goal.amount" aria-describedby="amountHelp"
                   placeholder="Amount">
            <b-button @click="send()" variant="outline-primary">Add Goal</b-button>
            <p>{{message}}</p>
        </div>
    </div>-->
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
                axios.post(process.env.VUE_APP_API + '/goal', {month: 1, amount: this.goal.amount})
                    .then(response => (this.success = response.data.success));
            }
        }
    }
</script>

<style scoped>
</style>