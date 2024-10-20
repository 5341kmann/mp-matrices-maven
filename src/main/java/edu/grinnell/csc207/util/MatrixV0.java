package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @param <T> The type of values stored in the matrix.
 * @author Grant Sackmann
 * @author Samuel A. Rebelsky
 */
public class MatrixV0<T> implements Matrix<T> {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  int width;
  int height;
  T def;
  T[][] matrix;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the given value as the default.
   *
   * @param width  The width of the matrix.
   * @param height The height of the matrix.
   * @param def    The default value, used to fill all the cells.
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    this.width = width;
    this.height = height;
    this.def = def;
    initialize();
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with null as the default value.
   *
   * @param width  The width of the matrix.
   * @param height The height of the matrix.
   * @throws NegativeArraySizeException If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
    initialize();
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  private void initialize() {
    matrix = (T[][]) new Object[height][width];
    if (def != null) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          matrix[i][j] = def;
        } // for
      } // for
    } // if
  } // initialize()

  /**
   * Get the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   * @return the value at the specified location.
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    return matrix[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row The row of the element.
   * @param col The column of the element.
   * @param val The value to set.
   * @throws IndexOutOfBoundsException If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (row >= 0 & row < height && col >= 0 & col < width) {
      matrix[row][col] = val;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row The number of the row to insert.
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (row >= 0 && row < height) {
      this.height++;
      T[][] newMatrix = (T[][]) new Object[height][width];

      for (int i = 0; i < row; i++) {
        newMatrix[i] = this.matrix[i].clone();
      } // for

      for (int i = 0; i < width; i++) {
        newMatrix[row][i] = this.matrix[row][i];
      } // for

      for (int i = row + 1; i < height; i++) {
        newMatrix[i] = this.matrix[i - 1].clone();
      } // for

      this.matrix = newMatrix;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row  The number of the row to insert.
   * @param vals The values to insert.
   * @throws IndexOutOfBoundsException If the row is negative or greater than the height.
   * @throws ArraySizeException        If the size of vals is not the same as the width of the
   *                                   matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (row >= 0 && row < height) {
      if (vals.length != this.width) {
        throw new ArraySizeException();
      } // if
      this.height++;
      T[][] newMatrix = (T[][]) new Object[height][width];

      for (int i = 0; i < row; i++) {
        newMatrix[i] = this.matrix[i].clone();
      } // for

      newMatrix[row] = vals.clone();

      for (int i = row + 1; i < height; i++) {
        newMatrix[i] = this.matrix[i - 1].clone();
      } // for

      this.matrix = newMatrix;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col The number of the column to insert.
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (col >= 0 && col < width) {
      this.width++;
      T[][] newMatrix = (T[][]) new Object[width][height];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < col; j++) {
          newMatrix[i][j] = this.matrix[i][j];
        } // for
      } // for

      for (int i = 0; i < height; i++) {
        newMatrix[i][col] = def;
      } // for

      for (int i = 0; i < height; i++) {
        for (int j = col + 1; j < width; j++) {
          newMatrix[i][j] = this.matrix[i][j - 1];
        } // for
      } // for

      this.matrix = newMatrix;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col  The number of the column to insert.
   * @param vals The values to insert.
   * @throws IndexOutOfBoundsException If the column is negative or greater than the width.
   * @throws ArraySizeException        If the size of vals is not the same as the height of the
   *                                   matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (col >= 0 && col < width) {
      if (vals.length != this.height) {
        throw new ArraySizeException();
      } // if
      this.width++;
      T[][] newMatrix = (T[][]) new Object[width][height];

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < col; j++) {
          newMatrix[i][j] = this.matrix[i][j];
        } // for
      } // for

      for (int i = 0; i < height; i++) {
        newMatrix[i][col] = vals[i];
      } // for

      for (int i = 0; i < height; i++) {
        for (int j = col + 1; j < width; j++) {
          newMatrix[i][j] = this.matrix[i][j - 1];
        } // for
      } // for

      this.matrix = newMatrix;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row The number of the row to delete.
   * @throws IndexOutOfBoundsException If the row is negative or greater than or equal to the
   *                                   height.
   */
  public void deleteRow(int row) {
    if (row >= 0 && row < this.height) {
      this.height--;
      T[][] newMatrix = (T[][]) new Object[this.height][this.width];

      for (int i = 0; i < row; i++) {
        newMatrix[i] = this.matrix[i].clone();
      } // for

      for (int i = row; i < this.height; i++) {
        newMatrix[i] = this.matrix[i + 1].clone();
      } // for

      this.matrix = newMatrix;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col The number of the column to delete.
   * @throws IndexOutOfBoundsException If the column is negative or greater than or equal to the
   *                                   width.
   */
  public void deleteCol(int col) {
    if (col >= 0 && col < this.width) {
      this.width--;
      T[][] newMatrix = (T[][]) new Object[this.width][this.height];

      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < col; j++) {
          newMatrix[i][j] = this.matrix[i][j];
        } // for
      } // for

      for (int i = 0; i < this.height; i++) {
        for (int j = col; j < this.width; j++) {
          newMatrix[i][j] = this.matrix[i][j + 1];
        } // for
      } // for

      this.matrix = newMatrix;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow The top edge / row to start with (inclusive).
   * @param startCol The left edge / column to start with (inclusive).
   * @param endRow   The bottom edge / row to stop with (exclusive).
   * @param endCol   The right edge / column to stop with (exclusive).
   * @param val      The value to store.
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if (startRow >= 0 && startRow < this.height && startCol >= 0 && startCol < this.width) {
      for (int i = startRow; i < endRow; i++) {
        for (int j = startCol; j < endCol; j++) {
          this.matrix[i][j] = val;
        } // for
      } // for
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow The row to start with (inclusive).
   * @param startCol The column to start with (inclusive).
   * @param deltaRow How much to change the row in each step.
   * @param deltaCol How much to change the column in each step.
   * @param endRow   The row to stop with (exclusive).
   * @param endCol   The column to stop with (exclusive).
   * @param val      The value to store.
   * @throw IndexOutOfBoundsException If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    for (int i = startRow; i < endRow; i+=deltaRow) {
      for (int j = startCol; j < endCol; j+=deltaCol) {
        this.matrix[i][j] = val;
      } // for
    } // for
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual elements are mutable,
   * mutating them in one matrix may affect the other matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0<T> cloneMatrix = new MatrixV0<>(this.width, this.height, this.def);
    for (int i = 0; i < this.height; i++) {
      cloneMatrix.matrix[i] = this.matrix[i].clone();
    } // for

    return cloneMatrix;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other The object to compare.
   * @return true if the other object is a matrix with the same width, height, and equal elements;
   * false otherwise.
   */
  public boolean equals(Object other) {
    if (other instanceof MatrixV0 && ((MatrixV0) other).width == this.width && ((MatrixV0) other).height == this.height) {
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          if(this.matrix[i][j] != ((MatrixV0) other).matrix[i][j]){
            return false;
          } // if
        } // for
      } // for
      return true;
    } // if
    return false;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object that implements `equals` is
   * expected to implement `hashCode` and ensure that the hash codes for two equal objects are the
   * same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
