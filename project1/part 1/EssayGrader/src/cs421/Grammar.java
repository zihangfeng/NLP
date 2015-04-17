
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
	

    public void getGrammarScore(Essay essay){
    	
    }
	
    public void getSentenceScore(String sentence, EssayResult essayR) throws IOException{
    	// using third part tool for spelling check
    	this.SentenceSpellingCheck(sentence, essayR);
    	// get the word POS tag, chunk information and Span information
    	String[] sentencePOS = null;
		String[] sentenceChunk=null;
		Span[] sentenceSpan=null;
		this.getChunkPOS(sentence, sentencePOS, sentenceChunk, sentenceSpan );
    	this.SentenceSubAgree(sentencePOS,sentenceChunk,sentenceSpan, essayR);
    	this.SentenceVerbCheck(sentence, essayR);
    }



	// score 1.a
	public void SentenceSpellingCheck(String sentence, EssayResult essayR) throws IOException
	{
		List<RuleMatch> matches = langTool.check(" He have all my documents .");
		 
		for (RuleMatch match : matches) {
		  System.out.println("Potential error at line " +
		      match.getLine() + ", column " +
		      match.getColumn() + ": " + match.getMessage());
		  System.out.println("Suggested correction: " +
		      match.getSuggestedReplacements());
		}
		
	}
	
	// score 1.b
	
	public void SentenceSubAgree(String[] sentencePOS,
			String[] sentenceChunk, Span[] sentenceSpan, EssayResult essayR) {
		// case 0, check the VP in the sentence
		int i=0;
		for(; i<sentencePOS.length; i++) {
			if (sentencePOS[i].contains("VB")) break;
			//if( !sentencePOS.toString().contains("VB")) return; 
		}
		if( !sentencePOS[i].contains("VB")) return; 
		String[] pos=sentencePOS;
		// check the auxiliary verbs
		if(pos[0].contains("VB")){
		 if(pos[0].contains("does")||pos[0].contains("Does")||pos[0].contains("Has")||pos[0].contains("has")) {
			 // we find a singular auxiliary verbs
			 // find the real verb and NB
			 int vi=-1, ni=-1;
			 
			 for( i=1; i<sentenceSpan.length; i++){
				if(sentenceSpan[i].toString().contains("NP")&&vi==-1) {vi=i;}
				if(sentenceSpan[i].toString().contains("VP")&&ni==-1) {ni=i;break;} 
			 }
			 if(ni==-1) {
				 // something similar to "do it as soon as possible!"
				 essayR.addResult ("1.b") ;
				 return ;}
			 if(vi==-1) {
				 // can not find anything to match. format is wrong
				 // essayR.addResult("1.c");
				  return;
			 }
			 for( i=sentenceSpan[vi].getStart(); i<=sentenceSpan[vi].getEnd();i++){
				if(pos[i].contains("NNS")||pos[i].contains("NNPS")) {
					 essayR.addResult ("1.b") ;
				}
			 }
			 
			 
			 
			 
			 
		    }	
		 else if(pos[0].contains("do")||pos[0].contains("Do")||pos[0].contains("Have")||pos[0].contains("have")) {
			 
		    }
		 else if(pos[0].contains("Is")||pos[0].contains("is")) {
			 
		    }
		 else if(pos[0].contains("are")||pos[0].contains("Are")) {
			 
		    }
		   
		}
		// check two special case  start with do and how
         if(pos[0].contains("WRB")){
			
			
		}
		
		// TO check the sentence sub agreement 
		
		
		
		 
	}
 
	
	
	
	// score 1.c
	
	public void SentenceVerbCheck(String sentence, EssayResult essayR) {
		
		
	}
	
	
	public static void getChunkPOS(String input, String[] POSresult, String[] Chunkresult , Span[] sentenceSpan) {
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
							 POSresult=sample.toString().split(" ");
						 	System.out.println(sample.toString());
						//	System.out.println("POS end");
								perfMon.incrementCounter();
						}
					  //	perfMon.stopAndPrintFinalResult();
					 
					 
						String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
						 
						for (String s : result)
							System.out.println(s);
						//System.out.println("Result end ---------------------");
						Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
						for (Span s : span)
							System.out.println(s.toString());
				        sentenceSpan=span;
					 
					 
					 
					 
					 
					 
					 
					 
					 
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

