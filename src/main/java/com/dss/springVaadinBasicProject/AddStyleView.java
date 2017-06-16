package com.dss.springVaadinBasicProject;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.dss.springVaadinBasicProject.dao.MasterServiceDao;
import com.dss.springVaadinBasicProject.dao.SpringDataDao;
import com.dss.springVaadinBasicProject.dto.CountryDto;
import com.dss.springVaadinBasicProject.dto.StyleDto;
import com.dss.springVaadinBasicProject.impl.MasterServiceImpl;
import com.dss.springVaadinBasicProject.impl.SpringDataServiceImpl;
import com.dss.springVaadinBasicProject.model.CountryEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = AddStyleView.NAME)
public class AddStyleView extends FormLayout implements View {
	public static final long serialVersionID =1L;
	public static final String NAME = "AddStyle";
	
	/*@Autowired
	private MasterServiceImpl masterServiceImpl;
	
	@Autowired
	private SpringDataServiceImpl springDataServiceImpl;*/
	
	@Autowired
	private SpringDataDao springDataDao;
	
	@Autowired
	private MasterServiceDao masterServiceDao;
	
	private Button add;
	private Button cancel;
	private Label addLabel;
	private TextField styleNumberText;
	private TextField styleDescText;
	private ComboBox<CountryDto> countryComboBox;
	private Iterable<CountryDto> countryEntityList;
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		countryEntityList = masterServiceDao.findAllCountry();
		countryComboBox = new ComboBox<CountryDto>();
		countryComboBox.setItems((Collection<CountryDto>) countryEntityList);
		countryComboBox.setPlaceholder("Select Country");
		
		addLabel = new Label("Add New Style");
		addLabel.setStyleName(ValoTheme.LABEL_H1);
		
		styleNumberText = new TextField("Style Number :");
		
		styleDescText = new TextField("Style Description :");
		
		add = new Button("Add");
		add.setStyleName(ValoTheme.BUTTON_PRIMARY);
		add.setClickShortcut(KeyCode.ENTER);
		add.addClickListener(e -> {
			
			StyleDto styleEntity = new StyleDto();
			styleEntity.setStyleNo(styleNumberText.getValue());
			styleEntity.setDesc(styleDescText.getValue());
			styleEntity.setCountry(countryComboBox.getValue());
			if(springDataDao.isStyleExist(styleEntity))
			{
				Notification.show("Style Already Exists");
				//getUI().getNavigator().navigateTo(StyleView.NAME);
			}
			else 
			{
				springDataDao.createStyle(styleEntity);
				Notification.show("Added");
				getUI().getNavigator().navigateTo(StyleView.NAME);
			}
			
		});
		
		cancel = new Button("Cancel");
		cancel.addClickListener(e -> {
			Notification.show("Canceled");
			getUI().getNavigator().navigateTo(StyleView.NAME);
		});
		
		HorizontalLayout buttons = new HorizontalLayout(add,cancel);
		buttons.setSpacing(true);
		addComponents(addLabel, styleNumberText, styleDescText, countryComboBox, buttons );
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		
	}
}
