import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Setting extends JDialog implements ActionListener {
	private BombTable table;
	private Box baseBox, rowBox, colBox, bombBox, buttonBox;
	private JTextField rowField, colField, bombField;
	private JButton okButton, cancelButton;
	private LimitedDocument rowDocument, colDocument, bombDocument;
	private Setting(JFrame frame) {
		super(frame, true);
		this.setLocation(frame.getLocation().x + 100, frame.getLocation().y + 100);
		setTitle("设置");
		
		rowDocument = new LimitedDocument(8);	//参数为能输入的最大长度
		rowDocument.setAllowChar("0123456789");	//只能输入的字符
		colDocument = new LimitedDocument(8);
		colDocument.setAllowChar("0123456789");
		bombDocument = new LimitedDocument(8);
		bombDocument.setAllowChar("0123456789");
		
		baseBox = Box.createVerticalBox();
		rowBox = Box.createHorizontalBox();
		colBox = Box.createHorizontalBox();
		bombBox = Box.createHorizontalBox();
		buttonBox = Box.createHorizontalBox();
		rowField = new JTextField(10);
		rowField.setDocument(rowDocument);
		colField = new JTextField(10);
		colField.setDocument(colDocument);
		bombField = new JTextField(10);
		bombField.setDocument(bombDocument);
		okButton = new JButton("确定");
		cancelButton = new JButton("取消");
		
		baseBox.add(Box.createVerticalStrut(7));
		baseBox.add(rowBox);
		baseBox.add(Box.createVerticalStrut(3));
		baseBox.add(colBox);
		baseBox.add(Box.createVerticalStrut(3));
		baseBox.add(bombBox);
		baseBox.add(Box.createVerticalStrut(3));
		baseBox.add(buttonBox);
		rowBox.add(new JLabel("行数："));
		rowBox.add(rowField);
		colBox.add(new JLabel("列数："));
		colBox.add(colField);
		bombBox.add(new JLabel("雷数："));
		bombBox.add(bombField);
		buttonBox.add(okButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(cancelButton);
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(baseBox);
		validate();
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}
	public Setting(BombTable table) {
		this((JFrame)table);
		this.table = table;
	}
	public Setting(BombTable table, int row, int col, int bomb) {
		this(table);
		this.rowField.setText(String.valueOf(row));
		this.colField.setText(String.valueOf(col));
		this.bombField.setText(String.valueOf(bomb));
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			if (!check()) return;
			int rowNum = Integer.parseInt(rowField.getText());
			int colNum = Integer.parseInt(colField.getText());
			int bombNum = Integer.parseInt(bombField.getText());
			table.setTable(colNum, rowNum, bombNum);
			table.validate();
			table.pack();
			this.dispose();
		} else if (e.getSource() == cancelButton) {
			this.dispose();
		}
	}
	private boolean check() {
		int rowNum;
		int colNum;
		int bombNum;
		try {
			rowNum = Integer.parseInt(rowField.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "行数格式错误",
				        "数据错误", JOptionPane.WARNING_MESSAGE);
			rowField.requestFocus();
			return false;
		}
		try {
			colNum = Integer.parseInt(colField.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "列数格式错误",
				        "数据错误", JOptionPane.WARNING_MESSAGE);
			colField.requestFocus();
			return false;
		}
		try {
			bombNum = Integer.parseInt(bombField.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "雷数格式错误",
				        "数据错误", JOptionPane.WARNING_MESSAGE);
			bombField.requestFocus();
			return false;
		}
		if (rowNum == 0) {
			JOptionPane.showMessageDialog(this, "行数不能为零",
				        "数据错误", JOptionPane.WARNING_MESSAGE);
			rowField.requestFocus();
			return false;
		} else if (colNum == 0) {
			JOptionPane.showMessageDialog(this, "列数不能为零",
				        "数据错误", JOptionPane.WARNING_MESSAGE);
			colField.requestFocus();
			return false;
		} else if (bombNum ==0) {
			JOptionPane.showMessageDialog(this, "雷数不能为零",
				        "数据错误", JOptionPane.WARNING_MESSAGE);
			bombField.requestFocus();
			return false;
		} else if (bombNum >= rowNum * colNum) {
			JOptionPane.showMessageDialog(this, "雷数过多",
				        "数据错误", JOptionPane.WARNING_MESSAGE);
			bombField.requestFocus();
			return false;
		}
		return true;
	}
}