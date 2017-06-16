package com.dss.springVaadinBasicProject;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dss.springVaadinBasicProject.dao.MasterServiceDao;
import com.dss.springVaadinBasicProject.dao.SpringDataDao;
import com.dss.springVaadinBasicProject.dto.CountryDto;
import com.dss.springVaadinBasicProject.dto.StyleDto;
import com.dss.springVaadinBasicProject.dto.StyleOverFilterDto;
import com.dss.springVaadinBasicProject.impl.MasterServiceImpl;
import com.dss.springVaadinBasicProject.impl.SpringDataServiceImpl;
import com.dss.springVaadinBasicProject.model.CountryEntity;
import com.dss.springVaadinBasicProject.model.StyleEntity;
import com.dss.springVaadinBasicProject.model.StyleOverFilter;
import com.dss.springVaadinBasicProject.service.Service;
import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.ProgressBarRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = StyleView.NAME)
public class StyleView extends VerticalLayout implements View{

	public static final long serialVersionID = 1L;
	public static final String NAME = "Style";
	
	@Autowired
	private SpringDataDao springDataDao;
	
	@Autowired
	private MasterServiceDao masterServiceDao;
	private TextField styleNumberFilter;
	private Iterable<CountryDto> countryEntityList= masterServiceDao.findAllCountry();;
	private ComboBox<CountryDto> countryComboBox;
	private List<StyleDto> styles;
	private ListDataProvider<StyleDto> dataProvider;
	private TextField gridStyleNo;
	private TextField gridStyleDesc;
	private ComboBox<CountryDto> gridCountry;
	private HorizontalLayout buttons;
	private Button searchButton;
	private Button addStyleButton;
	private Button deleteStyleButton;
	private Button updateButton;
	private Button yesButton;
	private Button noButton;
	private Button logoutButton;
	private Button refreshButton;
	private Grid<StyleDto> grid = new Grid<StyleDto>(StyleDto.class);
	private Window window = new Window();
	private Integer id;
	private Pagination pagination=new Pagination();
	private FormLayout formLayout;
	private ListDataProvider<StyleDto> styleDataProvider;
	
	@Override
	public void enter(ViewChangeEvent event) {
	
		buttons = new HorizontalLayout();
		buttons.setSizeUndefined();
		buttons.setSpacing(true);
		
		styleNumberFilter = new TextField();
		styleNumberFilter.setPlaceholder("Style Number");
		
		searchButton = new Button("");
		searchButton.setIcon(VaadinIcons.SEARCH);
		
		addStyleButton = new Button("Add Style");
		deleteStyleButton = new Button("Delete Style");
		updateButton = new Button("Update Style");
		logoutButton = new Button("Logout");
		logoutButton.setStyleName(ValoTheme.BUTTON_DANGER);
		refreshButton = new Button();
		refreshButton.setIcon(VaadinIcons.REFRESH);
		yesButton = new Button("Yes");
		noButton = new Button("No");
		
		
		
		final VerticalLayout popupVLayout = new VerticalLayout();
		Label label = new Label("Do You Really Want to Delete It");
		HorizontalLayout horizontalLayout1 = new HorizontalLayout();
		horizontalLayout1.addComponents(yesButton, noButton);
		popupVLayout.addComponents(label, horizontalLayout1);
		
		window.setContent(popupVLayout);
		window.center();
		window.setWidth("350px");
		window.setHeight("100px");
		
		countryComboBox = new ComboBox<CountryDto>();
		countryComboBox.setItems((Collection<CountryDto>) countryEntityList);
		countryComboBox.setPlaceholder("Select Country");
			
		updateGrid();
				
		searchButton.addClickListener(e ->{
			
			updateGrid();
			
		});
		
		grid.addSelectionListener(e -> {
			deleteStyleButton.addClickListener(e1 -> {
				if (window.getParent() == null) {
					UI.getCurrent().addWindow(window);

					if (grid.asSingleSelect() != null) {
						Set<StyleDto>  style = (Set<StyleDto>) grid.getSelectedItems();
						for (StyleDto styleEntity : style) {
							id = styleEntity.getId();
						}
						yesButton.addClickListener(e2 -> {
							springDataDao.deleteStyle(id);
							window.close();
							updateGrid();
						});

						noButton.focus();
						noButton.addClickListener(e2 -> {
							window.close();
						});
					}
				}
			});
		});
		
		/*grid.addSelectionListener(e ->{
			if(grid.asSingleSelect()!=null)
			{
				updateButton.addClickListener(e1 ->{	
					VaadinSession.getCurrent().setAttribute("Style", grid.getSelectedItems());
					getUI().getNavigator().navigateTo(UpdateStyleView.NAME);		
				});
			}
		});*/
				
		addStyleButton.addClickListener(e ->{		
				getUI().getNavigator().navigateTo(AddStyleView.NAME);		
		});
		
		logoutButton.addClickListener(e ->{
			getUI().getNavigator().navigateTo(LoginView.NAME);
		});
		
		refreshButton.addClickListener(e ->{
			getUI().getNavigator().navigateTo(StyleView.NAME);
		});
	
	}
	
