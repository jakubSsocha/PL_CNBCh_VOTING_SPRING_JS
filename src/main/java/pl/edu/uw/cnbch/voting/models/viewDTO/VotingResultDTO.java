package pl.edu.uw.cnbch.voting.models.viewDTO;

public class VotingResultDTO {

    private int yes;
    private int no;
    private int abstain;
    private int users;
    private int votes;
    private int majority;
    private boolean result;

    public VotingResultDTO(int yes, int no, int abstain, int users) {
        this.yes = yes;
        this.no = no;
        this.abstain = abstain;
        this.users = users;
        this.majority = (this.users/2)+1;
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

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
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
