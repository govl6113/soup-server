package com.github.soup.post.exception

class NotFoundPostAuthorityException : RuntimeException(
    "게시글에 대한 권한이 없습니다."
)