package shapeAgent;

import processing.core.*;

public class ArcShape extends ShapeAgent {
  int shpOriginMode;
  boolean hoverOk;
  boolean enableHoverEvent;
  float[] shapeSize;


  public ArcShape(PApplet papp, float x, float y, float shapeWidth, float shapeHeight, float init, float stop) {
    super(papp);

    this.papp = papp;

    this.shapeObj = this.papp.createShape(PApplet.ARC, 0, 0, shapeWidth, shapeHeight, init, stop);
    this.shapeObj.setStrokeCap(PApplet.SQUARE);

    this.position = new PVector(x, y);

    this.shpOriginMode = PApplet.CENTER;
    // hoverOk = (abs(shapeHeight - shapeWidth) < 0.01) ? true : false;
    this.hoverOk = false;
  }


  public void shapeOptions() {
    // shapeMode(shpOriginMode);
  }


  public void setArcCap(int capType) {
    this.shapeObj.setStrokeCap(capType);
  }


  public void arcMode(int mode) {
    // shpOriginMode = mode;
  }


  public void enableHover(boolean enable) {
    // enableHoverEvent = (hoverOk) ? enable : false;
  }


  public boolean isHovered() {
    // boolean shapeHovered;
    // if (hoverOk) {
    // PVector mouseLoc = new PVector(mouseX,mouseY);
    // shapeHovered = PVector.dist(position,mouseLoc) <= shapeSize[0] / 2 ? true
    // : false;
    // }
    // else {
    // shapeHovered = false;
    // }

    return false;
  }
}
