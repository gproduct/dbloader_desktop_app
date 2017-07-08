import java.awt.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.io.BufferedReader;


public class Communication{
	
	private static final String req_code 			= "9K2v6F7hkB";												// request code
	private static final String upd_code			= "21FA921faj";												// update code
	private static final String code				= "54OLjkQtcK";												// connecting code
	private static final String cont        		= "OIjy8mgRi1";												// awaiting code from loader
	private static final String welcome     		= "XtjsM4McmA";												// second awaiting code from loader
	private static final String mysql_code 			= "jLIYEmZORV";												// mysql control code
	private static final String succ				= "XDDy4DYpSz";
	private static final String auth_code			= "82Bda9218c";
	
	private static final String control_code 		= "3gfEpLT6qo";												// control control code
	public static final String control_shutdown		= "gy7lU6kLvU";												// control code to shutdown
	public static final String control_restart   	= "wXg4QjO3NC";												// control code to restart
	public static final String control_reupload 	= "cZpa2eE3Zt";												// control code to reupload
		
	
	private static final String exit_code			= "0000000000";												// exit code
	
	private static final Set<String> control_codes 	= new HashSet<String>(Arrays.asList(new String[] {control_shutdown, control_restart, control_reupload}));							// array for checking if the input is a valid control code
	private static final Set<String> sqlcol_check	= new HashSet<String>(Arrays.asList(new String[] {"effects","main","options","status","timelapse","video_effects","wifi_data"}));	// array for checking if the input is a valid coloumn

	public static String token 						= null;
	public static String[] val_settings				= {"","","","","","","","","","","","","","",""};
	public static String[] val_effects				= {"","","","","","",""};
	public static String[] val_timelapse			= {"","","","",""};
	public static String[] val_video				= {"","","","","","","",""};
	public static String wifi_ssid					= null;
	public static String[] val_dashboard			= {"","","","","","",""};
	public static String val_authurl				= null;
	public static String val_check 					= null;
	
	public static final String req_dash				= "status";
	public static final String req_effects			= "effects";
	public static final String req_video			= "video_effects";
	public static final String req_settings			= "options";
	public static final String req_timelapse		= "timelapse";
	public static final String req_token			= "token";
	public static final String req_wifi_ssid		= "wifi_ssid";
	public static final String req_all				= "status|effects|video_effects|options";					// not including dashbaord
	public static final String req_authurl			= "authurl";
	public static final String req_authurl_stage2	= "authurl2";
	
	public static final String req_dashboard		= "dashboard";
	public static final String req_check			= "check";
	
	public static BufferedReader br;																			// buffer reader used as input from TCP 
	public static PrintWriter out;																				// buffer that is used as output for TCP
	public static Socket socket;																				// main socket object
	
	private static void init_socket() {
		/*
		 *  init_socket()
		 *  
		 *  Initializes the socket and connects to the dbloader through TCP if avaible
		 */						
	
		request(req_all);
		//request(req_token);
	}
	
	private static void update_array_string(String[] array, String[] values){

		
		for(int i = 0; i < values.length; i++){
			array[i] = values[i];
		}
		array = values;
		
		System.out.print("Updated to " + Arrays.toString(array) + "\n");
		
	}
	private static void update_token(String value0, String value1, String value2, String value3){
		
		String value;
		
		if(value0 == "" || value1 == "" || value2 == "" || value3 == ""){
			System.out.println("Too short");
			token = "Not set!";
		}else{
			
			value = value0+value1+value2+value3;
			
			System.out.println("Token value set to: " + value + "\n");
			token = value;
		}
	}
	
	private static void update_ui(String inputs, String table){

		String[] exploded = null;
		
		if (table == req_all){
			exploded = inputs.split("\\{}");
			System.out.println(Arrays.toString(exploded));
			update_array_string(val_settings, 	exploded[0].split("\\|"));
			update_array_string(val_effects,  	exploded[1].split("\\|"));
			update_array_string(val_timelapse,  exploded[2].split("\\|"));
			update_array_string(val_video,  	exploded[3].split("\\|"));
		}else{
			switch(table){
				case req_effects:
					update_array_string(val_effects, inputs.split("\\|"));
					break;
				case req_video:
					update_array_string(val_video, inputs.split("\\|"));
					break;
				case req_settings:
					update_array_string(val_settings, inputs.split("\\|"));
					break;
				case req_timelapse:
					update_array_string(val_timelapse, inputs.split("\\|"));
					break;
				case req_token:
					exploded = inputs.split("\\{}");
					update_token(exploded[0], exploded[1], exploded[2], exploded[3]);
					break;
				case req_wifi_ssid:
					wifi_ssid = inputs;
					break;
				case req_dashboard:
					exploded = inputs.split("\\|");
					update_array_string(val_dashboard, inputs.split("\\|"));
					break;
				case req_authurl_stage2:
					val_authurl = inputs;
					break;
				case req_check:
					System.out.println(inputs);
					val_check = inputs;
					break;
					
			}
		}

	}
	
	public static void request(String table){
		socketit();
			
		out.println(req_code);
			
		try {
			if((br.readLine()).equals(cont)){												// if the reply is the same as continue code
				out.println(table);
				if(table != req_authurl)
					update_ui(br.readLine(), table);
				public_vars.connected_mysql = true;
			}else{
				debug.log("Not Connected");	
				public_vars.connected_mysql = false;										// we are not connected!
			}
		} catch (IOException e) {
			e.printStackTrace();
			public_vars.connected_mysql = false;
		} catch (Exception e) {
			e.printStackTrace();
			public_vars.connected_mysql = false;
		}
	}

	public static void update(String table, String coloumn, String value){
		socketit();
		
		out.println(upd_code);
		
		try {
			if((br.readLine()).equals(cont)){												// if the reply is the same as continue code
				out.println(table);
				if((br.readLine()).equals(cont)){		
					out.println(coloumn);
					if((br.readLine()).equals(cont)){		
						out.println(value);
					}
				}
				
			}else{
				debug.log("Not Connected");													// we are not connected!
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void socketit(){
		try {
			socket = new Socket("dbloader.local", 6789);									// sets up a new socket connection to dbloader through 6789 port
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));		// setup of the bufferreader using the inputstreamreader
			out = new PrintWriter(socket.getOutputStream(),true);							// setup of the printwriter using the getoutputstream
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}									
		
		
	}

	public static void close_TCP(){
		/*
		 *  close_TCP()
		 *  
		 *  Closes the TCP connection
		 */
		try{ 
			debug.log("Closing TCP");
			br.close();																		// closes the bufferreader
			out.close();																	// closes the printwriter
		    socket.close();																	// closes the socket
		    debug.log("TCP Closed");
		} catch (IOException e) {															// error handling
			e.printStackTrace();
		}
	}

	
	public static boolean first_time(){
		/*
		 *  first_time()
		 *  
		 *  Inits the socket and returns the status of connected
		 */
		System.out.println("First Time setup");
		init_socket();
		return public_vars.connected_mysql;
	}
	
	
	
}
