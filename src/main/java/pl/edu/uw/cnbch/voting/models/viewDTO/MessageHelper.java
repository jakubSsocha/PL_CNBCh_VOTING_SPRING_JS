package pl.edu.uw.cnbch.voting.models.viewDTO;

public class MessageHelper {

    public static MessageHelper generateMessage(String text, String type){
        return new MessageHelper(text, type);
    }

    private String text;
    private String type;

    public String getText() {
        return text;
    }

    public String getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(String type) {
        this.type = type;
    }

    private MessageHelper(String text, String type) {
        this.text = text;
        this.type = type;
    }
}
