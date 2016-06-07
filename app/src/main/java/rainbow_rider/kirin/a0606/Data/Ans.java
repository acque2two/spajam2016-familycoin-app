package rainbow_rider.kirin.a0606.Data;

public class Ans {
    private User user;
    private Question question;
    private long ans_id;

    public String getAns_text( ) {
        return ans_text;
    }

    public void setAns_text( String ans_text ) {
        this.ans_text = ans_text;
    }

    private String  ans_text;
    private Long count;
    public Long getCount( ) {
        return count;
    }


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
