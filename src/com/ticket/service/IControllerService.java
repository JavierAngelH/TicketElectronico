package com.ticket.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.zkoss.zul.Window;

import com.ticket.detalle.bean.DetalleBean;

public interface IControllerService {

	
	public void generarReporte(DetalleBean detalleBean, Window parentWindow);
	
	public List<DetalleBean> BusquedaPorLocalizador(String localizador);
}
