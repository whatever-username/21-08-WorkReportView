package whatever;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Innokentiy on 21.08.2019.
 */
public class WorkReportView extends Window {
    //вид отчета по работам
    UI ui;
    List<CheckBox> checkBoxList = formCheckBoxes();
    Grid<Work> workGrid;
    HorizontalLayout toolbar = new HorizontalLayout();
    private Map<String,String> columnNames = fillColumnNames();
    VerticalLayout time = new VerticalLayout();
    VerticalLayout columns = new VerticalLayout();
    VerticalLayout brigade = new VerticalLayout();
    VerticalLayout verticalLayout= new VerticalLayout();
    public WorkReportView(UI ui){
        super("Отчет по работам");
        this.ui = ui;
        setSizeFull();
        center();
        setClosable(true);
        setContent(verticalLayout);
        //1-я колонка
        DateField startDateField = new DateField();
        startDateField.setCaption("Начало");
        startDateField.setDateFormat("hh.mm dd.mm.yyy");
        DateField endDateField = new DateField();
        endDateField.setCaption("Конец");
        endDateField.setDateFormat("hh.mm dd.mm.yyy");
        Button refreshButton = new Button("Обновить");
        refreshButton.addClickListener(e ->{
           //логика обновления
        });
        time.addComponents(startDateField,endDateField,refreshButton);
        //2-я
        checkBoxList.stream().forEach((s)->{
            columns.addComponent(s);
        });
        columns.setCaption("Столбцы");

        //3-я
        NativeSelect<String> brigadeName = new NativeSelect<>("Бригада");
        brigadeName.setItems("A","B","C");
        NativeSelect<String> reportType = new NativeSelect<>("Формат отчёта");
        reportType.setItems("pdf","doc","txt");
        brigade.addComponents(brigadeName,reportType);


        updateColumns();
        toolbar.addComponents(time,columns,brigade);
        toolbar.setSizeFull();
        workGrid.setSizeFull();

        Button print = new Button("Печать");
        print.addClickListener(e->{
           //обработка нажатия кнопки печати
        });
        Button watch= new Button("Просмотр");
        watch.addClickListener(e->{
           //обработка нажатия кнопки просмотра
        });
        Button save = new Button("Сохранить");
        save.addClickListener(e->{
           //обработка нажатия кнопки сохранения
        });
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(print,watch,save);
        verticalLayout.addComponents(toolbar,workGrid,buttons);




    }
    public List<CheckBox> formCheckBoxes(){

        //получать из WorkDto
        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(new CheckBox("Адрес"));
        checkBoxes.add(new CheckBox("Описание"));
        checkBoxes.add(new CheckBox("Исполнитель"));
        checkBoxes.add(new CheckBox("Начало по плану"));
        checkBoxes.add(new CheckBox("Окончание по плану"));
        checkBoxes.add(new CheckBox("Источник"));
        checkBoxes.add(new CheckBox("Норматив, ч"));
        checkBoxes.stream().forEach((s) ->{
            s.addValueChangeListener(e ->{
                //убрать или добавить к grid колонку
                updateColumns();
            });
        });
        return checkBoxes;
    }
    private void updateColumns(){
        if(workGrid==null){
            workGrid = new Grid<>(Work.class);

            /*workGrid.getColumn("address").setCaption("Адрес");
            workGrid.getColumn("description").setCaption("Описание");
            workGrid.getColumn("executor").setCaption("Исполнитель");
            workGrid.getColumn("startDatePlan").setCaption("Начало по плану");
            workGrid.getColumn("finishDatePlan").setCaption("Окончание по плану");
            workGrid.getColumn("source").setCaption("Источник");*/
        }

        for (CheckBox cb: checkBoxList) {
            if (cb.getValue()){
                if(workGrid.getColumn(columnNames.get(cb.getCaption()))==null){
                    workGrid.addColumn(columnNames.get(cb.getCaption())).setCaption(cb.getCaption());
                }
            }else{
                if (workGrid.getColumn(columnNames.get(cb.getCaption()))!=null){
                    workGrid.removeColumn(columnNames.get(cb.getCaption()));
                }
            }
        }
    }
    private Map<String,String> fillColumnNames(){
        //костыль. заменить на получение из WorkOrderConstants
        Map<String,String> columns = new HashMap<>();
        columns.put("Адрес","address");
        columns.put("Описание","description");
        columns.put("Исполнитель","executor");
        columns.put("Начало по плану","startDatePlan");
        columns.put("Окончание по плану","finishDatePlan");
        columns.put("Источник","source");
        columns.put("Норматив, ч","norm");
        return columns;
    }
}
