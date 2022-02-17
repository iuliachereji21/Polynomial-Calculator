package org.ro.tuc.pt.controller;

import org.ro.tuc.pt.model.Operations;
import org.ro.tuc.pt.model.Polynomial;
import org.ro.tuc.pt.view.View;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;
    private String polynomial1;
    private String polynomial2;
    protected Object source;


    public Controller(View view)
    {
        this.view=view;

        int nrButtons=view.getNrButtons();
        for(int j=0;j<nrButtons;j++)
            view.addButtonListener(new ButtonsListener(),j);

        int nrFields=view.getNrFields() -1;
        for(int k=0;k<nrFields;k++)
            view.addFieldListener(new FieldListener(),k);
    }

    class FieldListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent e) {
            update(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            update(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            update(e);
        }

        public void update(DocumentEvent e)
        {
            Object sourceDocument = e.getDocument();
            if(sourceDocument==view.fields.get(0).getDocument()) //polynomial one
            {
                Controller.this.polynomial1 = view.fields.get(0).getText();
                if(polynomial1==null || polynomial1.isEmpty())
                    view.setExampleLabelsVisible(0,true);
                else view.setExampleLabelsVisible(0,false);
                return;
            }
            if(sourceDocument==view.fields.get(1).getDocument()) //polynomial two
            {
                Controller.this.polynomial2 = view.fields.get(1).getText();
                if(polynomial2==null || polynomial2.isEmpty())
                    view.setExampleLabelsVisible(1,true);
                else view.setExampleLabelsVisible(1,false);
                return;
            }
        }
    }

    class ButtonsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Controller.this.source=e.getSource();
            view.fields.get(2).setText(null); //the result field

            Thread thread = new Thread(new MyRunnable()); //to perform the computations needed in another thread
            thread.start();
        }
    }

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            Polynomial[] reading = Operations.readPolynomials(polynomial1,polynomial2);
            view.setWrongLabelVisible(false);
            if(source==view.buttons.get(0)){ //add
                if(reading[0]==null || reading[1]==null)
                    view.setWrongLabelVisible(true);
                else view.fields.get(2).setText(Operations.add(reading[0],reading[1]).toString());
            } else if(source==view.buttons.get(1)){ //subtract
                        if(reading[0]==null || reading[1]==null)
                            view.setWrongLabelVisible(true);
                        else view.fields.get(2).setText(Operations.subtract(reading[0],reading[1]).toString());
                    } else if(source==view.buttons.get(2)){ //multiply
                                if(reading[0]==null || reading[1]==null)
                                    view.setWrongLabelVisible(true);
                                else view.fields.get(2).setText(Operations.multiply(reading[0],reading[1]).toString());
                            } else if(source==view.buttons.get(3)){ //divide
                                        if(reading[0]==null || reading[1]==null || reading[1].toString().compareTo("0")==0)
                                            view.setWrongLabelVisible(true);
                                        else{
                                            Polynomial[] division = Operations.divide(reading[0],reading[1]);
                                            view.fields.get(2).setText("quotient: "+division[0].toString()+", remainder: "+division[1].toString());
                                        }
                                    } else if(source==view.buttons.get(4)){ //derivate
                                                if(reading[0]==null)
                                                    view.setWrongLabelVisible(true);
                                                else view.fields.get(2).setText(Operations.derivate(reading[0]).toString());
                                            } else if(source==view.buttons.get(5)){ //integrate
                                                        if(reading[0]==null)
                                                            view.setWrongLabelVisible(true);
                                                        else view.fields.get(2).setText(Operations.integrate(reading[0]).toString());
                                                    }
        }
    }
}
