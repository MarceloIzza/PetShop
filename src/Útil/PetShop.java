package Útil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Classe para criar tabelas (subistituir nosso terminal)
public class PetShop {
    
     public Connection conecta() throws SQLException{
                      Connection conexao = null;

        String url = "jdbc:mysql://localhost/petshop";  //Nome da base de dados
        String user = "root"; //nome do usuário do MySQL
        String password = ""; //senha do MySQL
        try{
            conexao = DriverManager.getConnection(url, user, password);
}catch(SQLException sqlex){
System.out.println("Erro na conexão "+ sqlex);
}
        return conexao;
    }

    public void desconecta(Connection conexao){
    try{
    conexao.close();
    }catch(SQLException sqlex){
    System.out.println("Erro na conexão "+ sqlex);
    }
    }
    public void criaTabela(String nomeTabela,String atributos) throws SQLException {
            Statement statement = null;
            Connection conexao = null;

        try {
            conexao = conecta();
            statement = conexao.createStatement();

            String createTableSQL = "CREATE TABLE "+nomeTabela+"("+atributos+");";

        	System.out.println(createTableSQL);
                        // executa o comando de criação
        	statement.execute(createTableSQL);

        	System.out.println("Tabela \"nomeTabela\" foi criada com sucesso!");

        } catch (SQLException e) {

        	System.out.println(e.getMessage());

        } finally {   // sempre feche o statement a conexão com banco

        	if (statement != null) {
        		statement.close();
        	}

        	if (conexao != null) {
        		conexao.close();
        	}

        }

    }
    
    public void criaBanco(String banco) throws SQLException{
        
        String sql="CREATE DATABASE "+banco;
        Connection conxao= conecta();
        Statement stmt = conxao.createStatement();
        stmt.execute(sql);
    
    }}
    /*
    public static void main(String args[]) { 
    * criaBanco("PetShop");
    * criaTabela("cliente", "'ID' INT NULL AUTO_INCREMENT,"+"'CPF' VARCHAR(11) NULL,"+"'ENDERECO' VARCHAR(45) NULL,"+"'TELEFONE' VARCHAR(45) NULL,"+"'NOME' VARCHAR(45) NULL,"+"'NASCIMENTO' VARCHAR(45) NULL,"+"PRIMARY KEY('ID')");
    * criaTabela("animal","`idAnimal` INT NULL AUTO_INCREMENT,"+
  "`Raca` VARCHAR(45) NULL,"+
  "`Cor` VARCHAR(45) NULL,"+
  "`Tamanho` VARCHAR(20) NULL,"+
  "`Obs` VARCHAR(45) NULL,"+
  "`Nome` VARCHAR(45) NULL,"+
  "`Nascimento` VARCHAR(45) NULL,"+
  "`Dono_ID` INT NOT NULL,"+
  "PRIMARY KEY (`idAnimal`, `Dono_ID`");
  * 
  *  criaTabela("servico","`ID` INT NULL AUTO_INCREMENT,"+
  "`Nome` VARCHAR(45) NULL,"+
  "`Descricao` VARCHAR(45) NULL,"+
  "PRIMARY KEY (`ID`");
}*/