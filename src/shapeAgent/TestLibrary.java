package shapeAgent;

import processing.core.*;
//import processing.core.PApplet;

public class TestLibrary extends PApplet {
  float diameter = 100.0f;
  RectShape a_rect;
  RectShape[] n_rects = new RectShape[10000];
  PVector position;
  float marginPercent;

  public static void main(String args[]) {
    PApplet.main(new String[] { "shapeAgent.TestLibrary" });
  }

  public void settings() {
    // size(500, 500, P2D);
    fullScreen(FX2D);

  }

  public void setup() {
    rectMode(CENTER);

//    for (int i = 0; i < n_rects.length; i++) {
//      n_rects[i] = new RectShape(this, 0, 0, random(10, 120), random(10, 120));
//      n_rects[i].enableHover(true);
//    }
    
    for (int i = 0; i < n_rects.length; i++) {
      n_rects[i] = new RectShape(this, 0, 0, 2, 2);
      n_rects[i].enableHover(true);
      n_rects[i].setStrokeColor(false);
    }

    marginPercent = 20 / 100;
  }

  public void draw() {
    background(0);
    
    for (int i = 0 ; i < n_rects.length ; i++) {
      if (mousePressed) {
        n_rects[i].finishedTranslating = true;
        n_rects[i].translate(mouseX,mouseY,100,5);
      }
      else if (n_rects[i].finishedTranslating) {
          position = new PVector(random(marginPercent * width,(1 - marginPercent) * width),
              random(marginPercent * height,(1 - marginPercent) * height));
          n_rects[i].translate(position, (int)random(60,250), 4);
        }
      
      if (n_rects[i].finishedRotating) {
        float rotateDeg = random(-35,35);
        
        n_rects[i].rotate(rotateDeg, 20, 4);
      }
      
      if (n_rects[i].finishedFill) {
        int fillColor = color(random(255),random(255),random(255));
        
        n_rects[i].setFillColor(fillColor, 120, 4);
      }
      
      
      n_rects[i].draw();
    }
    
    // Frame rate display
    textSize(32);
    fill(255);
    text(frameRate, 5, 30);
  }
}
