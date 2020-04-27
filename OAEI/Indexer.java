package EXONA.OAEI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.analysis.standard.ClassicAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {

	/**
	 * Creates a new instance of Indexer
	 */
	public Indexer() {
	}

	private IndexWriter indexWriter = null;

	public IndexWriter getIndexWriter(boolean create, String directory) throws IOException {
		if (indexWriter == null) {
			Directory indexDir = FSDirectory.open(new File(directory));
			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_10_2, new ClassicAnalyzer());
			indexWriter = new IndexWriter(indexDir, config);
			indexWriter.deleteAll();
		}
		return indexWriter;
	}

	public void closeIndexWriter() throws IOException {
		if (indexWriter != null) {
			indexWriter.close();
		}
	}

	public void indexInstance(Instance ins, String directory) throws IOException {

		IndexWriter writer = getIndexWriter(false, directory);
		Document doc = new Document();
		if (ins.getId() != null) {
			doc.add(new StringField("id", ins.getId(), Field.Store.YES));
			String cont = "";

			doc.add(new StringField("Content1", ins.toString().replace("/", " "), Field.Store.YES));
			doc.add(new TextField("content", ins.toString() + cont.replace("/", " "), Field.Store.NO));
			writer.addDocument(doc);
		}
	}

	public void rebuildIndexes(ArrayList<Instance> list, String directory) throws IOException {

		getIndexWriter(true, directory);

		for (Instance instance : list) {
			indexInstance(instance, directory);
		}

		closeIndexWriter();
	}
}
