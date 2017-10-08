public class Client {
	public static void main(String[] args) {
		
		testComposite();
	}
	public static void testComposite() {
		FileComponent root=new Directory("");
		FileComponent read=new Directory("read");
		FileComponent jim=new Directory("jim");
		FileComponent tools=new Directory("tools");
		FileComponent eclipse=new Directory("eclipse");
		FileComponent conf=new Directory("conf");
		FileComponent as=new Directory("as");
		FileComponent txt=new Text("txt");
		FileComponent ftxt=new Text("ftxt");
		as.add(txt);
		
		eclipse.add(conf);
		conf.add(ftxt);
		
		tools.add(eclipse);
		tools.add(as);
		
		jim.add(read);
		jim.add(tools);
		
		root.add(jim);
		
		System.out.println(root.getSize());
	}

}
