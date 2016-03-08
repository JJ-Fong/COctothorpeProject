/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDE;

import Grammar.expDecaf2Lexer;
import Grammar.expDecaf2Parser;
import Grammar.expDecaf2Parser.ProgramContext;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.TreeViewer;

/**
 *
 * @author Javier Fong
 */
public class mainView extends javax.swing.JFrame {
    //ELEMENTOS DEL GUI
    private JSplitPane verticalPane; 
    private JSplitPane horizontalPane; 
    public JTabbedPane inferior; 
    public JTabbedPane lateral; 
    
    public JMenuBar menu;
    JMenu fileMenu; 
    JMenu runMenu;
    JMenuItem open;
    JMenuItem save;
    JMenuItem run;
    
    //Paneles
    public JScrollPane sintaxTree, graphicTree, errors, symbolTable, methodTable, structTable;   
    public codePane code;
    //Programa
    String programText; 
    String filePath; 
    
    JTree arbol; 
    TreeViewer view; 
    
    ParseToList ptl; 
    
    public mainView() {
        initializeGUI(); 
    }
    
    private void initializeGUI() { 
        this.setSize(1800, 1000);
        
        menu = new JMenuBar(); 
        fileMenu = new JMenu();
        fileMenu.setText("File");
        runMenu = new JMenu(); 
        runMenu.setText("Run");
        
        open = new JMenuItem();
        open.setText("Open file");
        save = new JMenuItem();
        save.setText("Save file");
        run = new JMenuItem();
        run.setText("Compile");
        
        
        menu.add(fileMenu);
        menu.add(runMenu); 
        
        fileMenu.add(open);
        fileMenu.add(save);
        runMenu.add(run);
        
         
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFile();
            }
        });
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFile(programText);
            }
        });
        run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parse();
            }
        });
        filePath = "";

        this.setJMenuBar(menu);
        
        verticalPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT); 
        horizontalPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        inferior = new JTabbedPane(); 
        lateral = new JTabbedPane();
        
        code = new codePane(); 
        sintaxTree = new JScrollPane(); 
        graphicTree = new JScrollPane(); 
        errors = new JScrollPane(); 
        symbolTable = new JScrollPane(); 
        methodTable = new JScrollPane(); 
        structTable = new JScrollPane(); 
        
        inferior.addTab("Errores", errors);
        inferior.addTab("Tabla de Simbolos", symbolTable);
        inferior.addTab("Tabla de Metodos", methodTable);
        inferior.addTab("Tabla de Estructuras", structTable);
        
        lateral.addTab("Arbol Sintactico", sintaxTree);
        lateral.addTab("Arbol Grafico", graphicTree);
        
        horizontalPane.setDividerLocation(900);
        verticalPane.setDividerLocation(700);
        
        this.add(verticalPane, BorderLayout.CENTER);
        
        horizontalPane.setLeftComponent(code);
        horizontalPane.setRightComponent(lateral);
        verticalPane.setRightComponent(inferior);
        verticalPane.setLeftComponent(horizontalPane);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1063, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
        
    public String openFile() {
        String aux; 
        try {
            JFileChooser file=new JFileChooser("C:\\Users\\Javier Fong\\Documents\\Universidad\\2016\\Ciclo 1\\Clases\\Compiladores\\Pruebas\\");
            file.showOpenDialog(null);
            File abre = file.getSelectedFile();

            if(abre!=null) {   
                programText = "";
                filePath = abre.getAbsolutePath();
                System.out.println(filePath);
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                while((aux=lee.readLine())!=null){
                    programText += (aux+"\n");
                }
                code.setText(programText);
            }
        }
        catch(Exception ex){
            System.out.println("File not Found");
        }
        return filePath; 
    }
    
    public boolean saveFile(String text) {
        boolean flag = false;
        programText = code.getText();
        if (filePath.equals("")) { 
            JFileChooser fileSaver = new JFileChooser("C:\\Users\\Javier Fong\\Documents\\Universidad\\2016\\Ciclo 1\\Clases\\Compiladores\\Pruebas\\");
            fileSaver.setDialogTitle("SAVE FILE");
            int userSelection = fileSaver.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileSaver.getSelectedFile();
                filePath = fileToSave.getAbsolutePath();
                try {
                    PrintWriter writer = new PrintWriter(filePath,"UTF-8");
                    writer.println(programText);
                    writer.close();
                    System.out.println("SAVE SUCCESFUL");
                    flag = true; 
                } catch (Exception e) {
                    System.out.println("SAVE UNSUCCESFUL");
                }
            } 
        } else {
            try {
                PrintWriter writer = new PrintWriter(filePath,"UTF-8");
                writer.println(programText);
                writer.close();
                System.out.println("SAVE SUCCESFUL");
                flag = true; 
            } catch (Exception e) {
                System.out.println("SAVE UNSUCCESFUL");
            }
        }
        return flag; 
    }
    
    public void parse() { 
        programText = code.getText();
        ANTLRInputStream input = new ANTLRInputStream(programText);
        expDecaf2Lexer lexer = new expDecaf2Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        expDecaf2Parser parser = new expDecaf2Parser(tokens);
        
        parser.removeErrorListeners();
        
        DescriptiveErrorListener del = new DescriptiveErrorListener(); 
        del.setErrores("");
        parser.addErrorListener(del);
        
        ProgramContext tree = parser.program();
        
        String mstk = "";
        mstk = del.getErrores();
        JTextArea errores = new JTextArea();
        errores.setEnabled(false);
        if (mstk.equals("")) { 
            mstk = "Program Parceable";
            errores.setText(mstk);
        
            view = new TreeViewer(Arrays.asList(
            parser.getRuleNames()),tree);
            view.setScale(1.5);
            ParseTree hijo = tree.getChild(0);
            ParseTree arbolGen = hijo.getParent();

            TreeBuilder tb = new TreeBuilder(); 
            arbol = tb.crearArbol(arbolGen, parser); 

            sintaxTree.setViewportView(arbol);
            graphicTree.setViewportView(view);
            errors.setViewportView(errores); 
        
            ptl = new ParseToList(arbolGen,parser); 
            myNode list = ptl.getList();
            SymbolTable st = new SymbolTable(list); 
        } else {
            errores.setText(mstk);
            sintaxTree.setViewportView(errores);
            graphicTree.setViewportView(errores);
            errors.setViewportView(errores); 
        
        }
        

        
        
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
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new mainView();
            
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

