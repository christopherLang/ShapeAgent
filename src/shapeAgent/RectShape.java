package shapeAgent;

import processing.core.*;

/**
 * Class for an rect shape in Processing. Inherits from {@code ShapeAgent} 
 * which defines the necessary methods for color, movement, and transparency 
 * changes with easing and linear interpolation
 */
public class RectShape extends ShapeAgent {
  int shpOriginMode;
  boolean hoverOk;
  boolean enableHoverEvent;
  float[] shapeBounds;
  float[] shapeSize;

  /**
   * <h1> Constructor of {@code RectShape}. Expects a {@code PApplet} for drawing </h1>
   * 
   * @param papp 
   * the processing PApplet to draw on
   * @param x
   * initial {@code x} position of center with respect to {@code papp}
   * @param y
   * initial {@code y} position of center with respect to {@code papp}
   * @param shapeWidth
   * the shape's width, in pixels
   * @param shapeHeight
   * the shape's height, in pixels
   */
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

  /**
   * <h1> Enable hover detection </h1>
   * <p>
   * @param enable 
   * boolean to indicate whether the shape should detect hover
   */
  public void enableHover(boolean enable) {
    this.enableHoverEvent = (this.hoverOk) ? enable : false;
  }

  /**
   * <h1> Recompute shape bounds </h1>
   * <p>
   * {@code RectShape} needs to recompute bounds if the shape's size has changed
   * </p>
   */
  public void computeBounds() {
    this.shapeBounds = new float[4];
    // bottom, left, top, right
    this.shapeBounds[0] = this.position.y + this.shapeSize[1];
    this.shapeBounds[1] = this.position.x;
    this.shapeBounds[2] = this.position.y;
    this.shapeBounds[3] = this.position.x + this.shapeSize[0];
  }

  /**
   * <h1> Detect whether or not shape is hovered </h1>
   * <p>
   * If hovering is enabled, this method will return {@code True} if the shape 
   * is hovered and {@code False} otherwise
   * </p>
   */
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
