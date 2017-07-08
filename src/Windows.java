import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class Windows {
	
	public static JFrame effects_image;			public static JPanel ei;
	public static JFrame effects_video;			public static JPanel ev;
	public static JFrame setup_wifi;			public static JPanel sw;
	public static JFrame setup_auth;			public static JPanel sa;
	public static JFrame settings_settings;		public static JPanel ss;
	public static JFrame settings_watermark;	public static JPanel sew;
	public static JFrame settings_timelapse;	public static JPanel st;
	public static JFrame settings_autoench;		public static JPanel sea;
	public static JFrame dashboard;				public static JPanel dash;
	
	public static JCheckBox chk_set_img_effects 	= new JCheckBox("Image Effects");
	public static JCheckBox chk_set_bw				= new JCheckBox("Black&White copies");
	public static JCheckBox chk_set_wm_copies		= new JCheckBox("Watermark copies");
	public static JCheckBox chk_set_wm_folder		= new JCheckBox("Watermark Folder");
	public static JCheckBox chk_set_timelapse		= new JCheckBox("Timelapse");
	public static JCheckBox chk_set_time_inc		= new JCheckBox("Timelapse - Include");
	public static JCheckBox chk_set_time_only		= new JCheckBox("Timelapse - Only");
	public static JCheckBox chk_set_vid_ef			= new JCheckBox("Video Effects");
	public static JCheckBox chk_set_vid_sep			= new JCheckBox("Video in separate folder");
	public static JCheckBox chk_set_vid_stbl		= new JCheckBox("Video stabilization");
	public static JCheckBox chk_set_en_back			= new JCheckBox("Enable Backup");
	public static JCheckBox chk_set_day				= new JCheckBox("1 Day Rule");
	
	private static final int window_x	 			= 500;
	private static final int window_y	 			= 300;
	
	public static GridBagConstraints gbc;		
    public static GridBagConstraints c;			// grid
	
	public static JFrame[] jframe;
	public static JPanel[] jpanel;
	
	public static JLabel img_label;
	
	public static JSlider[] sliders_video;
	public static String[] sliders_label_value;
	public static JComboBox<Object> water_combobox;
	public static JComboBox<Object> time_combo;
	public static JComboBox<Object> res_time_combo;
	
	public static String resource_water 		= "/Resources/main_icon.png";
	public static String resource_tl_water 		= "/Resources/tl_wm.jpg";
	public static String resource_tr_water 		= "/Resources/tr_wm.jpg";
	public static String resource_bl_water 		= "/Resources/bl_wm.jpg";
	public static String resource_br_water 		= "/Resources/br_wm.jpg";
	public static String resource_full_water 	= "/Resources/full_wm.jpg";
	public static JLabel[] slider_labels;
	public static GridBagConstraints layout_gridbag;
	public static JCheckBox chk_use_auto;
	public static JCheckBox chk_auto_lvl;
	
	public static JCheckBox[] chk_set =  {chk_set_img_effects, chk_set_en_back, chk_set_day, chk_set_wm_copies, chk_set_wm_folder, chk_set_vid_sep,
										  chk_set_bw, chk_set_vid_stbl, chk_set_vid_ef, chk_set_timelapse, chk_set_time_inc, chk_set_time_only};
	
	public static String[] chk_set_values = {"use_effects","use_backup","use_day","use_wm","wm_folder","vid_folder",
											"use_bw","use_deshake","enable_video_effects","use_timelapse","include_time","only_time"};
	
	public static final String[] time_fps_val = {"240FPS","120FPS","60FPS","45FPS","30FPS","24FPS","1FPS"};
	public static final String[] time_res_val	= {"1366x768","1920x1080","1280x800","1440x900","1680x1050","1920x1200","1280x720","Same as pictures","Website custom"};
	
	public static final String[] water_combobox_values 		= {"Top-Left","Top-Right","Bottom-Right","Bottom-Left","Full-Page for photo/Center for video"};
	public static final String[] water_combobox_val_short 	= {"tl","tr","br","bl","full"};
	
	public static JLabel 		lasttime_label;
	public static JProgressBar 	cpuusage_bar;
	public static JProgressBar 	cputemp_bar;
	public static JProgressBar 	memory_bar;
	public static JLabel 		usbstatus_label;
	public static JLabel 		usbcapac_label;
	public static JLabel 		usbused_label;
	
	public static boolean status_auth = false;
	public static JTextField field_auth = null;
	public static JButton submit_auth  = null;
	
	@SuppressWarnings("deprecation")
	public static void setup(){
		effects_image 		= new JFrame("Image Effects | dbloader");
		effects_video 		= new JFrame("Video Effects | dbloader");
		
		setup_wifi			= new JFrame("Wifi Setup | dbloader");
		setup_auth			= new JFrame("Authentication Setup | dbloader");
		
		settings_settings 	= new JFrame("Settings | dbloader");
		settings_watermark	= new JFrame("Watermark Settings | dbloader");
		settings_timelapse	= new JFrame("Timelapse Settings | dbloader");
		settings_autoench	= new JFrame("Auto enchancement Settings | dbloader");
		
		dashboard 			= new JFrame("Dashboard | dbloader");
	
		JFrame[] jframe = {effects_image, effects_video, setup_wifi, setup_auth, settings_settings, settings_watermark, settings_timelapse, settings_autoench, dashboard};
		JPanel[] jpanel = {ei, ev, sw, sa, ss, sew, st, sea, dash};
		
		gbc = new GridBagConstraints();
		gbc.gridwidth   = GridBagConstraints.REMAINDER;							// gridwidth is 
		gbc.fill 		= GridBagConstraints.HORIZONTAL;						// grids fill goes horizontal
		
		GridBagConstraints layout_gridbag = new GridBagConstraints();
		
		for(int i = 0; i< jframe.length; i++){
			jframe[i].setIconImage(Main.window_icon.getImage());
			jpanel[i] = new JPanel();
			jpanel[i].setLayout(new GridBagLayout());
			jpanel[i].setBackground(Color.WHITE);
			jframe[i].setSize(new Dimension(window_x, window_y));
			jframe[i].setLocationRelativeTo(null);
			jframe[i].setResizable(false);
			jframe[i].add(jpanel[i]);	
		}
		
		jframe[0].setSize(new Dimension(window_x - 180, window_y + 100));   	// new size for video_effects
		jframe[1].setSize(new Dimension(window_x - 180, window_y + 100));   	// new size for video_effects	
		jframe[5].setSize(new Dimension(window_x, window_y + 50));   			// new size for video_effects	
		jframe[4].setSize(new Dimension(window_x + 150, window_y - 100));
		
		gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        c = new GridBagConstraints();
        
        c.weightx = 0.5;
        c.weighty = .15;
        c.insets = new Insets(2, 0, 2, 0);
        c.gridwidth = GridBagConstraints.REMAINDER;
		
        /*
         * 	   Image Effects
         */
 
        JButton image_effects_submit 				= new JButton("Submit");					// creating a submit button
		JButton reset_image 						= new JButton("Reset to Default");			// creating a reset button
        // array for the sliders label value
     	final String[] slider_image_label_value 	= 	{
     														"Exposure: ",
     														"Highlight: ",
     														"Shadow: ",
     														"Brightness: ",
     														"Contrast: ",
     														"Sharpness: ",
     														"Saturation: "
     													};
     			
     			
		// creating jlabels
		JLabel label_ie_exposure					= new JLabel(slider_image_label_value[0]);
		JLabel label_ie_shadow 						= new JLabel(slider_image_label_value[1]);
		JLabel label_ie_contrast 				 	= new JLabel(slider_image_label_value[2]);
		JLabel label_ie_saturation					= new JLabel(slider_image_label_value[3]);
		JLabel label_ie_highlight					= new JLabel(slider_image_label_value[4]);
		JLabel label_ie_brightness					= new JLabel(slider_image_label_value[5]);
		JLabel label_ie_sharpness 					= new JLabel(slider_image_label_value[6]);
		
		final JSlider slider_ie_exposure 			= new JSlider(JSlider.HORIZONTAL,-300,300,1);
		final JSlider slider_ie_shadow 				= new JSlider(JSlider.HORIZONTAL,0,20,1);
		final JSlider slider_ie_contrast 			= new JSlider(JSlider.HORIZONTAL,0,30,1);
		final JSlider slider_ie_saturation			= new JSlider(JSlider.HORIZONTAL,0,100,1);
		final JSlider slider_ie_highlight			= new JSlider(JSlider.HORIZONTAL,0,100,1);
		final JSlider slider_ie_brightness			= new JSlider(JSlider.HORIZONTAL,0,100,1);
		final JSlider slider_ie_sharpness			= new JSlider(JSlider.HORIZONTAL,0,100,1);
		
		
		//exp, high, shadow, bright, cont, black, sat
		
		// sliders of the image in array for easier usage
		final JSlider[] sliders_image				= 	{
															slider_ie_exposure,
															slider_ie_highlight,
															slider_ie_shadow,
															slider_ie_brightness,
															slider_ie_contrast,
															slider_ie_sharpness,
															slider_ie_saturation
														};
		
		//default values for the labels
		final String[] default_image_val 			= 	{
															"0.0",
															"1.0",
															"2.0",
															"1.0",
															"1.0",
															"1.0",
															"1.0"
														};
		
		// all the sliders in an array 
     	final JLabel[] slider_effects_labels		= 	{
     														label_ie_exposure,
     														label_ie_highlight,
     														label_ie_shadow,
     														label_ie_brightness,
     														label_ie_contrast,
     														label_ie_sharpness,
     														label_ie_saturation
     													};
     	final String[] mysql_image_val				= 	{
     														"exp",
     														"high",
     														"shadow",
     														"bright",
     														"cont",
     														"black",
     														"sat"
     													};
     	
     	for(int i = 0; i < sliders_image.length; i++){
			final int copy_int = i;																				// copying the i
			final JSlider current_slider = sliders_image[i];													// copying the object
			if(current_slider != null){
				current_slider.addChangeListener(new ChangeListener(){											// creating the change listener
					@Override
					public void stateChanged(ChangeEvent arg0) {
						String value;
						value = Float.toString((float) ((current_slider.getValue() ) / 10.0)); 					// converting it to float
						Communication.val_effects[copy_int] 	= value;										// setting the value to the variable
						String label_value 						= slider_image_label_value[copy_int] + value;	// changing the label
						slider_effects_labels[copy_int].setText(label_value);									// setting the text
					}
					
				});
			}else{debug.log("null found");}
		}

     	// creating the gridbag and setting it to vertiacl
     	layout_gridbag.fill = GridBagConstraints.VERTICAL;
     		
     	// for loop for the slider lables to set the position and add it to the panel
     	for(int i=0;i<sliders_image.length;i++){
     		if(sliders_image[i] != null){
	     		layout_gridbag.anchor = GridBagConstraints.FIRST_LINE_START;
	     		layout_gridbag.gridx = 2;
	     		jpanel[0].add(slider_effects_labels[i], layout_gridbag);
	     		layout_gridbag.anchor = GridBagConstraints.LINE_START;
	     		jpanel[0].add(sliders_image[i], layout_gridbag);
     		}else{debug.log("null found");}
     	}
		
     	// debug
     	System.out.println("GETTING DATA" + Arrays.toString(Communication.val_effects));
     		
     	// for loop of the video sliders and video labels and setting their value
     	for(int i=0;i<sliders_image.length;i++){
     		if(sliders_image[i] != null){
	     		Float valuez = Float.parseFloat(Communication.val_effects[i]);					// getting the value
	     		switch(mysql_image_val[i]){
					case "high":
						sliders_image[i].setValue(Math.round(valuez * 500));					// setting the sliders value
						break;
					case "shadow":
						sliders_image[i].setValue(Math.round(valuez * 250));					// setting the sliders value
						break;
					default:
						sliders_image[i].setValue(Math.round(valuez * 10));						// setting the sliders value
						break;
				}
	     		
	     		slider_effects_labels[i].setText(slider_image_label_value[i] + valuez);			// setting the label value
     		}
     	}
		

		// changing the gridbag parameters
		
		layout_gridbag.ipady = 5;
		layout_gridbag.ipadx = 30;
		layout_gridbag.anchor = GridBagConstraints.CENTER;
		
		// adding the submit button
		jpanel[0].add(image_effects_submit, layout_gridbag);
		
		// if the submit button si clicked
		image_effects_submit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<sliders_image.length;i++){
					if(sliders_image[i] != null){
						System.out.println(Arrays.toString(Communication.val_effects));			// debug
						
						float value = (float)sliders_image[i].getValue();						// getting the value of the sliders
						
						String updating_value;
						
						switch(mysql_image_val[i]){
							case "high":
								updating_value = Float.toString((value / 10) * 50);
								break;
							case "shadow":
								updating_value = Float.toString((value / 10) * 25);
								break;
							default:
								updating_value = Float.toString(value / 10);
								break;
						}
						
						System.out.println(mysql_image_val[i] + " " + updating_value);
						Communication.update("effects",mysql_image_val[i],updating_value);	// updating
						
					}
				}
				
				JOptionPane.showMessageDialog(null, "Updated!");					// alerting the user
			}
		});
		
		
		// resetting the parameters of the gridbag
		layout_gridbag.ipady = 0;
		layout_gridbag.ipadx = 0;
		layout_gridbag.anchor = GridBagConstraints.CENTER;
		layout_gridbag.insets = new Insets(5,5,5,5);
		
		jpanel[0].add(reset_image, layout_gridbag);									// adding the reset button to the panel
		
		// if we click the reset button
		reset_image.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// for loop through all of the slidesr and labels
				for(int i=0;i<sliders_image.length;i++){
					if(sliders_image[i] != null){
						sliders_image[i].setValue(Math.round(Float.parseFloat(default_image_val[i]) * 10));	// setting it to default
						slider_effects_labels[i].setText(slider_image_label_value[i] + default_image_val[i]);			// setting it to default
					}
				}

				JOptionPane.showMessageDialog(null, "All values have been set to default value!");		// alerting the user
				
			}
		});
		
        /*
         * 	   Wifi setup
         */
        
		JLabel title = new JLabel("WiFi Setup");								// create title label
		title.setFont(title.getFont().deriveFont(16.0f));						// setting the font
		
		final JTextField field_ssid = new JTextField("Network name",20);		// creating a field for ssid
		final JPasswordField field_password = new JPasswordField("xxxx",20);	// creating a field for password
		
		Communication.request(Communication.req_wifi_ssid);						// requesting the wifi ssid
		
		final JLabel last_network = new JLabel("Current network: " + Communication.wifi_ssid);	// display the requested
		
		JButton submit_wifi = new JButton("Submit");							// creat the submit button
		
		// adding the elements to the jpanel
		jpanel[2].add(title, c);			
		jpanel[2].add(field_ssid, gbc);
		jpanel[2].add(field_password, gbc);
		jpanel[2].add(last_network, gbc);
		jpanel[2].add(submit_wifi, c);
		
		// when submit is clicked
		submit_wifi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(field_ssid.getText());								//	debug for ssid
				System.out.println(field_password.getText());							// debug for pass

				last_network.setText("Loading...");										// visually "loading"
				
				Communication.update("wifi_data","ssid_home",field_ssid.getText());		// updating the ssid
				Communication.update("wifi_data","pass_home",field_password.getText());	// updating the pass
					

				JOptionPane.showMessageDialog(null, "Updated!");						// alerting the user for update
				
				
				Communication.request(Communication.req_wifi_ssid);						// requesting the ssid
				last_network.setText("Current network: " + Communication.wifi_ssid);	// updating the current network label
				
				
				
			}
		});
		
		/*****************************************************************/
		
		/*
		 *  	Authentication Setup
		 */
		
	
		
		field_auth = new JTextField("", 20);					// creating a textfield for auth
		submit_auth= new JButton("Connect dbloader to your dropbox");							// creating the button
		
		/*if( Communication.token == null ){										// if the auth doesnt excists
			current_auth.setText("Nothing...");
		}else if( Communication.token.length() > 15){							// if too long
			String upToNCharacters = Communication.token.substring(0, Math.min(Communication.token.length(), 14)); // show just a part of it
			current_auth.setText("Current code is: " + upToNCharacters);		// display it
		}else{
			current_auth.setText("Current code is: " + Communication.token);	// nothing above, just show
		}*/
		
		// adding components to panel
		
		jpanel[3].add(field_auth, c);
		field_auth.show(false);
		jpanel[3].add(submit_auth,c);
		status_auth = false;
		submit_auth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(status_auth == false){
					Communication.request(Communication.req_authurl);
					Communication.close_TCP();
					Communication.request(Communication.req_authurl_stage2);
					
					if(Communication.val_authurl != "null" || Communication.val_authurl != null){
						System.out.println(Communication.val_authurl);
						Main.open_website(Communication.val_authurl);
						field_auth.show(true);
						field_auth.setText("Put your received code here");
						submit_auth.setText("Submit your given token");
						status_auth = true;
					}else{
						status_auth = false;
					}
				}else{
					String field_auth_var = field_auth.getText();
					if(field_auth != null){
						Communication.token = field_auth_var;
						Communication.update("main","auth_main",Communication.token);	// update the database
						JOptionPane.showMessageDialog(null, "Updated!");				// alert user that its updated
					} else
						JOptionPane.showMessageDialog(null, "Please paste the given code into the input.");				// alert user that its updated
				}
				
				/*String auth_got = field_auth.getText();							// get the auth
				Communication.token = auth_got;									// update the variable
				Communication.update("main","auth_main",Communication.token);	// update the database
				
				try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e1) { e1.printStackTrace(); } // waiting 1 sec
				
				Communication.request(Communication.req_token);					// requesting the token
				System.out.println(Communication.token);						// debug token
				
				JOptionPane.showMessageDialog(null, "Updated!");				// alert user that its updated
				
				// same thing as above
				if( Communication.token.length() > 15){
					String upToNCharacters = Communication.token.substring(0, Math.min(Communication.token.length(), 14));
					current_auth.setText("Current code is: " + upToNCharacters);
				}else{
					current_auth.setText("Current code is: " + Communication.token);
				}*/
			}
		});
		
		/*****************************************************************/
		
		/*
		 *  	Video Setup
		 */

		JButton video_effects_submit 		= new JButton("Submit");					// creating a submit button
		
		// array for the sliders label value
		final String[] sliders_label_value 	= 	{
													"Contrast: ",
													"Brightness: ",
													"Saturation: ",
													"Gamma: ",
													"Gamma Red: ",
													"Gamma_Green: ",
													"Gamma_Blue: ",
													"Gamma_Weight: "
												};
		
		// creating jlabels
		JLabel label_contrast 				= new JLabel(sliders_label_value[0]);
		JLabel label_brightness 			= new JLabel(sliders_label_value[1]);
		JLabel label_saturation 			= new JLabel(sliders_label_value[2]);
		JLabel label_gamma					= new JLabel(sliders_label_value[3]);
		JLabel label_gamma_r				= new JLabel(sliders_label_value[4]);
		JLabel label_gamma_g				= new JLabel(sliders_label_value[5]);
		JLabel label_gamma_b 				= new JLabel(sliders_label_value[6]);
		JLabel label_gamma_weight 			= new JLabel(sliders_label_value[7]);
		
		// all the sliders in an array 
		final JLabel[] slider_labels		= 	{
													label_contrast, 
													label_brightness, 
													label_saturation, 
													label_gamma, 
													label_gamma_r, 
													label_gamma_g, 
													label_gamma_b, 
													label_gamma_weight
												};
		
		//default values for the labels
		final String[] default_video_val 	= 	{
													"1.0",
													"0.0",
													"1.0",
													"1.0",
													"1.0",
													"1.0",
													"1.0",
													"1.0"
												};
		
		final JSlider slider_contrast 		= new JSlider(JSlider.HORIZONTAL,-20,20,1);
		final JSlider slider_brightness 	= new JSlider(JSlider.HORIZONTAL,-10,10,1);
		final JSlider slider_saturation 	= new JSlider(JSlider.HORIZONTAL,0,30,1);
		final JSlider slider_gamma			= new JSlider(JSlider.HORIZONTAL,1,100,1);
		final JSlider slider_gamma_r		= new JSlider(JSlider.HORIZONTAL,1,100,1);
		final JSlider slider_gamma_g		= new JSlider(JSlider.HORIZONTAL,1,100,1);
		final JSlider slider_gamma_b		= new JSlider(JSlider.HORIZONTAL,1,100,1);
		final JSlider slider_gamma_weight 	= new JSlider(JSlider.HORIZONTAL,1,100,1);
		
		// sliders of the video in array for easier usage
		final JSlider[] sliders_video 		= 	{
													slider_contrast,  
													slider_brightness, 
													slider_saturation, 
													slider_gamma, 
													slider_gamma_r, 
													slider_gamma_g, 
													slider_gamma_b, 
													slider_gamma_weight
												};
				
		
		
		for(int i = 0; i < sliders_video.length; i++){
			final int copy_int = i;																	// copying the i
			final JSlider current_slider = sliders_video[i];										// copying the object
			
			current_slider.addChangeListener(new ChangeListener(){									// creating the change listener
				@Override
				public void stateChanged(ChangeEvent arg0) {
					String value = Float.toString((float) ((current_slider.getValue() ) / 10.0)); 	// converting it to float
					Communication.val_video[copy_int] 	= value;									// setting the value to the variable
					String label_value 					= sliders_label_value[copy_int] + value;	// changing the label
					slider_labels[copy_int].setText(label_value);									// setting the text
				}
				
			});
			
		}
		
		// creating the gridbag and setting it to vertiacl
		layout_gridbag.ipady = 0;
		layout_gridbag.ipadx = 100;
		layout_gridbag.anchor = GridBagConstraints.CENTER;
		layout_gridbag.insets = new Insets(1,1,1,1);
		
		// for loop for the slider lables to set the position and add it to the panel
		for(int i=0;i<slider_labels.length;i++){
			layout_gridbag.anchor = GridBagConstraints.FIRST_LINE_START;
			jpanel[1].add(slider_labels[i], layout_gridbag);
			layout_gridbag.anchor = GridBagConstraints.LINE_START;
			jpanel[1].add(sliders_video[i], layout_gridbag);
		}
		
		// debug
		System.out.println("GETTING DATA" + Arrays.toString(Communication.val_video));
		
		// for loop of the video sliders and video labels and setting their value
		for(int i=0;i<sliders_video.length;i++){
			
			Float valuez = Float.parseFloat(Communication.val_video[i]);			// getting the value
			
			sliders_video[i].setValue(Math.round(valuez * 10));						// setting the sliders value
			slider_labels[i].setText(sliders_label_value[i] + valuez);				// setting the label value
		}
		
		
		// changing the gridbag parameters
		
		layout_gridbag.anchor = GridBagConstraints.CENTER;
		
		// adding the submit button
		jpanel[1].add(video_effects_submit, layout_gridbag);
		
		// if the submit button si clicked
		video_effects_submit.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String video_update = "";											// update variable
				
				for(int i=0;i<sliders_video.length;i++){
					
					System.out.println(Arrays.toString(Communication.val_video));	// debug
					
					float value = (float)sliders_video[i].getValue();				// getting the value of the sliders
					
					// parsing the values
					if(i == sliders_video.length - 1){ video_update = video_update + (Float.toString(value / 10)); }else{ video_update = video_update + (Float.toString(value / 10)) + "l"; }
				}

				System.out.println(video_update);									// debug
				Communication.update("video_effects", "all", video_update);			// updating 
				video_update = "";													// reseting it
				
				JOptionPane.showMessageDialog(null, "Updated!");					// alerting the user
			}
		});
		
		JButton reset_video = new JButton("Reset to Default");						// creating a reset button
		
		// resetting the parameters of the gridbag
		layout_gridbag.ipady = 0;
		layout_gridbag.ipadx = 0;
		layout_gridbag.anchor = GridBagConstraints.CENTER;
		layout_gridbag.insets = new Insets(5,5,5,5);
		
		jpanel[1].add(reset_video, layout_gridbag);									// adding the reset button to the panel
		
		// if we click the reset button
		reset_video.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// for loop through all of the slidesr and labels
				for(int i=0;i<sliders_video.length;i++){
					sliders_video[i].setValue(Math.round(Float.parseFloat(default_video_val[i]) * 10));	// setting it to default
					slider_labels[i].setText(sliders_label_value[i] + default_video_val[i]);			// setting it to default
				}

				JOptionPane.showMessageDialog(null, "All values have been set to default value!");		// alerting the user
				
			}
		});
		
		/*****************************************************************/
		
		/*
		 *  	Watermark
		 */

		
		img_label 		= new JLabel();															// creating label for image
		water_combobox 	= new JComboBox<Object>(water_combobox_values);							// creating a combobox for watermark values
		
		// setting the new parameters or the gridbag
		layout_gridbag.ipady = 0;
		layout_gridbag.ipadx = 0;
		layout_gridbag.anchor = GridBagConstraints.CENTER;
		layout_gridbag.insets = new Insets(0,0,20,0);
		
		jpanel[5].add(img_label,layout_gridbag);												// adding the image label
		layout_gridbag.insets = new Insets(0,0,20,0);											//  setting the insets
		jpanel[5].add(water_combobox, layout_gridbag);											// adding the combobox
		
		Main.update_watermark();																// updating the watermark
		
		// if we change something on the combobox
		water_combobox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String value_combo = (String) water_combobox.getSelectedItem();					// get the selected value 
				int val_pos 	= Arrays.asList(water_combobox_values).indexOf(value_combo);	// get the pos
				int val_pos_com = Arrays.asList(water_combobox_val_short).indexOf(Communication.val_settings[4]);	// get the val
				if(val_pos == val_pos_com){ //checking if the same with the pos in the arrays
					System.out.println("Same");													// its the same as selected
				}else{
					// not the same
					Communication.update("options", "wm_pos", water_combobox_val_short[val_pos]); 	// update the database
					Main.update_watermark();													  	// update it 
						
					JOptionPane.showMessageDialog(null, "Submited!");								// alert the user							
				}
			}
		});
		
		/*****************************************************************/
		
		/*
		 *  	Timelapse
		 */

		
		time_combo = new JComboBox<Object>(time_fps_val);												// creating fps combobox
		res_time_combo = new JComboBox<Object>(time_res_val);											// creating res combobox
		JButton submit_time = new JButton("Submit");													// creating submit button
		
		Dimension combo_dimension = new Dimension(250,20);												// creating combo dimension
		
		layout_gridbag.insets = new Insets(5,5,5,5);													// setting the insets
		time_combo.setPreferredSize(combo_dimension);													// setting the size
		res_time_combo.setPreferredSize(combo_dimension);												// setting the size
		//adding everything to the panel
		jpanel[6].add(time_combo,layout_gridbag);
		jpanel[6].add(res_time_combo, layout_gridbag);
		jpanel[6].add(submit_time, layout_gridbag);
		
		//if we select custom
		res_time_combo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(res_time_combo.getSelectedIndex() == 8){
					Main.open_website("http://"+public_vars.dbloader_ip+"/settings.php");				// opens the dbloader in a website
				}
			}
		});
		
		// when submit clickd
		submit_time.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int val_pos 	= Arrays.asList(time_res_val).indexOf(res_time_combo.getSelectedItem());	// get the value 
				
				//debug
				//System.out.println("X:"+time_res_combo[val_pos][0]);
				//System.out.println("Y:"+time_res_combo[val_pos][1]);
				//System.out.println(val_fps);
				String val_fps = (String) time_combo.getSelectedItem();										// get the value 
				val_fps = val_fps.substring(0, val_fps.length() - 3);										// setting the val_fps
				Communication.update("timelapse", "fps", val_fps);											// updating fps 
				Communication.update("timelapse", "x_pos", Main.time_res_combo[val_pos][0]);				// updating x_pos
				Communication.update("timelapse", "y _pos", Main.time_res_combo[val_pos][1]);				// updating y_pos
				
				JOptionPane.showMessageDialog(null, "Submited!");											// alert the user
			}
		});
		/*****************************************************************/
		
		/*
		 *  	Auto ench
		 */
		
		chk_use_auto = new JCheckBox("Use Auto Enhancement");				// creating ae checkbox
		chk_auto_lvl = new JCheckBox("Use Auto-Level");						// creating ae checkbox
		
		// setting the mnemonic
		chk_use_auto.setMnemonic(KeyEvent.VK_C);							
		chk_auto_lvl.setMnemonic(KeyEvent.VK_C);
		
		Main.update_auto();													// updating auto
		
		// adding items to panel
		jpanel[7].add(chk_use_auto, layout_gridbag);
		jpanel[7].add(chk_auto_lvl, layout_gridbag);
		
		// clicking on the use auto check
		chk_use_auto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean state = chk_use_auto.isSelected();					// get the state
				String state_str = "0";										// set variable to 0
				if(state){ state_str = "1"; }								// if state is true than set it to string 1
				Communication.update("options", "auto_use", state_str);		// update it
				JOptionPane.showMessageDialog(null, "Submited!");			// alert he user
			}
		});
		
		// clicking the auto level check
		chk_auto_lvl.addActionListener(new ActionListener() {				
			
			// same as above
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean state = chk_auto_lvl.isSelected();					
				String state_str = "0";
				if(state){ state_str = "1"; }
				Communication.update("options", "auto_l", state_str);
				JOptionPane.showMessageDialog(null, "Submited!");
			}
		});
		
		/*****************************************************************/
		
		/*
		 *  	Settings
		 */
	
		
		
		jpanel[4].setLayout(new GridLayout(5, 1));
	    jpanel[4].setAlignmentY(JComponent.LEFT_ALIGNMENT);

	    
	    update_settings();
	    
		for(int i=0; i<chk_set.length;i++){
			final int i_copy = i; 
			chk_set[i].setMnemonic(KeyEvent.VK_C);
			jpanel[4].add(chk_set[i]);
			final JCheckBox check_current = chk_set[i];							// assigning it to a new object (becuase cant get it into the actionlistener)
			check_current.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.out.println(chk_set[i_copy]);
					boolean state = chk_set[i_copy].isSelected();
					String state_str = "0";										// set variable to 0
					if(state){ state_str = "1"; }								// if state is true than set it to string 1
					
					//updating it (its more simple than writing 4 if statements
					if(check_current == chk_set_time_inc || check_current == chk_set_time_only){
						if((check_current == chk_set_time_inc && state) || (check_current == chk_set_time_only && !state)){
							update_inconly("1", "0", false, true);
						}else{
							update_inconly("0" , "1", true, false);
						}
					}else{
						System.out.println("Update to " + state_str);
						Communication.update("options", chk_set_values[i_copy] , state_str);
					}
					JOptionPane.showMessageDialog(null, "Changed!");
					update_settings();
				}
			});
			
		}
		
		
		lasttime_label 		= new JLabel("Last update time: ");
		cpuusage_bar 		= new JProgressBar(0,100);
		cputemp_bar 		= new JProgressBar(0,150);
		memory_bar 			= new JProgressBar(0,100);
		usbstatus_label 	= new JLabel("Usb status: ");
		usbcapac_label 		= new JLabel("Usb capacity: ");
		usbused_label 		= new JLabel("Usb used: ");
		
		cpuusage_bar.setStringPainted(true);
		cputemp_bar.setStringPainted(true);
		memory_bar.setStringPainted(true);
		
		
		final JComponent[] dashboard_elements =  	{
														lasttime_label, 
														cpuusage_bar, 
														cputemp_bar, 
														memory_bar, 
														usbstatus_label, 
														usbcapac_label, 
														usbused_label
													};
		
		// resetting the parameters of the gridbag
		layout_gridbag.ipady = 0;
		layout_gridbag.ipadx = 0;
		layout_gridbag.anchor = GridBagConstraints.CENTER;
		layout_gridbag.insets = new Insets(5,5,5,5);
		
		for(int i=0;i<dashboard_elements.length;i++){
			jpanel[8].add(dashboard_elements[i], layout_gridbag);
		}
		update_dashboard();
	
	}
	public static void set_img_label_water(String resource){
		img_label.setIcon(new ImageIcon(new ImageIcon(Windows.class.getResource(resource)).getImage().getScaledInstance(400, 200, Image.SCALE_DEFAULT)));
	}
	public static void update_inconly(String inc_str, String only_str, Boolean inc_bool, Boolean only_bool){
		Communication.update("timelapse", "include_time", inc_str);
		Communication.update("timelapse", "only_time", only_str);
		chk_set_time_only.setSelected(only_bool);
		chk_set_time_inc.setSelected(inc_bool);
	}
	public static void update_settings(){
		Communication.request(Communication.req_settings);
		Communication.request(Communication.req_timelapse);
		
		//debug
		/*for(int i = 0; i < Communication.val_settings.length; i++){
			System.out.println(Communication.val_settings[i]);
		}*/
		// image effects 	0
		// bw 				7
		// wm copies 		3
		// wm folder		5
		// timelapse 		12
		// video ef			11
		// video fol		6
		// video stab 		10
		// backup 			1
		// one day			2

		JCheckBox[] checkboxes_easier = {
			chk_set_img_effects,
			chk_set_en_back,
			chk_set_day,
			chk_set_wm_copies,
			chk_set_wm_folder,
			chk_set_vid_sep,
			chk_set_bw,
			chk_set_vid_stbl,
			chk_set_vid_ef,
			chk_set_timelapse
		};
		
		for(int i=0; i<checkboxes_easier.length; i++){
			if(i != 4 || i != 8 || i != 9){
				check_it(Communication.val_settings[i], checkboxes_easier[i]);
			}
		}
		
		// only and include for timelapse
		String only_time 	= Communication.val_timelapse[1];
		String include_time = Communication.val_timelapse[2];
		System.out.println("Only time: " + only_time + "  Include time: " + include_time); // debug
		check_it(only_time, chk_set_time_only);
		check_it(include_time, chk_set_time_inc);
	}
	private static void check_it(String value, JCheckBox checkbox){
		if(value.equals("1")){
			checkbox.setSelected(true);
		}else{
			checkbox.setSelected(false);
		}
	}
	public static void update_dashboard(){
		Communication.request(Communication.req_dashboard);
		
		float cpu_bar 		= Float.parseFloat(Communication.val_dashboard[1]);
		int cpu_temp 		= Math.round(Float.parseFloat(Communication.val_dashboard[2])) / 1000;
		int memory_bar_v 	= Math.round(Float.parseFloat(Communication.val_dashboard[3]));
		
		lasttime_label.setText("Last update time: " + ((Integer.parseInt(Communication.val_dashboard[0]) / 1000) / 60) + " minutes");
		
		cpuusage_bar.setValue(Math.round(cpu_bar));
		cpuusage_bar.setString("CPU: " + cpu_bar + "%");
		
		cputemp_bar.setValue(cpu_temp);
		cputemp_bar.setString("Temperature: " + cpu_temp + "C");
		
		memory_bar.setValue(memory_bar_v);
		memory_bar.setString("Memory: " + memory_bar_v + "%");
		
		usbstatus_label.setText("Usb status: " + Communication.val_dashboard[4]);
		usbcapac_label.setText("Usb capacity: " + Communication.val_dashboard[5]);
		usbused_label.setText("Usb used: " + Communication.val_dashboard[6]);
	}
	public static void checkConnection()
	{
		boolean toldyouso = false;
		
		Communication.request(Communication.req_check);
		if(!public_vars.connected_mysql){
			if(!toldyouso){
				JOptionPane.showMessageDialog(null, "The app disconnected from dbloader. Please restart this app or the dbloader.");
				toldyouso = true;
			}
			Communication.close_TCP();
		}
	}
}
