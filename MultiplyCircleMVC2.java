
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MultiplyCircleMVC2 extends Application implements CircleEvents {
	private Button btControllerView = new Button("Show Controller and View");
	private ObservableList<CircleController2> circleControllerList;
	private ObservableList<CircleView2> circleViewList;
	private ObservableList<CircleModel2> circleModelList;
	private int circleCounter = 0;

	@Override
	public void start(Stage mvcStage) throws Exception {
		circleControllerList =FXCollections.observableArrayList();
		circleViewList=FXCollections.observableArrayList();
		circleModelList =FXCollections.observableArrayList();
		BorderPane pane = new BorderPane();
		pane.setCenter(btControllerView);
		btControllerView.setOnAction(e -> createNewControllerAndViewWindow());		
		mvcStage.setTitle("MultiplyCircleMVC2");
		mvcStage.setOnCloseRequest(e -> Platform.exit());
		
		Scene scene = new Scene(pane, S_WIDTH, S_HEIGHT/4);		
		mvcStage.setScene(scene);
		mvcStage.setX(0);
		mvcStage.setY(0);
		mvcStage.setResizable(false);
		mvcStage.setAlwaysOnTop(true);
		mvcStage.show();
	}

	public void createNewControllerAndViewWindow() {
		CircleModel2 model = new CircleModel2(circleCounter);
		CircleView2 view = new CircleView2(circleCounter);
		CircleController2 controller = new CircleController2(circleCounter);
		circleCounter++;
		
		circleModelList.add(model);
		circleViewList.add(view);
		circleControllerList.add(controller);
				
		Scene controllScene = new Scene(controller, S_WIDTH, S_HEIGHT/2);
		controller.setModel(model);	
		Stage controllStage = new Stage();
		controllStage.setTitle("Controller number " + circleCounter);
		controllStage.setScene(controllScene);
		controllStage.setMinWidth(S_WIDTH);
		controllStage.setX(X);
		controllStage.setY(Y);	
		controllStage.setResizable(false);
		controllStage.setAlwaysOnTop(true);
		controllStage.setOnCloseRequest(e->e.consume());
		controllStage.show();
		
		Scene viewScene = new Scene(view, S_WIDTH*2, S_HEIGHT);
		view.setModel(model);
		Stage viewStage = new Stage();
		viewStage.setTitle("View number " + circleCounter);
		viewStage.setScene(viewScene);	
		viewStage.setMinWidth(S_WIDTH);
		viewStage.setX(X*2);
		viewStage.setY(Y*2);
		viewStage.setAlwaysOnTop(true);
		viewStage.setOnCloseRequest(e->e.consume());
		viewStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}