import java.util.Locale;
import java.util.Scanner;

//--------------------------------------------------//
// MATH226 - Numerical Methods for EE
// Fall 2021 – 2022

//---------------------------------------------------//
class Math226 {
    public static void main(String[] args) {
    	menu();
    }


    public static void menu(){

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Enter Your Function :");
        String expression = myObj.nextLine();  // Read user input
        System.out.println("Enter a :");
        double a = myObj.nextDouble();  // Read user input
        System.out.println("Enter b :");
        double b = myObj.nextDouble();  // Read user input
        System.out.println("Enter Tolerance :");
        double tolerance = myObj.nextDouble();  // Read user input

        if(expression.contains("=")){
            String parts[] = expression.split("=");
            expression = parts[0];
        }
        chooseMethod(expression,a,b,tolerance);

    }
    public static void chooseMethod(String expression,double a,double b, double tolerance){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println();
        System.out.println("1. Solve with Bisection Method");
        System.out.println("2. Solve with Secant Method");
        System.out.println("3. Solve with Golden Section Search Method");
        System.out.println("4. Return to the main menu");
        int result = myObj.nextInt();  // Read user input

        if(result==1){
            bisection(expression,a,b,tolerance);
            chooseMethod(expression,a,b,tolerance);
        }else if(result==2){
            secantmethod(expression,a,b,tolerance);
            chooseMethod(expression,a,b,tolerance);
        }else if(result==3) {
            GoldenSectionSearch(expression,a,b,tolerance);
            chooseMethod(expression,a,b,tolerance);
        }else if(result==4) {
           menu();
        }

    }
    public static void GoldenSectionSearch(String expression,double a,double b, double tolerance){
        double T = 0.618;
        double x1 = a +((1-T)*(b-a));
        double f1 = CalculateFunction(expression,x1);

        double x2 = a +((T)*(b-a));

        double f2 = CalculateFunction(expression,x2);
        System.out.println("-------------------------------GOLDEN SECTION METHOD-------------------------------");
        System.out.println("f(x)="+ expression);
        System.out.println();

        System.out.printf(Locale.ROOT,"%10.6s","x1");
        System.out.printf(Locale.ROOT,"%10.6s","f(x1)");
        System.out.printf(Locale.ROOT,"%10.6s","x2");
        System.out.printf(Locale.ROOT,"%10.6s","f(x2)");
        System.out.println();

        System.out.printf(Locale.ROOT,"%10.6f",x1);
        System.out.printf(Locale.ROOT,"%10.6f",f1);
        System.out.printf(Locale.ROOT,"%10.6f",x2);
        System.out.printf(Locale.ROOT,"%10.6f",f2);
        System.out.println();
        while((b-a)>tolerance){
            if(f1>f2){
                a=x1;
                x1=x2;
                f1=f2;
                x2 = a+(T*(b-a));
                f2 = CalculateFunction(expression,x2);
            }else{
                b=x2;
                x2=x1;
                f2=f1;
                x1=a+((1-T)*(b-a));
                f1 = CalculateFunction(expression,x1);
            }
            System.out.printf(Locale.ROOT,"%10.6f",x1);
            System.out.printf(Locale.ROOT,"%10.6f",f1);
            System.out.printf(Locale.ROOT,"%10.6f",x2);
            System.out.printf(Locale.ROOT,"%10.6f",f2);
            System.out.println();
        }


    }


    public static void secantmethod(String expression,double a,double b, double tolerance){
        double fa=CalculateFunction(expression,a);
        double fb=CalculateFunction(expression,b);

        System.out.println("-------------------------------SECANT METHOD-------------------------------");
        System.out.println("f(x)="+ expression);
        System.out.println();
        System.out.printf(Locale.ROOT,"%10.6s","x");
        System.out.printf(Locale.ROOT,"%10.6s","f(x)");
        System.out.printf(Locale.ROOT,"%10.6s","h");
        System.out.println();

        System.out.printf(Locale.ROOT,"%10.6f",a);
        System.out.printf(Locale.ROOT,"%10.6f",fa);
        System.out.println();
        System.out.printf(Locale.ROOT,"%10.6f",b);
        System.out.printf(Locale.ROOT,"%10.6f",fb);
        System.out.println();
        while(Math.abs(b-a)>tolerance){
            double h;
            double newx = b - fb*((b-a)/(fb-fa));
            a= b;
            b = newx;
            h=b-a;
            fa=CalculateFunction(expression,a);
            fb=CalculateFunction(expression,b);

            System.out.printf(Locale.ROOT,"%10.6f",b);
            System.out.printf(Locale.ROOT,"%10.6f",fb);
            System.out.printf(Locale.ROOT,"%10.6f",h);
            System.out.println();
            }

        }

    public static void bisection(String expression,double a,double b, double tolerance){

        System.out.println("-------------------------------BISECTION METHOD-------------------------------");
        System.out.println("f(x)="+ expression);
        System.out.println();
        System.out.printf(Locale.ROOT,"%10.6s","a");
        System.out.printf(Locale.ROOT,"%10.6s","f(a)");
        System.out.printf(Locale.ROOT,"%10.6s","b");
        System.out.printf(Locale.ROOT,"%10.6s","f(b)");
        System.out.println();
        /**
         * If f(a) and f(b) for initial points has same sign 
         * exit the method
         */
       if ((CalculateFunction(expression,a)*CalculateFunction(expression,b)) >=0) {
    	   System.out.println("Your a and b is not right for bisection method.");
    	   return;
       }
       
        double m = a;
        while ((b-a)>tolerance){
            m = (a+b)/2;
            double fa=CalculateFunction(expression,a);
            double fb= CalculateFunction(expression,b);
            double fm= CalculateFunction(expression,m);
             if (Math.signum(fa) == Math.signum(fm)){
                 a=m;
             }else{
                 b=m;
             }
            System.out.printf(Locale.ROOT,"%10.6f",a);
            System.out.printf(Locale.ROOT,"%10.6f",fa);
            System.out.printf(Locale.ROOT,"%10.6f",b);
            System.out.printf(Locale.ROOT,"%10.6f",fb);
            System.out.println();
        }

    System.out.println(m);
    }
/**
 * this method tests JEP library and its functions
 */
    public static void testLibrary(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Your Function :");
        String expression = myObj.nextLine();  // Read user input
        System.out.println("Enter Your x :");
        double x = myObj.nextDouble();  // Read user input

        System.out.println(CalculateFunction(expression,x));
    }
    public static double CalculateFunction(String Expression,double x){
        org.nfunk.jep.JEP myParser = new org.nfunk.jep.JEP();
        myParser.addStandardFunctions();
        myParser.addStandardConstants();
        myParser.addVariable("x", x);
        myParser.parseExpression(Expression);
        double fa = myParser.getValue();
        return fa;

    }
}
