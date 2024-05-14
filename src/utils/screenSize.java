package utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class screenSize {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    public int boardWidth = screenSize.width;
    public int boardHeight = screenSize.height;
}
