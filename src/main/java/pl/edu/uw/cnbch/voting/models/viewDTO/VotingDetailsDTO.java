package pl.edu.uw.cnbch.voting.models.viewDTO;

import pl.edu.uw.cnbch.voting.models.entities.Voting;

import java.time.format.DateTimeFormatter;

public class VotingDetailsDTO {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

    private boolean active;
    private boolean secret;
    private boolean closed;
    private String createdDate;
    private String lastModificationDate;
    private String closedDate;
    private String text;

    public VotingDetailsDTO(Voting voting) {
        this.active = voting.isActive();
        this.secret = voting.isSecret();
        this.closed = voting.isClosed();
        this.createdDate = voting.getCreatedDate().format(formatter);
        this.lastModificationDate = voting.getLastModificationDate().format(formatter);
        this.closedDate = voting.getClosedDate().format(formatter);
        this.text = voting.getText();
    }

    public VotingDetailsDTO() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(String lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
