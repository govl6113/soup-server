package com.github.soup.follow.exception

class NotFollowSelfException : RuntimeException(
    "자기 자신을 follow 할 수 없습니다."
)