package at.fhstp.sniffable;

import java.util.ArrayList;
import java.util.List;

public class Tweet {

    private String content;
    private long timestamp;
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Like> likes = new ArrayList<Like>();

    

    public Tweet(String content){
        this.content = content;
        timestamp = System.currentTimeMillis();

    }

    public List<Like> getLikes() {
        return likes;
    }

    public void addComment(String user, String content){
        comments.add(new Comment(user, content));
    }

    public void removeComment(String user, String content){
        
        for (Comment comment : comments) {
            if(comment.getUser().equals(user) && comment.getContent().equals(content)){
                comments.remove(comment);
            }
        }
    }

    public void addLike(String user) {
        
        for (Like like : likes) {
            if(like.getUser().equals(user)){
                likes.add(new Like(user));
            }
            
        }
        
    }
    
    public void removeLike(String user) {
        
        for (Like like : likes) {
            if(like.getUser().equals(user)){
                likes.remove(like);
            }
            
        }
        
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    
     public String getContent() {
        return content;
    }


    /**
     * @return long return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @return List<Comment> return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

}
