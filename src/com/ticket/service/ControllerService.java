package com.ticket.service;


import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;


import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.Window;


import com.ticket.detalle.bean.DetalleBean;
import com.ticket.detalle.dao.IDetalleDAO;

public class ControllerService implements IControllerService {
	private IDetalleDAO detalleDAO;
	
	private static final String REPORT_URI = "/WEB-INF/reporte/ticketElectronico.jasper";
	
	
	
	
	public IDetalleDAO getDetalleDAO() {
		return detalleDAO;
	}




	public void setDetalleDAO(IDetalleDAO detalleDAO) {
		this.detalleDAO = detalleDAO;
	}




	public ControllerService(IDetalleDAO detalleDAO) {
		super();
		this.detalleDAO = detalleDAO;
	}


	public String generarCodigo(DetalleBean detalleBean){
	String codigo = "";
	String formatCode = "M";
	String numberOfLegsEncoded = "1";
	String passengerName = detalleBean.getApellidos().trim()+"/"+detalleBean.getNombres().trim();
	if(passengerName.length()<20){
		passengerName = StringUtils.rightPad(passengerName, 20, " ");
	}
	else if(passengerName.length()>20){
		passengerName = StringUtils.substringBefore(detalleBean.getApellidos(), " ") + "/" + StringUtils.substringBefore(detalleBean.getNombres(), " ");
		passengerName = StringUtils.rightPad(passengerName, 20, " ");
	}
	String electronicTicketIndicator ="E";
	String operatingCarrierPNRCode = StringUtils.rightPad("", 7,"0");
	String fromCityAirportCode = detalleBean.getOrigen();
	String toCityAirportCode = detalleBean.getDestino();
	String operatingCarrierDesignator = StringUtils.rightPad("", 3, " ");
	String flightNumber = StringUtils.leftPad(detalleBean.getNumVuelo(), 5, "0");
    Calendar ca1 = Calendar.getInstance();
	ca1.setTime(detalleBean.getFechaVuelo());
	Integer julianTime = ca1.get(Calendar.DAY_OF_YEAR);
	String DateOfFlight= julianTime.toString();
	if (DateOfFlight.length()==2) DateOfFlight = "0" + DateOfFlight;
	String compartmentCode= "F";
	String seatNumber = StringUtils.leftPad(detalleBean.getAsiento(),4,"0");
	String checkInSequence = "00000";
	String passengerStatus ="1";
	String followingVariableField="00";
	
	codigo = formatCode + numberOfLegsEncoded + passengerName + electronicTicketIndicator + operatingCarrierPNRCode +
	fromCityAirportCode + toCityAirportCode + operatingCarrierDesignator + flightNumber + DateOfFlight + 
	compartmentCode + seatNumber + checkInSequence + passengerStatus + followingVariableField;
	
		return codigo;
	}

	public void generarReporte(DetalleBean detalleBean, Window parentWindow)  {
		
		WebApp webApp = Executions.getCurrent().getDesktop().getWebApp();
		String jasperPath = webApp.getRealPath(REPORT_URI);
		String nombreCompleto = detalleBean.getApellidos() + " / " + detalleBean.getNombres()+ ", " + detalleBean.getTipoPax();
		Map parameters = new HashMap();
		
		parameters.put("ASIENTO", detalleBean.getAsiento());
		parameters.put("IDENTIFICACION", detalleBean.getCedula());
		parameters.put("DESTINO", detalleBean.getDestino());
		parameters.put("FECHA_VUELO", detalleBean.fechaToString());
		parameters.put("HORA_CHECK", detalleBean.horaToString());
		parameters.put("LOCALIZADOR", detalleBean.getLocalizador());
		parameters.put("NOMBRE_COMPLETO", nombreCompleto);
		parameters.put("NUMERO_VUELO", detalleBean.getNumVuelo());
		parameters.put("ORIGEN", detalleBean.getOrigen());
		parameters.put("TIPO_PAX", detalleBean.getTipoPax());
	    String codigoBarras = generarCodigo(detalleBean);
	    System.out.println(codigoBarras);
		parameters.put("CODIGO_BARRA", codigoBarras);
      
    Window win = new Window("ticket", "normal", true);
    win.setPosition("center");
    win.setWidth("80%");
    win.setHeight("80%");
    Jasperreport jr = new Jasperreport(jasperPath);
    jr.setWidth("100%");
    jr.setHeight("100%");
    jr.setParent(win);
    jr.setParameters(parameters);
    jr.setType("pdf");
    win.setParent(parentWindow);
    win.doModal();
		
	}

	public List<DetalleBean> BusquedaPorLocalizador(String localizador){
		return detalleDAO.findByLocalizador(localizador);
	}
	
	
}


