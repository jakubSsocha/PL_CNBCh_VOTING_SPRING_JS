package pl.edu.uw.cnbch.voting.models.viewDTO;

import pl.edu.uw.cnbch.voting.models.entities.Result;

import java.util.List;

public class VotingResultDTO {

    private int yes;
    private int no;
    private int abstain;
    private List<Result> users;
    private int votes;
    private int majority;
    private boolean result;

    public VotingResultDTO(int yes, int no, int abstain, List<Result> users) {
        this.yes = yes;
        this.no = no;
        this.abstain = abstain;
        this.users = users;
        this.majority = (this.users.size()/2)+1;
        this.result = isResultPositive();
        this.votes = this.yes + this.no + this.abstain;
    }

    private boolean isResultPositive(){
        if (this.yes >= this.majority){
            return true;
        }
        return false;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(int yes) {
        this.yes = yes;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getAbstain() {
        return abstain;
    }

    public void setAbstain(int abstain) {
        this.abstain = abstain;
    }

    public List<Result> getUsers() {
        return users;
    }

    public void setUsers(List<Result> users) {
        this.users = users;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getMajority() {
        return majority;
    }

    public void setMajority(int majority) {
        this.majority = majority;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
