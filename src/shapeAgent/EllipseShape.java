package shapeAgent;

import processing.core.*;

/**
 * Class for an Ellipse shape in Processing. Inherits from {@code ShapeAgent} 
 * which defines the necessary methods for color, movement, and transparency 
 * changes with easing and linear interpolation
 */
public class EllipseShape extends ShapeAgent {
  int shpOriginMode;
  boolean hoverOk;
  boolean enableHoverEvent;
  float[] shapeSize;

  /**
   * <h1> Constructor of {@code EllipseShape}. Expects a {@code PApplet} for drawing </h1>
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
  public EllipseShape(PApplet papp, float x, float y, float shapeWidth, float shapeHeight) {
    super(papp);

    this.papp = papp;

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

  /**
   * <h1> Enable hover detection </h1>
   * <p>
   * Whether or not this can be enable depends on {@code shapeWidth} and 
   * {@code shapeHeight}. They need to be within 0.01 difference
   * </p>
   * @param enable 
   * boolean to indicate whether the shape should detect hover
   */
  public void enableHover(boolean enable) {
    this.enableHoverEvent = (this.hoverOk) ? enable : false;
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
    if (this.hoverOk) {
      PVector mouseLoc = new PVector(this.papp.mouseX, this.papp.mouseY);
      shapeHovered = PVector.dist(this.position, mouseLoc) <= shapeSize[0] / 2 ? true : false;
    } else {
      shapeHovered = false;
    }

    return shapeHovered;
  }
}
