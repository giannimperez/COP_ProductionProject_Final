package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Date;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controller class handles all mouse/action events and GUI/database management
 *
 * @author Gianni Perez
 */
public class Controller {

    @FXML
    private TextField productName;
    @FXML
    private Button AddProduct;
    @FXML
    private TableView<Product> table;
    @FXML
    private TextField manufacturer;
    @FXML
    private ChoiceBox<String> productType;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ListView<Product> produceListView;
    @FXML
    private TableColumn<?, ?> idCol1;
    @FXML
    private TableColumn<?, ?> typeCol1;
    @FXML
    private TableColumn<?, ?> manuCol1;
    @FXML
    private TableColumn<?, ?> nameCol1;
    @FXML
    private TextArea prodLogTextArea;
    @FXML
    private Label addProdEmptyLabel;
    @FXML
    private TextField empName;
    @FXML
    private TextField empPass;
    @FXML
    private Button employeeButton;
    @FXML
    private Label ProduceEmptyLabel1;
    @FXML
    private Button prodLogButton;
    @FXML
    private TextArea empTextArea;

    //  Database credentials
    public String password = "password";
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/myDB";
    final String USER = "";
    final String PASS = "password";

    private static Connection conn;
    private Statement stmt = null;
    private PreparedStatement pstmt;

    /**
     * Creates the database connection
     *
     * @author Gianni Perez
     */
    private void initializeDB() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initializes the scene
     *
     * @author Gianni Perez
     */
    public void initialize() {
        ObservableList<String> list = FXCollections
                .observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        comboBox.setItems(list);
        comboBox.getSelectionModel().selectFirst();
        ObservableList<String> typeList = FXCollections.
                observableArrayList("AUDIO", "VISUAL", "AUDIO_MOBILE", "VISUAL_MOBILE"); // Use ifs to set itemType "if Audio set audio with constructor"
        productType.setItems(typeList);
        productType.getSelectionModel().selectFirst();
        initializeTableview1();
        initializeProdListView();
        initializeProdLodTextField();
    }

    /**
     * "Add Product button" function that adds current product info into the database
     *
     * @author Gianni Perez
     */
    @FXML
    void pressAddProduct() {
        System.out.println("Add Product Button Pressed!");
        if (productName.getText().trim().isEmpty() || manufacturer.getText().trim().isEmpty()) {
            addProdEmptyLabel.setText("Please enter Manufacturer/Product.");
        } else {
            try {
                initializeDB();
                System.out.println("1");
                String sql = "INSERT INTO Product(type, MANUFACTURER, name) VALUES ( ?, ?, ?)";
                System.out.println("2");
                pstmt = conn.prepareStatement(sql);
                System.out.println("3");
                if (productType.getValue().equals("AUDIO")) {
                    pstmt.setString(1, "Audio");
                } else if (productType.getValue().equals("VISUAL")) {
                    pstmt.setString(1, "Visual");
                } else if (productType.getValue().equals("AUDIO_MOBILE")) {
                    pstmt.setString(1, "Audio_Mobile");
                } else if (productType.getValue().equals("VISUAL_MOBILE")) {
                    pstmt.setString(1, "Visual_Mobile");
                }
                pstmt.setString(2, manufacturer.getText());
                pstmt.setString(3, productName.getText());
                System.out.println(pstmt);
                pstmt.executeUpdate();
                manufacturer.clear();
                productName.clear();
                addProdEmptyLabel.setText("");

            } catch (SQLException e) {
                e.printStackTrace();
            }
            initializeTableview1();
            initializeProdListView();
        }
    }

    private String prodNum;
    private int prodID;
    private String serNum;
    private Date dateProd;

