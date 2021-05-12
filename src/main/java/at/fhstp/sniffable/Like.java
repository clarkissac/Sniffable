package at.fhstp.sniffable;

public class Like implements java.io.Serializable{


    private String user;
    private long timestamp;

    public Like(String user){

        this.user = user;
        timestamp = System.currentTimeMillis();

    }

    /**
     * @return String return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return long return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
