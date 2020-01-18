package pl.edu.uw.cnbch.voting.models.viewHelpers;

import pl.edu.uw.cnbch.voting.models.entities.Voting;

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

    public AllVotingViewHelper(Voting voting) {
        this.id = voting.getId();
        this.name = voting.getName();
        this.text = voting.getDescription();
        this.closed = voting.isClosed();
    }
}
