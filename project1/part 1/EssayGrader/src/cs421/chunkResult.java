package cs421;

import opennlp.tools.util.Span;

public class chunkResult {
	public  String[] sentencePOS ;
	public String[] sentenceChunk;
	public Span[] sentenceSpan;
	
	public chunkResult(){
		sentencePOS =null;
		sentenceChunk=null;
		sentenceSpan=null;
	}
}
