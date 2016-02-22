package com.ticket.detalle.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import com.ticket.detalle.bean.DetalleBean;

public class DetalleDAOImpl extends SqlMapClientDaoSupport implements IDetalleDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleBean> findAll() {
		return (List<DetalleBean>) getSqlMapClientTemplate().queryForList(
		"Detalle.findAll");
	}

	@Override
	public List<DetalleBean> findByLocalizador(String localizador) {
		return (List<DetalleBean>) getSqlMapClientTemplate().queryForList(
				"Detalle.findByLocalizador", localizador);
	}

}
