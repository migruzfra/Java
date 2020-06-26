package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

	private final String title;
	private final List<MenuItem> items = new ArrayList<>();

	public Menu(String title, MenuItem... menuItems) {
		this.title = title;
		items.addAll(Arrays.asList(menuItems));
	}

	public int getSize() {
		return items.size();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(title.toUpperCase()).append("\n");
		for (int i = 0; i < items.size(); i++) {
			sb.append(String.format("%d - %s\n", i + 1, items.get(i).getTitle()));
		}
		sb.append("Elija una opción: ");
		return sb.toString();
	}

}
