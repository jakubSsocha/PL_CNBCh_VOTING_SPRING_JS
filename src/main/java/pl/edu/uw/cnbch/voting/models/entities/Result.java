package pl.edu.uw.cnbch.voting.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdDate;

    private boolean active;

    private String vote;

    private LocalDateTime userVotedDate;

    private boolean closed;

    private LocalDateTime closedDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Voting voting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUserVotedDate() {
        return userVotedDate;
    }

    public void setUserVotedDate(LocalDateTime userVotedDate) {
        this.userVotedDate = userVotedDate;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDateTime closedDate) {
        this.closedDate = closedDate;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Voting getVoting() {
        return voting;
    }

    public void setVoting(Voting voting) {
        this.voting = voting;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

// Builder and constructors

    public static class Builder{
        private Voting voting;
        private User user;
        private boolean active;
        private LocalDateTime createdDate;
        private boolean closed;

        public Builder voting(Voting voting) {
            this.voting = voting;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder closed(boolean closed) {
            this.closed = closed;
            return this;
        }

        public Result build(){
            return new Result(this);
        }
    }

    private Result (Builder builder){
        this.voting = builder.voting;
        this.user = builder.user;
        this.active = builder.active;
        this.createdDate = builder.createdDate;
        this.closed = builder.closed;
    }

    public Result(){
    }
}
