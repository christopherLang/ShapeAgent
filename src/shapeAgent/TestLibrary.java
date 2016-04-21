package shapeAgent;

import processing.core.*;
//import processing.core.PApplet;

public class TestLibrary extends PApplet {
  float diameter = 100.0f;
  RectShape a_rect;


  public static void main(String args[]) {
    PApplet.main(new String[] { "shapeAgent.TestLibrary" });
  }


  public void settings() {
    size(900, 800,P2D);
    //fullScreen(P2D);
  }


  public void setup() {
    rectMode(CENTER);

    a_rect = new RectShape(this,0,0,300,250);
    a_rect.enableHover(true);
  }


  public void draw() {
    background(0);
    
    a_rect.translate2(new PVector(mouseX,mouseY), 1, 1);
    a_rect.draw();
    textSize(32);
    fill(255);
    text(frameRate, 5, 30);
  }


  public void mousePressed() {
    if (mouseButton == LEFT) {
      a_rect.translate2(new PVector(mouseX,mouseY), 1, 1);
    }
  }
}
