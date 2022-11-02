package com.github.soup.scrap.infra.http

import com.github.soup.scrap.application.facade.ScrapFacadeImpl
import com.github.soup.scrap.infra.http.request.CreateScrapRequest
import com.github.soup.scrap.infra.http.response.ScrapResponse
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore
import javax.validation.Valid

@RestController
@RequestMapping("/scrap")
class ScrapController(
    private val scrapFacade: ScrapFacadeImpl
) {

    @ApiOperation(value = "스크랩하기")
    @PostMapping("/new")
    fun getGroup(
        @ApiIgnore authentication: Authentication,
        @Valid request: CreateScrapRequest
    ): ResponseEntity<ScrapResponse> =
        ResponseEntity.ok().body(
            scrapFacade.create(
                authentication.name,
                request
            )
        )

    @ApiOperation(value = "스크랩 목록 조회")
    @GetMapping("/list")
    fun getList(
        @ApiIgnore authentication: Authentication,
    ): ResponseEntity<List<ScrapResponse>> =
        ResponseEntity.ok().body(
            scrapFacade.getList(
                authentication.name,
            )
        )

    @ApiOperation(value = "스크랩 취소")
    @DeleteMapping("/{scrapId}")
    fun getGroup(
        @ApiIgnore authentication: Authentication,
        @PathVariable scrapId: String
    ): ResponseEntity<Boolean> =
        ResponseEntity.ok().body(
            scrapFacade.delete(
                authentication.name,
                scrapId
            )
        )
}