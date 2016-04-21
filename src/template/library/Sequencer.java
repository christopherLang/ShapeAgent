package template.library;


public class Sequencer {
	public float[] value_sequencer(float init,float end,int steps,int type) {
		// type values
		// 1 - linear sequence
		// 2 - quadratic easing in
		// 3 - quadratic easing out
		// 4 - quadratic easing in/out
		// 5 - quartic easing in/out
		float[] sequence = new float[steps + 1];
		
		if (type == 1) {
			sequence = this.seq_linear(init,end,steps);
		}
		else if (type == 4) {
			sequence = this.seq_quadraticEase_inout(init,end,steps);
		}
		else if (type == 5) {
			sequence = this.seq_quarticEase_inout(init,end,steps);
		}
		
		return sequence;
	}
}
