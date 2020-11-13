package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Constants;
import model.Car;
import model.SellingProposition;

public class SellingPropositionDAO extends AbstractDAO {
	
	public SellingPropositionDAO() {
		super();
	}

	public List<SellingProposition> getSellingProposition() {
		var sellingProposition = new ArrayList<SellingProposition>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(Constants.SELECT_PROPOSITION);
			while (rs.next()) {
				sellingProposition.add(new SellingProposition(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getDate(5)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return sellingProposition;
	}

	
}
