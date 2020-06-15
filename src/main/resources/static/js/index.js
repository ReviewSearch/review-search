import api from "../api/index.js"
import { commentTemplate } from "../utils/templates.js";

const CLICK = 'click';

function Index() {
  const $submitButton = document.querySelector("#submit-button")

  const onSearchKeyword = event => {
    event.preventDefault()
    const $comments = document.querySelector("#comments")
    const keyword = document.querySelector("#keyword").value

    api.search.getComments(keyword)
      .then(comments => {
        $comments.innerHTML = comments.map(({login, content, htmlUrl}) => {
          const markedContent = marked(content);
          return commentTemplate({login, markedContent, htmlUrl})
        }).join('')
      })
  }

  const initEventListener = () => {
    $submitButton.addEventListener(CLICK, onSearchKeyword)
  }

  this.init = () => {
    initEventListener();
  }
}

const index = new Index()
index.init()