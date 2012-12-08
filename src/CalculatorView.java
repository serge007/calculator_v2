import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CalculatorView extends JFrame implements ActionListener {
	private static final int OPERATION_PLUS = 1;
	private static final int OPERATION_MINUS = 2;
	private static final int OPERATION_MULTIPLY = 3;
	private static final int OPERATION_DIVIDE = 4;
	private static final int OPERATION_CLEAR = 5;
	private static final int OPERATION_RESULT = 6;
	private static final int POINT = 7;
	private JTextArea textArea;
	private int saveOperation = 0;
	private String firstValue = null;
	private String secondValue = null;

	public void openWindow() {
		init();
		setVisible(true);
	}
	protected void processWindowEvent(WindowEvent event) {
		if (event.getID() == WindowEvent.WINDOW_CLOSING) {
			exit();
		} else
			super.processWindowEvent(event);
	}
	private void exit(){
		dispose();
		System.exit(0);
	}
	private void init() {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("L&Floadingerror");
		}
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		createCalculator();
		setSizes();
	}

	private void setSizes() {
		java.awt.Dimension dim = getToolkit().getScreenSize();
		int dx = dim.width / 8;
		int dy = dim.height / 8;
		setSize(dx * 2, dy * 2);
		setLocation(dx, dy);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private void createCalculator() {
		GridLayout layout = new GridLayout(2, 1);
		getContentPane().setLayout(layout);
		textArea = new JTextArea();
		textArea.setFont(new Font("Serif", Font.ITALIC, 35));
		textArea.setAlignmentX(JTextArea.RIGHT_ALIGNMENT);
		getContentPane().add(textArea);
		getContentPane().add(createpanel());
	}

	private JPanel createpanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.weightx = 0.25;
		c.weighty= 0.25;
		c.gridy = 0;
		panel.add(createButton(7),c);
		c.gridx = 1;
		panel.add(createButton(8),c);
		c.gridx=2;
		panel.add(createButton(9),c);
		c.gridx=3;
		panel.add(createActionButton("+", OPERATION_PLUS),c);
		c.gridx=0;
		c.gridy=1;
		panel.add(createButton(4),c);
		c.gridx=1;
		panel.add(createButton(5),c);
		c.gridx=2;
		panel.add(createButton(6),c);
		c.gridx=3;
		panel.add(createActionButton("-", OPERATION_MINUS),c);
		c.gridx=4;
		c.gridy=0;
		c.gridheight=2;
		panel.add(createActionButton("C", OPERATION_CLEAR),c);
		c.gridx = 0;
		c.gridy=2;
		panel.add(createButton(1),c);
		c.gridx = 1;
		panel.add(createButton(2),c);
		c.gridx = 2;
		panel.add(createButton(3),c);
		c.gridx = 3;
		panel.add(createActionButton("*", OPERATION_MULTIPLY),c);
		c.gridy = 4;
		c.gridx = 0;
		c.gridwidth=2;
		panel.add(createButton(0),c);
		c.gridwidth=1;
		c.gridx = 2;
		panel.add(createActionButton(".", POINT),c);
		c.gridx = 3;
		panel.add(createActionButton("/", OPERATION_DIVIDE),c);
		c.gridx = 4;
		c.gridy = 3;
		c.gridheight=3;
		panel.add(createActionButton("=", OPERATION_RESULT),c);
		return panel;
	}



	// Create a Button of Action with listener
	private JButton createActionButton(String str, int operation) {
		JButton Button = new JButton(str);
		Button.addActionListener(new ActionListenerImpl(operation));
		return Button;
	}

	// Create Button with title and listener
	private JButton createButton(int value) {
		JButton Button = new JButton(Integer.toString(value));
		Button.addActionListener(new ButtonListenerImpl(value));
		return Button;
	}

	// Listener of Actions
	private class ActionListenerImpl implements ActionListener {
		public int active_operation = 0;

		ActionListenerImpl(int operation) {
			active_operation = operation;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(active_operation == 7){
				textArea.setText(textArea.getText() + ".");
			} else {
				if (firstValue != null) {
					if (textArea.getText().trim().length() > 0)
						secondValue = textArea.getText();
				} else {
					if (textArea.getText().trim().length() > 0)
						firstValue = textArea.getText();
				}
				textArea.setText("");
				if (active_operation == OPERATION_CLEAR) {
					firstValue = secondValue = null;
				}
				if (secondValue != null) {
					switch (saveOperation) {
					case 0:
						break;
					case 1:
						textArea.setText(CalculatorController.add(firstValue,
								secondValue));
						break;
					case 2:
						textArea.setText(CalculatorController.subtract(firstValue,
								secondValue));
						break;
					case 3:
						textArea.setText(CalculatorController.multiply(firstValue,
								secondValue));
						break;
					case 4:
						textArea.setText(CalculatorController.divide(firstValue,
								secondValue));
						break;
					}
					firstValue = secondValue = null;
			}
		}

			saveOperation = active_operation;
		}
	}

	// Listener of Number buttons
	private class ButtonListenerImpl implements ActionListener {
		private String number;
		private int button;

		ButtonListenerImpl(int value) {
			button = value;
			number = Integer.toString(button);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			number = textArea.getText() + button;
			textArea.setText(number);
		}
	}

}
