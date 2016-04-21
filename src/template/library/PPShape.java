package template.library;

import processing.core.*;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class PPShape {
	PApplet myParent;  // reference to parent sketch
	PShape shapeObj;
	PVector position;
	PVector newPosition;
	int shpFill = myParent.color(255,255,255);
	float shpFillAlpha = 255;
	int shpStroke = myParent.color(255,255,255);
	float shpStrokeAlpha = 255;
	float shpStrokeWeight = 1;
	float shpRotation = 0;
	float shpScale= 1;
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
	
	PFont textFont = myParent.createFont("Arial",14,true);;
	String textLabel = "";
	boolean enableText = false;
	int textJustify = PApplet.CENTER;
	int textFill = myParent.color(0,0,0);
	
	
	ColorIterator stroke_iter = new ColorIterator(this.shpStroke);
	ColorIterator fill_iter = new ColorIterator(this.shpFill);
	FloatIterator strokeAlpha_iter = new FloatIterator(shpStrokeAlpha);
	FloatIterator fillAlpha_iter = new FloatIterator(this.shpFillAlpha);
	FloatIterator scale_iter = new FloatIterator(1);
	FloatIterator strokeWeight_iter = new FloatIterator(this.shpStrokeWeight);
	PVectorIterator velocity_iter = new PVectorIterator(0,0);
	FloatIterator rotate_iter = new FloatIterator(0);
	public final static String VERSION = "##library.prettyVersion##";
	

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the Library.
	 * 
	 * @example Hello
	 * @param theParent
	 */
	public PPShape(PApplet parentSketch) {
		myParent = parentSketch;
	}
	
}

