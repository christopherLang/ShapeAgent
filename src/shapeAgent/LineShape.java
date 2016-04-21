package shapeAgent;

import processing.core.*;

public class LineShape extends ShapeAgent {
  int shpOriginMode;


  public LineShape(PApplet papp, float x1, float y1, float x2, float y2) {
    super(papp);
    this.papp = papp;
    
    this.shapeObj = this.papp.createShape(PApplet.LINE, x1, y1, x2, y2);

    PVector endpoint1 = new PVector(x1, y1);
    PVector endpoint2 = new PVector(x2, y2);
    this.position = PVector.sub(endpoint1, endpoint2);
    this.position.x = PApplet.abs(this.position.x) / 2;
    this.position.y = PApplet.abs(this.position.y) / 2;
  }


  public void shapeOptions() {
    // shapeMode(CENTER);
  }


  public void setFillColor() throws Exception {
    throw new Exception("LineShape does not support fill");
  }


  public void setFillAlpha() throws Exception {
    throw new Exception("LineShape does not support fill alpha");
  }
}
