<template>
    <div>
        <select>
            tere
            <option v-for="(option, name) in options" :value="option">{{ name }}</option>
        </select>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: "CategoryDropdown",
        props: {
            options: {
                type: Object,
                required: true
            }
        }
    }
</script>

<style scoped>

</style>