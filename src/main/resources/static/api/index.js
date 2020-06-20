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
        getComments({keyword, repoName}) {
            console.log(keyword);
            console.log(repoName);
            return requestWithJsonData(`/api/comments?keyword=${keyword}&repoName=${repoName}`)
        }
    }

    const repos = {
        getRepoNames() {
            return requestWithJsonData('api/github/repo-names')
        }
    }

    return {
        search,
        repos
    }
})()

export default api
