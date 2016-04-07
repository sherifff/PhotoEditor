/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.jai.PlanarImage;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author OmarElfaroukAhmed
 */
public class InternalFrameCustom extends JInternalFrame  {
//        PanelCustom panel;

    JScrollPane scrollPane;
    PlanarImage image;
    AjiDisplay panel;

    public InternalFrameCustom(String name, int start, int size, PlanarImage image) {
        super(name, true, true, true, true);
        setBounds(start, start, size * 3, size * 2);
//        setEnabled(true);
//        panel = new PanelCustom(image);
        panel = new AjiDisplay(image);
        this.image = image;
        int extra = (int) Math.sqrt(Math.pow(image.getHeight(), 2) + Math.pow(image.getWidth(), 2));
        extra /= 2;
        extra = 0;
//        panel.setPreferredSize(new Dimension(image.getWidth(this)+extra, image.getHeight(this) +extra));
        panel.setSize(image.getWidth(), image.getHeight());
        panel.setBackground(Color.WHITE);
        scrollPane = new JScrollPane();//panel);
        scrollPane.setPreferredSize(new Dimension(size, size / 2));
        scrollPane.setViewportView(panel);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBackground(Color.red);
        setVisible(true);
        panel.setScrollPane(scrollPane);
        setMaximumSize(new Dimension(1000000000, 1000000000));
        panel.setMaximumSize(new Dimension(1000000000, 1000000000));
    }

    public Point[] getSelectedArea() {
        return panel.getSelectedArea();
    }

}
