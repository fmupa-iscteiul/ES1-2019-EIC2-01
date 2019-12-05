
public class Regra {

	private String nome;
	private String box1;
	private String box2;
	private String box3;
	private String box4;
	private String box5;
	private int number1;
	private int number2;

	public Regra(String nome, String box1, String box2, int number1) {
		this.nome = nome;
		this.box1 = box1;
		this.box2 = box2;
		this.number1 = number1;
	}
	
	public Regra(String nome, String box1, String box2, int number1, String box3, String box4, String box5, int number2) {
		this.nome = nome;
		this.box1 = box1;
		this.box2 = box2;
		this.box3 = box3;
		this.box4 = box4;
		this.box5 = box5;
		this.number1 = number1;
		this.number2 = number2;
	}

	public String getNome() {
		return nome;
	}

	public String getBox1() {
		return box1;
	}

	public String getBox2() {
		return box2;
	}
	
	public String getBox3() {
		return box3;
	}

	public String getBox4() {
		return box4;
	}

	public String getBox5() {
		return box5;
	}

	public int getNumber1() {
		return number1;
	}

	public int getNumber2() {
		return number2;
	}
	
	
	
}
