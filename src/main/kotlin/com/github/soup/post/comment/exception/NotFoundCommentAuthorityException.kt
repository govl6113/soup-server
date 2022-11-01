package com.github.soup.post.comment.exception

class NotFoundCommentAuthorityException : RuntimeException(
    "해당 댓글에 대한 권한이 없습니다."
)