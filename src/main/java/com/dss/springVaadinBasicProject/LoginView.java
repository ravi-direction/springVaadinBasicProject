package com.dss.springVaadinBasicProject;

import org.apache.tomcat.util.buf.UDecoder;
import org.springframework.beans.factory.annotation.Autowired;

import com.dss.springVaadinBasicProject.dao.SpringDataDao;
import com.dss.springVaadinBasicProject.dto.LoginDto;
import com.dss.springVaadinBasicProject.impl.SpringDataServiceImpl;
import com.dss.springVaadinBasicProject.model.LoginEntity;
import com.vaadin.data.Validator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = LoginView.NAME)
public class LoginView extends VerticalLayout implements View{
	
	
	public static final long serialVersionID =1L;
	public static final String NAME = "Login";
	
	@Autowired
	private SpringDataDao springDataDao;
	
	private TextField userName;
	private PasswordField password;
	private Label loginLabel;
	private TextField windowUserName;
	private PasswordField windowPassword;
	private PasswordField confirmPassword;
	private Button loginButton;
	private Button registerButton;
	private HorizontalLayout buttons;
	private Window window;
	private LoginDto loginDto;
	private VerticalLayout registrationLayout;
	private Button windowRegister;
	
	@Override
	public void enter(ViewChangeEvent event) {
	
	 
	 buttons = new HorizontalLayout();
	 loginLabel = new Label("Log In");
	 loginLabel.setStyleName(ValoTheme.LABEL_H1);
	 userName = new TextField("User Name :");
	 password = new PasswordField("Password :");
	 windowUserName = new TextField("User Name :");
	 windowPassword = new PasswordField("Password :");
	 loginButton = new Button("Login");
	 loginButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
	 loginButton.setClickShortcut(KeyCode.ENTER);
	 //registerLabel = new Label("New User ???");
	 
	 confirmPassword = new PasswordField("ConfirmPassword :");
	 windowRegister = new Button("Register");
	 
	 registerButton = new Button ("Register");
	 registrationLayout = new VerticalLayout();
	 registrationLayout.addComponents(windowUserName,windowPassword,confirmPassword,windowRegister);
	 
	 window = new Window("Registration");
	 window.setContent(registrationLayout);
	 window.center();
	 window.setWidth("250px");
	 window.setHeight("450px");
	 window.setModal(true);
	 
	 
	 setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
	 setSpacing(true);
	 buttons.setSpacing(true);
	 
	 loginButton.addClickListener(e -> {
		 if(springDataDao.validateUser(userName.getValue(), password.getValue()))
		//	 Notification.show("Valid Credentials");
			 getUI().getNavigator().navigateTo(StyleView.NAME);
		else
			 Notification.show("Invalid Credentials");
	 });
	 
	 registerButton.addClickListener(e -> {
		 if (window.getParent() == null) {
				UI.getCurrent().addWindow(window);
				
				windowRegister.addClickListener( e1-> {
				
					if(springDataDao.validateUser(windowUserName.getValue(), windowPassword.getValue()))
					{
						Notification.show("User Already Exists");
					}
					else
					{
						if ((windowPassword.getValue()).equals(confirmPassword.getValue()))
							{
								loginDto=new LoginDto();
								loginDto.setUserName(windowUserName.getValue());
								loginDto.setPassword(windowPassword.getValue());
								springDataDao.saveLogin(loginDto);
							}
						else
							Notification.show("Password and Confirm Password should be same");
					}
					window.close();
				});
			 }
	 });
	 
	 buttons.addComponents(loginButton, registerButton);
	 addComponents(loginLabel,userName, password, buttons );
	 

	}
	

}
