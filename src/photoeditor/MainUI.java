/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photoeditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.renderable.ParameterBlock;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author OmarElfaroukAhmed
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form MainUI
     */
    static ArrayList<InternalFrameCustom> internalFrames;
    static PlanarImage clipboard;
    static boolean wait = false;
    static String state = "normal";
    int f = 0;
    String statusbarString;
    boolean noSelect = true;

    public MainUI() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        internalFrames = new ArrayList<>();
//      createInternalFrame("test", 25, 200,null);
        fileChooserFrame.setSize(600, 500);

        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
        jLabel2.setText("100%");
        rotateFrame.setSize(300, 85);
        rotateFrame.setLocationRelativeTo(null);
        rotateFrame.setUndecorated(true);
        axis.setText("0:0");
        message.setText("No Message!");
        apply.setVisible(false);
        undoMenu.setEnabled(false);
//                frame.setLocationRelativeTo( null );
//        spinner.addChangeListener(new ChangeListener() {
//
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                System.out.println("change");
//                for (InternalFrameCustom internalFrame : internalFrames) {
//                    if (internalFrame.isSelected()) {
//                        internalFrame.panel.scaleChange(((Double) spinner.getValue()).floatValue());
//                        slider.setValue((int) ((((Double)spinner.getValue()).floatValue()*50)+0.01f));
//                        internalFrame.scrollPane.validate();
//                        internalFrame.scrollPane.repaint();
//                    }
//                }
//            }
//        });
//        
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                noSelect = true;
                message.setText("No Message!");
                for (InternalFrameCustom internalFrame : internalFrames) {
                    if (internalFrame.isSelected()) {
                        internalFrame.panel.scaleChange(((float) slider.getValue() / (float) 50) + 0.1f);
                        internalFrame.panel.setSlider(slider.getValue());
//                        spinner.setValue(new Double(((float)slider.getValue()/(float)50)));
                        jLabel2.setText(String.valueOf((int) ((((float) slider.getValue() + 0.1f) / (float) 50) * 100)) + "%");
                        internalFrame.scrollPane.validate();
                        internalFrame.scrollPane.repaint();
                        noSelect = false;
                    }
                }
                if (noSelect) {
                    message.setText("No Selected Window!");
                    Toolkit.getDefaultToolkit().beep();
                    slider.setValue(50);
                    jLabel2.setText("100" + "%");
                }
            }
        });
        System.setProperty("com.sun.media.jai.disableMediaLib", "true");

        axis.setSize(getWidth(), 14);
