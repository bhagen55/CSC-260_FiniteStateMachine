package proj2;

import proj2.document.Document;

public class TestingSaveAndLoad{
	Document d;
	FileFormatController f;
	public TestingSaveAndLoad()
	{
		/*
		d= new Document();
		f= new FileFormatController(d);
		d.addVertex("A");
		d.addVertex("B");
		d.addVertex("C");
		d.addEdge("A","B","z");
		f.saveFile();
		*/
	}

	public static void main(String[] args)
	{
		d= new Document();
		f= new FileFormatController(d);
		d.addVertex("A");
		d.addVertex("B");
		d.addVertex("C");
		d.addEdge("A","B","z");
		f.saveFile();
	}
}