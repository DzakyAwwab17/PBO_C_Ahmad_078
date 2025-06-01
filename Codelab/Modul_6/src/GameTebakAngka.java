import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;

    public class GameTebakAngka extends Application {

        private int angkaRahasia;
        private int jumlaCoba;
        private Random random;

        private Label titleLabel;
        private Label instructionLabel;
        private TextField guessField;
        private Button guessButton;
        private Label feedbackLabel;
        private Label attemptsLabel;

        @Override
        public void start(Stage primaryStage) {
            random = new Random();
            generateNewNumber();

            createComponents();

            VBox mainLayout = createLayout();

            setupEventHandlers();

            Scene scene = new Scene(mainLayout, 400, 300);
            primaryStage.setTitle("Game Tebak Angka");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }

        private void createComponents() {
            titleLabel = new Label(" GAME TEBAK ANGKA");
            titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            titleLabel.setTextFill(Color.DARKBLUE);

            instructionLabel = new Label("Saya memikirkan angka 1-100. Bisakah kamu menebaknya?");
            instructionLabel.setFont(Font.font("Arial", 12));
            instructionLabel.setWrapText(true);

            guessField = new TextField();
            guessField.setPromptText("Masukkan tebakan Anda (1-100)");
            guessField.setMaxWidth(200);
            guessField.setFont(Font.font("Arial", 14));

            guessButton = new Button(" Coba Tebak!");
            guessButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            guessButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px 20px;");

            feedbackLabel = new Label("Silakan masukkan tebakan Anda!");
            feedbackLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            feedbackLabel.setTextFill(Color.DARKGRAY);

            attemptsLabel = new Label("Percobaan: " + jumlaCoba);
            attemptsLabel.setFont(Font.font("Arial", 12));
        }

        private VBox createLayout() {
            VBox mainLayout = new VBox(20);
            mainLayout.setAlignment(Pos.CENTER);
            mainLayout.setPadding(new Insets(30));
            mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #e3f2fd, #ffffff);");

            HBox inputSection = new HBox(10);
            inputSection.setAlignment(Pos.CENTER);
            inputSection.getChildren().addAll(guessField, guessButton);

            mainLayout.getChildren().addAll(
                    titleLabel,
                    instructionLabel,
                    inputSection,
                    feedbackLabel,
                    attemptsLabel
            );

            return mainLayout;
        }

        private void setupEventHandlers() {
            guessButton.setOnAction(e -> handleGuess());

            guessField.setOnAction(e -> handleGuess());
        }

        private void handleGuess() {
            String buttonText = guessButton.getText();

            if (buttonText.equals(" Main Lagi")) {
                resetGame();
                return;
            }

            String guessText = guessField.getText().trim();
            if (guessText.isEmpty()) {
                showFeedback(" Masukkan angka dull!", Color.RED);
                return;
            }

            try {
                int guess = Integer.parseInt(guessText);

                if (guess < 1 || guess > 100) {
                    showFeedback(" Angka harus antara 1-100!", Color.RED);
                    return;
                }

                jumlaCoba++;
                attemptsLabel.setText("Percobaan: " + jumlaCoba);

                if (guess == angkaRahasia) {
                    showFeedback(" BENAR! Angkanya adalah " + angkaRahasia + "!", Color.GREEN);
                    guessButton.setText(" Main Lagi");
                    guessButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 10px 20px;");
                    guessField.setDisable(true);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" Selamat!");
                    alert.setHeaderText("Tebakan Benar!");
                    alert.setContentText(angkaRahasia +
                            " adalah benar " + jumlaCoba + " percobaan!\n\nKlik 'Main Lagi' untuk bermain lagi.");
                    alert.showAndWait();

                } else if (guess < angkaRahasia) {
                    showFeedback(" Terlalu kecil! Coba angka yang lebih besar.", Color.ORANGE);
                } else {
                    showFeedback(" Terlalu besar! Coba angka yang lebih kecil.", Color.ORANGE);
                }

                guessField.clear();
                guessField.requestFocus();

            } catch (NumberFormatException ex) {
                showFeedback(" Input harus berupa angka!", Color.RED);
                guessField.clear();
            }
        }

        private void showFeedback(String message, Color color) {
            feedbackLabel.setText(message);
            feedbackLabel.setTextFill(color);
        }

        private void generateNewNumber() {
            angkaRahasia = random.nextInt(100) + 1; // 1-100
            jumlaCoba = 0;
            System.out.println("DEBUG: Target number = " + angkaRahasia);
        }

        private void resetGame() {
            generateNewNumber();

            guessButton.setText(" Coba Tebak!");
            guessButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px 20px;");
            guessField.setDisable(false);
            guessField.clear();
            guessField.requestFocus();

            showFeedback("Silakan masukkan tebakan Anda!", Color.DARKGRAY);
            attemptsLabel.setText("Percobaan: " + jumlaCoba);
        }

        public static void main(String[] args) {
            launch(args);

        }
    }

