<template>
    <top-level-view>
        <div class="wrapper">
            <div class="container">
                <h2>REGISTRATION</h2>
                <twf-register-form 
                    :form-submit="service.createAccount">
                    <span :class="service.success ? 'green' : 'red'">
                        {{ service.message }}
                    </span>
                    Already have an account? Login <router-link to="/">here</router-link>
                </twf-register-form>
            </div>
        </div>
    </top-level-view>
</template>

<script setup lang="ts">
    import TopLevelView from '../templates/top-level-view.vue'
    import TwfRegisterForm from '../organisms/twf-register-form.vue'
    import useAccounts from '../../stores/accounts.ts'

    const service = useAccounts()
    //reset everything
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
    }

    .wrapper {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 92vh;
    }
</style>