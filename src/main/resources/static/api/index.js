const METHOD = {
    GET() {
        return {
            method: 'GET',
        }
    }
}

const api = (() => {
    const requestWithJsonData = (uri, config) => fetch(uri, config).then(data => data.json())

    const search = {
        getComments(keyword) {
            return requestWithJsonData(`/api/comments?keyword=${keyword}`)
        }
    }

    return {
        search
    }
})()

export default api
