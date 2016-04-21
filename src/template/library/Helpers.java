package template.library;

import processing.core.*;

public class Helpers {
  PApplet papp;

  public Helpers(PApplet processingApp) {
    this.papp = processingApp;
  }

  public int colorAlpha(int hexColor, float alpha) {
    float colorRed = hexColor >> 16 & 0xFF;
    float colorGreen = hexColor >> 8 & 0xFF;
    float colorBlue = hexColor & 0xFF;

    int combinedColor = this.papp.color(colorRed, colorGreen, colorBlue, alpha);

    return combinedColor;
  }
}
