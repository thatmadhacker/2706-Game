package com.thatmadhacker.utils.joystick;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class JFrameWindow.
 */
public class JFrameWindow extends javax.swing.JFrame
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

/** The j label controller name. */
private JLabel jLabelControllerName;
  
  /** The j label XY axis. */
  private JLabel jLabelXYAxis;
  
  /** The j panel axes. */
  private JPanel jPanelAxes;
  
  /** The j panel buttons. */
  private JPanel jPanelButtons;
  
  /** The j panel hat switch. */
  private JPanel jPanelHatSwitch;
  
  /** The j panel XY axis. */
  private JPanel jPanelXYAxis;
  
  /** The progress bar 1. */
  private javax.swing.JProgressBar progressBar1;
  
  /** The progress bar 2. */
  private javax.swing.JProgressBar progressBar2;
  
  /** The progress bar 3. */
  private javax.swing.JProgressBar progressBar3;
  
  /** The progress bar label 1. */
  private JLabel progressBarLabel1;
  
  /** The progress bar label 2. */
  private JLabel progressBarLabel2;
  
  /** The progress bar label 3. */
  private JLabel progressBarLabel3;
  
  /**
   * Instantiates a new j frame window.
   */
  public JFrameWindow()
  {
/*  26 */     initComponents();
    
/*  28 */     setResizable(false);
/*  29 */     setLocationRelativeTo(null);
/*  30 */     setVisible(true);
  }
  







  /**
   * Inits the components.
   */
  private void initComponents()
  {
/*  42 */     this.jPanelAxes = new JPanel();
/*  43 */     this.jLabelXYAxis = new JLabel();
/*  44 */     this.progressBarLabel1 = new JLabel();
/*  45 */     this.progressBarLabel2 = new JLabel();
/*  46 */     this.progressBar1 = new javax.swing.JProgressBar();
/*  47 */     this.progressBar2 = new javax.swing.JProgressBar();
/*  48 */     this.jPanelXYAxis = new JPanel();
/*  49 */     this.progressBarLabel3 = new JLabel();
/*  50 */     this.progressBar3 = new javax.swing.JProgressBar();
/*  51 */     this.jPanelButtons = new JPanel();
/*  52 */     this.jPanelHatSwitch = new JPanel();
/*  53 */     this.jLabelControllerName = new JLabel();
    
/*  55 */     setDefaultCloseOperation(3);
/*  56 */     setTitle("JInput Joystick Test");
    
/*  58 */     this.jPanelAxes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Axes", 0, 0, null, new java.awt.Color(0, 51, 204)));
    
/*  60 */     this.jLabelXYAxis.setText("X Axis / Y Axis");
    
/*  62 */     this.progressBarLabel1.setText("Z Axis");
    
/*  64 */     this.progressBarLabel2.setText("Z Rotation");
    
/*  66 */     this.jPanelXYAxis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
/*  67 */     this.jPanelXYAxis.setPreferredSize(new java.awt.Dimension(111, 111));
    
/*  69 */     GroupLayout jPanelXYAxisLayout = new GroupLayout(this.jPanelXYAxis);
/*  70 */     this.jPanelXYAxis.setLayout(jPanelXYAxisLayout);
/*  71 */     jPanelXYAxisLayout.setHorizontalGroup(jPanelXYAxisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 109, 32767));
    


/*  75 */     jPanelXYAxisLayout.setVerticalGroup(jPanelXYAxisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 109, 32767));
    



/*  80 */     this.progressBarLabel3.setText("Z Axis");
    
/*  82 */     this.progressBar3.setMaximum(99);
    
/*  84 */     GroupLayout jPanelAxesLayout = new GroupLayout(this.jPanelAxes);
/*  85 */     this.jPanelAxes.setLayout(jPanelAxesLayout);
/*  86 */     jPanelAxesLayout.setHorizontalGroup(jPanelAxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelAxesLayout.createSequentialGroup().addGroup(jPanelAxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelAxesLayout.createSequentialGroup().addGap(58, 58, 58).addComponent(this.jLabelXYAxis)).addGroup(jPanelAxesLayout.createSequentialGroup().addGap(37, 37, 37).addComponent(this.jPanelXYAxis, -2, -1, -2))).addGap(67, 67, 67).addGroup(jPanelAxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(this.progressBar2, -2, -1, -2).addComponent(this.progressBar1, -2, -1, -2).addComponent(this.progressBarLabel2).addComponent(this.progressBarLabel1).addComponent(this.progressBarLabel3).addComponent(this.progressBar3, -2, -1, -2)).addContainerGap(26, 32767)));
    


















