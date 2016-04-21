package template.library;

import processing.core.*;

public class PVectorIterator {
  PVector[] arraySeq;
  int index;
  int arrayLength;
  int max_index;
  boolean iter_complete;
  boolean perpetual;

  public PVectorIterator(PVector[] arraySequence) {
    this.arraySeq = arraySequence;
    this.Initializer();
  }

  public PVectorIterator(PVector increment) {
    this.arraySeq = new PVector[1];
    this.arraySeq[0] = increment;
    this.Initializer();
    this.perpetual = true;
  }

  public PVectorIterator(float x, float y) {
    PVector pointInSpace = new PVector(x, y);
    this.arraySeq = new PVector[1];
    this.arraySeq[0] = pointInSpace;
    this.Initializer();
  }

  private void Initializer() {
    this.arrayLength = this.arraySeq.length;
    this.index = 0;
    this.max_index = this.arrayLength - 1;
    this.iter_complete = false;
    this.perpetual = false;
  }

  public PVector next() {
    PVector valueToReturn = this.arraySeq[this.index];

    if (!this.iter_complete & !this.perpetual) {
      this.index += 1;
    }

    if (this.index > this.max_index) {
      this.iter_complete = true;
      this.index = this.max_index;
    }

    return valueToReturn;
  }

  public PVector current() {
    return this.arraySeq[this.index];
  }

  public boolean hasNext() {
    return !this.iter_complete;
  }
}
