package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		//Delete the lines below and add your code here
		Tuple returned = this.getChild().next();
		while(returned != null){
			if(!this.getChild().from.equals(whereTablePredicate)){
				return returned;
			}
			else{
				ArrayList<Attribute> list = returned.getAttributeList();
				for(int i = 0; i<list.size(); i++){
					if(list.get(i).getAttributeName().equals(whereAttributePredicate)){
						if(list.get(i).getAttributeValue().equals(whereValuePredicate)){
							return returned;
						}
						else break;
					}
				}
			}
			returned = this.getChild().next();
		}
		return returned;
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}