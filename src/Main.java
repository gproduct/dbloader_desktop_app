import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

class public_vars {
	static boolean connected_mysql = false;												// public variable that shows if we connected to the dbloader
	static String dbloader_ip = null;													// the loaders offical ip
}

public class Main extends JPanel{
	
	public static JFrame frame;															// frame object
	public static JPanel panel_beginner;												// "loading" panel object
	public static JPanel panel_main;													// main panel object
	public static JMenuBar menu;														// main menubar														
	public static GridBagConstraints gbc;												// grid for main page
	
	
	public static ImageIcon main_icon;													// main icon
	public static ImageIcon window_icon;												// 32x32 icon
	public static ImageIcon loader_icon;												// gif for loading
	
	public final static int app_x 		= 700;											// apps x size
	public final static int app_y 		= 400;											// apps y size

	private final static String[] fps_vals = {"240","120","60","45","30","24","1"};
	
	final static String[][] time_res_combo = {
			{"1366","768"},
			{"1920","1080"},
			{"1280","800"},
			{"1440","900"},
			{"1680","1050"},
			{"1920","1200"},
			{"1280","720"},
			{"0","0"}
		};
	
	public static void init_main(){
		/**
		 * 
		 * 	init_main()
		 * 
		 *  Main function after "loading" screen.
		 *   
		 *  Init function for:
		 *  - main panel
		 *  - menu
		 */
		
		
		panel_main = new JPanel();												// creating main panel
		panel_main.setBackground(Color.WHITE);									// setting the background white
		
		
		
		
		frame.add(panel_main);													// adding the panel to the frame
		frame.revalidate();														// recalculating 
		
		
		
	}
	
	public static boolean ping(String host){
		/*
		 *  ping(String host)
		 *  
		 *  Returns true if the host is avaible on the network
		 *  
		 *  @parameter
		 *  	String host - the ip of the host that we will check
		 */
		try(Socket socket = new Socket()){										// creating new socket object
			socket.connect(new InetSocketAddress(host, 80), 100);				// trying to connect to the host
			return true;
		} catch(IOException e){
			return false;
		}
	}
	
	public static void open_website(String url){
		/*
		 *  open_website(String url)
		 *  
		 *  Opens a website on the default browser
		 *  
		 *  @parameter
		 *  	String url - the url that will be opened
		 */
		if(Desktop.isDesktopSupported()){										// if the desktop is supported
			try {
				Desktop.getDesktop().browse(new URI(url));						// open the browser and open the given url
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		/*
		 *  main(String[] args)
		 *  
		 *  The main function that inits the images, sets up the frame and shows the loading gif and the big logo
		 *  
		 *  @parameter
		 *   
		 *  String[] args - basically nothing for now
		 */
		
		
		// image resources so it can be exported properly
		java.net.URL url_main_icon = Main.class.getResource(
                "/Resources/main_icon.png");
		java.net.URL url_window_icon = Main.class.getResource(
                "/Resources/icon.png");
		java.net.URL url_loader_icon = Main.class.getResource(
                "/Resources/loader.gif");
		
		// creating usable image icons with ImageIcon
		main_icon 	= new ImageIcon(url_main_icon);								
		window_icon = new ImageIcon(url_window_icon); 
		loader_icon = new ImageIcon(url_loader_icon);
		
		frame = new JFrame("dbloader");											// creating the main frame, naming it dbloader
		frame.setIconImage(window_icon.getImage());								// setting the icon
		
		panel_beginner = new JPanel();											// creating the beginner panel
		panel_beginner.setLayout(null);											// no layout
		
		panel_beginner.setBackground(Color.WHITE);								// setting the background to white
		
		// creating labels for images
		JLabel begin_image = new JLabel();										
		JLabel loader_img  = new JLabel();
	
		final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		
		// boundries for the main icon on "loading" screen
		int bounds_x1			= (app_x - main_icon.getIconWidth()) / 2;
		int bounds_y1 			= (int) (app_y / 3.2);
		int bounds_x2 			= main_icon.getIconWidth();
		int bounds_y2 			= main_icon.getIconHeight();
		
		begin_image.setBounds(bounds_x1, bounds_y1, bounds_x2, bounds_y2);		// setting the bounds of the begin image
		begin_image.setIcon(main_icon);											// setting the icon for it
		
		loader_img.setBounds((app_x - loader_icon.getIconWidth()) / 2,app_y - 170,30, 30);	// bounds for the loading gif
		loader_img.setIcon(loader_icon);													// setting the icon for it
		
		menu = new JMenuBar();													// creating menubar object
		JMenu menu_file 			= new JMenu("File");						// first object in the menu is File
		JMenuItem menuitem_open_web	= new JMenuItem("Open");					// Files first object is Open
		JMenuItem menuitem_exit 	= new JMenuItem("Exit");					// second is Exit
		
		// action listener for the Open tab
		menuitem_open_web.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				open_website("http://"+public_vars.dbloader_ip);				// opens the dbloader in a website
			}
		});
		
