package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import entity.model.Policy;
import util.DBConnection;

public class InsuranceServiceImpl implements IPolicyService{
	private Connection conn ;
	public InsuranceServiceImpl() {
        conn = DBConnection.getConnection(); // Initialize the connection in the constructor

    }

	@Override
	public boolean createPolicy(Policy policy) {
		try {
            String query = "INSERT INTO policies (policyId, policyName) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);// object that represents a recompiled sql statement
            // Set parameters
            stmt.setInt(1, policy.getPolicyId());
            stmt.setString(2, policy.getPolicyName());
            // Set other parameters
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;//If the change is made then the rows affected would be > 0 that is true
        } catch (Exception e) {
            e.printStackTrace();//Traces and Shows the line which has the error
            return false;
        }
	}

	@Override
	public Policy getPolicy(int policyID) {
		 try {
	            String query = "SELECT * FROM policies WHERE policyId = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setInt(1, policyID);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                // Create and return a Policy object
	                Policy policy = new Policy();
	                policy.setPolicyId(rs.getInt("policyId"));
	                policy.setPolicyName(rs.getString("policyName"));
	                // Set other fields
	                return policy;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return null;
	}

	@Override//In Array list <> we can dynamically store the values
	public Collection<Policy> getAllPolicies() {
		Collection<Policy> policies = new ArrayList<>();
        try {
            String query = "SELECT * FROM policies";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Policy policy = new Policy();
                policy.setPolicyId(rs.getInt("policyId"));
                policy.setPolicyName(rs.getString("policyName"));
                // Set other fields
                policies.add(policy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return policies;
	}

	@Override
	public boolean updatePolicy(Policy policy) {
		try {
            String query = "UPDATE policies SET policyName = ? WHERE policyId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, policy.getPolicyName());
            // Set other parameters
            stmt.setInt(2, policy.getPolicyId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean deletePolicy(int policyId) {
		try {
            String query = "DELETE FROM policies WHERE policyId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, policyId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	

}
