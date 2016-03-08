/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDE;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;


public class DescriptiveErrorListener extends BaseErrorListener {
    public static DescriptiveErrorListener INSTANCE = new DescriptiveErrorListener();
    private static final boolean REPORT_SYNTAX_ERRORS = true;
    public static String errores = new String();
    
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
        String sourceName = recognizer.getInputStream().getSourceName();
        
        String error = sourceName+"line "+line+":"+charPositionInLine+" "+msg;
        errores += error+"\n";
        System.err.println(error);
    }
    
    public String getErrores() {
        return errores; 
    }
    
    public void setErrores(String error){
        errores = error; 
    }
}