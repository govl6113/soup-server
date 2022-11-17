package com.github.soup.review.exception

class ReviewDontWriteSelfException : RuntimeException(
    "자신에게 리뷰를 작성할 수 없습니다."
)