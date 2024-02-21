import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*public class bruteForce {
    public static void main(String[] args) {
        List<Asset> assets = new ArrayList<>();
        double totalInvestment = 0; // Change to double
        double riskTolerance = 0;

        try {
            File file = new File("Example.txt");
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
                        totalInvestment = Double.parseDouble(words[3]);
                        
                    } else if (words[0].equals("Risk")) {
                        riskTolerance = Double.parseDouble(words[4]);
                        
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bruteForcePortfolioOptimization(assets, totalInvestment, riskTolerance);
    }//end main

    public static void bruteForcePortfolioOptimization(List<Asset> assets, double totalInvestment, double riskTolerance) {
        int n = assets.size();
        double[] bestAllocation = null;
        double maxReturn = 0;
        boolean minRiskExceeded = false;

        // Generate all possible distribution of assets 
        
        List<double[]> allocations = generateAllocations(totalInvestment, n);

        for (double[] allocation : allocations){
            double totalRisk = calculatePortfolioRisk(assets, allocation);
            double totalReturn = calculatePortfolioReturn(assets, allocation);
            
            if (totalRisk <= riskTolerance && totalReturn > maxReturn) {
                maxReturn = totalReturn;
                bestAllocation = allocation;
            }
            
            if (totalRisk > riskTolerance) {
                minRiskExceeded = true;
            }
        }

        if (bestAllocation == null) {
            System.out.println("No valid distribution of assets found within risk tolerance.");
        } else {
            System.out.println("Best Allocation:");
            for (int i = 0; i < n; i++) {
                System.out.println(assets.get(i).id + ": " + bestAllocation[i] + " units");
            }
            System.out.println("Expected Return: " + maxReturn);
            System.out.println("Total calculated Risk: " + calculatePortfolioRisk(assets, bestAllocation));
            if (minRiskExceeded) {
                System.out.println("Risk tolerance was exceeded.");
            }
        }
    }

    public static List<double[]> generateAllocations(double totalInvestment, int numAssets) {
        List<double[]> allocations = new ArrayList<>();
        generateAllocationsHelper(totalInvestment, numAssets, new double[numAssets], allocations, 0);
        return allocations;
    }

    private static void generateAllocationsHelper(double totalInvestment, int numAssets, double[] currentAllocation, List<double[]> allocations, int index) {
        if (index == numAssets) {
            if (sum(currentAllocation) <= totalInvestment) {
                allocations.add(currentAllocation.clone());
            }
            return;
        }
        for (int i = 0; i <= totalInvestment; i++) {
            currentAllocation[index] = i;
            generateAllocationsHelper(totalInvestment - i, numAssets, currentAllocation, allocations, index + 1);
        }
    }

    public static double calculatePortfolioRisk(List<Asset> assets, double[] allocation) {
        double totalRisk = 0;
        for (int i = 0; i < assets.size(); i++)
            totalRisk += allocation[i] * assets.get(i).riskLevel;
        return totalRisk;
    }

    public static double calculatePortfolioReturn(List<Asset> assets, double[] allocation) {
        double totalReturn = 0;
        for (int i = 0; i < assets.size(); i++)
            totalReturn += allocation[i] * assets.get(i).expectedReturn;
        return totalReturn;
    }

    private static double sum(double[] array) {
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum;
    }
}*/
/*
public class bruteForce {
    public static void main(String[] args) {
        List<Asset> assets = new ArrayList<>();
        double totalInvestment = 0; // Change to double
        double riskTolerance = 0;

        try {
            File file = new File("Example.txt");
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
                        totalInvestment = Double.parseDouble(words[3]);
                        
                    } else if (words[0].equals("Risk")) {
                        riskTolerance = Double.parseDouble(words[4]);
                        
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bruteForcePortfolioOptimization(assets, totalInvestment, riskTolerance);
    }//end main
    public static void bruteForcePortfolioOptimization(List<Asset> assets, double totalInvestment, double riskTolerance) {
        int n = assets.size();
        double[] bestAllocation = null;
        double maxReturn = 0;
        double minRisk = Double.MAX_VALUE;

        // Generate all possible distributions of assets
        List<double[]> allocations = generateAllocations(totalInvestment, n);

        // Iterate through all allocations
        for (double[] allocation : allocations) {
            double totalRisk = calculatePortfolioRisk(assets, allocation);
            double totalReturn = calculatePortfolioReturn(assets, allocation);

            // Check if this allocation is within risk tolerance and has maximum return
            if (totalRisk <= riskTolerance && totalReturn > maxReturn) {
                maxReturn = totalReturn;
                bestAllocation = allocation.clone();
                minRisk = totalRisk;
            }
        }

        // Print the best allocation
        if (bestAllocation != null) {
            System.out.println("Optimal Allocation:");
            for (int i = 0; i < n; i++) {
                System.out.println(assets.get(i).id + ": " + bestAllocation[i] + " units");
            }
            System.out.println("Expected Portfolio Return: " + maxReturn);
            System.out.println("Portfolio Risk Level: " + minRisk);
        } else {
            System.out.println("No valid distribution of assets found within risk tolerance.");
        }
    }
    

     public static List<double[]> generateAllocations(double totalInvestment, int numAssets) {
        List<double[]> allocations = new ArrayList<>();
        generateAllocationsHelper(totalInvestment, numAssets, new double[numAssets], allocations, 0);
        return allocations;
    }

    private static void generateAllocationsHelper(double totalInvestment, int numAssets, double[] currentAllocation, List<double[]> allocations, int index) {
        if (index == numAssets) {
            if (sum(currentAllocation) <= totalInvestment) {
                allocations.add(currentAllocation.clone());
            }
            return;
        }
        for (int i = 0; i <= totalInvestment; i++) {
            currentAllocation[index] = i;
            generateAllocationsHelper(totalInvestment - i, numAssets, currentAllocation, allocations, index + 1);
        }
    }
    
    
    
    
    public static double calculatePortfolioRisk(List<Asset> assets, double[] allocation) {
        double totalRisk = 0;
        for (int i = 0; i < assets.size(); i++)
            totalRisk += allocation[i] * assets.get(i).riskLevel;
        return totalRisk;
    }

    public static double calculatePortfolioReturn(List<Asset> assets, double[] allocation) {
        double totalReturn = 0;
        for (int i = 0; i < assets.size(); i++)
            totalReturn += allocation[i] * assets.get(i).expectedReturn;
        return totalReturn;
    }

    private static double sum(double[] array) {
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum;
    }
} /*
/*public class bruteForce {

    public static void main(String[] args) {
        List<Asset> assets = new ArrayList<>();
        double totalInvestment = 0;
        double riskTolerance = 0;

        try {
            File file = new File("Example.txt");
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
                        totalInvestment = Double.parseDouble(words[3]);
                    } else if (words[0].equals("Risk")) {
                        riskTolerance = Double.parseDouble(words[4]);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bruteForcePortfolioOptimization(assets, totalInvestment, riskTolerance);
    }

    /*public static void bruteForcePortfolioOptimization(List<Asset> assets, double totalInvestment, double riskTolerance) {
        List<double[]> allocations = generateAllocations(totalInvestment, assets.size());

        double maxReturn = 0;
        double[] bestAllocation = null;

        for (double[] allocation : allocations) {
            double totalReturn = calculatePortfolioReturn(assets, allocation);
            double totalRisk = calculatePortfolioRisk(assets, allocation);

            if (totalRisk <= riskTolerance && totalReturn > maxReturn) {
                maxReturn = totalReturn;
                bestAllocation = allocation.clone();
            }
        }

        if (bestAllocation != null) {
            System.out.println("Optimal Allocation:");
            for (int i = 0; i < assets.size(); i++) {
                System.out.println(assets.get(i).getId() + ": " + bestAllocation[i] + " units");
            }
            System.out.println("Expected Portfolio Return: " + maxReturn);
            System.out.println("Portfolio Risk Level: " + calculatePortfolioRisk(assets, bestAllocation));
        } else {
            System.out.println("No valid allocation found within risk tolerance.");
        }
    }


/* 
    public static List<double[]> generateAllocations(double totalInvestment, int numAssets) {
        List<double[]> allocations = new ArrayList<>();
        generateAllocationsHelper(totalInvestment, numAssets, new double[numAssets], allocations, 0);
        return allocations;
    }

    private static void generateAllocationsHelper(double totalInvestment, int numAssets, double[] currentAllocation, List<double[]> allocations, int index) {
        if (index == numAssets) {
            if (sum(currentAllocation) <= totalInvestment) {
                allocations.add(currentAllocation.clone());
            }
            return;
        }
        for (int i = 0; i <= totalInvestment; i++) {
            currentAllocation[index] = i;
            generateAllocationsHelper(totalInvestment - i, numAssets, currentAllocation, allocations, index + 1);
        }
    }*/
    
    /*public static void bruteForcePortfolioOptimization(List<Asset> assets, double totalInvestment, double riskTolerance) {
        int n = assets.size();
        double maxReturn = 0;
        double[] bestAllocation = new double[n];
        double[] currentAllocation = new double[n];
    
        // Call generateAllocations with additional parameters required for recursion
        generateAllocations(assets, totalInvestment, n, currentAllocation, 0, riskTolerance, 0, maxReturn, bestAllocation);
    
        if (maxReturn > 0) {
            System.out.println("Optimal Allocation:");
            for (int i = 0; i < n; i++) {
                System.out.println(assets.get(i).getId() + ": " + bestAllocation[i] + " units");
            }
            System.out.println("Expected Portfolio Return: " + maxReturn);
            System.out.println("Portfolio Risk Level: " + calculatePortfolioRisk(assets, bestAllocation));
        } else {
            System.out.println("No valid allocation found within risk tolerance.");
        }
    }
    
        // Generate all possible allocations of assets
       
        public static void generateAllocations(List<Asset> assets, double totalInvestment, int n, double[] currentAllocation, int index,
        double riskTolerance, double currentRisk, double maxReturn, double[] bestAllocation) {
if (index == n) {
double totalReturn = calculatePortfolioReturn(assets, currentAllocation);
if (currentRisk <= riskTolerance && totalReturn > maxReturn) {
maxReturn = totalReturn;
System.arraycopy(currentAllocation, 0, bestAllocation, 0, n);
}
return;
}

for (int quantity = 0; quantity <= totalInvestment / assets.get(index).getExpectedReturn(); quantity++) {
double[] newAllocation = currentAllocation.clone();
newAllocation[index] = quantity;
double newRisk = currentRisk + quantity * assets.get(index).getRiskLevel();
if (newRisk > riskTolerance) {
// If adding more of this asset exceeds risk tolerance, move to the next asset
continue;
}
double newTotalInvestment = totalInvestment - quantity * assets.get(index).getExpectedReturn();
if (newTotalInvestment < 0) {
// If investment is negative, skip this allocation
continue;
}
generateAllocations(assets, newTotalInvestment, n, newAllocation,
index + 1, riskTolerance, newRisk, maxReturn, bestAllocation);
}
}

    



    public static double calculatePortfolioRisk(List<Asset> assets, double[] allocation) {
        double totalRisk = 0;
        for (int i = 0; i < assets.size(); i++) {
            totalRisk += allocation[i] * assets.get(i).getRiskLevel();
        }
        return totalRisk;
    }

    public static double calculatePortfolioReturn(List<Asset> assets, double[] allocation) {
        double totalReturn = 0;
        for (int i = 0; i < assets.size(); i++) {
            totalReturn += allocation[i] * assets.get(i).getExpectedReturn();
        }
        return totalReturn;
    }

    private static double sum(double[] array) {
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        return sum;
    }
}*/

public class bruteForce {
public static void main(String[] args) {
List<Asset> assets = new ArrayList<>();
double totalInvestment = 0; // Change to double
double riskTolerance = 0;

// To checking the length of the args array 
    if (args.length < 1) {
        System.out.println("Please provide the input file path as a command line argument.");
        return;
    }
    String filePath = args[0];

        try {
            File file = new File("Example.txt");
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
            }
          else if (values.length == 1) {
             String[] words = values[0].split(" ");
          if (words[0].equals("Total")) {   
             totalInvestment = Double.parseDouble(words[3]);

           } 

         else if (words[0].equals("Risk")) {
         riskTolerance = Double.parseDouble(words[4]);

        }
      }
    }
    scanner.close();
  } 
catch (FileNotFoundException e) {
  e.printStackTrace();
}

bruteForcePortfolioOptimization(assets, totalInvestment, riskTolerance);
}//end main

