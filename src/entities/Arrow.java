package entities;

import java.awt.Image;

public class Arrow {
    private Image image;
    public int x, y;
    private int min,max;
    private String Direction = "Down";
    public Arrow() {
        image = new ImageAsset("../assets/arrow.png").getImage();
       
    }
    public void setLocationX(int x){
        this.x = x;
    }
    public Image getImage() {
        return image;
    }

    public int getX(){
        return x;
    }

    public void setLocationY(int y){
        this.y = y;

        min = y - 15;
        max = y + 15;
    }

    public void Animation(){


        if(y == min){
            Direction = "Down";
        }
        else if(y == max){
            Direction = "Up";
        }
     
        
        if(Direction == "Down"){
            
            y += 1;
        }
        else if (Direction =="Up"){
            y -= 1 ;
        }
    }

}
