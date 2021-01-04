package com.niux.spring;

public class Main {
  public static void main(String[] args) {
    // Default class loader;
    Tester ta1 = new Tester();
    ta1.setText("test");
    System.out.println(ta1.getText());
 
    Tester ta2 = new Tester();
    System.out.println(ta2.getText());
 
    // Custom class loader;
    CustomClassLoader ccl  = new CustomClassLoader();
 
    try {
      Tester tb = (Tester) ccl.loadClass("Tester").newInstance();
      System.out.println(tb.getText());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}