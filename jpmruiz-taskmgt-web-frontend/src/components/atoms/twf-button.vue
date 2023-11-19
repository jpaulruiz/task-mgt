<template>
    <button
        v-bind="$attrs"
        :value="modelValue"
        @click="$emit('update:modelValue', $event.target.id)">
        <slot></slot>
    </button>
</template>

<script setup lang="ts">
    defineProps({    
        modelValue: String
    })

    defineEmits([
        'update:modelValue'
    ])
</script>