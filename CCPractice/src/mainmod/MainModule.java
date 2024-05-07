package mainmod;

import java.util.Collection;
import java.util.Scanner;

import dao.IPolicyService;
import dao.InsuranceServiceImpl;
import entity.model.Policy;
import myexceptions.PolicyNotFoundException;

public class MainModule {

	public static void main(String[] args) {
		IPolicyService policyService = new InsuranceServiceImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	//System.out.println("Menu:");
            System.out.println("Menu:");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1: //Creating a new policy
                    System.out.println("Enter Policy ID:");
                    int policyId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.println("Enter Policy Name:");
                    String policyName = scanner.nextLine();
                    Policy newPolicy = new Policy();
                    newPolicy.setPolicyId(policyId);
                    newPolicy.setPolicyName(policyName);
                    // Set other fields as needed
                    boolean created = policyService.createPolicy(newPolicy);
                    if (created) {
                        System.out.println("Policy created.");
                    } else {
                        System.out.println("Failed to create policy.");
                    }
                    break;

                case 2://Getting a particular policy from the MySQL DB
                    System.out.println("Enter Policy ID:");
                    policyId = scanner.nextInt();
                    try {
                        Policy policy = policyService.getPolicy(policyId);
                        if (policy != null) {
                            System.out.println(policy);
                        } else {
                            throw new PolicyNotFoundException("Policy not found.");
                        }
                    } catch (PolicyNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3://Getting all policies from the MySQL DB
                    Collection<Policy> policies = policyService.getAllPolicies();
                    System.out.println("All Policies:");
                    for (Policy policy : policies) {
                        System.out.println(policy);
                    }
                    break;

                case 4://Updating a policy using PolicyID
                    System.out.println("Enter Policy ID to update:");
                    policyId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    try {
                        Policy policy = policyService.getPolicy(policyId);
                        if (policy == null) {
                            throw new PolicyNotFoundException("Policy not found.");
                        }
                        System.out.println("Enter new Policy Name:");
                        policyName = scanner.nextLine();
                        policy.setPolicyName(policyName);
                        // Update other fields as needed
                        boolean updated = policyService.updatePolicy(policy);
                        if (updated) {
                            System.out.println("Policy updated successfully.");
                        } else {
                            System.out.println("Failed to update policy.");
                        }
                    } catch (PolicyNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5://Deleting a policy using PolicyID
                    System.out.println("Enter Policy ID to delete:");
                    policyId = scanner.nextInt();
                    boolean deleted = policyService.deletePolicy(policyId);
                    if (deleted) {
                        System.out.println("Policy deleted successfully.");
                    } else {
                        System.out.println("Failed to delete policy.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}
