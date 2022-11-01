package com.github.soup.post.infra.http.request

import com.github.soup.post.domain.PostTypeEnum
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class UpdatePostRequest(

    @NotNull
    val type: PostTypeEnum,

    @NotEmpty
    val title: String,

    @NotEmpty
    val content: String
)
