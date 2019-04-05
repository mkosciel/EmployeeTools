package mvc.employee.model.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.employee.model.Job;

public class JobsDAL {

	SQLException ex;
	public SQLException getSQLException() {
		return ex;
	}

	public JobsDAL() { }
	
	public ObservableList<Job> getJobs() {
		
		ObservableList<Job> jobs = FXCollections.observableArrayList();
		try (Statement statement = OraConn.getConnection().createStatement();) {
		    
			String query = "SELECT * FROM JOBS";
	        ResultSet resultSet = statement.executeQuery(query);
	        
	        while (resultSet.next()) {
	        	jobs.add(rs2Job(resultSet));
	        }
		}
		catch (SQLException ex ) {
			System.out.println(ex);
		} 
		return jobs;
	}
	public ObservableList<Job>  getJobsByJobId(String JobId) {
		
		ObservableList<Job> jobs = FXCollections.observableArrayList();
		try (Statement statement = OraConn.getConnection().createStatement();) {
		    
			String query = "SELECT * FROM JOBS WHERE JOB_ID ='" + JobId + "'";
	        ResultSet resultSet = statement.executeQuery(query);
	        
	        while (resultSet.next()) {
	        	jobs.add(rs2Job(resultSet));
	        }
		}
		catch (SQLException ex ) {
			System.out.println(ex);
		} 
		return jobs;
	}
	
	private Job rs2Job(ResultSet resultSet){
		Job job = new Job();
		try {
			int col = 1;
			job.setJobId(resultSet.getString(col++));
			job.setJobTitle(resultSet.getNString(col++));
			job.setMinSalary(resultSet.getInt(col++));
			job.setMaxSalary(resultSet.getInt(col++));
		}
		catch (SQLException ex ) {
			this.ex = ex;
		}
		return job;
	}

}
