/*
 * GPL Licence
 * Voting @JMaghrebConf
 */
package io.jmaghreb.voting;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.trigger.GpioCallbackTrigger;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Naciri
 */
public class Pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            GpioPinDigitalInput myButton;
            GpioPinDigitalOutput myLed;

            GpioController myController = GpioFactory.getInstance();
            GpioController ledController = GpioFactory.getInstance();

            myButton = myController.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
            myLed = ledController.provisionDigitalOutputPin(RaspiPin.GPIO_07, "LED #1", PinState.LOW);

            myButton.addTrigger(new GpioSetStateTrigger(PinState.HIGH, myLed, PinState.HIGH));
            myButton.addTrigger(new GpioSetStateTrigger(PinState.LOW, myLed, PinState.LOW));

            /* myButton.addListener(new GpioPinListenerDigital() {
                
             @Override
             public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                    
             System.out.println("Hello");
                    
             }
             });
            
             System.out.println("Listener Ready");*/
            myButton.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
                public Void call() throws Exception {
                    System.out.println(" --> I LIKED IT ");
                    return null;
                }
            }));

            for (;;) {
                Thread.sleep(500);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Pi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
