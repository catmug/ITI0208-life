<template>
    <div>
        <b-table
                striped
                hover
                :items="expenses"
                :fields="fields"
        >
            <template slot="actions" slot-scope="row">
                <b-button size="sm" @click="info(row.item, row.index, $event.target)">
                    Edit
                </b-button>
            </template>
        </b-table>
        <b-modal id="modalInfo" @hide="resetModal" :title="modalInfo.title" ok-only>
            <pre>{{ modalInfo.content }}</pre>
        </b-modal>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "ExpensesTable",
        data() {
            return {
                fields: [
                    {key: 'amount', label: 'Amount', sortable: true, sortDirection: 'desc'},
                    {key: 'insertTime', label: 'Inseriton time', sortable: true, class: 'text-center'},
                    {key: 'actions', label: ''}
                ],
                expenses: [],
                modalInfo: {title: '', content: ''}

            }
        },
        methods: {
            info(item, index, button) {
                var coolBeans = JSON.stringify(item);
                console.log(coolBeans)
                this.modalInfo.title = item.comment;
                this.modalInfo.content = JSON.stringify(item, null, 2);
                this.$root.$emit('bv::show::modal', 'modalInfo', button)
            },
            resetModal() {
                this.modalInfo.title = '';
                this.modalInfo.content = '';
            },
        },
        mounted() {
            axios.get('http://localhost:8080/api/expense').then(response => (this.expenses = response.data));
        }
    }
</script>

<style scoped>

</style>