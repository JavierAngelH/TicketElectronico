package com.ticket.detalle.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DetalleBean implements Serializable {

private String localizador;
private String apellidos;
private String nombres;
private String tipoPax;
private String cedula;
private String numVuelo;
private Date fechaVuelo;
private String origen;
private String destino;
private String asiento;
private Timestamp horaCheck;

    
public String getLocalizador() {
	return localizador;
}
public void setLocalizador(String localizador) {
	this.localizador = localizador;
}
public String getApellidos() {
	return apellidos;
}
public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}
public String getNombres() {
	return nombres;
}
public void setNombres(String nombres) {
	this.nombres = nombres;
}
public String getTipoPax() {
	return tipoPax;
}
public void setTipoPax(String tipoPax) {
	this.tipoPax = tipoPax;
}
public String getCedula() {
	return cedula;
}
public void setCedula(String cedula) {
	this.cedula = cedula;
}
public String getNumVuelo() {
	return numVuelo;
}
public void setNumVuelo(String numVuelo) {
	this.numVuelo = numVuelo;
}
public Date getFechaVuelo() {
	return fechaVuelo;
}
public void setFechaVuelo(Date fechaVuelo) {
	this.fechaVuelo = fechaVuelo;
}
public String getOrigen() {
	return origen;
}
public void setOrigen(String origen) {
	this.origen = origen;
}
public String getDestino() {
	return destino;
}
public void setDestino(String destino) {
	this.destino = destino;
}
public String getAsiento() {
	return asiento;
}
public void setAsiento(String asiento) {
	this.asiento = asiento;
}
public Timestamp getHoraCheck() {
	return horaCheck;
}
public void setHoraCheck(Timestamp horaCheck) {
	this.horaCheck = horaCheck;
}

public String fechaToString(){
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	return sdf.format(this.getFechaVuelo());
}

public String horaToString(){
	String hora="";
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	if (this.getHoraCheck()!=null){
		return sdf.format(this.getHoraCheck());
	}
	return hora;
}

//public DetalleBean(String localizador, String apellidos, String nombres,
//		String tipoPax, String cedula, String numVuelo, Date fechaVuelo,
//		String origen, String destino, String asiento, Date horaCheck) {
//	super();
//	this.localizador = localizador;
//	this.apellidos = apellidos;
//	this.nombres = nombres;
//	this.tipoPax = tipoPax;
//	this.cedula = cedula;
//	this.numVuelo = numVuelo;
//	this.fechaVuelo = fechaVuelo;
//	this.origen = origen;
//	this.destino = destino;
//	this.asiento = asiento;
//	this.horaCheck = horaCheck;
//}
@Override
public String toString() {
	return "DetalleBean [Localizador=" + localizador + ", apellidos="
			+ apellidos + ", nombres=" + nombres + ", tipoPax=" + tipoPax
			+ ", cedula=" + cedula + ", numVuelo=" + numVuelo + ", fechaVuelo="
			+ fechaVuelo + ", origen=" + origen + ", destino=" + destino
			+ ", asiento=" + asiento + ", horaCheck=" + horaCheck + "]";
}
public DetalleBean() {
	super();
}





}
