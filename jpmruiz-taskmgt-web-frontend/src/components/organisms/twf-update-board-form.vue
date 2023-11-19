<template>
    <!-- <form
        @submit.prevent="formSubmit(newData)"
        aria-label="boardForm"
        aria-describedby="Board form"> -->
    <form
        @submit.prevent="formSubmit(newData)"
        aria-label="boardForm"
        aria-describedby="Board form">
            <twf-label-input
                input-type="text"
                input-name="Board Name"
                aria-label="boardNameINputField"
                aria-described="board name field input"
                required
                @update:model-value="e => newData.name = e"/>
            <twf-label-input
                input-type="text"
                input-name="Update Owner"
                aria-label="board owner"
                aria-described="board owner field input"
                required
                @update:model-value="e => newData.owner = e"/>
            <twf-button
                type="submit"
                id="form-submit"
                name="form-submit"
                aria-label="addButton"
                aria-described="Button for form submission">
                New Board Name
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
        },
        modelValue: {
            type: String,
            default: ''
        }
    })

    defineEmits([
        'update:modelValue',
        'click'
    ])

    const newData = {
        name: '',
        owner: ''
    }
</script>

<style scoped>
    button {
        margin-top: 0.4rem;
    }
</style>