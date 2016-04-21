package shapeAgent;

import processing.core.*;

public class RectShape extends ShapeAgent {
  int shpOriginMode;
  boolean hoverOk;
  boolean enableHoverEvent;
  float[] shapeBounds;
  float[] shapeSize;


  public RectShape(PApplet papp,float x, float y, float shapeWidth, float shapeHeight) {
    super(papp);
    
    this.papp = papp;
    
    this.shapeObj = this.papp.createShape(PApplet.RECT, 0, 0, shapeWidth, shapeHeight);
    this.position = new PVector(x, y);
    this.shapeObj.translate(x, y);
    this.shapeSize = new float[2];
    this.shapeSize[0] = shapeWidth;
    this.shapeSize[1] = shapeHeight;
    this.computeBounds();

    this.shpOriginMode = PApplet.CENTER;
    this.hoverOk = true;
    this.enableHoverEvent = false;
  }


  public void shapeOptions() {
    // shapeMode(CENTER);
  }


  public void enableHover(boolean enable) {
    this.enableHoverEvent = (this.hoverOk) ? enable : false;
  }


  public void computeBounds() {
    this.shapeBounds = new float[4];
    // bottom, left, top, right
    this.shapeBounds[0] = this.position.y + this.shapeSize[1];
    this.shapeBounds[1] = this.position.x;
    this.shapeBounds[2] = this.position.y;
    this.shapeBounds[3] = this.position.x + this.shapeSize[0];
  }


  public boolean isHovered() {
    boolean shapeHovered;

    if (this.enableHoverEvent) {
      this.computeBounds();
      
      float mouseX_pos = this.papp.mouseX;
      float mouseY_pos = this.papp.mouseY;
      if ((this.shapeBounds[1]) < mouseX_pos & (mouseX_pos < this.shapeBounds[3])) {
        if ((this.shapeBounds[2]) < mouseY_pos & (mouseY_pos < this.shapeBounds[0])) {
          shapeHovered = true;
        } else {
          shapeHovered = false;
        }
      } else {
        shapeHovered = false;
      }
    } else {
      shapeHovered = false;
    }

    return shapeHovered;
  }


}
