<template>
    <top-level-view>
        <h2>Dashboard</h2>
        <section>
            <twf-button 
                @update:model-value="showModal = true">
                Create Board
            </twf-button>
            <twf-button
                @update:model-value="service.logoutAccount">
                Logout
            </twf-button>
        </section>
        <section>
            <Teleport to="body">
                <twf-modal :show="showModal" @close="showModal = false">
                    <template #header>
                        <h3>Add New Board</h3>
                    </template>
                    <template #body>
                        <twf-board-form
                            :formSubmit="board.newBoard"/>
                    </template>
                </twf-modal>
            </Teleport>
        </section>
        <section class="board-wrapper">
            <div 
                class="board-container" 
                v-for="bData, index in board.boardData"
                :key="index"
            >
                    <div>Board Name: {{bData.name}}</div>
                    <div>Owner: {{bData.owner}}</div>
                <twf-button
                    class="board-button" 
                    @update:model-value="e => task.openBoard(bData)">
                    View Board
                </twf-button>
                <twf-button
                    class="board-button" 
                    @update:model-value="e => updateModal = true">
                    <Teleport to="body">
                        <twf-modal :show="updateModal" @close="updateModal = false">
                            <template #header>
                                <h3>Update Board</h3>
                            </template>
                            <template #body>
                                <twf-update-board-form
                                    :form-submit="updateHandler(bData.id)"/>
                            </template>
                        </twf-modal>
                    </Teleport>
                    Update Board
                </twf-button>
                <twf-button
                    class="board-button" 
                    @update:model-value="e => board.deleteBoard(bData.id)">
                    Delete Board
                </twf-button>
            </div>
        </section>
    </top-level-view>
</template>

<script setup lang="ts">
    import TopLevelView from '../templates/top-level-view.vue'
    import TwfModal from '../molecules/twf-modal.vue'
    import TwfButton from '../atoms/twf-button.vue'
    import TwfBoardForm from '../organisms/twf-board-form.vue'
    import TwfUpdateBoardForm from '../organisms/twf-update-board-form.vue'
    import useAccounts from '../../stores/accounts.ts'
    import useTasks from '../../stores/tasks.ts'
    import useBoards from '@/stores/boards'
    import { ref } from 'vue'

    const service = useAccounts()
    const task = useTasks()
    const board = useBoards()

    const showModal = ref(false)
    const updateModal = ref(false)

    // board.getBoards()

    const updateHandler = (boardID) => {
        return (e) => {
            board.updateBoard(e, boardID)
        }
    }

</script>

<style scoped>
    .wrapper {
        display: flex;
        flex-direction: column;
    }

    button {
        background-color: #020815;
        color: white;
        border: 2px solid black;
        font-size: 1vw;
        border-radius: 2px;
        margin: 0 4px 0 4px;
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

    .board-wrapper {
        display: flex;
        flex-direction: column;
        height: 80vh;
        justify-content: flex-start;
        align-items: center;
    }

    .board-container {
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