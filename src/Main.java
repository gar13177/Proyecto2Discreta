
/*
* KeyEventDemo
*/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.TreeSet;
import javax.swing.*;

public class Main extends JFrame
        implements KeyListener,
        ActionListener
{
    JTextArea displayArea;
    JTextField typingArea;
    ReadXML archivo = new ReadXML();
    TreeSet palabras = archivo.getPalabras();
    static final String newline = System.getProperty("line.separator");
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        
        //Create and set up the window.
        Main frame = new Main("Diccionario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane();
        
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    private void addComponentsToPane() {
        
        JButton button = new JButton("Clear");
        button.addActionListener(this);
        button.setActionCommand("boton");
        
        JButton change = new JButton("Change");
        change.addActionListener(this);
        change.setActionCommand("change");
        
        
        typingArea = new JTextField(20);
        typingArea.addKeyListener(this);
        
        //Uncomment this if you wish to turn off focus
        //traversal.  The focus subsystem consumes
        //focus traversal keys, such as Tab and Shift Tab.
        //If you uncomment the following line of code, this
        //disables focus traversal and the Tab events will
        //become available to the key event listener.
        typingArea.setFocusTraversalKeysEnabled(false);
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setPreferredSize(new Dimension(375, 125));
        
        getContentPane().add(typingArea, BorderLayout.PAGE_START);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(button, BorderLayout.PAGE_END);
        getContentPane().add(change, BorderLayout.EAST);
        displayArea.append("Busqueda inclusiva\n");
    }
    
    public Main(String name) {
        super(name);
    }
    
    
    /** Handle the key typed event from the text field. */
    public void keyTyped(KeyEvent e) {
        //displayInfo(e, "KEY TYPED: ");
    }
    
    /** Handle the key pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        //displayInfo(e, "KEY PRESSED: ");
    }
    
    /** Handle the key released event from the text field. */
    public void keyReleased(KeyEvent e) {
        //displayInfo(e, "KEY RELEASED: ");
        if(palabras.contains(typingArea.getText()))
            displayArea.append("Si exite\n");
            //jLabel1.setText("Si existe");
        else
            displayArea.append(archivo.getNear(typingArea.getText())+"\n");
            //jLabel1.setText("No existe");
    }
    
    /** Handle the button click. */
    public void actionPerformed(ActionEvent e) {
        String temp = e.getActionCommand();
        
        if (temp.equals("boton")){
            //Clear the text components.
            displayArea.setText("");
            typingArea.setText("");
        
            //Return the focus to the typing area.
            typingArea.requestFocusInWindow();
        }else if (temp.equals("change")){
            archivo.change();
            if (archivo.getNum()==1)  displayArea.append("Busqueda inclusiva\n");
            else displayArea.append("Busqueda exacta\n");
        }
        
    }
}
