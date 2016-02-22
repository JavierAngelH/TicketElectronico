package com.ticket.ui;

import java.util.List;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import com.ticket.detalle.bean.DetalleBean;
import com.ticket.service.IControllerService;

@SuppressWarnings({ "serial", "rawtypes" })
public class IndexComposer extends GenericForwardComposer {
	private IControllerService controllerService;
	private Textbox tbLocalizador;
	private Window indexWin;
	private Listbox lbData;

	public void setControllerService(IControllerService controllerService) {
		this.controllerService = controllerService;
	}

	public IControllerService getControllerService() {
		return controllerService;
	}

	public IndexComposer(IControllerService controllerService) {
		super();
		this.controllerService = controllerService;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

	}

	public void onClick$btnBuscar() {
		String localizador = tbLocalizador.getValue();
		fillListBox(lbData, localizador);
	}

	public void onOK$tbLocalizador(){
		String localizador = tbLocalizador.getValue();
		fillListBox(lbData, localizador);
	}
	@SuppressWarnings({ "unchecked" })
	public void fillListBox(final Listbox lst, String localizador) {
		lst.getItems().clear();
		List<DetalleBean> listaDetalles = (List<DetalleBean>) controllerService
				.BusquedaPorLocalizador(localizador);
		
		if (listaDetalles.isEmpty()){
			Messagebox.show("El localizador no corresponde a ningún usuario registrado","Información", Messagebox.OK, Messagebox.EXCLAMATION);
			
		}
		for (DetalleBean detalle : listaDetalles) {
			Listitem li = new Listitem();
			li.setValue(detalle);
			li.appendChild(new Listcell(detalle.getNombres()));
			li.appendChild(new Listcell(detalle.getApellidos()));
			li.appendChild(new Listcell(detalle.getTipoPax()));
			li.appendChild(new Listcell(detalle.getCedula()));
			li.appendChild(new Listcell(detalle.getNumVuelo()));
			li.appendChild(new Listcell(detalle.fechaToString()));
			li.appendChild(new Listcell(detalle.horaToString()));
			li.appendChild(new Listcell(detalle.getOrigen()));
			li.appendChild(new Listcell(detalle.getDestino()));
			li.appendChild(new Listcell(detalle.getAsiento()));
			lst.appendChild(li);
			li.addEventListener(Events.ON_DOUBLE_CLICK, new EventListener() {

				public void onEvent(Event event) throws Exception {
					onDetalle(event);
				}
			});

		}
	}

	public void onDetalle(final Event event) {
		Listitem item = lbData.getSelectedItem();
		DetalleBean detalleBean = null;
		detalleBean = (DetalleBean) item.getValue();
		controllerService.generarReporte(detalleBean, indexWin);
	}

}
