import java.io.IOException;

public class main{

	public static int nServidores;
	public static int nClientes;
	
	public main() {
		
	}		
	
public static void main(String[] args) throws IOException {
		
	if (args.length!=3){
		System.out.println("");
		System.out.println("");
		System.out.println("You must introduce three parameters.");
		System.out.println("The execution of the program is as follow:");
		System.out.println("java main <file> <number of servers> <max number of clients in buffer>");
	}
	else{
	    String nombre = new String (args[0]);
	    nServidores = Integer.parseInt(args[1]);
	    nClientes = Integer.parseInt(args[2]);
	    FEL fel = new FEL();
	    fel.ejecuta(nombre,nServidores,nClientes);
	}		
}
}
