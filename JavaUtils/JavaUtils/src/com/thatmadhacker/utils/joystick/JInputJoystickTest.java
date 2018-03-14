package com.thatmadhacker.utils.joystick;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Version;








// TODO: Auto-generated Javadoc
/**
 * The Class JInputJoystickTest.
 */
public class JInputJoystickTest
{
  
  /**
   * Gets the all controllers info.
   *
   * @return the all controllers info
   */
  public void getAllControllersInfo()
  {
/*  22 */     System.out.println("JInput version: " + Version.getVersion());
/*  23 */     System.out.println("");
    

/*  26 */     Controller[] controllersList = ControllerEnvironment.getDefaultEnvironment().getControllers();
    

/*  29 */     for (int i = 0; i < controllersList.length; i++) {
/*  30 */       System.out.println(controllersList[i].getName());
    }
    

/*  34 */     for (int i = 0; i < controllersList.length; i++) {
/*  35 */       System.out.println("\n");
/*  36 */       System.out.println("-----------------------------------------------------------------");
      

/*  39 */       System.out.println(controllersList[i].getName());
      

/*  42 */       System.out.println("Type: " + controllersList[i].getType().toString());
      

/*  45 */       Component[] components = controllersList[i].getComponents();
/*  46 */       System.out.print("Component count: " + components.length);
/*  47 */       for (int j = 0; j < components.length; j++) {
/*  48 */         System.out.println("");
        

/*  51 */         System.out.println("Component " + j + ": " + components[j].getName());
        

/*  54 */         System.out.println("    Identifier: " + components[j].getIdentifier().getName());
/*  55 */         System.out.print("    ComponentType: ");
/*  56 */         if (components[j].isRelative()) {
/*  57 */           System.out.print("Relative");
        } else {
/*  59 */           System.out.print("Absolute");
        }
/*  61 */         if (components[j].isAnalog()) {
/*  62 */           System.out.print(" Analog");
        } else {
/*  64 */           System.out.print(" Digital");
        }
      }
/*  67 */       System.out.println("\n");
/*  68 */       System.out.println("-----------------------------------------------------------------");
    }
  }
  





  /**
   * Poll controller and its components.
   *
   * @param controllerType the controller type
   */
  public void pollControllerAndItsComponents(Controller.Type controllerType)
  {
/*  79 */     Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
    

/*  82 */     Controller firstController = null;
    
/*  84 */     for (int i = 0; (i < controllers.length) && (firstController == null); i++) {
/*  85 */       if (controllers[i].getType() == controllerType)
      {
/*  87 */         firstController = controllers[i];
/*  88 */         break;
      }
    }
    
/*  92 */     if (firstController == null)
    {
/*  94 */       System.out.println("Found no desired controller!");
/*  95 */       System.exit(0);
    }
    
/*  98 */     System.out.println("First controller of a desired type is: " + firstController.getName());
    for (;;)
    {
/* 101 */       firstController.poll();
/* 102 */       Component[] components = firstController.getComponents();
/* 103 */       StringBuffer buffer = new StringBuffer();
/* 104 */       for (int i = 0; i < components.length; i++) {
/* 105 */         if (i > 0) {
/* 106 */           buffer.append(", ");
        }
/* 108 */         buffer.append(components[i].getName());
/* 109 */         buffer.append(": ");
/* 110 */         if (components[i].isAnalog()) {
/* 111 */           buffer.append(components[i].getPollData());
        }
/* 113 */         else if (components[i].getPollData() == 1.0F) {
/* 114 */           buffer.append("On");
        } else {
/* 116 */           buffer.append("Off");
        }
      }
      
/* 120 */       System.out.println(buffer.toString());
      try
      {
/* 123 */         Thread.sleep(20L);
      } catch (InterruptedException e) {
/* 125 */         e.printStackTrace();
      }
    }
  }
}


/* Location:              C:\Users\finma\Desktop\JInputJoystickTest.jar!\joystick\JInputJoystickTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */