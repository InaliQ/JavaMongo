package org.softli.utl.mongonube;


import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import org.softli.utl.mongonube.ConexionMySQLNube;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerAnime  implements Initializable {




    @FXML
        private Button btnDelete;

        @FXML
        private Button btnSave;

        @FXML
        private Button btnUpdate;

        @FXML
        private TableColumn<Anime,String> colCap;
    @FXML
    private TableColumn<Anime,String> colidAnime;

        @FXML
        private TableColumn<Anime, String> colNombre;

        @FXML
        private TableColumn<Anime, String> colTem;

        @FXML
        private TableView<Anime> tblCatalogo;

        @FXML
        private TextField txtAño;

        @FXML
        private TextField txtCap;

        @FXML
        private TextField txtGenero;

        @FXML
        private TextField txtNombre;

        @FXML
        private TextField txtTem;
        int myIndex;
        int id;
        String name;

    //-------------------------------funcionalidad de botones Uwu----------------------------------
    public void save(ActionEvent event) throws Exception {
        String Tnombre,Tcap,Ttem,Tanio,Tgene;
        Tnombre = txtNombre.getText().toString();
        Tcap = txtCap.getText().toString();
        Ttem = txtTem.getText().toString();
        Tanio = txtAño.getText().toString();
        Tgene = txtGenero.getText().toString();

        String sql = "INSERT INTO anime(nombre,capitulos,temporadas,anio,genero)VALUES(?,?,?,?,?)";

            ConexionMySQLNube mySQLNube = new ConexionMySQLNube();
            Connection conn = mySQLNube.open();
            CallableStatement statement = conn.prepareCall(sql);
        statement.setString(1,Tnombre);
        statement.setString(2,Tcap);
        statement.setString(3,Ttem);
        statement.setString(4,Tanio);
        statement.setString(5,Tgene);

        statement.executeUpdate();
        statement.close();
        mySQLNube.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ANIMES Uwu");
        alert.setContentText("ADD!");
        read();
        clean();

        }

    public void read()throws Exception{
        ObservableList<Anime> animeList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM anime";
            ConexionMySQLNube mySQLNube = new ConexionMySQLNube();
            Connection conn = mySQLNube.open();
            CallableStatement statement = conn.prepareCall(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Anime anime = new Anime();
                anime.setIdAnime(rs.getInt("idAnime"));
                anime.setNombre(rs.getString("nombre"));
                anime.setTemporadas(rs.getString("temporadas"));
                anime.setCapitulos(rs.getString("capitulos"));
                anime.setGenero(rs.getString("genero"));
                anime.setAnio(rs.getString("anio"));
                animeList.add(anime);
            }
            tblCatalogo.setItems(animeList);
            colidAnime.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getIdAnime()).asString());
            colNombre.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getNombre()));
            colTem.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getTemporadas()));
            colCap.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue().getCapitulos()));
        }catch (SQLException ex){
            Logger.getLogger(ControllerAnime.class.getName()).log(Level.SEVERE,null,ex);
        }
        tblCatalogo.setRowFactory(index ->{
            TableRow<Anime> animeTableRow = new TableRow<>();
            animeTableRow.setOnMouseClicked(event -> {
                if(event.getClickCount()==1 &&(!animeTableRow.isEmpty())){
                     myIndex = tblCatalogo.getSelectionModel().getSelectedIndex();
                     id = Integer.parseInt(String.valueOf(tblCatalogo.getItems().get(myIndex).getIdAnime()));
                    txtNombre.setText(tblCatalogo.getItems().get(myIndex).getNombre());
                    txtTem.setText(tblCatalogo.getItems().get(myIndex).getTemporadas());
                    txtCap.setText(tblCatalogo.getItems().get(myIndex).getCapitulos());
                    txtGenero.setText(tblCatalogo.getItems().get(myIndex).getGenero());
                    txtAño.setText(tblCatalogo.getItems().get(myIndex).getAnio());
                }
            });
            return animeTableRow;
        });


    }
    public void update(ActionEvent event) throws Exception {
        String Tnombre,Tcap,Ttem,Tanio,Tgene;
        myIndex = tblCatalogo.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tblCatalogo.getItems().get(myIndex).getIdAnime()));
        Tnombre = txtNombre.getText().toString();
        Tcap = txtCap.getText().toString();
        Ttem = txtTem.getText().toString();
        Tanio = txtAño.getText().toString();
        Tgene = txtGenero.getText().toString();

        String sql = "UPDATE anime SET nombre = ?,capitulos = ?,temporadas = ?,anio = ?,genero =? where idAnime =?";

        ConexionMySQLNube mySQLNube = new ConexionMySQLNube();
        Connection conn = mySQLNube.open();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,Tnombre);
        statement.setString(2,Tcap);
        statement.setString(3,Ttem);
        statement.setString(4,Tanio);
        statement.setString(5,Tgene);
        statement.setInt(6,id);

        statement.executeUpdate();
        statement.close();
        mySQLNube.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ANIMES Uwu");
        alert.setContentText("Updateddd!");
        read();
        clean();


    }


    public void delete(ActionEvent event) throws Exception {
        String sql = "DELETE FROM anime where idAnime = ?";
        myIndex = tblCatalogo.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(tblCatalogo.getItems().get(myIndex).getIdAnime()));
        try{
            ConexionMySQLNube mySQLNube = new ConexionMySQLNube();
            Connection conn = mySQLNube.open();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            Logger.getLogger(ControllerAnime.class.getName()).log(Level.SEVERE,null,ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ANIMES Uwu");
        alert.setContentText("DELETE!");
        read();

    }
    public void clean(){
        txtNombre.setText("");
       txtCap.setText("");
         txtTem.setText("");
       txtAño.setText("");
        txtGenero.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            read();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

