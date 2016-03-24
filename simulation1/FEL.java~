import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class FEL {

	//GLOBAL VARIABLES OF THE PROGRAM --------------------------------------------------------
	static String eventoNuevo;
	static float tiempoNuevo;
	static int servidoresOcupados;
    static float tiempoE;
	static float tiempoS;
	static float clock; 
	static String[] palabrasSeparadas;
	static String cadena;
	static int buffer; 
	static int packet;
	static int numServidores;
	static int numClientes;
	static int menor;
	static int numeroLoco;
	static ArrayList<Float> tiemposBuffer;
	static ArrayList<Float> tiemposEntradaBuffer;
	static ArrayList<Float> tiemposServicioBuffer;
	//------------------------------------------------------------------------------------------
	//FEL PARAMETERS----------------------------------------------------------------------------
	static ArrayList<Float> tiemposServ;          //
	static ArrayList<String> eventosFel;          //TYPE OF FEL EVENTS
	static ArrayList<Float> tiemposFel;           //TIMES OF THE FEL EVENTS
	static ArrayList<Float> tiemposServicio;      //TIME OF SERVICE OF EACH EVENT
	static ArrayList<Float> tiemposEntrada;       //SYSTEM ENTRANCE TIME OF EACH EVENT
	//------------------------------------------------------------------------------------------
	//FEL RELATED VARIABLES ON EACH ITERATION---------------------------------------------------
	static String eventoInminente;                //TYPE OF INMINENT EVENT
	static Float tiempoInminente;                 //TIME WHEN THE EVENT OCCURS
	static Float tiempoServicioEventoInminente;   //SERVICE EVENT TIME
	static Float tiempoEntradaEventoInminente;    //SYSTEM ENTRANCE TIME OF THE EVENT
	
	
	//CONSTRUCTOR-------------------------------------------------------------------------------
	public FEL() {
		eventosFel=new ArrayList<String>();
		tiemposFel=new ArrayList<Float>();
		tiemposServicio=new ArrayList<Float>();
		tiemposEntrada=new ArrayList<Float>();
		buffer=0;
		packet=0;
		servidoresOcupados=0;
		palabrasSeparadas=new String[2];
		numeroLoco=1;
		tiemposBuffer=new ArrayList<Float>();
		tiemposEntradaBuffer= new ArrayList<Float>();
	    tiemposServicioBuffer=new ArrayList<Float>();
	}
	//------------------------------------------------------------------------------------------

	
	
	
	
	
	//HERE STARTS THE PROGRAM EXECUTION
	
	
	
	
	
	
	public static void ejecuta(String name,int serv,int client) throws IOException{
		FileWriter fichero = null;
		int a=0;
                PrintWriter pw = null;
		fichero = new FileWriter("salida.txt");
                pw = new PrintWriter(fichero);
	        System.out.println(" ");
		System.out.println(" ");
		//RECOVER PARAMETERS
		numServidores=serv;
		numClientes=client;
		File nombre = new File (name);
		FileReader lector;
		lector=new FileReader(nombre.getAbsolutePath());
		BufferedReader br;
                br= new BufferedReader (lector);
		//READ FIRST FILE LINE
		cadena=br.readLine();
		palabrasSeparadas = cadena.split(" ");
		tiempoE=Float.parseFloat(palabrasSeparadas[0]);
		tiempoS=Float.parseFloat(palabrasSeparadas[1]);
		//INCOMING SIMULATION MESSAGE
		System.out.println("SIMULACION EN CURSO:");
		System.out.println("Simulacion con "+numServidores+" servidores y "+numClientes+" clientes permisibles en buffer");
		//START FEL WITH FIRST VALUES
		tiemposFel.add(tiempoE);
		eventosFel.add("llegada externa");
		tiemposServicio.add(tiempoS);
		tiemposEntrada.add(tiempoE);
		
		
		//PROCESSING EVENTS AND FILE READING BUCLE-----------------------------------
		
		
		while (cadena!=null || tiemposFel.size()!=0){
			
			getEventoInminente();
			
			
			//FIRST CASE: EXTERNAL ARRIVAL
			if (eventoInminente.equals("llegada externa")){ 
				
				
			   //OPCTION 1: FULL BUFFER, THE PACKET IS DISCARDED
				if (numClientes==0){
					if (servidoresOcupados<numServidores){
						eventoNuevo="entrada a servidor";
						tiempoNuevo=tiempoInminente;
						eventosFel.add(eventoNuevo);
						tiemposFel.add(tiempoNuevo);
						tiemposServicio.add(tiempoServicioEventoInminente);
						tiemposEntrada.add(tiempoEntradaEventoInminente);
					}
					else{
						packet=1;
						System.out.println(tiempoEntradaEventoInminente+" "+tiempoServicioEventoInminente+" "+packet);
						pw.println(tiempoEntradaEventoInminente+" "+tiempoServicioEventoInminente+" "+packet);
					}
				}
				
				else{
				if (buffer>=numClientes){
					packet=1;
					System.out.println(tiempoEntradaEventoInminente+" "+tiempoServicioEventoInminente+" "+packet);
					pw.println(tiempoEntradaEventoInminente+" "+tiempoServicioEventoInminente+" "+packet);
				}
				//OPTION 2: THE PACKET ENTERS BUFFER
				if (buffer<numClientes && buffer>0){
					eventoNuevo="entrada al buffer";
					tiempoNuevo=tiempoInminente;
					tiemposFel.add(tiempoNuevo);
					eventosFel.add("entrada al buffer");
					tiemposServicio.add(tiempoServicioEventoInminente);
					tiemposEntrada.add(tiempoEntradaEventoInminente);
					
				}
				//OPCTION 3: EMPTY BUFFER, SEARCHING FOR SERVERS
				if (buffer==0 && a==0){
					//OPCTION 3.1: THERE IS AN EMPTY SERVER, THE PACKET ENTERS IT
					if (servidoresOcupados<numServidores){
						eventoNuevo="entrada a servidor";
						tiempoNuevo=tiempoInminente;
						eventosFel.add(eventoNuevo);
						tiemposFel.add(tiempoNuevo);
						tiemposServicio.add(tiempoServicioEventoInminente);
						tiemposEntrada.add(tiempoEntradaEventoInminente);
					}
					//OPTION 3.2: ALL FULL SERVERS, PACKET GOES TO BUFFER.
					if (servidoresOcupados>=numServidores){ 
						eventoNuevo="entrada al buffer";
						tiempoNuevo=tiempoInminente;
						eventosFel.add(eventoNuevo);
						tiemposFel.add(tiempoNuevo);
						tiemposServicio.add(tiempoServicioEventoInminente);
						tiemposEntrada.add(tiempoEntradaEventoInminente);
					}
				}
				}
			}
				
			//SECOND CASE: SERVER ENTRANCE	
			if (eventoInminente.equals("entrada a servidor")){ 
				servidoresOcupados++;
				tiempoNuevo=tiempoInminente+tiempoServicioEventoInminente;
				eventoNuevo="salida del servidor";
				eventosFel.add(eventoNuevo);
				tiemposFel.add(tiempoNuevo);
				tiemposServicio.add(tiempoServicioEventoInminente);
				tiemposEntrada.add(tiempoEntradaEventoInminente);
			}
			//THIRD CASE: SERVER EXIT
			if (eventoInminente.equals("salida del servidor")){
				servidoresOcupados--;
				packet=0;
				System.out.println(tiempoEntradaEventoInminente+" "+tiempoServicioEventoInminente+" "+0);
				pw.println(tiempoEntradaEventoInminente+" "+tiempoServicioEventoInminente+" "+0);
				if (buffer>0){
					recogeDeBuffer();
				}
				
				
			}
			//BUFFER ENTRANCE CASE
			if (eventoInminente.equals("entrada al buffer")){
				buffer++;
				tiempoNuevo=tiempoInminente;
				tiemposBuffer.add(tiempoNuevo);
				tiemposEntradaBuffer.add(tiempoEntradaEventoInminente);
				tiemposServicioBuffer.add(tiempoServicioEventoInminente);				
			}
			
			//READ THE FILE AND ADD OTHER EXTERNAL ARRIVAL TO FEL
			cadena=br.readLine();
			if (cadena!=null){
			palabrasSeparadas = cadena.split(" ");
			tiempoE=Float.parseFloat(palabrasSeparadas[0]);
			tiempoS=Float.parseFloat(palabrasSeparadas[1]);
			tiemposFel.add(tiempoE);
			eventosFel.add("llegada externa");
			tiemposServicio.add(tiempoS);
			tiemposEntrada.add(tiempoE);
			}
			
			
			
			
		}
		
			br.close();
			pw.close();
	}
	
	
	
	
	
	
	//METHOD TO RECOVER THE INMINENT EVENT(WITH THE LOWEST TIME)-----------------------------------
	public static void getEventoInminente(){
		int iMenor = 0;
        for(int i=0;i<tiemposFel.size();i++){
            if(tiemposFel.get(i)<tiemposFel.get(iMenor))
                iMenor = i;
        }
        eventoInminente=eventosFel.get(iMenor);
        tiempoInminente=tiemposFel.get(iMenor);
        tiempoServicioEventoInminente=tiemposServicio.get(iMenor);
        tiempoEntradaEventoInminente=tiemposEntrada.get(iMenor);
        //DELETE THE INMINENT EVENT TO PROCESS IT AND GENERATE A NEW ONE
        eventosFel.remove(iMenor);
        tiemposFel.remove(iMenor);
        tiemposServicio.remove(iMenor);
        tiemposEntrada.remove(iMenor);
     }
	//------------------------------------------------------------------------------------------------
	//METHOD TO RECOVER FROM BUFFER THE FIRST PACKET WAITING, ADD EVENT TO FEL AND REMOVE THE PREVIOUS ONE
	public static void recogeDeBuffer(){
		menor=0;
		for (int b=0;b<tiemposBuffer.size();b++){
			if (tiemposBuffer.get(b)<tiemposBuffer.get(menor)){
				menor=b;
			} 
		}
		
		eventoNuevo="entrada a servidor";
		tiempoNuevo=tiempoInminente;
		eventosFel.add(eventoNuevo);
		tiemposFel.add(tiempoNuevo);
		tiemposServicio.add(tiemposServicioBuffer.get(menor));
		tiemposEntrada.add(tiemposEntradaBuffer.get(menor));
		
		tiemposBuffer.remove(menor);
		tiemposServicioBuffer.remove(menor);
		tiemposEntradaBuffer.remove(menor);
		buffer--;
	}
	//---------------------------------------------------------------------------------------------------
	
	
}
