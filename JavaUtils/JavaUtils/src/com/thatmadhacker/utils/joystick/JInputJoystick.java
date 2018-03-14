package com.thatmadhacker.utils.joystick;

import java.util.ArrayList;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

// TODO: Auto-generated Javadoc
/**
 * The Class JInputJoystick.
 */
public class JInputJoystick
{
  
  /** The controller. */
  private Controller controller;
  
  /** The buttons values. */
  private ArrayList<Boolean> buttonsValues;
  
  /**
   * Instantiates a new j input joystick.
   *
   * @param controllerType the controller type
   */
  public JInputJoystick(Controller.Type controllerType)
  {
/*  51 */     initialize();
/*  52 */     initController(controllerType, null);
  }
  
  /**
   * Instantiates a new j input joystick.
   *
   * @param controllerType_1 the controller type 1
   * @param controllerType_2 the controller type 2
   */
  public JInputJoystick(Controller.Type controllerType_1, Controller.Type controllerType_2)
  {
/*  64 */     initialize();
/*  65 */     initController(controllerType_1, controllerType_2);
  }
  
  /**
   * Initialize.
   */
  private void initialize()
  {
/*  70 */     this.controller = null;
/*  71 */     this.buttonsValues = new ArrayList<Boolean>();
  }
  





  /**
   * Inits the controller.
   *
   * @param controllerType_1 the controller type 1
   * @param controllerType_2 the controller type 2
   */
  private void initController(Controller.Type controllerType_1, Controller.Type controllerType_2)
  {
/*  81 */     Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
    
/*  83 */     for (int i = 0; (i < controllers.length) && (this.controller == null); i++) {
/*  84 */       if ((controllers[i].getType() == controllerType_1) || (controllers[i].getType() == controllerType_2))
      {



/*  89 */         this.controller = controllers[i];
/*  90 */         break;
      }
    }
  }
  















  /**
   * Checks if is controller connected.
   *
   * @return true, if is controller connected
   */
  public boolean isControllerConnected()
  {
    try
    {
/* 114 */       return this.controller.poll();
    } catch (Exception e) {}
/* 116 */     return false;
  }
  








  /**
   * Gets the controller type.
   *
   * @return the controller type
   */
  public Controller.Type getControllerType()
  {
/* 129 */     return this.controller.getType();
  }
  







  /**
   * Gets the controller name.
   *
   * @return the controller name
   */
  public String getControllerName()
  {
/* 141 */     return this.controller.getName();
  }
  











  /**
   * Poll controller.
   *
   * @return true, if successful
   */
  public boolean pollController()
  {
/* 157 */     this.buttonsValues.clear();
    
/* 159 */     boolean isControllerValid = this.controller.poll();
/* 160 */     if (!isControllerValid) {
/* 161 */       return false;
    }
/* 163 */     Component[] components = this.controller.getComponents();
    
/* 165 */     for (int i = 0; i < components.length; i++) {
/* 166 */       Component component = components[i];
      

/* 169 */       if (component.getName().contains("Button")) {
/* 170 */         if (component.getPollData() == 1.0F) {
/* 171 */           this.buttonsValues.add(Boolean.TRUE);
        } else
/* 173 */           this.buttonsValues.add(Boolean.FALSE);
      }
    }
/* 176 */     return isControllerValid;
  }
  







  /**
   * Component exists.
   *
   * @param identifier the identifier
   * @return true, if successful
   */
  public boolean componentExists(Component.Identifier identifier)
  {
/* 188 */     Component component = this.controller.getComponent(identifier);
    
/* 190 */     if (component != null) {
/* 191 */       return true;
    }
/* 193 */     return false;
  }
  






  /**
   * Gets the component value.
   *
   * @param identifier the identifier
   * @return the component value
   */
  public float getComponentValue(Component.Identifier identifier)
  {
/* 204 */     return this.controller.getComponent(identifier).getPollData();
  }
  






  /**
   * Gets the number of buttons.
   *
   * @return the number of buttons
   */
  public int getNumberOfButtons()
  {
/* 215 */     return this.buttonsValues.size();
  }
  








