
package cs421;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
/* we need to do some grammar check which includes
 *  1.a  spelling mistakes
 *  1.b  subject-verb agreement
 *  1.c  verb tense
 *  1.d sentence formation
 *  
 */

public class Grammar {
	private static final Grammar Grammarinstance= new Grammar();
	JLanguageTool langTool ;
	 
	static POSModel model;
	private Grammar()
	{
		langTool = ShareInstance.getJLanguageTool();
		 
		model=ShareInstance.getPOSModel() ;
	}
	
	public static Grammar getInstance() {
		return Grammarinstance;
	}
	

    public void getGrammarScore(Essay essay)throws IOException{
    	for(String s:essay.getEssay()){
    		getSentenceScore(s, essay.getResultObject());
    	}
     
    	
    }
	
    private static void printlog(String s) {
         	System.out.println(s);
    }
    public void getSentenceScore(String sentence, EssayResult essayR) throws IOException{
       	
    	// get the word POS tag, chunk information and Span information
    	chunkResult CR=new chunkResult();
    	getChunkPOS(sentence, CR );
    	// using third part tool for spelling check
    	SentenceSpellingCheck(sentence, essayR);
    	// use some user defined rule for grammar check 
    	SentenceSubAgree(CR, 0, essayR);
    	// use some rule for error type 1.c
    	SentenceVerbCheck(CR, 0, essayR);
	
    }



	// score 1.a
	public void SentenceSpellingCheck(String sentence, EssayResult essayR) throws IOException
	{    // sentence=" He have all my docments .";
		List<RuleMatch> matches = langTool.check(sentence);
		 
		for (RuleMatch match : matches) {
		 if(	match.getMessage().contains("spelling mistake")
				 && !match.getSuggestedReplacements().toString().contains("[SS, is")
				 ) {
			   printlog("Potential error at line " +
			     match.getLine() + ", column " +
			      match.getColumn() + ": " + match.getMessage());
			  printlog("Suggested correction: " +
			     match.getSuggestedReplacements());
			 essayR.addResult("1.a"); 
		 }

		}
		
	}
	
	
	private void checkNPvsVP(String[] sentencePOS,
			String[] sentenceChunk, Span[] sentenceSpan, int NPin, int VPin,  EssayResult essayR){

		for(int i=sentenceSpan[VPin].getStart();i<sentenceSpan[VPin].getEnd(); i++){
			if(sentencePOS[i].contains("VB")){
				// verb is VBZ
				if(sentencePOS[i].contains("VBZ")) {
			         for(int j=sentenceSpan[NPin].getStart();j<sentenceSpan[NPin].getEnd(); j++){
			         		
			        	  
						}	
					
				}
				else if(sentencePOS[i].contains("VBP")) {
			         for(int j=sentenceSpan[NPin].getStart();j<sentenceSpan[NPin].getEnd(); j++){
							
			        	  
						}
					
					
				}
				
				
	 
				
			}
		 
			
			
		}
		
	
		
	}
	
	private void checkNPvsVPcase3(String[] sentencePOS,
			String[] sentenceChunk, Span[] sentenceSpan, int NPin, int VPin,  EssayResult essayR){
		// check for case 3  neither or and or nor
		
		
		
	}
	
	
	private void SentenceSubCheckNPVP(String[] sentencePOS,
			String[] sentenceChunk, Span[] sentenceSpan, int beg, int end,  EssayResult essayR){
	if(sentenceSpan[beg].toString().contains("SBAR")
					||sentenceSpan[beg].toString().contains("SBARQ"))	{beg++;}
		
		// case 1 NP VP
	
	   if((beg+1<sentenceSpan.length)&&sentenceSpan[beg].toString().contains("NP")
			   &&sentenceSpan[beg+1].toString().contains("VP")){
		   printlog("check NP VP ___ case 1");
		   checkNPvsVP(sentencePOS, sentenceChunk, sentenceSpan,  beg, beg+1,  essayR);   
		   
	   } 
	    // case 2 NP PP NP VP
	   else if((beg+2<sentenceSpan.length)&&sentenceSpan[beg].toString().contains("NP")
			   &&sentenceSpan[beg+1].toString().contains("PP")
			   &&(sentenceSpan[beg+2].toString().contains("NP")||sentenceSpan[beg+2].toString().contains("PP"))
			   ){
		   printlog("check NP PP NP VP ___ case 2");   
		checkNPvsVP(sentencePOS, sentenceChunk, sentenceSpan,  beg, end,  essayR);  
		   
	   }
	   // case 3 NP NP VP without CC words  and  case 4 NP NP VP with CC words
	   else if((beg+2<sentenceSpan.length)&&sentenceSpan[beg].toString().contains("NP")
			   &&sentenceSpan[beg+1].toString().contains("NP")
			   &&sentenceSpan[beg+2].toString().contains("VP")
			   ){
		   printlog("check NP NP VP ___ case 3");
		   checkNPvsVPcase3(sentencePOS, sentenceChunk, sentenceSpan,  beg, beg+2,  essayR);  
		   
	   }  
	   
	
	  
		
		
	}
	
