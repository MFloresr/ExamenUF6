package mario;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Vipi on 15/03/2016.
 */
public class Controller {

    @FXML
    private Button btnlluitar;
    @FXML
    private TextField poke1;
    @FXML
    private TextField poke12;
    @FXML
    private TextField poke2;
    @FXML
    private TextField poke22;
    @FXML
    private Connection con = null;
    public ResultSet resultat;
    public ResultSet resultatatac;
    public ResultSet resultattipo;
    ArrayList<Pokemon> equipo1=new ArrayList<Pokemon>();
    ArrayList<Pokemon> equipo2=new ArrayList<Pokemon>();

    private Connection conectarsebdd() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://192.168.4.1/pokemons", "pokemon", "pokemon");
        return con;
    }

    @FXML
    public void initialize(){
        try {
            conectarsebdd();
            PreparedStatement peticion = con.prepareStatement("SELECT DISTINCT POKEMON_ID, NOM, PES FROM POKEMONS;");
            //peticion.setString(1, poke1.getText());
            resultat = peticion.executeQuery();
            while(resultat.next()){
                System.out.println(resultat.getString("NOM"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void lluitarPokemons(Event event) throws SQLException {
            try {
                Connection con = conectarsebdd();
                PreparedStatement peticion = con.prepareStatement("SELECT DISTINCT POKEMON_ID, NOM, PES FROM POKEMONS WHERE NOM=?;");//System.out.println(poke1.getText());
                peticion.setString(1, poke1.getText());
                resultat = peticion.executeQuery();
                while (resultat.next()) {
                    Pokemon pokemon1 = new Pokemon();
                    pokemon1.setNom(resultat.getString("NOM"));
                    pokemon1.setVida(200);
                    pokemon1.setPes(resultat.getInt("PES"));
                    equipo1.add(pokemon1);
                    System.out.println(resultat.getString("NOM"));
                }

                PreparedStatement peticion2 = con.prepareStatement("SELECT DISTINCT POKEMON_ID, NOM, PES FROM POKEMONS WHERE NOM=?;");//System.out.println(poke1.getText());
                peticion2.setString(1, poke12.getText());
                resultat = peticion2.executeQuery();
                while (resultat.next()) {
                    Pokemon pokemon2 = new Pokemon();
                    pokemon2.setNom(resultat.getString("nom"));
                    pokemon2.setVida(200);
                    pokemon2.setPes(resultat.getInt("PES"));
                    equipo1.add(pokemon2);
                    System.out.println(resultat.getString("NOM"));
                }

                PreparedStatement peticion3 = con.prepareStatement("SELECT DISTINCT POKEMON_ID, NOM, PES FROM POKEMONS WHERE NOM=?;");
                peticion3.setString(1, poke2.getText());
                resultat = peticion3.executeQuery();
                while (resultat.next()) {


                    //PreparedStatement peticionatac = con.prepareStatement("SELECT DISTINCT VALOR FROM POKEMON_PODER WHERE POKEMON_ID=?;");//System.out.println(poke1.getText());
                    PreparedStatement peticionatac = con.prepareStatement("SELECT DISTINCT NOM, PODER_ID FROM PODERS");
                    //int id = resultat.getInt("POKEMON_ID");
                    //System.out.println(id);
                    //peticionatac.setString(1, String.valueOf(id));
                    resultatatac = peticionatac.executeQuery();
                    while (resultatatac.next()) {
                        System.out.println(resultatatac.getString("NOM"));
                    }


                    Pokemon pokemon3 = new Pokemon();
                    pokemon3.setNom(resultat.getString("nom"));
                    pokemon3.setVida(200);
                    pokemon3.setPes(resultat.getInt("PES"));
                    equipo2.add(pokemon3);
                    System.out.println(resultat.getString("NOM"));
                }

                PreparedStatement peticion4 = con.prepareStatement("SELECT DISTINCT POKEMON_ID, NOM, PES FROM POKEMONS WHERE NOM=?;");//System.out.println(poke1.getText());
                peticion4.setString(1, poke22.getText());
                resultat = peticion4.executeQuery();
                while (resultat.next()) {
                    Pokemon pokemon4 = new Pokemon();
                    pokemon4.setNom(resultat.getString("nom"));
                    pokemon4.setVida(200);
                    pokemon4.setPes(resultat.getInt("PES"));
                    equipo2.add(pokemon4);
                    System.out.println(resultat.getString("NOM"));
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                con.close();
            }
        }
}
