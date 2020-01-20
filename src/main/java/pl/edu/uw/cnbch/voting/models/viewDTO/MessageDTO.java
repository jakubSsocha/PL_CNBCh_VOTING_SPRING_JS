package pl.edu.uw.cnbch.voting.models.viewDTO;

public class MessageDTO {

    public static MessageDTO generateMessage(String text, String type){
        return new MessageDTO(text, type);
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

    private MessageDTO(String text, String type) {
        this.text = text;
        this.type = type;
    }
}