		// Action listener for the Exit tab
		menuitem_exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);													// terminates the program
			}
		});
		
		JMenu menu_about = new JMenu("About");									// second object is About
		JMenuItem menuitem_website = new JMenuItem("Website");					// first object of About is Website
		
		// action listener for website tab
		menuitem_website.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				open_website("https://dbloader.com/");							// opens the official website
			}
		});
		
		JMenu menu_setup = new JMenu("Setup");									// object for setup page
		JMenuItem menusetup_wifi = new JMenuItem("WiFi");
		menusetup_wifi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Communication.request(Communication.req_wifi_ssid);
				Windows.setup_wifi.setVisible(true);
			}
		});
		
		JMenuItem menusetup_authentication = new JMenuItem("Authentication");
		menusetup_authentication.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Windows.setup_auth.setVisible(true);
			}
		});
		
		JMenu menu_settings = new JMenu("Settings");							// object for settings page
		JMenuItem menusettings_settings = new JMenuItem("Settings");
		menusettings_settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Windows.update_settings();
				Windows.settings_settings.setVisible(true);
			}
		});
		
		JMenuItem menusettings_timelapse = new JMenuItem("Timelapse");
		menusettings_timelapse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update_timelapse();
				
				Windows.settings_timelapse.setVisible(true);
			}
		});
		
		JMenuItem menusettings_watermark = new JMenuItem("Watermark");
		menusettings_watermark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				update_watermark();
				
				Windows.settings_watermark.setVisible(true);
			}
		});
		
		JMenuItem menusettings_autoench = new JMenuItem("Auto Ench.");
		menusettings_autoench.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update_auto();
				Windows.settings_autoench.setVisible(true);
			}
		});
		
		
		JMenu menu_effects  = new JMenu("Effects");								// object for effects page
		
		JMenuItem menueffects_video = new JMenuItem("Video Effects");
		menueffects_video.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Communication.request(Communication.req_video);
				Windows.effects_video.setVisible(true);
			}
		});
		
		JMenuItem menueffects_image = new JMenuItem("Image Effects");
		menueffects_image.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				Communication.request(Communication.req_effects);
				Windows.effects_image.setVisible(true);
			}
		});
		

		JMenu menu_dashboard = new JMenu("Dashboard");
		
		JMenuItem menudashboard_item = new JMenuItem("Dashboard");
		menudashboard_item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Windows.update_dashboard();
				Windows.dashboard.setVisible(true);
				scheduler.scheduleAtFixedRate(new DashboardUpdater(), 0, 3000, TimeUnit.MILLISECONDS);
			}
		});
		
		menu_file.add(menuitem_open_web);										// adds web tab to file menu
		menu_file.add(menuitem_exit);											// adds exit tab to file menu
		menu_about.add(menuitem_website);										// adds website to about menu
		menu_effects.add(menueffects_image);
		menu_effects.add(menueffects_video);
		menu_setup.add(menusetup_wifi);
		menu_setup.add(menusetup_authentication);
		menu_settings.add(menusettings_settings);
		menu_settings.add(menusettings_watermark);
		menu_settings.add(menusettings_timelapse);
		menu_settings.add(menusettings_autoench);
		menu_dashboard.add(menudashboard_item);
		
		menu.add(menu_file);													// adds file menu to the menu
		menu.add(menu_setup);
		menu.add(menu_settings);
		menu.add(menu_effects);
		menu.add(menu_dashboard);
		menu.add(menu_about);
		
		panel_beginner.add(begin_image);										// adding the beginner logo/image to the panel
		panel_beginner.add(loader_img);											// adding the loading gif to the panel
			
		frame.add(panel_beginner);												// adding the panel to the frame
		frame.setSize(new Dimension(app_x,app_y));								// setting the dimensions of the frame
		frame.setLocationRelativeTo(null);										// setting the location to null
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					// setting the default close operation to exit when close is hit
		frame.setResizable(false);												// disabling resizing
		frame.setVisible(true);													// showing the frame to the world	
		
		try {
			if(ping("dbloader.local")){																					// if dbloader.local is avaible
				InetAddress ip = InetAddress.getByName("dbloader.local");												// getting the real ip
				public_vars.dbloader_ip =  ip.getHostAddress();															// putting the ip into a public variable
				
				JOptionPane.showMessageDialog(null, "We've detected dbloader on the network, please wait...");			// informing the user that the dbloader was detected
				
				boolean com_status = false;																				// boolean for showing if we got info from dbloader
				
				// multiple attempts to connect to the dbloader
				for(int i=0;i<10;i++){
					
					com_status = Communication.first_time();															// executing the first time function which connect to it on basic level

					if(com_status == true){																				// if it connected																	// "destroying" the beginner panel which is the loading gif and main logo
						panel_beginner.remove(loader_img);
						frame.setJMenuBar(menu);																		// assining the menu to the frame
						init_main();																					// exectuing init_main()
						Windows.setup();																				// init all other windows
						final ScheduledExecutorService scheduler_checkcon = Executors.newScheduledThreadPool(1);
						scheduler_checkcon.scheduleAtFixedRate(new DashboardCheckCon(), 0, 5000, TimeUnit.MILLISECONDS);
						break;																							// breaking out of the loop
					}else{	// no connect
						debug.log("Still not connected, try: " + String.valueOf(i));									
						try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }		// waiting one second and try again to connect
					}
				}
				// if all attempts failed
				if(!public_vars.connected_mysql)
				{
					int reply = JOptionPane.showConfirmDialog(null, "Couldn't connect to dbloader, restart the program?");	// ask user to restart the program
					// if the user decides to restart
					if(reply == JOptionPane.YES_OPTION){
						frame.dispose();																				// terminate the program
						Main.main(null);																				// relaunch it
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "Nothing found...");												// error if ping did not find dbloader
			}
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Nothing found...");													// host error which means no host/dbloader detected
		}
		
		
		
	}
	public static void update_watermark(){
		Communication.request(Communication.req_settings);
		
		switch(Communication.val_settings[4]){
			case "tl":
				Windows.set_img_label_water(Windows.resource_tl_water);
				Windows.water_combobox.setSelectedIndex(0);
				break;
			case "tr":
				Windows.set_img_label_water(Windows.resource_tr_water);
				Windows.water_combobox.setSelectedIndex(1);
				break;
			case "br":
				Windows.set_img_label_water(Windows.resource_br_water);
				Windows.water_combobox.setSelectedIndex(2);
				break;
			case "bl":
				Windows.set_img_label_water(Windows.resource_bl_water);
				Windows.water_combobox.setSelectedIndex(3);
				break;
			case "full":
				Windows.set_img_label_water(Windows.resource_full_water);
				Windows.water_combobox.setSelectedIndex(4);
				break;
		}
	}
	public static void update_timelapse(){
		
		Communication.request(Communication.req_timelapse);
		
		String fps = Communication.val_timelapse[0];
		String x_pos = Communication.val_timelapse[3];
		String y_pos = Communication.val_timelapse[4];
		
		System.out.println("FPS" + fps);//System.out.printf(x_pos);System.out.printf(y_pos);
		
		int item_pos = Arrays.asList(fps_vals).indexOf(fps);

		Windows.time_combo.setSelectedIndex(item_pos);
		
		for(int i=0;i<Main.time_res_combo.length;i++){
			if(Main.time_res_combo[i][0].equals(x_pos)){
				if(Main.time_res_combo[i][1].equals(y_pos)){
					System.out.println("X: " + x_pos + " Y:" + y_pos);
					Windows.res_time_combo.setSelectedIndex(i);
				}
			}
		}
		
		
	}
	public static void update_auto(){
		Communication.request(Communication.req_settings);
		
		String auto_enable  = Communication.val_settings[13];
		String auto_l		= Communication.val_settings[8];
		
		System.out.println(auto_enable + " " + auto_l); 
		
		
		if(auto_enable.equals("1")){
			System.out.println("Auto is enabled");
			Windows.chk_use_auto.setSelected(true);
		}else{
			System.out.println("Auto off");
			Windows.chk_use_auto.setSelected(false);
		}
		
		if(auto_l.equals("1")){
			System.out.println("Auto level enabled");
			Windows.chk_auto_lvl.setSelected(true);
		}else{
			System.out.println("Auto level off");
			Windows.chk_auto_lvl.setSelected(false);
		}
	}
}
