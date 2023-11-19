import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import request from '@/stores/request'

type BoardData = {
  name: string;
  owner: string;
};

const useBoards = defineStore('boards', () => {
    const boardData = ref(null)
    const board = ref(null)
    const task = ref(null)
    const boardMember = ref(null)

    const newBoard = async (e: BoardData) => {
        const formData = new URLSearchParams()
        formData.append('name', e.name)
        formData.append('owner', e.owner)
        await request('http://localhost:9000/board', 
          {
            method: 'POST',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: formData,
            credentials: 'include'
          },
          (res) => {
            if (res.id != '') {
              alert('Board has been created.')
              socket.send(JSON.stringify({ping: "ping"}))
            } else {
              alert('Board could not be created.')
            }
          }
        )
    }

    const updateBoard = async (e,id) => {
      const formData = new URLSearchParams()
        formData.append('name', e.name)
        formData.append('owner', e.owner)
        formData.append('id', id)
        await request('http://localhost:9000/boardUpdate', 
          {
            method: 'POST',
            headers: {
              'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: formData,
            credentials: 'include'
          },
          (res) => {
            if (res.id != '') {
              alert('Board has been updated')
            } else {
              alert('Board could not be updated.')
            }
          })
    }

    const deleteBoard = async (id) => {
      const formData = new URLSearchParams()
      formData.append('id', id)
      await request('http://localhost:9000/boardDeletion', 
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
          },
          body: formData,
          credentials: 'include'
        },
        (res) => {
          if (res.id != '') {
            alert('Board has been deleted')
          } else {
            alert('Board could not be deleted.')
          }
        })
    }

    // const getBoards = async () => {
    //   await request('http://localhost:9000/board', 
    //     {
    //       method: 'GET',
    //       credentials: 'include'
    //     },
    //     (res) => {
    //       console.log(res)
    //       boardData.value = res
    //     })
    // }

    const socket = new WebSocket('ws://localhost:9000/ws')
    socket.addEventListener("open", (event) => {
      socket.send(JSON.stringify({ping: "ping"}))
    })
    socket.addEventListener("message", (event) => {
      const parsedData = JSON.parse(event.data)
      boardData.value = parsedData
    })
   

    return {
      board,
      boardData,
      task,
      boardMember,
      newBoard,
      // getBoards,
      updateBoard,
      deleteBoard
    }


})

export default useBoards