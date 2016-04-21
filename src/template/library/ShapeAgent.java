package template.library;

import processing.core.*;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package
 * 'template' to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder
 * 'examples' will automatically include the example in the javadoc.)
 *
 * @example Hello
 */

public class ShapeAgent {
  PApplet papp; // reference to parent sketch
  PShape shapeObj;
  PVector position;
  PVector newPosition;
  int shpFill;
  float shpFillAlpha = 255;
  int shpStroke;
  float shpStrokeAlpha = 255;
  float shpStrokeWeight = 1;
  float shpRotation = 0;
  float shpScale = 1;
  boolean enableStroke = true;
  boolean enableFill = true;

  boolean perpetualRotate = false;
  float perpetualRotateIncrement;

  boolean finishedRotating = true;
  boolean finishedTranslating = true;
  boolean finishedScaling = true;
  boolean finishedFill = true;
  boolean finishedFillAlpha = true;
  boolean finishedStroke = true;
  boolean finishedStrokeAlpha = true;
  boolean finishedStrokeThickness = true;

  PVector translateIncrement;

//  PFont textFont;
//  String textLabel = "";
//  boolean enableText = false;
//  int textJustify = PApplet.CENTER;
//  int textFill;

  ColorIterator stroke_iter;
  ColorIterator fill_iter;
  FloatIterator strokeAlpha_iter;
  FloatIterator fillAlpha_iter;
  FloatIterator scale_iter;
  FloatIterator strokeWeight_iter;
  PVectorIterator velocity_iter;
  FloatIterator rotate_iter;

  Sequencer sequencer;
  Helpers helper;
  // public final static String VERSION = "##library.prettyVersion##";

  /**
   * a Constructor, usually called in the setup() method in your sketch to
   * initialize and start the Library.
   * 
   * @example Hello
   * @param theParent
   */
  public ShapeAgent(PApplet processingApp) {
    this.papp = processingApp;
    
    this.shpFill = this.papp.color(255, 255, 255);
    this.shpStroke = this.papp.color(255, 255, 255);
//    this.textFont = this.papp.createFont("Arial", 14, true);
//    this.textFill = this.papp.color(0, 0, 0);
    
    this.stroke_iter = new ColorIterator(this.shpStroke);
    this.fill_iter = new ColorIterator(this.shpFill);
    this.strokeAlpha_iter = new FloatIterator(shpStrokeAlpha);
    this.fillAlpha_iter = new FloatIterator(this.shpFillAlpha);
    this.scale_iter = new FloatIterator(1);
    this.strokeWeight_iter = new FloatIterator(this.shpStrokeWeight);
    this.velocity_iter = new PVectorIterator(0, 0);
    this.rotate_iter = new FloatIterator(0);
    
    this.sequencer = new Sequencer(this.papp);
    
    this.helper = new Helpers(this.papp);
  }

  public void setStrokeColor(int strokeColor, int steps, int type) {
    int[] colorGradient = this.sequencer.color_sequencer(shpStroke, strokeColor, steps, type);
    this.stroke_iter = new ColorIterator(colorGradient);

    this.finishedStroke = false;
  }

  public void setStrokeColor(int strokeColor, int steps) {
    this.setStrokeColor(strokeColor, steps, 1);
  }

  public void setStrokeColor(int strokeColor) {
    this.setStrokeColor(strokeColor, 1, 1);
  }

  public void setStrokeAlpha(float strokeOpacity, int steps, int type) {
    float[] opacityGradient = this.sequencer.value_sequencer(shpStrokeAlpha, strokeOpacity, steps, type);
    this.strokeAlpha_iter = new FloatIterator(opacityGradient);
    this.finishedStrokeAlpha = false;
  }

  public void setStrokeAlpha(float strokeOpacity, int steps) {
    this.setStrokeAlpha(strokeOpacity, steps, 1);
  }

  public void setStrokeAlpha(float strokeOpacity) {
    this.setStrokeAlpha(strokeOpacity, 1, 1);
  }

