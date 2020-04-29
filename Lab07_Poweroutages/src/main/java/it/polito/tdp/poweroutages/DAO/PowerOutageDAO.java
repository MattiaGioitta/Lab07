package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.Poweroutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<Poweroutages> getPoweroutages(Map<Integer, Nerc> createMap) {
		String sql = "SELECT id,nerc_id,customers_affected,date_event_began,date_event_finished FROM poweroutages ORDER BY date_event_began ASC";
		List<Poweroutages> poweroutages = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				Nerc nerc = createMap.get(rs.getInt("nerc_id"));
				int cu = rs.getInt("customers_affected");
				LocalDateTime db= rs.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime df = rs.getTimestamp("date_event_finished").toLocalDateTime();
				Poweroutages p = new Poweroutages(id,nerc,cu,db,df);
				poweroutages.add(p);
				
			}
			conn.close();
			return poweroutages;
			}catch(SQLException e ) {
			throw new RuntimeException(e);
		}
		
	}
	

}
