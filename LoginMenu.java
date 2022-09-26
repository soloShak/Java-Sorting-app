import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginMenu extends JFrame {

	private JPanel contentPane;
	public JTextField UserField;
	public JPasswordField PasswordField;
	public JButton LoginButton;
	public JButton RegisterButton;

	/**
	 * Create the login frame frame.
	 */
	public LoginMenu() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 281);
		contentPane = new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel WelcomeLabel = new JLabel("Welcome to Sorting Analysing App");
		WelcomeLabel.setBounds(0, 11, 298, 27);
		WelcomeLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(WelcomeLabel);
		
		JLabel InstrLabel = new JLabel("Please Login to access app ");
		InstrLabel.setHorizontalAlignment(SwingConstants.CENTER);
		InstrLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		InstrLabel.setBounds(0, 43, 298, 21);
		contentPane.add(InstrLabel);
		
		UserField = new JTextField();
		UserField.setBounds(33, 87, 229, 27);
		contentPane.add(UserField);
		UserField.setColumns(10);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(33, 151, 229, 27);
		contentPane.add(PasswordField);
		PasswordField.setColumns(10);
		
		JLabel NameLabel = new JLabel("UserName:");
		NameLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		NameLabel.setBounds(33, 75, 150, 14);
		contentPane.add(NameLabel);
		
		JLabel PassWordLabel = new JLabel("Password:");
		PassWordLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		PassWordLabel.setBounds(33, 136, 150, 14);
		contentPane.add(PassWordLabel);
		
		LoginButton = new JButton("Login");
		LoginButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		LoginButton.setBounds(156, 204, 132, 27);
		contentPane.add(LoginButton);
		
		RegisterButton = new JButton("Register");
		RegisterButton.setFont(new Font("Arial Black", Font.PLAIN, 12));
		RegisterButton.setBounds(10, 204, 132, 27);
		contentPane.add(RegisterButton);
		
		JLabel RegisterLabel = new JLabel("Don't have account yet? ");
		RegisterLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		RegisterLabel.setBounds(10, 189, 132, 14);
		contentPane.add(RegisterLabel);
	}

}
