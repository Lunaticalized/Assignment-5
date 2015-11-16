package simpledatabase;

import java.util.ArrayList;
import java.util.Comparator;

public class Sort extends Operator {

	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	public Sort(Operator child, String orderPredicate) {
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();

	}

	/**
	 * The function is used to return the sorted tuple
	 * 
	 * @return tuple
	 */
	@Override
	public Tuple next() {

		// Delete the lines below and add your code here
		Tuple e = this.getChild().next();
		while (e != null) {
			tuplesResult.add(e);
			e = this.getChild().next();
		}
		if(tuplesResult.isEmpty())return null;
		Tuple toBeReturned = tuplesResult.get(0);
		int pos;
		for (pos = 0; pos < toBeReturned.getAttributeList().size(); pos++) {
			if (toBeReturned.getAttributeName(pos).equals(orderPredicate))
				break;
		}
		Type type = toBeReturned.getAttributeType(pos);
		for (int i = 1; i < tuplesResult.size(); i++) {
			Tuple compared = tuplesResult.get(i);
			switch (type.type) {
			case INTEGER:
				toBeReturned = Integer.parseInt(toBeReturned.getAttributeValue(pos).toString()) < Integer
						.parseInt(compared.getAttributeValue(pos).toString()) ? toBeReturned : compared;
				break;

			case DOUBLE:
				toBeReturned = Double.parseDouble(toBeReturned.getAttributeValue(pos).toString()) < Double
						.parseDouble(compared.getAttributeValue(pos).toString()) ? toBeReturned : compared;
				break;

			case LONG:
				toBeReturned = Long.parseLong(toBeReturned.getAttributeValue(pos).toString()) < Long
						.parseLong(compared.getAttributeValue(pos).toString()) ? toBeReturned : compared;
				break;

			case SHORT:
				toBeReturned = Short.parseShort(toBeReturned.getAttributeValue(pos).toString()) < Short
						.parseShort(compared.getAttributeValue(pos).toString()) ? toBeReturned : compared;
				break;

			case FLOAT:
				toBeReturned = Float.parseFloat(toBeReturned.getAttributeValue(pos).toString()) < Float
						.parseFloat(compared.getAttributeValue(pos).toString()) ? toBeReturned : compared;
				break;

			case STRING:
				toBeReturned = (toBeReturned.getAttributeValue(pos).toString())
						.compareTo((compared.getAttributeValue(pos).toString())) <= 0 ? toBeReturned : compared;
				break;

			case BOOLEAN:
				toBeReturned = Boolean.parseBoolean(toBeReturned.getAttributeValue(pos).toString()) ? toBeReturned
						: compared;
				break;

			case CHAR:
				toBeReturned = (toBeReturned.getAttributeValue(pos).toString())
						.charAt(0) < (compared.getAttributeValue(pos).toString()).charAt(0) ? toBeReturned : compared;
				break;

			case BYTE:
				toBeReturned = Byte.parseByte(toBeReturned.getAttributeValue(pos).toString()) < Byte
						.parseByte(compared.getAttributeValue(pos).toString()) ? toBeReturned : compared;
				break;

			}
		}
		tuplesResult.remove(toBeReturned);
		return toBeReturned;
	}

	/**
	 * The function is used to get the attribute list of the tuple
	 * 
	 * @return attribute list
	 */
	public ArrayList<Attribute> getAttributeList() {
		return child.getAttributeList();
	}

}