package rainbow_rider.kirin.spajam.Data;

public class Comment {
    private Question question;
    private User user;
    private String body;
    private Long count;
    public Long getCount( ) {
        return count;
    }


    public Question getQuestion( ) {
        return question;
    }

    public void setQuestion( Question question ) {
        this.question = question;
    }

    public User getUser( ) {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public String getBody( ) {
        return body;
    }

    public void setBody( String body ) {
        this.body = body;
    }
}
