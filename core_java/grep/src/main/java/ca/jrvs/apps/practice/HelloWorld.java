package ca.jrvs.apps.practice;


class HelloWorld{
    public static void main(String args[]){
        System.out.println("Hello, World");
        RegexExcImp patternTester = new RegexExcImp();
        System.out.println("Check valid IP");
        System.out.println(patternTester.matchIp("909.909.909.0"));
        System.out.println("Check valid jpg/jpeg");
        System.out.println(patternTester.matchJpeg("ffff.jpg"));
        System.out.println("Check valid empty line");
        System.out.println(patternTester.isEmptyLine("    "));
    }
}