package whatever;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    private CustomerService service = CustomerService.getInstance();
    private Grid<Customer> grid = new Grid<>(Customer.class);
    private TextField filterText = new TextField();
    private CustomerForm customerForm = new CustomerForm(this);
//    private WorkReportView workReportView = new WorkReportView(this);
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        ReportView reportView = new ReportView(this);
        reportView.setVisible(true);
        setContent(reportView);




        /*final VerticalLayout layout = new VerticalLayout();
        filterText.setPlaceholder("filter by name");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());
        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        Button addNewCustomer = new Button("Add new customer");
        addNewCustomer.addClickListener(e ->{
           grid.asSingleSelect().clear();
           customerForm.setCustomer(new Customer());
        });
        HorizontalLayout toolbar = new HorizontalLayout(addNewCustomer, filtering);
        grid.setColumns("id", "firstName", "lastName", "email");
        HorizontalLayout horizontalLayout = new HorizontalLayout(grid,customerForm);
        horizontalLayout.setSizeFull();
        grid.setSizeFull();
        horizontalLayout.setExpandRatio(grid,1);
        layout.addComponents(toolbar, horizontalLayout);
        updateList();
        setContent(layout);
        customerForm.setVisible(false);
        grid.asSingleSelect().addValueChangeListener(e ->{
            if (e.getValue() == null){
                customerForm.setVisible(false);
            }else{
                customerForm.setCustomer(e.getValue());
            }
        });*/
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    public void updateList(){
        List<Customer> customerList = service.findAll(filterText.getValue());
        grid.setItems(customerList);
    }
}
