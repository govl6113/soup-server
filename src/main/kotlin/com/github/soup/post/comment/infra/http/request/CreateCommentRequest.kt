package com.github.soup.post.comment.infra.http.request

import javax.validation.constraints.NotEmpty

data class CreateCommentRequest(
    @NotEmpty
    val postId: String,

    val parentId: String?,

    @NotEmpty
    val content: String

)