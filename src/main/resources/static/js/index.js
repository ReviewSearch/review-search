import api from "../api/index.js"
import {commentTemplate} from "../utils/templates.js";

function Index() {
    const $submitButton = document.querySelector("#submit-button")


    const onSearchKeyword = event => {
        event.preventDefault()
        const $comments = document.querySelector("#comments")
        const keyword = document.querySelector("#keyword").value

        api.search.getComments(keyword)
            .then(comments =>
                $comments.innerHTML = comments.map(comment => commentTemplate(comment)).join('')
            )
    }

    const initEventListener = () => {
        $submitButton.addEventListener('click', onSearchKeyword)
    }

    this.init = () => {
        initEventListener();
    }
}

const index = new Index()
index.init()