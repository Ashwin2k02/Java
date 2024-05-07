package dao;

import java.util.Collection;

import entity.model.Policy;

public interface IPolicyService{
	 boolean createPolicy(Policy policy);
	 Policy getPolicy(int policyID);
	 Collection<Policy> getAllPolicies();
	 boolean updatePolicy(Policy policy);
	 boolean deletePolicy(int policyID);

}
