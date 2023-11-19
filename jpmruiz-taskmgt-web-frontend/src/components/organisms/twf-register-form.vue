<template>
    <form 
        class="form-class"
        name="register-form" 
        method="POST" 
        @submit.prevent="formSubmit(newData)"
        aria-labelledby="createForm"
        aria-describedby="new account form">
            <twf-label-input 
                input-type="text"
                input-name="Username"
                aria-label="usernameInputField"
                aria-described="username field input"
                :required=true
                :model-value="newData.username"
                @update:model-value="e => newData.username = e" />
            <twf-label-input 
                input-type="password"
                input-name="Password"
                aria-label="passwordInputField"
                aria-described="password field input"
                :required=true
                @update:model-value="e => newData.password = e" />
            <twf-label-input 
                input-type="password"
                input-name="Confirm Password"
                aria-label="repeatPasswordInputField"
                aria-described="password re-enter input field"
                :required=true
                @update:model-value="e => newData.confirmPassword = e" />
            <twf-button
                type="submit"
                id="form-submit"
                name="form-submit"
                aria-label="addButton"
                aria-described="submit button">
                Register
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
        password: '',
        confirmPassword: ''
    }
    
</script>

<style scoped>
    button {
        margin-top: 0.4rem;
    }
</style>