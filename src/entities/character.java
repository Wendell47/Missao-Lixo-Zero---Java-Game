package entities;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.Timer;

import utils.screenSize;

public class character {
    private Image character_pose_1;
    private Image character_pose_2;
    screenSize screenSize;
    int rotationAngle = 10;
    int increment = 1;
    private int x;
    private int y;
    
    int timer = 5; 

    private String animatedCharacterPose = "normal";

    private int centerBodyX;
   
    BufferedImage rotatedArmRight;

    public character(){
        screenSize = new screenSize();
        x = screenSize.boardWidth/2;
        y = screenSize.boardHeight;
        
        character_pose_1 = new ImageAsset("../assets/character_pose_1.png").getImage();
        character_pose_2 = new ImageAsset("../assets/character_pose_2.png").getImage();

        
    }


    public void animate(String pose){
        
        animatedCharacterPose = pose;
     
        
    }

   
    public void draw(Graphics g){
        centerBodyX = x - (character_pose_1.getWidth(null)/2);
        
        g.drawImage(character_pose_2, centerBodyX, y - character_pose_2.getHeight(null) + 50, 0, 0, null);

        switch (animatedCharacterPose) {
            case "normal":
                g.drawImage(character_pose_1, centerBodyX, y - character_pose_1.getHeight(null) + 50, character_pose_1.getWidth(null), character_pose_1.getHeight(null), null);
                break;
            case "trowing":
                g.drawImage(character_pose_2, centerBodyX, y - character_pose_2.getHeight(null) + 50, character_pose_2.getWidth(null), character_pose_1.getHeight(null), null);
                break;
            default:
                break;
        

        /*AffineTransform rotation = new AffineTransform();
        rotation.rotate(Math.toRadians(rotationAngle), armRight.getWidth(null) / 3, armRight.getHeight(null) / 3);
        // Aplicando a transformação à imagem do braço direito
        BufferedImage();
        Graphics2D g2d = rotatedArmRight.createGraphics();
       
        g2d.drawImage(armRight, rotation, null);
        
        g2d.dispose();
        
        // Desenhe a imagem do braço direito rotacionada
        
        g.drawImage(rotatedArmRight, centerBodyX + 120, 40, rotatedArmRight.getWidth(), rotatedArmRight.getHeight(), null); */
    }
}
   

    
 
    
}
