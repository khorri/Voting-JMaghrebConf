package controllers;



import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;


public class Application extends Controller {

    public static Result index() {
        return ok("ok");
    }
    
    public static Result saveVote(Long idSession, Long idVoter, Integer vote, String time) {
    	try {
    	DataSource ds= DB.getDataSource();
    	Connection conn=DB.getConnection();
    	String INSERT_SQL = "INSERT INTO votes " + "(idSession, idVoter, vote, time) "
                + " VALUES " + "("+idSession+", "+idVoter +", "+vote+", '"+time+"')";
    	
			Statement stat=conn.createStatement();
			stat.execute(INSERT_SQL);
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			return ok("error when inserting");
		}

    	return ok("Done");
     
    }
    
    
}
