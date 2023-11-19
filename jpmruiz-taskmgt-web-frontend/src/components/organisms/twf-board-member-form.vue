<template>
    <form
        @submit.prevent="formSubmit(newData)"
        aria-label="boardMemberForm"
        aria-describedby="Board member form">
            <twf-label-input
                input-type="text"
                input-name="Member name (username - must exist)"
                aria-label="boardMemberName"
                aria-described="board member"
                required
                @update:model-value="e => newData.name = e"/>
            <twf-button
                type="submit"
                id="form-submit"
                name="form-submit"
                aria-label="addButton"
                aria-described="Button for form submission">
                Add Member
            </twf-button>
            <slot></slot>
    </form>
</template>

<script setup lang="ts">
    import TwfLabelInput from '../molecules/twf-label-input.vue'
    import TwfButton from '../atoms/twf-button.vue'

    defineProps({
        formSubmit: {
            type: Function,
            default() {
                return 'Default Function'
            }
        }
    })

    const newData = {
        name: ''
    }
</script>

<style scoped>
    button {
        margin-top: 0.4rem;
    }
</style>