package entities;
import java.awt.*;
import javax.swing.*;

import utils.screenSize;
public class setFont  {
  private String text;
    private int y ;
    private int x; 
    private int size; 
    private String color;
    public int larguraTexto;
    boolean textCenter = true;

screenSize screenSize = new screenSize();

    public void  setText(String text, int y, int x,int size) {
        this.text = text;
        this.y = y;
        this.x = x;
        this.size = size;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    public void setTextCenter(boolean textCenter){
        this.textCenter = textCenter;
    }

    private void setCenteredFont(int width,int textWidth){

        if(x == 0){
            x = (width - textWidth) / 2;
        }
        else {
            if (textCenter){
                x = x - textWidth /2;
            }
            
        }
    }

    public void  Draw(Graphics g){

        int width = screenSize.boardWidth;

        Font fonte = new Font("Docu-Bold", Font.BOLD,size);

        FontMetrics metrics = g.getFontMetrics(fonte);
        int larguraTexto = metrics.stringWidth(text);
        setCenteredFont(width, larguraTexto);
        Graphics2D g2d = (Graphics2D) g;

        
        g2d.setFont(fonte);
    
        if(color != null){
            g2d.setColor(Color.decode(color));
        }else{
            g2d.setColor(Color.WHITE);
        }
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawString(text, x, y);
    }


}