public static void bruteForcePortfolioOptimization(List<Asset> assets, double totalInvestment, double riskTolerance) {
   int n = assets.size();
   double[] bestAllocation = null;
   double maxReturn = 0;
   double minRisk = Double.MAX_VALUE;
       
        // Generate all possible distributions of assets
   List<double[]> allocations = generateAllocations(totalInvestment, n);
      
        // Iterate through all allocations
   for (double[] allocation : allocations) {
      double totalRisk = calculatePortfolioRisk(assets, allocation);
      double totalReturn = calculatePortfolioReturn(assets, allocation);
            
            // Check if this allocation is within risk tolerance and has maximum return
   if (totalRisk <= riskTolerance && totalReturn > maxReturn) {
      maxReturn = totalReturn;
      bestAllocation = allocation.clone();
      minRisk = totalRisk;
     }
   }
       
        // Print the best allocation
   if (bestAllocation != null) {
        System.out.println("Optimal Allocation:");
        for (int i = 0; i < n; i++) {
            System.out.println(assets.get(i).id + ": " + bestAllocation[i] + " units");
        }
        System.out.println("Expected Portfolio Return: " + maxReturn);
        System.out.println("Portfolio Risk Level: " + minRisk);
    } 
    
    else {
        System.out.println("No valid distribution of assets found within risk tolerance.");
    }
}
     public static List<double[]> generateAllocations(double totalInvestment, int numAssets) {
        List<double[]> allocations = new ArrayList<>();
        generateAllocationsHelper(totalInvestment, numAssets, new double[numAssets], allocations, 0);
        return allocations;
}
   
    private static void generateAllocationsHelper(double totalInvestment, int numAssets, double[] currentAllocation, List<double[]> allocations, int index) {
       if (index == numAssets) {
          if (sum(currentAllocation) <= totalInvestment) {
             allocations.add(currentAllocation.clone());
   }

return;

 }

for (int i = 0; i <= totalInvestment; i++) {
currentAllocation[index] = i;
generateAllocationsHelper(totalInvestment - i, numAssets, currentAllocation, allocations, index + 1);
    }
   }




public static double calculatePortfolioRisk(List<Asset> assets, double[] allocation) {
double totalRisk = 0;
for (int i = 0; i < assets.size(); i++)
     totalRisk += allocation[i] * assets.get(i).riskLevel;
return totalRisk;
}
    public static double calculatePortfolioReturn(List<Asset> assets, double[] allocation) {
      double totalReturn = 0;
      for (int i = 0; i < assets.size(); i++)
         totalReturn += allocation[i] * assets.get(i).expectedReturn;
      return totalReturn;
}
    private static double sum(double[] array) {
      double sum = 0;
      for (double num : array) {
         sum += num;
    }
   return sum;
   }
 }
