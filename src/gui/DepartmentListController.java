package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable {

	@FXML
	private TableView<Department> tableViewDepartment;

	@FXML
	private TableColumn<Department, Integer> tableColumnId;//TableColumn - Tipo Entidade Department e Tipo Integer
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		System.out.println("onBtNewAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//macete para colunas funcionarem
		initializeNodes();
		
	}

	private void initializeNodes() {
		//comandos para iniciar apropriadamente as colunas da tabela
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		//abaixo macete para lista acompanhar altura e largura da janela
		Stage stage = (Stage) Main.getMainScene().getWindow();//getWindow eh uma superclasse do Stage assim tem q fazer downcasting
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

}
