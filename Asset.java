
class Asset {
    String id;
    double expectedReturn;
    double riskLevel;

    public Asset(String id, double expectedReturn, double riskLevel, int quantity) {
        this.id = id;
        this.expectedReturn = expectedReturn;
        this.riskLevel = riskLevel;
    }
}