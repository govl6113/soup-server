package com.github.soup.review.exception

class ReviewDontSeeSelfException : RuntimeException(
    "본인에게 작성된 리뷰는 본인이 확인할 수 없습니다."
)