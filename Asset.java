
class Asset {
    String id;
    double expectedReturn;
    double riskLevel;
    int quantity;

    public Asset(String id, double expectedReturn, double riskLevel, int quantity) {
        this.id = id;
        this.expectedReturn = expectedReturn;
        this.riskLevel = riskLevel;
        this.quantity=quantity;
    }
    public String getId(){
        return id;
    }
    public double getExpectedReturn(){
        return expectedReturn;
    }
    public double getRiskLevel(){
        return riskLevel;
    }
    public int getquantity(){
        return quantity;
    }
}