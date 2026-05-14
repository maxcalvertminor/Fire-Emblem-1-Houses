import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        PlayerController controller = new PlayerController(frame, 10);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();
        //System.out.println("Width: " + screenSize.getWidth());

        frame.setSize(width, height);
        TileGrid grid = new TileGrid(frame, new Vector2(width, height), new Vector2(width/2, height/2), 50);
        controller.SetGrid(grid);

        frame.addMouseListener(controller);

        Unit byleth = new Unit(frame, "gleeb", 20, 15, 15, 15, 30, 20, 15, 30, grid);
        byleth.SetGridPosition(new Vector2(5, 5));
    }
}
