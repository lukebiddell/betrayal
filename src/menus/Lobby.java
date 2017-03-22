package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import audio.BGM;
import lobby.LobbyServer;

public class Lobby extends JPanel {

	private JLabel lblNickname;
	private static final long serialVersionUID = 1L;
	private LobbyServer server;
	private Mainframe mainframe;
	private JButton btnBack;
	private JButton btnStart;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtNickname;
	private Font txtFont = new Font("Times New Roman", 30, 14);
	
	private BGM click;
	public Lobby(Mainframe m){
		
		super();
		this.mainframe = m;
		this.click = new BGM(10, "/Music/SFX_Click.wav");
//		this.setSize(640,420);

		
		setLayout(null);
	
		String[] col = new String[]{"IPaddress", "nickname"};
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		
			
		
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(488,163,386,186);
		scrollPane.setBackground(new Color(139, 69, 19));
		this.add(scrollPane, BorderLayout.CENTER);
	
		Object[] obs = {"127.0.0.1", "Its you!"};
		tableModel.addRow(obs);
	
		
		
	//	setLayout(new BorderLayout());
		
		btnBack = new JButton();
		btnBack.setBounds(20, 450, 180, 100);
		ImageIcon btnBackIcon = MenuButtonHandler.loadImageIcon("Resources/Images/back_button.png", 180, 100);
		btnBack.setIcon(btnBackIcon);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				//test = "back";
				m.setMenu(1);
			}
		});
		add(btnBack);
	
		lblNickname = new JLabel("New label");
		lblNickname.setIcon(MenuButtonHandler.loadImageIcon("Resources/Images/nickname_label.jpg", 386, 176));
		lblNickname.setBounds(10, 163, 386, 176);
		add(lblNickname);
		
		
		txtNickname = new JTextField();
		txtNickname.setBounds(199, 235, 149, 33);
		txtNickname.setColumns(10);
		txtNickname.setFont(txtFont);
		txtNickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtNickname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateOwnerNickname();
				
			}
		});
		add(txtNickname);
		
	
		btnStart = new JButton();
		btnStart.setBounds(694, 450, 180, 100);
		ImageIcon btnJoinGameIcon = MenuButtonHandler.loadImageIcon("Resources/Images/join_game_button.png", 180, 100);
		btnStart.setIcon(btnJoinGameIcon);
		btnStart.setBorderPainted(false);
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				click.playOnce();
				
			}
		});
		
		add(btnStart);

		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon = MenuButtonHandler.loadImageIcon("Resources/Images/betrayal_background.png", 900, 600);
		background.setIcon(backgroundIcon);
		add(background);
		
		
	
	}
	
	public void startServer(){
		this.server.start();
	}
	public void updatePlayers(InetAddress inetAddress, String string){
		Object[] obs = {inetAddress, string};
		tableModel.addRow(obs);
	}
	
	public void updateOwnerNickname(){

		tableModel.setValueAt(txtNickname.getText(), 0,1);
	}

}