	// score 1.b
	private void SentenceSubCheckWithoutHead(String[] sentencePOS,
			String[] sentenceChunk, Span[] sentenceSpan, int beg,  EssayResult essayR){
	 
		int i=beg;
		for(; i<sentencePOS.length; i++) {
			if (sentencePOS[i].contains("_VB")) break; 			
			}
		
		if(( i < sentencePOS.length && !sentencePOS[i].contains("_VB"))
				||(i >= sentencePOS.length)) return; 
		// we need find the start point of the span  
		int spanbeg=0;
		
		for(; spanbeg<sentenceSpan.length; spanbeg++) {
			if(sentenceSpan[spanbeg].getStart()==beg) break;
		}
		if(spanbeg>=sentenceSpan.length) return;
		String[] pos=sentencePOS;
		if(pos[beg].contains("_VB")||pos[beg].contains("_W")){}
		else{
		 // always NP --> VP
		 
			if (!(sentenceSpan[spanbeg].toString().contains("NP")
					||sentenceSpan[spanbeg].toString().contains("SBAR")
					||sentenceSpan[spanbeg].toString().contains("SBARQ"))){
	printlog("SentenceSubCheckWithoutHead for type 1.c  " + pos[beg]+"   "+sentenceSpan[spanbeg]);
				essayR.addResult("1.c"); return;
			}
			else {
				
			
				int start=spanbeg;
				for(int j=spanbeg; j<sentenceSpan.length; j++){
					if(sentenceSpan[j].toString().contains("VP")){
						
    	SentenceSubCheckNPVP(sentencePOS,sentenceChunk, sentenceSpan, start,j, essayR);
    	start=j+1;
					}	
					
					
				}
				
				
				
			}
			
			
		}
		
		
		
		
	}
	
