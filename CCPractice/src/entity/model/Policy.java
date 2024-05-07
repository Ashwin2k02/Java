package entity.model;

public class Policy {
	private int policyId;
    private String policyName;

    public Policy() {}

    public Policy(int policyId, String policyName) {
        this.policyId = policyId;
        this.policyName = policyName;
    }


    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }


    @Override
    public String toString() {
        return "Policy [policyId=" + policyId + ", policyName=" + policyName + "]";
    }

}