//        statusbarString = "    "
//        axis.setText("sfsfsssssssssssss             fksafaskfmaksf             asklf asfasklfasf kl                 lkfsafklafsafa ");

    }

    private void createInternalFrame(String name, int start, int size, PlanarImage image) {
        InternalFrameCustom internalFrame = new InternalFrameCustom(name, start, size, image);
        if (state.equals("rotate")) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image imageCursor = toolkit.getImage("rotate.png");
            Point hotSpot = new Point(10, 10);
            Cursor cursor = toolkit.createCustomCursor(imageCursor, hotSpot, "Pencil");
//            internalFrame.panel.setCursor(cursor);
        }
        internalFrames.add(internalFrame);
        desktop.add(internalFrame,0);
        message.setText("No Message!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooserFrame = new javax.swing.JFrame();
        fileChooser = new javax.swing.JFileChooser();
        rotateFrame = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        rotateValue = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        desktop = new javax.swing.JDesktopPane();
        slider = new javax.swing.JSlider();
        axis = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        markButton = new javax.swing.JToggleButton();
        rotateButton = new javax.swing.JToggleButton();
        moveButton = new javax.swing.JToggleButton();
        jButton3 = new javax.swing.JButton();
        zoomButton = new javax.swing.JButton();
        apply = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        message = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        undoMenu = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        fileChooserFrame.setResizable(false);

        fileChooser.setCurrentDirectory(new java.io.File("C:\\Program Files\\NetBeans 8.0.2\\‪C:\\Users\\OmarElfaroukAhmed\\Desktop"));
        fileChooser.setDialogTitle("Image Choose");
        fileChooser.setFileFilter(null);
        fileChooser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fileChooserFrameLayout = new javax.swing.GroupLayout(fileChooserFrame.getContentPane());
        fileChooserFrame.getContentPane().setLayout(fileChooserFrameLayout);
        fileChooserFrameLayout.setHorizontalGroup(
            fileChooserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );
        fileChooserFrameLayout.setVerticalGroup(
            fileChooserFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        rotateFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Rotate"));

        rotateValue.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        rotateValue.setMinimumSize(new java.awt.Dimension(20, 20));
        rotateValue.setPreferredSize(new java.awt.Dimension(40, 30));

        jButton5.setText("Rotate");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Cancel");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(rotateValue, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rotateValue, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rotateFrameLayout = new javax.swing.GroupLayout(rotateFrame.getContentPane());
        rotateFrame.getContentPane().setLayout(rotateFrameLayout);
        rotateFrameLayout.setHorizontalGroup(
            rotateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rotateFrameLayout.setVerticalGroup(
            rotateFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setForeground(new java.awt.Color(153, 153, 255));

        desktop.setPreferredSize(new java.awt.Dimension(600, 444));
        desktop.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                desktopMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                desktopMouseMoved(evt);
            }
        });
        desktop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desktopMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1213, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(desktop);

        slider.setMinimum(1);

        axis.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        axis.setForeground(new java.awt.Color(255, 51, 51));
        axis.setText("jLabel1");
        axis.setMaximumSize(new java.awt.Dimension(1000, 14));
        axis.setPreferredSize(new java.awt.Dimension(500, 14));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText("jLabel2");

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));

        markButton.setIcon(new javax.swing.ImageIcon("select.png"));
        markButton.setText("Select");
        markButton.setToolTipText("Select Area to (crop,copy,zoom)");
        markButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                markButtonActionPerformed(evt);
            }
        });

        rotateButton.setIcon(new javax.swing.ImageIcon("rotateDrage.png"));
        rotateButton.setText("Rotate");
        rotateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotateButtonActionPerformed(evt);
            }
        });

        moveButton.setIcon(new javax.swing.ImageIcon("move.png"));
        moveButton.setText("Move");
        moveButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                moveButtonStateChanged(evt);
            }
        });
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveButtonActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("crop.png"));
        jButton3.setText("Crop");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        zoomButton.setIcon(new javax.swing.ImageIcon("zoom.png"));
        zoomButton.setText("Zoom");
        zoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomButtonActionPerformed(evt);
            }
        });

        apply.setIcon(new javax.swing.ImageIcon("apply.png"));
        apply.setText("Apply");
        apply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(markButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rotateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(moveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zoomButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(apply)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zoomButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(markButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rotateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(moveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(apply, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel3.setIcon(new javax.swing.ImageIcon("axis.png"));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        message.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        message.setForeground(new java.awt.Color(255, 0, 0));
        message.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(axis, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(axis, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(message))
                        .addComponent(slider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Print");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        undoMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenu.setText("Undo");
        undoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoMenuActionPerformed(evt);
            }
        });
        jMenu2.add(undoMenu);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Copy");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setText("Paste");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Reset");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Action");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem10.setText("Rotate ( Angle )");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem11.setText("Zoom ( SubArea )");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem12.setText("Crop");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        message.setText("No Message!");
        fileChooserFrame.setVisible(true);
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed
        message.setText("No Message!");
        File f = fileChooser.getSelectedFile();
        if (f != null) {
            System.out.println(f.toString());
//                Image image = new ImageIcon(ImageIO.read(f)).getImage();
            PlanarImage image = JAI.create("fileload", f.getPath());
            createInternalFrame(f.getName(), 25, 200, image);
            fileChooserFrame.setVisible(false);

        }
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_fileChooserActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        message.setText("No Message!");
        for (InternalFrameCustom internalFrame : internalFrames) {
            if (internalFrame.isSelected()) {
                PrinterJob job = PrinterJob.getPrinterJob();
                JPanel panel = internalFrame.panel;
                // set the Printable to the PrinterJob
                job.setPrintable((Graphics graphics, PageFormat pageFormat, int pageIndex) -> {
                    if (pageIndex == 0) {
                        panel.print(graphics);
                        return Printable.PAGE_EXISTS;
                    }
                    return Printable.NO_SUCH_PAGE;
                });
                if (job.printDialog()) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        // handle exception
                    }
                }
            }
        }
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void desktopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopMouseClicked

    }//GEN-LAST:event_desktopMouseClicked

    private void desktopMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_desktopMouseMoved

    private void desktopMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopMouseDragged
    }//GEN-LAST:event_desktopMouseDragged

    private void undoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_undoMenuActionPerformed
        message.setText("No Message!");
        for (InternalFrameCustom internalFrame : internalFrames) {
            if (internalFrame.isSelected()) {
                internalFrame.panel.undoo();
                if (internalFrame.panel.undo.isEmpty()) {
                    undoMenu.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_undoMenuActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        rotateFrame.setVisible(false);
        message.setText("No Message!");
        noSelect = true;
        for (InternalFrameCustom internalFrame : internalFrames) {
            if (internalFrame.isSelected()) {
                if (!rotateValue.getText().isEmpty()) {
                    internalFrame.panel.rotate(Integer.parseInt(rotateValue.getText()) % 360);
                } else {
                    message.setText("Empty Field!");
                    Toolkit.getDefaultToolkit().beep();
                }
                noSelect = false;
            }
        }
        if( noSelect ){
            message.setText("No Selected Window!");
                    Toolkit.getDefaultToolkit().beep();
        }
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        message.setText("No Message!");
        Point[] points = null;
        PlanarImage image = null;
        for (InternalFrameCustom internalFrame : internalFrames) {
            if (internalFrame.isSelected()) {
                image = internalFrame.panel.image;
                points = internalFrame.getSelectedArea();
            }
        }
        try {
            if (image != null && points != null) {
                PlanarImage clipboard2;
                Point p = new Point(points[0].x + image.getMinX(), points[0].y + image.getMinY());
                Dimension d = new Dimension(points[1].x, points[1].y);
                Rectangle r = new Rectangle(p, d);
                ParameterBlock pb = new ParameterBlock();
                pb.addSource(image);
                pb.add((float) r.getX());
                pb.add((float) r.getY());
                pb.add((float) r.getWidth());
                pb.add((float) r.getHeight());
                clipboard = JAI.create("crop", pb);
                message.setText("Copied To ClipBoard!");
            }
        } catch (Exception e) {
            MainUI.message.setText("Zero Area !!!");
            Toolkit.getDefaultToolkit().beep();
        }
        internalFrames.stream().forEach((internalFrame) -> {
            internalFrame.panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        });
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        message.setText("No Message!");
        wait = true;
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        message.setText("No Message!");
        noSelect = true;
        for (InternalFrameCustom internalFrame : internalFrames) {
            if (internalFrame.isSelected()) {
                internalFrame.panel.reset();
                noSelect = false;
            }
        }
        if (noSelect) {
            message.setText("No Selected Window!");
            Toolkit.getDefaultToolkit().beep();
        }
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        message.setText("No Message!");
        rotateFrame.setVisible(false);
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        rotateButton.setSelected(false);
        markButton.setSelected(false);
        moveButton.setSelected(false);
        state = "normal";
        undoMenu.setEnabled(true);
        message.setText("No Message!");
        rotateFrame.setVisible(true);
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void applyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyActionPerformed
        message.setText("No Message!");
        for (InternalFrameCustom internalFrame : internalFrames) {
            if (internalFrame.isSelected()) {
                internalFrame.panel.apply();
            }
        }
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        apply.setVisible(false);
    }//GEN-LAST:event_applyActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        rotateButton.setSelected(false);
        markButton.setSelected(false);
        moveButton.setSelected(false);
        state = "normal";
        message.setText("No Message!");
        try {
            PlanarImage image = null;
            Point[] points = null;
            for (InternalFrameCustom internalFrame : internalFrames) {
                if (internalFrame.isSelected()) {
                    image = internalFrame.panel.image;
                    points = internalFrame.getSelectedArea();
                }
            }
            if (image != null && points != null) {
                PlanarImage clipboard2;
                //                image.getMinX();
                Point p = new Point(points[0].x + image.getMinX(), points[0].y + image.getMinY());
                Dimension d = new Dimension(points[1].x, points[1].y);
                Rectangle r = new Rectangle(p, d);
                ParameterBlock pb = new ParameterBlock();
                pb.addSource(image);
                pb.add((float) r.getX());
                pb.add((float) r.getY());
                pb.add((float) r.getWidth());
                pb.add((float) r.getHeight());

                //Creates the cropped area
                clipboard2 = JAI.create("crop", pb);

                //image.getSubimage(points[0].x, points[0].y, Math.abs(points[1].x - points[0].x), Math.abs(points[1].y - points[0].y));
                createInternalFrame("Cropped", 25, 200, clipboard2);
            }
        } catch (Exception e) {
            MainUI.message.setText("Zero Area !!!");
            Toolkit.getDefaultToolkit().beep();
        }
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void zoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomButtonActionPerformed
        message.setText("No Message!");
        ////////////////////////////////////////////////////////////////////
        for (InternalFrameCustom internalFrame : internalFrames) {
            if (internalFrame.isSelected()) {
                internalFrame.panel.zoom();
                internalFrame.scrollPane.validate();
                internalFrame.scrollPane.repaint();
            }
        }
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        ////////////////////////////////////////////////////////////////////
    }//GEN-LAST:event_zoomButtonActionPerformed

    private void markButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_markButtonActionPerformed
        message.setText("No Message!");
        rotateButton.setSelected(false);
        moveButton.setSelected(false);
        state = "mark";
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_markButtonActionPerformed

    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveButtonActionPerformed

        message.setText("No Message!");
        rotateButton.setSelected(false);
        markButton.setSelected(false);
        state = "move";
        undoMenu.setEnabled(true);
        for (int i = 0; i < internalFrames.size(); i++) {
            internalFrames.get(i).panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_moveButtonActionPerformed

    private void rotateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotateButtonActionPerformed
        message.setText("No Message!");
        markButton.setSelected(false);
        moveButton.setSelected(false);
        state = "rotate";
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("rotate.png");
        Point hotSpot = new Point(10, 10);
        Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Pencil");
        for (InternalFrameCustom internalFrame : internalFrames) {
            internalFrame.panel.setCursor(cursor);
        }
        undoMenu.setEnabled(true);
    }//GEN-LAST:event_rotateButtonActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        message.setText("No Message!");
        ////////////////////////////////////////////////////////////////////
        for (InternalFrameCustom internalFrame : internalFrames) {
            if (internalFrame.isSelected()) {
                internalFrame.panel.zoom();
                internalFrame.scrollPane.validate();
                internalFrame.scrollPane.repaint();
            }
        }
        internalFrames.stream().forEach((internalFrame) -> {
            internalFrame.panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        });
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        state = "normal";
        rotateButton.setSelected(false);
        markButton.setSelected(false);
        moveButton.setSelected(false);
        message.setText("No Message!");
        try {
            PlanarImage image = null;
            Point[] points = null;
            for (InternalFrameCustom internalFrame : internalFrames) {
                if (internalFrame.isSelected()) {
                    image = internalFrame.panel.image;
                    points = internalFrame.getSelectedArea();
                }
            }
            if (image != null && points != null) {
                PlanarImage clipboard2;
                //                image.getMinX();
                Point p = new Point(points[0].x + image.getMinX(), points[0].y + image.getMinY());
                Dimension d = new Dimension(points[1].x, points[1].y);
                Rectangle r = new Rectangle(p, d);
                ParameterBlock pb = new ParameterBlock();
                pb.addSource(image);
                pb.add((float) r.getX());
                pb.add((float) r.getY());
                pb.add((float) r.getWidth());
                pb.add((float) r.getHeight());

                //Creates the cropped area
                clipboard2 = JAI.create("crop", pb);

                //image.getSubimage(points[0].x, points[0].y, Math.abs(points[1].x - points[0].x), Math.abs(points[1].y - points[0].y));
                createInternalFrame("Cropped", 25, 200, clipboard2);
            }
        } catch (Exception e) {
            MainUI.message.setText("Zero Area !!!");
            Toolkit.getDefaultToolkit().beep();
        }
        internalFrames.stream().forEach((internalFrame) -> {
            internalFrame.panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        });
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void moveButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_moveButtonStateChanged
    }//GEN-LAST:event_moveButtonStateChanged

    private void spinnerPropertyChange2(java.beans.PropertyChangeEvent evt) {
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton apply;
    public static javax.swing.JLabel axis;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JFrame fileChooserFrame;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToggleButton markButton;
    public static javax.swing.JLabel message;
    private javax.swing.JToggleButton moveButton;
    private javax.swing.JToggleButton rotateButton;
    private javax.swing.JFrame rotateFrame;
    private javax.swing.JTextField rotateValue;
    public static javax.swing.JSlider slider;
    public static javax.swing.JMenuItem undoMenu;
    private javax.swing.JButton zoomButton;
    // End of variables declaration//GEN-END:variables

}
