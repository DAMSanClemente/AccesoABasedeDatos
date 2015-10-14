//package operacions;
import java.sql.*;
import java.util.*;
public class OperacionsBD{
	private Connection conexion;
	private String erro;
	public String getErro(){
		return erro;
	}
	public void abrirConexion(){
		try{
			String url = "jdbc:mysql://dbalumnos:3312/a14albertoab";
			conexion = DriverManager.getConnection(url, "a14albertoab", "");
			DatabaseMetaData meta=conexion.getMetaData();
		}catch(SQLException erro){
			System.out.println("Erro SQL: "+erro.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void cerrarConexion(){
		try{
			conexion.close();
		}catch(SQLException erro){
			System.out.println("Erro SQL: "+erro.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void engadeAlumno(Alumno alumno){
		try{		
			PreparedStatement sentenzaParametrizada=conexion.prepareStatement("insert into alumno(DNI,nome,apelidos,idade) values(?,?,?,?)");
			sentenzaParametrizada.setString(1,alumno.getDNI());
			sentenzaParametrizada.setString(2,alumno.getNome());
			sentenzaParametrizada.setString(3,alumno.getApelidos());
			sentenzaParametrizada.setInt(4,alumno.getIdade());
			sentenzaParametrizada.executeUpdate();
		}catch(SQLException erro){
			System.out.println("Erro SQL: "+erro.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public Alumno ConsultaAlumno(String dni){
		Alumno alumno=null;
		ResultSet datos=null;
		try{
			PreparedStatement sentenzaParametrizada=conexion.prepareStatement("select * from alumno where DNI=?");
			sentenzaParametrizada.setString(1,dni);
			datos=sentenzaParametrizada.executeQuery();
			alumno=new Alumno(datos.getString("DNI"),datos.getString("nome"),datos.getString("apelidos"),datos.getInt("idade"));
		}catch(SQLException erro){
			System.out.println("Erro SQL: "+erro.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return alumno;
	}
	public void borraAlumno(String dni){
		try{		
			PreparedStatement sentenzaParametrizada=conexion.prepareStatement("delete from alumno where DNI=?");
			sentenzaParametrizada.setString(1,dni);
			sentenzaParametrizada.executeUpdate();
		}catch(SQLException erro){
			System.out.println("Erro SQL: "+erro.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void modificaAlumno(Alumno alumno, String dni){
		try{		
			PreparedStatement sentenzaParametrizada=conexion.prepareStatement("update alumno(DNI,nome,apelidos,idade) values(?,?,?,?) where dni=?");		
			sentenzaParametrizada.setString(1,alumno.getDNI());
			sentenzaParametrizada.setString(2,alumno.getNome());
			sentenzaParametrizada.setString(3,alumno.getApelidos());
			sentenzaParametrizada.setInt(4,alumno.getIdade());
			sentenzaParametrizada.setString(5,dni);
			sentenzaParametrizada.executeUpdate();
		}catch(SQLException erro){
			System.out.println("Erro SQL: "+erro.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public ArrayList<Alumno> listadoAlumnos(){
		ResultSet datos=null;
		ArrayList<Alumno> alumnos=new ArrayList<Alumno>();
		try{
			Statement sentenza=conexion.createStatement();
			datos=sentenza.executeQuery("select * from alumno");
			while(datos.next()==true){ //En cada iteracion accedemos a unha fila da taboa
				/*System.out.println("DNI: "+datos.getString("DNI")+" "+
					"Nome: "+datos.getString("nome")+
					"Apelidos: "+datos.getString("apelido")+
					"Idade: "+datos.getInt("idade"));*/
					Alumno alumno=new Alumno(datos.getString("DNI"),datos.getString("nome"),datos.getString("apelido"),datos.getInt("idade"));
					alumnos.add(alumno);
			}//while
		}catch(SQLException erro){
			System.out.println("Erro SQL: "+erro.getMessage());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return alumnos;
	}
}