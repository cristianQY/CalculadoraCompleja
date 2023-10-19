package dad.sumader;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class calculadora extends Application {
	private complejo complejo1 = new complejo();
	private complejo complejo2 = new complejo();
	private final StringProperty operation = new SimpleStringProperty();
	private complejo result = new complejo();

	@Override
	public void start(Stage primaryStage) {
		ComboBox<String> operatorCombo = new ComboBox<>();
		operatorCombo.getItems().addAll("+", "-", "*", "/");
		TextField real1Field = new TextField();
		TextField imaginario1Field = new TextField();
		TextField real2Field = new TextField();
		TextField imaginario2Field = new TextField();
		Button calculateButton = new Button("=");
		Label resultLabel = new Label();
		operatorCombo.valueProperty().bindBidirectional(operation);
		real1Field.textProperty().bindBidirectional(complejo1.realProperty(), new NumberStringConverter());
		imaginario1Field.textProperty().bindBidirectional(complejo1.imaginarioProperty(), new NumberStringConverter());
		real2Field.textProperty().bindBidirectional(complejo2.realProperty(), new NumberStringConverter());
		imaginario2Field.textProperty().bindBidirectional(complejo2.imaginarioProperty(), new NumberStringConverter());
		calculateButton.setOnAction(event -> {
			switch (operation.get()) {
			case "+":
				result.setReal(complejo1.getReal() + complejo2.getReal());
				result.setImaginario(complejo1.getImaginario() + complejo2.getImaginario());
				break;
			case "-":
				result.setReal(complejo1.getReal() - complejo2.getReal());
				result.setImaginario(complejo1.getImaginario() - complejo2.getImaginario());
				break;
			case "*":
				double a = complejo1.getReal();
				double b = complejo1.getImaginario();
				double c = complejo2.getReal();
				double d = complejo2.getImaginario();
				result.setReal(a * c - b * d);
				result.setImaginario(a * d + b * c);
				break;
			case "/":
				a = complejo1.getReal();
				b = complejo1.getImaginario();
				c = complejo2.getReal();
				d = complejo2.getImaginario();
				double denominator = c * c + d * d;
				result.setReal((a * c + b * d) / denominator);
				result.setImaginario((b * c - a * d) / denominator);
				break;
			default:
				break;
			}
		});
		resultLabel.textProperty()
				.bind(Bindings.createStringBinding(
						() -> String.format("(%f + %f)", result.getReal(), result.getImaginario()),
						result.realProperty(), result.imaginarioProperty()));
		HBox complex1Input = new HBox(real1Field, new Label("+"), imaginario1Field, new Label("i"));
		complex1Input.setAlignment(Pos.CENTER);
		HBox complex2Input = new HBox(real2Field, new Label("+"), imaginario2Field, new Label("i"));
		complex2Input.setAlignment(Pos.CENTER);
		VBox operator = new VBox(operatorCombo);
		operator.setAlignment(Pos.CENTER_LEFT);
		VBox complex3Inpunt = new VBox(15, complex1Input, complex2Input, resultLabel );
		complex3Inpunt.setAlignment(Pos.CENTER);
		VBox result = new VBox(calculateButton);
		result.setAlignment(Pos.CENTER);
		HBox root = new HBox(15, operator, complex3Inpunt, result);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root, 450, 240);
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
