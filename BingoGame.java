package pa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
public class BingoGame extends JFrame implements ActionListener{

	JButton mkCardBtn;
	JPanel BingoCardPanel;
	JLabel bingoNumLabel[] = new JLabel[25];
	Container c;
	JButton startBtn;
	Random random = new Random();
	JFrame gameFrame;
	JTextArea numLog;
	JButton spinBtn;
	ArrayList<Integer> numList = new ArrayList<Integer>();
	public static void main(String[] args) {
		BingoGame frame = new BingoGame();
		frame.setVisible(true);

	}
	
	public BingoGame() {
		setBounds(10,10,600,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mkCardBtn = new JButton("ビンゴカード作成");
		mkCardBtn.addActionListener(this);
		
		BingoCardPanel = new JPanel();
		BingoCardPanel.setLayout(new GridLayout(5,5));
		
		for(int i=0; i<bingoNumLabel.length; i++) {
			String testNum = Integer.toString(i+1);
			bingoNumLabel[i] = new JLabel(testNum);
			LineBorder border = new LineBorder(Color.LIGHT_GRAY,2,true);
			bingoNumLabel[i].setBorder(border);
			BingoCardPanel.add(bingoNumLabel[i]);
		}
		
		JPanel p1_1 = new JPanel();
		p1_1.setLayout(new GridLayout(1,2));
		p1_1.add(mkCardBtn);
		p1_1.add(BingoCardPanel);
		
		startBtn  =new JButton("ゲーム開始");
		startBtn.addActionListener(this);
		
		c = getContentPane();
		c.add(p1_1, BorderLayout.CENTER);
		c.add(startBtn, BorderLayout.SOUTH); 
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mkCardBtn) {
			numList.clear();
			mkBingoCard();
		}else if(e.getSource() == startBtn) {
			insertGameFrame();
			System.out.println("スタート");
		}
	}
	
	public void mkBingoCard() { //ランダムな数字をカードに配置
		
		while(numList.size() < 25) {
			int num = random.nextInt(100);
			if(numList.indexOf(num) == -1) {
				numList.add(num);
			}
		}
		
		for(int i=0; i<numList.size(); i++) {
			String number =Integer.toString(numList.get(i));
			bingoNumLabel[i].setText(number);
		}
		
	}
	
	public void insertGameFrame() {
		gameFrame = new JFrame();
		gameFrame.setBounds(100, 100, 600, 400);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		numLog = new JTextArea(5,10);
		LineBorder black = new LineBorder(Color.black,2,true);
		numLog.setBorder(black);
		numLog.setEditable(false);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(1,2));
		panel1.add(BingoCardPanel);
		panel1.add(numLog);
		spinBtn = new JButton("Spin");
		spinBtn.addActionListener(this);
		
		Container c2 = gameFrame.getContentPane();
		c2.add(panel1,BorderLayout.CENTER);
		c2.add(spinBtn, BorderLayout.SOUTH);
		
		gameFrame.setVisible(true);
	}
	

}
