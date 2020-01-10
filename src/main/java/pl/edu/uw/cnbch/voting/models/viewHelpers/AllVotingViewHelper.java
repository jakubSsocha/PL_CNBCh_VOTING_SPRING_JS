package pl.edu.uw.cnbch.voting.models.viewHelpers;

public class AllVotingViewHelper {

    private Long id;
    private String name;
    private String text;
    private boolean closed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public AllVotingViewHelper(Long id, String name, String test, boolean closed) {
        this.id = id;
        this.name = name;
        this.text = test;
        this.closed = closed;
    }
}
