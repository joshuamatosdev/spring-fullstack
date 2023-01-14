package com.joshuamatos.springfullstack.note

import com.joshuamatos.springfullstack.note.types.NoteDto
import com.joshuamatos.springfullstack.note.types.NoteRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/note")
@CrossOrigin(origins = ["*"])
class NoteController(
    private val noteService: NoteService
) {


    @GetMapping
    fun getAllNotes(): List<NoteDto> = noteService.getAllNotes();

    @GetMapping("/{id}")
    fun getNoteById(@PathVariable id: Long): NoteDto = noteService.getNoteById(id)

    @PostMapping
    fun createNote(@RequestBody note: NoteRequest) = noteService.createNote(note)

    @PutMapping("/{id}")
    fun updateNote(@PathVariable id: Long, @RequestBody note: NoteRequest) = noteService.updateNote(id, note)

    @DeleteMapping("/{id}")
    fun deleteNoteById(@PathVariable id: Long) = noteService.deleteNoteById(id)
}