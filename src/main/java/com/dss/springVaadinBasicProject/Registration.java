package com.dss.springVaadinBasicProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dss.springVaadinBasicProject.impl.SpringDataServiceImpl;
import com.dss.springVaadinBasicProject.model.LoginEntity;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class Registration extends Window  implements View{
	
	public static final long serialVersionID = 1L;
	public static final String NAME = "Register";
	
	
	//@Qualifier("serviceImpl")
	@Autowired
	private SpringDataServiceImpl springDataServiceImpl;
	
	//@Autowired
	private LoginEntity loginEntity;
	
	private TextField userName;
	private PasswordField password;
	private PasswordField confirmPassword;
	private Button registerButton;
	private Window window;
	private VerticalLayout layout;
	
	public Registration() {
		createWindow();
	}
	
	public Registration(String caption) {
		super(caption);
		createWindow();
	}
	
	public void createWindow() {
		
		
		userName = new TextField("Username");
		password = new PasswordField("Password");
		confirmPassword = new PasswordField("Confirm Password");
		registerButton = new Button("Register");
		registerButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		registerButton.setClickShortcut(KeyCode.ENTER);
		
		registerButton.addClickListener(e -> { 
			//System.out.println(""+userName.getValue()+""+password.getValue());
			if(springDataServiceImpl.loginValidator(userName.getValue(), password.getValue()))
			{
				Notification.show("User Already Exists");
			}
			else
			{
				if ((password.getValue()).equals(confirmPassword.getValue()))
					{
						loginEntity=new LoginEntity();
						loginEntity.setUserName(userName.getValue());
						loginEntity.setPassword(password.getValue());
						springDataServiceImpl.saveLogin(loginEntity);
					}
				else
					Notification.show("Password and Confirm Password should be same");
			}
		});
		
		layout = new VerticalLayout();
		setContent(layout);
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		layout.addComponents(userName,password,confirmPassword,registerButton);
		setPosition(20,20);
		setWidth("250px");
		setHeight("450px");
		setModal(true);
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	

}