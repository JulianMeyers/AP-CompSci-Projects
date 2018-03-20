import javax.swing.*;
import java.awt.*;

public class ZMoveFrame extends JFrame {
    int screenWidth = 800;
    int screenHeight = 800;

    private ZMovePanel visualPanel;

    public ZMoveFrame() {
        super("ZPanel");
        setSize(screenWidth, screenHeight);
        getContentPane().setLayout(new GridLayout(1,1));
        visualPanel = new ZMovePanel(screenWidth,screenHeight);
        getContentPane().add(visualPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
