package Query_Selector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Query_getType {

	public static int getType(String Username) {
		int type = -1;
		String query = "Select Type_Account from Account Where username = ?";
		try {
			Connection con = DatabaseConnect.getConnection();

			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, Username);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				type = rs.getInt("type_account");

				rs.close();
				stmt.close();
				con.close();
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return type;

	}

}
