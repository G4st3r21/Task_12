package ru.vsu.cs.PoryadinAV;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class StarFractalFrame extends JFrame {
    private JPanel panelMain;
    private JSpinner spinnerSize;
    private JSpinner spinnerLevelCount;
    private JButton buttonDraw;
    private JLabel labelImg;
    private JButton buttonSaveToFile;

    public StarFractalFrame() {
        this.setTitle("StarFractal");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();

        JFileChooser fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image", "jpg");
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        spinnerSize.setValue(512);
        spinnerLevelCount.setValue(3);

        buttonDraw.addActionListener(e -> {
            StarFractal drawer = new StarFractal();
            int size = (int) spinnerSize.getValue();
            int levelCount = (int) spinnerLevelCount.getValue();

            BufferedImage img = new BufferedImage(size, (int) Math.round(size * Math.sqrt(3) / 2), BufferedImage.TYPE_INT_BGR);
            Graphics2D g2d = img.createGraphics();
            g2d.setColor(Color.RED);
            drawer.drawStar(img.getWidth(), levelCount, g2d);
            labelImg.setIcon(new ImageIcon(img));
        });

        buttonSaveToFile.addActionListener(e -> {
            if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                ImageIcon image = (ImageIcon) labelImg.getIcon();
                BufferedImage img = (BufferedImage) image.getImage();

                String fileName = fileChooserSave.getSelectedFile().getPath();
                if (!fileName.toLowerCase().endsWith(".jpg")) {
                    fileName += ".jpg";
                }

                File file = new File(fileName);
                try {
                    Main.printImageToFile(file, img);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(3, 4, new Insets(10, 10, 10, 10), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Размер треугольника:");
        panelMain.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Вложенность (кол-во уровней):");
        panelMain.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spinnerSize = new JSpinner();
        panelMain.add(spinnerSize, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spinnerLevelCount = new JSpinner();
        panelMain.add(spinnerLevelCount, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonDraw = new JButton();
        buttonDraw.setText("Нарисовать");
        panelMain.add(buttonDraw, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panelMain.add(scrollPane1, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelImg = new JLabel();
        labelImg.setHorizontalAlignment(2);
        labelImg.setVerticalAlignment(1);
        scrollPane1.setViewportView(labelImg);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

}
