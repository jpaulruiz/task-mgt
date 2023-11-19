80<template>
    <top-level-view>
        <h2>{{ task.board.name }}</h2>
        <section>
            <twf-button 
                @update:model-value="showModalOne = true">
                Create Task
            </twf-button>
            <twf-button 
                @update:model-value="showModalTwo = true">
                Add Members
            </twf-button>
            <twf-button
                @update:model-value="gotoDashboard">
                Home
            </twf-button>
            <twf-button
                @update:model-value="service.logoutAccount">
                Logout
            </twf-button>
        </section>
        <section>
            <Teleport to="body">
                <twf-modal :show="showModalOne" @close="showModalOne = false">
                    <template #header>
                        <h3>Add New Task</h3>
                    </template>
                    <template #body>
                        <twf-task-form
                            :formSubmit="task.newTask"/>
                    </template>
                </twf-modal>
            </Teleport>
        </section>
        <section>
            <Teleport to="body">
                <twf-modal :show="showModalTwo" @close="showModalTwo = false">
                    <template #header>
                        <h3>Add New Member</h3>
                    </template>
                    <template #body>
                        <twf-board-member-form
                            :formSubmit="task.newMember"/>
                    </template>
                </twf-modal>
            </Teleport>
        </section>
        <div class="container">
            <section class="task-wrapper">
                Tasks
                <div 
                    class="task-container" 
                    v-for="bData in task.task">
                        <div>Task Name: {{bData.name}}</div>
                        <div>Assigned User: {{bData.assignedUser}}</div>
                        <div>Completed: {{bData.isComplete ? "YES" : "NO"}}</div>
                </div>
            </section>
            <section class="task-wrapper">
                Added Members
                <div 
                    class="task-container">
                    Board Member/s:
                    <div
                        v-for="bData in task.boardMember">
                            <div>{{bData.userId}}</div>
                    </div>
                </div>
            </section>
        </div>
    </top-level-view>
</template>

<script setup lang="ts">
    import TopLevelView from '../templates/top-level-view.vue'
    import TwfModal from '../molecules/twf-modal.vue'
    import TwfButton from '../atoms/twf-button.vue'
    import TwfTaskForm from '../organisms/twf-task-form.vue'
    import TwfBoardMemberForm from '../organisms/twf-board-member-form.vue'
    import useAccounts from '../../stores/accounts.ts'
    import gotoDashboard from '../../stores/services.ts'
    import useTasks from '../../stores/tasks.ts'
    import { ref } from 'vue'

    const service = useAccounts()
    const task = useTasks()

    const showModalOne = ref(false)
    const showModalTwo = ref(false)
</script>

<style scoped>
    .wrapper {
        display: flex;
        flex-direction: column;
    }

    .container {
        display: flex;
        flex-direction: row;
        justify-content: space-evenly;
    }

    button {
        background-color: #020815;
        color: white;
        border: 2px solid black;
        font-size: 1vw;
        border-radius: 2px;
        margin: 0 4px 0 4px;
    }

    board button {

    }

    button:hover {
        color: yellow;
    }

    h2 {
        text-align: center;
    }

    section {
        display: flex;
        justify-content: center;
    }

    .task-wrapper {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
    }

    .task-container {
        display: flex;
        flex-direction: column;
        padding: 1rem;
        background: rgba(240, 240, 240, 1);
        background-color: #020815;
        color: white;
        border-radius: 16px;
        box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
        backdrop-filter: blur(5px);
        border: 1px solid rgba(240, 240, 240, 0.3);
        width: 20vw;
        margin-top: 0.8rem;
    }

    .member-wrapper {
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
    }

    .member-container {
        display: flex;
        flex-direction: column;
        padding: 1rem;
        background: rgba(240, 240, 240, 1);
        background-color: #020815;
        color: white;
        border-radius: 16px;
        box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
        backdrop-filter: blur(5px);
        border: 1px solid rgba(240, 240, 240, 0.3);
        width: 20vw;
        margin-top: 0.8rem;
    }

</style>