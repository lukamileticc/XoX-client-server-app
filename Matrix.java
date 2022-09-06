package TCP_JAN1_2021;

public class Matrix {


    private StringBuilder matrix;
    public Matrix(String matrix) {
        this.matrix = new StringBuilder(matrix);
    }

    public String getTable() {

        String first_row = matrix.substring(0,3);
        String second_row = matrix.substring(3,6);
        String third_row = matrix.substring(6,9);

        return first_row + "\n" + second_row + "\n" + third_row;
    }

    public StringBuilder getMatrix() {
        return this.matrix;
    }

    public void setHand(String hand,char c) {
        int position = Integer.parseInt(hand);
        this.matrix.setCharAt(position,c);
    }
}
