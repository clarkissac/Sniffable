package at.fhstp.sniffable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

@Component
public class ImageMetaRepository {

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

    public List<Path> getImagePathsForUser(String user){

        List <Path> userPaths = new ArrayList<Path>();
        for(ImageMeta meta : metaData){
            if(meta.getUser().equals(user)){
                userPaths.add(meta.getFilePath());
            }
        }
        return userPaths;


    }


}
