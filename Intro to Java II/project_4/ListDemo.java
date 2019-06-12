package listdemo;
/**
 * A program that demonstrates the use of the List class.
 * @author Duncan
 * @author Trevor DeBardeleben
 * @since 4/15/2016
 */
public class ListDemo
{
	public static void main(String[] args)
	{
		List<String> staff = new List();
		staff.addFirst("Tolliver");
		staff.addFirst("Rusk");
		staff.addFirst("Hector");
		staff.addFirst("Davis");
		// | in the comments indicates the iterator position
		ListIteratorAPI<String> iterator = staff.listIterator(); // |DHRT
		iterator.next(); // D|HRT
		iterator.next(); // DH|RT
		// Add more elements after second element
		iterator.add("Johnson"); // DHJ|RT
		iterator.add("Nguyen"); // DHJN|RT
		iterator.next(); // DHJNR|T
		// Remove last traversed element
		iterator.remove(); // DHJN|T
		// Print all elements backward and forward
		System.out.println("Forward version of the list is:");
		iterator = staff.listIterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
		System.out.println("Backward version of the list is");
		iterator = staff.listIterator();
		while (iterator.hasPrevious())
			System.out.println(iterator.previous());
		//*******************Begin Project 4 Code Here ****************************
		
                staff.add("Busselle"); //DHJNTB|
                staff.add(1, "Gluth"); //DG|HJNTB                
                staff.addLast("Arnold"); //DGHJNTBA|
                staff.addFirst("Hoying"); //H|DGHJNTBA
                System.out.print("The name currently at the head of the list is ");
                System.out.println(staff.element() + ".");
                System.out.println("There are " + staff.size() + " names on the list.");
                System.out.println("The fourth name on the list is " + staff.get(3) + "."); //Hector
		System.out.println("The last name on the list is " + staff.get((int) staff.size()-1)); //Arnold
                System.out.println("The name at the head of the list is " + staff.poll() + "."); //Hoying
                System.out.println("Now, the name at the head of the list is " + staff.peek() + "."); //Davis
		System.out.println(staff.getFirst() + " is at the head of the list"); //Davis
                System.out.println(staff.getLast() + " is at the tail of the list"); //Arnold
                System.out.println(staff.remove(0) + " has been removed form the head of the list"); //David GHJNTBA
                System.out.println(staff.remove((int) staff.size() - 1) + " has been removed form the tail of the list"); //Arnold GHJNTB 
                staff.set(1, "Lambert"); //GLJNTB 
                // Print all elements backward and forward
		System.out.println("Forward version of the list is:"); //GLJNTB 
		iterator = staff.listIterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
		System.out.println("Backward version of the list is"); //BTNJLG
		iterator = staff.listIterator();
		while (iterator.hasPrevious())
			System.out.println(iterator.previous());
                System.out.println("There are " + staff.size() + " names still on the list.");


//*******************End Project 4 Code************************************
	}
}
