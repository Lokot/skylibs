package ru.skysoftlab.skylibs.web.ui;

import com.vaadin.ui.ComboBox;

import ru.skysoftlab.skylibs.web.dto.VaadinItemDto;

public class VaadinUtils {

	/**
	 * Сделает select ComboBox по объекту.
	 * 
	 * @param cb
	 * @param itemObject
	 */
	public static void selectComboBox(ComboBox cb, Object itemObject) {
		for (Object itemId : cb.getItemIds()) {
			VaadinItemDto dto = (VaadinItemDto) itemId;
			if (dto.getObj().equals(itemObject)) {
				cb.select(itemId);
			}
		}
	}
	
	/**
	 * 
	 * @param cb
	 */
	public static void comboboxReadOnly(ComboBox cb) {
		cb.setTextInputAllowed(false);
		cb.setNullSelectionAllowed(false);
	}

	/**
	 * 
	 * @param cb
	 */
	public static void comboboxReadOnlyAndSelectFirst(ComboBox cb) {
		comboboxReadOnly(cb);
		selectFirst(cb);
	}

	/**
	 * Установит выбранным первый элемент.
	 * 
	 * @param cb
	 */
	public static void selectFirst(ComboBox cb) {
		cb.setValue(cb.getItemIds().iterator().next());
	}
}