	public void updateGrid() {
		grid.setColumns("id");
				
		if((styleNumberFilter.getValue()==null)&&(countryComboBox.getValue()==null)) {
			styles = springDataDao.findAllStyles();
			styleDataProvider = DataProvider.ofCollection((Collection<StyleDto>) styles.subList(1, 7));
		}
		else {
			StyleOverFilterDto filterEntity = new StyleOverFilterDto();
			filterEntity.setStyleNo(styleNumberFilter.getValue());
			filterEntity.setCountry(countryComboBox.getValue());
			styles = springDataDao.filterByStyleNoAndCountry(filterEntity);
			styleDataProvider = DataProvider.ofCollection((Collection<StyleDto>) styles.subList(1, 7));
		}
		pagination = createPagination(styles.size(), 1, 7);
		
		gridStyleNo = new TextField();
		gridStyleDesc = new TextField();
		gridCountry = new ComboBox<CountryDto>();
		gridCountry.setItems((Collection<CountryDto>) countryEntityList);
		
		grid.addColumn(StyleDto::getStyleNo).setEditorComponent(gridStyleNo,StyleDto::setStyleNo).setCaption("Style Number").setExpandRatio(1);
		grid.addColumn(StyleDto::getDesc).setEditorComponent(gridStyleDesc,StyleDto::setDesc).setCaption("Style Description").setExpandRatio(1);
		grid.addColumn(StyleDto::getCountry).setEditorComponent(gridCountry,StyleDto::setCountry).setCaption("Country").setExpandRatio(1);
		
		grid.getEditor().setEnabled(true);
		grid.getEditor().addSaveListener(e ->{
			
			StyleDto styleDto = e.getBean();
			StyleDto updateStyleDto = springDataDao.findByStyleId(styleDto.getId());
			
			updateStyleDto.setStyleNo(gridStyleNo.getValue());
			updateStyleDto.setDesc(gridStyleDesc.getValue());
			updateStyleDto.setCountry(gridCountry.getSelectedItem().get());
			springDataDao.updateStyle(updateStyleDto);
		});
		
		grid.clearSortOrder();
		grid.setDataProvider(dataProvider);
		grid.setSizeFull();
		
		pagination.addPageChangeListener(e ->{
		grid.setItems(styles.subList(e.fromIndex(), e.toIndex()));
	
		
		
		
		
		
		
		
		
		
		
		});
		removeAllComponents();
		buttons.addComponents(styleNumberFilter, countryComboBox, searchButton, addStyleButton, deleteStyleButton, updateButton, refreshButton, logoutButton);
		addComponents(buttons, grid,pagination);
		grid.setSizeFull();
	}
	
	private Pagination createPagination(long total, int page, int limit) {
	    final PaginationResource paginationResource = PaginationResource.newBuilder().setTotal(total).setPage(page).setLimit(limit).build();
	    final Pagination pagination = new Pagination(paginationResource);
	    pagination.setItemsPerPage(5, 10, 15);
	    return pagination;
	}
	
	
}
