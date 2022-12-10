package ai_project;

public class CoastGuard{
   public static String solve(String problem, String algorithm, boolean visualize)
   {
         if(algorithm.equals("BF"))
         {
              return sProblem.bf_Search(problem);
         }
         else if(algorithm.equals("DF"))
         {
            return sProblem.df_Search(problem);
         }
         else
         {
              return "Invalid algorithm";
         }   
   } 

   public static void main(String[] args)
   {
    String grid0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
    String solution = CoastGuard.solve(grid0, "BF", false);
    System.out.println(solution);
   } 
}