  /**
   * Gets the buttons values.
   *
   * @return the buttons values
   */
  public ArrayList<Boolean> getButtonsValues()
  {
/* 228 */     return this.buttonsValues;
  }
  






  /**
   * Gets the button value.
   *
   * @param index the index
   * @return the button value
   */
  public boolean getButtonValue(int index)
  {
/* 239 */     return ((Boolean)this.buttonsValues.get(index)).booleanValue();
  }
  






  /**
   * Gets the x axis value.
   *
   * @return the x axis value
   */
  public float getXAxisValue()
  {
/* 250 */     Component.Identifier identifier = Component.Identifier.Axis.X;
/* 251 */     return this.controller.getComponent(identifier).getPollData();
  }
  








  /**
   * Gets the x axis percentage.
   *
   * @return the x axis percentage
   */
  public int getXAxisPercentage()
  {
/* 264 */     float xAxisValue = getXAxisValue();
/* 265 */     int xAxisValuePercentage = (int)((2.0F - (1.0F - xAxisValue)) * 100.0F) / 2;
    
/* 267 */     return xAxisValuePercentage;
  }
  






  /**
   * Gets the y axis value.
   *
   * @return the y axis value
   */
  public float getYAxisValue()
  {
/* 278 */     Component.Identifier identifier = Component.Identifier.Axis.Y;
/* 279 */     return this.controller.getComponent(identifier).getPollData();
  }
  








  /**
   * Gets the y axis percentage.
   *
   * @return the y axis percentage
   */
  public int getYAxisPercentage()
  {
/* 292 */     float yAxisValue = getYAxisValue();
/* 293 */     int yAxisValuePercentage = (int)((2.0F - (1.0F - yAxisValue)) * 100.0F) / 2;
    
/* 295 */     return yAxisValuePercentage;
  }
  






  /**
   * Gets the z rotation value.
   *
   * @return the z rotation value
   */
  public float getZRotationValue()
  {
/* 306 */     Component.Identifier identifier = Component.Identifier.Axis.RZ;
/* 307 */     return this.controller.getComponent(identifier).getPollData();
  }
  








  /**
   * Gets the z rotation percentage.
   *
   * @return the z rotation percentage
   */
  public int getZRotationPercentage()
  {
/* 320 */     float zRotation = getZRotationValue();
/* 321 */     int zRotationValuePercentage = (int)((2.0F - (1.0F - zRotation)) * 100.0F) / 2;
    
/* 323 */     return zRotationValuePercentage;
  }
  






  /**
   * Gets the z axis value.
   *
   * @return the z axis value
   */
  public float getZAxisValue()
  {
/* 334 */     Component.Identifier identifier = Component.Identifier.Axis.Z;
/* 335 */     return this.controller.getComponent(identifier).getPollData();
  }
  








  /**
   * Gets the z axis percentage.
   *
   * @return the z axis percentage
   */
  public int getZAxisPercentage()
  {
/* 348 */     float zAxisValue = getZAxisValue();
/* 349 */     int zAxisValuePercentage = (int)((2.0F - (1.0F - zAxisValue)) * 100.0F) / 2;
    
/* 351 */     return zAxisValuePercentage;
  }
  






  /**
   * Gets the x rotation value.
   *
   * @return the x rotation value
   */
  public float getXRotationValue()
  {
/* 362 */     Component.Identifier identifier = Component.Identifier.Axis.RX;
/* 363 */     return this.controller.getComponent(identifier).getPollData();
  }
  








  /**
   * Gets the x rotation percentage.
   *
   * @return the x rotation percentage
   */
  public int getXRotationPercentage()
  {
/* 376 */     float xRotationValue = getXRotationValue();
/* 377 */     int xRotationValuePercentage = (int)((2.0F - (1.0F - xRotationValue)) * 100.0F) / 2;
    
/* 379 */     return xRotationValuePercentage;
  }
  






