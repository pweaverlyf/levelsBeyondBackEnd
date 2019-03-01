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

    @RequestMapping(value = "/api/notes", method = RequestMethod.GET)
    public ArrayList get(@RequestParam(value="name", defaultValue="World") String name) {
        return notes;
    }

    @RequestMapping(value = "/api/notes", method = RequestMethod.POST)
    public Note post(@RequestBody String body) {
      JSONObject jo = new JSONObject(body);
      Note note = new Note(counter.incrementAndGet(), jo.getString("body"));
      notes.add(note);
        return note;
    }

    @RequestMapping(value = "/api/notes/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
      notes.remove(id-1);
        return "Note deleted.";
    }

    @RequestMapping(value = "/api/notes/{id}", method = RequestMethod.POST)
    public Note update(@RequestBody String body, @PathVariable Integer id) {
      JSONObject jo = new JSONObject(body);
      Note note = new Note(id, jo.getString("body"));
      notes.set(id-1, note);
        return note;
    }

    @RequestMapping("/api/notes/{id}")
    public Note working(@PathVariable Integer id) {
      return notes.get(id-1);
    }
}