  public void setStrokeThickness(float weight, int steps, int type) {
    float[] thicknessGradient = this.sequencer.value_sequencer(shpStrokeWeight, weight, steps, type);
    this.strokeWeight_iter = new FloatIterator(thicknessGradient);

    this.finishedStrokeThickness = false;
  }

  public void setStrokeThickness(float weight, int steps) {
    this.setStrokeThickness(weight, steps, 1);
  }

  public void setStrokeThickness(float weight) {
    this.setStrokeThickness(weight, 1, 1);
  }

  public void setFillColor(int fillColor, int steps, int type) {
    int[] colorGradient = this.sequencer.color_sequencer(shpFill, fillColor, steps, type);
    this.fill_iter = new ColorIterator(colorGradient);

    this.finishedFill = false;
  }

  public void setFillColor(int fillColor, int steps) {
    this.setFillColor(fillColor, steps, 1);
  }

  public void setFillColor(int fillColor) {
    this.setFillColor(fillColor, 1, 1);
  }

  public void setFillAlpha(float fillOpacity, int steps, int type) {
    float[] opacityGradient = this.sequencer.value_sequencer(shpFillAlpha, fillOpacity, steps, type);
    this.fillAlpha_iter = new FloatIterator(opacityGradient);

    this.finishedFillAlpha = false;
  }

  public void setFillAlpha(float fillOpacity, int steps) {
    this.setFillAlpha(fillOpacity, steps, 1);
  }

  public void setFillAlpha(float fillOpacity) {
    this.setFillAlpha(fillOpacity, 1, 1);
  }

//  public void setLabel(String text) {
//    this.textLabel = text;
//  }
//
//  public void setLabelFont(PFont newFont) {
//    this.textFont = newFont;
//  }
//
//  public void setLabelJustify(int justify) {
//    this.textJustify = justify;
//  }
//
//  public void enableLabel(boolean turnOnLabel) {
//    this.enableText = turnOnLabel;
//  }
//
//  public void setLabelColor(int labelColor) {
//    this.textFill = labelColor;
//  }

  public void rotate(float degrees, int steps, int type) {
    float initAngle = this.shpRotation;
    float endAngle = initAngle + PApplet.radians(degrees);

    float[] rotateIncrements;
    rotateIncrements = this.sequencer.value_incrementer(initAngle, endAngle, steps, type);

    this.rotate_iter = new FloatIterator(rotateIncrements);

    this.finishedRotating = false;
  }

  public void rotate(float degrees, int steps) {
    this.rotate(degrees, steps, 1);
  }

  public void rotate(float degrees) {
    this.rotate(degrees, 1, 1);
  }

  public void translate(PVector newLoc, int steps, int type) {
    steps = (steps < 1) ? 1 : steps;
    this.newPosition = newLoc;

    float[] x_increments, y_increments;

    x_increments = this.sequencer.value_incrementer(this.position.x, this.newPosition.x, steps, type);
    y_increments = this.sequencer.value_incrementer(this.position.y, this.newPosition.y, steps, type);
    

    PVector[] velocityIncrements = new PVector[x_increments.length];
    for (int i = 0; i < x_increments.length; i++) {
      velocityIncrements[i] = new PVector(x_increments[i], y_increments[i]);
    }

    this.velocity_iter = new PVectorIterator(velocityIncrements);

    this.finishedTranslating = false;
  }

  public void translate(PVector newLoc, int steps) {
    this.translate(newLoc, steps, 1);
  }

  public void translate(PVector newLoc) {
    this.translate(newLoc, 1, 1);
  }

  public void translate(float x, float y, int steps, int type) {
    steps = (steps < 1) ? 1 : steps;

    PVector newLoc = new PVector(x, y);
    this.translate(newLoc, steps, type);
  }

  public void translate(float x, float y, int steps) {
    this.translate(x, y, steps, 1);
  }

  public void translate(float x, float y) {
    this.translate(x, y, 1, 1);
  }

