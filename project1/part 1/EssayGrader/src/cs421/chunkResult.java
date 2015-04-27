package cs421;

import opennlp.tools.parser.Parse;
import opennlp.tools.util.Span;

public class chunkResult {
	public  String[] sentencePOS ;
	public String[] sentenceChunk;
	public Span[] sentenceSpan;
	public Parse[] sentenceParse;
	
	public chunkResult(){
		sentencePOS =null;
		sentenceChunk=null;
		sentenceSpan=null;
		sentenceParse=null;
	}
}
