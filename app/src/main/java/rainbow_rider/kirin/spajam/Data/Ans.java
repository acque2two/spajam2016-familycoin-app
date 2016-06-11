package rainbow_rider.kirin.spajam.Data;

import java.io.Serializable;

public class Ans  implements Serializable {
    private String ans_text;
    private int q_id;
    //String struser = tr.getUser().getUser_name();
    private long ans_id;
    private Long count;

    public String getAns_text() {
        return ans_text;
    }

    public void setAns_text(String ans_text) {
        this.ans_text = ans_text;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public long getAns_id() {
        return ans_id;
    }

    public void setAns_id(long ans_id) {
        this.ans_id = ans_id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
