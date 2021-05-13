package at.fhstp.sniffable;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

@Component
public class ImageMetaRepository implements java.io.Serializable{

    private List <ImageMeta> metaData = new ArrayList<ImageMeta>();

    public void addMeta(ImageMeta meta){
        metaData.add(meta);
    }

    public int getImageCount(){
        return metaData.size();
    }

    public List<ImageMeta> getMetaData(){
        return metaData;
    }


    public List<ImageMeta> getImagePathsForUser(String user){

        List <ImageMeta> userpictures = new ArrayList<ImageMeta>();
        for(ImageMeta meta : metaData){
            if(meta.getUser().equals(user)){
                userpictures.add(meta);
            }
        }
        return userpictures;
    }

    public int getImagePathCountForUser(String user)
    {
        int counter=0;
        for(ImageMeta meta : metaData){
            if(meta.getUser().equals(user)){
                counter++;
            }
        }
        return counter;
    }


}
