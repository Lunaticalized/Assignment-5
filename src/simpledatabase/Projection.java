package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}

	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		Tuple returned = this.getChild().next();
		if(returned == null) return null;
		Attribute attribute = null;
		for(int i = 0; i < returned.getAttributeList().size();i++){
			if(returned.getAttributeName(i).equals(attributePredicate)){
				attribute = returned.getAttributeList().get(i);
				newAttributeList = new ArrayList<Attribute>();
				newAttributeList.add(attribute);
				Tuple output = new Tuple(newAttributeList);
				return output;
				
			}
		}
		return null;
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}