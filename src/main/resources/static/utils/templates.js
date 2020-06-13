export const commentTemplate = ({login, markedContent, htmlUrl}) =>
    `<div class="mx-auto mb-5 mb-lg-0 mb-lg-3 col-12 col-md-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${login}</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="display:none;">${login}</h6>
                <p id="content" class="card-text">${markedContent}</p>
                <a href="${htmlUrl}" class="card-link">Github에서 보기</a>
            </div>
        </div>
    </div>`