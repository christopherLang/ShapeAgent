package shapeAgent;

import processing.core.*;

/**
 * Base abstract class for inheritance. Provides the key methods for color, 
 * movement, and transparency changes with easing and linear interpolation
 * <p>
 * Not to be used directly. This class does not define the Processing shapes
 * needed to work
 */
public abstract class ShapeAgent {
  public PApplet papp; // reference to parent sketch
  public PShape shapeObj;
  public PVector position;
  public PVector newPosition;
  public int shpFill;
  public float shpFillAlpha = 255;
  public int shpStroke;
  public float shpStrokeAlpha = 255;
  public float shpStrokeWeight = 1;
  public float shpRotation = 0;
  public float shpScale = 1;
  public boolean enableStroke = true;
  public boolean enableFill = true;

  public boolean perpetualRotate = false;
  public float perpetualRotateIncrement;

  public boolean finishedRotating = true;
  public boolean finishedTranslating = true;
  public boolean finishedScaling = true;
  public boolean finishedFill = true;
  public boolean finishedFillAlpha = true;
  public boolean finishedStroke = true;
  public boolean finishedStrokeAlpha = true;
  public boolean finishedStrokeThickness = true;

  // PFont textFont;
  // String textLabel = "";
  // boolean enableText = false;
  // int textJustify = PApplet.CENTER;
  // int textFill;

  public ColorIterator stroke_iter;
  public ColorIterator fill_iter;
  public FloatIterator strokeAlpha_iter;
  public FloatIterator fillAlpha_iter;
  public FloatIterator scale_iter;
  public FloatIterator strokeWeight_iter;
  public PVectorIterator velocity_iter;
  public FloatIterator rotate_iter;

  public Sequencer sequencer;
  public Helpers helper;
  // public final static String VERSION = "##library.prettyVersion##";


