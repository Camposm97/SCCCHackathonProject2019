package campos.util;


import campos.models.Major;
import campos.scene.layout.LoginPane;
import campos.scene.layout.SignUpPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FXUtil {
	public static final String TITLE = "Connect v1.0";
	public static final Insets DEFAULT_INSETS = new Insets(10);
	public static final double BT_WIDTH = 86;
	public static final double LOG_WIDTH = 380;
	public static final double LOG_HEIGHT = 524;
	
	public static VBox asVBox(Node...nodes) {
		VBox vBox = new VBox(10);
		vBox.setAlignment(Pos.CENTER);
		for (Node e : nodes)
			vBox.getChildren().add(e);
		return vBox;
	}
	
	public static HBox asHBox(Node...nodes) {
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.CENTER);
		for (Node e : nodes)
			hBox.getChildren().add(e);
		return hBox;
	}
	
	public static ComboBox<Gender> loadCbGender() {
		ComboBox<Gender> cb = new ComboBox<>();
		cb.setValue(Gender.Male);
		for (int i = 0; i < Gender.values().length; i++) {
			cb.getItems().add(Gender.values()[i]);
		}
		return cb;
	}
	
	public static ComboBox<Major> loadCbMajor() {
		ComboBox<Major> cb = new ComboBox<>();
		cb.setValue(Major.CSE);
		for (int i = 0; i < Major.values().length; i++) {
			cb.getItems().add(Major.values()[i]);
		}
		return cb;
	}
	
	public static Text loadLblTitle() {
		InnerShadow is = new InnerShadow();
		is.setOffsetX(4.0f);
		is.setOffsetY(4.0f);
		Text t = new Text(TITLE);
		t.setFill(Color.ALICEBLUE);
		t.setFont(Font.font(24));
		t.setEffect(is);
		return t;
	}
	
	public static void toSignUpPane(LoginPane loginPane) {
		Stage stage = (Stage) loginPane.getScene().getWindow();
		stage.setTitle("Sign Up");
		stage.setWidth(LOG_WIDTH);
		stage.setHeight(LOG_HEIGHT);
		BorderPane root = (BorderPane) loginPane.getScene().getRoot();
		root.setCenter(new SignUpPane(loginPane.getUserBag()));
	}
	
	public static void toLoginPane(SignUpPane signUpPane) {
		Stage stage = (Stage) signUpPane.getScene().getWindow();
		stage.setTitle("Login");
		stage.setWidth(LOG_WIDTH);
		stage.setHeight(LOG_HEIGHT);
		BorderPane root = (BorderPane) signUpPane.getScene().getRoot();
		root.setCenter(new LoginPane(signUpPane.getUserBag()));
	}
}
