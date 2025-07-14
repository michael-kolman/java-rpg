package object;

import javax.imageio.ImageIO;

public class Chest extends SuperObject{

    public Chest(){

        name = "chest";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/chest.png"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
