package com.github.soup.post.appllication.facade

import com.github.soup.post.infra.http.request.CreatePostRequest
import com.github.soup.post.infra.http.request.UpdatePostRequest
import com.github.soup.post.infra.http.response.PostResponse

interface PostFacade {

    fun create(memberId: String, request: CreatePostRequest): PostResponse

    fun get(memberId: String, postId: String): PostResponse

    fun update(memberId: String, postId: String, request: UpdatePostRequest): PostResponse

    fun delete(memberId: String, postId: String): Boolean
}