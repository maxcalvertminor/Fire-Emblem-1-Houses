import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        System.out.println("Width: " + screenSize.getWidth());

        frame.setSize(width, height);
        TileGrid grid = new TileGrid(frame, new Vector2(width, height), new Vector2(width/2, height/2), 50);
    }
}
