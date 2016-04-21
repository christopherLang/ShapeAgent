package TestLibrary;

import processing.core.PApplet;

public class TestLibrary extends PApplet {
  float diameter = 100.0f;

  public static void main(String args[]) {
    PApplet.main(new String[] { "TestLibrary" });
  }

  public void settings() {
    size(640, 480);
  }

  public void setup() {

  }

  public void draw() {
    ellipse(width * 0.5f, height * 0.5f, diameter, diameter);
  }
}
