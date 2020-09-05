import api from "../api/index.js"
import { EVENT_TYPE } from "../utils/contants.js";

function Admin() {
    const $addButton = document.querySelector("#add-button");
    const repoName = document.querySelector("#repo-name").value;

    const onClick = event => {
        event.preventDefault();

        api.repo.createRepo(repoName)
            .then();
        // todo : 여기서부터 시작
    }

    this.init = () => {
        $addButton.addEventListener(EVENT_TYPE.CLICK, onClick);
    }
}

const admin = new Admin();
admin.init();