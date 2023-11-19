import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import request from '@/stores/request'

type RegistrationData = {
  username: string;
  password: string;
  confirmPassword: string;
};

type LogonData = {
  username: string;
  password: string;
};

const useAccounts = defineStore('accounts', () => {
    const success = ref(false)
    const user = ref(null)

    const createAccount = async (e: RegistrationData) => {
      const formData = new URLSearchParams()
      formData.append('username', e.username)
      formData.append('password', e.password)
      formData.append('confirmPassword', e.confirmPassword)

      await request('http://localhost:9000/account',
        { 
          method: 'POST',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
          },
          body: formData 
        },
        (res) => {
          if (res.message !== '') {
            const searchWord = /(successfully)/g
            if (res.message.search(searchWord) > -1) {
              success.value = true
              alert(res.message)
            } else {
              success.value = false
              alert(res.message)
            }
          }
        })
    }

    const logonAccount = async (e: LogonData) => {
      const formData = new URLSearchParams()
      formData.append('username', e.username)
      formData.append('password', e.password)

      await request('http://localhost:9000/logon',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          body: formData,
          credentials: 'include'
        },
        (res) => {
          res.message
          if (res.message !== '') {
            const searchWord = /(successfully)/g
            if (res.message.search(searchWord) > -1) {
              success.value = true
              user.value = e.username
              router.push('/dashboard')
            } else {
              success.value = false
              router.push('/')
            }
          }
        }
      )
    }

    const logoutAccount = async () => {
      await fetch('http://localhost:9000/logout', {
        method: 'GET',
        credentials: 'include'
      })
      .then((response) => {
        if (response.status == 200) router.push('/')
      })
      .catch((error) => {
        console.log(error)
      })
    }

    return {
      success,
      createAccount,
      logonAccount,
      logoutAccount
    }


})

export default useAccounts