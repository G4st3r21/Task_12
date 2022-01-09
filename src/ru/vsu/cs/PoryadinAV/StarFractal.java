package ru.vsu.cs.PoryadinAV;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StarFractal {

    public void drawStar(int size, int depth, Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, size, size);

        Integer[] center = new Integer[]{size / 2, size / 2};
        g2d.setColor(Color.BLACK);
        printStarForFirstLevel(center[0], center[1], g2d, size);

        List<Integer[]> centres = new ArrayList<>();
        centres.add(center);

        if (depth > 1) {
            findApexesOfStarsOfNextLevel(2, depth, centres, g2d, size);
        }
    }

    public void findApexesOfStarsOfNextLevel(int level, int endLevel, List<Integer[]> centres, Graphics2D g2d, int size) {
        List<Integer[]> allCentres = new ArrayList<>();
        Integer[] firstCentres, secondCentres, thirdCentres, fourthCentres, fifthCentres, sixthCentres;
        int horizontalLineLastLevel = (int) ((size * 4 / 5) / (Math.pow(3, level - 1)));
        int diagonalLineByXLastLevel = (int) ((size * 4 / 8) / (Math.pow(3, level - 1)));
        int diagonalLineByYLastLevel = (int) ((size * 4 / 6) / (Math.pow(3, level - 1)));

        int horizontalLineNextLevel = (int) ((size * 4 / 5) / Math.pow(3, level));
        int diagonalLineByXNextLevel = (int) ((size * 4 / 8) / Math.pow(3, level));
        int diagonalLineByYNextLevel = (int) ((size * 4 / 6) / Math.pow(3, level));

        for (Integer[] center : centres) {
            firstCentres = new Integer[]{center[0] - diagonalLineByXLastLevel, center[1] - diagonalLineByYLastLevel};
            allCentres.add(firstCentres);

            secondCentres = new Integer[]{center[0] + diagonalLineByXLastLevel, center[1] - diagonalLineByYLastLevel};
            allCentres.add(secondCentres);

            thirdCentres = new Integer[]{center[0] + diagonalLineByXLastLevel, center[1] + diagonalLineByYLastLevel};
            allCentres.add(thirdCentres);

            fourthCentres = new Integer[]{center[0] - diagonalLineByXLastLevel, center[1] + diagonalLineByYLastLevel};
            allCentres.add(fourthCentres);

            fifthCentres = new Integer[]{center[0] - horizontalLineLastLevel, center[1]};
            allCentres.add(fifthCentres);

            sixthCentres = new Integer[]{center[0] + horizontalLineLastLevel, center[1]};
            allCentres.add(sixthCentres);

            printStars(new ArrayList<>(Arrays.asList(firstCentres, secondCentres, thirdCentres,
                    fourthCentres, fifthCentres, sixthCentres)), g2d, horizontalLineNextLevel,
                    diagonalLineByXNextLevel, diagonalLineByYNextLevel);
        }
        level++;

        if (level != endLevel + 1) {
            findApexesOfStarsOfNextLevel(level, endLevel, allCentres, g2d, size);
        }
    }

    private void printStars(List<Integer[]> centres, Graphics2D g2d, int horizontalLine,
                            int diagonalLineByX, int diagonalLineByY) {
        for (Integer[] center : centres) {
            printStar(center[0], center[1], g2d, horizontalLine, diagonalLineByX, diagonalLineByY);
        }
    }

    private void printStar(int centerX, int centerY, Graphics2D g2d,
                           int horizontalLine, int diagonalLineByX, int diagonalLineByY) {
        g2d.drawLine(centerX, centerY, centerX - diagonalLineByX, centerY - diagonalLineByY);
        g2d.drawLine(centerX, centerY, centerX + diagonalLineByX, centerY - diagonalLineByY);
        g2d.drawLine(centerX, centerY, centerX + diagonalLineByX, centerY + diagonalLineByY);
        g2d.drawLine(centerX, centerY, centerX - diagonalLineByX, centerY + diagonalLineByY);
        g2d.drawLine(centerX, centerY, centerX - horizontalLine, centerY);
        g2d.drawLine(centerX, centerY, centerX + horizontalLine, centerY);
    }

    private void printStarForFirstLevel(int centerX, int centerY, Graphics2D g2d, int size) {
        int horizontalLine = ((size * 4 / 5) / 3);
        int diagonalLineByX = ((size * 4 / 8) / 3);
        int diagonalLineByY = ((size * 4 / 6) / 3);

        printStar(centerX, centerY, g2d, horizontalLine, diagonalLineByX, diagonalLineByY);
    }
}
