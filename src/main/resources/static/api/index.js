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

    const comment = {
        getComments(keyword) {
            return requestWithJsonData(`/api/comments?keyword=${keyword}`);
        }
    }

    const repo = {
        createRepo(repoName) {
            const data = {
                name: repoName
            }
            return requestWithJsonData(`/api/github/repos`, METHOD.POST(data));
        }
    }

    return {
        comment, repo
    }
})()

export default api
