package com.github.soup.file.exception

class NotSupportedFileFormatException(mime: String) : RuntimeException(
    "$mime 은 지원하지 않는 파일 형식입니다."
)