/* 106 */     jPanelAxesLayout.setVerticalGroup(jPanelAxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelAxesLayout.createSequentialGroup().addGroup(jPanelAxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.jLabelXYAxis).addComponent(this.progressBarLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelAxesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanelAxesLayout.createSequentialGroup().addComponent(this.progressBar1, -2, -1, -2).addGap(13, 13, 13).addComponent(this.progressBarLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(this.progressBar2, -2, -1, -2).addGap(16, 16, 16).addComponent(this.progressBarLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(this.progressBar3, -2, -1, -2)).addComponent(this.jPanelXYAxis, -2, -1, -2)).addGap(0, 16, 32767)));
    




















/* 128 */     this.jPanelButtons.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buttons", 0, 0, null, new java.awt.Color(0, 51, 204)));
    
/* 130 */     GroupLayout jPanelButtonsLayout = new GroupLayout(this.jPanelButtons);
/* 131 */     this.jPanelButtons.setLayout(jPanelButtonsLayout);
/* 132 */     jPanelButtonsLayout.setHorizontalGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 248, 32767));
    


/* 136 */     jPanelButtonsLayout.setVerticalGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 112, 32767));
    



/* 141 */     this.jPanelHatSwitch.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hat Switch", 0, 0, null, new java.awt.Color(0, 51, 204)));
    
/* 143 */     GroupLayout jPanelHatSwitchLayout = new GroupLayout(this.jPanelHatSwitch);
/* 144 */     this.jPanelHatSwitch.setLayout(jPanelHatSwitchLayout);
/* 145 */     jPanelHatSwitchLayout.setHorizontalGroup(jPanelHatSwitchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
    


/* 149 */     jPanelHatSwitchLayout.setVerticalGroup(jPanelHatSwitchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
    



/* 154 */     this.jLabelControllerName.setHorizontalAlignment(0);
/* 155 */     this.jLabelControllerName.setText("Controller name");
/* 156 */     this.jLabelControllerName.setHorizontalTextPosition(0);
    
/* 158 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 159 */     getContentPane().setLayout(layout);
/* 160 */     layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanelButtons, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanelHatSwitch, -1, -1, 32767)).addComponent(this.jPanelAxes, -1, -1, 32767).addComponent(this.jLabelControllerName, -1, -1, 32767)).addContainerGap()));
    











/* 173 */     layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabelControllerName, -1, -1, 32767).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanelAxes, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(this.jPanelButtons, -1, -1, 32767).addComponent(this.jPanelHatSwitch, -1, -1, 32767)).addContainerGap()));
    












