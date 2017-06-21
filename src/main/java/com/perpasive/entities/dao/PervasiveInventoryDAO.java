package com.perpasive.entities.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.pervasive.entities.PervasiveInventory;

public class PervasiveInventoryDAO {
	
	
	static final String JDBC_DRIVER = "com.pervasive.jdbc.v2.Driver";  
	static final String DB_URL = "jdbc:pervasive://localhost:1583/POC";

    Connection conn = null;
    Statement stmt = null;

	  
	static final String USER = "root";
	static final String PASS = "";
	
	   
	
	public PervasiveInventoryDAO()
	{
		this.registerDriverAndConnectToDB();
		
	}
	
	public String deleteInventoryData(String itemKey) 
	{
		PreparedStatement preparedStatement = null;	
		String remark = "";
		
		try {	
			conn.setAutoCommit(false);
			String sql = "DELETE INMAST WHERE Itemkey = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, itemKey.toUpperCase());
			int rs = preparedStatement.executeUpdate();
			conn.commit();
			remark = "Data Deleted successfully";
			System.out.println(rs + " Data has been deleted...");
	       
	        
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	                remark = "Data Deleted failed";
	            } catch(SQLException excep) {
	                
	            }
	        }
		}
		finally{
			
			if(preparedStatement != null){
				
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}	
			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return remark;
	}
	
	public PervasiveInventory updateInventoryData(PervasiveInventory pervasiveInventory) 
	{
		PreparedStatement preparedStatement = null;	
		
		try {	
			conn.setAutoCommit(false);
			String sql = "UPDATE INMAST SET stockuom = ? WHERE Itemkey = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, pervasiveInventory.getStockuom().toUpperCase());
			preparedStatement.setString(2, pervasiveInventory.getItemkey().toUpperCase());
			int rs = preparedStatement.executeUpdate();
			conn.commit();
			System.out.println(rs + " Data has been updated...");
	       
	        
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	                
	            }
	        }
		}
		finally{
			
			if(preparedStatement != null){
				
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}	
			
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return pervasiveInventory;
	}
	
	public PervasiveInventory addInventoryData(PervasiveInventory pervasiveInventory) throws SQLException
	{
		PreparedStatement preparedStatement = null;	
		
		try {	
			conn.setAutoCommit(false);
			String sql = "INSERT INTO INMAST(Itemkey, Itemdescription1, Itemdescription2, stockuom) VALUES (?, ?, ?, ?)";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, pervasiveInventory.getItemkey().toUpperCase());
			preparedStatement.setString(2, pervasiveInventory.getItemdescription1().toUpperCase());
			preparedStatement.setString(3, pervasiveInventory.getItemdescription2().toUpperCase());
			preparedStatement.setString(4, pervasiveInventory.getStockuom().toUpperCase());
			int rs = preparedStatement.executeUpdate();
			conn.commit();
			System.out.println(rs + " Data has been inserted...");
	       
	        
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			if (conn != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                conn.rollback();
	            } catch(SQLException excep) {
	                
	            }
	        }
		}
		finally{
			
			if(preparedStatement != null){
				
				preparedStatement.close();
				
			}	
			
			conn.setAutoCommit(true);
		}
		
		return pervasiveInventory;
	}
	
	public List<PervasiveInventory> getInventoryData(String Itemdescription)
	{
		List <PervasiveInventory> dataInventory = new ArrayList<PervasiveInventory>();
		PreparedStatement preparedStatement = null;
		
		try {		
			String sql = "SELECT Itemkey, Itemdescription1, Itemdescription2, stockuom FROM INMAST WHERE Itemdescription1 like ? ";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, "%" + Itemdescription.toUpperCase() + "%");
			ResultSet rs = preparedStatement.executeQuery();
	       
	        while(rs.next()){
	           PervasiveInventory pervasiveInventory = new PervasiveInventory();
	           pervasiveInventory.setItemkey(rs.getString("Itemkey"));
	           pervasiveInventory.setItemdescription1(rs.getString("Itemdescription1"));
	           pervasiveInventory.setItemdescription2(rs.getString("Itemdescription2"));
	           pervasiveInventory.setStockuom(rs.getString("stockuom"));
	           dataInventory.add(pervasiveInventory);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
        	
		return dataInventory;
	}
	
	public List<PervasiveInventory> getInventoryDataById(String Itemkey)
	{
		List <PervasiveInventory> dataInventory = new ArrayList<PervasiveInventory>();
		PreparedStatement preparedStatement = null;
		
		try {		
			String sql = "SELECT Itemkey, Itemdescription1, Itemdescription2, stockuom FROM INMAST WHERE Itemkey = ? ";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, Itemkey);
			ResultSet rs = preparedStatement.executeQuery();
	       
	        while(rs.next()){
	           PervasiveInventory pervasiveInventory = new PervasiveInventory();
	           pervasiveInventory.setItemkey(rs.getString("Itemkey"));
	           pervasiveInventory.setItemdescription1(rs.getString("Itemdescription1"));
	           pervasiveInventory.setItemdescription2(rs.getString("Itemdescription2"));
	           pervasiveInventory.setStockuom(rs.getString("stockuom"));
	           dataInventory.add(pervasiveInventory);
	        }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			try {
				conn.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
        
		
		return dataInventory;
	}
	
	public void registerDriverAndConnectToDB()
	{
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
	}

}
