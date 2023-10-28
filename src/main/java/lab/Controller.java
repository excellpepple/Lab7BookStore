package lab;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * Name:
 * Username:
 */
public class Controller {

    @FXML
    private Button addBtn;

    @FXML
    private ListView<Book> availableBooks;

    @FXML
    private Label bannerLabel;

    @FXML
    private Label booksLabel;

    @FXML
    private Label cartLabel;

    @FXML
    private MenuItem checkOutBtn;

    @FXML
    private MenuItem clearCartBtn;

    @FXML
    private MenuItem darkTheme;

    @FXML
    private MenuItem exit;

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem lightTheme;
    @FXML
    private MenuItem pinkTheme;

    @FXML
    private MenuItem loadBooksBtn;

    @FXML
    private ImageView logo;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button removeBtn;

    @FXML
    private AnchorPane root;

    @FXML
    private ListView<Book> shoppingCart;

    @FXML
    private Menu shoppingMenu;

    @FXML
    private Menu themesMenu;

    @FXML
    public void initialize() {
        availableBooks.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        shoppingCart.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void loadBooks(){
        availableBooks.getItems().clear();
        FileChooser fileChooser = new FileChooser();
        File projectDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(projectDirectory);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null){
            try {
                RandomAccessFile file = new RandomAccessFile(selectedFile, "r");
                int index = 0;
                Book bk;
                while (index < file.length()){
                    try {
                        String title = file.readUTF();
                        double value = file.readDouble();
                        bk = new Book(title, value);
                        availableBooks.getItems().add(bk);
                        } catch (EOFException E){
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    public void swapToLightTheme(){
        root.getStylesheets().remove("darktheme.css");
        root.getStylesheets().remove("pink.css");
        root.getStylesheets().add("style.css");


    }
    public void swapToFancyTheme(){
        root.getStylesheets().remove("darktheme.css");
        root.getStylesheets().remove("style.css");
        root.getStylesheets().add("pink.css");


    }

    @FXML
    public void swapToDarkTheme(){
        root.getStylesheets().remove("style.css");
        root.getStylesheets().remove("pink.css");
        root.getStylesheets().add("darktheme.css");

    }

    @FXML
    public void exitApplication(){
        System.exit(0);
    }

    @FXML
    public void clearCart(){
        shoppingCart.getItems().clear();
    }

    @FXML
    public void checkOut(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Checkout");
        alert.setHeaderText("Checkout Details");
        ObservableList<Book> cart = shoppingCart.getItems();
        double subtotal = 0.0;
        for (Book book : cart){
            subtotal += book.getPrice();
        }
        double tax = subtotal * 0.007;
        alert.setContentText(String.format(    "%-15s$%-8.2f\n%-15s$%-8.2f\n%-15s$%-8.2f",
                "Subtotal:",
                subtotal,
                "Tax:",
                tax,
                "Total:",
                subtotal + tax));
        alert.showAndWait();

    }

    @FXML
    void addItems(ActionEvent event) {
        ObservableList<Book> selectedBooks =  availableBooks.getSelectionModel().getSelectedItems();
        shoppingCart.getItems().addAll(selectedBooks);
    }

    @FXML
    void removeItems(ActionEvent event) {
        ObservableList<Book> selectedBooks =  shoppingCart.getSelectionModel().getSelectedItems();
        shoppingCart.getItems().removeAll(selectedBooks);
    }

}






















