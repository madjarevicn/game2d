

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimatedButton extends JPanel implements ActionListener {
	
	Timer timer;
	private double angle = 0;
	private double scale = 1;
	private double delta = 0.01;
	Rectangle.Float r = new Rectangle.Float(20, 20, 200, 200);

	public AnimatedButton() {
		timer = new Timer(10, this);
		timer.start();
	}

	public void paint(Graphics g) {
		int h = getHeight();
		int w = getWidth();

		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.translate(w / 2, h / 2);
		g2d.rotate(angle);
		g2d.scale(scale, scale);

		g2d.fill(r);
	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Moving star");
		frame.add(new AnimatedButton());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 250);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (scale < 0.01) {
			delta = -delta;
		} else if (scale > 0.99) {
			delta = -delta;
		}

		scale += delta;
		angle += 0.01;

		repaint();
	}
}