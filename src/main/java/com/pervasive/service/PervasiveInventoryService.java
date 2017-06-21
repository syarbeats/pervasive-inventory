package com.pervasive.service;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.perpasive.entities.dao.PervasiveInventoryDAO;
import com.pervasive.entities.PervasiveInventory;


@Path("/unionmetal")
public class PervasiveInventoryService {

	PervasiveInventoryDAO inventoryServices = new PervasiveInventoryDAO();
	
	@GET
    @Path("/{namabarang}")
    @Produces(MediaType.APPLICATION_XML)
	public List<PervasiveInventory> getInventoryByNamaBarang(@PathParam("namabarang") String namabarang)
	{
		return inventoryServices.getInventoryData(namabarang);
	}
	
	@GET
    @Path("/databyid/{kodebarang}")
    @Produces(MediaType.APPLICATION_XML)
	public List<PervasiveInventory> getInventoryById(@PathParam("kodebarang") String kodebarang)
	{
		return inventoryServices.getInventoryDataById(kodebarang);
	}
	
	@POST
    @Produces(MediaType.APPLICATION_XML)
	public PervasiveInventory addPervasiveInventory(PervasiveInventory pervasiveInventory)
	{
		try {
			return inventoryServices.addInventoryData(pervasiveInventory);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    @PUT
    @Produces(MediaType.APPLICATION_XML)
	public PervasiveInventory updatePervasiveInventory(PervasiveInventory pervasiveInventory)
	{
		
    	return inventoryServices.updateInventoryData(pervasiveInventory);
				
	}
    
    @DELETE
    @Path("/{itemkey}")
    @Produces(MediaType.APPLICATION_XML)
	public String deleteInventoryByItemkey(@PathParam("itemkey") String itemkey)
	{
		return inventoryServices.deleteInventoryData(itemkey);
	}
    
}

