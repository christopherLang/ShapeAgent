package template.library;

import processing.core.*;

public class EllipseShape extends ShapeAgent {
  PApplet papp;
  int shpOriginMode;
  boolean hoverOk;
  boolean enableHoverEvent;
  float[] shapeSize;

  public EllipseShape(PApplet processingApp, float x, float y, float shapeWidth, float shapeHeight) {
    super(processingApp);

    this.papp = processingApp;

    this.shapeObj = this.papp.createShape(PApplet.ELLIPSE, 0, 0, shapeWidth, shapeHeight);
    this.position = new PVector(x, y);
    this.shapeSize = new float[2];
    this.shapeSize[0] = shapeWidth;
    this.shapeSize[1] = shapeHeight;

    this.shpOriginMode = PApplet.CENTER;
    this.hoverOk = (PApplet.abs(shapeHeight - shapeWidth) < 0.01) ? true : false;
  }

  public void shapeOptions() {
    // shapeMode(CENTER);
  }

  public void enableHover(boolean enable) {
    this.enableHoverEvent = (this.hoverOk) ? enable : false;
  }

  public boolean isHovered() {
    boolean shapeHovered;
    if (this.hoverOk) {
      PVector mouseLoc = new PVector(this.papp.mouseX, this.papp.mouseY);
      shapeHovered = PVector.dist(this.position, mouseLoc) <= shapeSize[0] / 2 ? true : false;
    } else {
      shapeHovered = false;
    }

    return shapeHovered;
  }
}
