package shapeAgent;

import processing.core.*;
//import processing.core.PApplet;

public class TestLibrary extends PApplet {
  float diameter = 100.0f;
  RectShape a_rect;
  PVector position;
  float marginPercent;


  public static void main(String args[]) {
    PApplet.main(new String[] { "shapeAgent.TestLibrary" });
  }


  public void settings() {
    size(500, 500, P2D);
    // fullScreen(P2D);
  }


  public void setup() {
    rectMode(CENTER);

    a_rect = new RectShape(this,0,0,80,80);
    a_rect.enableHover(true);
    
    marginPercent = 20 / 100;
  }


  public void draw() {
    background(0);
    
    if (a_rect.finishedTranslating) {
      position = new PVector(random(marginPercent * width,(1 - marginPercent) * width),
          random(marginPercent * height,(1 - marginPercent) * height));
      
      a_rect.translate(position, 20, 4);
    }
    
    if (a_rect.finishedRotating) {
      float rotateDeg = random(-35,35);
      
      a_rect.rotate(rotateDeg, 25, 4);
    }
    
    if (a_rect.finishedFill) {
      int fillColor = color(random(255),random(255),random(255));
      
      a_rect.setFillColor(fillColor, 15, 5);
    }

    a_rect.draw();

    // Frame rate display
    textSize(32);
    fill(255);
    text(frameRate, 5, 30);
  }


  public void mousePressed() {
    if (mouseButton == LEFT) {
      a_rect.perpetualRotate(2, true);
    }
    if (mouseButton == RIGHT) {
      a_rect.disablePerpetualRotate();
    }
  }
}
