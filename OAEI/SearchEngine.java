package EXONA.OAEI;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class SearchEngine {

	private IndexSearcher searcher = null;
	private QueryParser parser = null;

	/**
	 * Creates a new instance of SearchEngine
	 */
	public SearchEngine(String sdirectory) throws IOException {
		searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File(sdirectory))));
		parser = new QueryParser("content", new ClassicAnalyzer());
	}

	public TopDocs performSearch(String queryString, int n) throws IOException, ParseException {
		if (queryString.length() > 5) {
			Query query = parser.parse(queryString);
			return searcher.search(query, n);
		}
		return (null);
	}

	public Document getDocument(int docId) throws IOException {
		return searcher.doc(docId);
	}

	public static ArrayList<String> search(String text, String sdirectory) throws IOException, ParseException {

		SearchEngine se = new SearchEngine(sdirectory);
		ArrayList<String> best = new ArrayList<String>();

		if (text != null) {
			TopDocs topDocs = se
					.performSearch(text.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "").replaceAll("     ", ""), 1);

			if (topDocs != null) {

				ScoreDoc[] hits = topDocs.scoreDocs;

				if (hits.length >= 0) {
					// float score = hits[0].score ;
					for (int i = 0; i < hits.length; i++) {
						Document doc = se.getDocument(hits[i].doc);

						if (hits[i].score >= 0) {

							best.add(doc.get("id"));
						}
					}
				}
			}
		}
		// System.out.println("BEST: "+best);
		return (best);
	}
}
