package com.table.editabletableview.utility;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.table.editabletableview.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoadSave {
    private final String filename = "C:/Users/epish/IdeaProjects/JavaFX-editable-table-view/tables";

    public void Save(ObservableList<User> dataTable) {
        Gson gson = new Gson();
        try {
            FileWriter fl = new FileWriter(filename);
            gson.toJson(dataTable, fl);
//            fl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<User> Load() {
        ObservableList<User> data_table = FXCollections.observableArrayList();

        try (FileReader reader = new FileReader(filename)) {
            JsonArray jsonArray = new Gson().fromJson(reader, JsonArray.class);
//            JsonArray jsonArray = jsonObject.getAsJsonArray("datatable");
            for(JsonElement jOb: jsonArray) {
                User user = new Gson().fromJson(jOb, User.class);
                data_table.add(user);
            }
            return data_table;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}