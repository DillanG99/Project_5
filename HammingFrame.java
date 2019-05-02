import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HammingFrame extends JFrame {	 
	//Holds the stations from the file and any words added
	private ArrayList<String> words = new ArrayList<String>();
	//Displays words with the same Ascii Average as the selected station (sorted)
	private JTextArea stationField = new JTextArea(30, 30);
	//Sets the station whose Ascii Average being searched for
	JLabel stationNamesInfo = new JLabel("Compare with:");
	private JComboBox<String> stationNames;
	//Will perform the actions displayed in hamm0-hamm4
	private JButton showStation = new JButton("Show Station");
	//Displays the number of Stations with a specific Hamming Distance (1-4)
	private JLabel hamm0Info = new JLabel("Distance 0");
	private JTextField hamm0 = new JTextField("0", 3);
	private JLabel hamm1Info = new JLabel("Distance 1");
	private JTextField hamm1 = new JTextField("0", 3);
	private JLabel hamm2Info = new JLabel("Distance 2");
	private JTextField hamm2 = new JTextField("0", 3);
	private JLabel hamm3Info = new JLabel("Distance 3");
	private JTextField hamm3 = new JTextField("0", 3);
	private JLabel hamm4Info = new JLabel("Distance 4");
	private JTextField hamm4 = new JTextField("0", 3);
	private JButton buttonHD = new JButton("Calculate HD");
	//Used to enter the name of the station being added
	private JTextField stationNameAdd = new JTextField(15);
	//Adds stationNameAdd to the stationNames list
	private JButton stationAdd = new JButton("Add Station");
	
	private static final int HAMM_MIN = 1;
	private static final int HAMM_INIT = 2;
	private static final int HAMM_MAX = 4;
	//Used to set the Hamming Distance being searched for
	private JSlider hammSlide = new JSlider(JSlider.HORIZONTAL, HAMM_MIN, HAMM_MAX, HAMM_INIT);
	JLabel hammingDistInfo = new JLabel("Enter Hamming Dist");
	JTextField hammingDist = new JTextField(Integer.toString(HAMM_INIT), 15);
	
	private JPanel mainPanel = new JPanel();
	private JPanel freePanel = new JPanel();
	
	JLabel title = new JLabel("Ascii Average Calculator");
	JLabel wordInfo = new JLabel("Enter Word");
	JTextField word = new JTextField(15);
	JButton calculate = new JButton("Calc Average");
	JTextArea average = new JTextArea(1, 3);
	private GridBagConstraints gbc = null;
	
	public void read(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		while(br.ready()) {
			String currLine = br.readLine();
			words.add(currLine);
		}
		br.close();
	}
	/**
	 * Performs basic calculations to find the Hamming Distance of a 4-Letter String and find the Ascii
	 * Average of any length of string and displays it in a GUI format.
	 * @throws IOException
	 */
	public HammingFrame() throws IOException {
		this.setLayout(new GridLayout(1, 2));
		mainPanel.setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hammingDistInfo, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hammingDist, gbc);
		
		hammSlide.setMajorTickSpacing(1);
		hammSlide.setPaintLabels(true);
		hammSlide.setPaintTicks(true);
		hammSlide.setPaintTrack(true);
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hammSlide, gbc);
		hammSlide.addChangeListener((e) -> {
			int currValue = hammSlide.getValue();
			hammingDist.setText(Integer.toString(currValue));
		});
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(showStation, gbc);
		showStation.addActionListener(e -> {
			try {
				String[] wordsOfHamming = HammingDist.findWordsOfHamming((String)stationNames.getSelectedItem(),
						Integer.parseInt(hammingDist.getText()), words);
				String text = "";
				for(String s : wordsOfHamming) {
					text = text + s + "\n";
				}
				stationField.setText(text);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		});
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		stationField.setEditable(false);
		JScrollPane scroller = new JScrollPane(stationField);
		scroller.setWheelScrollingEnabled(true);
		scroller.setPreferredSize(new Dimension(300, 300));
		
		mainPanel.add(scroller, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(stationNamesInfo, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(10, 10, 10, 10);
		this.read("Mesonet.txt");
		String[] vals = new String[0];
		String[] stations = words.toArray(vals);
		stationNames = new JComboBox(stations);
		mainPanel.add(stationNames, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(buttonHD, gbc);
		buttonHD.addActionListener((e) -> {
			String station = (String)stationNames.getSelectedItem();
				hamm1.setText(Integer.toString(HammingDist.findHammingList(station, 1, words)));
				hamm2.setText(Integer.toString(HammingDist.findHammingList(station, 2, words)));
				hamm3.setText(Integer.toString(HammingDist.findHammingList(station, 3, words)));
				hamm4.setText(Integer.toString(HammingDist.findHammingList(station, 4, words)));
		});
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm0Info, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm0, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm1Info, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm1, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm2Info, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm2, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm3Info, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm3, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm4Info, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(hamm4, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(stationAdd, gbc);
		stationAdd.addActionListener((e) -> {
			String name = stationNameAdd.getText();
			if(!words.contains(name) && name.length() == 4) {
				words.add(name);
				stationNames.addItem(name);
			}
			stationNameAdd.setText("");
		});
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(stationNameAdd, gbc);
		
		freePanel.setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		freePanel.add(title, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		freePanel.add(wordInfo, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 10, 10, 10);
		freePanel.add(word, gbc);
		
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		freePanel.add(calculate, gbc);
		calculate.addActionListener((e) -> {
			String val = word.getText();
			if(val != null) {
				MesoAscii currWord = new MesoAscii(val);
				int currAverage = currWord.calAverage();
				average.setText(Integer.toString(currAverage));
			}
			
		});
		
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(10, 10, 10, 10);
		freePanel.add(average, gbc);
		
		this.add(mainPanel);
		this.add(freePanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.pack();
	}
	public static void main(String[] args) throws IOException {
		new HammingFrame();
	}

}
