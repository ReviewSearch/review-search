const METHOD = {
    POST(data) {
        return {
            method:'POST',
            body: data
        }
    }
}

const api = (() => {
    const requestWithJsonData = (uri, config) => fetch(uri, config).then(response => response.json());
    const repo = {
        createRepo(repoName) {
            const data = {
                name: repoName
            }
            return requestWithJsonData(`/api/github/repos`, METHOD.POST(data));
    const search = {
        getComments({keyword, repoName}) {
            return requestWithJsonData(`/api/comments?keyword=${keyword}&repoName=${repoName}`)
        }
    }

    const repos = {
        getRepoNames() {
            return requestWithJsonData('api/github/repo-names')
        }
    }

    return {
        comment, repo,
        search,
        repos
    }
})()

export default api
