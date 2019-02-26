<template>
    <div>
        <CategoryDropdown></CategoryDropdown>
        <p></p>
        <b-button variant="outline-primary">Edit</b-button>
    </div>
</template>

<script>

    import CategoryDropdown from "./CategoryDropdown";

    export default {
        name: "RenameCategory",
        components: {CategoryDropdown},
    }
</script>

<style scoped>

</style>