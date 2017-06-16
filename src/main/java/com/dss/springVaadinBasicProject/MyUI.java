package com.dss.springVaadinBasicProject;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class MyUI extends UI {

	@Autowired
	private SpringViewProvider viewProvider;
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		final VerticalLayout root = new VerticalLayout();
		setContent(root);
		root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		Label welcome = new Label("Welcome To Shopping Cart...");
		welcome.setStyleName(ValoTheme.LABEL_H1);
		root.addComponents(welcome, createNavigationButton("Click to Continue...", LoginView.NAME));
		Navigator navigator = new Navigator(this, this);
		navigator.addProvider(viewProvider);
		/*getNavigator().addView(InputView.NAME, InputView.class);
		getNavigator().addView(ShowResultView.NAME, ShowResultView.class);
		getNavigator().navigateTo(InputView.NAME);*/
		
	}
	
	private Button createNavigationButton(String caption, final String viewName) {
		Button button = new Button(caption);
		button.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		button.addClickListener(event -> getUI().getNavigator().navigateTo(
				viewName));
		return button;
	}

}
