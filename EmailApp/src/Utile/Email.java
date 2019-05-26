package Utile;

public class Email {
    private String date;
    private String id;
    private String content;
    private String encoding;
    private String title;
    private String from;

    public Email(String date, String id, String content, String encoding, String title, String from) {
        this.date = date;
        this.id = id;
        this.content = content;
        this.encoding = encoding;
        this.title = title;
        this.from = from;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getTitle() {
        return title;
    }

    public String getFrom() {
        return from;
    }
}