	public void SentenceSubAgree(chunkResult CR, int beg, EssayResult essayR){
		SentenceSubAgree(CR.sentencePOS, CR.sentenceChunk, CR.sentenceSpan, beg, essayR);
	}
	
	
	private void checkSubAgreeRule1(String[] sentencePOS,
		String[] sentenceChunk, Span[] sentenceSpan, int beg,  EssayResult essayR){
		int i=beg;
		String[] pos=sentencePOS;
		if(pos[beg].contains("_VB")){
            
			
			 if(pos[beg].toLowerCase().contains("does_vb")||
					 pos[beg].toLowerCase().contains("has_vb")||
					 pos[beg].toLowerCase().contains("do_vb")
					 ||pos[beg].toLowerCase().contains("have_vb")) {
				 // we find a singular auxiliary verbs
				 // find the real verb and NB
				 int vi=-1, ni=-1;
				 for( i=beg; i<sentenceSpan.length; i++){
				//	 System.out.println("the index i " + i + "   "+ sentenceSpan[i].toString() );
					if(sentenceSpan[i].toString().contains("NP")&&vi==-1) {vi=i;}
					if(sentenceSpan[i].toString().contains("VP")&&ni==-1) {ni=i;} 
				 }
				//	System.out.println("the index ni " + ni + " vi "+ vi + " beg " + beg);
				 if(ni==-1 || vi==-1) {
					 // need to detemine
					 printlog("checkSubAgreeRule1 for type 1.c -----1");
					 essayR.addResult("1.c");
					 // something similar to "do it as soon as possible!"
					    return ;
					 }
				
		
			if(pos[beg].toLowerCase().contains("does")||pos[beg].toLowerCase().contains("has")) {
			      for( i=sentenceSpan[vi].getStart(); i<sentenceSpan[vi].getEnd();i++){
							if(pos[i].contains("NNS")||pos[i].contains("NNPS")
									||pos[i].toLowerCase().contains("you_prp")
									||pos[i].toLowerCase().contains("i_prp")
									||pos[i].toLowerCase().contains("they_prp")
									) {
		 printlog(" Case 1======================"+ pos[i] + "==== " +i );
								essayR.addResult ("1.b") ; break;
							}
						 }	 
				 }	
		    else {
		for( i=sentenceSpan[vi].getStart(); i<sentenceSpan[vi].getEnd();i++){
							if((pos[i].contains("NN") && !(pos[i].contains("NNS")
									||pos[i].contains("NNPS")))
									||pos[i].toLowerCase().contains("he_prp")) {
			printlog(" Case 2======================" );					
								essayR.addResult ("1.b") ; break;
							}
						 }		 
				 }
				

	SentenceSubCheckWithoutHead(sentencePOS, sentenceChunk, sentenceSpan, sentenceSpan[ni].getEnd(), essayR);
				 
			    }	
			 else if(pos[beg].toLowerCase().contains("is_vb") 
					 || pos[beg].toLowerCase().contains("are_vb")) {
				 int ni=-1;
				 for( i=beg; i<sentenceSpan.length; i++){
					if(sentenceSpan[i].toString().contains("NP")&&ni==-1) {ni=i; break;}
				 }
		 
				 if(ni==-1) {
					 // can not find anything to match. format is wrong
					 printlog("checkSubAgreeRule1 for type 1.c -----2");
					  essayR.addResult("1.c");
					  return;
				 }
				 
				 if(pos[beg].toLowerCase().contains("is")) {
				     for( i=sentenceSpan[ni].getStart(); i<sentenceSpan[ni].getEnd();i++){
							if(pos[i].contains("NNS")||pos[i].contains("NNPS")
									||pos[i].toLowerCase().contains("you_prp")
									||pos[i].toLowerCase().contains("i_prp")
									||pos[i].toLowerCase().contains("they_prp")
									) {
			printlog(" Case 3======================"+ pos[i] + "==== " +i );
								 essayR.addResult ("1.b") ; break;
							}
						 }	 
					 
					 
				 }
				 else {
					 
					 for( i=sentenceSpan[ni].getStart(); i<sentenceSpan[ni].getEnd();i++){
						 if((pos[i].contains("NN") && !(pos[i].contains("NNS")||pos[i].contains("NNPS")))
								 ||pos[i].toLowerCase().contains("he_prp")) {
		 printlog(" Case 4======================"+ pos[i] + "==== " +i );
								 essayR.addResult ("1.b") ; break;
							}
						 }	
					 
					 
					 
				 }
				 
				 
				 
			    }

			}
		
	}
	private void checkSubAgreeRule2(String[] sentencePOS,
			String[] sentenceChunk, Span[] sentenceSpan, int beg,  EssayResult essayR) {
		int i=beg;
	
		String[] pos=sentencePOS;
		// check the auxiliary verbs

		// check two special case  start with do and how
         if(pos[beg].toLowerCase().contains("what")||pos[beg].toLowerCase().contains("how")
					||pos[beg].toLowerCase().contains("when")||pos[beg].toLowerCase().contains("where")
					||pos[beg].toLowerCase().contains("who")||pos[beg].toLowerCase().contains("whose")
					||pos[beg].toLowerCase().contains("why")||pos[beg].toLowerCase().contains("which")
					){
        	 // VP ~ NP
        	 int ni=-1, vi=-1;
			 for( i=beg+1; i<sentenceSpan.length; i++){
				if(sentenceSpan[i].toString().contains("NP")&&ni==-1) {ni=i;}
				if(sentenceSpan[i].toString().contains("VP")&&vi==-1) {vi=i;} 
			 }
			 if(ni==-1 || vi==-1 || vi>ni) {
				 printlog("checkSubAgreeRule2 for type 1.c -----1");
				 essayR.addResult("1.c");
				 // something similar to "do it as soon as possible!"
				    return ;
				 }
		   // check NP and VP
			 int NPflag=1, VPflag=1;
		     for( i=sentenceSpan[ni].getStart(); i<sentenceSpan[ni].getEnd();i++){
						if(pos[i].contains("NNS")||pos[i].contains("NNPS")
								||pos[i].toLowerCase().contains("you_prp")
								||pos[i].toLowerCase().contains("they_prp")
								||pos[i].toLowerCase().contains("i_prp")	) {
							NPflag=2;
						}
					 }	
		     for( i=sentenceSpan[vi].getStart(); i<sentenceSpan[vi].getEnd();i++){
					if(pos[i].contains("VBZ")) { NPflag=1; 	}
					else  {
						 VPflag=2; 	
					}
				 }	
			 // find a error
		     if(NPflag>VPflag){
		    	 printlog("checkSubAgreeRule2 for type 1.b -----1");
		    	 essayR.addResult ("1.b") ;
		     }
		     i=ni+1;
        	 vi=-1;
			 for( ; i<sentenceSpan.length; i++){
				if(sentenceSpan[i].toString().contains("VP")&&vi==-1) {vi=i; break;} 
			 }
			 if(vi==-1) {return;}
			 else {
				 
		  SentenceSubCheckWithoutHead(sentencePOS, sentenceChunk, sentenceSpan, sentenceSpan[vi].getEnd(), essayR);		 
			 }
        	 
			
		}
		
	}
	
