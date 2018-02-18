package ru.skysoftlab.skylibs.utils;

public class Translit {

	public static String transliterate(String message) {
		char[] abcCyr = { ' ', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
				'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е',
				'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
				'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		String[] abcLat = { " ", "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p",
				"r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "i", "", "e", "ju", "ja", "A", "B", "V", "G",
				"D", "E", "E", "Zh", "Z", "I", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "Ts",
				"Ch", "Sh", "Sch", "", "I", "", "E", "Ju", "Ja", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F",
				"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			for (int x = 0; x < abcCyr.length; x++) {
				if (message.charAt(i) == abcCyr[x]) {
					builder.append(abcLat[x]);
				}
			}
		}
		return builder.toString();
	}

	public static String cyr2lat(char ch) {
		switch (ch) {
		case 'А':
			return "A";
		case 'Б':
			return "B";
		case 'В':
			return "V";
		case 'Г':
			return "G";
		case 'Д':
			return "D";
		case 'Е':
			return "E";
		case 'Ё':
			return "JE";
		case 'Ж':
			return "ZH";
		case 'З':
			return "Z";
		case 'И':
			return "I";
		case 'Й':
			return "Y";
		case 'К':
			return "K";
		case 'Л':
			return "L";
		case 'М':
			return "M";
		case 'Н':
			return "N";
		case 'О':
			return "O";
		case 'П':
			return "P";
		case 'Р':
			return "R";
		case 'С':
			return "S";
		case 'Т':
			return "T";
		case 'У':
			return "U";
		case 'Ф':
			return "F";
		case 'Х':
			return "KH";
		case 'Ц':
			return "C";
		case 'Ч':
			return "CH";
		case 'Ш':
			return "SH";
		case 'Щ':
			return "JSH";
		case 'Ъ':
			return "HH";
		case 'Ы':
			return "IH";
		case 'Ь':
			return "JH";
		case 'Э':
			return "EH";
		case 'Ю':
			return "JU";
		case 'Я':
			return "JA";
		default:
			return String.valueOf(ch);
		}
	}

	public static String cyr2lat(String s) {
		StringBuilder sb = new StringBuilder(s.length() * 2);
		for (char ch : s.toCharArray()) {
			sb.append(cyr2lat(ch));
		}
		return sb.toString();
	}
}
