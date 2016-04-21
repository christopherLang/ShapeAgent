package template.library;

public class FloatIterator {
  float[] arraySeq;
  int index;
  int arrayLength;
  int max_index;
  boolean iter_complete;

  public FloatIterator(float[] arraySequence) {
    this.arraySeq = arraySequence;
    this.Initializer();
  }

  public FloatIterator(float element) {
    this.arraySeq = new float[1];
    this.arraySeq[0] = element;

    this.Initializer();
  }

  public void Initializer() {
    this.arrayLength = this.arraySeq.length;
    this.index = 0;
    this.max_index = this.arrayLength - 1;
    this.iter_complete = false;
  }

  public float next() {
    float valueToReturn = this.arraySeq[this.index];
    if (!this.iter_complete) {
      this.index += 1;
    }
    if (this.index > this.max_index) {
      this.iter_complete = true;
      this.index = this.max_index;
    }

    return valueToReturn;
  }

  public float current() {
    return this.arraySeq[this.index];
  }

  public boolean hasNext() {
    return !this.iter_complete;
  }

}
