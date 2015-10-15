import java.util.*;
import operacions.*;
public class ProgramaAlumno{
	public static void main(String[] args){
		OperacionsBD op=new OperacionsBD();
		int numero=0;
		String resposta="";
		op.abrirConexion();			
		do{	
			do{	
				System.out.println("Menu:");
				System.out.println("1. Mostrar alumnos");
				System.out.println("2. Consulta");
				System.out.println("3. Borrar");
				System.out.println("4. Modificar");
				System.out.println("5. Engadir");
				System.out.println("6. Salir");
				numero=Integer.parseInt(System.console().readLine());
				if(numero==1) mostrarAlumnos(op);
				if(numero==2) consulta(op);
				if(numero==3) borrar(op);
				if(numero==4) modificar(op);
				if(numero==5) engadirDatos(op);
			    if(numero==6) System.exit(1);
			}while(numero<1 || numero>6);
			System.out.println("Queres realizar outra operacion?");
			resposta=System.console().readLine();
		}while(resposta.equalsIgnoreCase("si"));
		op.cerrarConexion();
		if(op.getErro()!="") System.out.println("ERROS "+op.getErro());
	}
	public static void mostrarAlumnos(OperacionsBD op){
		ArrayList<Alumno>alumnos =op.listadoAlumnos();
		for(int x=0;x<alumnos.size();x++){
			System.out.println("DNI: "+alumnos.get(x).getDNI()+" "+
				"Nome: "+alumnos.get(x).getNome()+" "+
				"Apelidos: "+alumnos.get(x).getApelidos()+" "+
				"Idade: "+alumnos.get(x).getIdade());
		}
		if(op.getErro()!="") System.out.println("ERROS "+op.getErro());
	}
	public static void engadirDatos(OperacionsBD op){
		System.out.println("Vamos engadir un alumno");
		System.out.println("Escribe un DNI:");
		String dni=System.console().readLine();
		System.out.println("Escribe un nome:");
		String nome=System.console().readLine();
		System.out.println("Escribe un apelido:");
		String apelido=System.console().readLine();
		System.out.println("Escribe unha idade:");
		int idade=Integer.parseInt(System.console().readLine());
		Alumno alumno=new Alumno(dni,nome,apelido,idade);
		op.engadeAlumno(alumno);
		System.out.println("Alumno engadido:");
		if(op.getErro()!="") System.out.println("ERROS "+op.getErro());
	}
	public static void consulta(OperacionsBD op){
		System.out.println("Vamos facer unha consulta.");
		System.out.println("Escribe un DNI:");
		String dni=System.console().readLine();
		try{
			Alumno alumno=op.consultaAlumno(dni);
			System.out.println("DNI: "+alumno.getDNI()+" "+
				"Nome: "+alumno.getNome()+" "+
				"Apelidos: "+alumno.getApelidos()+" "+	
				"Idade: "+alumno.getIdade());
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		if(op.getErro()!="") System.out.println("ERROS "+op.getErro());
	}
	public static void borrar(OperacionsBD op){
		System.out.println("Vamos borrar un alumno.");
		System.out.println("Introduce o seu DNI: ");
		String dni=System.console().readLine();
		try{
			Alumno alumno=op.consultaAlumno(dni);
			System.out.println("DNI: "+alumno.getDNI()+" "+
				"Nome: "+alumno.getNome()+" "+
				"Apelidos: "+alumno.getApelidos()+" "+	
				"Idade: "+alumno.getIdade());
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		if(op.getErro()!="") System.out.println("ERROS "+op.getErro());
		System.out.println("Seguro que queres borralo?");
		String resposta=System.console().readLine();
		if(resposta.equalsIgnoreCase("si")) op.borraAlumno(dni);
		if(op.getErro()!="") System.out.println("ERROS "+op.getErro());
	}
	public static void modificar(OperacionsBD op){
		System.out.println("Vamos modificar un alumno.");
		System.out.println("Introduce o dni do alumno a modificar:");
		String dni=System.console().readLine();
		System.out.println("Introduce o novo DNI:");
		String novoDNI=System.console().readLine();
		System.out.println("Introduce o novo nome:");
		String nome=System.console().readLine();
		System.out.println("Introduce o novo apelido:");
		String apelido=System.console().readLine();
		System.out.println("Introduce a nova idade:");
		int idade=Integer.parseInt(System.console().readLine());
		Alumno alumno=new Alumno(dni,nome,apelido,idade);
		op.modificaAlumno(alumno,dni);
		if(op.getErro()!="") System.out.println("ERROS "+op.getErro());
	}
}
