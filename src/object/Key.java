package object;

import javax.imageio.ImageIO;

public class Key  extends SuperObject {

    public Key(){

        name = "key";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key.png"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