  /**
   * Constructor of ShapeAgent. Not to be used directly but for inheriting 
   * into other classes.
   * 
   * @param processingApp
   * The processing PApplet to draw on
   */
  public ShapeAgent(PApplet processingApp) {
    this.papp = processingApp;

    this.shpFill = this.papp.color(255, 255, 255);
    this.shpStroke = this.papp.color(255, 255, 255);
    // this.textFont = this.papp.createFont("Arial", 14, true);
    // this.textFill = this.papp.color(0, 0, 0);

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

  /**
   * <h1> Set the shape's stroke color </h1>
   * 
   * @param strokeColor color integer to color the stroke
   * @param steps number of frames for linear interpolation of color
   * @param type the type of easing. 1 for linear, 4 for quad, 5 for quartic
   * 
   */
  public void setStrokeColor(int strokeColor, int steps, int type) {
    int[] colorGradient = this.sequencer.color_sequencer(shpStroke, strokeColor, steps, type);
    this.stroke_iter = new ColorIterator(colorGradient);

    this.finishedStroke = false;
  }

  /**
   * <h1> Set the shape's stroke color </h1>
   * <p>
   * This defaults to linear easing
   * </p>
   * @param strokeColor color integer to color the stroke
   * @param steps number of frames for linear interpolation of color
   * 
   */
  public void setStrokeColor(int strokeColor, int steps) {
    this.setStrokeColor(strokeColor, steps, 1);
  }

  /**
   * <h1> Set the shape's stroke color </h1>
   * <p>
   * This defaults to linear easing and instant color change (1 frame)
   * </p>
   * @param strokeColor color integer to color the stroke
   * @param steps number of frames for linear interpolation of color
   */
  public void setStrokeColor(int strokeColor) {
    this.setStrokeColor(strokeColor, 1, 1);
  }
  
  /**
   * <h1> Enable or disable stroke color </h1>
   * <p>
   * Set to {@True} (the default) to enable stroke coloring
   * </p>
   * @param enableStroke enable or disable stroke coloring
   */
  public void setStrokeColor(boolean enableStroke) {
    this.shapeObj.setStroke(enableStroke);
  }

  /**
   * <h1> Set stroke color transparency </h1>
   * <p>
   * @param strokeOpacity a float indicating the opacity. Relative to color range
   * @param steps number of frames for linear interpolation of opacity
   * @param type the type of easing. 1 for linear, 4 for quad, 5 for quartic
   */
  public void setStrokeAlpha(float strokeOpacity, int steps, int type) {
    float[] opacityGradient = this.sequencer.value_sequencer(shpStrokeAlpha, strokeOpacity, steps, type);
    this.strokeAlpha_iter = new FloatIterator(opacityGradient);
    this.finishedStrokeAlpha = false;
  }

  /**
   * <h1> Set stroke color transparency </h1>
   * <p>
   * This defaults to linear easing
   * </p>
   * @param strokeOpacity a float indicating the opacity. Relative to color range
   * @param steps number of frames for linear interpolation of opacity
   */
  public void setStrokeAlpha(float strokeOpacity, int steps) {
    this.setStrokeAlpha(strokeOpacity, steps, 1);
  }

  /**
   * <h1> Set stroke color transparency </h1>
   * <p>
   * This defaults to linear easing and instant transparency change (1 frame)
   * </p>
   * @param strokeOpacity a float indicating the opacity. Relative to color range
   */
  public void setStrokeAlpha(float strokeOpacity) {
    this.setStrokeAlpha(strokeOpacity, 1, 1);
  }

  /**
   * <h1> Set stroke thickness </h1>
   * <p>
   * @param strokeOpacity a float indicating the stroke thickness
   * @param steps number of frames for linear interpolation of opacity
   * @param type the type of easing. 1 for linear, 4 for quad, 5 for quartic
   */
  public void setStrokeThickness(float weight, int steps, int type) {
    float[] thicknessGradient = this.sequencer.value_sequencer(shpStrokeWeight, weight, steps, type);
    this.strokeWeight_iter = new FloatIterator(thicknessGradient);

    this.finishedStrokeThickness = false;
  }

  /**
   * <h1> Set stroke thickness </h1>
   * <p>
   * This defaults to linear easing
   * @param strokeOpacity a float indicating the stroke thickness
   * @param steps number of frames for linear interpolation of opacity
   */
  public void setStrokeThickness(float weight, int steps) {
    this.setStrokeThickness(weight, steps, 1);
  }

  /**
   * <h1> Set stroke thickness </h1>
   * <p>
   * This defaults to linear easing and instant thickness change (1 frame)
   * @param strokeOpacity a float indicating the stroke thickness
   * @param steps number of frames for linear interpolation of opacity
   */
  public void setStrokeThickness(float weight) {
    this.setStrokeThickness(weight, 1, 1);
  }

  /**
   * <h1> Set fill color of shape </h1>
   * <p>
   * @param fillColor an integer indicating fill color
   * @param steps number of frames for linear interpolation of color
   * @param type the type of easing. 1 for linear, 4 for quad, 5 for quartic
   */
  public void setFillColor(int fillColor, int steps, int type) {
    int[] colorGradient = this.sequencer.color_sequencer(shpFill, fillColor, steps, type);
    this.fill_iter = new ColorIterator(colorGradient);

    this.finishedFill = false;
  }

  /**
   * <h1> Set fill color of shape </h1>
   * <p>
   * This defaults to linear easing
   * </p>
   * @param fillColor an integer indicating fill color
   * @param steps number of frames for linear interpolation of color
   */
  public void setFillColor(int fillColor, int steps) {
    this.setFillColor(fillColor, steps, 1);
  }

  /**
   * <h1> Set fill color of shape </h1>
   * <p>
   * This defaults to linear easing and instant color change (1 frame)
   * </p>
   * @param fillColor an integer indicating fill color
   */
  public void setFillColor(int fillColor) {
    this.setFillColor(fillColor, 1, 1);
  }

  /**
   * <h1> Set fill transparency of shape </h1>
   * <p>
   * @param fillOpacity a float indicating opacity. Relative to color range
   * @param steps number of frames for linear interpolation of color
   * @param type the type of easing. 1 for linear, 4 for quad, 5 for quartic
   */
  public void setFillAlpha(float fillOpacity, int steps, int type) {
    float[] opacityGradient = this.sequencer.value_sequencer(shpFillAlpha, fillOpacity, steps, type);
    this.fillAlpha_iter = new FloatIterator(opacityGradient);

    this.finishedFillAlpha = false;
  }

  /**
   * <h1> Set fill transparency of shape </h1>
   * <p>
   * This defaults to linear easing
   * </p>
   * @param fillOpacity a float indicating opacity. Relative to color range
   * @param steps number of frames for linear interpolation of color
   */
  public void setFillAlpha(float fillOpacity, int steps) {
    this.setFillAlpha(fillOpacity, steps, 1);
  }

  /**
   * <h1> Set fill transparency of shape </h1>
   * <p>
   * This defaults to linear easing and instant transparency change (1 frame)
   * </p>
   * @param fillOpacity a float indicating opacity. Relative to color range
   */
  public void setFillAlpha(float fillOpacity) {
    this.setFillAlpha(fillOpacity, 1, 1);
  }


  // public void setLabel(String text) {
  // this.textLabel = text;
  // }
  //
  // public void setLabelFont(PFont newFont) {
  // this.textFont = newFont;
  // }
  //
  // public void setLabelJustify(int justify) {
  // this.textJustify = justify;
  // }
  //
  // public void enableLabel(boolean turnOnLabel) {
  // this.enableText = turnOnLabel;
  // }
  //
  // public void setLabelColor(int labelColor) {
  // this.textFill = labelColor;
  // }
  /**
   * <h1> Rotate shape in place </h1>
   * <p>
   * Rotation is relative to center point of shape. Positive values indicate 
   * clock-wise rotation, negative is counter clock-wise rotation
   * </p>
   * @param degrees a float indicating degree of rotation (not radians)
   * @param steps number of frames for linear interpolation of rotation
   * @param type the type of easing. 1 for linear, 4 for quad, 5 for quartic
   */
  public void rotate(float degrees, int steps, int type) {
    float initAngle = this.shpRotation;
    float endAngle = initAngle + PApplet.radians(degrees);

    float[] rotateIncrements;
    rotateIncrements = this.sequencer.value_incrementer(initAngle, endAngle, steps, type);

    this.rotate_iter = new FloatIterator(rotateIncrements);

    this.finishedRotating = false;
  }

  /**
   * <h1> Rotate shape in place </h1>
   * <p>
   * Rotation is relative to center point of shape. Positive values indicate 
   * clock-wise rotation, negative is counter clock-wise rotation
   * </p>
   * <p>
   * This defaults to linear easing
   * </p>
   * @param degrees a float indicating degree of rotation (not radians)
   * @param steps number of frames for linear interpolation of rotation
   */
  public void rotate(float degrees, int steps) {
    this.rotate(degrees, steps, 1);
  }

  /**
   * <h1> Rotate shape in place </h1>
   * <p>
   * Rotation is relative to center point of the shape. Positive values indicate 
   * clock-wise rotation, negative is counter clock-wise rotation
   * </p>
   * <p>
   * This defaults to linear easing and instant rotation (1 frame)
   * </p>
   * @param degrees a float indicating degree of rotation (not radians)
   */
  public void rotate(float degrees) {
    this.rotate(degrees, 1, 1);
  }

  /**
   * <h1> Translate a shape </h1>
   * <p>
   * Translation is relative to the center of the shape
   * </p>
   * @param newLoc a {@code PVector} indicating location to translate to
   * @param steps number of frames for linear interpolation of translation
   * @param type the type of easing. 1 for linear, 4 for quad, 5 for quartic
   */
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

  /**
   * <h1> Translate a shape </h1>
   * <p>
   * Translation is relative to the center of the shape
   * </p>
   * <p>
   * This defaults to linear easing
   * </p>
   * @param newLoc a {@code PVector} indicating location to translate to
   * @param steps number of frames for linear interpolation of translation
   */
  public void translate(PVector newLoc, int steps) {
    this.translate(newLoc, steps, 1);
  }

  /**
   * <h1> Translate a shape </h1>
   * <p>
   * Translation is relative to the center of the shape
   * </p>
   * <p>
   * This defaults to linear easing and instant translation (1 frame)
   * </p>
   * @param newLoc a {@code PVector} indicating location to translate to
   */
  public void translate(PVector newLoc) {
    this.translate(newLoc, 1, 1);
  }

  /**
   * <h1> Translate a shape </h1>
   * <p>
   * Translation is relative to the center of the shape
   * </p>
   * @param x a float indicating the {@code x} position to translate to
   * @param y a float indicating the {@code y} position to translate to
   * @param steps number of frames for linear interpolation of translation
   * @param type the type of easing. 1 for linear, 4 for quad, 5 for quartic
   */
  public void translate(float x, float y, int steps, int type) {
    steps = (steps < 1) ? 1 : steps;

    PVector newLoc = new PVector(x, y);
    this.translate(newLoc, steps, type);
  }

  /**
   * <h1> Translate a shape </h1>
   * <p>
   * Translation is relative to the center of the shape
   * </p>
   * <p>
   * This defaults to linear easing
   * </p>
   * @param x a float indicating the {@code x} position to translate to
   * @param y a float indicating the {@code y} position to translate to
   * @param steps number of frames for linear interpolation of translation
   */
  public void translate(float x, float y, int steps) {
    this.translate(x, y, steps, 1);
  }

  /**
   * <h1> Translate a shape </h1>
   * <p>
   * Translation is relative to the center of the shape
   * </p>
   * <p>
   * This defaults to linear easing and instant translation (1 frame)
   * </p>
   * @param x a float indicating the {@code x} position to translate to
   * @param y a float indicating the {@code y} position to translate to
   */
  public void translate(float x, float y) {
    this.translate(x, y, 1, 1);
  }

  /**
   * <h1> Change scale of the shape </h1>
   * <p>
   * Scale is multiplicative, not additive
   * </p>
   * @param scaleFactor a float indicating the scaling factor
   * @param steps number of frames for linear interpolation of scale
   */
  public void scale(float scaleFactor, int steps) {
    steps = (steps < 1) ? 1 : steps;

    float[] sequenceScale = this.sequencer.value_sequencer(this.shpScale, scaleFactor, steps, 1);
    this.scale_iter = new FloatIterator(sequenceScale);

    this.finishedScaling = false;
  }

  /**
   * <h1> Change scale of the shape </h1>
   * <p>
   * Scale is multiplicative, not additive
   * </p>
   * <p>
   * This defaults to linear easing
   * </p>
   * @param scaleFactor a float indicating the scaling factor
   */
  public void scale(float scaleFactor) {
    int steps = 1;
    this.scale(scaleFactor, steps);
  }

  /**
   * <h1> Enable perpetual rotation about center </h1>
   * <p>
   * Setting this enables perpetual rotation regardless of previous state
   * </p>
   * @param degree the degree (not radians) to rotate per frame
   * @param counterClock a negative value rotates counter, otherwise clockwise
   */
  public void perpetualRotate(float degree, boolean counterClock) {
    int direction = (counterClock) ? 1 : -1;
    this.perpetualRotateIncrement = PApplet.radians(degree) * direction;

    this.perpetualRotate = true;
    this.finishedRotating = false;
  }

  /**
   * <h1> Enable perpetual rotation about center </h1>
   * <p>
   * Setting this enables perpetual rotation regardless of previous state
   * </p>
   * <p>
   * This defaults to the value of {@code degree} in regards to direction
   * </p>
   * @param degree the degree (not radians) to rotate per frame
   */
  public void perpetualRotate(float degree) {
    this.perpetualRotate(degree, true);
  }

  /**
   * <h1> Disable perpetual rotation about center </h1>
   * <p>
   */
  public void disablePerpetualRotate() {
    this.perpetualRotate = false;
    this.finishedRotating = true;
  }


  private void colorStroke() {
    if (this.enableStroke) {
      this.shpStroke = this.stroke_iter.next();
      this.shpStrokeAlpha = this.strokeAlpha_iter.next();
      this.shpStrokeWeight = this.strokeWeight_iter.next();

      this.shpStroke = helper.colorAlpha(this.shpStroke, this.shpStrokeAlpha);

      this.shapeObj.setStroke(this.shpStroke);
      this.shapeObj.setStrokeWeight(this.shpStrokeWeight);

      this.finishedStroke = this.stroke_iter.hasNext() ? false : true;
      this.finishedStrokeAlpha = this.strokeAlpha_iter.hasNext() ? false : true;
    } else {
      this.shapeObj.setStroke(false);
    }
  }


  private void colorFill() {
    if (enableFill) {
      this.shpFill = this.fill_iter.next();
      this.shpFillAlpha = this.fillAlpha_iter.next();

      this.shapeObj.setFill(this.helper.colorAlpha(this.shpFill, this.shpFillAlpha));

      this.finishedFill = this.fill_iter.hasNext() ? false : true;
      this.finishedFillAlpha = this.fillAlpha_iter.hasNext() ? false : true;
    } else {
      this.shapeObj.setFill(false);
    }
  }


  private void rotateShape() {
    if (perpetualRotate) {
      this.shpRotation += this.perpetualRotateIncrement;
    } else if (this.rotate_iter.hasNext()) {
      float rotateIncrement = this.rotate_iter.next();
      this.shpRotation += rotateIncrement;
    } else {
      this.finishedRotating = true;
    }

    this.papp.rotate(this.shpRotation);
  }


  private void translateShape() {
    if (this.velocity_iter.hasNext()) {
      this.position.add(this.velocity_iter.next());
    } else {
      this.finishedTranslating = true;
    }

    this.papp.translate(this.position.x, this.position.y);
  }


  private void scaleShape() {
    if (scale_iter.hasNext()) {
      this.shpScale = this.scale_iter.next();
      this.shapeObj.scale(this.shpScale);
    } else {
      this.finishedScaling = true;
    }
  }


  // public void labelShape() {
  // if (enableText) {
  // this.papp.textFont(textFont);
  // this.papp.textAlign(textJustify);
  // this.papp.fill(textFill);
  // this.papp.text(textLabel, this.position.x, this.position.y);
  // }
  // }

  public void shapeOptions() {

  }

  /**
   * <h1> Render the shape onto canvas </h1>
   */
  public void draw() {
    this.papp.pushMatrix();

    this.colorFill();
    this.colorStroke();
    this.translateShape();
    this.rotateShape();
    this.scaleShape();
    this.shapeOptions();
    // this.labelShape();

    this.papp.shape(this.shapeObj);

    this.papp.popMatrix();
  }
}
