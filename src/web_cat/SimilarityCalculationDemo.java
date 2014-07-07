/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web_cat;

/**
 *
 * @author nawaz
 */
import edu.cmu.lti.lexical_db.ILexicalDatabase;
import edu.cmu.lti.lexical_db.NictWordNet;
import edu.cmu.lti.ws4j.RelatednessCalculator;
import edu.cmu.lti.ws4j.impl.WuPalmer;
import edu.cmu.lti.ws4j.util.WS4JConfiguration;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimilarityCalculationDemo {
       
        private static ILexicalDatabase db = new NictWordNet();
        private static RelatednessCalculator[] rcs = {new WuPalmer(db),};
        PrintWriter out = null;
       
        public void run( String word1, String word2 ) {
                WS4JConfiguration.getInstance().setMFS(true);
                for ( RelatednessCalculator rc : rcs ) {
                        double s = rc.calcRelatednessOfWords(word1, word2);
                        if(s>=0.9)
                        {
                            try {
                                out = new PrintWriter(new FileWriter("word_similar.txt"),true);
                            } catch (IOException ex) {
                                Logger.getLogger(SimilarityCalculationDemo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        System.out.println( rc.getClass().getName()+"\t"+s );
                }
        }
        
}
