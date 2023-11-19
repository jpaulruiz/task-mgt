const request = async (url: string, body: object, cb: (res: object) => void) => {
    await fetch(url,body)
            .then(response => response.json())
            .then(res => {
                cb(res)
            })
            .catch(error => {
                console.log(error)
            })
}

export default request