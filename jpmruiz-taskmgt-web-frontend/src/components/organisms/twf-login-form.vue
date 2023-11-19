<template>
    <form
        @submit.prevent="formSubmit(newData)"
        aria-label="loginForm"
        aria-describedby="login form">
            <twf-label-input
                input-type="text"
                input-name="Username"
                aria-label="usernameInputField"
                aria-described="Input field for username"
                required
                @update:model-value="e => newData.username = e"/>
            <twf-label-input 
                input-type="password"
                input-name="Password"
                aria-label="passwordInputField"
                aria-described="Input field for password"
                required
                @update:model-value="e => newData.password = e" />
            <twf-button
                type="submit"
                id="form-submit"
                name="form-submit"
                aria-label="addButton"
                aria-described="Button for form submission">
                Login
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
        username: '',
        password: ''
    }
</script>

<style scoped>
    button {
        margin-top: 0.4rem;
    }
</style>