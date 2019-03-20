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
public class ChartView implements Serializable {

    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    private LocatarioBean lDAO = new LocatarioBean();


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

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boy");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createBarModels() {

        createHorizontalBarModel();
    }



    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();

        //pega a quantidade de físico e jurídico
        int qtdPf = lDAO.quantidadeLocatarioFisico();
        int qtdPj = lDAO.quantidadeLocatarioJuridico();
        
        ChartSeries barra = new ChartSeries();
        barra.setLabel("Pessoa Física");
        barra.set("Jurídica", qtdPj);
        barra.set("Física", qtdPf);
//        boys.set("2006", 44);
//        boys.set("2007", 55);
//        boys.set("2008", 25);

//        ChartSeries girls = new ChartSeries();
//        girls.setLabel("Pessoa Jurídica");
//        girls.set("2004", 52);
//        girls.set("2005", 60);
//        girls.set("2006", 82);
//        girls.set("2007", 35);
//        girls.set("2008", 120);

        //Caracteristicas da barra
        horizontalBarModel.addSeries(barra);
        //horizontalBarModel.addSeries(girls);

        horizontalBarModel.setTitle("Quantidade por tipo de pessoa");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
        horizontalBarModel.setBarPadding(0);
        horizontalBarModel.setAnimate(true);

        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Quantidade");
        xAxis.setTickInterval("1");
        xAxis.setMin(0);
        xAxis.setMax(qtdPf+qtdPj+1);//esta soma é para a tela grafica se ajustar

        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tipo de Pessoa");
    }

}
