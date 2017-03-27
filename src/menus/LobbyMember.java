package menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import audio.BGM;
import lobby.LobbyClient;
import network.TCPClient;

/*
 * @author Sam Dowell
 */
public class LobbyMember extends JPanel {

	
	private JLabel lblNickname;
	private static final long serialVersionUID = 1L;
	private LobbyClient client;
	private Mainframe mainframe;
	private JButton btnBack;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtNickname;
	private Font txtFont = new Font("Times New Roman", 30, 14);
	private String hostIP;
	
	public ArrayList<InetAddress> ips;
	public ArrayList<String> nicknames;
	
	private BGM click;
	public LobbyMember(Mainframe m){
		
		super();
		this.mainframe = m;
		this.click = new BGM(10, "/Music/SFX_Click.wav");
		this.client = new LobbyClient(this);
		this.hostIP = null;
//		this.setSize(640,420);

		
		setLayout(null);
	
		String[] col = new String[]{"IPaddress", "nickname"};
		tableModel = new DefaultTableModel(col, 0);
		table = new JTable(tableModel);
		
		this.ips = new ArrayList<InetAddress>();
		this.nicknames = new ArrayList<String>();
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ips.add(local);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		
		//ips.add(3)
		
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(488,163,386,186);
		scrollPane.setBackground(new Color(139, 69, 19));
		this.add(scrollPane, BorderLayout.CENTER);
	
		
		
		
	//	setLayout(new BorderLayout());
		
		btnBack = new JButton();
		btnBack.setBounds(20, 450, 180, 100);
		ImageIcon btnBackIcon = MenuButtonHandler.loadImageIcon("Resources/Images/back_button.png_2", 180, 100);
		btnBack.setIcon(btnBackIcon);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				click.playOnce();
				ips.clear();
				nicknames.clear();
				
				m.setMenu(1);
			}
		});
		add(btnBack);
	
		lblNickname = new JLabel("New label");
		lblNickname.setIcon(MenuButtonHandler.loadImageIcon("Resources/Images/nickname_label_2.jpg", 436, 124));
		lblNickname.setBounds(10, 193, 436, 124);
		add(lblNickname);
		
		
		txtNickname = new JTextField();
		txtNickname.setBounds(252, 250, 130, 23);
		txtNickname.setColumns(10);
		txtNickname.setFont(txtFont);
		txtNickname.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		add(txtNickname);
		
	
		
		JLabel background = new JLabel();
		background.setBounds(0, 0, 900, 600);
		ImageIcon backgroundIcon = MenuButtonHandler.loadImageIcon("Resources/Images/betrayal_background.png", 900, 600);
		background.setIcon(backgroundIcon);
		add(background);
		
		
	
	}
	
	public void updatePlayers(InetAddress inetAddress, String string){
		Object[] obs = {inetAddress, string};
		tableModel.addRow(obs);
	}
	
	public boolean startClient(String address, int port){
		return this.client.connect(port, address);
	}
	
	public void ownsetNickname(String name){
		this.txtNickname.setText(name);
		if(!nicknames.isEmpty())
			nicknames.remove(0);
			nicknames.add(0,name);
			txtNickname.setText(nicknames.get(0));
			txtNickname.setEnabled(false);
	}
	public void setIP(String ip) throws UnknownHostException{
			ips.add(InetAddress.getByName(ip));
		
	}
	public void setlocalIP(){
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			ips.add(0,local);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	
	}
	public boolean startClient(int port, String name){
		return this.client.connect(port, name);
	}
//	public void updateOwnerNickname(){
//
//		nicknames.remove(0);
//		nicknames.add(0, txtNickname.getText());
//		tableModel.setValueAt(nicknames.get(0),0,1);
//	}

	public boolean joinLobby(String nickname, String host) {
		if(this.client.connect(4444, host) == true){
			this.txtNickname.setText(nickname);
			this.client.addToQueue(nickname);
			this.hostIP = host;
			return true;
		}else {
			return false;
		}
	}

	public void startGame() {
		System.out.println("starting game");
		String[] args = new String[]{"4445", hostIP};
		TCPClient.main(args);
		
		
	}

	public void leave() {
		this.mainframe.setMenu(5);
		this.ips.clear();
		this.nicknames.clear();
		this.table.clearSelection();
		while(this.tableModel.getRowCount() > 0)
		this.tableModel.removeRow(0);
		
		
	}
}


