package it.polito.tdp.extflightdelays.db;

import it.polito.tdp.extflightdelays.model.AirportIdMap;

public class TestDAO {

	public static void main(String[] args) {

		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		
		AirportIdMap idMap= new AirportIdMap();

		System.out.println(dao.loadAllAirlines().size());
		System.out.println(dao.loadAllAirports(idMap).size());
		System.out.println(dao.loadAllFlights().size());
		System.out.println(dao.getVertx(10, idMap).size());

	}

}
