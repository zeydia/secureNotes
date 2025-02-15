package com.zeta.EmbarkxTuto.controllers;

import com.zeta.EmbarkxTuto.models.Note;
import com.zeta.EmbarkxTuto.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    // Create Note
    @PostMapping
    public Note createNoteForUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody String content
    )
    {
        String username = userDetails.getUsername();
        System.out.println("User Details: "+username);
        return  noteService.createNoteForUser(username, content);
    }

    // Retrieve Notes
//    @GetMapping
//    public List<Note> getAllNotes() {
//        List
//    }

    // Retrieve Notes for User
    @GetMapping
    public List<Note> getNoteForUser(
            @AuthenticationPrincipal UserDetails userDetails
    )
    {
        String username = userDetails.getUsername();
        return noteService.getNotesForUser(username);
    }

    // Update Note
    @PutMapping("{id}")
    public Note updateNoteForUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody String content
    )
    {
        String username = userDetails.getUsername();
        return noteService.updateNoteForUser(id, username, content);
    }


    // Delete Note
    @DeleteMapping("{id}")
    public void deleteNoteForUser(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    )
    {
        String username = userDetails.getUsername();
        noteService.deleteNoteForUser(id, username);
    }

}
