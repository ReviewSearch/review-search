export const commentTemplate = comment =>
    `<div class="mx-auto mb-5 mb-lg-0 mb-lg-3 col-12 col-md-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${comment.login}</h5>
                <h6 class="card-subtitle mb-2 text-muted" style="display:none;">${comment.login}</h6>
                <p id="content" class="card-text">${comment.content}</p>
                <a href="${comment.htmlUrl}" class="card-link">Github에서 보기</a>
            </div>
        </div>
    </div>`