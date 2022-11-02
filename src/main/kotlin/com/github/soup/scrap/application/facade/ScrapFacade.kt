package com.github.soup.scrap.application.facade

import com.github.soup.scrap.infra.http.request.CreateScrapRequest
import com.github.soup.scrap.infra.http.response.ScrapResponse

interface ScrapFacade {

    fun create(memberId: String, request: CreateScrapRequest): ScrapResponse

    fun getList(memberId: String): List<ScrapResponse>

    fun delete(memberId: String, scrapId: String): Boolean
}