package webnotes.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webnotes.model.entity.Note;
import webnotes.model.repository.NoteRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAll(int userId) {
        return noteRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Note add(Note note, int userId) {
        note.setUserId(userId);

        return noteRepository.save(note);
    }

    public void deleteById(long id) {
        noteRepository.delete(getById(id));
    }

    public void update(Note note) {
        getById(note.getId());
        noteRepository.save(note);
    }

    public Note getById(long id) {
        return noteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
