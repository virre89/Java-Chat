package chat.ourClientPackage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.*;

public class ChatGUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// General
	private ActionEventClass actionEventClass;
	
	// Variables related to chatGui
	private JButton sendButton;
	private JTextField textField;
	private TextArea textArea;
	
	// Variables related to menuGui
	//coming
	private JButton connectButton;
	private JTextField usernameTextField;
	
	public ChatGUI()
	{
		setTitle("Chat application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.actionEventClass = new ActionEventClass();
	}
	
	public void createChatGUI()
	{	
		JPanel chatInterface = new JPanel(new GridBagLayout());
		
		textField = new JTextField(20);
		textArea = new TextArea(5, 20);
		textArea.setEditable(false);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Helvetica", Font.BOLD, 10));
		
		sendButton = new JButton("Send");
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		// Add Keyevent
		// TODO better solution?
		textField.addKeyListener(new KeyEventClass("sendMsgToServer"));
		
		// Set actionCommands for the eventClass to determine
		// NOTE TODO Currently string dependencies between this and eventClass
		sendButton.setActionCommand("SendButtonCmd");
		sendButton.addActionListener(this.actionEventClass);
		
		GridBagConstraints layout = new GridBagConstraints();
		
		layout.gridwidth = GridBagConstraints.REMAINDER;		
		layout.fill = GridBagConstraints.BOTH;
		layout.anchor = GridBagConstraints.NORTH;
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		layout.gridx = 0;
		layout.gridy = 0;
		chatInterface.add(scrollPane, layout);	
		
		layout.gridwidth = GridBagConstraints.RELATIVE;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.anchor = GridBagConstraints.SOUTHWEST;
		layout.weightx = 1.0;
		layout.weighty = 0.0;
		layout.gridx = 0;
		layout.gridy = 1;
		layout.ipady = 40;
		chatInterface.add(textField, layout);
		
		layout.gridwidth = GridBagConstraints.RELATIVE;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.anchor = GridBagConstraints.SOUTHEAST;
		layout.weightx = 0.0;
		layout.weighty = 0.0;
		layout.gridx = 1;
		layout.gridy = 1;
		layout.ipady = 40;
		chatInterface.add(sendButton, layout);

		textField.requestFocus();
		
		add(chatInterface);
		setSize(300,400);
		setVisible(true);
	}
	
	public void createMenuGUI()
	{
		JPanel menuInterface = new JPanel(new GridBagLayout());
		usernameTextField = new JTextField(20);
		connectButton = new JButton("Join");
		JLabel descriptionUsername = new JLabel("Enter username :");
		
		GridBagConstraints layout = new GridBagConstraints();
		
		layout.gridwidth = GridBagConstraints.RELATIVE;
		layout.fill = GridBagConstraints.BOTH;
		layout.anchor = GridBagConstraints.NORTH;
		layout.weightx = 1.0;
		layout.weighty = 0.0;
		layout.gridx = 0;
		layout.gridy = 0;
		layout.ipady = 20;
		menuInterface.add(descriptionUsername, layout);
		
		layout.gridwidth = GridBagConstraints.RELATIVE;
		layout.fill = GridBagConstraints.BOTH;
		layout.anchor = GridBagConstraints.SOUTHWEST;
		layout.weightx = 1.0;
		layout.weighty = 0.0;
		layout.gridx = 0;
		layout.gridy = 1;
		layout.ipady = 20;
		menuInterface.add(usernameTextField, layout);
		
		layout.gridwidth = GridBagConstraints.RELATIVE;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.anchor = GridBagConstraints.SOUTH;
		layout.weightx = 0.0;
		layout.weighty = 0.0;
		layout.gridx = 1;
		layout.gridy = 1;
		layout.ipady = 20;
		menuInterface.add(connectButton, layout);
		
		// Add keyevent
		// TODO better solution?
		usernameTextField.addKeyListener(new KeyEventClass("verifyDetails"));
			
		// Set actionCommands for the eventClass to determine
		// NOTE TODO Currently string dependencies between this and eventClass
		connectButton.setActionCommand("ConnectButtonCmd");
		connectButton.addActionListener(this.actionEventClass);
		
		usernameTextField.requestFocus();
		
		add(menuInterface, BorderLayout.SOUTH);
		setSize(300,400);
		setVisible(true);
	}
	
	// GETTERS FOR : MENUGUI variables
	public JTextField getUsernameTextField()
	{
		return this.usernameTextField;
	}
	
	public JButton getConnectButton()
	{
		return this.connectButton;
	}
	
	// GETTERS FOR :  CHATGUI variables
	public TextArea getTextArea()
	{
		return this.textArea;
	}
	
	public JTextField getTextField()
	{		
		return this.textField;
	}
	
	public JButton getButton()
	{
		return this.sendButton;
	}
}
