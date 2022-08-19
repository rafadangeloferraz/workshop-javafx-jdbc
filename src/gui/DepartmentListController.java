package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {

	private DepartmentService service; //não fazer = new DepartmentService() pois gera acoplamento forte mas fazer outra forma de injetar
	
	@FXML
	private TableView<Department> tableViewDepartment;

	@FXML
	private TableColumn<Department, Integer> tableColumnId;//TableColumn - Tipo Entidade Department e Tipo Integer
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList;//aqui carrega os departamentos
	
	@FXML
	public void onBtNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogForm("/gui/DepartmentForm.fxml", parentStage);
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
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
	
	//metodo abaixo responsavel por acessar o serviço, carregar os departamentos e jogar na ObservableList
	public void updateTableView() {
		if (service == null) {
			throw new IllegalStateException("Service was null");//feito isso pq injeção de dependencia está manual, não está sendo feita por framework ou container de injeção automática
		}
		List<Department> list = service.findAll();//lista recupera deptos mocados
		obsList = FXCollections.observableArrayList(list);//carrega lista no obsList
		tableViewDepartment.setItems(obsList);//chama tableViewDepartment carregando itens na tableView
		//initEditButtons();
		//initRemoveButtons();
	}
	
	//abaixo metodo recebendo um parm de referencia para o Stage da janela q criou a janelinha de dialogo(pq tem q informar a ela quem e o Stage q criou essa janelinha de dialogo)
	private void createDialogForm(String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();  
			//abaixo palo na frente no outro
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Deparment data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false); //resizable - redimensiona ou n a janela
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
						
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
