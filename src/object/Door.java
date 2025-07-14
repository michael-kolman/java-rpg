package object;

import javax.imageio.ImageIO;

public class Door extends SuperObject {

    public Door(){

        name = "door";
        try{
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/door.png"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
