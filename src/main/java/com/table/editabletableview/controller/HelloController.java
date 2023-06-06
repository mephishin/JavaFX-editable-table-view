package com.table.editabletableview.controller;

import com.table.editabletableview.model.User;
import com.table.editabletableview.utility.ExpressionEvaluator;
import com.table.editabletableview.utility.LoadSave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    public static TableView<User> table_info_app;
    public static ObservableList<User> data_table;
    @FXML
    private TableView<User> table_info;
    @FXML
    private TableColumn<User, String> column_id, column_A, column_B, column_C;
    @FXML
    private TextField keywordTextField;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table_info_app = table_info;

        initializeCols();
        makeData();

        FilteredList<User> filteredList = new FilteredList<>(data_table, b->true);

        keywordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toLowerCase();

                if (user.getA().toLowerCase().contains(searchKeyword)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<User> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(table_info.comparatorProperty());

        table_info.setItems(sortedList);
    }

    private void initializeCols() {
        // User.java => id, name, email, notes;

        column_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_A.setCellValueFactory(new PropertyValueFactory<>("A"));
        column_B.setCellValueFactory(new PropertyValueFactory<>("B"));
        column_C.setCellValueFactory(new PropertyValueFactory<>("C"));
        editableCols();
    }

    private void editableCols() {
        column_id.setCellFactory(TextFieldTableCell.forTableColumn());
        column_id.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue()));

        column_A.setCellFactory(TextFieldTableCell.forTableColumn());
        column_A.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setA(
                ExpressionEvaluator.evalForTableView(e.getNewValue(), table_info)));

        column_B.setCellFactory(TextFieldTableCell.forTableColumn());
        column_B.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setB(
                ExpressionEvaluator.evalForTableView(e.getNewValue(), table_info)));

        column_C.setCellFactory(TextFieldTableCell.forTableColumn());
        column_C.setOnEditCommit(e -> e.getTableView().getItems().get(e.getTablePosition().getRow()).setC(
                ExpressionEvaluator.evalForTableView(e.getNewValue(), table_info)));

        table_info.setEditable(true);
    }
    
    private void makeData() {
        data_table = FXCollections.observableArrayList();

        for (int x = 1; x < 80; x++) {
            data_table.add(new User(String.valueOf(x), "0", "0", "0"));
        }

        table_info.setItems(data_table);
    }
    

    private String checkStr(String str) {
        return "";
    }

    public static TableView<User> getTable_info_app() {
        return table_info_app;
    }

    public static void setTable_info_app(TableView<User> table_info_app) {
        HelloController.table_info_app = table_info_app;
    }

    public static ObservableList<User> getData_table() {
        return data_table;
    }

    public static void setData_table(ObservableList<User> data_table) {
        HelloController.data_table = data_table;
    }

    public TableView<User> getTable_info() {
        return table_info;
    }

    public void setTable_info(TableView<User> table_info) {
        this.table_info = table_info;
    }

    public TableColumn<User, String> getColumn_id() {
        return column_id;
    }

    public void setColumn_id(TableColumn<User, String> column_id) {
        this.column_id = column_id;
    }

    public TableColumn<User, String> getColumn_A() {
        return column_A;
    }

    public void setColumn_A(TableColumn<User, String> column_A) {
        this.column_A = column_A;
    }

    public TableColumn<User, String> getColumn_B() {
        return column_B;
    }

    public void setColumn_B(TableColumn<User, String> column_B) {
        this.column_B = column_B;
    }

    public TableColumn<User, String> getColumn_C() {
        return column_C;
    }

    public void setColumn_C(TableColumn<User, String> column_C) {
        this.column_C = column_C;
    }


    public TextField getKeywordTextField() {
        return keywordTextField;
    }

    public void setKeywordTextField(TextField keywordTextField) {
        this.keywordTextField = keywordTextField;
    }

    public void getData(ActionEvent actionEvent) {
        System.out.println(table_info.getItems());
        System.out.println(table_info.getItems().get(1).getA());
        System.out.println(convert("=A1+B2"));
        System.out.println(convert("A1+B2"));
        System.out.println(convert("=A1+Bsadas2"));
        System.out.println(convert("12eds"));
        System.out.println(convert("='d;s'a[[p'"));
        System.out.println(ExpressionEvaluator.solveExpr("A1+B2*C3", table_info));

    }
    public String result(List<String> diff_ex) {
        if(diff_ex.size() > 1) {
            Integer first_number = Integer.parseInt(table_info.getItems().get(Integer.parseInt(diff_ex.get(2))).getVar(diff_ex.get(1)));
            Integer second_number = Integer.parseInt(table_info.getItems().get(Integer.parseInt(diff_ex.get(5))).getVar(diff_ex.get(4)));
            Integer res = math_func(first_number, second_number, diff_ex.get(3));
            System.out.println(math_func(first_number, second_number, diff_ex.get(3)));
            return res.toString();
        }
        else if (!diff_ex.isEmpty()) {
            System.out.println(diff_ex.get(0));
            return diff_ex.get(0);
        }
        return "";
    }

     public List<String> convert(String value) {
        String regex1 = "=[ABC][0-9]+[-+*/][ABC][0-9]+";
        String regex2 = "[A-Za-z0-9]+";
        Pattern pattern_ex = Pattern.compile(regex1);
        Matcher matcher_ex = pattern_ex.matcher(value);
        Pattern pattern_string = Pattern.compile(regex2);
        Matcher matcher_string = pattern_string.matcher(value);
        if (matcher_ex.matches()) {
            List<String> list = value.chars().mapToObj(e -> (char) e).map(Object::toString).toList();
            System.out.println(list);
            return list;
        }
        else if (matcher_string.matches()){
            List<String> diff_ex = new ArrayList<>();
            diff_ex.add(value);
            System.out.println(diff_ex);
            return diff_ex;
        }
        else return new ArrayList<>();
    }

    public Integer math_func(Integer first, Integer second, String sign) {
        return switch (sign) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> 0;
        };
    }


    public void saveData(ActionEvent actionEvent) throws IOException {
        LoadSave s = new LoadSave();
        s.Save(table_info.getItems());
    }

    public void loadData(ActionEvent actionEvent) throws IOException {
        LoadSave l = new LoadSave();
        ObservableList<User> loaded_dt = l.Load();
        System.out.println(loaded_dt);;
        table_info.setItems(loaded_dt);
    }
}