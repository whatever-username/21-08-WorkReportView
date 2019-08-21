package whatever;

import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Innokentiy on 21.08.2019.
 */
public class ReportView extends VerticalLayout {
    //вид, отвечающий за показ всех вариантов отчета (по заявкам, по бригадам, ....)
    Label reportText;
    Grid<Report> reportGrid;
    UI ui;
    private List<Report> reports = generateReports();
    public ReportView(UI ui){
        this.ui = ui;
        reportText = new Label("Отчет");
        reportGrid = new Grid<>();
        reportGrid.addColumn(Report::getNumber).setId("id").setCaption("№");
        reportGrid.addColumn(Report::getName).setId("name").setCaption("Название");
        reportGrid.addColumn(Report::getDescription).setId("description").setCaption("Описание");
        reportGrid.setItems(reports);
        reportGrid.asSingleSelect().addValueChangeListener(e ->{
            openReport(e.getValue());
        });
        reportGrid.setSizeFull();
        addComponents(reportText,reportGrid);
    }
    private void openReport(Report report){
        //заменить на динамическое получение объекта окна по типу репорта
        WorkReportView workReportView = new WorkReportView(ui);
        UI.getCurrent().addWindow(workReportView);

    }
    //костыль. Не нашел, где в проекте лежит список видов отчетов. Заменить на получение списка
    private List<Report> generateReports(){
        List<Report> reports = new ArrayList<>();
        reports.add(new Report(1L,"Отчет по работам", "Составляет отчет по работам", WorkReportView.class));
        reports.add(new Report(2L,"Отчет по заявкам", "Составляет отчет по заявкам",null));
        reports.add(new Report(3L,"Отчет по бригадам", "Составляет отчет по бригадам",null));
        reports.add(new Report(4L,"Отчет по сотрудникам", "Составляет отчет по сотрудникам",null));
        reports.add(new Report(5L,"Отчет по событиям", "Составляет отчет по событиям",null));
        return reports;
    }

}
