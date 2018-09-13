package ngordnet;

public class Synset {
	private int id;
	private String[] synlist;
	private String definition;
	public Synset(int id, String[] synlist, String definition) {
		this.id = id;
		this.synlist = synlist;
		this.definition = definition;
	}
	public int getID() {
		return id;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public String[] getSynList() {
		return synlist;
	}
	
}
