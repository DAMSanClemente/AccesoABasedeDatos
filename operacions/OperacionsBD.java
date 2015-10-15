package operacions;
import java.sql.*;
import java.util.*;
public class OperacionsBD{
	private Connection conexion;
	private String erro="";
	public String getErro(){
		return erro;
	}
	public  void abrirConexion(){
		try{
			String url = "jdbc:mysql://dbalumnos:3312/a14albertoab";
			conexion = DriverManager.getConnection(url, "a14albertoab", "");
			DatabaseMetaData meta=conexion.getMetaData();
			System.out.println("Conexion establecida.");
		}catch(SQLException error){
			erro=erro+"\nErro en abrir conexion. "+error.getMessage();
		}catch(Exception ex){
			erro=erro+"\nErro en abrir conexion. "+ex.getMessage();
		}
	}
	public void cerrarConexion(){
		try{
			conexion.close();
		}catch(SQLException error){
			erro=erro+"\nErro en cerrar conexion. "+error.getMessage();
		}catch(Exception ex){
			erro=erro+"\nErro en cerrar conexion. "+ex.getMessage();
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
			sentenzaParametrizada.close();
		}catch(SQLException error){
			erro=erro+"\nErro en engadirAlumno. "+error.getMessage();
		}catch(Exception ex){
			erro=erro+"\nErro en engadirAlumno. "+ex.getMessage();
		}
	}
	public  Alumno consultaAlumno(String dni){
		Alumno alumno=null;
		ResultSet datos=null;
		try{
			PreparedStatement sentenzaParametrizada=conexion.prepareStatement("select * from alumno where DNI=?");
			sentenzaParametrizada.setString(1,dni);
			datos=sentenzaParametrizada.executeQuery();
			while(datos.next()==true){
				alumno=new Alumno(datos.getString("DNI"),datos.getString("nome"),datos.getString("apelidos"),datos.getInt("idade"));
			}	
			datos.close();
			sentenzaParametrizada.close();
		}catch(SQLException error){
			erro=erro+"\nErro en consultaAlumno. "+error.getMessage();
		}catch(Exception ex){
			erro=erro+"\nErro en consultaAlumno. "+ex.getMessage();
		}	
		return alumno;
	}
	public void borraAlumno(String dni){
		try{		
			PreparedStatement sentenzaParametrizada=conexion.prepareStatement("delete from alumno where DNI=?");
			sentenzaParametrizada.setString(1,dni);
			sentenzaParametrizada.executeUpdate();
			sentenzaParametrizada.close();
		}catch(SQLException error){
			erro=erro+"\nErro en borrarAlumno. "+error.getMessage();
		}catch(Exception ex){
			erro=erro+"\nErro en borraAlumno. "+ex.getMessage();
		}
	}
	public  void modificaAlumno(Alumno alumno, String dni){
		try{		
			PreparedStatement sentenzaParametrizada=conexion.prepareStatement("update alumno set DNI=?, nome=?, apelidos=?, idade=? where dni=?");		
			sentenzaParametrizada.setString(1,alumno.getDNI());
			sentenzaParametrizada.setString(2,alumno.getNome());
			sentenzaParametrizada.setString(3,alumno.getApelidos());
			sentenzaParametrizada.setInt(4,alumno.getIdade());
			sentenzaParametrizada.setString(5,dni);
			sentenzaParametrizada.executeUpdate();
			sentenzaParametrizada.close();
		}catch(SQLException error){
			erro=erro+"\nErro en modificaAlumno. "+error.getMessage();
		}catch(Exception ex){
			erro=erro+"\nErro en modificaAlumno. "+ex.getMessage();
		}
	}
	public  ArrayList<Alumno> listadoAlumnos(){
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
					Alumno alumno=new Alumno(datos.getString("DNI"),datos.getString("nome"),datos.getString("apelidos"),datos.getInt("idade"));
					alumnos.add(alumno);
			}//while
			datos.close();
			sentenza.close();
		}catch(SQLException error){
			erro=erro+"\nErro en listadoAlumnos. "+error.getMessage();
		}catch(Exception ex){
			erro=erro+"\nErro en listadoAlumnos. "+ex.getMessage();
		}
		return alumnos;
	}
}