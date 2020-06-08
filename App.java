import javax.swing.JFrame;

public class App {

	public App() {
		JFrame frame = new JFrame();
		Screen screen = new Screen();
	       
        frame.add(screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake");
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);    
	}

	public static void main(String[] args) {  
		new App();
	}  

}
