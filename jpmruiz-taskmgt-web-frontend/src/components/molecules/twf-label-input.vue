<template>
    <twf-label 
        :id="labelId"
        :name="inputName"/>
    <twf-input 
        :aria-label="ariaLabel"
        :aria-describedby="ariaDescribed"
        :type="inputType" 
        :name="inputName"
        :model-value="modelValue"
        :required="$attrs.required"
        @update:model-value="$emit('update:modelValue', $event)"/>
</template>

<script setup lang="ts">
    import TwfLabel from '../atoms/twf-label.vue'
    import TwfInput from '../atoms/twf-input.vue'

    defineProps({
        labelId: {
            type: String,
            default: '0'
        },
        inputName: {
            type: String,
            default: 'input-name'
        },
        ariaLabel: {
            type: String,
            default: ''
        },
        ariaDescribed: {
            type: String,
            default: ''
        },
        inputType: {
            type: String,
            default: 'text'
        },
        modelValue: {
            type: String,
            default: ''
        }
    })

    defineEmits([
        'update:modelValue'
    ])
</script>