	private void SentenceSubAgree(String[] sentencePOS,
			String[] sentenceChunk, Span[] sentenceSpan, int beg,  EssayResult essayR) {
		// case 0, check the VP in the sentence
       int i=beg;
		for( ;i<sentencePOS.length; i++) {
		
			if (sentencePOS[i].contains("VB")) break; 			
			}
		if( i < sentencePOS.length && !sentencePOS[i].contains("VB")) { 
			 printlog("checkSubAgree for type 1.c -----1");
			essayR.addResult("1.c"); return;} 
		
		 
          
	 	checkSubAgreeRule1(sentencePOS, sentenceChunk, sentenceSpan, beg, essayR);
	 	checkSubAgreeRule2(sentencePOS, sentenceChunk, sentenceSpan, beg, essayR); 
        SentenceSubCheckWithoutHead(sentencePOS, sentenceChunk, sentenceSpan, beg, essayR);	 
        	 
 	
		
		 
	}
 
	
	
	
	// score 1.c
	
	public void SentenceVerbCheck(chunkResult CR, int beg, EssayResult essayR) {
		
		
		
		
	}
	
	
	public static void getChunkPOS(String input, chunkResult CR) {
		InputStream is;
		ChunkerModel cModel;
		ChunkerME chunkerME;
			try {
				is = new FileInputStream("en-chunker.bin");
				try {
					cModel = new ChunkerModel(is);
					 chunkerME = new ChunkerME(cModel);
					 PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
					 
					 POSTaggerME tagger = new POSTaggerME(model);
					 ObjectStream<String> lineStream = new PlainTextByLineStream(
								new StringReader(input));
					 
						perfMon.start();
						String line;
						String whitespaceTokenizerLine[] = null;
					 
						String[] tags = null;
						while ((line = lineStream.read()) != null) {
							whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
									.tokenize(line);
							tags = tagger.tag(whitespaceTokenizerLine);
					 
							 POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
							 CR.sentencePOS=sample.toString().split(" ");
						 	printlog(sample.toString());
						 
								perfMon.incrementCounter();
						}
					  //	perfMon.stopAndPrintFinalResult();
					 
					 
						String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
						 
						for (String s : result)
							    printlog(s);
						//System.out.println("Result end ---------------------");
						Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
						for (Span s : span)
							   printlog(s.toString());
				        CR.sentenceSpan=span;
				        CR.sentenceChunk=result;
					 
				} catch (InvalidFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	
}

