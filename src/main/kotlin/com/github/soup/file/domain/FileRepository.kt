package com.github.soup.file.domain

interface FileRepository {
    fun save(file: File): File

    fun delete(fileId: String)
}