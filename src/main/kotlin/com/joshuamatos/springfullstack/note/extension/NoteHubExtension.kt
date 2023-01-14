package com.joshuamatos.springfullstack.note.extension

import com.joshuamatos.springfullstack.note.Note
import com.joshuamatos.springfullstack.note.types.NoteDto


fun Note.toNoteDto() = NoteDto (
    id = id!!,
    title = title!!,
    noteBody = noteBody!!,
    pinned = pinned,
    createdAt = createdAt
)