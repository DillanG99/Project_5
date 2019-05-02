# Project_5
Public Class HammingFrame
This Class creates a frame to perform basic calculations on 4-Letter strings and finds the average of strings of any length. First a JSlider is used to select the hamming dist being searched for using a string held in a JComboBox. The value selected with the slider will be displayed in a JTextField. Once you have the desired word and Hamming Distance selected you can click the JButton labeled "Show Station" to display all words with a hamming distance you selected in a scrollable JTextArea.
Next using the same JComboBox you can select the desired word you would like to perform calculations on and after pressing the JButton labeled "Calculate HD" it will show all words in a file and any words that the user may have added in 5 JTextAreas (0-4).
The user may also add words to have calculations performed on. To do this enter the ord you would like ti use in a JTextField and press the JButton labeled "Add Station" it will then be added to an arrayList of values so calculations may be peformed on it.
Next the Ascii Average calculator. First enter the word you would like to calculate the average of into a JTextField. It will then pass this word into the MesoAscii calAverage method and find the AsciiAverage and display it in a JTextArea.

Public Class HammingDist
This class does all calculations done in the mainPanel of HammingFrame. Performs calculations to find how many words in a file have a certain hamming dist. Will aslo return a list of values that have the same hamming distance of the word selected in HammingFrame. This class has two methods that are very close in what they do. returns an array read from a file, the other returns an array from an arrayList. These two methods are crucial to the output in HammingFrame. It can also tell how many words in a file have a specific HammingDist from the word in a file and the words that are added during the running of the program.

public abstract class MesoAsciiAbstract
serves as a blueprint for the MesoAsciiClass. Shows user that the calAverage method must be implemented.

public class MesoAscii
Computes the Ascii Averaage which is found in the Ascii Average Calculator in the freePanel of the HammingFrame. Unlike previous versions it can be done with any length word or String of characters.
