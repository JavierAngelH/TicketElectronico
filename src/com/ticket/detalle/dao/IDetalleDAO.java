package com.ticket.detalle.dao;

import java.util.List;

import com.ticket.detalle.bean.DetalleBean;

public interface IDetalleDAO {

	List<DetalleBean> findAll();
	List<DetalleBean> findByLocalizador(String localizador);
	
}
