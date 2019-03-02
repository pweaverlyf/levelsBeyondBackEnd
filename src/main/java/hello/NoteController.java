package hello;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.json.*;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@RestController
public class NoteController {

    // private Map<Integer, Note> notes = new HashMap<Integer, Note>();
    private ArrayList<Note> notes = new ArrayList();
    private final AtomicInteger counter = new AtomicInteger();
    boolean addNote = false;
    boolean shouldDelete = false;
    int indexToDelete = 0;
    int index = 0;
    boolean shouldUpdate = false;

    @RequestMapping(value = "/api/notes", method = RequestMethod.GET)
    public ArrayList get(String query) {
      if (query != null) {
        ArrayList<Note> response = new ArrayList();
        notes.forEach((n) -> {
          if (n.getBody().contains(query)) {
            response.add(n);
          }
        });
        return response;
      } else {
        return notes;
      }
    }

    @RequestMapping(value = "/api/notes", method = RequestMethod.POST)
    public Note post(@RequestBody String body) {
      JSONObject jo = new JSONObject(body);
      addNote = false;
      if (!notes.isEmpty()) {
        Note newNote = new Note();
        notes.forEach((n) -> {
          if (n.getId() == jo.getInt("id")) {
            String alert = "Id already exists";
            newNote.setId(n.getId());
            newNote.setBody(alert);
            addNote = false;
          } else {
            newNote.setId(jo.getInt("id"));
            newNote.setBody(jo.getString("body"));
            addNote = true;
          }
        });
        if (addNote) {
          notes.add(newNote);
        }
        return newNote;
      } else {
        Note note = new Note(jo.getInt("id"), jo.getString("body"));
        notes.add(note);
        return note;
      }
    }

    @RequestMapping(value = "/api/notes/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
      shouldDelete = false;
      indexToDelete = 0;
      index = 0;
      notes.forEach((n) -> {
        if (n.getId() == id) {
          shouldDelete = true;
          index = indexToDelete;
        }
        indexToDelete++;
      });
     
      if (shouldDelete) {
        notes.remove(index);
        return "Note deleted.";
      } else {
        return "Note not found.";
      }
    }

    @RequestMapping(value = "/api/notes/{id}", method = RequestMethod.POST)
    public Note update(@RequestBody String body, @PathVariable int id) {
      JSONObject jo = new JSONObject(body);
      if (!notes.isEmpty()) {
        Note responseNote = new Note();
        notes.forEach((n) -> {
          if (n.getId() == id) {
            n.setBody(jo.getString("body"));
            responseNote.setId(n.getId());
            responseNote.setBody(n.getBody());
          } else {
            responseNote.setId(id);
            String alert = "No id exists.";
            responseNote.setBody(alert);
          }
        });
        return responseNote;
      } else {
        String alert = "No id exists.";
        Note noID = new Note(id, alert);
        return noID;
      }
    }

    @RequestMapping("/api/notes/{id}")
    public Note working(@PathVariable int id) {
      if (!notes.isEmpty()) {
        Note responseNote = new Note();
        notes.forEach((n) -> {
          if (n.getId() == id) {
            responseNote.setId(n.getId());
            responseNote.setBody(n.getBody());
          } else {
            responseNote.setId(id);
            String alert = "No note found.";
            responseNote.setBody(alert);
          }
        });
        return responseNote;
      } else {
        String alert = "No note found.";
        Note noID = new Note(id, alert);
        return noID;
      }
    }
}