  public void scale(float scaleFactor, int steps) {
    steps = (steps < 1) ? 1 : steps;

    float[] sequenceScale = this.sequencer.value_sequencer(this.shpScale, scaleFactor, steps, 1);
    this.scale_iter = new FloatIterator(sequenceScale);

    this.finishedScaling = false;
  }

  public void scale(float scaleFactor) {
    int steps = 1;
    this.scale(scaleFactor, steps);
  }

  public void perpetualRotate(float degree, boolean counterClock) {
    int direction = (counterClock) ? 1 : -1;
    this.perpetualRotateIncrement = PApplet.radians(degree) * direction;

    this.perpetualRotate = true;
    this.finishedRotating = false;
  }

  public void perpetualRotate(float degree) {
    this.perpetualRotate(degree, true);
  }

  public void disablePerpetualRotate() {
    this.perpetualRotate = false;
    this.finishedRotating = true;
  }

  public void colorStroke() {
    if (enableStroke) {
      int strokeColor = this.stroke_iter.next();
      float strokeOpacity = this.strokeAlpha_iter.next();
      float strokeThickness = this.strokeWeight_iter.next();

      strokeColor = helper.colorAlpha(strokeColor, strokeOpacity);

      this.shapeObj.setStroke(strokeColor);
      this.shapeObj.setStrokeWeight(strokeThickness);

      this.shpStroke = strokeColor;
      this.shpStrokeAlpha = strokeOpacity;
      this.shpStrokeWeight = strokeThickness;

      this.finishedStroke = this.stroke_iter.hasNext() ? false : true;
      this.finishedStrokeAlpha = this.strokeAlpha_iter.hasNext() ? false : true;
    } else {
      this.shapeObj.setStroke(false);
    }
  }

  public void colorFill() {
    if (enableFill) {
      shpFill = this.fill_iter.next();
      shpFillAlpha = this.fillAlpha_iter.next();

      this.shapeObj.setFill(helper.colorAlpha(shpFill, shpFillAlpha));

      this.finishedFill = this.fill_iter.hasNext() ? false : true;
      this.finishedFillAlpha = this.fillAlpha_iter.hasNext() ? false : true;
    } else {
      this.shapeObj.setFill(false);
    }
  }

  public void rotateShape() {
    if (perpetualRotate) {
      shpRotation += perpetualRotateIncrement;
      this.shapeObj.rotate(perpetualRotateIncrement);
    } else if (rotate_iter.hasNext()) {
      float rotateIncrement = rotate_iter.next();
      this.shpRotation += rotateIncrement;
      this.shapeObj.rotate(rotateIncrement);
    } else {
      this.finishedRotating = true;
    }

  }

  public void translateShape() {
    if (velocity_iter.hasNext()) {
      this.translateIncrement = velocity_iter.next();
      this.position.add(translateIncrement);

      // translateIncrement.rotate(-shpRotation);
      // shapeObj.translate(translateIncrement.x,translateIncrement.y);
      // translateIncrement.rotate(shpRotation);
    } else {
      this.finishedTranslating = true;
    }
  }

  public void scaleShape() {
    if (scale_iter.hasNext()) {
      this.shpScale = this.scale_iter.next();
      this.shapeObj.scale(this.shpScale);
    } else {
      this.finishedScaling = true;
    }
  }

//  public void labelShape() {
//    if (enableText) {
//      this.papp.textFont(textFont);
//      this.papp.textAlign(textJustify);
//      this.papp.fill(textFill);
//      this.papp.text(textLabel, this.position.x, this.position.y);
//    }
//  }

  void shapeOptions() {

  }

  void draw() {
    this.papp.pushMatrix();

    this.colorFill();
    this.colorStroke();
    this.translateShape();
    this.rotateShape();
    this.scaleShape();
    this.shapeOptions();
//    this.labelShape();

    this.papp.shape(this.shapeObj, this.position.x, this.position.y);

    this.papp.popMatrix();
  }
}
