import api from "../api/index.js"
import { commentTemplate } from "../utils/templates.js";
import { EVENT_TYPE } from "../utils/contants.js";

function Index() {
    const $submitButton = document.querySelector("#submit-button")

    const onClick = event => {
        event.preventDefault()
        const $comments = document.querySelector("#comments")
        const keyword = document.querySelector("#keyword").value

        api.comment.getComments(keyword)
            .then(comments => {
                $comments.innerHTML = comments.map(({login, content, htmlUrl}) => {
                    marked.setOptions({
                        highlight: function (code, language) {
                            const validLanguage = hljs.getLanguage(language) ? language : 'plaintext';
                            return hljs.highlight(validLanguage, code).value;
                        }
                    });

                    const markedContent = marked(content)
                    return commentTemplate({login, markedContent, htmlUrl})
                }).join('')
            })
    }

    const initEventListener = () => {
        $submitButton.addEventListener(EVENT_TYPE.CLICK, onClick)
    }

    this.init = () => {
        initEventListener();
    }
}

const index = new Index()
index.init()