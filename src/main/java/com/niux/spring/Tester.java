package com.niux.spring;

public class Tester {
 
  public Tester() {
    System.out.println(getClass().getClassLoader());
  }
 
  public void setText(String text) {
    GlobalObject.globalText = text;
  }
 
  public String getText() {
    return GlobalObject.globalText;
  }
}