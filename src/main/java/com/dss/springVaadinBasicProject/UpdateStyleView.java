package com.dss.springVaadinBasicProject;

import java.util.Collection;
import java.util.Set;

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
import com.vaadin.server.VaadinSession;
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

@SpringView(name = UpdateStyleView.NAME)
public class UpdateStyleView extends FormLayout implements View {

	public static final long serialVersionID = 1L;
	public static final String NAME = "UpdateStyle";

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
	private Integer id;

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

		Set<StyleDto> style =  (Set<StyleDto>) VaadinSession.getCurrent().getAttribute("Style");
		//StyleDto style = (StyleDto) VaadinSession.getCurrent().getAttribute("Style");
		for (StyleDto styleDto : style) {
			styleNumberText.setValue(styleDto.getStyleNo());
			styleDescText.setValue(styleDto.getDesc());
			countryComboBox.setValue(styleDto.getCountry());
			id = styleDto.getId();
		}
		add = new Button("Update");
		add.setStyleName(ValoTheme.BUTTON_PRIMARY);
		add.setClickShortcut(KeyCode.ENTER);
		add.addClickListener(e -> {

			StyleDto updateStyle = springDataDao.findByStyleId(id);
			updateStyle.setStyleNo(styleNumberText.getValue());
			updateStyle.setDesc(styleDescText.getValue());
			updateStyle.setCountry(countryComboBox.getValue());
			springDataDao.updateStyle(updateStyle);
			Notification.show("Updated");
			getUI().getNavigator().navigateTo(StyleView.NAME);
			
		});

		cancel = new Button("Cancel");
		cancel.addClickListener(e -> {
			Notification.show("Canceled");
			getUI().getNavigator().navigateTo(StyleView.NAME);
		});

		HorizontalLayout buttons = new HorizontalLayout(add, cancel);
		buttons.setSpacing(true);
		addComponents(addLabel, styleNumberText, styleDescText, countryComboBox, buttons);
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

	}

}
