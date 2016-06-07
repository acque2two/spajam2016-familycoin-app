package rainbow_rider.kirin.a0606.Data;

public class Ans {
    private User user;
    private Question question;
    private long ans_id;
    private Long count;

    public User getUser( ) {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public Question getQuestion( ) {
        return question;
    }

    public void setQuestion( Question question ) {
        this.question = question;
    }

    public long getAns_id( ) {
        return ans_id;
    }

    public void setAns_id( long ans_id ) {
        this.ans_id = ans_id;
    }
}
