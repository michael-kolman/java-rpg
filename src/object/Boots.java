package object;

import javax.imageio.ImageIO;

public class Boots extends SuperObject{

    public Boots(){

        name = "boots";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/boot.png"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
