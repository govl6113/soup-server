package com.github.soup.member.infra.http

import com.github.soup.member.application.service.MemberServiceImpl
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@RestController
class MemberController(
    private val memberService: MemberServiceImpl
) {

    @GetMapping("/me")
    fun me(@ApiIgnore authentication: Authentication) = memberService.me(authentication.name)
}