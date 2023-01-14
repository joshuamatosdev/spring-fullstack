package com.joshuamatos.springfullstack.note.types

import java.time.Instant

data class NoteRequest(
    var id: Long? = null,
    var title: String = "",
    var noteBody: String = "",
    var pinned: Boolean? = false,
    var createdAt: Instant? = null
)