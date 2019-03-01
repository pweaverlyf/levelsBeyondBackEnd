package hello;

public class Note {

    private final Integer id;
    private final String body;

    public Note(Integer id, String body) {
        this.id = id;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public String getBody() {
        return body;
    }
}