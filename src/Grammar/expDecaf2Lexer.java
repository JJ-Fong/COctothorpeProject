// Generated from C:\Users\Javier Fong\Documents\Universidad\2016\Ciclo 1\Clases\Compiladores\COctothorpeProject\src\Grammar\expDecaf2.g4 by ANTLR 4.4
package Grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class expDecaf2Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__37=1, T__36=2, T__35=3, T__34=4, T__33=5, T__32=6, T__31=7, T__30=8, 
		T__29=9, T__28=10, T__27=11, T__26=12, T__25=13, T__24=14, T__23=15, T__22=16, 
		T__21=17, T__20=18, T__19=19, T__18=20, T__17=21, T__16=22, T__15=23, 
		T__14=24, T__13=25, T__12=26, T__11=27, T__10=28, T__9=29, T__8=30, T__7=31, 
		T__6=32, T__5=33, T__4=34, T__3=35, T__2=36, T__1=37, T__0=38, ID=39, 
		NUM=40, COMMENTS=41, WS=42, CHAR=43;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'", "'*'", "'+'"
	};
	public static final String[] ruleNames = {
		"T__37", "T__36", "T__35", "T__34", "T__33", "T__32", "T__31", "T__30", 
		"T__29", "T__28", "T__27", "T__26", "T__25", "T__24", "T__23", "T__22", 
		"T__21", "T__20", "T__19", "T__18", "T__17", "T__16", "T__15", "T__14", 
		"T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", 
		"T__4", "T__3", "T__2", "T__1", "T__0", "LETTER", "DIGIT", "ID", "NUM", 
		"COMMENTS", "WS", "CHAR"
	};


	public expDecaf2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "expDecaf2.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2-\u010d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3#\3#\3$\3$"+
		"\3%\3%\3%\3%\3%\3%\3&\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\7*\u00e8\n*\f"+
		"*\16*\u00eb\13*\3+\3+\7+\u00ef\n+\f+\16+\u00f2\13+\3,\3,\3,\3,\7,\u00f8"+
		"\n,\f,\16,\u00fb\13,\3,\3,\3-\6-\u0100\n-\r-\16-\u0101\3-\3-\3.\3.\3."+
		"\3.\3.\3.\5.\u010c\n.\2\2/\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25"+
		"\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32"+
		"\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O\2Q\2S)U*W+Y,[-\3\2\7\4"+
		"\2C\\c|\4\2\f\f\17\17\7\2\13\f\16\17\"\"))~~\6\2\"\61<B]b}\177\5\2\f\f"+
		"$$))\u0113\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W"+
		"\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\3]\3\2\2\2\5_\3\2\2\2\7a\3\2\2\2\tc\3\2"+
		"\2\2\13i\3\2\2\2\rk\3\2\2\2\17m\3\2\2\2\21o\3\2\2\2\23r\3\2\2\2\25v\3"+
		"\2\2\2\27x\3\2\2\2\31~\3\2\2\2\33\u0081\3\2\2\2\35\u0084\3\2\2\2\37\u0089"+
		"\3\2\2\2!\u008b\3\2\2\2#\u008d\3\2\2\2%\u0095\3\2\2\2\'\u009a\3\2\2\2"+
		")\u009c\3\2\2\2+\u00a4\3\2\2\2-\u00a9\3\2\2\2/\u00b0\3\2\2\2\61\u00b5"+
		"\3\2\2\2\63\u00b7\3\2\2\2\65\u00b9\3\2\2\2\67\u00bb\3\2\2\29\u00bd\3\2"+
		"\2\2;\u00bf\3\2\2\2=\u00c6\3\2\2\2?\u00c8\3\2\2\2A\u00cb\3\2\2\2C\u00ce"+
		"\3\2\2\2E\u00d0\3\2\2\2G\u00d3\3\2\2\2I\u00d5\3\2\2\2K\u00db\3\2\2\2M"+
		"\u00de\3\2\2\2O\u00e0\3\2\2\2Q\u00e2\3\2\2\2S\u00e4\3\2\2\2U\u00ec\3\2"+
		"\2\2W\u00f3\3\2\2\2Y\u00ff\3\2\2\2[\u010b\3\2\2\2]^\7_\2\2^\4\3\2\2\2"+
		"_`\7.\2\2`\6\3\2\2\2ab\7/\2\2b\b\3\2\2\2cd\7y\2\2de\7j\2\2ef\7k\2\2fg"+
		"\7n\2\2gh\7g\2\2h\n\3\2\2\2ij\7]\2\2j\f\3\2\2\2kl\7,\2\2l\16\3\2\2\2m"+
		"n\7*\2\2n\20\3\2\2\2op\7k\2\2pq\7h\2\2q\22\3\2\2\2rs\7k\2\2st\7p\2\2t"+
		"u\7v\2\2u\24\3\2\2\2vw\7>\2\2w\26\3\2\2\2xy\7h\2\2yz\7c\2\2z{\7n\2\2{"+
		"|\7u\2\2|}\7g\2\2}\30\3\2\2\2~\177\7#\2\2\177\u0080\7?\2\2\u0080\32\3"+
		"\2\2\2\u0081\u0082\7>\2\2\u0082\u0083\7?\2\2\u0083\34\3\2\2\2\u0084\u0085"+
		"\7x\2\2\u0085\u0086\7q\2\2\u0086\u0087\7k\2\2\u0087\u0088\7f\2\2\u0088"+
		"\36\3\2\2\2\u0089\u008a\7)\2\2\u008a \3\2\2\2\u008b\u008c\7}\2\2\u008c"+
		"\"\3\2\2\2\u008d\u008e\7R\2\2\u008e\u008f\7t\2\2\u008f\u0090\7q\2\2\u0090"+
		"\u0091\7i\2\2\u0091\u0092\7t\2\2\u0092\u0093\7c\2\2\u0093\u0094\7o\2\2"+
		"\u0094$\3\2\2\2\u0095\u0096\7g\2\2\u0096\u0097\7n\2\2\u0097\u0098\7u\2"+
		"\2\u0098\u0099\7g\2\2\u0099&\3\2\2\2\u009a\u009b\7\177\2\2\u009b(\3\2"+
		"\2\2\u009c\u009d\7d\2\2\u009d\u009e\7q\2\2\u009e\u009f\7q\2\2\u009f\u00a0"+
		"\7n\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a3\7p\2\2\u00a3"+
		"*\3\2\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7w\2\2\u00a7"+
		"\u00a8\7g\2\2\u00a8,\3\2\2\2\u00a9\u00aa\7u\2\2\u00aa\u00ab\7v\2\2\u00ab"+
		"\u00ac\7t\2\2\u00ac\u00ad\7w\2\2\u00ad\u00ae\7e\2\2\u00ae\u00af\7v\2\2"+
		"\u00af.\3\2\2\2\u00b0\u00b1\7e\2\2\u00b1\u00b2\7j\2\2\u00b2\u00b3\7c\2"+
		"\2\u00b3\u00b4\7t\2\2\u00b4\60\3\2\2\2\u00b5\u00b6\7\'\2\2\u00b6\62\3"+
		"\2\2\2\u00b7\u00b8\7\60\2\2\u00b8\64\3\2\2\2\u00b9\u00ba\7+\2\2\u00ba"+
		"\66\3\2\2\2\u00bb\u00bc\7-\2\2\u00bc8\3\2\2\2\u00bd\u00be\7?\2\2\u00be"+
		":\3\2\2\2\u00bf\u00c0\7t\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2\7v\2\2\u00c2"+
		"\u00c3\7w\2\2\u00c3\u00c4\7t\2\2\u00c4\u00c5\7p\2\2\u00c5<\3\2\2\2\u00c6"+
		"\u00c7\7=\2\2\u00c7>\3\2\2\2\u00c8\u00c9\7(\2\2\u00c9\u00ca\7(\2\2\u00ca"+
		"@\3\2\2\2\u00cb\u00cc\7~\2\2\u00cc\u00cd\7~\2\2\u00cdB\3\2\2\2\u00ce\u00cf"+
		"\7@\2\2\u00cfD\3\2\2\2\u00d0\u00d1\7?\2\2\u00d1\u00d2\7?\2\2\u00d2F\3"+
		"\2\2\2\u00d3\u00d4\7\61\2\2\u00d4H\3\2\2\2\u00d5\u00d6\7e\2\2\u00d6\u00d7"+
		"\7n\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da\7u\2\2\u00da"+
		"J\3\2\2\2\u00db\u00dc\7@\2\2\u00dc\u00dd\7?\2\2\u00ddL\3\2\2\2\u00de\u00df"+
		"\7#\2\2\u00dfN\3\2\2\2\u00e0\u00e1\t\2\2\2\u00e1P\3\2\2\2\u00e2\u00e3"+
		"\4\62;\2\u00e3R\3\2\2\2\u00e4\u00e9\5O(\2\u00e5\u00e8\5O(\2\u00e6\u00e8"+
		"\5Q)\2\u00e7\u00e5\3\2\2\2\u00e7\u00e6\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00eaT\3\2\2\2\u00eb\u00e9\3\2\2\2"+
		"\u00ec\u00f0\5Q)\2\u00ed\u00ef\5Q)\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3"+
		"\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1V\3\2\2\2\u00f2\u00f0"+
		"\3\2\2\2\u00f3\u00f4\7\61\2\2\u00f4\u00f5\7\61\2\2\u00f5\u00f9\3\2\2\2"+
		"\u00f6\u00f8\n\3\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7"+
		"\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc"+
		"\u00fd\b,\2\2\u00fdX\3\2\2\2\u00fe\u0100\t\4\2\2\u00ff\u00fe\3\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u0104\b-\2\2\u0104Z\3\2\2\2\u0105\u010c\5O(\2\u0106\u010c\5"+
		"Q)\2\u0107\u010c\t\5\2\2\u0108\u0109\7\u0080\2\2\u0109\u010c\7\13\2\2"+
		"\u010a\u010c\t\6\2\2\u010b\u0105\3\2\2\2\u010b\u0106\3\2\2\2\u010b\u0107"+
		"\3\2\2\2\u010b\u0108\3\2\2\2\u010b\u010a\3\2\2\2\u010c\\\3\2\2\2\t\2\u00e7"+
		"\u00e9\u00f0\u00f9\u0101\u010b\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}