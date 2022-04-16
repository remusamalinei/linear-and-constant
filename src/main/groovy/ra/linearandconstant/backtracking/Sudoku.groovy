package ra.linearandconstant.backtracking

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static ra.linearandconstant.backtracking.Sudoku.Cell.FIRST_CELL_OUTSIDE_GRID

/**
 * @author Remus Amalinei
 */
class Sudoku {

    private static class Digit {
        Integer value
        Boolean preFilled
    }

    @EqualsAndHashCode
    @ToString
    private static class Cell implements Comparable<Cell>{
        static final FIRST_CELL_OUTSIDE_GRID = new Cell(row: 9, col: 9)

        Integer row
        Integer col

        @Override
        int compareTo(Cell cell) {
            Integer thisIndex = row * 9 + col
            Integer cellIndex = cell.row * 9 + cell.col

            thisIndex.compareTo(cellIndex)
        }
    }

    private Integer[][] gridSolved


    Integer[][] solve(Integer[][] initialValues) {
        // TODO validate input, may not be possible to solve

        Digit[][] grid = initializeGrid(initialValues)

        gridSolved = null

        Cell initialCell = new Cell(
                row: -1,
                col: -1)
        Cell nextSolutionCell = findNextEmptyCell(grid, initialCell)

        addNextValueAndSolve(grid, nextSolutionCell)

        gridSolved
    }

    private Digit[][] initializeGrid(Integer[][] initialValues) {
        Digit[][] grid = new Digit[9][9]
        0.upto(8) { row ->
            0.upto(8) { col ->
                if (initialValues[row][col]) {
                    grid[row][col] = new Digit(
                            value: initialValues[row][col],
                            preFilled: true)
                }
            }
        }

        grid
    }

    private void toSolution(Digit[][] grid) {
        gridSolved = new Integer[9][9]
        0.upto(8) { row ->
            0.upto(8) { col ->
                gridSolved[row][col] = grid[row][col].value
            }
        }
    }

    private void addNextValueAndSolve(Digit[][] grid, Cell solutionCell) {
        if (gridSolved) {
            return
        }

        if (partialSolutionUpTo(solutionCell)) {
            Cell nextSolutionCell = findNextEmptyCell(grid, solutionCell)
            1.upto(9) { i ->
                grid[solutionCell.row][solutionCell.col] = new Digit(
                        value: i,
                        preFilled: false)

                if (canBeSolution(grid, solutionCell)) {
                    addNextValueAndSolve(grid, nextSolutionCell)
                }
            }
        } else {
            toSolution(grid)
        }
    }

    private Boolean partialSolutionUpTo(Cell solutionCell) {
        solutionCell < FIRST_CELL_OUTSIDE_GRID
    }

    private Cell findNextEmptyCell(Digit[][] grid, Cell solutionCell) {
        for (Integer row = 0; row < 9; row++) {
            for (Integer col = 0; col < 9; col++) {
                Cell currentCell = new Cell(
                        row: row,
                        col: col)

                if (currentCell <= solutionCell) {
                    continue
                }

                if ( ! grid[row][col]?.preFilled) {
                    return new Cell(
                            row: row,
                            col: col)
                }
            }
        }

        FIRST_CELL_OUTSIDE_GRID
    }

    private Boolean canBeSolution(Digit[][] grid, Cell solutionCell) {
        validSubGrids(grid, solutionCell) &&
                validOnRows(grid, solutionCell) &&
                validOnCols(grid, solutionCell)
    }

    private Boolean validSubGrids(Digit[][] grid, Cell solutionCell) {
        for (Integer x = 0; x < 3; x++) {
            for (Integer y = 0; y < 3; y++) {
                List<Integer> values = []
                for (Integer row = 0; row < 3; row++) {
                    for (Integer col = 0; col < 3; col++) {
                        Cell currentCell = new Cell(
                                row: 3 * x + row,
                                col: 3 * y + col)

                        if (partOfSolution(grid, currentCell, solutionCell)) {
                            values << grid[currentCell.row][currentCell.col].value
                        } else {
                            values << null
                        }
                    }
                    if (!allElementsUnique(values)) {
                        return false
                    }
                }
            }
        }

        true
    }

    private Boolean partOfSolution(Digit[][] grid, Cell currentCell, Cell solutionCell) {
        (currentCell <= solutionCell) || grid[currentCell.row][currentCell.col]?.preFilled
    }

    private Boolean allElementsUnique(List<Integer> list) {
        boolean nullRemoved = true
        while (nullRemoved) {
            nullRemoved = list.remove(null)
        }

        Set<Integer> set = (list as Set<Integer>)

        set.size() == list.size()
    }

    private Boolean validOnRows(Digit[][] grid, Cell solutionCell) {
        for (Integer i = 0; i < 9; i++) {
            List<Integer> rowValues = []
            for (Integer j = 0; j < 9; j++) {
                Cell currentCell = new Cell(
                        row: i,
                        col: j)

                if (partOfSolution(grid, currentCell, solutionCell)) {
                    rowValues << grid[currentCell.row][currentCell.col].value
                } else {
                    rowValues << null
                }
            }
            if (!allElementsUnique(rowValues)) {
                return false
            }
        }

        true
    }

    private Boolean validOnCols(Digit[][] grid, Cell solutionCell) {
        for (Integer i = 0; i < 9; i++) {
            List<Integer> colValues = []
            for (Integer j = 0; j < 9; j++) {
                Cell currentCell = new Cell(
                        row: j,
                        col: i)

                if (partOfSolution(grid, currentCell, solutionCell)) {
                    colValues << grid[currentCell.row][currentCell.col].value
                } else {
                    colValues << null
                }
            }
            if (!allElementsUnique(colValues)) {
                return false
            }
        }

        true
    }
}
