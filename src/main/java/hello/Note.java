package hello;

public class Note {

    private Integer id;
    private String body;

    public Note() {
        this.id = 1;
        this.body = "No content.";
    }

    public Note(Integer id, String body) {
        this.id = id;
        this.body = body;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public String getBody() {
        return body;
    }
}