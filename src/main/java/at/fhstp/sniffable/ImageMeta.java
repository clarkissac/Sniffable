package at.fhstp.sniffable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImageMeta implements java.io.Serializable{

    private String name;
    private String user;
    private long size;
    private Path filePath;
    private long timestamp;
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Like> likes = new ArrayList<Like>();

    public ImageMeta(String name, long size, Path filePath, String user){

        this.name = name;
        this.size = size;
        this.user = user;
        this.filePath = filePath;
        timestamp = System.currentTimeMillis();

    }

    
    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return long return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }

    /**
     * @return String return the filePath
     */
    public Path getFilePath() {
        return  filePath;
    }
    
    public String getFilePathHtml() {
        return  filePath.toString().substring(filePath.toString().indexOf("static\\") + 7,filePath.toString().length());
        
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(Path filePath) {
        this.filePath = filePath;
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


    /**
     * @return List<comment> return the comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * @return List<like> return the likes
     */
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

}
