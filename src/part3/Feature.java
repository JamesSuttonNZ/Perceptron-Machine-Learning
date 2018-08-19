package part3;

import java.util.Arrays;

public class Feature {

	private int[] row;
	private int[]col;
	private boolean[] sgn;
	
	public Feature(int[] row, int[] col, boolean[] sgn){
		this.row = row;
		this.col = col;
		this.sgn = sgn;
	}

	public int[] getRow() {
		return row;
	}

	public void setRow(int[] row) {
		this.row = row;
	}

	public int[] getCol() {
		return col;
	}

	public void setCol(int[] col) {
		this.col = col;
	}

	public boolean[] getSgn() {
		return sgn;
	}

	public void setSgn(boolean[] sgn) {
		this.sgn = sgn;
	}
	
	public int getValue(Instance inst){
		int sum=0;
		for(int i=0; i < 4; i++) {
			boolean[][] image = inst.getImage();
			if (image[row[i]][col[i]] == sgn[i]) sum++;
		}
		return (sum>=3)?1:0;
	}

	@Override
	public String toString() {
		return "Feature [row=" + Arrays.toString(row) + ", col=" + Arrays.toString(col) + ", sgn="
				+ Arrays.toString(sgn) + "]";
	}

	
}
