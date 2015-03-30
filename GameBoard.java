package homework1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.*;

public class GameBoard {
	Timer timer; // timer reference!
	CanvasPanel canvasPanel;
	RadiusAction ra;
	int s=0;
	Balloon balloon;
	//boolean isExist;
	double	distance;
	ArrayList<Balloon> balloons;
	JLabel score;
	int Time = 30;
	JLabel timeBased;
	JLabel finalScore;
	
	class StartTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.start();
		}
	}

	class StopTimer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			finalScore.setText("Your final score is  "+Integer.toString(s));
		}
	}
	
	class PauseTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			finalScore.setText(Integer.toString(s));
		}
		
	}

	class RadiusAction implements ActionListener {
		private int radius = 5;

		int getRadius() {
			return radius;
		}
		int getRandomPosition(){
			return (int)(Math.random()*100); 
			
		}

		void resetRadius() {
			radius = 5;
		}

		public void actionPerformed(ActionEvent e) {
			if(radius>14){
				radius--;
			}
			else
				radius++;
			Time--;
			timeBased.setText(Integer.toString(Time));
			if(Time==0)
				timer.stop();
//			canvasPanel.repaint();
			//Balloon balloon = new Balloon();
			for (Balloon balloon : balloons) {
				balloon.drift();
			}
			
			canvasPanel.repaint();
		}
	}

	class CanvasPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		CanvasPanel() {
			super();
			this.addMouseListener(new MouseAction());
		}

		protected void paintComponent(Graphics pen) {
			super.paintComponent(pen);
			pen.setColor(Color.blue);
			
				for(Balloon balloon:balloons){
					if(balloon.isExist()){
					
					//balloon.drift();
					//balloon.drift(N);
					pen.fillOval(balloon.getX(), balloon.getY(), balloon.getRadius(), balloon.getRadius());
					
				}
				else {
				//timer.stop();
				
				}
				}
				//timer.start();
			}
	}

	class MouseAction implements MouseListener {

		public void mouseClicked(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			//int x=0,y=0;
			for(Balloon balloon:balloons){
			distance = Math.pow(((x - balloon.getX())*(x -balloon.getX() )+(y-balloon.getY())*(y-balloon.getY())),0.5);
			if(distance<=balloon.getRadius())
			{
				balloon.setExist(false);
				canvasPanel.repaint();
				s++;
				score.setText("Score: "+Integer.toString(s));
				//timeBased.setText(Integer.toString(Time));
				
			}
			}}

		public void mouseEntered(MouseEvent event) {
		}

		public void mouseExited(MouseEvent event) {
		}

		public void mousePressed(MouseEvent event) {
		}

		public void mouseReleased(MouseEvent event) {
		}

}

	GameBoard() {
		JFrame frame = new JFrame("simple gui");
		frame.setVisible(true);
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 1));

		// create radius action
		ra = new RadiusAction();

		// create a timer to update the action
		timer = new Timer(1000, ra);

		// create the panel
		canvasPanel = new CanvasPanel();
		JPanel controlPanel = new JPanel();
		JPanel FinishPanel  = new JPanel();
		// create a button widget
		JButton startButton = new JButton("start");
		startButton.addActionListener(new StartTimer());

		JButton stopButton = new JButton("stop");
		stopButton.addActionListener(new StopTimer());

		JButton pauseButton = new JButton("pause");
		score = new JLabel("Score: ");
		timeBased = new JLabel("Time: ");
		finalScore = new JLabel ("Your final score is :  ");
		// add the button to the panel
		
		controlPanel.add(startButton);
		controlPanel.add(stopButton);
		controlPanel.add(pauseButton);
		controlPanel.add(score);
		controlPanel.add(timeBased);
		FinishPanel.add(finalScore);
		// canvas specific layout
		canvasPanel.setLayout(new GridLayout(1, 1));

		// add the panel to the frame
		frame.add(canvasPanel);
		frame.add(controlPanel);
		frame.add(FinishPanel);
		balloons = new ArrayList<Balloon>();
		
		for (int i = 0; i < 10; i++) {
			balloons.add(new Balloon());
		}
		
	}

	public static void main(String args[]) {
		new GameBoard();
	}
}
