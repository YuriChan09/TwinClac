import javax.swing.*;

public class TwinCalc {
    public static void main(String[] args) {
        JFrame frame = new JFrame("T-Calc");
        frame.setContentPane(new GuiDesign().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
