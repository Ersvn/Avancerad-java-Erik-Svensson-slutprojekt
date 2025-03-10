package com.example.todo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TodoFrontend extends Application {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "http://localhost:8080/todos";
    private ListView<String> todoListView = new ListView<>();
    private List<Todo> todoCache = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Todo-List 3000");

        VBox vbox = new VBox();
        vbox.setSpacing(0);

        todoListView.setMinHeight(250);

        //Buttons
        Button postButton = new Button("Lägg till");
        Button toggleDoneButton = new Button("Klar");
        Button deleteButton = new Button("Ta bort");

        //Ny todo fält
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Beskrivning");

        //Layout
        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(postButton, toggleDoneButton, deleteButton);

        //getChildren vbox
        vbox.getChildren().addAll(todoListView, descriptionField, buttonBox);

        //action events knapp
        postButton.setOnAction(event -> {
            createTodo(descriptionField.getText());
            descriptionField.clear();
        });

        toggleDoneButton.setOnAction(event -> {
            Todo selectedTodo = getSelectedTodo();
            if (selectedTodo != null) {
                toggleTodoDone(selectedTodo.getId());
            } else {
                showAlert("Error", "Please select a Todo to mark as done/undone.");
            }
        });

        deleteButton.setOnAction(event -> {
            Todo selectedTodo = getSelectedTodo();
            if (selectedTodo != null) {
                deleteTodo(selectedTodo.getId());
            } else {
                showAlert("Error", "Please select a Todo to delete.");
            }
        });

        //Skapa scene vbox/knappar
        Scene scene = new Scene(vbox, 400, 335);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        postButton.setPrefWidth(128);
        postButton.setPrefHeight(50);
        postButton.setStyle("-fx-font-size: 16px;");

        toggleDoneButton.setPrefWidth(128);
        toggleDoneButton.setPrefHeight(50);
        toggleDoneButton.setStyle("-fx-font-size: 16px;");

        deleteButton.setPrefWidth(128);
        deleteButton.setPrefHeight(50);
        deleteButton.setStyle("-fx-font-size: 16px;");

        descriptionField.setPrefHeight(40);
        descriptionField.setStyle("-fx-font-size: 16px;");
        todoListView.setPrefHeight(40);
        todoListView.setStyle("-fx-font-size: 16px;");
    }

    //Hämta todos och visa lista
    private void getAllTodos() {
        Todo[] todosArray = restTemplate.getForObject(BASE_URL, Todo[].class);
        if (todosArray != null) {
            todoCache.clear();
            todoCache.addAll(List.of(todosArray));
            updateListView();
        }
    }

    //Skapa ny todo och hämta uppgifter
    private void createTodo(String description) {
        if (description.isEmpty()) {
            showAlert("Error", "Description cannot be empty");
            return;
        }
        Todo newTodo = new Todo(null, description, false);
        restTemplate.postForObject(BASE_URL, newTodo, Todo.class);
        getAllTodos();
    }

    //Bool done/undone - uppdatera lista
    private void toggleTodoDone(Long id) {
        Todo todoToUpdate = todoCache.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (todoToUpdate != null) {
            todoToUpdate.setDone(!todoToUpdate.isDone());
            restTemplate.put(BASE_URL + "/" + id, todoToUpdate);
            getAllTodos();
        } else {
            showAlert("Error", "Could not find Todo to update.");
        }
    }

    //delet todo
    private void deleteTodo(Long id) {
        try {
            restTemplate.delete(BASE_URL + "/" + id);
            getAllTodos(); // Uppdatera listan efter borttagning
        } catch (Exception e) {
            showAlert("Error", "Failed to delete Todo.");
            e.printStackTrace();
        }
    }

    //uppdatera listview
    private void updateListView() {
        List<String> displayList = new ArrayList<>();
        int index = 1;
        for (Todo todo : todoCache) {
            String displayText = index + ". " + todo.getDescription();
            if (todo.isDone()) {
                displayText += " (Färdig)";
            }
            displayList.add(displayText);
            index++;
        }
        todoListView.getItems().setAll(displayList);
    }

    //listview index-hämtning
    private Todo getSelectedTodo() {
        int selectedIndex = todoListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < todoCache.size()) {
            return todoCache.get(selectedIndex);
        }
        return null;
    }

    //Varningsruta
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}






















