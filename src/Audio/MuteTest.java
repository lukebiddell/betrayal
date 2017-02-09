package Audio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Font;

public class MuteTest {

	private JFrame frame;
	private boolean check;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MuteTest window = new MuteTest();
					window.frame.setVisible(true);
					
					boolean mute = window.getMuteStatus();
					System.out.println(mute);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MuteTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSlider slider = new JSlider();
		slider.setBorder(new TitledBorder(null, "Volume", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		slider.setMajorTickSpacing(25);
		slider.setPaintTicks(true);
		slider.setBounds(52, 13, 242, 67);
		frame.getContentPane().add(slider);
		
		Checkbox checkbox = new Checkbox("Mute\r\n");
		checkbox.setState(true);
		checkbox.setFont(new Font("Dialog", Font.PLAIN, 18));
		checkbox.setBackground(Color.ORANGE);
		checkbox.setBounds(103, 86, 80, 41);
		getCheckStatus(checkbox.getState());
		frame.getContentPane().add(checkbox);
	}
	
	public void getCheckStatus(boolean check){
		this.check = check;
	}
	
	public boolean getMuteStatus(){
		return check;
	}
}
