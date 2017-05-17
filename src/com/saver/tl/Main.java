package com.saver.tl;

import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField field;
	private JButton choose;
	private Tl tl;
	private JLabel label;
	private JComboBox<Object> jComboBox;

	public Main() {
		tl = new Tl("20170514000047341", "ywxK9MIUkpdyRIDkF6OR");
		setSize(400, 250);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jComboBox = new JComboBox<Object>();
		Country[] values = Country.values();
		for (Country country : values) {
			jComboBox.addItem(country.getName());
		}

		label = new JLabel("");
		field = new TextField("", 40);
		choose = new JButton("选择");
		choose.addActionListener(new ChooseListener());
		setLayout(new FlowLayout(FlowLayout.CENTER, 100, 50));
		add(field);
		add(jComboBox);
		add(choose);
		add(label);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.setVisible(true);
	}

	public class ChooseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			label.setText("");
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jFileChooser.showDialog(null, null);
			File file = jFileChooser.getSelectedFile();
			if (file == null) {
				return;
			}
			field.setText(file.getAbsolutePath());
			String languge = (String) jComboBox.getSelectedItem();
			boolean tl2 = tl.tl(file, languge);
			if (tl2) {
				label.setText("翻译成功");
			} else {
				label.setText("翻译失败");
			}
		}

	}

}
