import java.io.*;
import java.util.*;

public class InvestmentFirm {
    private static class Asset {
        String id;
        double expectedReturn;
        double individualrisk;
        double units;

        public Asset(String id, double expectedReturn, double risk, double units) {
            this.id = id;
            this.expectedReturn = expectedReturn;
            this.individualrisk = risk;
            this.units = units;
        }
    }
    // Portfolio class representing each client
    private static class Portfolio {
        double[] allocation; // Percentage how much of each asset in total investment
        double expectedReturn;
        double risk;

    public Portfolio(int asset1, int asset2, int asset3) {
        allocation = new double[3];
        allocation[0] = asset1 / 100.0;
        allocation[1] = asset2 / 100.0;
        allocation[2] = asset3 / 100.0;
    }

    public void calculatePortfolioEfficiency(List<Asset> assets) {
        expectedReturn = 0.0;
        risk = 0.0;
        for (int i = 0; i < assets.size(); i++) {
            expectedReturn += allocation[i] * assets.get(i).expectedReturn; //return of each asset in portfolio allocation
            risk += allocation[i] * assets.get(i).individualrisk; //risk of each asset in portfolio allocation
        }
    }
}
    public static void main(String[] args) throws IOException {
     
    /*  List<Asset> assets = new ArrayList<>();
        int totalInvestment = 0; 
        double riskTolerance = 0;

        try {
            File file = new File("Example1.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(" : ");
                if (values.length == 4) {
                    String id = values[0];
                    double expectedReturn = Double.parseDouble(values[1]);
                    double riskLevel = Double.parseDouble(values[2]);
                    int quantity = Integer.parseInt(values[3]);
                    assets.add(new Asset(id, expectedReturn, riskLevel, quantity));
                } else if (values.length == 1) {
                    String[] words = values[0].split(" ");
                    if (words[0].equals("Total")) {   
                        totalInvestment = Integer.parseInt(words[3]);
                        
                    } else if (words[0].equals("Risk")) {
                        riskTolerance = Double.parseDouble(words[4]);
                        
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        Portfolio optimalPortfolio = findOptimalAllocationBF(assets, totalInvestment, riskTolerance);

        // solution
        System.out.println("Optimal Allocation:");
        for (int i = 0; i < assets.size(); i++) {
            System.out.printf("%s: %.0f units (%.0f%% of investment)\n",
                    assets.get(i).id, optimalPortfolio.allocation[i] * totalInvestment,
                    optimalPortfolio.allocation[i] * 100);
        }
        System.out.printf("Expected Portfolio Return: %.4f\n", optimalPortfolio.expectedReturn);
        System.out.printf("Portfolio Risk Level: %.4f\n", optimalPortfolio.risk);*/
        //DYNAMIC
        double totalInvestment=0;
        double riskTolerance=0;
          
            // List to store the parsed Asset objects
            List<Asset> assets = new ArrayList<>();
          try{
            File file = new File("Example1.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(" : ");
                if (values.length == 4) {
                    String id = values[0];
                    double expectedReturn = Double.parseDouble(values[1]);
                    double riskLevel = Double.parseDouble(values[2]);
                    int quantity = Integer.parseInt(values[3]);
                    assets.add(new Asset(id, expectedReturn, riskLevel, quantity));
                } else if (values.length == 1) {
                    String[] words = values[0].split(" ");
                    if (words[0].equals("Total")) {   
                        totalInvestment = Integer.parseInt(words[3]);
                        
                    } else if (words[0].equals("Risk")) {
                        riskTolerance = Double.parseDouble(words[4]);
                    }
                }
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
            Portfolio optimalPortfolio = findOptimalAllocationDP(assets, totalInvestment, riskTolerance);
          
            System.out.println("Optimal Portfolio:");
            for (int i = 0; i < assets.size(); i++) {
                System.out.printf("%s: %.0f units (%.0f%% of investment)\n",
                        assets.get(i).id, optimalPortfolio.allocation[i] * totalInvestment,
                        optimalPortfolio.allocation[i] * 100);
            }
            System.out.printf("Expected Portfolio Return: %.4f\n", optimalPortfolio.expectedReturn);
            System.out.printf("Portfolio Risk Level: %.4f\n", optimalPortfolio.risk);
            //System.out.println(optimalPortfolio);*/
    }//main
    
    /*// Find optimal allocation with brute force
    private static Portfolio findOptimalAllocationBF(List<Asset> assets, double totalInvestment, double riskTolerance) {
        Portfolio optimalPortfolio = null;
        double maxReturn = 0;
        Portfolio portfolio = new Portfolio(0, 0, 0);

        // Loop through all possible allocations
        for (int i = 0; i <= 100; i++) {         //0%-->100%
            for (int j = 0; j <= 100 - i; j++) { // 0%-->100-i%
                int remaining = 100 - i - j;     //remaining%
                
                portfolio = new Portfolio(i, j, remaining);

                // Check if units assigned to each asset don't exceed available units
                if (((i / 100.0) * totalInvestment) <= assets.get(0).units && ((j / 100.0) * totalInvestment) <= assets.get(1).units && ((remaining / 100.0) * totalInvestment) <= assets.get(2).units) {
                    portfolio = new Portfolio(i, j, remaining);
                    portfolio.calculatePortfolioEfficiency(assets); // Calculate portfolio return and risk
                
                    // Check if risk is within tolerance and return is higher than best so far and update when a new optimal allocation is found
                    if (portfolio.risk <= riskTolerance && portfolio.expectedReturn > maxReturn) {
                        maxReturn = portfolio.expectedReturn;
                        optimalPortfolio = portfolio;
                    }
                }
            }
        }
        return optimalPortfolio;
    }*/
   
private static Portfolio findOptimalAllocationDP(List<Asset> assets, double totalInvestment, double riskTolerance) {
    Portfolio optimalPortfolio = null;
    double maxReturn = 0;
    Portfolio[][][] dp = new Portfolio[101][101][101]; //momoization table(ranges from 0% to 100%)

    // Loop through all possible allocations
    for (int i = 0; i <= 100; i++) { // 0%-->100%
        for (int j = 0; j <= 100 - i; j++) { // 0%-->100-i%
            int remaining = 100 - i - j; // remaining%

            // Check if units assigned to each asset don't exceed available units
            if (((i / 100.0) * totalInvestment) <= assets.get(0).units &&
                    ((j / 100.0) * totalInvestment) <= assets.get(1).units &&
                    ((remaining / 100.0) * totalInvestment) <= assets.get(2).units) {

                Portfolio portfolio = new Portfolio(i, j, remaining);//new portfolio with the current allocation
                
                //reuse past calculations if available 
                if (i > 0&&(dp[i-1][j][remaining])!=null) {
                    portfolio.expectedReturn += dp[i - 1][j][remaining].expectedReturn;
                    portfolio.risk += dp[i - 1][j][remaining].risk;
                }
                if (j > 0&&(dp[i][j-1][remaining])!=null) {
                    portfolio.expectedReturn += dp[i][j - 1][remaining].expectedReturn;
                    portfolio.risk += dp[i][j - 1][remaining].risk;
                }
                if (remaining > 0&&(dp[i][j][remaining-1])!=null) {
                    portfolio.expectedReturn += dp[i][j][remaining - 1].expectedReturn;
                    portfolio.risk += dp[i][j][remaining - 1].risk;
                }

                portfolio.calculatePortfolioEfficiency(assets); //calculate portfolio return and risk
                // Check if risk is within tolerance and return is higher than best so far & update when a new optimal allocation is found
                if (portfolio.risk <= riskTolerance && portfolio.expectedReturn > maxReturn) {
                    maxReturn = portfolio.expectedReturn;
                    optimalPortfolio = portfolio;
                }
                dp[i][j][remaining] = portfolio; // Store the optimal portfolio for current asset percentages
            }
        }
    }
    return optimalPortfolio;
}  
}