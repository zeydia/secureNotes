package com.zeta.EmbarkxTuto.repositories;

import com.zeta.EmbarkxTuto.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByOwnerUsername(String ownerUsername);
}
