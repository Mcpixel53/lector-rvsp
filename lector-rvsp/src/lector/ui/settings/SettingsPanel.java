package lector.ui.settings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lector.main.Lector;

public class SettingsPanel extends JPanel {

	public static final ImageIcon panelHeaderImage = new ImageIcon(SettingsPanel.class.getResource("/lector/resources/settings.png"));
	
	private static final long serialVersionUID = 6842657262859909156L;
	private static final int[] wordPerMinuteValues = {250, 300, 350, 400, 450, 500, 600, 700, 800, 900, 1000};
	
	private final JFrame frame;
	private int wpm;
	
	private JPanel entryBox(String labelText, JComponent content) {
		JPanel entry = new JPanel();
		entry.setLayout(new GridLayout(0, 2, 8, 0));
		JLabel label = new JLabel(labelText);
		label.setHorizontalAlignment(JLabel.RIGHT);
		entry.add(label);
		entry.add(content);
		return entry;
	}
	
	private Box wpmSliderBox() {
		Box sliderBox = Box.createHorizontalBox();
		final JSlider slider = new JSlider(0, 10);
		slider.setSnapToTicks(true);
		final JTextField value = new JTextField();
		value.setBorder(null);
		value.setEditable(false);
		value.setHorizontalAlignment(JTextField.RIGHT);
		value.setMinimumSize(new Dimension(80, 0));
		wpm = wordPerMinuteValues[slider.getValue()];
		value.setText(Integer.toString(wpm));
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				wpm = wordPerMinuteValues[slider.getValue()];
				value.setText(Integer.toString(wpm));
			}
		});
		sliderBox.add(value);
		sliderBox.add(slider);
		return sliderBox;
	}

	public SettingsPanel(JFrame _frame) {
		super();
		frame = _frame;
		
		setLayout(new BorderLayout());

		setBackground(Color.BLACK);
		JPanel whiteHeader = new JPanel();
		whiteHeader.setBackground(Color.WHITE);
		whiteHeader.setLayout(new BorderLayout());
		Box titleBox = Box.createHorizontalBox();
		titleBox.add(Box.createRigidArea(new Dimension(20, 20)));
		titleBox.add(new JLabel("Settings"));
		whiteHeader.add(titleBox, BorderLayout.WEST);
		whiteHeader.add(new JLabel(panelHeaderImage), BorderLayout.EAST);
		
		add(whiteHeader, BorderLayout.NORTH);
		
		JPanel content = new JPanel();
		content.setBackground(Color.LIGHT_GRAY);
		content.setLayout(new BorderLayout());
		
		Box settingsBox = Box.createVerticalBox();
		settingsBox.setBorder(BorderFactory.createEtchedBorder());

		settingsBox.add(entryBox("Words per minute:", wpmSliderBox()));

		content.add(settingsBox, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Save")) {
					Lector.getControl().setWordsPerMinute(wpm);
					frame.dispose();
				}
			}
		});
		
		buttonPanel.add(saveButton);
		
		content.add(buttonPanel, BorderLayout.SOUTH);
		
		add(content, BorderLayout.CENTER);
	}
}