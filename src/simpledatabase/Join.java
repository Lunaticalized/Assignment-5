package simpledatabase;

import java.util.ArrayList;

public class Join extends Operator {

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	// Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();

	}

	/**
	 * It is used to return a new tuple which is already joined by the common
	 * attribute
	 * 
	 * @return the new joined tuple
	 */
	// The record after join with two tables
	@Override
	public Tuple next() {
		// Delete the lines below and add your code here
		Tuple e = leftChild.next();
		while (e != null) {
			tuples1.add(e);
			e = leftChild.next();
		}
		if (tuples1.size() == 0)
			return null;

		Tuple returned = rightChild.next();
		while (returned != null) {
			ArrayList<Attribute> list1 = returned.getAttributeList();
			for (int tupleIndex = 0; tupleIndex < tuples1.size(); tupleIndex++) {
				ArrayList<Attribute> list2 = tuples1.get(tupleIndex).getAttributeList();
				for (int i = 0; i < list1.size(); i++) {
					for (int j = 0; j < list2.size(); j++) {
						if (list1.get(i).getAttributeName().equals(list2.get(j).getAttributeName())
								&& list1.get(i).getAttributeValue().equals(list2.get(j).getAttributeValue())) {
							newAttributeList = new ArrayList<Attribute>();
							for (int k = 0; k < list1.size(); k++) {
								newAttributeList.add(list1.get(k));
							}
							for (int k = 0; k < list2.size(); k++) {
								if (k != j) {
									newAttributeList.add(list2.get(k));
								}
							}

							return new Tuple(newAttributeList);
						}

					}
				}
			}
			returned = rightChild.next();
		}
		return null;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		if (joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return (newAttributeList);
	}

}