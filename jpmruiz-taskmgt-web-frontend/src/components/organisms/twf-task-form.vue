<template>
    <form
        @submit.prevent="formSubmit(newData)"
        aria-label="taskForm"
        aria-describedby="Task form">
            <twf-label-input
                input-type="text"
                input-name="Task Name"
                aria-label="taskField"
                aria-described="task field"
                required
                @update:model-value="e => newData.name = e"/>
            <twf-label-input
                input-type="text"
                input-name="Username (username - must exist / optional)"
                aria-label="taskOwner"
                aria-described="task owner"
                @update:model-value="e => newData.user = e"/>
            <div>Mark as complete: {{ isComplete }}</div>
            <select v-model="isComplete"
                @change="e => newData.isComplete = isComplete">
                <option disabled value="">Please select one</option>
                <option>true</option>
                <option>false</option>
            </select>
            <twf-button
                type="submit"
                id="form-submit"
                name="form-submit"
                aria-label="addButton"
                aria-described="Button for form submission">
                Add Task
            </twf-button>
            <slot></slot>
    </form>
</template>

<script setup lang="ts">
    import TwfLabelInput from '../molecules/twf-label-input.vue'
    import TwfButton from '../atoms/twf-button.vue'
    import { ref } from 'vue'

    defineProps({
        formSubmit: {
            type: Function,
            default() {
                return 'Default Function'
            }
        }
    })

    const newData = {
        name: '',
        user: '',
        isComplete: false
    }
    
    const isComplete = ref(false)
</script>

<style scoped>
    button {
        margin-top: 0.4rem;
    }
</style>