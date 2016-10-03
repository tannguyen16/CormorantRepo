import java.util.Comparator;

/**
 * The Class Person.
 */
public class Person {
	/** The id of the person. */
	private int id;

	/** The name of the person. */
	private String name;

	/** The gender of the person. */
	private String gender;

	/** The culture of the person. */
	private String culture;

	/** The occupation of the person. */
	private String occupation;

	/** The notes of the person . */
	private String notes;

	/**
	 * Instantiates a new person.
	 *
	 * @param id
	 *            the id
	 * @param name
	 *            the name
	 * @param gender
	 *            the gender
	 * @param culture
	 *            the culture
	 * @param occupation
	 *            the occupation
	 * @param notes
	 *            the notes
	 */
	public Person(int id, String name, String gender, String culture, String occupation, String notes) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.culture = culture;
		this.occupation = occupation;
		this.notes = notes;
	}

	/**
	 * TODO: Comment here.
	 * 
	 * @param csvRowData
	 */
	public Person(String[] csvRowData) {
		this.id = Integer.parseInt(csvRowData[0]);
		this.name = csvRowData[1];
		this.gender = csvRowData[2];
		this.culture = csvRowData[3];
		this.occupation = csvRowData[4];
		this.notes = csvRowData[5];
	}


	
	 
	@Override
	public String toString() {
		if (name.isEmpty()) {
			name="Anonymous";
		}
		if (gender == null) {
			gender="Unknown";
		}
		if (culture.isEmpty()){
			culture="Unknown";
		}
		if (occupation.isEmpty()){
			occupation="Unknown";
		}
		if (notes.isEmpty()){
			notes="none";
		}
		return name+" {id=" +id + " gender="+gender + " culture="+culture + " occupation="+occupation + 
				" notes="+notes +"}";
	}
	
	

	/*
	 * Comments: This is the method modified from DataStorage
	 * 
	 * @Override public String toString() { return "Person {id=" +id + " name="
	 * + name + " gender="+gender + " culture="+culture + " occupation="
	 * +occupation + " notes="+notes +"}"; }
	 */

	public String[] toCSVRowArray() {
		return new String[] { Integer.toString(id), name, gender, culture, notes };
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender
	 *            the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the culture.
	 *
	 * @return the culture
	 */
	public String getCulture() {
		return culture;
	}

	/**
	 * Sets the culture.
	 *
	 * @param culture
	 *            the new culture
	 */
	public void setCulture(String culture) {
		this.culture = culture;
	}

	/**
	 * Gets the occupation.
	 *
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * Sets the occupation.
	 *
	 * @param occupation
	 *            the new occupation
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes
	 *            the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	
	//Checks if invalid chars are entered and returns -1 if there are and 0 if there aren't
	public int checkForUnallowedInput(String name, String culture, String occupation){
		String unallowedChars="1234567890!@#$%^&*()-+=[]{}?<>;";
		
		for (int i=0; i<unallowedChars.length();i++){
			for(int j=0; j<name.length(); j++){
				if (name.charAt(j)==((unallowedChars.charAt(i)))){

					return -1;

				}
			}
			for (int j = 0; j < culture.length(); j++) {
				if (culture.charAt(j) == ((unallowedChars.charAt(i)))) {
					return -1;
				}
			}
			for (int j = 0; j < occupation.length(); j++) {
				if (occupation.charAt(j) == ((unallowedChars.charAt(i)))) {
					return -1;
				}
			}

		}
		return 0;

	}
	
	
	public static Comparator<Person> personNameComparator=new Comparator<Person>(){

		@Override
		public int compare(Person p1, Person p2) {
			String name1=p1.getName().toLowerCase();
			String name2=p2.getName().toLowerCase();
			return name1.compareTo(name2);
		}
		
	};
	public void replacePerson(Person person){
		this.name=person.getName();
		this.gender=person.getGender();
		this.culture=person.getCulture();
		this.occupation=person.getOccupation();
		this.notes=person.getNotes();
	}
	//public int checkEquality(Person person){
		//if (this.name.equals(person.getName())&&this.gender.equals(person.getGender())&&this.culture.equals(person.getCulture())&&this.occupation.equals(person.oc)){
			
		//}
	//}
}
