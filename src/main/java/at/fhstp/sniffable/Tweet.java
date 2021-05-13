package at.fhstp.sniffable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Tweet implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String id;
    private String content;
    private long timestamp;
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Like> likes = new ArrayList<Like>();

    

    public Tweet(String content){
        this.content = content;
        this.id = UUID.randomUUID().toString();
        timestamp = System.currentTimeMillis();

    }

    public Tweet() {
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
        

        boolean isLiked = false;
        for (Like like : likes) {
            if(like.getUser().equals(user)){
                isLiked = true;
            }      
        }
        if(!isLiked){
            likes.add(new Like(user));
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
    
     public String[] getContent() {
        return new String[] {content,id};
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
