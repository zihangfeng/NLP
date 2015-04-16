package cs421;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.util.InvalidFormatException;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class ShareInstance {
	private static final ShareInstance EAinstance= new ShareInstance();
	static JLanguageTool langTool ;
	static MaxentTagger tagger;
	static POSModel model;
	 
	private ShareInstance() {
		langTool = new JLanguageTool(new AmericanEnglish());
		tagger =  new MaxentTagger("english-left3words-distsim.tagger");
		model = new POSModelLoader().load(new File("en-pos-maxent.bin"));
		
	}
	
	public static JLanguageTool getJLanguageTool(){
		return langTool;
	}
	
	public static MaxentTagger getTagger(){
		return tagger;
	}
	
	public static POSModel getPOSModel(){
		return model;
	}
}
