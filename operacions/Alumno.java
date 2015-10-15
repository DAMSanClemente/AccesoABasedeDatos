package operacions;
public class Alumno{
	private String dni;
	private String nome;
	private String apelidos;
	private int idade;
	public Alumno(String dni,String nome, String apelidos, int idade){
		this.dni=dni;
		this.nome=nome;
		this.apelidos=apelidos;
		this.idade=idade;
	}
	public String getDNI(){
		return dni;
	}
	public String getNome(){
		return nome;
	}
	public String getApelidos(){
		return apelidos;
	}
	public int getIdade(){
		return idade;
	}
}