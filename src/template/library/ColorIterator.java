package template.library;

public class ColorIterator {
	int[] arraySeq;
	int index;
	int arrayLength;
	int max_index;
	boolean iter_complete;
	
	
	public ColorIterator(int[] arraySequence) {
		this.arraySeq = arraySequence;
		this.Initializer();
	}
	
	
	public ColorIterator(int element) {
		this.arraySeq = new int[1];
		this.arraySeq[0] = element;
		
		this.Initializer();
	}
	
	
	public void Initializer() {
		this.arrayLength = this.arraySeq.length;
		this.index = 0;
		this.max_index = this.arrayLength - 1;
		this.iter_complete = false;
	}
	
	
	public int next() {
		int valueToReturn = this.arraySeq[this.index];
		if (!this.iter_complete) {
			this.index += 1;
		}
		if (this.index > this.max_index) {
			this.iter_complete = true;
			this.index = this.max_index;
		}
		
		return valueToReturn;
	}
	
	
	public int current() {
		return this.arraySeq[this.index];
	}
	
	
	public boolean hasNext() {
		return !this.iter_complete;
	}
		
}