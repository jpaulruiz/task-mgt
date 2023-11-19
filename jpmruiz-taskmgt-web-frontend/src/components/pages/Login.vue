<template>
    <top-level-view>
        <div class="wrapper">
            <div class="container">
                <h2>LOGIN</h2>
                <twf-login-form 
                    :form-submit="service.logonAccount">
                    <span :class="service.success ? 'green' : 'red'">
                        {{ service.message }}
                    </span>
                    No account yet? Sign up 
                    <router-link to="/register-form">here</router-link>
                </twf-login-form>
            </div>
        </div>
    </top-level-view>
</template>

<script setup lang="ts">
    import TopLevelView from '../templates/top-level-view.vue'
    import TwfLoginForm from '../organisms/twf-login-form.vue'
    import useAccounts from '../../stores/accounts.ts'

    const service = useAccounts()
    service.message = ''
    service.success = false
</script>

<style scoped>
    .red {
        color: #cc0000;
    }

    .green {
        color: #4bb543;
    }

    h2 {
        text-align: center;
        letter-spacing: 2px;
    }

    .container {
        padding: 1rem;
        background: rgba(240, 240, 240, 1);
        background-color: #020815;
        color: white;
        border-radius: 16px;
        box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
        backdrop-filter: blur(5px);
        border: 1px solid rgba(240, 240, 240, 0.3);
        width: 20vw;
    }

    .wrapper {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 92vh;
    }
</style>