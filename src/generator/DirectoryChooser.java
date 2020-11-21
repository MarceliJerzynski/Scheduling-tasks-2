package generator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DirectoryChooser extends JPanel implements ActionListener {
    JButton go;

    JFileChooser chooser;
    boolean log;

    public DirectoryChooser(String buttonText) {
        this(buttonText, false);
    }

    public DirectoryChooser(String buttonText, boolean log) {
        go = new JButton(buttonText);
        go.addActionListener(this);
        add(go);
        this.log = log;
    }

    public File getDirectory() {
        try {
            return chooser.getSelectedFile();
        } catch (Exception e){
            System.out.println("You didn't select directory, bye bye");
            System.exit(1);
            return new File("");
        }
    }


    public void actionPerformed(ActionEvent e) {

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        var optionSelected = chooser.showOpenDialog(this);
        if (log) {
            if (optionSelected == JFileChooser.APPROVE_OPTION) {
                System.out.println("selected directory: "
                        + chooser.getCurrentDirectory());
                System.out.println("selected files: "
                        + chooser.getSelectedFile());
            } else {
                System.out.println("No selection");
            }
        }
    }

    public Dimension getPreferredSize(){
        return new Dimension(500, 200);
    }

}
