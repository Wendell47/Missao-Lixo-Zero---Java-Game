package entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import utils.screenSize;

public class MainGame extends JPanel  implements ActionListener{

private int cloudX = 0;

Image backgroundImage;
Image cloud;
Image sky;
Image trash;
int width;
int height;

Timer gameLoop;
Timer cloudLoop;

 //Imagens dos lixos
Image redTrash; 
Image OrangeTrash; 
Image YellowTrash; 
Image GreenTrash; 

class TrashSize{
    

}
class cloud{
    int x = 0;
    Image image;

    cloud(Image image){
        this.image = image;
    }
}

public MainGame(){
    
    screenSize screenSize = new screenSize();
    width = screenSize.boardWidth;
    height = screenSize.boardHeight;

    setPreferredSize(new Dimension(width,height)); 
    setFocusable(true);

    cloud = new ImageAsset("../assets/cloud.png").getImage();
    sky = new ImageAsset("../assets/sky.png").getImage();

    //tipos de lixos
    redTrash = new recycleBin("vidro").getImage();
    OrangeTrash = new recycleBin("plastico").getImage();
    YellowTrash = new recycleBin("papel").getImage();
    GreenTrash = new recycleBin("organico").getImage();
    


    backgroundImage = new ImageIcon(getClass().getResource("../assets/background.png")).getImage();
    backgroundImage = backgroundImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);



    cloudLoop = new Timer(75/25,new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e){
            CloudMoving();
            
    System.out.println("rodando");
        }
    });

    JPanel panel = new JPanel(new FlowLayout());
    component comp = new component();
    

    panel.add(comp);
  
    

    gameLoop = new Timer(1000/75,this);
    gameLoop.start();
}

class component extends JComponent{
 
   
    

}

private void CloudMoving(){
   
    cloudX += 1;
    
    if (cloudX > getWidth()) {
        cloudX = -100; // Reposicione a nuvem quando sair da tela
    }
}


public void  paintComponent(Graphics g){
    super.paintComponent(g);
    Draw(g);
   
}


private void  Draw(Graphics g){
    //background
    g.drawImage(sky, 0, 0,width,sky.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(cloud,cloudX, 0,cloud.getWidth(getFocusCycleRootAncestor()),cloud.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(backgroundImage, 0, 0,width,height,null);
    
    g.drawImage(redTrash, width/4, height/2 - 100,redTrash.getWidth(getFocusCycleRootAncestor()),redTrash.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(GreenTrash, width/4 + 250, height/2 - 100,GreenTrash.getWidth(getFocusCycleRootAncestor()),GreenTrash.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(YellowTrash, width/4 + 500, height/2 - 100,YellowTrash.getWidth(getFocusCycleRootAncestor()),YellowTrash.getHeight(getFocusCycleRootAncestor()),null);
    g.drawImage(OrangeTrash, width/4 +750, height/2 - 100,OrangeTrash.getWidth(getFocusCycleRootAncestor()),OrangeTrash.getHeight(getFocusCycleRootAncestor()),null);

}



@Override
public void actionPerformed(ActionEvent e) {
    cloudLoop.start();
    repaint();
}
}