    /**
     * "Record Production button" function that creates the productionRecord object.
     *
     * @author Gianni Perez
     */
    @FXML
    public void pressRecordProduction(ActionEvent event) {
        System.out.println("Record Production Button Pressed!");
        prodNum = comboBox.getValue(); // needs to be int for constructor (combobox change to int populate)

        if (produceListView.getSelectionModel().getSelectedItem() == null) {
            //System.out.println("YERRRRR");
            ProduceEmptyLabel1.setText("Please select a product");
        } else {
            try {
                initializeDB();
                int numProduced = comboBox.getSelectionModel().getSelectedIndex() + 1;
                Product product = produceListView.getSelectionModel().getSelectedItem();
                prodID = product.getId();
                int itemCount = 0;
                for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
                    ProductionRecord pr = new ProductionRecord(product, itemCount++);
                    serNum = pr.getSerialNum();
                    Date date = new Date(System.currentTimeMillis());

                    dateProd = date;
                    ProductionRecord prTest = new ProductionRecord(itemCount, prodID, serNum, date);
                    System.out.println("///////////////////////////////" + "\n" + prTest.toString() + "\n" + product + "\n" + "///////////////////////////////");

                    String sql = "INSERT INTO PRODUCTIONRECORD(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) VALUES ( ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    long time = date.getTime();
                    System.out.println("Time in Milliseconds: " + time);
                    Timestamp ts = new Timestamp(time);
                    System.out.println("Current Time Stamp: " + ts);
                    pstmt.setString(1, String.valueOf(itemCount));
                    pstmt.setString(2, String.valueOf(prodID));
                    pstmt.setString(3, String.valueOf(serNum));
                    pstmt.setString(4, String.valueOf(ts));
                    System.out.println(pstmt);
                    pstmt.executeUpdate();
                }
                ProduceEmptyLabel1.setText("");
                initializeProdLodTextField();
                comboBox.getSelectionModel().selectFirst();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Initializes the ProductLine page table view
     *
     * @author Gianni Perez
     */
    // Initializes ProductLine page tableView
    public void initializeTableview1() {
        ObservableList<Product> prodList1 = FXCollections.observableArrayList();
        idCol1.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol1.setCellValueFactory(new PropertyValueFactory<>("itemType"));
        manuCol1.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        nameCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.setItems(prodList1);
        try {
            initializeDB();
            String sql = "SELECT * FROM PRODUCT";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("Type").equalsIgnoreCase("Audio")) {
                    prodList1.add(new Product(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("MANUFACTURER"),
                            ItemType.AUDIO));
                    table.setItems(prodList1);
                } else if (rs.getString("Type").equalsIgnoreCase("Visual")) {
                    prodList1.add(new Product(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("MANUFACTURER"),
                            ItemType.VISUAL));
                    table.setItems(prodList1);
                } else if (rs.getString("Type").equalsIgnoreCase("Audio_Mobile")) {
                    prodList1.add(new Product(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("MANUFACTURER"),
                            ItemType.AUDIO_MOBILE));
                    table.setItems(prodList1);
                } else if (rs.getString("Type").equalsIgnoreCase("Visual_Mobile")) {
                    prodList1.add(new Product(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("MANUFACTURER"),
                            ItemType.VISUAL_MOBILE));
                    table.setItems(prodList1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the Produce tab list view
     *
     * @author Gianni Perez
     */
    private void initializeProdListView() {
        ObservableList<Product> prodList2 = FXCollections.observableArrayList();
        produceListView.setItems(prodList2);
        try {
            initializeDB();
            String sql = "SELECT * FROM PRODUCT";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("Type").equalsIgnoreCase("Audio")) {

                    prodList2.add(new Product(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("MANUFACTURER"),
                            ItemType.AUDIO));
                    produceListView.setItems(prodList2);
                } else if (rs.getString("Type").equalsIgnoreCase("Visual")) {

                    prodList2.add(new Product(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("MANUFACTURER"),
                            ItemType.VISUAL));
                    produceListView.setItems(prodList2);
                } else if (rs.getString("Type").equalsIgnoreCase("Audio_Mobile")) {

                    prodList2.add(new Product(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("MANUFACTURER"),
                            ItemType.AUDIO_MOBILE));
                    produceListView.setItems(prodList2);
                } else if (rs.getString("Type").equalsIgnoreCase("Visual_Mobile")) {

                    prodList2.add(new Product(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("MANUFACTURER"),
                            ItemType.VISUAL_MOBILE));
                    produceListView.setItems(prodList2);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * initializes the Production Log text area
     *
     * @author Gianni Perez
     */
    public void initializeProdLodTextField() {
        ObservableList<ProductionRecord> prodRec = FXCollections.observableArrayList();
        prodLogTextArea.clear();
        try {
            initializeDB();
            String sql = "SELECT * FROM PRODUCTIONRECORD";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                prodRec.add(new ProductionRecord(
                        rs.getInt("PRODUCTION_NUM"),
                        rs.getInt("PRODUCT_ID"),
                        rs.getString("SERIAL_NUM"),
                        rs.getTimestamp("DATE_PRODUCED")));
            }
            prodLogTextArea.appendText(prodRec.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        prodLogTextArea.setEditable(false);
    }

    /**
     * Deletes selected product on Product line tab
     *
     * @author Gianni Perez
     */
    @FXML
    void deleteProduct() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Product");
        alert.setHeaderText("Are you sure you want to DELETE this product?");
        alert.setContentText(null);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            try {
                System.out.println("Deleting Product");
                Product product = table.getSelectionModel().getSelectedItem();
                String selectedProduct = product.getName();
                String sql = "DELETE FROM PRODUCT WHERE NAME = " + "\'" + selectedProduct + "\';";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute();
                System.out.println("Product Deleted!");

            } catch (SQLException e) {
                System.out.println("Could Not Delete Product");
                e.printStackTrace();
            }
            initializeTableview1();
            initializeProdListView();
        }
    }

    /**
     * Deletes all data in the ProductionRecord table in the database
     *
     * @author Gianni Perez
     */
    @FXML
    void clearProdRecordList(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Production Record");
        alert.setHeaderText("Are you sure you want to DELETE ALL production records?");
        alert.setContentText(null);
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            try {
                String sql = "DELETE FROM PRODUCTIONRECORD";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.execute();
                prodLogTextArea.clear();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates new employee and displays in the Employee tab
     *
     * @author Gianni Perez
     */
    @FXML
    void addEmployee() {

        empTextArea.clear();
        Employee employee = new Employee(empName.getText(), empPass.getText());
        empTextArea.appendText(employee.toString());
        empName.clear();
        empPass.clear();
    }

}