  /**
   * Gets the y rotation value.
   *
   * @return the y rotation value
   */
  public float getYRotationValue()
  {
/* 390 */     Component.Identifier identifier = Component.Identifier.Axis.RY;
/* 391 */     return this.controller.getComponent(identifier).getPollData();
  }
  








  /**
   * Gets the y rotation percentage.
   *
   * @return the y rotation percentage
   */
  public int getYRotationPercentage()
  {
/* 404 */     float yRotationValue = getYRotationValue();
/* 405 */     int yRotationValuePercentage = (int)((2.0F - (1.0F - yRotationValue)) * 100.0F) / 2;
    
/* 407 */     return yRotationValuePercentage;
  }
  








  /**
   * Gets the hat switch position.
   *
   * @return the hat switch position
   */
  public float getHatSwitchPosition()
  {
/* 420 */     Component.Identifier identifier = Component.Identifier.Axis.POV;
/* 421 */     return this.controller.getComponent(identifier).getPollData();
  }
  
















  /**
   * Gets the x left joystick value.
   *
   * @return the x left joystick value
   */
  public float getX_LeftJoystick_Value()
  {
/* 442 */     return getXAxisValue();
  }
  









  /**
   * Gets the x left joystick percentage.
   *
   * @return the x left joystick percentage
   */
  public int getX_LeftJoystick_Percentage()
  {
/* 456 */     return getXAxisPercentage();
  }
  










  /**
   * Gets the y left joystick value.
   *
   * @return the y left joystick value
   */
  public float getY_LeftJoystick_Value()
  {
/* 471 */     return getYAxisValue();
  }
  









  /**
   * Gets the y left joystick percentage.
   *
   * @return the y left joystick percentage
   */
  public int getY_LeftJoystick_Percentage()
  {
/* 485 */     return getYAxisPercentage();
  }
  





  /**
   * Gets the x right joystick value.
   *
   * @return the x right joystick value
   */
  public float getX_RightJoystick_Value()
  {
    float xValueRightJoystick;
    




    




/* 507 */     if (this.controller.getType() == Controller.Type.STICK)
    {
/* 509 */       xValueRightJoystick = getZAxisValue();

    }
    else
    {
/* 514 */       xValueRightJoystick = getXRotationValue();
    }
    
/* 517 */     return xValueRightJoystick;
  }
  




  /**
   * Gets the x right joystick percentage.
   *
   * @return the x right joystick percentage
   */
  public int getX_RightJoystick_Percentage()
  {
    int xValueRightJoystickPercentage;
    



    



/* 536 */     if (this.controller.getType() == Controller.Type.STICK)
    {
/* 538 */       xValueRightJoystickPercentage = getZAxisPercentage();

    }
    else
    {
/* 543 */       xValueRightJoystickPercentage = getXRotationPercentage();
    }
    
/* 546 */     return xValueRightJoystickPercentage;
  }
  





  /**
   * Gets the y right joystick value.
   *
   * @return the y right joystick value
   */
  public float getY_RightJoystick_Value()
  {
    float yValueRightJoystick;
    



    



/* 566 */     if (this.controller.getType() == Controller.Type.STICK)
    {
/* 568 */       yValueRightJoystick = getZRotationValue();

    }
    else
    {
/* 573 */       yValueRightJoystick = getYRotationValue();
    }
    
/* 576 */     return yValueRightJoystick;
  }
  




  /**
   * Gets the y right joystick percentage.
   *
   * @return the y right joystick percentage
   */
  public int getY_RightJoystick_Percentage()
  {
    int yValueRightJoystickPercentage;
    



    



/* 595 */     if (this.controller.getType() == Controller.Type.STICK)
    {
/* 597 */       yValueRightJoystickPercentage = getZRotationPercentage();

    }
    else
    {
/* 602 */       yValueRightJoystickPercentage = getYRotationPercentage();
    }
    
/* 605 */     return yValueRightJoystickPercentage;
  }
}


/* Location:              C:\Users\finma\Desktop\JInputJoystickTest.jar!\joystick\JInputJoystick.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */