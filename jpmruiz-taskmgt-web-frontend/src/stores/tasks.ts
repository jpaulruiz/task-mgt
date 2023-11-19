import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import request from '@/stores/request'

const useTasks = defineStore('tasks', () => {
    const board = ref(null)
    const task = ref(null)
    const boardMember = ref(null)

    const getBoardTask = async () => {
      await request('http://localhost:9000/task/' + board.value.id, 
        {
          method: 'GET',
          credentials: 'include'
        },
        (res) => {
          task.value = res
        }
        )
    }

    const getBoardMembers = async () => {
      await request('http://localhost:9000/boardMember/' + board.value.id, 
        {
          method: 'GET',
          credentials: 'include'
        },
        (res) => {
          boardMember.value = res
        })
    }

    const openBoard = async (e) => {
      board.value = e
      await getBoardTask()
      await getBoardMembers()
      router.push("/tasks")
    }

    
    const newTask = async(e) => {
      const formData = new URLSearchParams()
        formData.append('name', e.name)
        formData.append('boardId',board.value.id)
        formData.append('user', e.user)
        formData.append('isComplete', e.isComplete)

        await request('http://localhost:9000/task', 
          {
            method: 'POST',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: formData,
            credentials: 'include'
          },
          async (res) => {
            if (res.id != '') {
              alert('Task has been created.')
              await getBoardTask()
            } else {
              alert('Task could not be created.')
            }
          })
    }

    const newMember = async(e) => {
      const formData = new URLSearchParams()
        formData.append('boardId', board.value.id)
        formData.append('username', e.name)

        await request('http://localhost:9000/boardMember', 
          {
            method: 'POST',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: formData,
            credentials: 'include'
          },
          async (res) => {
            if (res.id != '') {
              alert('Board member has been created.')
              await getBoardMembers()
            } else {
              alert('Board member could not be created.')
            }
          })
    }

    return {
      board,
      task,
      boardMember,
      openBoard,
      newTask,
      newMember
    }


})

export default useTasks