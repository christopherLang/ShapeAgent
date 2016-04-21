package shapeAgent;

import processing.core.*;

public class Sequencer {
  PApplet papp;


  public Sequencer(PApplet processingApp) {
    this.papp = processingApp;
  }


  public float[] value_sequencer(float init, float end, int steps, int type) {
    // type values
    // 1 - linear sequence
    // 2 - quadratic easing in
    // 3 - quadratic easing out
    // 4 - quadratic easing in/out
    // 5 - quartic easing in/out
    float[] sequence = new float[steps + 1];

    if (type == 1) {
      sequence = this.seq_linear(init, end, steps);
    } else if (type == 4) {
      sequence = this.seq_quadraticEase_inout(init, end, steps);
    } else if (type == 5) {
      sequence = this.seq_quarticEase_inout(init, end, steps);
    }

    return sequence;
  }


  public float[] value_sequencer(float init, float end, int steps) {
    float[] sequence = value_sequencer(init, end, steps, 1);

    return sequence;
  }


  public int[] color_sequencer(int from, int to, int steps, int type) {
    int[] sequence = new int[steps + 1];
    float[] sequence_amt = value_sequencer(0, 1, steps, type);

    for (int i = 0; i <= steps; i++) {
      sequence[i] = this.papp.lerpColor(from, to, sequence_amt[i]);
    }

    return sequence;
  }


  public int[] color_sequencer(int from, int to, int steps) {
    int[] sequence = this.color_sequencer(from, to, steps, 1);

    return sequence;
  }


  public float[] value_incrementer(float init, float end, int steps, int type) {
    // type values
    // 1 - linear sequence
    // 2 - quadratic easing in
    // 3 - quadratic easing out
    // 4 - quadratic easing in/out
    float[] sequence = this.value_sequencer(init, end, steps, type);
    float[] increments = this.arrayIncrementer(sequence);

    return increments;
  }


  private float[] seq_linear(float init, float end, int steps) {
    steps = (steps < 1) ? 1 : steps;
    float total_change = end - init;
    float[] sequence = new float[steps + 1];

    for (int i = 0; i <= steps; i++) {
      sequence[i] = total_change * ((float) i / steps) + init;
    }

    return sequence;
  }


  private float[] seq_quadraticEase_inout(float init, float end, int steps) {
    steps = (steps < 1) ? 1 : steps;
    float total_change = end - init;
    float[] sequence = new float[steps + 1];

    for (int i = 0; i <= steps; i++) {
      float time_t = (float) i / ((float) steps / 2);

      if (time_t < 1) {
        sequence[i] = (total_change / 2) * (time_t * time_t) + init;
      } else {
        time_t -= 1;
        sequence[i] = (-1 * total_change) / 2 * (time_t * (time_t - 2) - 1) + init;
      }
    }

    return sequence;
  }


  private float[] seq_quarticEase_inout(float init, float end, int steps) {
    steps = (steps < 1) ? 1 : steps;
    float total_change = end - init;
    float[] sequence = new float[steps + 1];

    for (int i = 0; i <= steps; i++) {
      float time_t = (float) i / ((float) steps / 2);

      if (time_t < 1) {
        sequence[i] = (total_change / 2) * (time_t * time_t * time_t * time_t) + init;
      } else {
        time_t -= 2;
        sequence[i] = (-1 * total_change) / 2 * (time_t * time_t * time_t * time_t - 2) + init;
      }
    }

    return sequence;
  }


  private float[] arrayIncrementer(float[] floatArray) {
    float[] increments = new float[floatArray.length - 1];

    for (int i = 0; i < (floatArray.length - 1); i++) {
      increments[i] = floatArray[i + 1] - floatArray[i];
    }

    return increments;
  }
}
