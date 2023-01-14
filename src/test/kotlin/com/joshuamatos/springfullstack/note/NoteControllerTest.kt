package com.joshuamatos.springfullstack.note

import com.fasterxml.jackson.databind.ObjectMapper
import com.joshuamatos.springfullstack.note.factories.createNoteDto
import com.joshuamatos.springfullstack.note.factories.createNoteDtoList
import com.joshuamatos.springfullstack.note.factories.createNoteRequest
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.*


@WebMvcTest(NoteController::class)
class NoteControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var noteService: NoteService

    @Test
    fun `should get a list of notes`() {
        val notes = createNoteDtoList()
        every { noteService.getAllNotes() } returns notes

        mockMvc.get("/api/v1/note")
            .andExpect {
                status { isOk() }
                content {
                    json(objectMapper.writeValueAsString(notes))
                }
            }

    }

    @Test
    fun `gets a single note`() {
        val noteDto = createNoteDto()

        every { noteService.getNoteById(noteDto.id) } returns noteDto

        mockMvc.get("/api/v1/note/${noteDto.id}")
            .andExpect {
                status { isOk() }
                content {
                    json(objectMapper.writeValueAsString(noteDto) )
                }
            }
    }

    @Test
    fun `creates a note`() {
        val noteRequest = createNoteRequest()
        every { noteService.createNote(noteRequest) } just runs

        mockMvc.post("/api/v1/note") {
            contentType = APPLICATION_JSON
            content = objectMapper.writeValueAsString(noteRequest)
        }.andExpect {
            status { isOk() }
        }

        verify (exactly = 1) { noteService.createNote(noteRequest) }
    }

    @Test
    fun `updates a note`() {
        val noteId = 1L
        val noteRequest = createNoteRequest()

        every { noteService.updateNote(noteId, noteRequest) } just runs


        mockMvc.put("/api/v1/note/${noteId}") {
            contentType = APPLICATION_JSON
            content = objectMapper.writeValueAsString(noteRequest)
        }.andExpect {
            status { isOk() }
        }

        verify (exactly = 1) { noteService.updateNote(noteId, noteRequest) }
    }

    @Test
    fun `delete note by ID`() {
        val noteId = 1L
        every { noteService.deleteNoteById(any()) } just runs

        mockMvc.delete("/api/v1/note/${noteId}")
            .andExpect {
                status { isOk() }
            }

        verify (exactly = 1){ noteService.deleteNoteById(noteId) }
    }
}