package campos.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.TreeSet;

import campos.models.UserAccount;
import campos.models.UserAccountBag;

public class DataLoader implements DataConstants{
	public static void main(String[] args) {
		UserAccountBag bag = loadUsers();
		bag.display();
		UserAccount user = bag.findByUsername("CurtK2");
		System.out.println(user);
	}
	
	public static UserAccountBag loadUsers() {
		UserAccountBag bag = null;
		File file = new File(USER_BAG_SRC);
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			bag = (UserAccountBag) ois.readObject();
			ois.close();
			System.out.println("Successfully read from " + file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bag;
	}
}
