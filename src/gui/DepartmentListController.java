package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{

	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColunmId;
	
	@FXML
	private TableColumn<Department, Integer> tableColunmName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	public void setDepartmentService(DepartmentService service) { //dependecy inject
		this.service = service;
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {		
		initializeNodes();
	}

	private void initializeNodes() {
		tableColunmId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColunmName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow(); //return config of this scene
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); // table follow this window  height config
		
	}
	
	public void updateTabView(){
		if(service == null) {
			throw new IllegalStateException("Service was null!"); //return error if service is null
		}
		List<Department> list = service.findAll(); //return list of DepartmentService
		obsList = FXCollections.observableArrayList(list); // instance obsList with data and reference to list
		tableViewDepartment.setItems(obsList); //load itens in tableView
		
	}

}
