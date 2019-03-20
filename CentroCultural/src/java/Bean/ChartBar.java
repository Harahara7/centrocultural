package Bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;

@ManagedBean
public class ChartBar implements Serializable {

    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    private LocatarioBean lDAO = new LocatarioBean();
    private EventoBean eDAO = new EventoBean();

    Long qtdAgendado = eDAO.quantidadeStatusAgendado();
    Long qtdConfirmado = eDAO.quantidadeStatusConfirmado();
    Long qtdCancelado = eDAO.quantidadeStatusCancelado();

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:"
        //"Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex()
        );

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

//        ChartSeries boys = new ChartSeries();
//        boys.setLabel("Boy");
//        boys.set("2004", 120);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Quantidade Status");
        girls.set("Agendado", qtdAgendado);
        girls.set("Confirmado", qtdConfirmado);
        girls.set("Cancelado", qtdCancelado);
//        girls.set("2007", 135);
//        girls.set("2008", 120);

//        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createBarModels() {
        createBarModel();
 
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Quantidade por STATUS");
        barModel.setLegendPosition("ne");
        barModel.setAnimate(true);

        Axis xAxis = barModel.getAxis(AxisType.X);
        // xAxis.setLabel("Gender");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        //yAxis.setLabel("Births");
        yAxis.setTickInterval("1");
        yAxis.setMin(0);
        yAxis.setMax(qtdAgendado + qtdConfirmado + qtdCancelado+1);
    }

}
