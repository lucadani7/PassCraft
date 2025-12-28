package com.lucadani.ui;

import com.lucadani.logic.PasswordGenerator;
import com.lucadani.logic.StrengthCalculator;
import com.lucadani.model.PasswordConfig;
import com.lucadani.model.PasswordItem;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.ArrayList;
import java.util.List;

public class MainViewController {
    @FXML private Slider lengthSlider;
    @FXML private Label lengthLabel;
    @FXML private CheckBox lowercaseCheck;
    @FXML private CheckBox uppercaseCheck;
    @FXML private CheckBox digitsCheck;
    @FXML private CheckBox symbolsCheck;
    @FXML private Spinner<Integer> countSpinner;
    @FXML private Button generateBtn;
    @FXML private ListView<PasswordItem> passwordList;
    @FXML private ProgressBar strengthBar;
    @FXML private Label strengthLabel;
    @FXML private Button copyBtn;

    @FXML
    public void initialize() {
        lengthSlider.valueProperty().addListener((obs, oldV, newV) ->
                lengthLabel.setText("Length: " + newV.intValue())
        );

        // Spinner default
        countSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 50, 5));

        // Generate button
        generateBtn.setOnAction(e -> generatePasswords());

        // Copy button
        copyBtn.setOnAction(e -> copySelected());

        // ListView double click = copy
        passwordList.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                copySelected();
            }
        });
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void generatePasswords() {
        int length = (int) lengthSlider.getValue();
        boolean lowercase = lowercaseCheck.isSelected();
        boolean uppercase = uppercaseCheck.isSelected();
        boolean digits = digitsCheck.isSelected();
        boolean symbols = symbolsCheck.isSelected();
        int count = countSpinner.getValue();
        if (!lowercase && !uppercase && !digits && !symbols) {
            showAlert("Select at least one character type!");
            return;
        }
        PasswordConfig cfg = new PasswordConfig(length, lowercase, uppercase, digits, symbols);
        List<PasswordItem> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String pwd = PasswordGenerator.generate(cfg);
            int strength = StrengthCalculator.calculate(pwd);
            items.add(new PasswordItem(pwd, strength));
        }
        passwordList.getItems().setAll(items);
        if (items.isEmpty()) {
            strengthBar.setProgress(0);
            strengthLabel.setText("Strength: -");
        } else {
            PasswordItem first = items.getFirst();
            strengthBar.setProgress(first.strength() / 5.0);
            strengthLabel.setText("Strength: " + StrengthCalculator.label(first.strength()));
        }
    }

    private void copySelected() {
        PasswordItem selected = passwordList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(selected.value());
        clipboard.setContent(content);
        showAlert("Password copied to clipboard!");
    }
}
