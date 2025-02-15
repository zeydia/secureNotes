package com.zeta.EmbarkxTuto.services;

import com.zeta.EmbarkxTuto.models.Note;
import com.zeta.EmbarkxTuto.repositories.NoteRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoteService {

    Note createNoteForUser(String username, String content);

    Note updateNoteForUser(Long noteId, String username, String content);

    void deleteNoteForUser(Long noteId, String Username);

    List<Note> getNotesForUser(String username);
}
