package com.github.soup.scrap.infra.http.request

import javax.validation.constraints.NotEmpty

data class CreateScrapRequest(
    @NotEmpty
    val groupId: String
)
