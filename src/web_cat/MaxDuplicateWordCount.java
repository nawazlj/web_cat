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
import LBJ2.nlp.SentenceSplitter;
import LBJ2.nlp.WordSplitter;
import LBJ2.nlp.seg.PlainToTokenParser;
import LBJ2.nlp.seg.Token;
import LBJ2.parse.Parser;
import static com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker.check;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.Scanner;
 
public class MaxDuplicateWordCount {
     String[][] events_tex;
    private String check_temp;
    String[] final_events;
       FileOutputStream finaloutput = null;
       PrintStream psfinal = null;
       SimilarityCalculationDemo scd = new SimilarityCalculationDemo();
          
             public Map<String, Integer> getWordCount(String fileName){
 
        FileInputStream fis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            String line = null;
            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, " ");
                while(st.hasMoreTokens()){
                    String tmp = st.nextToken().toLowerCase();
                    if(wordMap.containsKey(tmp)){
                        wordMap.put(tmp, wordMap.get(tmp)+1);
                    } else {
                        wordMap.put(tmp, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return wordMap;
    }
     
    public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap){
         
        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list;
    }
     
    public void show() throws FileNotFoundException, IOException{
        FileOutputStream fileoutput = null;
        
            fileoutput = new FileOutputStream("filter_events.txt");
            PrintStream ps = new PrintStream(fileoutput);
              
   finaloutput=new FileOutputStream("final_events.txt");
             psfinal = new PrintStream(finaloutput);
        String[] entires;
        int[] first_arr;
        
        String line="",comp="";
        int first=0;
        int first_max=0,second_max=0;
       Map<String, Integer> eventmap = new HashMap<String, Integer>();
        MaxDuplicateWordCount mdc = new MaxDuplicateWordCount();
        Map<String, Integer> wordMap = mdc.getWordCount("events.txt");
        List<Entry<String, Integer>> list = mdc.sortByValue(wordMap);
        int total =0,i=1,j=0,k=0 ;
        int allocation = wordMap.size();
        events_tex = new String[allocation][allocation];
        entires = new String[allocation];
        first_arr = new int[allocation];
        final_events = new String[allocation];
              
//check = new String[allocation];
        for(Map.Entry<String, Integer> entry:list){
            total+=entry.getValue();
            if(i==1)
            {
            first=entry.getValue();
            i=0;
            }
            entires[j]=entry.getKey();
            first_arr[j]=entry.getValue();
            j++;
            eventmap.put(entry.getKey(),entry.getValue());
            ps.println("Name of the Event = "+entry.getKey()+"\t"+" Number of Occurence = "+entry.getValue());
           System.out.println(entry.getKey()+" "+entry.getValue());
        }
            
       /* for(int k=0;k<j;k++)
        {
            System.out.println(entires[k]);
        }*/
           //first_max = (total/first); 
          // second_max =((first_max*50)/100);
           //System.out.println(first_max+"   "+second_max);
           //int p=0,q=0,r=0;
           
           for(int m=0;m<j;m++)
           {
               for(int n=m+1;n<j;n++)
               {
                   if(first_arr[n]>1)
                   check(entires[m].toLowerCase(),entires[n].toLowerCase());
               }
           }
         
      /* while(eventmap.containsKey(entires[k]))
        {
             events[q][r]=entires[p];
             System.out.println(events[q][r]);
             p++;
             r++;
             comp=entires[p];
             if(events.length==p+1)
             {
                 p=p-1;
             }
             System.out.println(entires[p]);
            
            while((line=buf.nextLine())!=null)
            {
                System.out.println(line);
                StringTokenizer lines = new StringTokenizer(line);
                while(lines.hasMoreTokens())
                {
                    String temp_comp = lines.nextToken().toLowerCase();
               // System.out.println(temp_comp);
            if(temp_comp.equals(comp.toLowerCase())&& )
            {
                System.out.println(comp);
                System.out.println(events[q][r-1]);
               String pur=comp.concat(events[q][r-1]);
               r++;
                events[q][r]=pur;
                //System.out.println(events[q][r]);
                r++;
            }
           else
            {
               // System.out.println(temp_comp+""+comp);
            }
                 }
        }
            
            q++;
    }*/
           
        //System.out.println(events.length);
    }
    
    public void check(String arg1,String arg2) throws FileNotFoundException
         {
             //System.out.println(arg1+" "+arg2);
           
             int flag_arg1=0,flag_arg2=0; 
             Parser parser =
      new PlainToTokenParser(
          new WordSplitter(new SentenceSplitter("events.txt")));
             int q=0,s=1,z=1;
             final_events[0]="";     
             String senten = "";
             for (Token word = (Token) parser.next(); word != null;
         word = (Token) parser.next())
             {
                     check_temp = word.form.toLowerCase();
                     
                 if(check_temp!=null)
                 {
                     
                     if(arg1.equals(check_temp))
                     {
           //              System.out.println("arg1 = "+arg1 + "check_temp "+check_temp);
                         flag_arg1=1;
                     }
                     else 
                     {
                      if(arg2.equals(check_temp))
                      {
         //                System.out.println("arg2 = "+arg2 + "check_temp "+check_temp);
                          flag_arg2=1;
                      }
                     }
                 }
                 if(flag_arg1==1&&flag_arg2==1)
                     {
                        flag_arg1=0;
                         flag_arg2=0;
                        String temp_final = arg1+" "+arg2;
                        scd.run(arg1,arg2);
                        if(!temp_final.equals(final_events[s-1])||z==1)
                        {
                            if(z==1)
                            {
                            s=0;
                            }final_events[s]=temp_final;
                            psfinal.println(final_events[s]);
                            System.out.println(final_events[s]);
                            z=0;
                            s++;
                        }

                     }
                 if(word.next==null)
                 {
                     flag_arg1=0;
                     flag_arg2=0;
                 }
                    
                     
                        //flag_arg1=0;
                         //flag_arg2=0;
                         q++;
                 }
                 }
             
         
}