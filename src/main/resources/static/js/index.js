import api from "../api/index.js"
import { commentTemplate } from "../utils/templates.js";

const CLICK = 'click';

function Index() {
    const $submitButton = document.querySelector("#submit-button")
    const $exampleFormControlSelect1 = document.querySelector("#exampleFormControlSelect1");

    const onSearchKeyword = event => {
        event.preventDefault()
        const $comments = document.querySelector("#comments")
        const keyword = document.querySelector("#keyword").value

        const search = {
            keyword : keyword,
            repoName : $exampleFormControlSelect1.value
        }

        api.search.getComments(search)
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

    const showRepoNames = () => {
        api.repos.getRepoNames().then(
            repos => {
                repos.map(repoName => {
                    $exampleFormControlSelect1.options.add(new Option(repoName));
                })
            })
    }

    const initEventListener = () => {
        $submitButton.addEventListener(CLICK, onSearchKeyword)
    }

    this.init = () => {
        initEventListener();
        showRepoNames();
    }
}

const index = new Index()
index.init()