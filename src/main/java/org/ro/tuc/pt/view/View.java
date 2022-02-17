package org.ro.tuc.pt.view;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class View extends JFrame {
    private int nrButtons;
    private int nrFields;
    public ArrayList<JButton> buttons;
    public ArrayList<JTextField> fields;
    private JLabel wrongLabel;
    private ArrayList<JLabel> exampleLabels;

    public View(String title, int x, int y, int width, int height)
    {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(x,y,width,height);
        this.setLayout(null);
        this.setResizable(false);

        JPanel onlyPanel = new JPanel();
        onlyPanel.setLayout(null);
        this.add(onlyPanel);
        onlyPanel.setBackground(Color.BLACK);
        onlyPanel.setBounds(0,0,width, height);


        nrButtons=6;
        int buttonWidth=250, buttonHeight=100, firstx=85, firsty=250;
        int nrLabels = 3, labelWidth=250, labelHeight=25, firstxLabel=85, firstyLabel=80;
        nrFields=3;
        int fieldWidth=530, fieldHeight = 50, xField=365, firstyField=68;

        JLabel[] labels = new JLabel[nrLabels];
        for(int i = 0; i< nrLabels; i++)
        {
            labels[i]=new JLabel();
            labels[i].setFont(new Font("TimesRoman",20,labelHeight));
            labels[i].setForeground(Color.WHITE);
            onlyPanel.add(labels[i]);
        }

        labels[0].setText("First polynomial:");
        labels[0].setBounds(firstxLabel,firstyLabel,labelWidth,labelHeight);
        labels[1].setText("Second polynomial:");
        labels[1].setBounds(firstxLabel,160,labelWidth,labelHeight);
        labels[2].setText("Result:");
        labels[2].setBounds(firstxLabel,550,100,labelHeight);


        wrongLabel= new JLabel();
        wrongLabel.setFont(new Font("TimesRoman",20,labelHeight));
        wrongLabel.setForeground(Color.RED);
        onlyPanel.add(wrongLabel);
        wrongLabel.setText("*Incorrect form of input, please try again");
        wrongLabel.setBounds(xField,firstyField-30,fieldWidth,labelHeight);
        wrongLabel.setVisible(false);

        exampleLabels = new ArrayList<JLabel>();
        JLabel exampleLabel=new JLabel();
        exampleLabel.setFont(new Font("TimesRoman",20,labelHeight));
        exampleLabel.setForeground(Color.LIGHT_GRAY);
        exampleLabels.add(exampleLabel);
        onlyPanel.add(exampleLabel);
        JLabel exampleLabel2=new JLabel();
        exampleLabel2.setFont(new Font("TimesRoman",20,labelHeight));
        exampleLabel2.setForeground(Color.LIGHT_GRAY);
        exampleLabels.add(exampleLabel2);
        onlyPanel.add(exampleLabel2);
        exampleLabels.get(0).setText("x^2 + 3x^-1 - x + 7");
        exampleLabels.get(0).setBounds(xField+2,firstyField,fieldWidth,fieldHeight);
        exampleLabels.get(1).setText("- 4x^4 + 6x - 8");
        exampleLabels.get(1).setBounds(xField+2,firstyField+fieldHeight+30,fieldWidth,fieldHeight);

        fields = new ArrayList<JTextField>();

        for(int i=0;i<nrFields;i++)
        {
            JTextField field=new JTextField();
            field.setFont(new Font("TimesRoman",20,labelHeight));
            field.setForeground(Color.WHITE);
            field.setOpaque(false);
            fields.add(field);
            onlyPanel.add(field);
        }

        fields.get(0).setBounds(xField,firstyField,fieldWidth,fieldHeight);
        fields.get(1).setBounds(xField,firstyField+fieldHeight+30,fieldWidth,fieldHeight);
        fields.get(2).setBounds(xField-150,538,fieldWidth+150,fieldHeight);

        buttons = new ArrayList<JButton>();
        for(int i=0;i<nrButtons;i++)
        {
            JButton button=new JButton();
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("TimesRoman",20,20));
            buttons.add(button);
            onlyPanel.add(button);
        }

        buttons.get(0).setText("ADD");
        buttons.get(0).setBounds(firstx,firsty,buttonWidth,buttonHeight);
        buttons.get(1).setText("SUBTRACT");
        buttons.get(1).setBounds(firstx+buttonWidth+30,firsty,buttonWidth,buttonHeight);
        buttons.get(2).setText("MULTIPLY");
        buttons.get(2).setBounds(firstx+2*buttonWidth+60,firsty,buttonWidth,buttonHeight);
        buttons.get(3).setText("DIVIDE");
        buttons.get(3).setBounds(firstx,firsty+buttonHeight+30,buttonWidth,buttonHeight);
        buttons.get(4).setText("DERIVATE");
        buttons.get(4).setBounds(firstx+buttonWidth+30,firsty+buttonHeight+30,buttonWidth,buttonHeight);
        buttons.get(5).setText("INTEGRATE");
        buttons.get(5).setBounds(firstx+2*buttonWidth+60,firsty+buttonHeight+30,buttonWidth,buttonHeight);


    }

    public void addButtonListener(ActionListener listener, int nrOfTheButton)
    {
        if(nrOfTheButton<nrButtons)
            buttons.get(nrOfTheButton).addActionListener(listener);
    }

    public void addFieldListener(DocumentListener listener, int nrOfTheField)
    {
        if(nrOfTheField<nrFields)
            fields.get(nrOfTheField).getDocument().addDocumentListener(listener);
    }

    public int getNrButtons() {
        return nrButtons;
    }

    public int getNrFields() {
        return nrFields;
    }

    public void setWrongLabelVisible (boolean visible)
    {
        this.wrongLabel.setVisible(visible);
    }

    public void setExampleLabelsVisible(int nrOfTheLabel, boolean visible) {
        exampleLabels.get(nrOfTheLabel).setVisible(visible);
    }
}
