/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 11 - Auto Complete
 * Name: Michael Wood
 * Created: 3/28/2024
 */
package woodm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * The Controller class is used to add functionality to the layout.fxml GUI
 */
public class Controller {
    private static final String DATA_FOLDER = "data/";
    @FXML
    private Button selectFileButton;
    @FXML
    private TextField searchBar;
    @FXML
    private Label totalSizeLabel;
    @FXML
    private Label searchSizeLabel;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField uLExactTime;
    @FXML
    private TextField uLAllMatchesTime;
    @FXML
    private TextField sLExactTime;
    @FXML
    private TextField sLAllMatchesTime;
    @FXML
    private TextField bSTExactTime;
    @FXML
    private TextField bSTAllMatchesTime;
    @FXML
    private TextField trieExactTime;
    @FXML
    private TextField trieAllMatchesTime;
    @FXML
    private TextField hashTableExactTime;
    @FXML
    private TextField hashTableAllMatchesTime;
    @FXML
    private Button unsortedListALButton;
    @FXML
    private Button unsortedListLLButton;
    @FXML
    private Button sortedListALButton;
    @FXML
    private Button sortedListLLButton;
    private File dataFile;
    private AutoCompleter unorderedList;
    private AutoCompleter orderedList;
    private AutoCompleter binarySearchTree;
    private AutoCompleter trie;
    private AutoCompleter hashTable;
    private List<String> unorderedListBackingList;
    private List<String> orderedListBackingList;
    private final Background greenBackground;

    /**
     * Initializes the Controller and
     * initializes the unorderedListBackingList
     * and the orderedListBackingList
     * to empty ArrayLists
     */
    public Controller() {
        unorderedListBackingList = new ArrayList<>();
        orderedListBackingList = new ArrayList<>();
        greenBackground = new Background(
                new BackgroundFill(Color.LIGHTGREEN, null, null));
    }

    @FXML
    private void selectFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        fileChooser.setInitialDirectory(new File(DATA_FOLDER));
        File temp = fileChooser.showOpenDialog(null);
        if(temp != null) {
            if(unorderedListBackingList instanceof ArrayList<String>
                    && orderedListBackingList instanceof ArrayList<String>) {
                unsortedListALButton.setBackground(greenBackground);
                sortedListALButton.setBackground(greenBackground);
            }
            listView.getItems().clear();
            searchBar.setText("");
            changeTimesTextField(null, "", "", null);
            this.dataFile = temp;
            selectFileButton.setText(this.dataFile.getName());
            searchBar.setEditable(true);
            processFile();
            unsortedListALButton.setDisable(false);
            unsortedListLLButton.setDisable(false);
            sortedListALButton.setDisable(false);
            sortedListLLButton.setDisable(false);
        }
    }

    @FXML
    private void createBackingList(ActionEvent event) {
        Button button = (Button) event.getSource();
        String listType = ((Label) ((HBox) button.getParent()).getChildren().getFirst()).getText();
        if(button.getText().equals("LL")) {
            if(listType.equals("Unsorted List")
                    && !(unorderedListBackingList instanceof LinkedList<String>)) {
                unorderedListBackingList = new LinkedList<>(unorderedListBackingList);
                unorderedList = new UnorderedList(unorderedListBackingList);
                unsortedListLLButton.setBackground(greenBackground);
                unsortedListALButton.setBackground(null);
            } else if(listType.equals("Sorted List")
                    && !(orderedListBackingList instanceof LinkedList<String>)) {
                orderedListBackingList = new LinkedList<>(orderedListBackingList);
                orderedList = new OrderedList(orderedListBackingList);
                sortedListLLButton.setBackground(greenBackground);
                sortedListALButton.setBackground(null);
            }
        } else {
            if(listType.equals("Unsorted List")
                    && !(unorderedListBackingList instanceof ArrayList<String>)) {
                unorderedListBackingList = new ArrayList<>(unorderedListBackingList);
                unorderedList = new UnorderedList(unorderedListBackingList);
                unsortedListALButton.setBackground(greenBackground);
                unsortedListLLButton.setBackground(null);
            } else if(listType.equals("Sorted List")
                    && !(orderedListBackingList instanceof ArrayList<String>)) {
                orderedListBackingList = new ArrayList<>(orderedListBackingList);
                orderedList = new OrderedList(orderedListBackingList);
                sortedListALButton.setBackground(greenBackground);
                sortedListLLButton.setBackground(null);
            }
        }
    }

    private void processFile() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        try (Scanner reader = new Scanner(dataFile)) {
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                unorderedListBackingList.add(line);
                orderedListBackingList.add(line);
            }
            unorderedList = new UnorderedList(unorderedListBackingList);
            orderedList = new OrderedList(orderedListBackingList);
            binarySearchTree = new BinarySearchTree(unorderedListBackingList);
            trie = new Trie(unorderedListBackingList);
            hashTable = new HashTable(unorderedListBackingList);
            updateSizeLabels();
        } catch (IOException e) {
            alert.setContentText("The file could not be read, please try again");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void updateUI() {
        if(searchBar.isEditable()) {
            listView.getItems().clear();
            String text = searchBar.getText();
            updateTimes(unorderedList, text);
            updateTimes(orderedList, text);
            updateTimes(binarySearchTree, text);
            updateTimes(trie, text);
            updateTimes(hashTable, text);
            updateSizeLabels();
        }
    }

    private void updateTimes(AutoCompleter autoCompleter, String text) {
        long startTime = System.nanoTime();
        autoCompleter.exactMatch(text);
        String exactMatchTime = AutoCompleter.format(System.nanoTime() - startTime);
        startTime = System.nanoTime();
        String[] allMatches = autoCompleter.allMatches(text);
        String allMatchesTime = AutoCompleter.format(System.nanoTime() - startTime);
        changeTimesTextField(autoCompleter, exactMatchTime, allMatchesTime, allMatches);
    }

    private void changeTimesTextField(AutoCompleter autoCompleter, String exactMatchTime,
                                      String allMatchesTime, String[] allMatches) {
        if(autoCompleter instanceof UnorderedList) {
            uLExactTime.setText(exactMatchTime);
            uLAllMatchesTime.setText(allMatchesTime);
        } else if(autoCompleter instanceof OrderedList) {
            sLExactTime.setText(exactMatchTime);
            sLAllMatchesTime.setText(allMatchesTime);
            listView.getItems().addAll(allMatches);
        } else if(autoCompleter instanceof BinarySearchTree) {
            bSTExactTime.setText(exactMatchTime);
            bSTAllMatchesTime.setText(allMatchesTime);
        } else if(autoCompleter instanceof Trie) {
            trieExactTime.setText(exactMatchTime);
            trieAllMatchesTime.setText(allMatchesTime);
        } else if(autoCompleter instanceof HashTable) {
            hashTableExactTime.setText(exactMatchTime);
            hashTableAllMatchesTime.setText(allMatchesTime);
        } else {
            uLExactTime.setText("");
            uLAllMatchesTime.setText("");
            sLExactTime.setText("");
            sLAllMatchesTime.setText("");
            bSTExactTime.setText("");
            bSTAllMatchesTime.setText("");
        }
    }

    private void updateSizeLabels() {
        totalSizeLabel.setText("Total Size: " + unorderedList.size());
        searchSizeLabel.setText("Search Size: " + listView.getItems().size());
    }
}