/* 187 */     pack();
  }
  



















  /**
   * Sets the controller name.
   *
   * @param controllerName the new controller name
   */
  public void setControllerName(String controllerName)
  {
/* 211 */     this.jLabelControllerName.setText(controllerName);
  }
  
  /**
   * Sets the XY axis.
   *
   * @param xPercentage the x percentage
   * @param yPercentage the y percentage
   */
  public void setXYAxis(int xPercentage, int yPercentage) {
/* 215 */     java.awt.Graphics2D g2d = (java.awt.Graphics2D)this.jPanelXYAxis.getGraphics();
/* 216 */     g2d.clearRect(1, 1, this.jPanelXYAxis.getWidth() - 2, this.jPanelXYAxis.getHeight() - 2);
/* 217 */     g2d.fillOval(xPercentage, yPercentage, 10, 10);
  }
  
  /**
   * Sets the z axis.
   *
   * @param zAxisValueInPercentage the new z axis
   */
  public void setZAxis(int zAxisValueInPercentage) {
/* 221 */     this.progressBar1.setValue(zAxisValueInPercentage);
  }
  
  /**
   * Sets the z rotation.
   *
   * @param zRotationValueInPercentage the new z rotation
   */
  public void setZRotation(int zRotationValueInPercentage) {
/* 225 */     this.progressBar2.setValue(zRotationValueInPercentage);
  }
  
  /**
   * Sets the z axis gamepad.
   *
   * @param zAxisValueInPercentage the new z axis gamepad
   */
  public void setZAxisGamepad(int zAxisValueInPercentage) {
/* 229 */     this.progressBar3.setValue(zAxisValueInPercentage);
  }
  
  /**
   * Sets the controller buttons.
   *
   * @param buttonsPanel the new controller buttons
   */
  public void setControllerButtons(JPanel buttonsPanel) {
/* 233 */     this.jPanelButtons.removeAll();
/* 234 */     this.jPanelButtons.add(buttonsPanel);
/* 235 */     validate();
  }
  
  /**
   * Sets the hat switch.
   *
   * @param hatSwitchPosition the new hat switch
   */
  public void setHatSwitch(float hatSwitchPosition) {
/* 239 */     int circleSize = 100;
    
/* 241 */     java.awt.Graphics2D g2d = (java.awt.Graphics2D)this.jPanelHatSwitch.getGraphics();
/* 242 */     g2d.clearRect(5, 15, this.jPanelHatSwitch.getWidth() - 10, this.jPanelHatSwitch.getHeight() - 22);
/* 243 */     g2d.drawOval(20, 22, circleSize, circleSize);
    
/* 245 */     if (Float.compare(hatSwitchPosition, 0.0F) == 0) {
/* 246 */       return;
    }
/* 248 */     int smallCircleSize = 10;
/* 249 */     int upCircleX = 65;
/* 250 */     int upCircleY = 17;
/* 251 */     int leftCircleX = 15;
/* 252 */     int leftCircleY = 68;
/* 253 */     int betweenX = 37;
/* 254 */     int betweenY = 17;
    
/* 256 */     int x = 0;
/* 257 */     int y = 0;
    
/* 259 */     g2d.setColor(java.awt.Color.blue);
    
/* 261 */     if (Float.compare(hatSwitchPosition, 0.25F) == 0) {
/* 262 */       x = upCircleX;
/* 263 */       y = upCircleY;
/* 264 */     } else if (Float.compare(hatSwitchPosition, 0.75F) == 0) {
/* 265 */       x = upCircleX;
/* 266 */       y = upCircleY + circleSize;
/* 267 */     } else if (Float.compare(hatSwitchPosition, 1.0F) == 0) {
/* 268 */       x = leftCircleX;
/* 269 */       y = leftCircleY;
/* 270 */     } else if (Float.compare(hatSwitchPosition, 0.5F) == 0) {
/* 271 */       x = leftCircleX + circleSize;
/* 272 */       y = leftCircleY;
/* 273 */     } else if (Float.compare(hatSwitchPosition, 0.125F) == 0) {
/* 274 */       x = upCircleX - betweenX;
/* 275 */       y = upCircleY + betweenY;
/* 276 */     } else if (Float.compare(hatSwitchPosition, 0.375F) == 0) {
/* 277 */       x = upCircleX + betweenX;
/* 278 */       y = upCircleY + betweenY;
/* 279 */     } else if (Float.compare(hatSwitchPosition, 0.875F) == 0) {
/* 280 */       x = upCircleX - betweenX;
/* 281 */       y = upCircleY + circleSize - betweenY;
/* 282 */     } else if (Float.compare(hatSwitchPosition, 0.625F) == 0) {
/* 283 */       x = upCircleX + betweenX;
/* 284 */       y = upCircleY + circleSize - betweenY;
    }
    
/* 287 */     g2d.fillOval(x, y, smallCircleSize, smallCircleSize);
  }
  

  /**
   * Sets the progress bar 1 name.
   *
   * @param name the new progress bar 1 name
   */
  public void setProgressBar1Name(String name)
  {
/* 293 */     this.progressBarLabel1.setText(name);
  }
  
  /**
   * Sets the progress bar 2 name.
   *
   * @param name the new progress bar 2 name
   */
  public void setProgressBar2Name(String name)
  {
/* 298 */     this.progressBarLabel2.setText(name);
  }
  
  /**
   * Sets the progress bar 3 name.
   *
   * @param name the new progress bar 3 name
   */
  public void setProgressBar3Name(String name)
  {
/* 303 */     this.progressBarLabel3.setText(name);
  }
  

  /**
   * Hide progres bar 3.
   */
  public void hideProgresBar3()
  {
/* 309 */     this.progressBar3.setVisible(false);
  }
}


/* Location:              C:\Users\finma\Desktop\JInputJoystickTest.jar!\joystick\JFrameWindow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */