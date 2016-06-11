package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

import rainbow_rider.kirin.spajam.Data.Multiple.Anss;

public class Question implements Serializable {
    private static final long serialVersionUID = 1741796578394857L;
    private long q_id;
    private Genre genre;
    private User user;
    private String q_name;
    private String q_text;
    private String image_url;
    private Anss answer;
    private long true_id;
    private Long count;

    public Anss getAnswer() {
        return answer;
    }

    public void setAnswer(Anss answer) {
        this.answer = answer;
    }

    public Long getCount( ) {
        return count;
    }


    public long getQ_id( ) {
        return q_id;
    }

    public void setQ_id( long q_id ) {
        this.q_id = q_id;
    }

    public Genre getGenre( ) {
        return genre;
    }

    public void setGenre( Genre genre ) {
        this.genre = genre;
    }

    public User getUser( ) {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public String getQ_name( ) {
        return q_name;
    }

    public void setQ_name( String q_name ) {
        this.q_name = q_name;
    }

    public String getQ_text( ) {
        return q_text;
    }

    public void setQ_text( String q_text ) {
        this.q_text = q_text;
    }

    public String getImage_url( ) {
        return image_url;
    }

    public void setImage_url( String image_url ) {
        this.image_url = image_url;
    }



    public long getTrue_id( ) {
        return true_id;
    }

    public void setTrue_id( long true_id ) {
        this.true_id = true_id;
    }
}
