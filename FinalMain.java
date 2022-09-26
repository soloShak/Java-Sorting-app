import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JToggleButton;
import javax.swing.AbstractButton;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class FinalMain extends JFrame {
	
	final static int NumOfUsers = 100;
	final static String defaultText = "Please choose an algorithm. The size of array should be less than 10.000 and array should be splitted by one whiteSpace.";  
	WorkerClass worker;
	private JPanel contentPane;
	private JTextField ElemNumField;
	JTextPane OutputField;
	// these variables are used to determine which algorithm is currently selected
	boolean merge = false;
	boolean insert = false;
	boolean quick = false;
	boolean bubble = false;
	boolean heap = false;
	boolean select = false;
	// I made them global so program would not have to create a new object everytime
	MergeSort MergeObj = new MergeSort();
	QuickSort QuickObj = new QuickSort();
	InsertionSort InsertionObj = new InsertionSort();
	BubbleSort BubbleObj = new BubbleSort();
	HeapSort HeapObj = new HeapSort();
	SelectionSort SelectionObj = new SelectionSort();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// =============================================================================
					// If the user name and password are not correct then it shows error,
					// otherwise the main program can be accessed
					// And also we can create a new account by pressing the button "Create account"
					// and it will show us the registration menu
					// =============================================================================
					FinalMain MainFrame = new FinalMain();
					LoginMenu LoginFrame = new LoginMenu();
					RegisterMenu RegisterFrame = new RegisterMenu();
					LoginFrame.setVisible(true);
					LoginFrame.LoginButton.addActionListener(new ActionListener() {
						// ================================================================================
						// Following lines used for login into application
						// --------------------------------------------------------------------------------
						// First we create variables to store names and passwords found inside the files
						// and read files
						// After that using for loop we determine if user with such name and password
						// exists
						// If yes, then we can access the main frame, otherwise it shows error
						// ==============================================================================
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								boolean isFound = false; // to determine if user's name and password were found
								// variables to store all user names and passwords
								String UserNames[] = new String[NumOfUsers];
								String Passwords[] = new String[NumOfUsers];

								BufferedReader NameFr = new BufferedReader(new FileReader("username.txt"));
								BufferedReader PassFr = new BufferedReader(new FileReader("password.txt"));

								// reading names and passwords from file and storing them to string variables
								int i = 0;
								UserNames[i] = NameFr.readLine();
								while (UserNames[i] != null)
									i++;

								i = 0;
								Passwords[i] = PassFr.readLine();
								while (Passwords[i] != null)
									i++;

								// closing files
								NameFr.close();
								PassFr.close();

								for (i = 0; i < NumOfUsers; i++) {
									if ((LoginFrame.UserField.getText().equals(UserNames[i]))
											&& (LoginFrame.PasswordField.getText().equals(Passwords[i]))) {
										JOptionPane.showMessageDialog(null, "Login Successful!", "Success!",
												JOptionPane.INFORMATION_MESSAGE);
										LoginFrame.setVisible(false);
										MainFrame.setVisible(true);
										isFound = true;
										break;
									}
								}

								// the base case for login
								if ((LoginFrame.UserField.getText().equals("admin"))
										&& (LoginFrame.PasswordField.getText().equals("password"))) {
									JOptionPane.showMessageDialog(null, "Login Successful!", "Success!",
											JOptionPane.INFORMATION_MESSAGE);
									LoginFrame.setVisible(false);
									MainFrame.setVisible(true);
								} else {
									if (!isFound)
										throw new Exception();
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "No Files found!", "Error occured",
										JOptionPane.INFORMATION_MESSAGE);
							} catch (Exception err) {
								JOptionPane.showMessageDialog(null, "No such User found in the system!",
										"Error occured", JOptionPane.INFORMATION_MESSAGE);
								LoginFrame.UserField.setText("");
								LoginFrame.PasswordField.setText("");
							}
						}
					});
					LoginFrame.RegisterButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							LoginFrame.setVisible(false);
							RegisterFrame.setVisible(true);
							// action when Create account button is pressed
							RegisterFrame.CreateButton.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									String name = RegisterFrame.UserRegField.getText();
									String pass = RegisterFrame.PassRegField.getText();
									String passRep = RegisterFrame.Pass2RegField.getText();

									// =================================================================================
									// Main logic for Registration Menu:
									// ----------------------------------------------------------------------------------
									// First we check if all fields are filled if not than we collect error messages
									// from empty fields
									// If user wrote different passwords for Password field and Repeat Password
									// field than error message is collected
									// In the end if string variable ErrorMessage is not empty we throw an exception
									// Otherwise we write new user name and password to files, close them, and go
									// back to Login frame
									// =================================================================================
									try {
										FileWriter NameFw = new FileWriter("username.txt", true);
										FileWriter PassFw = new FileWriter("password.txt", true);
										String ErrorMessage = "";
										if (name.equals(""))
											ErrorMessage += ("Please fill UserName Field!\n");
										if (pass.equals(""))
											ErrorMessage += ("Please fill Password Field!\n");
										if (passRep.equals(""))
											ErrorMessage += ("Please fill Second Password Field!\n");

										if (!pass.equals(passRep))
											ErrorMessage = "Passwords don't match!";

										if (!ErrorMessage.equals("")) {
											NameFw.close();
											PassFw.close();
											throw new Exception(ErrorMessage);
										}
										NameFw.append(name + "\n");
										PassFw.append(pass + "\n");
										JOptionPane.showMessageDialog(null, "Registration completed!", "Success",
												JOptionPane.INFORMATION_MESSAGE);
										NameFw.close();
										PassFw.close();
										RegisterFrame.setVisible(false);
										LoginFrame.setVisible(true);
									} catch (Exception err) {
										JOptionPane.showMessageDialog(null, err.getMessage(), "Error occured",
												JOptionPane.INFORMATION_MESSAGE);
									}
								}
							});
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the main menu frame.
	 */
	public FinalMain() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 638);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel InLabel = new JLabel("Input:");
		InLabel.setFont(new Font("Arial Black", Font.BOLD, 13));
		InLabel.setBounds(10, 454, 155, 25);
		contentPane.add(InLabel);

		JLabel OutLabel = new JLabel("Ouput:");
		OutLabel.setFont(new Font("Arial Black", Font.BOLD, 13));
		OutLabel.setBounds(10, 511, 165, 25);
		contentPane.add(OutLabel);

		JLabel DescLabel = new JLabel("Description of Algorithm:");
		DescLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		DescLabel.setBounds(400, 11, 199, 25);
		contentPane.add(DescLabel);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setToolTipText("");
		progressBar.setBounds(10, 583, 631, 14);
		contentPane.add(progressBar);
		progressBar.setStringPainted(true);

		JPanel ButtonsPanel = new JPanel();
		ButtonsPanel.setBounds(400, 421, 241, 115);
		contentPane.add(ButtonsPanel);

		JLabel CodeLabel = new JLabel("Pseudo Code of Algorithm: ");
		CodeLabel.setFont(new Font("Arial Black", Font.PLAIN, 14));
		CodeLabel.setBounds(10, 11, 253, 25);
		contentPane.add(CodeLabel);

		JScrollPane DescPane = new JScrollPane();
		DescPane.setBounds(400, 40, 244, 347);
		contentPane.add(DescPane);

		JTextPane DescTextPane = new JTextPane();
		DescTextPane.setEditable(false);
		DescTextPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		DescTextPane.setText(defaultText);
		DescPane.setViewportView(DescTextPane);

		JScrollPane CodePane = new JScrollPane();
		CodePane.setBounds(10, 40, 356, 347);
		contentPane.add(CodePane);

		JTextPane CodeTextPane = new JTextPane();
		CodeTextPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		CodeTextPane.setText("Please choose an algorithm");
		CodeTextPane.setEditable(false);
		CodePane.setViewportView(CodeTextPane);

		JScrollPane InputPane = new JScrollPane();
		InputPane.setBounds(10, 478, 356, 35);
		contentPane.add(InputPane);

		JTextPane InputField = new JTextPane();
		InputField.setFont(new Font("Arial", Font.PLAIN, 14));
		InputPane.setViewportView(InputField);

		JScrollPane OutputPane = new JScrollPane();
		OutputPane.setBounds(10, 537, 356, 35);
		contentPane.add(OutputPane);

		OutputField = new JTextPane();
		OutputField.setEditable(false);
		OutputField.setFont(new Font("Arial", Font.PLAIN, 14));
		OutputPane.setViewportView(OutputField);

		ElemNumField = new JTextField();
		ElemNumField.setFont(new Font("Arial", Font.PLAIN, 14));
		ElemNumField.setBounds(10, 421, 356, 35);
		contentPane.add(ElemNumField);
		ElemNumField.setColumns(10);

		JLabel ElemNumLabel = new JLabel("Number of Elements:");
		ElemNumLabel.setFont(new Font("Arial Black", Font.PLAIN, 13));
		ElemNumLabel.setBounds(10, 398, 165, 25);
		contentPane.add(ElemNumLabel);

		// ===============================
		// All Buttons are placed here
		// ===============================

		int cnt[] = new int[1]; // when i used just int varible it kept me showing an error thus i used array of
								// one element
		cnt[0] = 0; // to count how many buttons are pressed

		// ========================================================================================
		// Simply saying, when any of the toggle buttons are pressed, user sees
		// the description of the sorting algorithm and its pseudo code, also variable
		// cnt becomes 1
		// and when we press the second button while other one is already pressed, 
		// cnt of course becomes 2 which is unacceptable,
		// Thus it shows user an error and disables last toggle button pressed
		// =========================================================================================

		JToggleButton MergeToggleButton = new JToggleButton("Merge Sort");
		MergeToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
					boolean ifSelected = abstractButton.getModel().isSelected();
					if (ifSelected) {
						cnt[0]++;
						merge = true;
						if (cnt[0] < 2) {
							CodeTextPane.setText(MergeObj.Code());
							DescTextPane.setText(MergeObj.Description());
						} else {
							Exception err = new Exception();
							throw err;
						}
					} else {
						// when button is unpressed we clean all text fields
						CodeTextPane.setText("Please choose an algorithm");
						DescTextPane.setText(defaultText);
						InputField.setText("");
						OutputField.setText("");
						ElemNumField.setText("");
						cnt[0] = 0;
						merge = false;
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null,
							"Only one Button of algorithm can be pressed at the same time!\nNumber of Buttons pressed: "
									+ cnt[0],
							"Error occured", JOptionPane.ERROR_MESSAGE);
					MergeToggleButton.setSelected(false);
					merge = false;
				}
			}
		});
		ButtonsPanel.setLayout(new GridLayout(0, 2, 5, 5));
		ButtonsPanel.add(MergeToggleButton);
		MergeToggleButton.setFont(new Font("Arial Narrow", Font.BOLD, 14));

		JToggleButton QuickToggleButton = new JToggleButton("Quick Sort");
		QuickToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
					boolean ifSelected = abstractButton.getModel().isSelected();
					if (ifSelected) {
						cnt[0]++;
						quick = true;
						if (cnt[0] < 2) {
							CodeTextPane.setText(QuickObj.Code());
							DescTextPane.setText(QuickObj.Description());
						} else {
							Exception err = new Exception();
							throw err;
						}
					} else {
						// when button is unpressed we clean all text fields
						CodeTextPane.setText("Please choose an algorithm");
						DescTextPane.setText(defaultText);
						InputField.setText("");
						OutputField.setText("");
						ElemNumField.setText("");
						cnt[0] = 0;
						quick = false;
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null,
							"Only one Button of algorithm can be pressed at the same time!\nNumber of Buttons pressed: "
									+ cnt[0],
							"Error occured", JOptionPane.ERROR_MESSAGE);
					QuickToggleButton.setSelected(false);
					quick = false;
				}
			}
		});
		QuickToggleButton.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		ButtonsPanel.add(QuickToggleButton);

		JToggleButton InsertionToggleButton = new JToggleButton("Insertion Sort");
		InsertionToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
					boolean ifSelected = abstractButton.getModel().isSelected();
					if (ifSelected) {
						cnt[0]++;
						insert = true;
						if (cnt[0] < 2) {
							CodeTextPane.setText(InsertionObj.Code());
							DescTextPane.setText(InsertionObj.Description());
						} else {
							Exception err = new Exception();
							throw err;
						}
					} else {
						// when button is unpressed we clean all text fields
						CodeTextPane.setText("Please choose an algorithm");
						DescTextPane.setText(defaultText);
						InputField.setText("");
						OutputField.setText("");
						ElemNumField.setText("");
						cnt[0] = 0;
						insert = false;
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null,
							"Only one Button of algorithm can be pressed at the same time!\nNumber of Buttons pressed: "
									+ cnt[0],
							"Error occured", JOptionPane.ERROR_MESSAGE);
					InsertionToggleButton.setSelected(false);
					insert = false;
				}
			}
		});
		InsertionToggleButton.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		ButtonsPanel.add(InsertionToggleButton);

		JToggleButton BubbleToggleButton = new JToggleButton("Bubble Sort");
		BubbleToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
					boolean ifSelected = abstractButton.getModel().isSelected();
					if (ifSelected) {
						cnt[0]++;
						bubble = true;
						if (cnt[0] < 2) {
							CodeTextPane.setText(BubbleObj.Code());
							DescTextPane.setText(BubbleObj.Description());
						} else {
							Exception err = new Exception();
							throw err;
						}
					} else {
						// when button is unpressed we clean all text fields
						CodeTextPane.setText("Please choose an algorithm");
						DescTextPane.setText(defaultText);
						InputField.setText("");
						OutputField.setText("");
						ElemNumField.setText("");
						cnt[0] = 0;
						bubble = false;
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null,
							"Only one Button of algorithm can be pressed at the same time!\nNumber of Buttons pressed: "
									+ cnt[0],
							"Error occured", JOptionPane.ERROR_MESSAGE);
					BubbleToggleButton.setSelected(false);
					bubble = false;
				}
			}
		});
		BubbleToggleButton.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		ButtonsPanel.add(BubbleToggleButton);

		JToggleButton HeapToggleButton = new JToggleButton("Heap Sort");
		HeapToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
					boolean ifSelected = abstractButton.getModel().isSelected();
					if (ifSelected) {
						cnt[0]++;
						heap = true;
						if (cnt[0] < 2) {
							CodeTextPane.setText(HeapObj.Code());
							DescTextPane.setText(HeapObj.Description());
						} else {
							Exception err = new Exception();
							throw err;
						}
					} else {
						// when button is unpressed we clean all text fields
						CodeTextPane.setText("Please choose an algorithm");
						DescTextPane.setText(defaultText);
						InputField.setText("");
						OutputField.setText("");
						ElemNumField.setText("");
						cnt[0] = 0;
						heap = false;
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null,
							"Only one Button of algorithm can be pressed at the same time!\nNumber of Buttons pressed: "
									+ cnt[0],
							"Error occured", JOptionPane.ERROR_MESSAGE);
					HeapToggleButton.setSelected(false);
					heap = false;
				}
			}
		});
		HeapToggleButton.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		ButtonsPanel.add(HeapToggleButton);

		JToggleButton SelectionToggleButton = new JToggleButton("Selection Sort");
		SelectionToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AbstractButton abstractButton = (AbstractButton) e.getSource();
					boolean ifSelected = abstractButton.getModel().isSelected();
					if (ifSelected) {
						cnt[0]++;
						select = true;
						if (cnt[0] < 2) {
							CodeTextPane.setText(SelectionObj.Code());
							DescTextPane.setText(SelectionObj.Description());
						} else {
							Exception err = new Exception();
							throw err;
						}
					} else {
						// when button is unpressed we clean all text fields
						CodeTextPane.setText("Please choose an algorithm");
						DescTextPane.setText(defaultText);
						InputField.setText("");
						OutputField.setText("");
						ElemNumField.setText("");
						cnt[0] = 0;
						select = false;
					}
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null,
							"Only one Button of algorithm can be pressed at the same time!\nNumber of Buttons pressed: "
									+ cnt[0],
							"Error occured", JOptionPane.ERROR_MESSAGE);
					SelectionToggleButton.setSelected(false);
					select = false;
				}
			}
		});
		SelectionToggleButton.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		ButtonsPanel.add(SelectionToggleButton);

		JButton StartButton = new JButton("Run");
		StartButton.setBounds(462, 537, 120, 35);
		contentPane.add(StartButton);
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				////////////////////////////////////////////////////////////////////////////////////////
				// Attention!!! The main algorithm for this program!!!                                //
				// ---------------------------------------------------------------------------------- //
				// First we divide the given string with n numbers into n substrings by splitting     //
				// it by whitespace                                                                   //
				// After that we sort numbers by one of the algorithms and inside the class           //
				// set its class variable "output" as sorted array                                    //
				// Then comes part with multiThreading where we update out progressBar and at         //
				// the same time print sorted array into outputField                                  //
				// ---------------------------------------------------------------------------------- //
				////////////////////////////////////////////////////////////////////////////////////////


				int size = 0;
				boolean isCorrect = true; // used to determine if user entered correct number of correct type elements
				OutputField.setText("");
				try {
					size = Integer.parseInt(ElemNumField.getText());
					int array[] = new int[size];
					String[] subStr = InputField.getText().split(" ");
					String output = "";

					if (subStr.length != size)
						throw new Exception();

					for (int i = 0; i < size; i++) {
						array[i] = Integer.parseInt(subStr[i]);
					}
					if (merge) {
						MergeObj.setInput(array);
						MergeObj.Sort(array, 0, size - 1);
						for (int i = 0; i < size; i++)
							output += (array[i] + " ");
					} else if (quick) {
						QuickObj.setInput(array);
						QuickObj.Sort(array, 0, size - 1);
						for (int i = 0; i < size; i++)
							output += (array[i] + " ");
					} else if (insert) {
						InsertionObj.setInput(array);
						InsertionObj.Sort(array);
						for (int i = 0; i < size; i++)
							output += (array[i] + " ");
					} else if (bubble) {
						BubbleObj.setInput(array);
						BubbleObj.Sort(array);
						for (int i = 0; i < size; i++)
							output += (array[i] + " ");
					} else if (heap) {
						HeapObj.setInput(array);
						HeapObj.Sort(array);
						for (int i = 0; i < size; i++)
							output += (array[i] + " ");
					} else if (select) {
						SelectionObj.setInput(array);
						SelectionObj.Sort(array);
						for (int i = 0; i < size; i++)
							output += (array[i] + " ");
					}
					if (output == "")
						JOptionPane.showMessageDialog(null, "Please choose algorithm!", "Warning",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(null, "Please enter integer values!", "Warning",
							JOptionPane.INFORMATION_MESSAGE);
					ElemNumField.setText("");
					InputField.setText("");
					isCorrect = false;
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Please enter integer values!", "Warning",
							JOptionPane.INFORMATION_MESSAGE);
					isCorrect = false;
				}

				if (isCorrect) {
					// getting the outputs from class variable
					if (merge)
						worker = new WorkerClass(MergeObj.getOutput(), size);
					else if (quick)
						worker = new WorkerClass(QuickObj.getOutput(), size);
					else if (insert)
						worker = new WorkerClass(InsertionObj.getOutput(), size);
					else if (bubble)
						worker = new WorkerClass(BubbleObj.getOutput(), size);
					else if (heap)
						worker = new WorkerClass(HeapObj.getOutput(), size);
					else if (select)
						worker = new WorkerClass(SelectionObj.getOutput(), size);

					// updating the progressBar
					worker.addPropertyChangeListener(new PropertyChangeListener() {
						@Override
						public void propertyChange(PropertyChangeEvent e) {
							if (e.getPropertyName().equals("progress")) {
								int newValue = (Integer) e.getNewValue();
								progressBar.setValue(newValue);
							}
						}
					});
					worker.execute();
				}
			}
		});
		StartButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
	}

	// ==============================================
	// Class for multiThreading
	// ----------------------------------------------
	// Gets a sorted array from the constructor
	// and prints it by copying each element of the array to the string,
	// which then publishes it to the outPut field
	// ==============================================
	public class WorkerClass extends SwingWorker<Integer, String> {
		int array[] = new int[10000];
		int size;

		public WorkerClass(int array[], int size) {
			for (int i = 0; i < size; i++)
				this.array[i] = array[i];
			this.size = size;
		}

		@Override
		protected Integer doInBackground() throws Exception {
			int i;
			String tempStr;
			for (i = 0; i < size; i++) {
				tempStr = array[i] + " ";
				publish(tempStr);
				Thread.sleep(100);
				setProgress((i + 1) * 100 / size);
			}
			return size;
		}

		protected void process(List<String> publishedVals) {
			for (int i = 0; i < publishedVals.size(); i++) {
				OutputField.setText(OutputField.getText() + publishedVals.get(i));
			}
		}
	}
}