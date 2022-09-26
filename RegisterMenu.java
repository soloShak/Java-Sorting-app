import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegisterMenu extends JFrame {

	private JPanel contentPane;
	public JTextField UserRegField;
	public JPasswordField PassRegField;
	public JPasswordField Pass2RegField;
	public JButton CreateButton;

	/**
	 * Create the frame.
	 */
	public RegisterMenu() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 284, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel MenuLabel = new JLabel("Register Menu");
		MenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MenuLabel.setFont(new Font("Arial Black", Font.PLAIN, 16));
		MenuLabel.setBounds(0, 11, 268, 35);
		contentPane.add(MenuLabel);
		
		UserRegField = new JTextField();
		UserRegField.setFont(new Font("Arial", Font.PLAIN, 12));
		UserRegField.setBounds(10, 81, 244, 35);
		contentPane.add(UserRegField);
		UserRegField.setColumns(10);
		
		PassRegField = new JPasswordField();
		PassRegField.setFont(new Font("Arial", Font.PLAIN, 12));
		PassRegField.setBounds(10, 152, 244, 35);
		contentPane.add(PassRegField);
		PassRegField.setColumns(10);
		
		Pass2RegField = new JPasswordField();
		Pass2RegField.setFont(new Font("Arial", Font.PLAIN, 12));
		Pass2RegField.setBounds(10, 223, 244, 35);
		contentPane.add(Pass2RegField);
		Pass2RegField.setColumns(10);
		
		JLabel UserRegLabel = new JLabel("Enter your Username:");
		UserRegLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		UserRegLabel.setBounds(10, 61, 234, 14);
		contentPane.add(UserRegLabel);
		
		JLabel PassRegLabel = new JLabel("Enter your Password:");
		PassRegLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		PassRegLabel.setBounds(10, 137, 234, 14);
		contentPane.add(PassRegLabel);
		
		JLabel Pass2RegLabel = new JLabel("Repeat your Password:");
		Pass2RegLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		Pass2RegLabel.setBounds(10, 208, 234, 14);
		contentPane.add(Pass2RegLabel);
		
		CreateButton = new JButton("Create Account");
		CreateButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		CreateButton.setBounds(65, 269, 143, 34);
		contentPane.add(CreateButton);
	}

}
