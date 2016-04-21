package template.library;

import processing.core.*;
//import processing.core.PApplet;

public class TestLibrary extends PApplet {
  float diameter = 100.0f;
  EllipseShape a_node;
  
  public static void main(String args[]) {
    PApplet.main(new String[] { "template.library.TestLibrary" });
  }

  public void settings() {
    size(640, 480,OPENGL);
    
    
  }

  public void setup() {
    a_node = new EllipseShape(this,width/2,height/2,100,100);
    System.out.println(width);
    System.out.println(height);
  }

  public void draw() {
    background(0);
    if (a_node.finishedFill) {
      a_node.setFillColor(color(random(255),random(255),random(255)), 100);
    }
//    if (a_node.finishedTranslating) {
//      a_node.translate(random(width), random(height), 100, 5);
//    }
    a_node.draw();
  }
  
  public void mousePressed() {
//    System.out.println("hello");
    if (mouseButton == PApplet.LEFT) {
      a_node.translate(new PVector(mouseX,mouseY),60,5);
    }
  }
}
