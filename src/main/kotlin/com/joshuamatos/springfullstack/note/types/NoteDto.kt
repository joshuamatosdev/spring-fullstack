package com.joshuamatos.springfullstack.note.types

import org.springframework.data.annotation.CreatedDate
import java.time.Instant

data class NoteDto (
    val id: Long = 0,
    val title: String = "",
    val noteBody: String = "",
    val pinned: Boolean? = false,

    @CreatedDate
    val createdAt: Instant? = null
)