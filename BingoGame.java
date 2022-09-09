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
	JPanel p1;
	JPanel p2;
	JPanel p3;
	JPanel p4;
	JPanel p5;
	
	
	JLabel bingoNumLabel1[] = new JLabel[5];
	JLabel bingoNumLabel2[] = new JLabel[5];
	JLabel bingoNumLabel3[] = new JLabel[5];
	JLabel bingoNumLabel4[] = new JLabel[5];
	JLabel bingoNumLabel5[] = new JLabel[5];
	LineBorder border = new LineBorder(Color.LIGHT_GRAY,2,true);
	GridLayout gl = new GridLayout(5,1);
	Container c;
	JButton startBtn;
	Random random = new Random();
	JFrame gameFrame;
	JTextArea numLog;
	JButton spinBtn;
	int spinNum;
	ArrayList<Integer> numList1 = new ArrayList<Integer>();
	ArrayList<Integer> numList2 = new ArrayList<Integer>();
	ArrayList<Integer> numList3 = new ArrayList<Integer>();
	ArrayList<Integer> numList4 = new ArrayList<Integer>();
	ArrayList<Integer> numList5 = new ArrayList<Integer>();
	ArrayList<Integer> spinNumbersList = new ArrayList<Integer>();
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
		BingoCardPanel.setLayout(new GridLayout(1,5));
		
		mkCard(p1,bingoNumLabel1,1);
		mkCard(p2,bingoNumLabel2,6);
		mkCard(p3,bingoNumLabel3,11);
		mkCard(p4,bingoNumLabel4,16);
		mkCard(p5,bingoNumLabel5,21);
		
		
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
			numList1.clear();
			numList2.clear();
			numList3.clear();
			numList4.clear();
			numList5.clear();
			mkRandomNum(numList1, 1, bingoNumLabel1);
			mkRandomNum(numList2, 16, bingoNumLabel2);
			mkRandomNum(numList3, 31, bingoNumLabel3);
			mkRandomNum(numList4, 46, bingoNumLabel4);
			mkRandomNum(numList5, 61, bingoNumLabel5);
		}else if(e.getSource() == startBtn) {
			insertGameFrame();
			System.out.println("スタート");
		}
		else if(e.getSource() == spinBtn) {

			do {
				spinNum = random.nextInt(75) + 1;
			}while(spinNumbersList.indexOf(spinNum) != -1); {
				spinNum = random.nextInt(75) + 1;
			}
			spinNumbersList.add(spinNum);
			String spinNumber = Integer.toString(spinNum);
			numLog.append(spinNumber + ",");
		}
		
		punchHole();
	}
	
	
	public void mkRandomNum(ArrayList<Integer> al, int i,JLabel[] la) {
		while(al.size() < 5) {
			int num = random.nextInt(15) + i;
			if(al.indexOf(num) == -1) {
				al.add(num);
			}
		}
		
		for(int j=0; j<al.size(); j++) {
			String number = Integer.toString(al.get(j));
			la[j].setText(number);
		}
	}
	
	public void mkCard(JPanel p, JLabel[] la, int i){ //カードのレイアウト
		p = new JPanel();
		p.setLayout(gl);
		for(int j=0;j<la.length; j++) {
			String testNum = Integer.toString(j + i);
			la[j] = new JLabel(testNum);
			la[j].setBorder(border);
			p.add(la[j]);
		}
		BingoCardPanel.add(p);
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
		numLog.setLineWrap(true);
		JPanel panel1 = new JPanel();
		GridLayout gridlayout = new GridLayout(1,2);
		gridlayout.setHgap(5);
		gridlayout.setVgap(5);
		panel1.setLayout(gridlayout);
		panel1.add(BingoCardPanel);
		panel1.add(numLog);
		spinBtn = new JButton("Spin");
		spinBtn.addActionListener(this);
		
		Container c2 = gameFrame.getContentPane();
		c2.add(panel1,BorderLayout.CENTER);
		c2.add(spinBtn, BorderLayout.SOUTH);
		
		gameFrame.setVisible(true);
	}
	
	public void punchHole() { //カードに穴あける。iはspinNum
		if(spinNum <= 15) {
			changeBackgroundColor(numList1, bingoNumLabel1);
		}else if(spinNum <= 30) {
			changeBackgroundColor(numList2, bingoNumLabel2);
		}else if(spinNum <= 45) {
			changeBackgroundColor(numList3, bingoNumLabel3);
		}else if(spinNum <= 60) {
			changeBackgroundColor(numList4, bingoNumLabel4);
		}else if(spinNum <= 75) {
			changeBackgroundColor(numList5, bingoNumLabel5);
		}
	}
	
	public void changeBackgroundColor(ArrayList<Integer> al, JLabel[] la) { //背景色を変える
		if(al.indexOf(spinNum) != -1) {
			la[al.indexOf(spinNum)].setOpaque(true);
			la[al.indexOf(spinNum)].setBackground(Color.red);
		}
	}
	

}
